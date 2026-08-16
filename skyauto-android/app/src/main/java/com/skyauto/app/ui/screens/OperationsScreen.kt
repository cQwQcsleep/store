package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.skyauto.app.ui.components.StatCard
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperCyan
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.theme.SunOrange
import com.skyauto.app.ui.viewmodel.OperationsViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.TaskAlt

@Composable
fun OperationsScreen(viewModel: OperationsViewModel = hiltViewModel()) {
    val stats by viewModel.stats.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "运营数据") {
        GradientHero(
            title = "平台运营数据",
            subtitle = "账号 · 任务 · 设备运行概况",
            colors = listOf(HyperBlue, HyperCyan)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }

        if (stats == null && !loading) {
            EmptyState("暂无运营数据")
            return@AppScreen
        }

        stats?.let { s ->
            SectionTitle("今日")
            PeriodBlock(s.today.taskRuns, s.today.accounts, s.today.executionDevices, s.today.completedRuns)
            Spacer(Modifier.height(6.dp))
            SectionTitle("本月")
            PeriodBlock(s.month.taskRuns, s.month.accounts, s.month.executionDevices, s.month.completedRuns)
            Spacer(Modifier.height(6.dp))

            SectionTitle("设备总数")
            GlassCard {
                Row(Modifier.fillMaxWidth()) {
                    Text("总设备：${s.devices.total}", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
                    Text("今日新增：${s.devices.todayAdded}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                }
                Text("本月新增：${s.devices.monthAdded}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            val types = (s.today.taskTypes + s.month.taskTypes).distinctBy { it.type }
            if (types.isNotEmpty()) {
                SectionTitle("任务类型分布")
                types.forEach { t ->
                    GlassCard {
                        Column(Modifier.fillMaxWidth()) {
                            Text(t.label ?: t.type ?: "任务", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                            Row(Modifier.fillMaxWidth().padding(top = 4.dp)) {
                                Text("今日：${t.runs} 次", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1f))
                                Text("完成：${t.completed}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PeriodBlock(runs: Long, accounts: Long, devices: Long, completed: Long) {
    Row(Modifier.fillMaxWidth()) {
        StatCard(
            label = "任务运行",
            value = "$runs",
            icon = Icons.Outlined.TaskAlt,
            tint = HyperBlue,
            modifier = Modifier.weight(1f).padding(end = 6.dp)
        )
        StatCard(
            label = "账号数",
            value = "$accounts",
            icon = Icons.Outlined.People,
            tint = HyperLavender,
            modifier = Modifier.weight(1f).padding(horizontal = 3.dp)
        )
    }
    Spacer(Modifier.height(8.dp))
    Row(Modifier.fillMaxWidth()) {
        StatCard(
            label = "执行设备",
            value = "$devices",
            icon = Icons.Outlined.Devices,
            tint = HyperCyan,
            modifier = Modifier.weight(1f).padding(end = 6.dp)
        )
        StatCard(
            label = "完成任务",
            value = "$completed",
            icon = Icons.Outlined.TaskAlt,
            tint = GrassGreen,
            modifier = Modifier.weight(1f).padding(horizontal = 3.dp)
        )
    }
}
