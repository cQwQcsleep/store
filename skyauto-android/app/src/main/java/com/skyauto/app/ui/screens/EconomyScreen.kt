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
import com.skyauto.app.data.model.CurrencyInfo
import com.skyauto.app.ui.components.EmptyState
import com.skyauto.app.ui.components.GlassCard
import com.skyauto.app.ui.components.GradientHero
import com.skyauto.app.ui.components.HyperLoader
import com.skyauto.app.ui.components.SectionTitle
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.WarmAmber
import com.skyauto.app.ui.viewmodel.EconomyViewModel

private val DYE_LABELS = listOf(
    "白" to "colorWhite", "黑" to "colorBlack", "红" to "colorRed",
    "绿" to "colorGreen", "蓝" to "colorBlue", "黄" to "colorYellow",
    "青" to "colorCyan", "紫" to "colorMagenta"
)

@Composable
fun EconomyScreen(viewModel: EconomyViewModel = hiltViewModel()) {
    val currency by viewModel.currency.collectAsState()
    val accountName by viewModel.accountName.collectAsState()
    val hasAccount by viewModel.hasAccount.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "货币钱包") {
        GradientHero(
            title = "资源经济",
            subtitle = accountName?.let { "账号：$it · 蜡烛/爱心/季节蜡烛实时余额" } ?: "蜡烛 · 爱心 · 季节蜡烛实时余额",
            colors = listOf(WarmAmber, HyperBlue)
        )

        HyperLoader(loading)

        if (hasAccount && currency != null) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                BalanceCard("蜡烛", (currency?.candles ?: 0L).toString(), GrassGreen, Modifier.weight(1f))
                BalanceCard("爱心", (currency?.hearts ?: 0L).toString(), Color(0xFFFF9EC1), Modifier.weight(1f))
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                BalanceCard("季节蜡烛", (currency?.seasonCandle ?: 0L).toString(), com.skyauto.app.ui.theme.HyperCyan, Modifier.weight(1f))
                BalanceCard("升华蜡烛", (currency?.ascendedCandles ?: 0L).toString(), com.skyauto.app.ui.theme.HyperLavender, Modifier.weight(1f))
            }

            SectionTitle("资源详情")
            GlassCard {
                ResourceRow("蜡火", currency?.wax ?: 0L)
                ResourceRow("季节爱心", currency?.seasonHeart ?: 0L)
                ResourceRow("献祭重塑", currency?.prestige ?: 0L)
                ResourceRow("VIP 积分", currency?.vip ?: 0L)
                ResourceRow("可锻造蜡烛", currency?.candleForgeable ?: 0L)
                currency?.candleEquivalent?.let {
                    Text(
                        "蜡烛等价：${"%.2f".format(it)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            SectionTitle("染料库存（共 ${currency?.totalDyes ?: 0}）")
            GlassCard {
                DYE_LABELS.forEach { (label, prop) ->
                    ResourceRow("$label 色染料", dyeValue(currency, prop))
                }
            }
        } else if (!loading) {
            EmptyState(if (hasAccount) "暂无货币数据，请点击刷新" else "暂无已绑定账号，请先在「账号管理」添加账号")
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

private fun dyeValue(currency: CurrencyInfo?, prop: String): Long = when (prop) {
    "colorWhite" -> currency?.colorWhite
    "colorBlack" -> currency?.colorBlack
    "colorRed" -> currency?.colorRed
    "colorGreen" -> currency?.colorGreen
    "colorBlue" -> currency?.colorBlue
    "colorYellow" -> currency?.colorYellow
    "colorCyan" -> currency?.colorCyan
    "colorMagenta" -> currency?.colorMagenta
    else -> null
} ?: 0L

@Composable
private fun ResourceRow(label: String, value: Long) {
    Row(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(label, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
        Text("$value", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun BalanceCard(label: String, value: String, tint: Color, modifier: Modifier = Modifier) {
    GlassCard(modifier) {
        Text(label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = tint)
    }
}