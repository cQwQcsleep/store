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
import com.skyauto.app.ui.components.AccountPicker
import com.skyauto.app.ui.components.EmptyState
import com.skyauto.app.ui.components.GlassCard
import com.skyauto.app.ui.components.GradientHero
import com.skyauto.app.ui.components.HyperLoader
import com.skyauto.app.ui.components.SectionTitle
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperCyan
import com.skyauto.app.ui.theme.SunOrange
import com.skyauto.app.ui.viewmodel.DoneTodayViewModel

/** 今日已完成各项任务（按账号） */
private val DONE_TODAY_ITEMS: List<Pair<String, (com.skyauto.app.data.model.DoneTodayResponse) -> Boolean>> = listOf(
    "例行任务" to { it.dailyDone },
    "染料" to { it.dyeDone },
    "大蜡" to { it.fireDone },
    "心火" to { it.heartDone },
    "收心火" to { it.receiveFireDone },
    "跑图" to { it.runtaskDone },
    "献祭" to { it.sacrificeDone },
    "季卡" to { it.seasonPassDone },
    "季节蜡" to { it.seasonalWaxDone },
    "阳光照 2026" to { it.sunlightPhoto2026Done },
    "翅膀" to { it.wingsDone },
    "世界任务" to { it.worldDone }
)

@Composable
fun DoneTodayScreen(viewModel: DoneTodayViewModel = hiltViewModel()) {
    val accounts by viewModel.accounts.collectAsState()
    val selectedId by viewModel.selectedAccountId.collectAsState()
    val done by viewModel.done.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "今日已完成") {
        GradientHero(
            title = "今日已完成",
            subtitle = "各账号今日任务完成情况",
            colors = listOf(HyperCyan, GrassGreen)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }

        AccountPicker(accounts, selectedId, viewModel::loadAccount)

        if (done == null && !loading) {
            EmptyState("暂无数据")
            return@AppScreen
        }

        done?.let { d ->
            SectionTitle("任务完成度")
            DONE_TODAY_ITEMS.forEach { (label, check) ->
                val finish = check(d)
                GlassCard {
                    Row(Modifier.fillMaxWidth()) {
                        Text(label, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
                        Text(
                            if (finish) "已完成" else "未完成",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = if (finish) GrassGreen else SunOrange
                        )
                    }
                }
            }
        }
    }
}