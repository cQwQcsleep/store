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
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperCyan
import com.skyauto.app.ui.theme.SunOrange
import com.skyauto.app.ui.viewmodel.WechatBindingViewModel

@Composable
fun WechatBindingScreen(viewModel: WechatBindingViewModel = hiltViewModel()) {
    val status by viewModel.status.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "微信绑定") {
        GradientHero(
            title = "微信绑定",
            subtitle = "绑定微信接收任务通知",
            colors = listOf(HyperBlue, HyperCyan)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }

        if (status == null && !loading) {
            EmptyState("暂无绑定信息")
            return@AppScreen
        }

        status?.let { s ->
            SectionTitle("绑定状态")
            GlassCard {
                Column(Modifier.fillMaxWidth()) {
                    Row(Modifier.fillMaxWidth()) {
                        Text("是否已绑定", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
                        Text(
                            if (s.bound) "已绑定" else "未绑定",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = if (s.bound) GrassGreen else SunOrange
                        )
                    }
                    if (s.boundAt != null) {
                        Spacer(Modifier.height(4.dp))
                        Text("绑定时间：${s.boundAt}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }

            SectionTitle("通知渠道")
            GlassCard {
                InfoRow("服务已配置", s.configured)
                InfoRow("通知已开启", s.notificationsEnabled)
                InfoRow("已订阅", s.subscribed)
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, enabled: Boolean) {
    Row(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(label, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
        Text(
            if (enabled) "已开启" else "未开启",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = if (enabled) GrassGreen else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}