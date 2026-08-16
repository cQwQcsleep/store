package com.skyauto.app.data.session

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.util.Collections.synchronizedMap

/**
 * 基于 SharedPreferences 的持久化 CookieJar，保持与站点一致的登录态。
 */
class PersistentCookieJar constructor(
    private val context: Context
) : CookieJar {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("skyauto_cookies", Context.MODE_PRIVATE)

    private val cache: MutableMap<String, MutableList<Cookie>> = synchronizedMap(mutableMapOf())

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val key = url.host
        val list = cache.getOrPut(key) { mutableListOf() }
        list.removeAll(cookies)
        list.addAll(cookies)
        persist(url.host, list)
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val key = url.host
        val cached = cache[key]
        if (cached != null) return cached.toList()
        val loaded = read(url.host)
        cache[key] = loaded.toMutableList()
        return loaded
    }

    private fun persist(host: String, cookies: List<Cookie>) {
        val set = cookies.map { it.toString() }.toSet()
        val editor = prefs.edit()
        editor.putStringSet(host, set)
        editor.apply()
        synchronized(cache) { cache[host] = cookies.toMutableList() }
    }

    private fun read(host: String): List<Cookie> {
        val set = prefs.getStringSet(host, null) ?: return emptyList()
        return set.mapNotNull { runCatching { Cookie.parse(HttpUrl.Builder().host(host).build(), it) }.getOrNull() }
    }

    fun clear() {
        prefs.edit().clear().apply()
        synchronized(cache) { cache.clear() }
    }
}