package sky.angin.cn.client

import android.util.Base64
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

class CryptoManager(private val client: OkHttpClient) {

    private val lock = Any()
    private var secretKey: SecretKeySpec? = null

    fun encrypt(plainText: String, apiBase: String): String? {
        val key = getKey(apiBase) ?: return null
        return try {
            val iv = ByteArray(12).also { SecureRandom().nextBytes(it) }
            val cipher = Cipher.getInstance("AES/GCM/NoPadding")
            cipher.init(Cipher.ENCRYPT_MODE, key, GCMParameterSpec(128, iv))
            val ciphertext = cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))
            val result = ByteArray(iv.size + ciphertext.size)
            System.arraycopy(iv, 0, result, 0, iv.size)
            System.arraycopy(ciphertext, 0, result, iv.size, ciphertext.size)
            Base64.encodeToString(result, Base64.NO_WRAP)
        } catch (e: Exception) {
            null
        }
    }

    fun decrypt(data: String, apiBase: String): String? {
        val key = getKey(apiBase) ?: return null
        return try {
            val decoded = Base64.decode(data, Base64.NO_WRAP)
            if (decoded.size <= 12) return null
            val iv = decoded.copyOfRange(0, 12)
            val ciphertext = decoded.copyOfRange(12, decoded.size)
            val cipher = Cipher.getInstance("AES/GCM/NoPadding")
            cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(128, iv))
            String(cipher.doFinal(ciphertext), Charsets.UTF_8)
        } catch (e: Exception) {
            null
        }
    }

    fun clearCache() {
        synchronized(lock) {
            secretKey = null
        }
    }

    private fun getKey(apiBase: String): SecretKeySpec? {
        synchronized(lock) {
            secretKey?.let { return it }
        }
        val url = "$apiBase/crypto/key"
        val request = Request.Builder().url(url).get().build()
        return try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return null
                val body = response.body?.string() ?: return null
                val json = JSONObject(body)
                if (json.optBoolean("success") && json.has("key")) {
                    val keyBytes = Base64.decode(json.getString("key"), Base64.NO_WRAP)
                    val key = SecretKeySpec(keyBytes, "AES")
                    synchronized(lock) {
                        if (secretKey == null) secretKey = key
                    }
                    key
                } else null
            }
        } catch (e: Exception) {
            null
        }
    }
}

package sky.angin.cn.client

import okhttp3.Headers
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class ResponseCache(private val maxAgeMs: Long = 10_000L) {

    private val cache = LinkedHashMap<String, CachedResponse>(16, 0.75f, true)

    data class CachedResponse(
        val code: Int,
        val message: String,
        val headers: Headers,
        val body: String,
        val expiresAt: Long
    ) {
        fun toResponse(request: Request): Response {
            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(code)
                .message(message)
                .headers(headers)
                .body(body.toResponseBody(headers.contentType()))
                .build()
        }
    }

    @Synchronized
    fun get(url: String): CachedResponse? {
        val entry = cache[url] ?: return null
        if (entry.expiresAt < System.currentTimeMillis()) {
            cache.remove(url)
            return null
        }
        return entry
    }

    @Synchronized
    fun put(url: String, response: Response, body: String) {
        val entry = CachedResponse(
            code = response.code,
            message = response.message,
            headers = response.headers,
            body = body,
            expiresAt = System.currentTimeMillis() + maxAgeMs
        )
        cache[url] = entry
    }

    @Synchronized
    fun clear() {
        cache.clear()
    }
}

package sky.angin.cn.client

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.RequestBody.Companion.toRequestBody
import okio.Buffer

class NetworkInterceptor(
    private val cryptoManager: CryptoManager,
    private val responseCache: ResponseCache
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()
        val method = request.method
        val hasBody = request.body != null
        val isCryptoKey = url.contains("/api/crypto/key")
        val isConfigSite = url.contains("/config/site")

        // GET /config/site 走缓存（不加密）
        if (!isCryptoKey && method.equals("GET", ignoreCase = true) && !hasBody && isConfigSite) {
            val cached = responseCache.get(url)
            if (cached != null) return cached.toResponse(request)
        }

        val shouldEncrypt = !isCryptoKey && hasBody && url.contains("/api/")
        var finalRequest = request
        if (shouldEncrypt) {
            val plainText = request.body?.let { readBody(it) }
            if (!plainText.isNullOrEmpty()) {
                val apiBase = extractApiBase(url)
                val encrypted = cryptoManager.encrypt(plainText, apiBase)
                if (encrypted != null) {
                    val mediaType = request.body?.contentType()
                    finalRequest = request.newBuilder()
                        .method(request.method, encrypted.toRequestBody(mediaType))
                        .header("X-Encrypted", "1")
                        .build()
                }
            }
        }

        val needsClearCache = !method.equals("GET", ignoreCase = true) &&
                (url.contains("/auth/login") || url.contains("/auth/logout") || url.contains("/auth/register"))
        if (needsClearCache) cryptoManager.clearCache()

        val response = chain.proceed(finalRequest)

        if (needsClearCache) cryptoManager.clearCache()

        // 解密响应（仅当请求被加密）
        if (shouldEncrypt && response.header("X-Encrypted") == "1") {
            val responseBody = response.body?.string()
            if (responseBody != null) {
                val apiBase = extractApiBase(url)
                val decrypted = cryptoManager.decrypt(responseBody, apiBase)
                if (decrypted != null) {
                    return response.newBuilder()
                        .body(decrypted.toResponseBody(response.body?.contentType()))
                        .build()
                }
            }
        }

        // 缓存 /config/site 响应（明文）
        if (isConfigSite && method.equals("GET", ignoreCase = true) && !hasBody) {
            val body = response.body?.string()
            if (body != null) {
                responseCache.put(url, response, body)
                return response.newBuilder()
                    .body(body.toResponseBody(response.body?.contentType()))
                    .build()
            }
        }

        return response
    }

    private fun readBody(body: okhttp3.RequestBody): String? {
        return try {
            val buffer = Buffer()
            body.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: Exception) {
            null
        }
    }

    private fun extractApiBase(url: String): String {
        val index = url.indexOf("/api/")
        return if (index >= 0) url.substring(0, index + 4) else ""
    }
}

