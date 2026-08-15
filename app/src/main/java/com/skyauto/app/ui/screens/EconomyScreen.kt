package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skyauto.app.ui.components.EmptyState
import com.skyauto.app.ui.components.GlassCard
import com.skyauto.app.ui.components.GradientHero
import com.skyauto.app.ui.components.HyperLoader
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.WarmAmber
import com.skyauto.app.ui.viewmodel.EconomyViewModel

@Composable
fun EconomyScreen(viewModel: EconomyViewModel = hiltViewModel()) {
    val currency by viewModel.currency.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "货币钱包") {
        GradientHero(
            title = "资源经济",
            subtitle = "蜡烛 · 爱心 · 季节蜡烛实时余额",
            colors = listOf(WarmAmber, HyperBlue)
        )

        HyperLoader(loading)

        if (currency != null) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                BalanceCard("蜡烛", (currency?.candles ?: 0L).toString(), GrassGreen, Modifier.weight(1f))
                BalanceCard("爱心", (currency?.hearts ?: 0L).toString(), Color(0xFFFF9EC1), Modifier.weight(1f))
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                BalanceCard("季节蜡烛", (currency?.seasonCandles ?: 0L).toString(), com.skyauto.app.ui.theme.HyperCyan, Modifier.weight(1f))
                BalanceCard("染料", (currency?.dyes ?: 0L).toString(), com.skyauto.app.ui.theme.HyperLavender, Modifier.weight(1f))
            }
        } else if (!loading) {
            EmptyState("暂无货币数据")
        }

        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
        }

        Button(
            onClick = { viewModel.refresh() },
            modifier = Modifier.fillMaxWidth(),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GrassGreen)
        ) {
            Icon(Icons.Rounded.Refresh, null)
            Text("刷新货币")
        }
    }
}

@Composable
private fun BalanceCard(label: String, value: String, tint: Color, modifier: Modifier = Modifier) {
    GlassCard(modifier) {
        Text(label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = tint)
    }
}