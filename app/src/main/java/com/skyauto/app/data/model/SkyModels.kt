package com.skyauto.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

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
    // ---- 染料蜡火（合成原料） ----
    @SerialName("color_white_wax") val colorWhiteWax: Long? = null,
    @SerialName("color_black_wax") val colorBlackWax: Long? = null,
    @SerialName("color_red_wax") val colorRedWax: Long? = null,
    @SerialName("color_green_wax") val colorGreenWax: Long? = null,
    @SerialName("color_blue_wax") val colorBlueWax: Long? = null,
    @SerialName("color_yellow_wax") val colorYellowWax: Long? = null,
    @SerialName("color_cyan_wax") val colorCyanWax: Long? = null,
    @SerialName("color_magenta_wax") val colorMagentaWax: Long? = null,
    // ---- 活动蜡烛 ----
    @SerialName("event_candle_days_of_nature") val eventCandleDaysOfNature: Long? = null,
    @SerialName("event_candle_days_of_sky") val eventCandleDaysOfSky: Long? = null,
    @SerialName("event_candle_days_of_sunlight") val eventCandleDaysOfSunlight: Long? = null,
    @SerialName("event_candle_days_of_sunlight_wax") val eventCandleDaysOfSunlightWax: Long? = null,
    @SerialName("event_candle_days_of_treasure") val eventCandleDaysOfTreasure: Long? = null,
    @SerialName("event_candle_days_of_treasure_wax") val eventCandleDaysOfTreasureWax: Long? = null,
    @SerialName("event_candle_days_of_twinribbon") val eventCandleDaysOfTwinribbon: Long? = null,
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

