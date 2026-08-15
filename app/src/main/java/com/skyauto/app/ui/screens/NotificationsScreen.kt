package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.viewmodel.NotificationsViewModel

@Composable
fun NotificationsScreen(viewModel: NotificationsViewModel = hiltViewModel()) {
    val items by viewModel.items.collectAsState()
    val loading by viewModel.loading.collectAsState()

    AppScreen(title = "通知中心") {
        GradientHero(
            title = "通知与公告",
            subtitle = "系统公告 · 消息中心",
            colors = listOf(HyperBlue, HyperLavender)
        )

        HyperLoader(loading)

        Button(
            onClick = { viewModel.readAll() },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GrassGreen),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("全部标为已读")
        }

        if (items.isEmpty() && !loading) {
            EmptyState("暂无通知")
        } else {
            items.forEach { item ->
                GlassCard {
                    Text(item.title ?: "通知", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    item.content?.let {
                        Text(it, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Row(Modifier.fillMaxWidth().padding(top = 6.dp)) {
                        Text(item.createdAt ?: "", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1f))
                        if (item.read != true) {
                            Text("未读", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}