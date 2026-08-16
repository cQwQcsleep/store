package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skyauto.app.data.model.HeartTradeAccount
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

    var showBatchDialog by remember { mutableStateOf(false) }
    val accounts = heartTrade?.accounts.orEmpty()

    AppScreen(title = "灵犀 · 心火") {
        Column(Modifier.verticalScroll(rememberScrollState())) {
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

            // ---- 创建心火批次 ----
            if (accounts.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = { showBatchDialog = true },
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("创建心火批次")
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

    if (showBatchDialog) {
        BatchDialog(
            accounts = accounts,
            onDismiss = { showBatchDialog = false },
            onCreate = { suppliers, receiver ->
                viewModel.createBatch(suppliers, receiver)
                showBatchDialog = false
            }
        )
    }
}

@Composable
private fun BatchDialog(
    accounts: List<HeartTradeAccount>,
    onDismiss: () -> Unit,
    onCreate: (List<Long>, Long) -> Unit
) {
    var receiverId by remember { mutableStateOf(accounts.firstOrNull()?.id) }
    var suppliers by remember { mutableStateOf(setOf<Long>()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("创建心火批次") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("接收方账号", style = MaterialTheme.typography.labelMedium)
                accounts.forEach { acc ->
                    val selected = receiverId == acc.id
                    FilterChip(
                        selected = selected,
                        onClick = {
                            receiverId = acc.id
                            suppliers = suppliers - (acc.id ?: 0L)
                        },
                        label = { Text(acc.displayUsername ?: "账号 #${acc.id}") }
                    )
                }

                Text("送心方账号（可多选）", style = MaterialTheme.typography.labelMedium)
                accounts.forEach { acc ->
                    val id = acc.id ?: return@forEach
                    val selected = id in suppliers && receiverId != id
                    FilterChip(
                        selected = selected,
                        onClick = {
                            suppliers = if (selected) suppliers - id else suppliers + id
                        },
                        label = { Text(acc.displayUsername ?: "账号 #${acc.id}") }
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                receiverId?.let { rcvr ->
                    onCreate(suppliers.toList(), rcvr)
                }
            }) { Text("创建") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("取消") } }
    )
}