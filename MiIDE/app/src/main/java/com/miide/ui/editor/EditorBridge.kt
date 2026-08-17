package com.miide.ui.editor

import android.os.Handler
import android.os.Looper
import com.miide.core.editor.EditorController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * 编辑器桥接：连接 AI 对话（[com.miide.ui.chat.ChatViewModel]）与编辑器视图。
 *
 * 编辑器页在就绪时 [register] 控制器、离开时注销；
 * 对话页通过 [insertAtCursor] 把 AI 生成的代码插入当前光标处（任意线程可调用，内部切回主线程）。
 */
object EditorBridge {

    private val _attached = MutableStateFlow(false)

    /** 当前是否已连接编辑器（用于决定是否显示「插入」按钮）。 */
    val attached: StateFlow<Boolean> = _attached.asStateFlow()

    @Volatile
    private var controller: EditorController? = null

    private val mainHandler = Handler(Looper.getMainLooper())

    /** 编辑器视图创建时注册；销毁时传 null 注销。 */
    fun register(controller: EditorController?) {
        this.controller = controller
        _attached.value = controller != null
    }

    /** 在当前光标处插入文本。 */
    fun insertAtCursor(text: String) {
        if (text.isEmpty()) return
        val ctrl = controller ?: return
        mainHandler.post { ctrl.insertText(text) }
    }

    /** 读取当前编辑器文本（回调在 UI 线程）。 */
    fun readCurrentText(callback: (String?) -> Unit) {
        val ctrl = controller ?: run { callback(null); return }
        mainHandler.post { callback(ctrl.getText()) }
    }

    /** 读取当前光标偏移（回调在 UI 线程）。 */
    fun readCursorOffset(callback: (Int?) -> Unit) {
        val ctrl = controller ?: run { callback(null); return }
        mainHandler.post { callback(ctrl.cursorOffset()) }
    }

    /** 整体设置编辑器文本（用于拒绝 diff 时回滚）。 */
    fun setText(text: String) {
        val ctrl = controller ?: return
        mainHandler.post { ctrl.setText(text) }
    }
}
