package com.miide.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.NorthWest
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miide.core.designsystem.theme.MiColors
import com.miide.core.model.ChatMessage
import com.miide.core.model.MessageStatus
import com.miide.core.model.ProviderConfig

/** 消息气泡。 */
@Composable
fun MessageBubble(
    message: ChatMessage,
    isStreaming: Boolean,
    onInsert: (() -> Unit)? = null
) {
    if (message.isUser) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Surface(
                shape = RoundedCornerShape(18.dp, 18.dp, 4.dp, 18.dp),
                color = MiColors.AccentBlue
            ) {
                Text(
                    text = message.content,
                    color = androidx.compose.ui.graphics.Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                )
            }
        }
    } else {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        RoundedCornerShape(4.dp, 18.dp, 18.dp, 18.dp)
                    )
                    .padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                val reasoning = message.reasoning
                if (!reasoning.isNullOrBlank()) {
                    ReasoningSection(reasoning = reasoning)
                }
                if (message.content.isNotBlank()) {
                    Text(
                        text = message.content,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                if (isStreaming && message.content.isBlank() && message.reasoning.isNullOrBlank()) {
                    // 正在思考/等待首个 token
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(14.dp),
                            strokeWidth = 2.dp
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "正在思考…",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                    }
                } else if (isStreaming) {
                    Text(
                        text = "▍",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MiColors.AccentCyan
                    )
                }
                if (message.status == MessageStatus.ERROR) {
                    Text(
                        text = message.error ?: "出错了",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                if (message.status == MessageStatus.INTERRUPTED) {
                    Text(
                        text = "已停止生成",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                }

                // 完成后可一键插入编辑器
                if (onInsert != null &&
                    !isStreaming &&
                    message.status == MessageStatus.COMPLETED &&
                    message.content.isNotBlank()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            onClick = onInsert,
                            shape = RoundedCornerShape(8.dp),
                            color = MiColors.AccentCyan.copy(alpha = 0.14f),
                            contentColor = MiColors.AccentCyan
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.NorthWest,
                                    contentDescription = null,
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                Text(
                                    text = "插入编辑器",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/** 思考过程（可折叠）。 */
@Composable
private fun ReasoningSection(reasoning: String) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable { expanded = !expanded }
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Psychology,
                contentDescription = null,
                tint = MiColors.AccentViolet,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = if (expanded) "思考过程" else "思考过程（点击展开）",
                style = MaterialTheme.typography.labelMedium,
                color = MiColors.AccentViolet,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.width(4.dp))
            Icon(
                imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null,
                tint = MiColors.AccentViolet.copy(alpha = 0.7f),
                modifier = Modifier.size(16.dp)
            )
        }
        if (expanded) {
            Text(
                text = reasoning,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

/** 工具调用展示。 */
@Composable
fun ToolCallsRow(toolCalls: List<ToolCallInfo>) {
    if (toolCalls.isEmpty()) return
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MiColors.AccentCyan.copy(alpha = 0.14f)
        ) {
            Text(
                text = "正在调用工具",
                style = MaterialTheme.typography.labelSmall,
                color = MiColors.AccentCyan,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
            )
        }
        toolCalls.takeLast(3).forEach { call ->
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Text(
                    text = call.name ?: "工具",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                )
            }
        }
    }
}

/** 用量页脚。 */
@Composable
fun UsageFooter(usage: UsageInfo?) {
    if (usage == null) return
    Text(
        text = "本次：${usage.promptTokens} in · ${usage.completionTokens} out · 缓存 ${usage.cachedTokens} · 共 ${usage.totalTokens} tokens",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    )
}

/** 顶部供应商/模型标识。 */
@Composable
fun ChatProviderBadge(provider: ProviderConfig?, modelId: String?) {
    if (provider == null) {
        Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.surfaceVariant) {
            Text(
                text = "未配置供应商",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
            )
        }
        return
    }
    Surface(shape = RoundedCornerShape(16.dp), color = MiColors.AccentViolet.copy(alpha = 0.12f)) {
        Text(
            text = "${provider.name} · ${modelId ?: "未选模型"}",
            style = MaterialTheme.typography.labelSmall,
            color = MiColors.AccentViolet,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

/** 输入栏（发送 / 停止 / 建议队列）。 */
@Composable
fun ChatInputBar(
    onSend: (String) -> Unit,
    onStop: () -> Unit,
    isStreaming: Boolean,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    pendingCount: Int = 0
) {
    var input by remember { mutableStateOf("") }
    Column(modifier) {
        if (pendingCount > 0) {
            Row(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Schedule,
                    contentDescription = null,
                    tint = MiColors.AccentCyan.copy(alpha = 0.8f),
                    modifier = Modifier.size(14.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "$pendingCount 条修改意见已排队，AI 本轮结束后自动处理",
                    style = MaterialTheme.typography.labelSmall,
                    color = MiColors.AccentCyan.copy(alpha = 0.85f)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            TextField(
                value = input,
                onValueChange = { input = it },
                placeholder = {
                    Text(if (isStreaming) "AI 正在工作，可继续输入…" else "输入问题，或描述你的修改意见…")
                },
                minLines = 1,
                maxLines = 5,
                shape = RoundedCornerShape(22.dp),
                enabled = enabled,
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                    focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    disabledIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
                )
            )
            Spacer(Modifier.width(8.dp))
            Box(Modifier.height(48.dp), contentAlignment = Alignment.Center) {
                if (isStreaming) {
                    Surface(
                        onClick = onStop,
                        shape = RoundedCornerShape(24.dp),
                        color = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    ) {
                        Icon(
                            imageVector = Icons.Default.Stop,
                            contentDescription = "停止",
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                } else {
                    Surface(
                        onClick = {
                            if (input.isNotBlank()) {
                                onSend(input)
                                input = ""
                            }
                        },
                        shape = RoundedCornerShape(24.dp),
                        color = if (enabled) MiColors.AccentBlue else MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = androidx.compose.ui.graphics.Color.White
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "发送",
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }
    }
}