// ------------------------------------------------------------------
// 公告（/system-announcements）
// ------------------------------------------------------------------
@Serializable
data class AnnouncementsResponse(
    @SerialName("announcements") val announcements: List<AnnouncementFull> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class AnnouncementFull(
    @SerialName("id") val id: Long? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("summary") val summary: String? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("content_format") val contentFormat: String? = null,
    @SerialName("level") val level: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("link") val link: String? = null,
    @SerialName("link_text") val linkText: String? = null,
    @SerialName("is_read") val isRead: Boolean = false,
    @SerialName("published_at") val publishedAt: String? = null,
    @SerialName("created_at") val createdAt: String? = null
)

// ------------------------------------------------------------------
// 邀请（/invitations/me）
// ------------------------------------------------------------------
@Serializable
data class InvitationsResponse(
    @SerialName("code") val code: String? = null,
    @SerialName("invite_url") val inviteUrl: String? = null,
    @SerialName("records") val records: List<InviteRecord> = emptyList(),
    @SerialName("rewarded_quota") val rewardedQuota: Long = 0,
    @SerialName("success_count") val successCount: Long = 0,
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class InviteRecord(
    @SerialName("id") val id: Long? = null,
    @SerialName("username") val username: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("status") val status: String? = null
)

// ------------------------------------------------------------------
// 世界任务（/world-quests）
// ------------------------------------------------------------------
@Serializable
data class WorldQuestsResponse(
    @SerialName("quests") val quests: List<WorldQuest> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class WorldQuest(
    @SerialName("code") val code: String? = null,
    @SerialName("label") val label: String? = null
)

// ------------------------------------------------------------------
// 平台运营数据（/stats）
// ------------------------------------------------------------------
@Serializable
data class OperationsResponse(
    @SerialName("devices") val devices: OperationsDevices = OperationsDevices(),
    @SerialName("generatedAt") val generatedAt: String? = null,
    @SerialName("month") val month: OperationsPeriod = OperationsPeriod(),
    @SerialName("today") val today: OperationsPeriod = OperationsPeriod(),
    @SerialName("timezone") val timezone: String? = null,
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class OperationsDevices(
    @SerialName("monthAdded") val monthAdded: Long = 0,
    @SerialName("todayAdded") val todayAdded: Long = 0,
    @SerialName("total") val total: Long = 0
)

@Serializable
data class OperationsPeriod(
    @SerialName("accounts") val accounts: Long = 0,
    @SerialName("completedRuns") val completedRuns: Long = 0,
    @SerialName("executionDevices") val executionDevices: Long = 0,
    @SerialName("taskRuns") val taskRuns: Long = 0,
    @SerialName("taskTypes") val taskTypes: List<OperationsTaskType> = emptyList()
)

@Serializable
data class OperationsTaskType(
    @SerialName("completed") val completed: Long = 0,
    @SerialName("label") val label: String? = null,
    @SerialName("runs") val runs: Long = 0,
    @SerialName("type") val type: String? = null
)

// ------------------------------------------------------------------
// 游戏情报（/game-insights/public 与 /accounts/{id}/game-insights）
// ------------------------------------------------------------------
@Serializable
data class GameInsightsPublicResponse(
    @SerialName("announcement") val announcement: GameInsightAnnouncement? = null,
    @SerialName("errors") val errors: List<GameInsightError> = emptyList(),
    @SerialName("fetched_at") val fetchedAt: String? = null,
    @SerialName("official") val official: Boolean = false,
    @SerialName("partial") val partial: Boolean = false,
    @SerialName("server_time") val serverTime: ServerTime? = null,
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class GameInsightAnnouncement(
    @SerialName("content") val content: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("updated_hint") val updatedHint: String? = null
)

@Serializable
data class GameInsightError(
    @SerialName("message") val message: String? = null,
    @SerialName("section") val section: String? = null
)

@Serializable
data class ServerTime(
    @SerialName("epoch") val epoch: Long? = null,
    @SerialName("iso") val iso: String? = null,
    @SerialName("offset_seconds") val offsetSeconds: Long? = null
)

@Serializable
data class GameInsightsResponse(
    @SerialName("capabilities") val capabilities: GameCapabilities? = null,
    @SerialName("commerce") val commerce: GameCommerce? = null,
    @SerialName("details") val details: GameDetails? = null,
    @SerialName("errors") val errors: List<GameInsightError> = emptyList(),
    @SerialName("event_currencies") val eventCurrencies: GameEventCurrencies? = null,
    @SerialName("events") val events: GameEvents? = null,
    @SerialName("exploration") val exploration: GameExploration? = null,
    @SerialName("fetched_at") val fetchedAt: String? = null,
    @SerialName("growth") val growth: GameGrowth? = null,
    @SerialName("magic") val magic: GameMagic? = null,
    @SerialName("official") val official: Boolean = false,
    @SerialName("partial") val partial: Boolean = false,
    @SerialName("quest_definition_count") val questDefinitionCount: Long = 0,
    @SerialName("quest_schedule") val questSchedule: List<GameQuestSchedule> = emptyList(),
    @SerialName("quests") val quests: GameQuests? = null,
    @SerialName("server_time") val serverTime: ServerTime? = null,
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class GameCapabilities(
    @SerialName("count") val count: Long = 0,
    @SerialName("items") val items: List<String> = emptyList()
)

@Serializable
data class GameCommerce(
    @SerialName("generic_shops") val genericShops: Long = 0,
    @SerialName("iap_items") val iapItems: Long = 0,
    @SerialName("lootboxes") val lootboxes: Long = 0,
    @SerialName("shop_items") val shopItems: Long = 0
)

// ------------------------------------------------------------------
// 货币合成（/accounts/{id}/currency/forge-options）
// ------------------------------------------------------------------
@Serializable
data class ForgeOptionsResponse(
    @SerialName("currency") val currency: CurrencyInfo? = null,
    @SerialName("options") val options: List<ForgeOption> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class ForgeOption(
    @SerialName("available") val available: Boolean = false,
    @SerialName("key") val key: String? = null,
    @SerialName("label") val label: String? = null,
    @SerialName("max_forgeable") val maxForgeable: Long = 0,
    @SerialName("reason") val reason: String? = null,
    @SerialName("source_balance") val sourceBalance: Long = 0,
    @SerialName("unit") val unit: String? = null,
    @SerialName("unit_cost") val unitCost: Long = 0
)

// ------------------------------------------------------------------
// 账号配置规则（/account-config-rules）
// ------------------------------------------------------------------
@Serializable
data class AccountConfigRulesResponse(
    @SerialName("accounts") val accounts: List<ConfigRuleAccount> = emptyList(),
    @SerialName("maps") val maps: List<ConfigRuleMap> = emptyList(),
    @SerialName("rules") val rules: List<ConfigRule> = emptyList(),
    @SerialName("schema_version") val schemaVersion: Long = 1,
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class ConfigRuleAccount(
    @SerialName("id") val id: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("account") val account: String? = null
)

@Serializable
data class ConfigRuleMap(
    @SerialName("code") val code: String? = null,
    @SerialName("fire_count") val fireCount: Long = 0,
    @SerialName("wax_count") val waxCount: Long = 0,
    @SerialName("label") val label: String? = null,
    @SerialName("dye_count") val dyeCount: Long? = null
)

@Serializable
data class ConfigRule(
    @SerialName("id") val id: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("group") val group: String? = null,
    @SerialName("default_task_types") val defaultTaskTypes: List<String> = emptyList(),
    @SerialName("bindings") val bindings: List<Long> = emptyList(),
    @SerialName("created_at") val createdAt: String? = null
)

// ------------------------------------------------------------------
// 任务历史 / 任务状态 / 今日完成
// ------------------------------------------------------------------
@Serializable
data class TaskHistoryResponse(
    @SerialName("account_id") val accountId: Long? = null,
    @SerialName("records") val records: List<TaskRecord> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class TaskRecord(
    @SerialName("task_id") val taskId: String? = null,
    @SerialName("task_type") val taskType: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("progress") val progress: Long = 0,
    @SerialName("progress_stage") val progressStage: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("source") val source: String? = null,
    @SerialName("account_id") val accountId: Long? = null,
    @SerialName("account_name") val accountName: String? = null,
    @SerialName("user_id") val userId: Long? = null,
    @SerialName("logs") val logs: List<String> = emptyList(),
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("started_at") val startedAt: String? = null,
    @SerialName("completed_at") val completedAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)

@Serializable
data class TaskStatusResponse(
    @SerialName("execution_eligibility") val executionEligibility: ExecutionEligibility? = null,
    @SerialName("has_task") val hasTask: Boolean = false,
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class ExecutionEligibility(
    @SerialName("account_status") val accountStatus: String? = null,
    @SerialName("allowed") val allowed: Boolean = false,
    @SerialName("code") val code: String? = null,
    @SerialName("message") val message: String? = null
)

@Serializable
data class DoneTodayResponse(
    @SerialName("daily_done") val dailyDone: Boolean = false,
    @SerialName("dye_done") val dyeDone: Boolean = false,
    @SerialName("fire_done") val fireDone: Boolean = false,
    @SerialName("heart_done") val heartDone: Boolean = false,
    @SerialName("receive_fire_done") val receiveFireDone: Boolean = false,
    @SerialName("runtask_done") val runtaskDone: Boolean = false,
    @SerialName("sacrifice_done") val sacrificeDone: Boolean = false,
    @SerialName("season_pass_done") val seasonPassDone: Boolean = false,
    @SerialName("seasonal_wax_done") val seasonalWaxDone: Boolean = false,
    @SerialName("sunlight_photo_2026_done") val sunlightPhoto2026Done: Boolean = false,
    @SerialName("wings_done") val wingsDone: Boolean = false,
    @SerialName("world_done") val worldDone: Boolean = false,
    @SerialName("success") val success: Boolean = false
)

// ------------------------------------------------------------------
// 好友关系 / 送心好友
// ------------------------------------------------------------------
@Serializable
data class FriendRelationsResponse(
    @SerialName("friends") val friends: List<FriendRelation> = emptyList(),
    @SerialName("scope") val scope: String? = null,
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class HeartFriendsResponse(
    @SerialName("friends") val friends: List<FriendRelation> = emptyList(),
    @SerialName("saved_friend_ids") val savedFriendIds: List<String> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class FriendRelation(
    @SerialName("friend_id") val friendId: String? = null,
    @SerialName("idx") val idx: Long = 0,
    @SerialName("nickname") val nickname: String? = null,
    @SerialName("selected") val selected: Boolean = false,
    @SerialName("raw") val raw: FriendRaw? = null
)

@Serializable
data class FriendRaw(
    @SerialName("friend_id") val friendId: String? = null,
    @SerialName("nickname") val nickname: String? = null,
    @SerialName("level") val level: Long? = null,
    @SerialName("abilities") val abilities: String? = null,
    @SerialName("given") val given: String? = null,
    @SerialName("hints") val hints: String? = null,
    @SerialName("outfit") val outfit: String? = null,
    @SerialName("player_badge_type") val playerBadgeType: String? = null,
    @SerialName("when_created") val whenCreated: String? = null
)

// ------------------------------------------------------------------
// 聊天消息（/chat/{id}/messages）
// ------------------------------------------------------------------
@Serializable
data class ChatMessagesResponse(
    @SerialName("messages") val messages: List<ChatMessage> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class ChatMessage(
    @SerialName("id") val id: Long? = null,
    @SerialName("user_id") val userId: Long? = null,
    @SerialName("username") val username: String? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("is_admin") val isAdmin: Boolean = false,
    @SerialName("is_deleted") val isDeleted: Boolean = false,
    @SerialName("created_at") val createdAt: String? = null
)

// ------------------------------------------------------------------
// 好友码（/accounts/{id}/friend-code/list）
// ------------------------------------------------------------------
@Serializable
data class FriendCodesResponse(
    @SerialName("data") val data: FriendCodesData? = null,
    @SerialName("items") val items: List<FriendCodeInvite> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class FriendCodesData(
    @SerialName("invites") val invites: List<FriendCodeInvite> = emptyList()
)

@Serializable
data class FriendCodeInvite(
    @SerialName("code") val code: String? = null,
    @SerialName("note") val note: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("invite_url") val inviteUrl: String? = null
)

// ------------------------------------------------------------------
// 灵犀亲密（/accounts/{id}/spirit-intimacy/options）
// ------------------------------------------------------------------
@Serializable
data class SpiritIntimacyResponse(
    @SerialName("notice") val notice: String? = null,
    @SerialName("options") val options: List<SpiritIntimacyItem> = emptyList(),
    @SerialName("spirits") val spirits: List<SpiritIntimacyItem> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class SpiritIntimacyItem(
    @SerialName("key") val key: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("giver") val giver: String? = null,
    @SerialName("quest_name") val questName: String? = null,
    @SerialName("value") val value: Long = 0
)

// ------------------------------------------------------------------
// 身高查询（/accounts/{id}/height）
// ------------------------------------------------------------------
@Serializable
data class HeightQueryResponse(
    @SerialName("device_name") val deviceName: String? = null,
    @SerialName("display_username") val displayUsername: String? = null,
    @SerialName("friends") val friends: List<HeightFriend> = emptyList(),
    @SerialName("self_account") val selfAccount: SelfHeightAccount? = null,
    @SerialName("self_height") val selfHeight: Double? = null,
    @SerialName("self_height_val") val selfHeightVal: Double? = null,
    @SerialName("self_scale") val selfScale: Double? = null,
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class SelfHeightAccount(
    @SerialName("account") val account: String? = null,
    @SerialName("account_id") val accountId: Long? = null,
    @SerialName("display_username") val displayUsername: String? = null,
    @SerialName("game_user_id") val gameUserId: String? = null
)

@Serializable
data class HeightFriend(
    @SerialName("account") val account: String? = null,
    @SerialName("display_username") val displayUsername: String? = null,
    @SerialName("friend_id") val friendId: String? = null,
    @SerialName("nickname") val nickname: String? = null,
    @SerialName("height") val height: Double? = null,
    @SerialName("height_val") val heightVal: Double? = null,
    @SerialName("height_value") val heightValue: Double? = null,
    @SerialName("scale") val scale: Double? = null
)

// ------------------------------------------------------------------
// 身高 API 配置（/accounts/{id}/height-api-config）
// ------------------------------------------------------------------
@Serializable
data class HeightApiConfigResponse(
    @SerialName("configured") val configured: Boolean = false,
    @SerialName("enabled") val enabled: Boolean = false,
    @SerialName("endpoint") val endpoint: String? = null,
    @SerialName("token") val token: String? = null,
    @SerialName("success") val success: Boolean = false
)

// ------------------------------------------------------------------
// 跑图配置（/accounts/{id}/runtask-config）
// ------------------------------------------------------------------
@Serializable
data class RuntaskConfigResponse(
    @SerialName("account_id") val accountId: Long? = null,
    @SerialName("config") val config: RuntaskConfig? = null,
    @SerialName("maps") val maps: List<ConfigRuleMap> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class RuntaskConfig(
    @SerialName("batch_interval_ms") val batchIntervalMs: Long = 0,
    @SerialName("map_interval") val mapInterval: Double = 0.0,
    @SerialName("maps") val maps: List<String> = emptyList(),
    @SerialName("updated_at") val updatedAt: String? = null
)

// ------------------------------------------------------------------
// 好友能力（/accounts/{id}/friend-ability/options）
// ------------------------------------------------------------------
@Serializable
data class FriendAbilityResponse(
    @SerialName("accounts") val accounts: List<ConfigRuleAccount> = emptyList(),
    @SerialName("nodes") val nodes: List<FriendAbilityNode> = emptyList(),
    @SerialName("notice") val notice: String? = null,
    @SerialName("source") val source: FriendAbilitySource? = null,
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class FriendAbilityNode(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("cost") val cost: Long = 0,
    @SerialName("unlocked") val unlocked: Boolean = false
)

@Serializable
data class FriendAbilitySource(
    @SerialName("id") val id: Long? = null,
    @SerialName("account") val account: String? = null,
    @SerialName("display_username") val displayUsername: String? = null,
    @SerialName("status") val status: String? = null
)

// ------------------------------------------------------------------
// 微信绑定 / 订单
// ------------------------------------------------------------------
@Serializable
data class WechatStatusResponse(
    @SerialName("bound") val bound: Boolean = false,
    @SerialName("bound_at") val boundAt: String? = null,
    @SerialName("configured") val configured: Boolean = false,
    @SerialName("notifications_enabled") val notificationsEnabled: Boolean = false,
    @SerialName("subscribed") val subscribed: Boolean = false,
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class OrdersResponse(
    @SerialName("orders") val orders: List<Order> = emptyList(),
    @SerialName("success") val success: Boolean = false
)

@Serializable
data class Order(
    @SerialName("id") val id: Long? = null,
    @SerialName("order_no") val orderNo: String? = null,
    @SerialName("amount") val amount: Double? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("created_at") val createdAt: String? = null
)

// ------------------------------------------------------------------
// 游戏情报 · 细节补充模型
// ------------------------------------------------------------------
@Serializable
data class GameDetails(
    @SerialName("account") val account: GameDetailsAccount? = null,
    @SerialName("commerce") val commerce: GameCommerce? = null,
    @SerialName("exploration") val exploration: GameDetailsExploration? = null,
    @SerialName("growth") val growth: GameGrowth? = null,
    @SerialName("magic") val magic: GameMagic? = null,
    @SerialName("quest_definitions") val questDefinitions: GameQuestDefinitions? = null,
    @SerialName("references") val references: GameReferences? = null,
    @SerialName("todos") val todos: GameTodos? = null,
    @SerialName("trust") val trust: GameTrust? = null
)

@Serializable
data class GameDetailsAccount(
    @SerialName("achievement_stats") val achievementStats: JsonElement? = null,
    @SerialName("event_currency") val eventCurrency: JsonElement? = null,
    @SerialName("event_schedule") val eventSchedule: JsonElement? = null,
    @SerialName("messages") val messages: JsonElement? = null,
    @SerialName("wing_buffs") val wingBuffs: JsonElement? = null
)

@Serializable
data class GameDetailsExploration(
    @SerialName("checkpoints") val checkpoints: Long = 0,
    @SerialName("collectibles") val collectibles: Long = 0,
    @SerialName("inventory_unlocks") val inventoryUnlocks: Long = 0,
    @SerialName("level_pickups") val levelPickups: Long = 0,
    @SerialName("status_unlocks") val statusUnlocks: Long = 0
)

@Serializable
data class GameEventCurrencies(
    @SerialName("event_currency_defs") val eventCurrencyDefs: List<GameEventCurrency> = emptyList()
)

@Serializable
data class GameEventCurrency(
    @SerialName("id") val id: String? = null,
    @SerialName("client_currency") val clientCurrency: String? = null,
    @SerialName("event_name") val eventName: String? = null,
    @SerialName("purchasable") val purchasable: Boolean = false,
    @SerialName("forge_cost") val forgeCost: Long? = null,
    @SerialName("hud_icon") val hudIcon: String? = null,
    @SerialName("forge_rate_reset_event") val forgeRateResetEvent: String? = null,
    @SerialName("use_forge_rates") val useForgeRates: Boolean? = null
)

@Serializable
data class GameEvents(
    @SerialName("base_time") val baseTime: Long = 0,
    @SerialName("events") val events: List<GameEvent> = emptyList(),
    @SerialName("server_time") val serverTime: Long = 0,
    @SerialName("valid_until") val validUntil: Long = 0
)

@Serializable
data class GameEvent(
    @SerialName("name") val name: String? = null,
    @SerialName("start_time") val startTime: Long? = null,
    @SerialName("end_time") val endTime: Long? = null,
    @SerialName("active") val active: Boolean = false
)

@Serializable
data class GameExploration(
    @SerialName("checkpoints") val checkpoints: Long = 0,
    @SerialName("collectibles") val collectibles: Long = 0,
    @SerialName("inventory_unlocks") val inventoryUnlocks: Long = 0,
    @SerialName("level_pickups") val levelPickups: Long = 0,
    @SerialName("status_unlocks") val statusUnlocks: Long = 0
)

@Serializable
data class GameGrowth(
    @SerialName("player_profile") val playerProfile: GamePlayerProfile? = null,
    @SerialName("rank") val rank: JsonElement? = null,
    @SerialName("serendipity") val serendipity: JsonElement? = null,
    @SerialName("star") val star: GameStar? = null
)

@Serializable
data class GamePlayerProfile(
    @SerialName("personality_type") val personalityType: Long? = null
)

@Serializable
data class GameStar(
    @SerialName("get_star_tag_defs") val getStarTagDefs: List<JsonElement> = emptyList(),
    @SerialName("star_tag_links") val starTagLinks: List<JsonElement> = emptyList()
)

@Serializable
data class GameMagic(
    @SerialName("active_buffs") val activeBuffs: Long = 0,
    @SerialName("buff_defs") val buffDefs: Long = 0,
    @SerialName("consumable_defs") val consumableDefs: Long = 0,
    @SerialName("consumables") val consumables: Long = 0
)

@Serializable
data class GameQuestDefinitions(
    @SerialName("get_quest_defs") val getQuestDefs: List<JsonElement> = emptyList()
)

@Serializable
data class GameReferences(
    @SerialName("map_defs") val mapDefs: JsonElement? = null,
    @SerialName("npc_defs") val npcDefs: JsonElement? = null,
    @SerialName("outfit_defs") val outfitDefs: JsonElement? = null,
    @SerialName("questionnaires") val questionnaires: JsonElement? = null,
    @SerialName("radiance_spawns") val radianceSpawns: JsonElement? = null,
    @SerialName("video_defs") val videoDefs: JsonElement? = null
)

@Serializable
data class GameTodos(
    @SerialName("app_badge") val appBadge: JsonElement? = null,
    @SerialName("login_rewards") val loginRewards: JsonElement? = null,
    @SerialName("meditation_topic") val meditationTopic: JsonElement? = null,
    @SerialName("red_dot") val redDot: JsonElement? = null
)

@Serializable
data class GameTrust(
    @SerialName("trust_activity_defs") val trustActivityDefs: JsonElement? = null,
    @SerialName("trust_group_defs") val trustGroupDefs: JsonElement? = null,
    @SerialName("trust_status") val trustStatus: JsonElement? = null,
    @SerialName("trust_tier_defs") val trustTierDefs: JsonElement? = null
)

@Serializable
data class GameQuestSchedule(
    @SerialName("epoch") val epoch: Long? = null,
    @SerialName("name") val name: String? = null
)

@Serializable
data class GameQuests(
    @SerialName("active") val active: List<GameQuest> = emptyList(),
    @SerialName("canceled") val canceled: List<GameQuest> = emptyList(),
    @SerialName("collected") val collected: List<GameQuest> = emptyList()
)

@Serializable
data class GameQuest(
    @SerialName("name") val name: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("progress") val progress: Long? = null,
    @SerialName("required") val required: Long? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("start_time") val startTime: Long? = null,
    @SerialName("expiry_time") val expiryTime: Long? = null
)

// ------------------------------------------------------------------
// 账号添加任务（/accounts/add/status/{taskId}）
// ------------------------------------------------------------------
@Serializable
data class AccountAddStatusResponse(
    @SerialName("success") val success: Boolean = false,
    @SerialName("message") val message: String? = null,
    @SerialName("task") val task: AccountAddTask? = null
)

@Serializable
data class AccountAddTask(
    @SerialName("task_id") val taskId: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("logs") val logs: List<AccountAddLog> = emptyList(),
    @SerialName("message") val message: String? = null,
    @SerialName("sms_verify_url") val smsVerifyUrl: String? = null,
    @SerialName("sms_content") val smsContent: String? = null,
    @SerialName("sms_target") val smsTarget: String? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("need_huawei_trust") val needHuaweiTrust: Boolean = false,
    @SerialName("need_huawei_captcha") val needHuaweiCaptcha: Boolean = false,
    @SerialName("need_bilibili_browser") val needBilibiliBrowser: Boolean = false,
    @SerialName("need_honor_browser") val needHonorBrowser: Boolean = false,
    @SerialName("need_vivo_subaccount") val needVivoSubaccount: Boolean = false,
    @SerialName("need_uc_account") val needUcAccount: Boolean = false,
    @SerialName("vivo_subaccounts") val vivoSubaccounts: List<JsonElement> = emptyList(),
    @SerialName("uc_accounts") val ucAccounts: List<JsonElement> = emptyList(),
    @SerialName("code") val code: String? = null,
    @SerialName("account") val account: Account? = null
)

@Serializable
data class AccountAddLog(
    @SerialName("time") val time: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("level") val level: String? = null
)
