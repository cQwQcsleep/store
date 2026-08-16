package com.miide.core.editor

import android.graphics.Typeface
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.miide.core.editor.theme.ThemeManager
import io.github.rosemoe.sora.event.ContentChangeEvent
import io.github.rosemoe.sora.event.EventReceiver
import io.github.rosemoe.sora.event.SelectionChangeEvent
import io.github.rosemoe.sora.event.Unsubscribe
import io.github.rosemoe.sora.lang.Language
import io.github.rosemoe.sora.widget.CodeEditor
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme

/**
 * 编辑器状态快照，通过 [MiCodeEditor] 回调暴露给 Compose 层。
 */
@Stable
class EditorState {
    /** 当前文本内容（只读快照，每次文本变更时刷新）。 */
    var text: String by mutableStateOf("")
        internal set

    /** 光标所在行号（0-based）。 */
    var cursorLine: Int by mutableStateOf(0)
        internal set

    /** 光标所在列号（0-based）。 */
    var cursorColumn: Int by mutableStateOf(0)
        internal set

    /** 是否可撤销。 */
    var canUndo: Boolean by mutableStateOf(false)
        internal set

    /** 是否可重做。 */
    var canRedo: Boolean by mutableStateOf(false)
        internal set

    /** 总行数。 */
    var lineCount: Int by mutableStateOf(0)
        internal set

    /** 总字符数。 */
    var charCount: Int by mutableStateOf(0)
        internal set
}

/**
 * Compose 可组合的编辑器组件。
 *
 * 基于 [CodeEditor]（Rosemoe Sora-Editor），以 `AndroidView` 方式嵌入 Compose 树，
 * 提供语法高亮、行号、撤销/重做、只读等能力。
 *
 * @param text 文本内容（外部受控；仅在内容实际变化时回写编辑器，避免打断输入）
 * @param fileName 用于推断语法高亮语言的文件名（含扩展名）
 * @param darkTheme 是否使用 HyperOS 暗色编辑器主题
 * @param readOnly 是否只读
 * @param showLineNumber 是否显示行号
 * @param fontSize 字号（sp）
 * @param tabWidth 制表符宽度（空格数）
 * @param wordWrap 是否自动换行
 * @param monoSpace 是否使用系统等宽字体
 * @param extraTypeface 自定义字体（优先于 [monoSpace]）
 * @param onEditorReady 编辑器就绪回调，用于把编辑器命令接口交给外部（如 ViewModel）
 * @param onStateChange 状态变更回调（光标、撤销/重做能力、行数等）
 * @param onTextChange 文本变更回调
 */
@Composable
fun MiCodeEditor(
    text: String,
    fileName: String = "",
    modifier: Modifier = Modifier,
    darkTheme: Boolean = true,
    readOnly: Boolean = false,
    showLineNumber: Boolean = true,
    fontSize: Float = 14f,
    tabWidth: Int = 4,
    wordWrap: Boolean = false,
    monoSpace: Boolean = true,
    extraTypeface: Typeface? = null,
    onEditorReady: (EditorController) -> Unit = {},
    onStateChange: (EditorState) -> Unit = {},
    onTextChange: (String) -> Unit = {},
) {
    val context = LocalContext.current
    val syntaxHighlighter = remember { SyntaxHighlighter(context) }
    val themeManager = remember { ThemeManager(context) }

    // 回调始终引用最新值，避免 AndroidView factory 捕获旧闭包
    val currentOnStateChange by rememberUpdatedState(onStateChange)
    val currentOnTextChange by rememberUpdatedState(onTextChange)

    // 主题（框体 + token 融合，按深浅色生成一次）
    val colorScheme: EditorColorScheme = remember(darkTheme) {
        themeManager.createTheme(dark = darkTheme)
    }

    // 记录已应用的语言，避免每次重组重复设置
    var lastFileName by remember { mutableStateOf<String?>(null) }
    var lastLanguage by remember { mutableStateOf<Language?>(null) }

    val editorState = remember { EditorState() }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { ctx ->
            CodeEditor(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                // 基本配置
                setEditable(!readOnly)
                isLineNumberEnabled = showLineNumber
                setTabWidth(tabWidth)
                setWordwrap(wordWrap)
                setTextSize(fontSize)
                typefaceText = extraTypeface ?: if (monoSpace) Typeface.MONOSPACE else Typeface.DEFAULT
                typefaceLineNumber = typefaceText
                setColorScheme(colorScheme)

                // 把命令接口交给外部
                onEditorReady(CodeEditorController(this))

                // 文本变更事件
                subscribeEvent(
                    ContentChangeEvent::class.java,
                    object : EventReceiver<ContentChangeEvent> {
                        override fun onReceive(event: ContentChangeEvent, unsubscribe: Unsubscribe) {
                            val content = getText()
                            editorState.text = content.toString()
                            editorState.lineCount = content.lineCount
                            editorState.charCount = content.length
                            editorState.canUndo = canUndo()
                            editorState.canRedo = canRedo()
                            currentOnStateChange(editorState)
                            currentOnTextChange(editorState.text)
                        }
                    }
                )

                // 光标 / 选区事件
                subscribeEvent(
                    SelectionChangeEvent::class.java,
                    object : EventReceiver<SelectionChangeEvent> {
                        override fun onReceive(event: SelectionChangeEvent, unsubscribe: Unsubscribe) {
                            val cursor = cursor
                            editorState.cursorLine = cursor.leftLine
                            editorState.cursorColumn = cursor.leftColumn
                            editorState.canUndo = canUndo()
                            editorState.canRedo = canRedo()
                            currentOnStateChange(editorState)
                        }
                    }
                )

                setText(text, null)
            }
        },
        update = { editor ->
            // 基础配置同步
            editor.setEditable(!readOnly)
            editor.isLineNumberEnabled = showLineNumber
            editor.setTabWidth(tabWidth)
            editor.setWordwrap(wordWrap)
            editor.setTextSize(fontSize)
            editor.typefaceText = extraTypeface ?: if (monoSpace) Typeface.MONOSPACE else Typeface.DEFAULT
            editor.typefaceLineNumber = editor.typefaceText
            editor.setColorScheme(colorScheme)

            // 语言同步（仅当文件名变化时）
            if (fileName != lastFileName) {
                lastFileName = fileName
                val lang = syntaxHighlighter.languageFor(fileName)
                if (lang != lastLanguage) {
                    lastLanguage = lang
                    editor.setEditorLanguage(lang)
                }
            }

            // 文本同步（避免回写打断正在进行的输入）
            if (editor.getText().toString() != text) {
                editor.setText(text, false, null)
            }
        }
    )
}
