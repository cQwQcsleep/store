package com.skyauto.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    @SerialName("success") val success: Boolean = false,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: T? = null
)

// ------------------------------------------------------------------
// 认证
// ------------------------------------------------------------------
@Serializable
data class LoginRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String
)

/**
 * 认证类接口（me/login/register）返回的用户字段为 `user` 而非 `data`，
 * 因此需要单独的响应类型来解析。
 */
@Serializable
data class AuthUserResponse(
    @SerialName("success") val success: Boolean = false,
    @SerialName("message") val message: String? = null,
    @SerialName("user") val user: User? = null
)

@Serializable
data class RegisterRequest(
    @SerialName("email") val email: String,
    @SerialName("username") val username: String,
    @SerialName("password") val password: String,
    @SerialName("verifyCode") val verifyCode: String? = null,
    @SerialName("invite_code") val inviteCode: String? = null
)

@Serializable
data class SendResetCodeRequest(
    @SerialName("email") val email: String
)

@Serializable
data class ResetPasswordRequest(
    @SerialName("email") val email: String,
    @SerialName("code") val code: String,
    @SerialName("password") val password: String
)

@Serializable
data class User(
    @SerialName("id") val id: Long? = null,
    @SerialName("username") val username: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("role") val role: String? = null,
    @SerialName("nickname") val nickname: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("height_label") val heightLabel: String? = null,
    @SerialName("height_value") val heightValue: Double? = null,
    @SerialName("is_active") val isActive: Boolean = true,
    @SerialName("is_admin") val isAdmin: Boolean = false,
    @SerialName("max_accounts") val maxAccounts: Long? = null,
    @SerialName("max_android_devices") val maxAndroidDevices: Long? = null,
    @SerialName("max_ios_devices") val maxIosDevices: Long? = null,
    @SerialName("proxy_points") val proxyPoints: Long? = null,
    @SerialName("tutorial_completed") val tutorialCompleted: Boolean = false
)

