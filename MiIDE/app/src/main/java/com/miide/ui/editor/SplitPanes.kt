package com.miide.ui.editor

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.OpenInFull
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.miide.core.designsystem.theme.MiColors
import com.miide.terminal.TerminalViewModel
import com.miide.ui.chat.ActivityStream
import com.miide.ui.chat.ChatInputBar
import com.miide.ui.chat.ChatStatusBar
import com.miide.ui.chat.ChatViewModel
import com.miide.ui.chat.DiffPreview
import com.miide.ui.chat.MessageBubble
import com.miide.ui.chat.TickerBar
import com.miide.ui.chat.ToolCallsRow
import com.miide.ui.chat.UsageFooter
import com.miide.ui.common.chatViewModelShared
import com.termux.view.TerminalView

/** 分屏窗格类型（M3 多窗格）。 */
enum class SplitPaneMode { CHAT, TERMINAL }

internal val SplitPaneMode.title: String
    get() = when (this) {
        SplitPaneMode.CHAT -> "AI 对话"
        SplitPaneMode.TERMINAL -> "终端"
    }

internal val SplitPaneMode.icon: ImageVector
    get() = when (this) {
        SplitPaneMode.CHAT -> Icons.Default.SmartToy
        SplitPaneMode.TERMINAL -> Icons.Default.Terminal
    }

/**
 * 分屏多窗格布局：编辑器 + 辅助窗格（对话 / 终端）。
 *
 * 竖屏上下分屏、横屏左右分屏；拖拽中间分隔条可调整占比。
 */
@Composable
fun EditorSplitLayout(
    pane: SplitPaneMode,
    onExpand: () -> Unit,
    onClose: () -> Unit,
    editor: @Composable () -> Unit,
    paneContent: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    var fraction by remember(pane) { mutableStateOf(if (isLandscape) 0.6f else 0.55f) }
    var size by remember { mutableStateOf(IntSize.Zero) }

    if (isLandscape) {
        Row(
            modifier
                .fillMaxSize()
                .onSizeChanged { size = it }
        ) {
            Box(Modifier.fillMaxHeight().weight(fraction)) { editor() }
            // 竖直分隔条
            Box(
                Modifier
                    .fillMaxHeight()
                    .width(8.dp)
                    .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f))
                    .pointerInput(size.width) {
                        detectHorizontalDragGestures { change, dragAmount ->
                            change.consume()
                            val total = size.width.coerceAtLeast(1).toFloat()
                            fraction = (fraction + dragAmount / total).coerceIn(0.25f, 0.75f)
                        }
                    }
            )
            Box(Modifier.fillMaxHeight().weight(1f - fraction)) {
                SplitPanePanel(
                    pane = pane,
                    verticalHeader = true,
                    onExpand = onExpand,
                    onClose = onClose
                ) { paneContent() }
            }
        }
    } else {
        Column(
            modifier
                .fillMaxSize()
                .onSizeChanged { size = it }
        ) {
            Box(Modifier.fillMaxWidth().weight(fraction)) { editor() }
            // 水平分隔条
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f))
                    .pointerInput(size.height) {
                        detectVerticalDragGestures { change, dragAmount ->
                            change.consume()
                            val total = size.height.coerceAtLeast(1).toFloat()
                            fraction = (fraction + dragAmount / total).coerceIn(0.25f, 0.75f)
                        }
                    }
            )
            Box(Modifier.fillMaxWidth().weight(1f - fraction)) {
                SplitPanePanel(
                    pane = pane,
                    verticalHeader = false,
                    onExpand = onExpand,
                    onClose = onClose
                ) { paneContent() }
            }
        }
    }
}

