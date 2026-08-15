package com.skyauto.app.data.network

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import okio.buffer

/**
 * 与站点一致的请求加密 / 响应解密拦截器。
 *
 *  - 请求：对带 body 的请求体做 AES-256-GCM 加密，置头部 X-Encrypted: 1
 *  - 响应：当响应头 X-Encrypted == 1 时解密 body
 *  - 登录 / 退出 / 注册：重置密钥缓存（站点会轮换密钥）
 */
class CryptoInterceptor(
    private val baseUrl: String,
    private val keys: ApiKeys
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath

        val shouldReset = RESET_PATHS.any { path.contains(it) }
        if (shouldReset) keys.reset()

        val key = runBlocking { keys.get() }
        val outRequest: Request = if (key != null && request.body != null
            && !path.contains("/crypto/key")
            && request.body!!.contentLength() != 0L
        ) {
            val plainBytes = request.body!!.readBytes()
            val encrypted = Crypto.encryptToBase64(key, plainBytes)
            request.newBuilder()
                .method(request.method, encrypted.toRequestBody(JSON_MEDIA))
                .header("X-Encrypted", "1")
                .build()
        } else {
            request
        }

        val response = chain.proceed(outRequest)

        if (shouldReset) keys.reset()

        if (key != null && response.header("X-Encrypted") == "1") {
            val bodyText = response.body?.string() ?: return response
            val decrypted = runCatching { Crypto.decryptFromBase64(key, bodyText) }
                .getOrNull()
            if (decrypted != null) {
                val newBody = decrypted.toResponseBody(JSON_MEDIA)
                return response.newBuilder().body(newBody).build()
            }
        }
        return response
    }

    private fun okhttp3.RequestBody.readBytes(): ByteArray {
        val buffer = Buffer()
        writeTo(buffer)
        return buffer.readByteArray()
    }

    private companion object {
        val JSON_MEDIA: okhttp3.MediaType = "application/json; charset=utf-8".toMediaType()
        val RESET_PATHS = listOf("/auth/login", "/auth/logout", "/auth/register")
    }
}