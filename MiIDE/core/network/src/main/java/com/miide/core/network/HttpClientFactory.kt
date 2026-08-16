package com.miide.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * 构建 Ktor 客户端：超时、日志、JSON 序列化。
 */
object HttpClientFactory {
    fun create(timeoutMs: Long = 60_000): HttpClient = HttpClient(OkHttp) {
        expectSuccess = false
        install(HttpTimeout) {
            requestTimeoutMillis = timeoutMs
            connectTimeoutMillis = 15_000
            socketTimeoutMillis = timeoutMs
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = true
                }
            )
        }
        install(Logging) {
            level = LogLevel.HEADERS
        }
    }

    val JsonConfig: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }
}
