package com.skyauto.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Accessibility
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Diversity2
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.outlined.PrecisionManufacturing
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Rule
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.material.icons.outlined.Wallet
import androidx.compose.material.icons.outlined.QrCode
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.ui.graphics.vector.ImageVector

/** 路由常量 */
object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val RESET = "reset"
    const val HUB = "hub"
    const val DASHBOARD = "dashboard"
    const val HEIGHT = "height"
    const val RANKING = "ranking"
    const val ECONOMY = "economy"
    const val ACCOUNTS = "accounts"
    const val DEVICES = "devices"
    const val FRIENDS = "friends"
    const val FRIEND_DETAIL = "friend_detail"
    const val SPIRITS = "spirits"
    const val TASKS = "tasks"
    const val TASK_HISTORY = "task_history"
    const val AI = "ai"
    const val CHAT = "chat"
    const val NOTIFICATIONS = "notifications"
    const val SETTINGS = "settings"
    const val ANNOUNCEMENTS = "announcements"
    const val INVITATIONS = "invitations"
    const val WORLD_QUESTS = "world_quests"
    const val OPERATIONS = "operations"
    const val GAME_INSIGHTS = "game_insights"
    const val FORGE = "forge"
    const val CONFIG_RULES = "config_rules"
    const val ORDERS = "orders"
    const val FEEDBACK = "feedback"
    const val WECHAT = "wechat"
    const val DONE_TODAY = "done_today"
    const val FRIEND_CODES = "friend_codes"
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
                MenuLeaf(Routes.RANKING, "身高排行榜", Icons.Outlined.FitnessCenter),
                MenuLeaf(Routes.WORLD_QUESTS, "世界任务", Icons.Outlined.Public),
                MenuLeaf(Routes.GAME_INSIGHTS, "游戏情报", Icons.Outlined.Insights)
            )
        ),
        MenuGroup(
            key = "economy",
            label = "资源经济",
            icon = Icons.Outlined.Wallet,
            children = listOf(
                MenuLeaf(Routes.ECONOMY, "货币钱包", Icons.Outlined.Wallet),
                MenuLeaf(Routes.FORGE, "货币合成", Icons.Outlined.PrecisionManufacturing),
                MenuLeaf(Routes.ORDERS, "订单管理", Icons.Outlined.ReceiptLong)
            )
        ),
        MenuGroup(
            key = "device",
            label = "账号设备",
            icon = Icons.Outlined.PhoneAndroid,
            children = listOf(
                MenuLeaf(Routes.ACCOUNTS, "账号管理", Icons.Outlined.PhoneAndroid),
                MenuLeaf(Routes.DEVICES, "设备管理", Icons.Outlined.PhoneAndroid),
                MenuLeaf(Routes.CONFIG_RULES, "配置规则", Icons.Outlined.Rule)
            )
        ),
        MenuGroup(
            key = "social",
            label = "社交",
            icon = Icons.Outlined.People,
            children = listOf(
                MenuLeaf(Routes.FRIENDS, "好友", Icons.Outlined.People),
                MenuLeaf(Routes.FRIEND_DETAIL, "好友深度", Icons.Outlined.Diversity2),
                MenuLeaf(Routes.FRIEND_CODES, "好友码", Icons.Outlined.QrCode),
                MenuLeaf(Routes.SPIRITS, "灵犀 · 心火", Icons.Outlined.Favorite),
                MenuLeaf(Routes.CHAT, "聊天室", Icons.Outlined.Chat)
            )
        ),
        MenuGroup(
            key = "auto",
            label = "自动化",
            icon = Icons.Outlined.TaskAlt,
            children = listOf(
                MenuLeaf(Routes.TASKS, "任务计划", Icons.Outlined.TaskAlt),
                MenuLeaf(Routes.TASK_HISTORY, "任务执行详情", Icons.Outlined.History),
                MenuLeaf(Routes.DONE_TODAY, "今日已完成", Icons.Outlined.CheckCircle)
            )
        ),
        MenuGroup(
            key = "ops",
            label = "公告运营",
            icon = Icons.Outlined.Campaign,
            children = listOf(
                MenuLeaf(Routes.ANNOUNCEMENTS, "系统公告", Icons.Outlined.Campaign),
                MenuLeaf(Routes.INVITATIONS, "邀请有礼", Icons.Outlined.PersonAdd),
                MenuLeaf(Routes.OPERATIONS, "运营数据", Icons.Outlined.BarChart)
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
                MenuLeaf(Routes.FEEDBACK, "反馈工单", Icons.Outlined.SupportAgent),
                MenuLeaf(Routes.WECHAT, "微信绑定", Icons.Outlined.ChatBubble),
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

    /** 展平所有一级功能为顺序列表（2D 网格按此顺序平铺）。 */
    fun leaves(): List<MenuLeaf> = groups().flatMap { it.children }

    fun iconFor(route: String): ImageVector {
        groups().forEach { g ->
            g.children.forEach { if (it.route == route) return it.icon }
        }
        return Icons.Outlined.Dashboard
    }
}