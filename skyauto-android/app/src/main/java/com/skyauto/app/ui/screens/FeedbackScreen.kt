package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Reply
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skyauto.app.data.model.FeedbackTicket
import com.skyauto.app.ui.components.EmptyState
import com.skyauto.app.ui.components.GlassCard
import com.skyauto.app.ui.components.GradientHero
import com.skyauto.app.ui.components.HyperLoader
import com.skyauto.app.ui.components.SectionTitle
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperCyan
import com.skyauto.app.ui.theme.SunOrange
import com.skyauto.app.ui.viewmodel.FeedbackViewModel

@Composable
fun FeedbackScreen(viewModel: FeedbackViewModel = hiltViewModel()) {
    val tickets by viewModel.tickets.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    var replyFor by remember { mutableStateOf<FeedbackTicket?>(null) }

    AppScreen(title = "反馈工单") {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            GradientHero(
                title = "反馈工单",
                subtitle = "提交的反馈与处理进度",
                colors = listOf(HyperCyan, GrassGreen)
            )

            HyperLoader(loading)
            message?.let {
                Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
            }

            SectionTitle("我的工单")
            if (tickets.isEmpty() && !loading) {
                EmptyState("暂无反馈工单")
            } else {
                tickets.forEach { ticket ->
                    GlassCard {
                        Column(Modifier.fillMaxWidth()) {
                            Text(
                                ticket.title ?: "工单 #${ticket.id}",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(Modifier.height(4.dp))
                            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    ticket.status ?: "-",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (ticket.status?.contains("处理") == true) SunOrange else GrassGreen,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    ticket.createdAt ?: "",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Spacer(Modifier.height(8.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                OutlinedButton(
                                    onClick = { replyFor = ticket },
                                    shape = RoundedCornerShape(18.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(Icons.Rounded.Reply, null, Modifier.size(18.dp))
                                    Spacer(Modifier.width(6.dp))
                                    Text("回复")
                                }
                                OutlinedButton(
                                    onClick = { ticket.id?.let { viewModel.close(it) } },
                                    shape = RoundedCornerShape(18.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(Icons.Rounded.Close, null, Modifier.size(18.dp))
                                    Spacer(Modifier.width(6.dp))
                                    Text("关闭")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    replyFor?.let { ticket ->
        ReplyDialog(
            ticket = ticket,
            onDismiss = { replyFor = null },
            onSend = { content ->
                ticket.id?.let { viewModel.reply(it, content) }
                replyFor = null
            }
        )
    }
}

@Composable
private fun ReplyDialog(
    ticket: FeedbackTicket,
    onDismiss: () -> Unit,
    onSend: (String) -> Unit
) {
    var content by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("回复工单") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    ticket.title ?: "工单 #${ticket.id}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("回复内容") },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp)
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onSend(content) }) { Text("发送") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("取消") } }
    )
}