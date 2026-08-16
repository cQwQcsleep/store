package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skyauto.app.ui.components.GlassCard
import com.skyauto.app.ui.components.GradientHero
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.viewmodel.AiViewModel

@Composable
fun AiScreen(viewModel: AiViewModel = hiltViewModel()) {
    var input by remember { mutableStateOf("") }
    val messages by viewModel.messages.collectAsState()
    val thinking by viewModel.thinking.collectAsState()

    AppScreen(title = "AI 助手") {
        GradientHero(
            title = "SkyAuto AI",
            subtitle = "基于大模型的智能问答与诊断",
            colors = listOf(HyperBlue, HyperLavender)
        )

        messages.forEach { msg ->
            GlassCard {
                Column {
                    Text(
                        if (msg.isUser) "我" else "AI",
                        style = MaterialTheme.typography.labelMedium,
                        color = if (msg.isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(msg.content, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

        if (thinking) {
            Text("AI 思考中…", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("向 AI 提问…") },
                shape = RoundedCornerShape(18.dp)
            )
            Button(
                onClick = { viewModel.send(input); input = "" },
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GrassGreen),
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(Icons.Rounded.Send, contentDescription = "发送")
            }
        }
    }
}