package com.miide.ui.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.miide.ui.common.findLocalActivity
import com.miide.ui.common.chatViewModelShared

/** 全屏 AI 对话页。 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    onBack: () -> Unit,
    viewModel: ChatViewModel = chatViewModelShared()
) {
    val uiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    // 消费「交给 AI」待发消息（如浏览器抓取当前页），打开后自动发送
    LaunchedEffect(Unit) {
        AiAskBridge.consume()?.let { viewModel.send(it) }
    }

    // 新消息 / 流式增量时自动滚到底部
    LaunchedEffect(uiState.messages.size, uiState.messages.lastOrNull()?.content?.length) {
        val last = uiState.messages.lastIndex
        if (last >= 0) listState.animateScrollToItem(last)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("AI 助手", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.width(8.dp))
                        ChatProviderBadge(
                            provider = uiState.activeProvider,
                            modelId = uiState.activeModelId
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (uiState.messages.isEmpty()) {
                ChatEmptyHint(Modifier.weight(1f))
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 8.dp)
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

            // 过程可见（AI 工作台）：工具调用 + 状态/耗时/token + 动作流 + 跑马灯
            ToolCallsRow(uiState.currentToolCalls)
            if (uiState.isStreaming || uiState.activityLog.isNotEmpty() || uiState.elapsedMs > 0) {
                ChatStatusBar(
                    status = uiState.currentStatus,
                    isStreaming = uiState.isStreaming,
                    elapsedMs = uiState.elapsedMs,
                    usage = uiState.usage
                )
                ActivityStream(uiState.activityLog)
                TickerBar(uiState.tickerText)
            }
            // 改动 diff 预览：接受 / 拒绝
            uiState.pendingDiff?.let { pending ->
                DiffPreview(
                    pending = pending,
                    onAccept = viewModel::acceptDiff,
                    onReject = viewModel::rejectDiff
                )
            }
            // 结束后展示本轮用量明细
            if (!uiState.isStreaming) UsageFooter(uiState.usage)
            uiState.error?.let { error ->
                Text(
                    text = error,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun ChatEmptyHint(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "开始与 AI 对话",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.padding(4.dp))
            Text(
                text = "可以提问、让它生成或修改代码、解释报错…",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
        }
    }
}
