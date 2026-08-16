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
import com.skyauto.app.ui.components.AccountPicker
import com.skyauto.app.ui.components.EmptyState
import com.skyauto.app.ui.components.GlassCard
import com.skyauto.app.ui.components.GradientHero
import com.skyauto.app.ui.components.HyperLoader
import com.skyauto.app.ui.components.SectionTitle
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.theme.WarmAmber
import com.skyauto.app.ui.viewmodel.TaskHistoryViewModel

@Composable
fun TaskHistoryScreen(viewModel: TaskHistoryViewModel = hiltViewModel()) {
    val accounts by viewModel.accounts.collectAsState()
    val selectedId by viewModel.selectedAccountId.collectAsState()
    val records by viewModel.records.collectAsState()
    val doneToday by viewModel.doneToday.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "任务执行详情") {
        GradientHero(
            title = "任务执行详情",
            subtitle = "执行记录 · 今日完成情况",
            colors = listOf(HyperBlue, HyperLavender)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }

        AccountPicker(accounts, selectedId, viewModel::loadAccount)

        doneToday?.let { d ->
            SectionTitle("今日完成")
            GlassCard {
                val doneLabels = listOf(
                    d.dailyDone to "每日",
                    d.fireDone to "收火",
                    d.runtaskDone to "跑图",
                    d.heartDone to "心火",
                    d.receiveFireDone to "收心火",
                    d.sacrificeDone to "献祭",
                    d.seasonalWaxDone to "季节蜡",
                    d.seasonPassDone to "季卡",
                    d.worldDone to "大世界",
                    d.dyeDone to "染色",
                    d.wingsDone to "光翼"
                ).filter { it.first }.map { it.second }
                if (doneLabels.isEmpty()) {
                    Text("今日暂无已完成任务", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                } else {
                    Text(doneLabels.joinToString(" · "), style = MaterialTheme.typography.bodyMedium, color = GrassGreen)
                }
            }
        }

        SectionTitle("执行记录")
        if (records.isEmpty() && !loading) {
            EmptyState("暂无执行记录")
        } else {
            records.forEach { record ->
                GlassCard {
                    Column(Modifier.fillMaxWidth()) {
                        Row(Modifier.fillMaxWidth()) {
                            Text(
                                record.taskType ?: "任务",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                statusText(record.status),
                                style = MaterialTheme.typography.labelMedium,
                                color = statusColor(record.status)
                            )
                        }
                        Row(Modifier.fillMaxWidth().padding(top = 4.dp)) {
                            Text("来源：${record.source ?: "-"}", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1f))
                            Text("进度：${record.progress}%", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                        }
                        record.message?.let {
                            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        (record.updatedAt ?: record.createdAt)?.let {
                            Text("更新：${it.take(16).replace("T", " ")}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline)
                        }
                        if (record.logs.isNotEmpty()) {
                            record.logs.take(4).forEach { log ->
                                Text("• $log", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun statusText(status: String?): String = when (status) {
    "running", "processing", "执行中" -> "执行中"
    "completed", "success", "已完成" -> "已完成"
    "failed", "error", "失败" -> "失败"
    "pending", "等待中" -> "等待中"
    else -> status ?: "未知"
}

@Composable
private fun statusColor(status: String?): androidx.compose.ui.graphics.Color = when (status) {
    "running", "processing", "执行中", "pending", "等待中" -> WarmAmber
    "completed", "success", "已完成" -> GrassGreen
    "failed", "error", "失败" -> MaterialTheme.colorScheme.error
    else -> MaterialTheme.colorScheme.onSurfaceVariant
}
