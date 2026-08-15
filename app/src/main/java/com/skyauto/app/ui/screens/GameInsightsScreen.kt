package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skyauto.app.data.model.GameInsightsResponse
import com.skyauto.app.ui.components.AccountPicker
import com.skyauto.app.ui.components.EmptyState
import com.skyauto.app.ui.components.GlassCard
import com.skyauto.app.ui.components.GradientHero
import com.skyauto.app.ui.components.HyperLoader
import com.skyauto.app.ui.components.SectionTitle
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperCyan
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.viewmodel.GameInsightsViewModel

@Composable
fun GameInsightsScreen(viewModel: GameInsightsViewModel = hiltViewModel()) {
    val accounts by viewModel.accounts.collectAsState()
    val selectedId by viewModel.selectedAccountId.collectAsState()
    val publicInsights by viewModel.publicInsights.collectAsState()
    val insights by viewModel.insights.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val refreshing by viewModel.refreshing.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "游戏情报") {
        GradientHero(
            title = "游戏情报中心",
            subtitle = "官方数据 · 账号动态",
            colors = listOf(HyperBlue, HyperLavender)
        )

        HyperLoader(loading || refreshing)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }

        AccountPicker(accounts, selectedId, viewModel::selectAccount)

        publicInsights?.announcement?.let { ann ->
            GlassCard {
                Column(Modifier.fillMaxWidth()) {
                    Text("官方公告", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(ann.title ?: "公告", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    ann.content?.let { Text(it, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 4.dp)) }
                    ann.updatedHint?.let {
                        Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline)
                    }
                }
            }
        }

        insights?.let { showInsights(it) }
        if (insights == null && !loading && accounts.isNotEmpty()) {
            EmptyState("暂无情报数据")
        }
    }
}

@Composable
private fun showInsights(data: GameInsightsResponse) {
    data.serverTime?.iso?.let {
        Text("服务器时间：${it.take(19).replace("T", " ")}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline)
    }

    data.details?.let { details ->
        details.commerce?.let { c ->
            SectionTitle("商店")
            Row(Modifier.fillMaxWidth()) {
                InfoCell("商店物品", c.shopItems, Modifier.weight(1f))
                InfoCell("IAP 内购", c.iapItems, Modifier.weight(1f))
                InfoCell("宝箱", c.lootboxes, Modifier.weight(1f))
            }
        }
        details.exploration?.let { e ->
            SectionTitle("探索")
            GlassCard {
                Row(Modifier.fillMaxWidth()) {
                    InfoCell("收集品", e.collectibles, Modifier.weight(1f))
                    InfoCell("检查点", e.checkpoints, Modifier.weight(1f))
                    InfoCell("物品解锁", e.inventoryUnlocks, Modifier.weight(1f))
                }
            }
        }
    }

    data.magic?.let { m ->
        SectionTitle("魔法")
        GlassCard {
            Row(Modifier.fillMaxWidth()) {
                InfoCell("增益", m.activeBuffs, Modifier.weight(1f))
                InfoCell("消耗品", m.consumables, Modifier.weight(1f))
            }
        }
    }

    data.eventCurrencies?.eventCurrencyDefs?.takeIf { it.isNotEmpty() }?.let { defs ->
        SectionTitle("活动货币")
        defs.forEach { d ->
            GlassCard {
                Text(d.eventName ?: d.clientCurrency ?: "活动", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                d.clientCurrency?.let {
                    Text("客户端：$it", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }

    data.events?.events?.takeIf { it.isNotEmpty() }?.let { events ->
        SectionTitle("活动时间表")
        events.forEach { e ->
            GlassCard {
                Row(Modifier.fillMaxWidth()) {
                    Text(e.name ?: "活动", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
                    Text(if (e.active) "进行中" else "未开始", style = MaterialTheme.typography.labelMedium, color = if (e.active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant)
                }
                if (e.startTime != null && e.endTime != null) {
                    Text(
                        "${epochTime(e.startTime)} ~ ${epochTime(e.endTime)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }

    data.quests?.let { q ->
        val all = q.active + q.collected + q.canceled
        if (all.isNotEmpty()) {
            SectionTitle("任务（${q.active.size} 进行中）")
            q.active.take(8).forEach { quest ->
                GlassCard {
                    Text(quest.name ?: "任务", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                    Row(Modifier.fillMaxWidth().padding(top = 4.dp)) {
                        Text("状态：${quest.status ?: "-"}", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1f))
                        if (quest.progress != null) {
                            Text("进度：${quest.progress}${quest.required?.let { "/$it" } ?: ""}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }

    if (data.errors.isNotEmpty()) {
        SectionTitle("部分数据加载失败")
        data.errors.forEach { e ->
            Text(
                e.section?.let { "[$it] " } + (e.message ?: ""),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
private fun InfoCell(label: String, value: Long, modifier: Modifier = Modifier) {
    Column(modifier.padding(vertical = 8.dp)) {
        Text("$value", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        Text(label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

private fun epochTime(epoch: Long): String {
    val ms = if (epoch > 10_000_000_000L) epoch else epoch * 1000L
    val sdf = java.text.SimpleDateFormat("MM-dd HH:mm", java.util.Locale.getDefault())
    sdf.timeZone = java.util.TimeZone.getDefault()
    return sdf.format(java.util.Date(ms))
}
