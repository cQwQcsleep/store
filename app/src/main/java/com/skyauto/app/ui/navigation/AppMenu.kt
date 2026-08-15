package com.skyauto.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Accessibility
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.material.icons.outlined.Wallet
import androidx.compose.ui.graphics.vector.ImageVector

/** 路由常量 */
object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val RESET = "reset"
    const val DASHBOARD = "dashboard"
    const val HEIGHT = "height"
    const val RANKING = "ranking"
    const val ECONOMY = "economy"
    const val ACCOUNTS = "accounts"
    const val DEVICES = "devices"
    const val FRIENDS = "friends"
    const val SPIRITS = "spirits"
    const val TASKS = "tasks"
    const val AI = "ai"
    const val CHAT = "chat"
    const val NOTIFICATIONS = "notifications"
    const val SETTINGS = "settings"
}

/** 二级菜单项 */
data class MenuLeaf(
    val route: String,
    val label: String,
    val icon: ImageVector
)

/** 一级菜单分组（可展开/收起） */
data class MenuGroup(
    val key: String,
    val label: String,
    val icon: ImageVector,
    val children: List<MenuLeaf>
)

object AppMenu {

    fun groups(): List<MenuGroup> = listOf(
        MenuGroup(
            key = "home",
            label = "工作台",
            icon = Icons.Outlined.Dashboard,
            children = listOf(
                MenuLeaf(Routes.DASHBOARD, "工作台", Icons.Outlined.Dashboard)
            )
        ),
        MenuGroup(
            key = "sky",
            label = "光遇数据",
            icon = Icons.Outlined.Accessibility,
            children = listOf(
                MenuLeaf(Routes.HEIGHT, "身高查询", Icons.Outlined.Accessibility),
                MenuLeaf(Routes.RANKING, "身高排行榜", Icons.Outlined.FitnessCenter)
            )
        ),
        MenuGroup(
            key = "economy",
            label = "资源经济",
            icon = Icons.Outlined.Wallet,
            children = listOf(
                MenuLeaf(Routes.ECONOMY, "货币钱包", Icons.Outlined.Wallet)
            )
        ),
        MenuGroup(
            key = "device",
            label = "账号设备",
            icon = Icons.Outlined.PhoneAndroid,
            children = listOf(
                MenuLeaf(Routes.ACCOUNTS, "账号管理", Icons.Outlined.PhoneAndroid),
                MenuLeaf(Routes.DEVICES, "设备管理", Icons.Outlined.PhoneAndroid)
            )
        ),
        MenuGroup(
            key = "social",
            label = "社交",
            icon = Icons.Outlined.People,
            children = listOf(
                MenuLeaf(Routes.FRIENDS, "好友", Icons.Outlined.People),
                MenuLeaf(Routes.SPIRITS, "灵犀 · 心火", Icons.Outlined.Favorite),
                MenuLeaf(Routes.CHAT, "聊天室", Icons.Outlined.Chat)
            )
        ),
        MenuGroup(
            key = "auto",
            label = "自动化",
            icon = Icons.Outlined.TaskAlt,
            children = listOf(
                MenuLeaf(Routes.TASKS, "任务计划", Icons.Outlined.TaskAlt)
            )
        ),
        MenuGroup(
            key = "ai",
            label = "AI 助手",
            icon = Icons.Outlined.AutoAwesome,
            children = listOf(
                MenuLeaf(Routes.AI, "AI 助手", Icons.Outlined.AutoAwesome)
            )
        ),
        MenuGroup(
            key = "system",
            label = "消息与设置",
            icon = Icons.Outlined.Pets,
            children = listOf(
                MenuLeaf(Routes.NOTIFICATIONS, "通知中心", Icons.Outlined.Notifications),
                MenuLeaf(Routes.SETTINGS, "设置", Icons.Outlined.Settings)
            )
        )
    )

    /** 根据路由找标题 */
    fun titleFor(route: String): String {
        groups().forEach { g ->
            g.children.forEach { if (it.route == route) return it.label }
        }
        return "光遇助手"
    }

    fun iconFor(route: String): ImageVector {
        groups().forEach { g ->
            g.children.forEach { if (it.route == route) return it.icon }
        }
        return Icons.Outlined.Dashboard
    }
}