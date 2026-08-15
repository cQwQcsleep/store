package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.theme.RosePink
import com.skyauto.app.ui.viewmodel.SpiritsViewModel

@Composable
fun SpiritsScreen(viewModel: SpiritsViewModel = hiltViewModel()) {
    val heartTrade by viewModel.heartTrade.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "灵犀 · 心火") {
        GradientHero(
            title = "灵犀与心火",
            subtitle = "灵犀等级 · 心火交易 · 灵犀商店",
            colors = listOf(RosePink, HyperLavender)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }

        SectionTitle("心火交易账号")
        val accounts = heartTrade?.accounts.orEmpty()
        if (accounts.isEmpty() && !loading) {
            EmptyState("暂无进行中的心火交易账号")
        } else {
            accounts.forEach { acc ->
                GlassCard {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            acc.displayUsername ?: "账号 #${acc.id}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            acc.status ?: "未知",
                            style = MaterialTheme.typography.labelMedium,
                            color = if (acc.ready) GrassGreen else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Text(
                        if (acc.enabled) "已启用" else "未启用",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        SectionTitle("心火批次")
        val batches = heartTrade?.batches.orEmpty()
        if (batches.isEmpty() && !loading) {
            EmptyState("暂无进行中的心火批次")
        } else {
            batches.forEach { batch ->
                GlassCard {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text("批次 #${batch.id}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
                        Text(batch.status ?: "-", style = MaterialTheme.typography.labelMedium, color = GrassGreen)
                    }
                    Text("数量：${batch.count ?: 0}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}