package sky.angin.cn.client

import android.content.Context
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import org.json.JSONArray
import org.json.JSONObject

class PersistentCookieJar(context: Context) : CookieJar {

    private val prefs = context.getSharedPreferences("sky_cookies", Context.MODE_PRIVATE)
    private val memoryCache = mutableMapOf<String, List<Cookie>>()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val host = url.host()
        val existing = memoryCache[host] ?: loadFromPrefs(host)
        val merged = LinkedHashMap<String, Cookie>()
        for (c in existing) merged[c.name] = c
        for (c in cookies) merged[c.name] = c
        val list = merged.values.toList()
        memoryCache[host] = list
        persist(host, list)
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val host = url.host()
        val cookies = memoryCache[host] ?: loadFromPrefs(host)
        val now = System.currentTimeMillis()
        val valid = cookies.filter { it.expiresAt > now }
        if (valid.size != cookies.size) {
            memoryCache[host] = valid
            persist(host, valid)
        }
        return valid
    }

    private fun loadFromPrefs(host: String): List<Cookie> {
        val jsonStr = prefs.getString("host_$host", null) ?: return emptyList()
        return try {
            val array = JSONArray(jsonStr)
            val list = mutableListOf<Cookie>()
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                val builder = Cookie.Builder()
                    .name(obj.getString("name"))
                    .value(obj.getString("value"))
                    .domain(obj.getString("domain"))
                    .path(obj.getString("path"))
                    .expiresAt(obj.getLong("expiresAt"))
                if (obj.optBoolean("secure")) builder.secure()
                if (obj.optBoolean("httpOnly")) builder.httpOnly()
                list.add(builder.build())
            }
            list
        } catch (e: Exception) {
            emptyList()
        }.also { memoryCache[host] = it }
    }

    private fun persist(host: String, cookies: List<Cookie>) {
        val array = JSONArray()
        for (c in cookies) {
            val obj = JSONObject()
            obj.put("name", c.name)
            obj.put("value", c.value)
            obj.put("domain", c.domain)
            obj.put("path", c.path)
            obj.put("expiresAt", c.expiresAt)
            obj.put("secure", c.secure)
            obj.put("httpOnly", c.httpOnly)
            array.put(obj)
        }
        val json = array.toString()
        var success = prefs.edit().putString("host_$host", json).commit()
        if (!success) {
            success = prefs.edit().putString("host_$host", json).commit()
        }
        // 如果仍失败则忽略（下次重新保存）
    }
}

package sky.angin.cn.client

import com.google.gson.JsonObject
import retrofit2.http.*

interface ApiService {

    // 加密密钥
    @GET("crypto/key")
    suspend fun getCryptoKey(): JsonObject

    // 站点配置
    @GET("config/site")
    suspend fun getConfigSite(): JsonObject

    // 认证
    @GET("auth/me")
    suspend fun getAuthMe(): JsonObject

    @POST("auth/login")
    suspend fun login(@Body body: Map<String, Any?>): JsonObject

    @POST("auth/register")
    suspend fun register(@Body body: Map<String, Any?>): JsonObject

    @POST("auth/logout")
    suspend fun logout(): JsonObject

    @GET("auth/send-reset-code/{email}")
    suspend fun sendResetCode(@Path("email") email: String): JsonObject

    // 游戏账号
    @GET("accounts")
    suspend fun getAccounts(): JsonObject

    @POST("accounts/add/start")
    suspend fun addAccountStart(@Body body: Map<String, Any?>): JsonObject

    @GET("accounts/add/status/{taskId}")
    suspend fun addAccountStatus(@Path("taskId") taskId: String): JsonObject

    @POST("accounts/{accountId}/friend-relations")
    suspend fun friendRelations(
        @Path("accountId") accountId: String,
        @Body body: Map<String, Any?>
    ): JsonObject

    // 设备模拟
    @GET("devices")
    suspend fun getDevices(): JsonObject

    @POST("devices")
    suspend fun addDevice(@Body body: Map<String, Any?>): JsonObject

    // 身高
    @GET("height/query")
    suspend fun queryHeight(@Query("game_user_id") gameUserId: String): JsonObject

    @GET("height/rank")
    suspend fun getHeightRank(@Query("limit") limit: Int = 100): JsonObject

    // 每日任务
    @GET("tasks")
    suspend fun getTasks(): JsonObject

    @POST("tasks/{taskId}/run")
    suspend fun runTask(@Path("taskId") taskId: String): JsonObject

    // 通用兜底
    @GET
    suspend fun get(@Url url: String): JsonObject

    @POST
    suspend fun post(@Url url: String, @Body body: Map<String, Any?>): JsonObject
}

package sky.angin.cn.client

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "https://sky.angin.cn/api/"

    fun create(context: Context): ApiService {
        val cookieJar = PersistentCookieJar(context.applicationContext)

        val cryptoClient = OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val cryptoManager = CryptoManager(cryptoClient)
        val responseCache = ResponseCache()
        val networkInterceptor = NetworkInterceptor(cryptoManager, responseCache)

        val mainClient = cryptoClient.newBuilder()
            .addInterceptor(networkInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mainClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}