// ------------------------------------------------------------------
// 站点配置
// ------------------------------------------------------------------
@Serializable
data class SiteConfigResponse(
    @SerialName("config") val config: SiteConfig? = null,
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class SiteConfig(
    @SerialName("announcements") val announcements: List<Announcement> = emptyList(),
    @SerialName("default_task_types") val defaultTaskTypes: List<String> = emptyList(),
    @SerialName("features") val features: SiteFeatures = SiteFeatures(),
    @SerialName("menu_order") val menuOrder: List<String> = emptyList(),
    @SerialName("nav_position") val navPosition: String? = null,
    @SerialName("site_enabled") val siteEnabled: Boolean = true,
    @SerialName("top_banner") val topBanner: String? = null,
    @SerialName("require_email_verify") val requireEmailVerify: Boolean = false
)

@Serializable
data class SiteFeatures(
    @SerialName("addAccount") val addAccount: Boolean = false,
    @SerialName("taskConsole") val taskConsole: Boolean = false
)

@Serializable
data class Announcement(
    @SerialName("id") val id: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("level") val level: String? = null,
    @SerialName("force") val force: Boolean = false,
    @SerialName("loginRequired") val loginRequired: Boolean = false
)

// ------------------------------------------------------------------
// 工作台 / 统计
// ------------------------------------------------------------------
@Serializable
data class TaskStatsResponse(
    @SerialName("stats") val stats: DashboardStats? = null,
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class DashboardStats(
    @SerialName("day") val day: PeriodStats = PeriodStats(),
    @SerialName("week") val week: PeriodStats = PeriodStats(),
    @SerialName("month") val month: PeriodStats = PeriodStats(),
    @SerialName("year") val year: PeriodStats = PeriodStats()
)

@Serializable
data class PeriodStats(
    @SerialName("accounts") val accounts: Long = 0,
    @SerialName("tasks") val tasks: Long = 0,
    @SerialName("by_type") val byType: Map<String, Long> = emptyMap()
)

@Serializable
data class OnlineCount(
    @SerialName("count") val count: Long = 0,
    @SerialName("success") val success: Boolean = false
)

// ------------------------------------------------------------------
// 身高
// ------------------------------------------------------------------
@Serializable
data class HeightInfo(
    @SerialName("height") val height: Double? = null,
    @SerialName("label") val label: String? = null,
    @SerialName("rank") val rank: Long? = null,
    @SerialName("percentile") val percentile: Double? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)

@Serializable
data class HeightRankingResponse(
    @SerialName("page") val page: Long = 1,
    @SerialName("per_page") val perPage: Long = 50,
    @SerialName("records") val records: List<HeightRankingEntry> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class HeightRankingEntry(
    @SerialName("game_user_id") val gameUserId: String? = null,
    @SerialName("height_label") val heightLabel: String? = null,
    @SerialName("height_val") val heightVal: Double? = null,
    @SerialName("height_value") val heightValue: Double? = null,
    @SerialName("id") val id: Long? = null,
    @SerialName("is_own") val isOwn: Boolean = false,
    @SerialName("scale") val scale: Double? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)

@Serializable
data class HeightSubmitRequest(
    @SerialName("height") val height: Double
)

// ------------------------------------------------------------------
// 货币（按账号查询 /accounts/{id}/currency）
// ------------------------------------------------------------------
@Serializable
data class AccountCurrencyResponse(
    @SerialName("currency") val currency: CurrencyInfo? = null,
    @SerialName("initialized") val initialized: Boolean = false,
    @SerialName("source") val source: String? = null,
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class CurrencyInfo(
    @SerialName("candles") val candles: Long? = null,
    @SerialName("heart") val heart: Long? = null,
    @SerialName("hearts") val hearts: Long? = null,
    @SerialName("season_candle") val seasonCandle: Long? = null,
    @SerialName("season_heart") val seasonHeart: Long? = null,
    @SerialName("season_pass_token") val seasonPassToken: Long? = null,
    @SerialName("season_wax") val seasonWax: Long? = null,
    @SerialName("ascended_candles") val ascendedCandles: Long? = null,
    @SerialName("wax") val wax: Long? = null,
    @SerialName("heart_wax") val heartWax: Long? = null,
    @SerialName("prestige") val prestige: Long? = null,
    @SerialName("prestige_wax") val prestigeWax: Long? = null,
    @SerialName("candle_equivalent") val candleEquivalent: Double? = null,
    @SerialName("candle_forgeable") val candleForgeable: Long? = null,
    @SerialName("candle_wax_per_candle") val candleWaxPerCandle: Long? = null,
    @SerialName("candle_wax_remainder") val candleWaxRemainder: Long? = null,
    @SerialName("vip") val vip: Long? = null,
    @SerialName("color_white") val colorWhite: Long? = null,
    @SerialName("color_black") val colorBlack: Long? = null,
    @SerialName("color_red") val colorRed: Long? = null,
    @SerialName("color_green") val colorGreen: Long? = null,
    @SerialName("color_blue") val colorBlue: Long? = null,
    @SerialName("color_yellow") val colorYellow: Long? = null,
    @SerialName("color_cyan") val colorCyan: Long? = null,
    @SerialName("color_magenta") val colorMagenta: Long? = null,
    @SerialName("updated_at") val updatedAt: String? = null
) {
    /** 各色染料总和 */
    val totalDyes: Long
        get() = listOf(colorWhite, colorBlack, colorRed, colorGreen, colorBlue, colorYellow, colorCyan, colorMagenta)
            .filterNotNull().sum()
}

// ------------------------------------------------------------------
// 账号
// ------------------------------------------------------------------
@Serializable
data class AccountsResponse(
    @SerialName("account_limit") val accountLimit: AccountLimit? = null,
    @SerialName("accounts") val accounts: List<Account> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class AccountLimit(
    @SerialName("current_count") val currentCount: Long = 0,
    @SerialName("max_count") val maxCount: Long = 0,
    @SerialName("is_over_limit") val isOverLimit: Boolean = false,
    @SerialName("excess_count") val excessCount: Long = 0
)

@Serializable
data class Account(
    @SerialName("id") val id: Long? = null,
    @SerialName("account") val account: String? = null,
    @SerialName("display_username") val displayUsername: String? = null,
    @SerialName("platform") val platform: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("device_name") val deviceName: String? = null,
    @SerialName("nid") val nid: String? = null,
    @SerialName("login_type") val loginType: String? = null,
    @SerialName("sdkuid") val sdkuid: String? = null,
    @SerialName("has_login_credentials") val hasLoginCredentials: Boolean = false,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("last_activity_at") val lastActivityAt: String? = null,
    @SerialName("height") val height: Double? = null
)

@Serializable
data class AccountAddStatus(
    @SerialName("state") val state: String? = null,
    @SerialName("step") val step: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("reference") val reference: String? = null
)

@Serializable
data class AccountTaskStatusesResponse(
    @SerialName("statuses") val statuses: Map<String, AccountTaskStatus> = emptyMap(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class AccountTaskStatus(
    @SerialName("account_id") val accountId: String? = null,
    @SerialName("running") val running: Boolean = false,
    @SerialName("task_type") val taskType: String? = null,
    @SerialName("progress") val progress: String? = null
)

// ------------------------------------------------------------------
// 设备
// ------------------------------------------------------------------
@Serializable
data class DevicesResponse(
    @SerialName("devices") val devices: List<Device> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class DeviceModelsResponse(
    @SerialName("android") val android: List<DeviceModel> = emptyList(),
    @SerialName("ios") val ios: List<DeviceModel> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class Device(
    @SerialName("id") val id: Long? = null,
    @SerialName("device_id") val deviceId: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("platform") val platform: String? = null,
    @SerialName("model") val model: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("created_at") val createdAt: String? = null
)

@Serializable
data class DeviceModel(
    @SerialName("id") val id: String? = null,
    @SerialName("label") val label: String? = null,
    @SerialName("brand") val brand: String? = null,
    @SerialName("index") val index: Long? = null,
    @SerialName("model") val model: String? = null,
    @SerialName("name") val name: String? = null
)

@Serializable
data class CreateDeviceRequest(
    @SerialName("name") val name: String? = null,
    @SerialName("model") val model: String? = null
)

// ------------------------------------------------------------------
// 好友
// ------------------------------------------------------------------
@Serializable
data class FriendsResponse(
    @SerialName("friends") val friends: List<Friend> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class Friend(
    @SerialName("id") val id: String? = null,
    @SerialName("uuid") val uuid: String? = null,
    @SerialName("nickname") val nickname: String? = null,
    @SerialName("note") val note: String? = null,
    @SerialName("height") val height: Double? = null,
    @SerialName("status") val status: String? = null
)

// ------------------------------------------------------------------
// 聊天室
// ------------------------------------------------------------------
@Serializable
data class ChatRoomsResponse(
    @SerialName("rooms") val rooms: List<ChatRoom> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class ChatRoom(
    @SerialName("id") val id: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("online") val online: Long? = null
)

// ------------------------------------------------------------------
// 通知 / 反馈
// ------------------------------------------------------------------
@Serializable
data class NotificationsResponse(
    @SerialName("notifications") val notifications: List<NotificationItem> = emptyList(),
    @SerialName("unread_count") val unreadCount: Long = 0,
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class NotificationItem(
    @SerialName("id") val id: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("read") val read: Boolean = false,
    @SerialName("created_at") val createdAt: String? = null
)

@Serializable
data class FeedbackTicketsResponse(
    @SerialName("tickets") val tickets: List<FeedbackTicket> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class FeedbackTicket(
    @SerialName("id") val id: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("created_at") val createdAt: String? = null
)

// ------------------------------------------------------------------
// 活动
// ------------------------------------------------------------------
@Serializable
data class ActivitiesResponse(
    @SerialName("activities") val activities: List<ActivityItem> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class ActivityItem(
    @SerialName("id") val id: Long? = null,
    @SerialName("activity_type") val activityType: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("subtitle") val subtitle: String? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("button_text") val buttonText: String? = null,
    @SerialName("theme") val theme: String? = null,
    @SerialName("force_show") val forceShow: Boolean = false,
    @SerialName("reward_account_quota") val rewardAccountQuota: Long? = null
)

// ------------------------------------------------------------------
// 心火交易
// ------------------------------------------------------------------
@Serializable
data class HeartTradeResponse(
    @SerialName("accounts") val accounts: List<HeartTradeAccount> = emptyList(),
    @SerialName("batches") val batches: List<HeartTradeBatch> = emptyList(),
    @SerialName("records") val records: List<HeartTradeBatch> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class HeartTradeAccount(
    @SerialName("id") val id: Long? = null,
    @SerialName("display_username") val displayUsername: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("ready") val ready: Boolean = false,
    @SerialName("enabled") val enabled: Boolean = false
)

@Serializable
data class HeartTradeBatch(
    @SerialName("id") val id: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("count") val count: Long? = null,
    @SerialName("created_at") val createdAt: String? = null
)

// ------------------------------------------------------------------
// 任务 / 排期
// ------------------------------------------------------------------
@Serializable
data class SchedulesResponse(
    @SerialName("schedules") val schedules: List<Schedule> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class Schedule(
    @SerialName("id") val id: Long? = null,
    @SerialName("account_id") val accountId: Long? = null,
    @SerialName("account_name") val accountName: String? = null,
    @SerialName("active") val active: Boolean = false,
    @SerialName("task_types") val taskTypes: List<String> = emptyList(),
    @SerialName("time_of_day") val timeOfDay: String? = null,
    @SerialName("start_date") val startDate: String? = null,
    @SerialName("end_date") val endDate: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("user_id") val userId: Long? = null,
    @SerialName("enabled") val enabled: Boolean = false,
    @SerialName("task_type") val taskType: String? = null,
    @SerialName("cron") val cron: String? = null,
    @SerialName("next_run") val nextRun: String? = null
)

// ------------------------------------------------------------------
// AI
// ------------------------------------------------------------------
@Serializable
data class AiConfigResponse(
    @SerialName("has_key") val hasKey: Boolean = false,
    @SerialName("key_hint") val keyHint: String? = null,
    @SerialName("model") val model: String? = null,
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class AiConfig(
    @SerialName("enabled") val enabled: Boolean = false,
    @SerialName("model") val model: String? = null
)

@Serializable
data class AiDiagnoseRequest(
    @SerialName("question") val question: String
)

@Serializable
data class AiDiagnoseResponse(
    @SerialName("answer") val answer: String? = null,
    @SerialName("success") val success: Boolean = false,
    @SerialName("message") val message: String? = null
)
