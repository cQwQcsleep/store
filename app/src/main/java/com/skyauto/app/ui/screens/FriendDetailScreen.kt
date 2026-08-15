package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
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
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.theme.RosePink
import com.skyauto.app.ui.theme.WarmAmber
import com.skyauto.app.ui.viewmodel.FriendDetailViewModel

@Composable
fun FriendDetailScreen(viewModel: FriendDetailViewModel = hiltViewModel()) {
    val accounts by viewModel.accounts.collectAsState()
    val selectedId by viewModel.selectedAccountId.collectAsState()
    val relations by viewModel.relations.collectAsState()
    val heartFriends by viewModel.heartFriends.collectAsState()
    val friendCodes by viewModel.friendCodes.collectAsState()
    val spiritOptions by viewModel.spiritOptions.collectAsState()
    val spiritNotice by viewModel.spiritNotice.collectAsState()
    val abilityNodes by viewModel.abilityNodes.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "好友深度") {
        GradientHero(
            title = "好友深度",
            subtitle = "关系 · 送心 · 灵犀 · 能力",
            colors = listOf(HyperLavender, HyperCyan)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }

        AccountPicker(accounts, selectedId, viewModel::loadAccount)

        // 好友关系
        SectionTitle("好友关系")
        if (relations.isEmpty() && !loading) {
            EmptyState("暂无好友关系")
        } else {
            relations.forEach { r ->
                GlassCard {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            r.nickname ?: r.raw?.nickname ?: "好友 ${r.idx + 1}",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f)
                        )
                        r.raw?.level?.let {
                            Text("Lv.$it", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                    r.raw?.playerBadgeType?.let {
                        Text("徽章：$it", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    r.raw?.hints?.takeIf { it.isNotBlank() }?.let {
                        Text("提示：$it", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }

        // 送心好友
        val savedHeartIds = heartFriends?.savedFriendIds ?: emptyList()
        val heartList = heartFriends?.friends ?: emptyList()
        if (heartList.isNotEmpty() || savedHeartIds.isNotEmpty()) {
            SectionTitle("送心好友（${savedHeartIds.size} 已保存）")
            heartList.take(20).forEach { h ->
                GlassCard {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            h.nickname ?: h.friendId ?: "好友",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            if (h.selected) Icons.Outlined.Star else Icons.Outlined.Star,
                            null,
                            tint = if (h.selected) WarmAmber else MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }
        }

        // 好友码
        if (friendCodes.isNotEmpty()) {
            SectionTitle("好友码邀请")
            friendCodes.forEach { f ->
                GlassCard {
                    Row(Modifier.fillMaxWidth()) {
                        Text(f.code ?: "-", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                        Text(f.status ?: "-", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    f.note?.let {
                        Text("备注：$it", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }

        // 灵犀亲密
        if (spiritOptions.isNotEmpty()) {
            SectionTitle("灵犀亲密")
            spiritNotice?.let {
                Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            spiritOptions.take(12).forEach { s ->
                GlassCard {
                    Row(Modifier.fillMaxWidth()) {
                        Text(s.name ?: s.key ?: "灵犀", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
                        Text("${s.value}", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }

        // 好友能力
        if (abilityNodes.isNotEmpty()) {
            SectionTitle("好友能力节点")
            abilityNodes.forEach { n ->
                GlassCard {
                    Row(Modifier.fillMaxWidth()) {
                        Column(Modifier.weight(1f)) {
                            Text(n.name ?: "能力", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                            Text("花费：${n.cost}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Row {
                            Icon(
                                if (n.unlocked) Icons.Outlined.LockOpen else Icons.Outlined.Lock,
                                null,
                                tint = if (n.unlocked) GrassGreen else MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                }
            }
        }
    }
}
