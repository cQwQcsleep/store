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
import com.miide.ui.editor.EditorScreen
import com.miide.ui.home.HomeScreen

/** 路由定义。 */
object Routes {
    const val HOME = "home"
    const val AI_SETTINGS = "ai_settings"
    const val PROVIDER_EDIT = "provider_edit"
    const val EDITOR = "editor"
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
                onOpenAiSettings = { navController.navigate(Routes.AI_SETTINGS) }
            )
        }

        composable(Routes.AI_SETTINGS) {
            AiSettingsScreen(
                onBack = { navController.popBackStack() },
                onAddProvider = { navController.navigate(Routes.PROVIDER_EDIT) },
                onEditProvider = { id -> navController.navigate("${Routes.PROVIDER_EDIT}?providerId=$id") }
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
                onBack = { navController.popBackStack() }
            )
        }
    }
}
