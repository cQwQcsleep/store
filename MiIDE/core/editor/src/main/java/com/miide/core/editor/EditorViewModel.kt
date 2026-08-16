package com.miide.core.editor

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * 编辑器命令接口：由 [MiCodeEditor] 内部实现，暴露给外部（ViewModel 等）执行命令。
 */
interface EditorController {
    fun undo()
    fun redo()

    /** 按当前语言格式化整个文档；无格式化能力时返回 false。 */
    fun format(): Boolean

    /** 设置文本（仅在内容变化时生效，避免打断输入）。 */
    fun setText(text: String)

    /** 读取当前文本。 */
    fun getText(): String

    /** 请求焦点；返回是否成功获得焦点。 */
    fun requestFocus(): Boolean

    /** 在当前光标位置插入文本（用于 AI 生成代码一键插入）。 */
    fun insertText(text: String)
}

/** [CodeEditor] 视图的默认实现。 */
internal class CodeEditorController(
    private val editor: io.github.rosemoe.sora.widget.CodeEditor
) : EditorController {
    override fun undo() = editor.undo()
    override fun redo() = editor.redo()
    override fun format(): Boolean = editor.formatCodeAsync()
    override fun setText(text: String) {
        if (editor.getText().toString() != text) {
            editor.setText(text, false, null)
        }
    }
    override fun getText(): String = editor.getText().toString()
    override fun requestFocus(): Boolean = editor.requestFocus()
    override fun insertText(text: String) {
        if (text.isEmpty()) return
        // commitText 在当前光标处插入，且内部按一次输入处理（可整体撤销）
        editor.commitText(text)
    }
}

/**
 * 编辑器状态快照（光标 / 撤销重做 / 统计）。
 */
data class EditorSnapshot(
    val cursorLine: Int = 0,
    val cursorColumn: Int = 0,
    val canUndo: Boolean = false,
    val canRedo: Boolean = false,
    val lineCount: Int = 0,
    val charCount: Int = 0,
)

/**
 * 编辑器状态管理：作为文本 / 文件名 / 状态的单一数据源，
 * 通过 [attach] 把命令接口绑定到真实编辑器视图，解耦 ViewModel 与 View。
 */
class EditorViewModel : ViewModel() {

    private val _text = MutableStateFlow("")
    val text: StateFlow<String> = _text.asStateFlow()

    private val _fileName = MutableStateFlow("")
    val fileName: StateFlow<String> = _fileName.asStateFlow()

    private val _snapshot = MutableStateFlow(EditorSnapshot())
    val snapshot: StateFlow<EditorSnapshot> = _snapshot.asStateFlow()

    private var controller: EditorController? = null

    /** 绑定编辑器命令接口（编辑器视图创建后调用）。 */
    fun attach(controller: EditorController?) {
        this.controller = controller
    }

    /** 打开文件：更新元数据并写入文本。 */
    fun openFile(path: String, content: String) {
        _fileName.value = path.substringAfterLast('/').ifBlank { path }
        _text.value = content
        controller?.setText(content)
    }

    /** 外部更新文本（如 AI 修改建议应用、粘贴代码片段等）。 */
    fun setText(text: String) {
        _text.value = text
        controller?.setText(text)
    }

    /** 由编辑器视图回调：合并文本与状态。 */
    fun onTextChanged(text: String) {
        _text.value = text
    }

    /** 由编辑器视图回调：合并状态快照。 */
    fun onSnapshotChanged(snapshot: EditorSnapshot) {
        _snapshot.value = snapshot
    }

    fun undo() = controller?.undo()
    fun redo() = controller?.redo()

    /** 格式化文档。 */
    fun format(): Boolean = controller?.format() ?: false
}
