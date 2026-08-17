package com.miide.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.miide.ui.ai.AiSettingsScreen
import com.miide.ui.ai.ProviderEditScreen
import com.miide.ui.chat.ChatScreen
import com.miide.ui.editor.EditorScreen
import com.miide.ui.home.HomeScreen
import com.miide.ui.mcp.McpEditScreen
import com.miide.ui.mcp.McpSettingsScreen
import com.miide.terminal.TerminalScreen

/** 路由定义。 */
object Routes {
    const val HOME = "home"
    const val AI_SETTINGS = "ai_settings"
    const val PROVIDER_EDIT = "provider_edit"
    const val EDITOR = "editor"
    const val CHAT = "chat"
    const val MCP_SETTINGS = "mcp_settings"
    const val MCP_EDIT = "mcp_edit"
    const val TERMINAL = "terminal"
}

@Composable
fun MiNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(
                onOpenNewFile = {
                    navController.navigate("${Routes.EDITOR}?uri=&name=untitled.txt")
                },
                onOpenFile = { uri, name ->
                    navController.navigate("${Routes.EDITOR}?uri=${android.net.Uri.encode(uri)}&name=${android.net.Uri.encode(name)}")
                },
                onOpenAiSettings = { navController.navigate(Routes.AI_SETTINGS) },
                onOpenChat = { navController.navigate(Routes.CHAT) }
            )
        }

        composable(Routes.CHAT) {
            ChatScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.AI_SETTINGS) {
            AiSettingsScreen(
                onBack = { navController.popBackStack() },
                onAddProvider = { navController.navigate(Routes.PROVIDER_EDIT) },
                onEditProvider = { id -> navController.navigate("${Routes.PROVIDER_EDIT}?providerId=$id") },
                onOpenMcp = { navController.navigate(Routes.MCP_SETTINGS) }
            )
        }

        composable(Routes.MCP_SETTINGS) {
            McpSettingsScreen(
                onBack = { navController.popBackStack() },
                onAddServer = { navController.navigate(Routes.MCP_EDIT) },
                onEditServer = { id -> navController.navigate("${Routes.MCP_EDIT}?serverId=$id") }
            )
        }

        composable(
            route = "${Routes.MCP_EDIT}?serverId={serverId}",
            arguments = listOf(
                navArgument("serverId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val serverId = backStackEntry.arguments?.getString("serverId")
            McpEditScreen(
                serverId = serverId,
                onBack = { navController.popBackStack() },
                onSaved = { navController.popBackStack() }
            )
        }

        composable(
            route = "${Routes.PROVIDER_EDIT}?providerId={providerId}",
            arguments = listOf(
                navArgument("providerId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val providerId = backStackEntry.arguments?.getString("providerId")
            ProviderEditScreen(
                providerId = providerId,
                onBack = { navController.popBackStack() },
                onSaved = { navController.popBackStack() }
            )
        }

        composable(Routes.TERMINAL) {
            TerminalScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "${Routes.EDITOR}?uri={uri}&name={name}",
            arguments = listOf(
                navArgument("uri") { type = NavType.StringType; defaultValue = "" },
                navArgument("name") { type = NavType.StringType; defaultValue = "untitled.txt" }
            )
        ) { backStackEntry ->
            val uri = backStackEntry.arguments?.getString("uri").orEmpty()
            val name = backStackEntry.arguments?.getString("name").orEmpty()
            EditorScreen(
                fileUri = uri,
                initialName = name,
                onBack = { navController.popBackStack() },
                onOpenChat = { navController.navigate(Routes.CHAT) },
                onOpenTerminal = { navController.navigate(Routes.TERMINAL) }
            )
        }
    }
}
