package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperCyan
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.viewmodel.InvitationsViewModel

@Composable
fun InvitationsScreen(viewModel: InvitationsViewModel = hiltViewModel()) {
    val data by viewModel.data.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "邀请") {
        GradientHero(
            title = "邀请有礼",
            subtitle = "邀请好友 · 获取账号额度",
            colors = listOf(HyperLavender, HyperCyan)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }

        if (data == null && !loading) {
            EmptyState("暂无邀请数据")
            return@AppScreen
        }

        data?.let { inv ->
            GlassCard {
                Column(Modifier.fillMaxWidth()) {
                    Text("我的邀请码", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(
                        inv.code ?: "-",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    inv.inviteUrl?.let {
                        Text("链接：$it", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Row(Modifier.fillMaxWidth().padding(top = 8.dp)) {
                        Text("成功邀请：${inv.successCount}", style = MaterialTheme.typography.bodyMedium, color = GrassGreen, modifier = Modifier.weight(1f))
                        Text("奖励额度：${inv.rewardedQuota}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            SectionTitle("邀请记录")
            if (inv.records.isEmpty()) {
                EmptyState("暂无邀请记录")
            } else {
                inv.records.forEach { record ->
                    GlassCard {
                        Column(Modifier.fillMaxWidth()) {
                            Text(record.username ?: "新用户", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                            Row(Modifier.fillMaxWidth().padding(top = 4.dp)) {
                                Text("状态：${record.status ?: "-"}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1f))
                                record.createdAt?.let {
                                    Text(it.take(10), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
