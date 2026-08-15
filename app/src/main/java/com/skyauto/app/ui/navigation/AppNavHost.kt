package com.skyauto.app.ui.navigation

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
import com.skyauto.app.ui.screens.ChatScreen
import com.skyauto.app.ui.screens.DashboardScreen
import com.skyauto.app.ui.screens.DevicesScreen
import com.skyauto.app.ui.screens.EconomyScreen
import com.skyauto.app.ui.screens.FriendsScreen
import com.skyauto.app.ui.screens.HeightScreen
import com.skyauto.app.ui.screens.NotificationsScreen
import com.skyauto.app.ui.screens.RankingScreen
import com.skyauto.app.ui.screens.SettingsScreen
import com.skyauto.app.ui.screens.SpiritsScreen
import com.skyauto.app.ui.screens.TasksScreen

@Composable
fun AppNavHost(
    onRequestAuth: () -> Unit
) {
    val navController = rememberNavController()

    // 全局滑动菜单 + 应用壳
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: Routes.DASHBOARD

    GlobalDrawerLayout(
        currentRoute = currentRoute,
        onNavigate = { route ->
            navController.navigate(route) {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
    ) {
        Scaffold(
            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.background,
            contentWindowInsets = androidx.compose.foundation.layout.WindowInsets(0, 0, 0, 0)
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Routes.DASHBOARD,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Routes.DASHBOARD) { DashboardScreen() }
                composable(Routes.HEIGHT) { HeightScreen() }
                composable(Routes.RANKING) { RankingScreen() }
                composable(Routes.ECONOMY) { EconomyScreen() }
                composable(Routes.ACCOUNTS) { AccountsScreen() }
                composable(Routes.DEVICES) { DevicesScreen() }
                composable(Routes.FRIENDS) { FriendsScreen() }
                composable(Routes.SPIRITS) { SpiritsScreen() }
                composable(Routes.TASKS) { TasksScreen() }
                composable(Routes.AI) { AiScreen() }
                composable(Routes.CHAT) { ChatScreen() }
                composable(Routes.NOTIFICATIONS) { NotificationsScreen() }
                composable(Routes.SETTINGS) {
                    SettingsScreen(onLogout = { onRequestAuth() })
                }
            }
        }
    }
}