package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
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
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperCyan
import com.skyauto.app.ui.viewmodel.TasksViewModel

@Composable
fun TasksScreen(viewModel: TasksViewModel = hiltViewModel()) {
    val schedules by viewModel.schedules.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "任务计划") {
        GradientHero(
            title = "自动化任务",
            subtitle = "每日任务 · 跑图 · 排期自动化",
            colors = listOf(HyperBlue, HyperCyan)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
        }

        SectionTitle("任务排期")
        if (schedules.isEmpty() && !loading) {
            EmptyState("暂无任务排期")
        } else {
            schedules.forEach { schedule ->
                GlassCard {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text(
                                schedule.accountName?.takeIf { it.isNotBlank() } ?: "账号 #${schedule.accountId ?: "-"}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                "任务：" + schedule.taskTypes.joinToString("、") { TASK_LABELS[it] ?: it },
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Switch(checked = schedule.active, onCheckedChange = null)
                    }
                    Text("每日时段：${schedule.timeOfDay ?: "-"}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("周期：${schedule.startDate ?: "-"} ~ ${schedule.endDate ?: "-"}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

private val TASK_LABELS = mapOf(
    "daily" to "每日任务",
    "runtask" to "跑图",
    "world" to "大世界",
    "fire" to "收火",
    "receive_fire" to "收心火",
    "seasonal_wax" to "季节蜡",
    "dye" to "染色",
    "wings" to "光翼"
)