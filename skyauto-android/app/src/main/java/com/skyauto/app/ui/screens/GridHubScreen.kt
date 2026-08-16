package com.skyauto.app.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skyauto.app.ui.navigation.AppMenu
import com.skyauto.app.ui.navigation.MenuLeaf
import com.skyauto.app.ui.navigation.Routes
import com.skyauto.app.ui.theme.FrostWhite

/**
 * 普通横向平铺桌面。
 *
 * - 所有功能单行横向平铺，可左右滑动，非卡片样式（图标 + 文字）；
 * - 点击任意项以 100% 覆盖层展开对应功能，返回键或右上角关闭按钮退出，
 *   切换其他项时自动关闭。
 */
@Composable
fun GridHubScreen(
    onRequestAuth: () -> Unit = {}
) {
    val leaves = remember { AppMenu.leaves() }
    val listState = rememberLazyListState()

    var expandedRoute by remember { mutableStateOf<String?>(null) }

    Box(
        Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(FrostWhite, Color(0xFFEDF1F8))))
    ) {
        LazyRow(
            state = listState,
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(leaves) { index, leaf ->
                RowItem(
                    leaf = leaf,
                    colorIndex = index,
                    onClick = { expandedRoute = leaf.route }
                )
            }
        }
    }

    // ---- 功能展开层（100% 覆盖，返回键/关闭按钮退出） ----
    BackHandler(enabled = expandedRoute != null) { expandedRoute = null }

    AnimatedVisibility(
        visible = expandedRoute != null,
        enter = scaleIn(initialScale = 0.6f, animationSpec = tween(340, easing = FastOutSlowInEasing)) + fadeIn(tween(340)),
        exit = scaleOut(targetScale = 0.6f, animationSpec = tween(280)) + fadeOut(tween(280)),
        modifier = Modifier.fillMaxSize()
    ) {
        val route = expandedRoute ?: return@AnimatedVisibility
        ExpandedFeature(
            route = route,
            onClose = { expandedRoute = null },
            onRequestAuth = onRequestAuth
        )
    }
}

/** 配色：图标强调色 + 图标色（按索引循环）。 */
private data class ItemStyle(val accent: Color, val icon: Color)

private val ItemStyles = listOf(
    ItemStyle(Color(0xFF6C8CFF), Color(0xFF4A63D6)), // 蓝
    ItemStyle(Color(0xFF7CCB9A), Color(0xFF3E9668)), // 绿
    ItemStyle(Color(0xFFFFC46B), Color(0xFFE09A2E)), // 橙
    ItemStyle(Color(0xFF9A8CFF), Color(0xFF6C5CE0)), // 紫
    ItemStyle(Color(0xFFFF7B7B), Color(0xFFE04F5F)), // 红
    ItemStyle(Color(0xFF7AD4E8), Color(0xFF2FA8C0)), // 青
    ItemStyle(Color(0xFF9CCC65), Color(0xFF6FA83B)), // 黄绿
    ItemStyle(Color(0xFFFFD54F), Color(0xFFE0A800)), // 黄
    ItemStyle(Color(0xFFB39DDB), Color(0xFF7E57C2)), // 深紫
)

/** 横向平铺项：图标底座 + 文字，无整卡背景。 */
@Composable
private fun RowItem(
    leaf: MenuLeaf,
    colorIndex: Int,
    onClick: () -> Unit
) {
    val style = ItemStyles[colorIndex % ItemStyles.size]
    Box(
        Modifier
            .width(92.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                Modifier
                    .size(46.dp)
                    .background(style.accent.copy(alpha = 0.15f), RoundedCornerShape(15.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(leaf.icon, contentDescription = null, tint = style.icon, modifier = Modifier.size(26.dp))
            }
            Spacer(Modifier.height(8.dp))
            Text(
                leaf.label,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = style.icon,
                maxLines = 1
            )
        }
    }
}

/** 展开层：以 100% 覆盖层展开对应功能。 */
@Composable
private fun ExpandedFeature(
    route: String,
    onClose: () -> Unit,
    onRequestAuth: () -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.35f))
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            tonalElevation = 6.dp,
            modifier = Modifier.fillMaxSize()
        ) {
            BackActionScope(onBack = onClose) {
                when (route) {
                    Routes.DASHBOARD -> DashboardScreen()
                    Routes.HEIGHT -> HeightScreen()
                    Routes.RANKING -> RankingScreen()
                    Routes.WORLD_QUESTS -> WorldQuestsScreen()
                    Routes.GAME_INSIGHTS -> GameInsightsScreen()
                    Routes.ECONOMY -> EconomyScreen()
                    Routes.FORGE -> ForgeScreen()
                    Routes.ACCOUNTS -> AccountsScreen()
                    Routes.DEVICES -> DevicesScreen()
                    Routes.CONFIG_RULES -> ConfigRulesScreen()
                    Routes.FRIENDS -> FriendsScreen()
                    Routes.FRIEND_DETAIL -> FriendDetailScreen()
                    Routes.FRIEND_CODES -> FriendCodeScreen()
                    Routes.SPIRITS -> SpiritsScreen()
                    Routes.TASKS -> TasksScreen()
                    Routes.TASK_HISTORY -> TaskHistoryScreen()
                    Routes.DONE_TODAY -> DoneTodayScreen()
                    Routes.AI -> AiScreen()
                    Routes.CHAT -> ChatScreen()
                    Routes.NOTIFICATIONS -> NotificationsScreen()
                    Routes.ANNOUNCEMENTS -> AnnouncementsScreen()
                    Routes.INVITATIONS -> InvitationsScreen()
                    Routes.OPERATIONS -> OperationsScreen()
                    Routes.ORDERS -> OrdersScreen()
                    Routes.FEEDBACK -> FeedbackScreen()
                    Routes.WECHAT -> WechatBindingScreen()
                    Routes.SETTINGS -> SettingsScreen(onLogout = { onRequestAuth() })
                    else -> Text("功能开发中", modifier = Modifier.padding(24.dp))
                }
            }
        }

        // 右上角显式关闭按钮
        Surface(
            onClick = onClose,
            shape = CircleShape,
            color = Color.White.copy(alpha = 0.85f),
            shadowElevation = 4.dp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp)
                .size(40.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    Icons.Rounded.Close,
                    contentDescription = "关闭",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}