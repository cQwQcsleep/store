package com.miide.browser

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/** 单个浏览器标签。 */
data class BrowserTab(
    val id: Long,
    val url: String,
    val title: String = "",
    val canGoBack: Boolean = false,
    val canGoForward: Boolean = false,
    val loading: Boolean = false
)

/** DevTools 数据。 */
data class DevToolsData(
    val console: List<ConsoleEntry> = emptyList(),
    val dom: String? = null,
    val requests: List<NetworkEntry> = emptyList()
)

data class ConsoleEntry(
    val level: String,
    val message: String
)

data class NetworkEntry(
    val url: String,
    val status: Int,
    val mime: String?
)

/**
 * 内置浏览器：多标签 WebView 管理。
 *
 * - 每个标签持有独立 WebView（保存在 [webViews]，随 ViewModel 存活，避免重组重建）。
 * - WebChromeClient 捕获 console / 进度 / 标题。
 * - WebViewClient 记录网络请求（DevTools 网络面板）。
 */
@HiltViewModel
class BrowserViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _tabs = MutableStateFlow<List<BrowserTab>>(emptyList())
    val tabs: StateFlow<List<BrowserTab>> = _tabs.asStateFlow()

    private val _activeIndex = MutableStateFlow(0)
    val activeIndex: StateFlow<Int> = _activeIndex.asStateFlow()

    private val _address = MutableStateFlow("")
    val address: StateFlow<String> = _address.asStateFlow()

    private val _devtools = MutableStateFlow(DevToolsData())
    val devtools: StateFlow<DevToolsData> = _devtools.asStateFlow()

    private val webViews = mutableMapOf<Long, WebView>()
    private var nextId = 1L

    init {
        newTab("about:blank", activate = true)
    }

    /** 获取（必要时创建）指定标签的 WebView。必须在主线程调用。 */
    @SuppressLint("SetJavaScriptEnabled")
    fun webViewFor(tab: BrowserTab): WebView {
        webViews[tab.id]?.let { return it }
        val webView = WebView(context)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.allowFileAccess = true
        webView.settings.allowContentAccess = true
        webView.settings.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                view.loadUrl(request.url.toString())
                return true
            }
            override fun onPageStarted(view: WebView, url: String, favicon: android.graphics.Bitmap?) {
                updateTab(tab.id) { it.copy(url = url, loading = true) }
                if (view === webViews[tab.id]) updateAddress(url)
            }
            override fun onPageFinished(view: WebView, url: String) {
                updateTab(tab.id) { it.copy(loading = false, canGoBack = view.canGoBack(), canGoForward = view.canGoForward()) }
                updateTabTitle(tab.id, view.title ?: url)
            }
            override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? {
                val accept = request.requestHeaders["Accept"]
                addRequest(NetworkEntry(request.url.toString(), -1, accept?.substringAfter("text/")?.substringBefore(",")))
                return super.shouldInterceptRequest(view, request)
            }
        }
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                updateTab(tab.id) { it.copy(loading = newProgress < 100) }
            }
            override fun onReceivedTitle(view: WebView, title: String?) {
                updateTabTitle(tab.id, title ?: tab.url)
            }
            override fun onConsoleMessage(consoleMessage: android.webkit.ConsoleMessage): Boolean {
                addConsole(ConsoleEntry(consoleMessage.messageLevel().name, consoleMessage.message()))
                return true
            }
        }
        webViews[tab.id] = webView
        return webView
    }

    /** 打开新标签；homepage 为空时用默认页。 */
    fun newTab(homepage: String = "", activate: Boolean = true) {
        val id = nextId++
        val initial = homepage.ifBlank { "about:blank" }
        _tabs.update { it + BrowserTab(id, initial) }
        if (activate) {
            _activeIndex.value = _tabs.value.lastIndex
            updateAddress(initial)
        }
        if (homepage.isNotBlank()) {
            viewModelScope.launch { webViewFor(_tabs.value.last())?.loadUrl(homepage) }
        }
    }

    fun closeTab(id: Long) {
        val idx = _tabs.value.indexOfFirst { it.id == id }
        if (idx < 0) return
        webViews.remove(id)?.destroy()
        val newList = _tabs.value.toMutableList().apply { removeAt(idx) }
        if (newList.isEmpty()) {
            _tabs.value = listOf(BrowserTab(nextId++, "about:blank"))
            _activeIndex.value = 0
            return
        }
        _tabs.value = newList
        _activeIndex.value = _activeIndex.value.coerceAtMost(newList.lastIndex)
        updateAddress(_tabs.value[_activeIndex.value].url)
    }

    fun selectTab(index: Int) {
        if (index !in _tabs.value.indices) return
        _activeIndex.value = index
        updateAddress(_tabs.value[index].url)
    }

    fun navigate(url: String) {
        val cleaned = normalizeUrl(url)
        val tab = _tabs.value.getOrNull(_activeIndex.value) ?: return
        updateAddress(cleaned)
        webViewFor(tab)?.loadUrl(cleaned)
    }

    fun goBack() {
        val tab = _tabs.value.getOrNull(_activeIndex.value) ?: return
        val v = webViews[tab.id] ?: return
        if (v.canGoBack()) v.goBack()
    }

    fun goForward() {
        val tab = _tabs.value.getOrNull(_activeIndex.value) ?: return
        val v = webViews[tab.id] ?: return
        if (v.canGoForward()) v.goForward()
    }

    fun reload() {
        val tab = _tabs.value.getOrNull(_activeIndex.value) ?: return
        webViews[tab.id]?.reload()
    }

    fun stop() {
        val tab = _tabs.value.getOrNull(_activeIndex.value) ?: return
        webViews[tab.id]?.stopLoading()
    }

    /** 编辑器内容实时预览：加载本地 HTML 内容。 */
    fun previewHtml(html: String) {
        val tab = _tabs.value.getOrNull(_activeIndex.value) ?: return
        webViewFor(tab)?.loadDataWithBaseURL(
            null,
            html,
            "text/html",
            "utf-8",
            null
        )
    }

    fun updateAddress(url: String) {
        _address.value = url
    }

    /** DevTools：抓取 DOM 快照。 */
    fun captureDom() {
        val tab = _tabs.value.getOrNull(_activeIndex.value) ?: return
        val v = webViews[tab.id] ?: return
        v.evaluateJavascript(
            "(function(){return document.documentElement.outerHTML;})();"
        ) { result -> _devtools.update { it.copy(dom = result) } }
    }

    fun clearDevTools() {
        _devtools.value = DevToolsData()
    }

    private fun addConsole(entry: ConsoleEntry) {
        _devtools.update { it.copy(console = (it.console + entry).takeLast(200)) }
    }

    private fun addRequest(entry: NetworkEntry) {
        _devtools.update { it.copy(requests = (it.requests + entry).takeLast(200)) }
    }

    private fun updateTab(id: Long, transform: (BrowserTab) -> BrowserTab) {
        _tabs.update { list -> list.map { if (it.id == id) transform(it) else it } }
    }

    private fun updateTabTitle(id: Long, title: String) {
        updateTab(id) { it.copy(title = title) }
    }

    override fun onCleared() {
        super.onCleared()
        webViews.values.forEach { it.destroy() }
        webViews.clear()
    }

    companion object {
        fun normalizeUrl(input: String): String {
            val trimmed = input.trim()
            if (trimmed.startsWith("about:")) return trimmed
            val hasScheme = trimmed.startsWith("http://") || trimmed.startsWith("https://") ||
                trimmed.startsWith("file://") || trimmed.startsWith("data:") || trimmed.startsWith("javascript:")
            return if (hasScheme) trimmed else {
                val q = trimmed.contains(' ') || trimmed.contains('.')
                if (q && !trimmed.contains(" ")) "https://$trimmed" else
                    "https://www.google.com/search?q=${java.net.URLEncoder.encode(trimmed, "UTF-8")}"
            }
        }
    }
}
