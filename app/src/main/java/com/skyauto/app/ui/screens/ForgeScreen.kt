package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.skyauto.app.ui.theme.HyperCyan
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.viewmodel.ForgeViewModel

@Composable
fun ForgeScreen(viewModel: ForgeViewModel = hiltViewModel()) {
    val accounts by viewModel.accounts.collectAsState()
    val selectedId by viewModel.selectedAccountId.collectAsState()
    val options by viewModel.options.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "货币合成") {
        GradientHero(
            title = "货币合成",
            subtitle = "蜡火 · 染料合成兑换",
            colors = listOf(HyperCyan, HyperLavender)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = if (it.contains("成功")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)
        }

        AccountPicker(accounts, selectedId, viewModel::loadAccount)

        SectionTitle("可合成项目")
        if (options.isEmpty() && !loading) {
            EmptyState("暂无合成项目")
        } else {
            options.forEach { option ->
                GlassCard {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text(option.label ?: option.key ?: "合成", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                            Row(Modifier.padding(top = 2.dp)) {
                                Text("消耗 ${option.unitCost} ${option.unit ?: ""} / 个", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text("  最多 ${option.maxForgeable}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                            }
                            if (!option.available) {
                                Text(option.reason ?: "当前不可合成", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                            }
                        }
                        ForgeStepper(
                            enabled = option.available && option.maxForgeable > 0,
                            onForge = { count -> viewModel.forge(option.key ?: "", count) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ForgeStepper(enabled: Boolean, onForge: (Long) -> Unit) {
    var count by rememberSaveable { mutableStateOf(1L) }
    Column(horizontalAlignment = Alignment.End) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { if (count > 1) count-- }, enabled = enabled) {
                Icon(Icons.Outlined.Remove, "减少", tint = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline)
            }
            Text("$count", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            IconButton(onClick = { count++ }, enabled = enabled) {
                Icon(Icons.Outlined.Add, "增加", tint = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline)
            }
        }
        OutlinedButton(
            onClick = { onForge(count) },
            enabled = enabled
        ) {
            Text("合成", color = if (enabled) GrassGreen else MaterialTheme.colorScheme.outline)
        }
    }
}
