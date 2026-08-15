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
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperCyan
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.theme.SunOrange
import com.skyauto.app.ui.viewmodel.ConfigRulesViewModel

@Composable
fun ConfigRulesScreen(viewModel: ConfigRulesViewModel = hiltViewModel()) {
    val data by viewModel.data.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "配置规则") {
        GradientHero(
            title = "账号配置规则",
            subtitle = "地图 · 任务 · 规则绑定",
            colors = listOf(SunOrange, HyperBlue)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }

        if (data == null && !loading) {
            EmptyState("暂无配置规则")
            return@AppScreen
        }

        data?.let { d ->
            if (d.rules.isNotEmpty()) {
                SectionTitle("规则")
                d.rules.forEach { rule ->
                    GlassCard {
                        Column(Modifier.fillMaxWidth()) {
                            Row(Modifier.fillMaxWidth()) {
                                Text(rule.name ?: "规则", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
                                rule.group?.let {
                                    Text(it, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                                }
                            }
                            if (rule.defaultTaskTypes.isNotEmpty()) {
                                Text(
                                    "默认任务：" + rule.defaultTaskTypes.joinToString("、"),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            if (rule.bindings.isNotEmpty()) {
                                Text("绑定账号：${rule.bindings.joinToString("、")}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }

            if (d.maps.isNotEmpty()) {
                SectionTitle("地图")
                d.maps.forEach { map ->
                    GlassCard {
                        Column(Modifier.fillMaxWidth()) {
                            Row(Modifier.fillMaxWidth()) {
                                Text(map.label ?: map.code ?: "地图", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
                                map.code?.let { Text(it, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant) }
                            }
                            Row(Modifier.fillMaxWidth().padding(top = 4.dp)) {
                                Text("心火：${map.fireCount}", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1f))
                                Text("蜡火：${map.waxCount}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                                map.dyeCount?.let {
                                    Text("  染料：$it", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                        }
                    }
                }
            }

            if (d.accounts.isNotEmpty()) {
                SectionTitle("适用账号")
                d.accounts.forEach { acc ->
                    GlassCard {
                        Row(Modifier.fillMaxWidth()) {
                            Text(acc.name ?: acc.account ?: "账号", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
                            acc.account?.let { Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant) }
                        }
                    }
                }
            }
        }
    }
}
