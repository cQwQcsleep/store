package com.miide.core.plugin

/**
 * 暴露给脚本插件的受限 API（沙箱边界）。
 *
 * 仅注册标注了 [android.webkit.JavascriptInterface] 的公开方法，
 * 脚本通过全局 `MiIDE` 对象访问，例如 `MiIDE.log("hi")`。
 * 基础版仅提供日志、键值偏好与事件上报；不暴露文件系统 / 网络 / 系统能力。
 */
class MiIDEPluginApi(
    initialPrefs: Map<String, String> = emptyMap()
) {
    private val prefs = HashMap(initialPrefs)
    private val logs = ArrayList<String>()
    private val emitted = ArrayList<String>()

    /** 输出日志（最终并入 stdout）。 */
    @android.webkit.JavascriptInterface
    fun log(message: String) {
        logs += message
    }

    /** 读取插件偏好。 */
    @android.webkit.JavascriptInterface
    fun getPref(key: String): String = prefs[key] ?: ""

    /** 写入插件偏好（仅内存态，基础版不落盘）。 */
    @android.webkit.JavascriptInterface
    fun setPref(key: String, value: String) {
        prefs[key] = value
    }

    /** 上报事件动作，供「过程可见」展示。 */
    @android.webkit.JavascriptInterface
    fun emit(event: String) {
        emitted += event
    }

    /** 宿主版本号。 */
    @android.webkit.JavascriptInterface
    fun getVersion(): String = "0.1.0"

    /** 收集到的日志行。 */
    val logLines: List<String> get() = logs

    /** 收集到的事件动作。 */
    val emitList: List<String> get() = emitted
}
