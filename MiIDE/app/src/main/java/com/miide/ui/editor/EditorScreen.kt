package com.miide.ui.editor

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.FormatAlignLeft
import androidx.compose.material.icons.automirrored.filled.Redo
import androidx.compose.material.icons.automirrored.filled.Undo
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miide.core.editor.EditorSnapshot
import com.miide.core.editor.EditorViewModel
import com.miide.core.editor.MiCodeEditor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    fileUri: String,
    initialName: String,
    onBack: () -> Unit,
    onOpenChat: () -> Unit = {},
    viewModel: EditorViewModel = viewModel()
) {
    val text by viewModel.text.collectAsState()
    val fileName by viewModel.fileName.collectAsState()
    val snapshot by viewModel.snapshot.collectAsState()

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val isDark = isSystemInDarkTheme()

    var uri by remember { mutableStateOf(fileUri.ifBlank { null }) }

    // 首次载入：按传入 URI 读取文件，否则新建空白文件
    LaunchedEffect(fileUri) {
        if (fileUri.isNotBlank()) {
            val content = EditorFileIo.read(context, Uri.parse(fileUri)).orEmpty()
            viewModel.openFile(initialName, content)
        } else {
            viewModel.openFile(initialName.ifBlank { "untitled.txt" }, "")
        }
    }

    val createDocument = rememberLauncherForActivityResult(
        ActivityResultContracts.CreateDocument("text/plain")
    ) { newUri ->
        if (newUri != null) {
            scope.launch {
                val ok = EditorFileIo.write(context, newUri, viewModel.text.value)
                if (ok) {
                    uri = newUri.toString()
                    runCatching {
                        context.contentResolver.takePersistableUriPermission(
                            newUri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                        )
                    }
                    snackbarHostState.showSnackbar("已保存到 ${fileName}")
                } else {
                    snackbarHostState.showSnackbar("保存失败")
                }
            }
        }
    }

    fun save() {
        val target = uri
        if (target != null) {
            scope.launch {
                val ok = EditorFileIo.write(context, Uri.parse(target), viewModel.text.value)
                snackbarHostState.showSnackbar(
                    if (ok) "已保存到 ${fileName}" else "保存失败"
                )
            }
        } else {
            createDocument.launch(fileName.ifBlank { "untitled.txt" })
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = fileName.ifBlank { "未命名" },
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
                    IconButton(onClick = onOpenChat) {
                        Icon(Icons.Default.SmartToy, contentDescription = "AI 助手")
                    }
                    IconButton(onClick = { viewModel.undo() }, enabled = snapshot.canUndo) {
                        Icon(Icons.AutoMirrored.Filled.Undo, contentDescription = "撤销")
                    }
                    IconButton(onClick = { viewModel.redo() }, enabled = snapshot.canRedo) {
                        Icon(Icons.AutoMirrored.Filled.Redo, contentDescription = "重做")
                    }
                    IconButton(onClick = { viewModel.format() }) {
                        Icon(Icons.AutoMirrored.Filled.FormatAlignLeft, contentDescription = "格式化")
                    }
                    IconButton(onClick = { save() }) {
                        Icon(Icons.Default.Save, contentDescription = "保存")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
            EditorStatusBar(
                fileName = fileName,
                snapshot = snapshot
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MiCodeEditor(
                text = text,
                fileName = fileName,
                darkTheme = isDark,
                readOnly = false,
                showLineNumber = true,
                fontSize = 14f,
                tabWidth = 4,
                wordWrap = false,
                onEditorReady = { controller ->
                    viewModel.attach(controller)
                    EditorBridge.register(controller)
                },
                onStateChange = { state ->
                    viewModel.onSnapshotChanged(
                        EditorSnapshot(
                            cursorLine = state.cursorLine,
                            cursorColumn = state.cursorColumn,
                            canUndo = state.canUndo,
                            canRedo = state.canRedo,
                            lineCount = state.lineCount,
                            charCount = state.charCount
                        )
                    )
                },
                onTextChange = { newText -> viewModel.onTextChanged(newText) }
            )
        }
    }

    // 离开编辑器时注销桥接，避免 AI 对话页误插入到已销毁的视图
    DisposableEffect(Unit) {
        onDispose { EditorBridge.register(null) }
    }
}

/** 底部状态栏：光标位置与文档统计。 */
@Composable
private fun EditorStatusBar(
    fileName: String,
    snapshot: EditorSnapshot
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Ln ${snapshot.cursorLine + 1}, Col ${snapshot.cursorColumn + 1}",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = "${snapshot.lineCount} 行 · ${snapshot.charCount} 字符",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = fileName.substringAfterLast('.', "").ifBlank { "txt" }.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
