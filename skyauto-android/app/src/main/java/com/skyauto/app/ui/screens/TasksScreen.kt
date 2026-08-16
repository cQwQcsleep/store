package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skyauto.app.data.model.Account
import com.skyauto.app.data.model.Schedule
import com.skyauto.app.ui.components.AccountPicker
import com.skyauto.app.ui.components.EmptyState
import com.skyauto.app.ui.components.GlassCard
import com.skyauto.app.ui.components.GradientHero
import com.skyauto.app.ui.components.HyperLoader
import com.skyauto.app.ui.components.SectionTitle
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperCyan
import com.skyauto.app.ui.theme.RosePink
import com.skyauto.app.ui.theme.SunOrange
import com.skyauto.app.ui.viewmodel.TasksViewModel

@Composable
fun TasksScreen(viewModel: TasksViewModel = hiltViewModel()) {
    val schedules by viewModel.schedules.collectAsState()
    val accounts by viewModel.accounts.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    var showScheduleDialog by remember { mutableStateOf(false) }
    var selectedAccountId by remember { mutableStateOf<Long?>(null) }

    AppScreen(title = "任务计划") {
        val scrollState = rememberScrollState()
        Column(Modifier.verticalScroll(scrollState)) {
            GradientHero(
                title = "自动化任务",
                subtitle = "每日任务 · 跑图 · 排期自动化",
                colors = listOf(HyperBlue, HyperCyan)
            )

            HyperLoader(loading)
            message?.let {
                Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
            }

            // ---- 任务控制 ----
            SectionTitle("任务控制")
            AccountPicker(accounts = accounts, selectedId = selectedAccountId, onSelect = { selectedAccountId = it })
            val aid = selectedAccountId ?: accounts.firstOrNull()?.id
            if (aid != null) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(
                        selected = false,
                        onClick = { viewModel.submit(aid, "daily") },
                        label = { Text("提交每日") },
                        leadingIcon = { Icon(Icons.Rounded.PlayArrow, null, Modifier.size(18.dp)) },
                        modifier = Modifier.weight(1f)
                    )
                    FilterChip(
                        selected = false,
                        onClick = { viewModel.stopAll(aid) },
                        label = { Text("停止全部") },
                        leadingIcon = { Icon(Icons.Rounded.Stop, null, Modifier.size(18.dp)) },
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(
                        selected = false,
                        onClick = { viewModel.reset(aid) },
                        label = { Text("重置任务") },
                        modifier = Modifier.weight(1f)
                    )
                    FilterChip(
                        selected = false,
                        onClick = { viewModel.reset(aid, listOf("runtask")) },
                        label = { Text("重置跑图") },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // ---- 定时计划 ----
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                SectionTitle("定时计划", Modifier.weight(1f))
                IconButton(onClick = { showScheduleDialog = true }) {
                    Icon(Icons.Rounded.Add, "添加计划")
                }
            }

            if (schedules.isEmpty() && !loading) {
                EmptyState("暂无任务排期")
            } else {
                schedules.forEach { schedule ->
                    ScheduleCard(schedule, viewModel)
                }
            }
        }
    }

    if (showScheduleDialog) {
        ScheduleDialog(
            accounts = accounts,
            onDismiss = { showScheduleDialog = false },
            onCreate = { accountId, types, time, start, end ->
                viewModel.createSchedule(accountId, types, time, start, end)
                showScheduleDialog = false
            }
        )
    }
}

@Composable
private fun ScheduleCard(schedule: Schedule, viewModel: TasksViewModel) {
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
            Switch(checked = schedule.active, onCheckedChange = { viewModel.toggleSchedule(schedule, it) })
            Spacer(Modifier.width(4.dp))
            IconButton(onClick = { schedule.id?.let { viewModel.removeSchedule(it) } }) {
                Icon(Icons.Rounded.Delete, "删除", tint = RosePink)
            }
        }
        Text("每日时段：${schedule.timeOfDay ?: "-"}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text("周期：${schedule.startDate ?: "-"} ~ ${schedule.endDate ?: "-"}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun ScheduleDialog(
    accounts: List<Account>,
    onDismiss: () -> Unit,
    onCreate: (Long, List<String>, String, String?, String?) -> Unit
) {
    var selectedAccountId by remember { mutableStateOf(accounts.firstOrNull()?.id) }
    var selectedTypes by remember { mutableStateOf(setOf("daily")) }
    var timeOfDay by remember { mutableStateOf("08:00") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("新建定时计划") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                // 账号选择
                com.skyauto.app.ui.components.AccountPicker(accounts = accounts, selectedId = selectedAccountId, onSelect = { selectedAccountId = it })

                // 任务类型
                Text("任务类型", style = MaterialTheme.typography.labelMedium)
                val allTypes = listOf("daily", "runtask", "world", "fire", "receive_fire", "seasonal_wax", "dye", "wings")
                @OptIn(ExperimentalLayoutApi::class)
                FlowRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    allTypes.forEach { type ->
                        FilterChip(
                            selected = type in selectedTypes,
                            onClick = {
                                selectedTypes = if (type in selectedTypes) selectedTypes - type
                                else selectedTypes + type
                            },
                            label = { Text(TASK_LABELS[type] ?: type, style = MaterialTheme.typography.bodySmall) }
                        )
                    }
                }

                OutlinedTextField(
                    value = timeOfDay,
                    onValueChange = { timeOfDay = it },
                    label = { Text("每日时段 (HH:mm)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp)
                )

                OutlinedTextField(
                    value = startDate,
                    onValueChange = { startDate = it },
                    label = { Text("开始日期 (可选, YYYY-MM-DD)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp)
                )

                OutlinedTextField(
                    value = endDate,
                    onValueChange = { endDate = it },
                    label = { Text("结束日期 (可选, YYYY-MM-DD)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp)
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                selectedAccountId?.let { aid ->
                    onCreate(aid, selectedTypes.toList(), timeOfDay, startDate.ifBlank { null }, endDate.ifBlank { null })
                }
            }) { Text("创建") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("取消") } }
    )
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