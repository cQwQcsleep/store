package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.viewmodel.FriendsViewModel

@Composable
fun FriendsScreen(viewModel: FriendsViewModel = hiltViewModel()) {
    val friends by viewModel.friends.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    AppScreen(title = "好友") {
        GradientHero(
            title = "好友管理",
            subtitle = "好友关系一览",
            colors = listOf(HyperLavender, HyperBlue)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }

        SectionTitle("好友列表")
        if (friends.isEmpty() && !loading) {
            EmptyState("暂无好友")
        } else {
            friends.forEach { friend ->
                GlassCard {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(friend.nickname ?: friend.uuid ?: "好友", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
                        friend.height?.let { Text("%.2f".format(it) + " cm", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary) }
                    }
                    friend.note?.let {
                        Text("备注：$it", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
    }
}