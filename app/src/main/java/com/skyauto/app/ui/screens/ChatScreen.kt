package com.skyauto.app.ui.screens

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
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skyauto.app.data.model.ChatMessage
import com.skyauto.app.ui.components.EmptyState
import com.skyauto.app.ui.components.GlassCard
import com.skyauto.app.ui.components.GradientHero
import com.skyauto.app.ui.components.HyperLoader
import com.skyauto.app.ui.components.SectionTitle
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.RosePink
import com.skyauto.app.ui.viewmodel.ChatMessagesViewModel

@Composable
fun ChatScreen(viewModel: ChatMessagesViewModel = hiltViewModel()) {
    val rooms by viewModel.rooms.collectAsState()
    val selectedRoomId by viewModel.selectedRoomId.collectAsState()
    val messages by viewModel.messages.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "聊天室") {
        GradientHero(
            title = "社区聊天室",
            subtitle = "房间 · 在线人数 · 消息",
            colors = listOf(RosePink, HyperBlue)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }

        // 房间选择
        SectionTitle("房间")
        if (rooms.isEmpty() && !loading) {
            EmptyState("暂无聊天室")
        } else {
            rooms.forEach { room ->
                val selected = room.id == selectedRoomId
                Surface(
                    onClick = { room.id?.let(viewModel::loadRoom) },
                    shape = RoundedCornerShape(20.dp),
                    color = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                    else MaterialTheme.colorScheme.surface.copy(alpha = 0.82f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Outlined.Forum,
                            null,
                            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(
                            room.name ?: "聊天室",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            "在线 ${room.online ?: 0}",
                            style = MaterialTheme.typography.labelMedium,
                            color = GrassGreen
                        )
                    }
                }
            }
        }

        // 消息列表
        val activeRoom = rooms.firstOrNull { it.id == selectedRoomId }
        if (activeRoom != null) {
            SectionTitle("${activeRoom.name ?: "聊天室"} · 消息")
            if (messages.isEmpty()) {
                EmptyState("暂无消息")
            } else {
                messages.takeLast(30).forEach { msg ->
                    MessageBubble(msg)
                }
            }
        }
    }
}

@Composable
private fun MessageBubble(msg: ChatMessage) {
    Row(Modifier.fillMaxWidth().padding(vertical = 4.dp), verticalAlignment = Alignment.Top) {
        Column(Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    msg.username ?: "用户",
                    style = MaterialTheme.typography.labelMedium,
                    color = if (msg.isAdmin) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = if (msg.isAdmin) FontWeight.SemiBold else FontWeight.Normal
                )
                if (msg.isAdmin) {
                    Spacer(Modifier.width(6.dp))
                    Text("管理员", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                }
                Spacer(Modifier.width(8.dp))
                msg.createdAt?.let {
                    Text(it.take(16).replace("T", " "), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
                }
            }
            Spacer(Modifier.height(2.dp))
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = if (msg.isAdmin) MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
                else MaterialTheme.colorScheme.surfaceContainer,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Text(
                    msg.content ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                )
            }
        }
    }
}
