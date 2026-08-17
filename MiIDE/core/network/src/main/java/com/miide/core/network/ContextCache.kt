package com.miide.core.network

import java.security.MessageDigest
import java.util.concurrent.ConcurrentLinkedDeque

/**
 * 上下文缓存结果。
 */
data class CachedResult(
    val result: ProviderResult,
    val events: List<ProviderEvent>
)

/**
 * 上下文缓存 Key，由请求参数 SHA-256 摘要生成。
 */
data class CacheKey(val hash: String)

/**
 * 上下文缓存条目。
 */
private data class CacheEntry(
    val key: CacheKey,
    val value: CachedResult,
    val createdAt: Long = System.currentTimeMillis()
)

/**
 * 上下文 / 提示词缓存。
 *
 * 对完全相同的请求（messages + systemPrompt + modelId + temperature + maxTokens + tools）
 * 缓存响应结果，在 TTL 内命中时直接回放事件，跳过 API 调用。
 *
 * 使用 LRU 策略自动淘汰；线程安全。
 */
class ContextCache(
    /** 最大缓存条目数。 */
    private val maxSize: Int = 32,
    /** 条目存活时间（毫秒）。 */
    private val ttlMs: Long = 30_000
) {
    private val cache = ConcurrentLinkedDeque<CacheEntry>()

    /**
     * 生成缓存 Key。
     */
    fun keyFor(request: ProviderRequest): CacheKey {
        val input = buildString {
            request.systemPrompt?.let { append(it).append('\n') }
            request.messages.forEach { msg ->
                append(msg.role).append('|').append(msg.content).append('\n')
                msg.toolCallId?.let { append("tcId:").append(it).append('\n') }
                msg.toolCalls?.forEach { tc ->
                    append("tc:").append(tc.id).append('|').append(tc.name).append('|').append(tc.arguments).append('\n')
                }
            }
            append("model=").append(request.modelId).append('\n')
            append("temp=").append(request.temperature).append('\n')
            append("max=").append(request.maxTokens).append('\n')
            request.tools?.forEach { t ->
                append("tool:").append(t.name).append('|').append(t.description).append('|').append(t.parametersJson).append('\n')
            }
        }
        val digest = MessageDigest.getInstance("SHA-256").digest(input.toByteArray(Charsets.UTF_8))
        val hex = digest.joinToString("") { "%02x".format(it) }
        return CacheKey(hex)
    }

    /**
     * 尝试命中缓存。
     */
    fun get(key: CacheKey): CachedResult? {
        evictExpired()
        val entry = cache.firstOrNull { it.key == key }
        if (entry == null) return null
        // 移到队尾（LRU）
        cache.remove(entry)
        cache.addLast(entry)
        return entry.value
    }

    /**
     * 写入缓存。
     */
    fun put(key: CacheKey, value: CachedResult) {
        evictExpired()
        // 移除旧条目
        cache.removeAll { it.key == key }
        // 淘汰超出大小
        while (cache.size >= maxSize) {
            cache.pollFirst()
        }
        cache.addLast(CacheEntry(key, value))
    }

    /** 清空缓存。 */
    fun clear() = cache.clear()

    /** 当前缓存条目数。 */
    val size: Int get() {
        evictExpired()
        return cache.size
    }

    private fun evictExpired() {
        val now = System.currentTimeMillis()
        while (true) {
            val first = cache.peekFirst() ?: break
            if (now - first.createdAt > ttlMs) cache.pollFirst()
            else break
        }
    }
}