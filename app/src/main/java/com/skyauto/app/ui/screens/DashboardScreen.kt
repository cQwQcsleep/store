package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.DoneAll
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.CheckCircle
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
import com.skyauto.app.ui.components.StatCard
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.theme.SunOrange
import com.skyauto.app.ui.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()) {
    val stats by viewModel.stats.collectAsState()
    val online by viewModel.online.collectAsState()
    val loading by viewModel.loading.collectAsState()

    AppScreen(title = "工作台") {
        GradientHero(
            title = "平台运行实况",
            subtitle = when {
                online != null -> "当前在线设备 ${online} 台"
                else -> "实时汇总生产任务与设备数据"
            }
        )

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard("服务账号", (stats?.accounts ?: 0L).toString(), Icons.Outlined.People, HyperBlue, Modifier.weight(1f))
            StatCard("执行设备", (stats?.devices ?: 0L).toString(), Icons.Outlined.Devices, HyperLavender, Modifier.weight(1f))
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard("已完成", (stats?.completed ?: 0L).toString(), Icons.Outlined.DoneAll, GrassGreen, Modifier.weight(1f))
            StatCard("完成率", "${(stats?.completionRate ?: 0.0) * 100}%", Icons.Outlined.CheckCircle, SunOrange, Modifier.weight(1f))
        }

        HyperLoader(loading)

        if (!loading && stats != null) {
            SectionTitle("主要任务类型")
            GlassCard {
                val types = stats?.mainTaskTypes.orEmpty()
                if (types.isEmpty()) {
                    Text("暂无执行记录", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                } else {
                    types.forEach { t ->
                        Row(Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                            Text(t.type ?: "-", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
                            Text("${t.count ?: 0} 次", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        } else if (!loading) {
            EmptyState("暂无统计数据")
        }
    }
}