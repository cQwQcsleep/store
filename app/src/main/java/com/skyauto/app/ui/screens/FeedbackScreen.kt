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
import com.skyauto.app.ui.theme.HyperCyan
import com.skyauto.app.ui.theme.SunOrange
import com.skyauto.app.ui.viewmodel.FeedbackViewModel

@Composable
fun FeedbackScreen(viewModel: FeedbackViewModel = hiltViewModel()) {
    val tickets by viewModel.tickets.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "反馈工单") {
        GradientHero(
            title = "反馈工单",
            subtitle = "提交的反馈与处理进度",
            colors = listOf(HyperCyan, GrassGreen)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }

        SectionTitle("我的工单")
        if (tickets.isEmpty() && !loading) {
            EmptyState("暂无反馈工单")
        } else {
            tickets.forEach { ticket ->
                GlassCard {
                    Column(Modifier.fillMaxWidth()) {
                        Text(
                            ticket.title ?: "工单 #${ticket.id}",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.height(4.dp))
                        Row(Modifier.fillMaxWidth()) {
                            Text(
                                ticket.status ?: "-",
                                style = MaterialTheme.typography.bodySmall,
                                color = if (ticket.status?.contains("处理") == true) SunOrange else GrassGreen
                            )
                            Spacer(Modifier.weight(1f))
                            Text(
                                ticket.createdAt ?: "",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}