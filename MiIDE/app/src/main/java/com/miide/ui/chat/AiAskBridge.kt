package com.miide.ui.chat

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * 跨页面桥：内置浏览器「交给 AI」→ AI 对话页自动发送。
 *
 * 浏览器把当前页 URL 交给 AI 抓取总结时写入待发消息，
 * AI 对话页（全屏）消费后自动发送，触发 [com.miide.browser.WebFetchTool]。
 */
object AiAskBridge {
    private val _pendingAsk = MutableStateFlow<String?>(null)
    val pendingAsk: StateFlow<String?> = _pendingAsk.asStateFlow()

    /** 请求 AI 抓取并总结指定 URL。 */
    fun askFetch(url: String) {
        _pendingAsk.value = "请抓取并总结这个页面：$url"
    }

    /** 消费待发消息（AI 对话页调用）；无消息时返回 null。 */
    fun consume(): String? {
        val v = _pendingAsk.value
        _pendingAsk.value = null
        return v
    }
}
