package com.skyauto.app.data.network

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.Base64
import java.util.concurrent.atomic.AtomicReference

/**
 * 缓存站点下发的 AES 密钥，负责按需拉取并支持失效重置。
 * 登录/退出/注册等操作会触发 [reset]，强制重新拉取新密钥。
 */
class ApiKeys(
    private val baseUrl: String,
    private val okHttp: OkHttpClient
) {
    private val mutex = Mutex()
    private val keyRef = AtomicReference<ByteArray?>(null)

    suspend fun get(): ByteArray? {
        keyRef.get()?.let { return it }
        return mutex.withLock {
            keyRef.get()?.let { return it }
            val fetched = fetchKey()
            keyRef.set(fetched)
            fetched
        }
    }

    /** 拉取一次，失败返回 null（不缓存 null）。 */
    private fun fetchKey(): ByteArray? {
        return runCatching {
            val req = Request.Builder()
                .url("$baseUrl/crypto/key")
                .get()
                .build()
            okHttp.newCall(req).execute().use { resp ->
                if (!resp.isSuccessful) return null
                val text = resp.body?.string() ?: return null
                val json = org.json.JSONObject(text)
                if (!json.optBoolean("success", false)) return null
                val keyB64 = json.optString("key") ?: return null
                Base64.getDecoder().decode(keyB64)
            }
        }.getOrNull()
    }

    fun reset() {
        keyRef.set(null)
    }
}