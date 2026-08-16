package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skyauto.app.ui.components.EmptyState
import com.skyauto.app.ui.components.GlassCard
import com.skyauto.app.ui.components.GradientHero
import com.skyauto.app.ui.components.HyperLoader
import com.skyauto.app.ui.components.SectionTitle
import com.skyauto.app.ui.theme.CoralRed
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.theme.WarmAmber
import com.skyauto.app.ui.viewmodel.AnnouncementsViewModel

@Composable
fun AnnouncementsScreen(viewModel: AnnouncementsViewModel = hiltViewModel()) {
    val announcements by viewModel.announcements.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "公告") {
        GradientHero(
            title = "系统公告",
            subtitle = "平台通知 · 维护 · 更新动态",
            colors = listOf(HyperBlue, HyperLavender)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }

        if (announcements.isEmpty() && !loading) {
            EmptyState("暂无公告")
        } else {
            announcements.forEach { item ->
                GlassCard {
                    Column(Modifier.fillMaxWidth()) {
                        Text(
                            item.title ?: "公告",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            levelLabel(item.level),
                            style = MaterialTheme.typography.labelSmall,
                            color = levelColor(item.level)
                        )
                        item.summary?.let {
                            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        item.content?.takeIf { it.isNotBlank() }?.let {
                            Text(
                                it,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(top = 6.dp)
                            )
                        }
                        (item.publishedAt ?: item.createdAt)?.let {
                            Text("发布于：${it.take(16).replace("T", " ")}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline)
                        }
                    }
                }
            }
        }
    }
}

private fun levelLabel(level: String?): String = when (level) {
    "urgent", "critical" -> "重要"
    "warning", "info" -> "提示"
    "success" -> "好消息"
    else -> level ?: "公告"
}

@Composable
private fun levelColor(level: String?): androidx.compose.ui.graphics.Color = when (level) {
    "urgent", "critical" -> CoralRed
    "success" -> WarmAmber
    else -> MaterialTheme.colorScheme.primary
}
