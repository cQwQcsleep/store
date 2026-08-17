package com.miide.ui.editor

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.FormatAlignLeft
import androidx.compose.material.icons.automirrored.filled.Redo
import androidx.compose.material.icons.automirrored.filled.Undo
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miide.browser.HtmlPreview
import com.miide.core.editor.EditorSnapshot
import com.miide.core.editor.EditorViewModel
import com.miide.core.editor.MiCodeEditor
import com.miide.ui.remote.RemoteOpenBridge
import com.miide.ui.remote.RemoteViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    fileUri: String,
    initialName: String,
    onBack: () -> Unit,
    onOpenChat: () -> Unit = {},
    onOpenTerminal: () -> Unit = {},
    onOpenBrowser: () -> Unit = {},
    viewModel: EditorViewModel = viewModel()
) {
    val text by viewModel.text.collectAsState()
    val fileName by viewModel.fileName.collectAsState()
    val snapshot by viewModel.snapshot.collectAsState()

    val runViewModel: RunViewModel = hiltViewModel()
    val runState by runViewModel.state.collectAsState()

    val remoteViewModel: RemoteViewModel = hiltViewModel()

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val isDark = isSystemInDarkTheme()

    var uri by remember { mutableStateOf(fileUri.ifBlank { null }) }
    // 标记是否为远程文件（由 RemoteOpenBridge 桥接）
    var isRemoteFile by remember { mutableStateOf(false) }
    // HTML 实时预览开关（仅 .html/.htm 文件显示）
    val isHtml = fileName.endsWith(".html", ignoreCase = true) || fileName.endsWith(".htm", ignoreCase = true)
    var showPreview by remember { mutableStateOf(false) }
    // M3 分屏多窗格：编辑器 + 对话 / 终端
    var splitPane by remember { mutableStateOf<SplitPaneMode?>(null) }

    // 首次载入：检查远程待打开文件，否则按 URI 读取
    LaunchedEffect(fileUri) {
        val pending = RemoteOpenBridge.pending
        if (pending != null) {
            viewModel.openFile(pending.name, pending.content)
            RemoteOpenBridge.active = pending
            RemoteOpenBridge.pending = null
            isRemoteFile = true
        } else if (fileUri.isNotBlank()) {
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
        if (isRemoteFile) {
            scope.launch {
                val result = remoteViewModel.saveActiveRemote(viewModel.text.value)
                if (result == null) {
                    snackbarHostState.showSnackbar("远程文件未绑定，已走本地保存")
                    return@launch
                }
                snackbarHostState.showSnackbar(if (result.first) result.second else result.second)
            }
            return
        }
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
                    IconButton(onClick = {
                        splitPane = if (splitPane == SplitPaneMode.TERMINAL) null else SplitPaneMode.TERMINAL
                    }) {
                        Icon(
                            Icons.Default.Terminal,
                            contentDescription = if (splitPane == SplitPaneMode.TERMINAL) "关闭终端分屏" else "终端分屏",
                            tint = if (splitPane == SplitPaneMode.TERMINAL) MaterialTheme.colorScheme.primary else LocalContentColor.current
                        )
                    }
                    IconButton(onClick = onOpenBrowser) {
                        Icon(Icons.Default.Public, contentDescription = "浏览器")
                    }
                    if (isHtml) {
                        IconButton(onClick = { showPreview = !showPreview }) {
                            Icon(
                                if (showPreview) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = if (showPreview) "关闭预览" else "实时预览"
                            )
                        }
                    }
                    IconButton(onClick = {
                        splitPane = if (splitPane == SplitPaneMode.CHAT) null else SplitPaneMode.CHAT
                    }) {
                        Icon(
                            Icons.Default.SmartToy,
                            contentDescription = if (splitPane == SplitPaneMode.CHAT) "关闭 AI 分屏" else "AI 分屏",
                            tint = if (splitPane == SplitPaneMode.CHAT) MaterialTheme.colorScheme.primary else LocalContentColor.current
                        )
                    }
                    IconButton(onClick = { viewModel.triggerCompletion() }) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = "手动补全")
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
                    IconButton(
                        onClick = { runViewModel.run(viewModel.text.value, fileName) },
                        enabled = !runState.running
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = "运行代码")
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // 编辑器主窗格（全屏或分屏时复用同一内容）
            val editorPane: @Composable () -> Unit = {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(if (showPreview && isHtml) 0.5f else 1f)
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
                                        charCount = state.charCount,
                                        ghostActive = state.ghostActive
                                    )
                                )
                            },
                            onTextChange = { newText -> viewModel.onTextChanged(newText) }
                        )
                    }
                    if (showPreview && isHtml) {
                        // HTML 实时预览：内容变化自动刷新
                        HtmlPreview(
                            html = text,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.5f)
                        )
                    }
                }
            }

            val pane = splitPane
            if (pane == null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { editorPane() }
            } else {
                // M3 分屏多窗格：编辑 + 对话 / 编辑 + 终端
                EditorSplitLayout(
                    pane = pane,
                    onExpand = {
                        when (pane) {
                            SplitPaneMode.CHAT -> onOpenChat()
                            SplitPaneMode.TERMINAL -> onOpenTerminal()
                        }
                    },
                    onClose = { splitPane = null },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    editor = editorPane,
                    paneContent = {
                        when (pane) {
                            SplitPaneMode.CHAT -> ChatSplitPane()
                            SplitPaneMode.TERMINAL -> TerminalSplitPane()
                        }
                    }
                )
            }
            RunOutputPanel(
                state = runState,
                onDismiss = { runViewModel.dismiss() }
            )
        }
    }

    // 离开编辑器时注销桥接，避免 AI 对话页误插入到已销毁的视图
    DisposableEffect(Unit) {
        onDispose {
            EditorBridge.register(null)
            if (isRemoteFile) RemoteOpenBridge.clear()
        }
    }
}

/** 运行结果输出面板：显示运行状态、stdout、stderr。 */
@Composable
private fun RunOutputPanel(
    state: RunUiState,
    onDismiss: () -> Unit
) {
    if (!state.running && state.result == null && state.error == null) return

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "运行结果",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.width(8.dp))
                if (state.running) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        strokeWidth = 2.dp
                    )
                }
                Spacer(Modifier.weight(1f))
                IconButton(onClick = onDismiss, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Default.Close, contentDescription = "关闭", modifier = Modifier.size(18.dp))
                }
            }
            Spacer(Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 280.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                when {
                    state.running -> {
                        Text(
                            "正在执行${state.language?.let { "（$it）" }.orEmpty()}…",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    state.error != null -> {
                        Text(
                            state.error!!,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    else -> {
                        val result = state.result!!
                        Text(
                            "退出码: ${result.exitCode}  |  耗时: ${result.durationMs}ms",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.height(4.dp))
                        if (result.stdout.isNotBlank()) {
                            Text(
                                "--- stdout ---",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                result.stdout.trimEnd(),
                                style = MaterialTheme.typography.bodySmall,
                                fontFamily = FontFamily.Monospace
                            )
                            Spacer(Modifier.height(4.dp))
                        }
                        if (result.stderr.isNotBlank()) {
                            Text(
                                "--- stderr ---",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.error
                            )
                            Text(
                                result.stderr.trimEnd(),
                                style = MaterialTheme.typography.bodySmall,
                                fontFamily = FontFamily.Monospace,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }
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
        if (snapshot.ghostActive) {
            Text(
                text = "幽灵补全 · Tab 接受 / Esc 取消",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.width(12.dp))
        }
        Text(
            text = fileName.substringAfterLast('.', "").ifBlank { "txt" }.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
