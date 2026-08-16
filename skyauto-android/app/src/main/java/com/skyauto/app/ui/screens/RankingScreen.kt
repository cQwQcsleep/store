package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.hilt.navigation.compose.hiltViewModel
import com.skyauto.app.ui.components.EmptyState
import com.skyauto.app.ui.components.GlassCard
import com.skyauto.app.ui.components.GradientHero
import com.skyauto.app.ui.components.HyperLoader
import com.skyauto.app.ui.theme.SunOrange
import com.skyauto.app.ui.viewmodel.HeightViewModel

@Composable
fun RankingScreen(viewModel: HeightViewModel = hiltViewModel()) {
    val ranking by viewModel.ranking.collectAsState()
    val loading by viewModel.loading.collectAsState()

    AppScreen(title = "身高排行榜") {
        GradientHero(
            title = "身高排行",
            subtitle = "社区玩家身高实时排名",
            colors = listOf(SunOrange, com.skyauto.app.ui.theme.HyperLavender)
        )

        HyperLoader(loading)

        if (ranking.isEmpty() && !loading) {
            EmptyState("暂无排行数据")
        } else {
            ranking.forEachIndexed { index, entry ->
                Box(Modifier.fillMaxWidth()) {
                    GlassCard {
                        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(
                                        when (index) {
                                            0 -> Color(0xFFFFD700)
                                            1 -> Color(0xFFC0C0C0)
                                            2 -> Color(0xFFCD7F32)
                                            else -> MaterialTheme.colorScheme.surfaceVariant
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "${index + 1}",
                                    color = if (index < 3) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(Modifier.size(14.dp))
                            Column(Modifier.weight(1f)) {
                                Text(entry.heightLabel ?: "身高 #${(entry.heightVal ?: 0).toLong()}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                                Text("身高级别：${entry.heightLabel ?: "-"} · 比例 ${entry.scale ?: 0.0}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Text(
                                entry.heightValue?.let { "%.2f".format(it) } ?: "--",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}