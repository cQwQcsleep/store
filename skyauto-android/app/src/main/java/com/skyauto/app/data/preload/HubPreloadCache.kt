package com.skyauto.app.data.preload

import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 共享的内存预加载缓存（线程安全）。
 * 由 [HubPreloader] 在后台线程写入，各功能页 ViewModel 读取，实现“十字 UI ±5 页异步预加载”。
 */
@Singleton
class HubPreloadCache @Inject constructor() {
    private val cache = ConcurrentHashMap<String, Any>()
    private val loaded = ConcurrentHashMap.newKeySet<String>()

    fun <T> get(key: String): T? = cache[key] as? T

    fun put(key: String, value: Any?) {
        if (value != null) cache[key] = value
    }

    /** 标记某个 key 已预加载过一次，避免重复请求。 */
    fun markLoaded(key: String): Boolean = loaded.add(key)

    fun clear() {
        cache.clear()
        loaded.clear()
    }
}