package com.skyauto.app.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.skyauto.app.ui.screens.AccountsScreen
import com.skyauto.app.ui.screens.AiScreen
import com.skyauto.app.ui.screens.AnnouncementsScreen
import com.skyauto.app.ui.screens.BackActionScope
import com.skyauto.app.ui.screens.ChatScreen
import com.skyauto.app.ui.screens.ConfigRulesScreen
import com.skyauto.app.ui.screens.DashboardScreen
import com.skyauto.app.ui.screens.DevicesScreen
import com.skyauto.app.ui.screens.EconomyScreen
import com.skyauto.app.ui.screens.ForgeScreen
import com.skyauto.app.ui.screens.FriendDetailScreen
import com.skyauto.app.ui.screens.FriendsScreen
import com.skyauto.app.ui.screens.GameInsightsScreen
import com.skyauto.app.ui.screens.GridHubScreen
import com.skyauto.app.ui.screens.HeightScreen
import com.skyauto.app.ui.screens.InvitationsScreen
import com.skyauto.app.ui.screens.NotificationsScreen
import com.skyauto.app.ui.screens.OperationsScreen
import com.skyauto.app.ui.screens.RankingScreen
import com.skyauto.app.ui.screens.SettingsScreen
import com.skyauto.app.ui.screens.SpiritsScreen
import com.skyauto.app.ui.screens.TaskHistoryScreen
import com.skyauto.app.ui.screens.TasksScreen
import com.skyauto.app.ui.screens.WorldQuestsScreen

/**
 * 十字 UI 导航宿主。
 * HubScreen 为登录后起点，横向分页主导航；详情页通过返回按钮/手势回 Hub。
 */
@Composable
fun AppNavHost(
    onRequestAuth: () -> Unit
) {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: Routes.HUB

    Scaffold(
        containerColor = androidx.compose.material3.MaterialTheme.colorScheme.background,
        contentWindowInsets = androidx.compose.foundation.layout.WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HUB,
            modifier = Modifier.padding(innerPadding),
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it / 3 }, animationSpec = tween(400)) + fadeIn(tween(400))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it / 4 }, animationSpec = tween(400)) + fadeOut(tween(400))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -it / 4 }, animationSpec = tween(400)) + fadeIn(tween(400))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it / 3 }, animationSpec = tween(400)) + fadeOut(tween(400))
            }
        ) {
            composable(Routes.HUB) {
                GridHubScreen(onRequestAuth = onRequestAuth)
            }
            composable(Routes.DASHBOARD) {
                Detail(route = currentRoute, navController = navController) { DashboardScreen() }
            }
            composable(Routes.HEIGHT) {
                Detail(route = currentRoute, navController = navController) { HeightScreen() }
            }
            composable(Routes.RANKING) {
                Detail(route = currentRoute, navController = navController) { RankingScreen() }
            }
            composable(Routes.WORLD_QUESTS) {
                Detail(route = currentRoute, navController = navController) { WorldQuestsScreen() }
            }
            composable(Routes.GAME_INSIGHTS) {
                Detail(route = currentRoute, navController = navController) { GameInsightsScreen() }
            }
            composable(Routes.ECONOMY) {
                Detail(route = currentRoute, navController = navController) { EconomyScreen() }
            }
            composable(Routes.FORGE) {
                Detail(route = currentRoute, navController = navController) { ForgeScreen() }
            }
            composable(Routes.ACCOUNTS) {
                Detail(route = currentRoute, navController = navController) { AccountsScreen() }
            }
            composable(Routes.DEVICES) {
                Detail(route = currentRoute, navController = navController) { DevicesScreen() }
            }
            composable(Routes.CONFIG_RULES) {
                Detail(route = currentRoute, navController = navController) { ConfigRulesScreen() }
            }
            composable(Routes.FRIENDS) {
                Detail(route = currentRoute, navController = navController) { FriendsScreen() }
            }
            composable(Routes.FRIEND_DETAIL) {
                Detail(route = currentRoute, navController = navController) { FriendDetailScreen() }
            }
            composable(Routes.SPIRITS) {
                Detail(route = currentRoute, navController = navController) { SpiritsScreen() }
            }
            composable(Routes.TASKS) {
                Detail(route = currentRoute, navController = navController) { TasksScreen() }
            }
            composable(Routes.TASK_HISTORY) {
                Detail(route = currentRoute, navController = navController) { TaskHistoryScreen() }
            }
            composable(Routes.AI) {
                Detail(route = currentRoute, navController = navController) { AiScreen() }
            }
            composable(Routes.CHAT) {
                Detail(route = currentRoute, navController = navController) { ChatScreen() }
            }
            composable(Routes.NOTIFICATIONS) {
                Detail(route = currentRoute, navController = navController) { NotificationsScreen() }
            }
            composable(Routes.ANNOUNCEMENTS) {
                Detail(route = currentRoute, navController = navController) { AnnouncementsScreen() }
            }
            composable(Routes.INVITATIONS) {
                Detail(route = currentRoute, navController = navController) { InvitationsScreen() }
            }
            composable(Routes.OPERATIONS) {
                Detail(route = currentRoute, navController = navController) { OperationsScreen() }
            }
            composable(Routes.SETTINGS) {
                Detail(route = currentRoute, navController = navController) {
                    SettingsScreen(onLogout = { onRequestAuth() })
                }
            }
        }
    }
}

/** 详情页包装：提供返回动作（返回按钮 + 系统返回手势）。 */
@Composable
private fun Detail(
    route: String,
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    BackActionScope(
        onBack = { navController.popBackStack() }
    ) {
        content()
    }
}