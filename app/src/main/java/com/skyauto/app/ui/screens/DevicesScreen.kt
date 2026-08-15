package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.skyauto.app.ui.components.EmptyState
import com.skyauto.app.ui.components.GlassCard
import com.skyauto.app.ui.components.GradientHero
import com.skyauto.app.ui.components.HyperLoader
import com.skyauto.app.ui.components.SectionTitle
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.viewmodel.DevicesViewModel

@Composable
fun DevicesScreen(viewModel: DevicesViewModel = hiltViewModel()) {
    val devices by viewModel.devices.collectAsState()
    val models by viewModel.models.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()
    var name by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }

    AppScreen(title = "设备管理") {
        GradientHero(
            title = "我的设备",
            subtitle = "云手机设备规模与注册",
            colors = listOf(HyperBlue, com.skyauto.app.ui.theme.HyperCyan)
        )

        HyperLoader(loading)

        SectionTitle("注册新设备")
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("设备名称（选填）") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = model,
            onValueChange = { model = it },
            label = { Text("设备型号") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                if (models.isNotEmpty()) Text("可选：" + models.joinToString(" / ") { it.label ?: it.id ?: "" })
            }
        )
        Button(
            onClick = { viewModel.create(name, model.ifBlank { null }) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GrassGreen)
        ) {
            Text("创建设备")
        }

        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
        }

        SectionTitle("设备列表")
        if (devices.isEmpty() && !loading) {
            EmptyState("暂无设备")
        } else {
            devices.forEach { device ->
                GlassCard {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(device.name ?: "设备 #${device.id ?: "-"}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
                        Text(device.status ?: "未知", style = MaterialTheme.typography.labelMedium, color = com.skyauto.app.ui.theme.HyperBlue)
                    }
                    Text("型号：${device.model ?: "-"}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}