/** 窗格外壳：标题 + 全屏/关闭入口 + 内容。竖屏时标题在顶部，横屏时标题在左侧。 */
@Composable
private fun SplitPanePanel(
    pane: SplitPaneMode,
    verticalHeader: Boolean,
    onExpand: () -> Unit,
    onClose: () -> Unit,
    content: @Composable () -> Unit
) {
    val accent = when (pane) {
        SplitPaneMode.CHAT -> MiColors.AccentViolet
        SplitPaneMode.TERMINAL -> MiColors.AccentGreen
    }
    if (verticalHeader) {
        Row(Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(34.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(vertical = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = onExpand, modifier = Modifier.size(30.dp)) {
                    Icon(
                        Icons.Default.OpenInFull,
                        contentDescription = "全屏",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(17.dp)
                    )
                }
                IconButton(onClick = onClose, modifier = Modifier.size(30.dp)) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "关闭分屏",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(17.dp)
                    )
                }
                Spacer(Modifier.weight(1f))
                Text(
                    text = pane.title,
                    style = MaterialTheme.typography.labelSmall,
                    color = accent,
                    modifier = Modifier.rotate(-90f)
                )
                Spacer(Modifier.height(6.dp))
            }
            Box(Modifier.fillMaxHeight().weight(1f)) { content() }
        }
    } else {
        Column(Modifier.fillMaxSize()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(34.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(pane.icon, contentDescription = null, tint = accent, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text(
                    text = pane.title,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = onExpand, modifier = Modifier.size(30.dp)) {
                    Icon(
                        Icons.Default.OpenInFull,
                        contentDescription = "全屏",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )
                }
                IconButton(onClick = onClose, modifier = Modifier.size(30.dp)) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "关闭分屏",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            Box(Modifier.fillMaxWidth().weight(1f)) { content() }
        }
    }
}

/**
 * 分屏窗格内的 AI 对话面板：与全屏对话共享同一 Activity 级 ViewModel，
 * 消息流 / 过程可见（状态·耗时·token、动作流、跑马灯、diff 预览）全部连续。
 */
@Composable
fun ChatSplitPane(viewModel: ChatViewModel = chatViewModelShared()) {
    val uiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(uiState.messages.size, uiState.messages.lastOrNull()?.content?.length) {
        val last = uiState.messages.lastIndex
        if (last >= 0) listState.animateScrollToItem(last)
    }

    Column(Modifier.fillMaxSize()) {
        if (uiState.messages.isEmpty()) {
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "开始与 AI 对话",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
            ) {
                items(uiState.messages, key = { it.id }) { message ->
                    MessageBubble(
                        message = message,
                        isStreaming = uiState.isStreaming && message.id == uiState.messages.lastOrNull()?.id,
                        onInsert = if (uiState.editorAttached) {
                            { viewModel.insertToEditor(message.content) }
                        } else {
                            null
                        }
                    )
                }
            }
        }

        // 过程可见（精简尺寸适配分屏）
        ToolCallsRow(uiState.currentToolCalls)
        if (uiState.isStreaming || uiState.activityLog.isNotEmpty() || uiState.elapsedMs > 0) {
            ChatStatusBar(
                status = uiState.currentStatus,
                isStreaming = uiState.isStreaming,
                elapsedMs = uiState.elapsedMs,
                usage = uiState.usage
            )
            ActivityStream(uiState.activityLog, maxVisible = 2)
            TickerBar(uiState.tickerText, height = 40)
        }
        uiState.pendingDiff?.let { pending ->
            DiffPreview(
                pending = pending,
                onAccept = viewModel::acceptDiff,
                onReject = viewModel::rejectDiff
            )
        }
        if (!uiState.isStreaming) UsageFooter(uiState.usage)
        uiState.error?.let { error ->
            Text(
                text = error,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
            )
        }
        ChatInputBar(
            onSend = { viewModel.send(it) },
            onStop = { viewModel.stop() },
            isStreaming = uiState.isStreaming,
            isPaused = uiState.isPaused,
            onPause = { viewModel.pause() },
            onResume = { viewModel.resume() },
            enabled = uiState.activeProvider != null,
            pendingSuggestions = uiState.pendingSuggestions,
            onRemoveSuggestion = { viewModel.removeSuggestion(it) },
            onClearPending = { viewModel.clearPending() }
        )
    }
}

/**
 * 分屏窗格内的虚拟终端：与全屏终端同实现（Termux TerminalView + 沙箱 shell 子进程）。
 * ViewModel 随编辑器页存活，关闭分屏后会话仍在后台运行，可随时再开。
 */
@Composable
fun TerminalSplitPane(viewModel: TerminalViewModel = hiltViewModel()) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                TerminalView(context, null).apply {
                    setTerminalViewClient(viewModel.client)
                    attachSession(viewModel.session())
                    requestFocus()
                }
            },
            update = { view ->
                if (!view.hasFocus()) view.requestFocus()
            }
        )
    }
}
