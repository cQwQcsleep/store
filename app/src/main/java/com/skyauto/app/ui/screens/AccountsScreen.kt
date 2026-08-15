package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
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
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.viewmodel.AccountsViewModel

@Composable
fun AccountsScreen(viewModel: AccountsViewModel = hiltViewModel()) {
    val accounts by viewModel.accounts.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "账号管理") {
        GradientHero(
            title = "我的账号",
            subtitle = "多账号管理 · 状态与数据一览",
            colors = listOf(HyperBlue, com.skyauto.app.ui.theme.HyperLavender)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }

        if (accounts.isEmpty() && !loading) {
            EmptyState("暂无账号，请先添加账号")
        } else {
            accounts.forEach { account ->
                GlassCard {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text(
                                account.displayUsername ?: account.account ?: "未命名账号",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            account.deviceName?.let {
                                Text("设备：$it", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                        Text(statusText(account.status), style = MaterialTheme.typography.labelMedium, color = statusColor(account.status))
                    }
                    Row(Modifier.fillMaxWidth().padding(top = 6.dp)) {
                        Text("平台：${account.platform ?: "-"}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1f))
                        Text("登录方式：${account.loginType ?: "-"}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    account.lastActivityAt?.let {
                        Text("最近活动：${it.take(16).replace("T", " ")}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
    }
}

private fun statusText(status: String?): String = when (status) {
    "在线" -> "在线"
    "离线" -> "离线"
    "pending", "待处理" -> "待处理"
    else -> status ?: "未知"
}

@Composable
private fun statusColor(status: String?): androidx.compose.ui.graphics.Color = when (status) {
    "在线" -> GrassGreen
    "离线" -> MaterialTheme.colorScheme.onSurfaceVariant
    else -> MaterialTheme.colorScheme.tertiary
}