package com.miide.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInFull
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miide.core.designsystem.components.MiCardGroup
import com.miide.core.designsystem.theme.MiColors
import com.miide.ui.common.chatViewModelShared

/**
 * 底部 AI 面板：固定在首页底部。
 * 与全屏对话页共享同一个 Activity 级 ViewModel，流式状态连续。
 */
@Composable
fun ChatBottomPanel(
    onExpand: () -> Unit,
    viewModel: ChatViewModel = chatViewModelShared()
) {
    val uiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(uiState.messages.size, uiState.messages.lastOrNull()?.content?.length) {
        val last = uiState.messages.lastIndex
        if (last >= 0) listState.animateScrollToItem(last)
    }

    MiCardGroup {
        // 头部
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.SmartToy,
                contentDescription = null,
                tint = MiColors.AccentViolet,
                modifier = Modifier
                    .padding(0.dp)
                    .height(20.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "AI 助手",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.width(8.dp))
            ChatProviderBadge(uiState.activeProvider, uiState.activeModelId)
            Spacer(Modifier.weight(1f))
            IconButton(onClick = onExpand) {
                Icon(Icons.Default.OpenInFull, contentDescription = "全屏")
            }
        }

        if (uiState.messages.isEmpty()) {
            Text(
                text = "让 AI 帮你写代码、改代码、解释报错…",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp)
            ) {
                items(uiState.messages, key = { it.id }) { message ->
                    MessageBubble(
                        message = message,
                        isStreaming = uiState.isStreaming && message.id == uiState.messages.lastOrNull()?.id
                    )
                }
            }
        }

        ToolCallsRow(uiState.currentToolCalls)
        uiState.usage?.let { UsageFooter(it) }
        uiState.error?.let { error ->
            Text(
                text = error,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }

        ChatInputBar(
            onSend = { viewModel.send(it) },
            onStop = { viewModel.stop() },
            isStreaming = uiState.isStreaming,
            enabled = viewModel.canSend(),
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}
