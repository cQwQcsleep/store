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
    @SerialName("id") val id: String? = null,
    @SerialName("username") val username: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("role") val role: String? = null,
    @SerialName("qq") val qq: String? = null,
    @SerialName("nickname") val nickname: String? = null,
    @SerialName("points") val points: Long? = null,
    @SerialName("proxy_points") val proxyPoints: Long? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("avatar") val avatar: String? = null
)

// ------------------------------------------------------------------
// 站点配置
// ------------------------------------------------------------------
@Serializable
data class SiteConfig(
    @SerialName("announcements") val announcements: List<Announcement> = emptyList(),
    @SerialName("default_task_types") val defaultTaskTypes: List<String> = emptyList()
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
data class DashboardStats(
    @SerialName("accounts") val accounts: Long? = null,
    @SerialName("tasks_executed") val tasksExecuted: Long? = null,
    @SerialName("completed") val completed: Long? = null,
    @SerialName("devices") val devices: Long? = null,
    @SerialName("completion_rate") val completionRate: Double? = null,
    @SerialName("main_task_types") val mainTaskTypes: List<TaskTypeStat> = emptyList(),
    @SerialName("today_new_devices") val todayNewDevices: Long? = null
)

@Serializable
data class TaskTypeStat(
    @SerialName("type") val type: String? = null,
    @SerialName("count") val count: Long? = null
)

@Serializable
data class OnlineCount(
    @SerialName("count") val count: Long = 0
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
data class HeightRankingEntry(
    @SerialName("rank") val rank: Long? = null,
    @SerialName("username") val username: String? = null,
    @SerialName("height") val height: Double? = null,
    @SerialName("label") val label: String? = null
)

@Serializable
data class HeightSubmitRequest(
    @SerialName("height") val height: Double
)

// ------------------------------------------------------------------
// 货币
// ------------------------------------------------------------------
@Serializable
data class CurrencyInfo(
    @SerialName("candles") val candles: Long? = null,
    @SerialName("hearts") val hearts: Long? = null,
    @SerialName("season_candles") val seasonCandles: Long? = null,
    @SerialName("dyes") val dyes: Long? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)

@Serializable
data class ForgeOptions(
    @SerialName("items") val items: List<ForgeItem> = emptyList()
)

@Serializable
data class ForgeItem(
    @SerialName("id") val id: String? = null,
    @SerialName("label") val label: String? = null,
    @SerialName("cost") val cost: Long? = null,
    @SerialName("currency") val currency: String? = null,
    @SerialName("yield") val yield: Long? = null
)

// ------------------------------------------------------------------
// 账号
// ------------------------------------------------------------------
@Serializable
data class Account(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("platform") val platform: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("height") val height: Double? = null,
    @SerialName("uuid") val uuid: String? = null,
    @SerialName("sms_phone") val smsPhone: String? = null
)

@Serializable
data class AccountAddStatus(
    @SerialName("state") val state: String? = null,
    @SerialName("step") val step: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("reference") val reference: String? = null
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
data class Device(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("model") val model: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("created_at") val createdAt: String? = null
)

@Serializable
data class DeviceModel(
    @SerialName("id") val id: String? = null,
    @SerialName("label") val label: String? = null
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
data class Friend(
    @SerialName("id") val id: String? = null,
    @SerialName("uuid") val uuid: String? = null,
    @SerialName("nickname") val nickname: String? = null,
    @SerialName("note") val note: String? = null,
    @SerialName("height") val height: Double? = null,
    @SerialName("status") val status: String? = null
)

@Serializable
data class FriendCode(
    @SerialName("id") val id: String? = null,
    @SerialName("code") val code: String? = null,
    @SerialName("link") val link: String? = null,
    @SerialName("used") val used: Boolean = false
)

@Serializable
data class AbilityOption(
    @SerialName("id") val id: String? = null,
    @SerialName("label") val label: String? = null,
    @SerialName("cost") val cost: Long? = null,
    @SerialName("currency") val currency: String? = null
)

// ------------------------------------------------------------------
// 灵犀 / 心火
// ------------------------------------------------------------------
@Serializable
data class IntimacyOption(
    @SerialName("id") val id: String? = null,
    @SerialName("label") val label: String? = null,
    @SerialName("level") val level: Long? = null
)

@Serializable
data class SpiritShop(
    @SerialName("items") val items: List<SpiritShopItem> = emptyList()
)

@Serializable
data class SpiritShopItem(
    @SerialName("id") val id: String? = null,
    @SerialName("label") val label: String? = null,
    @SerialName("cost") val cost: Long? = null,
    @SerialName("currency") val currency: String? = null
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
data class Schedule(
    @SerialName("id") val id: String? = null,
    @SerialName("account_id") val accountId: String? = null,
    @SerialName("task_type") val taskType: String? = null,
    @SerialName("cron") val cron: String? = null,
    @SerialName("enabled") val enabled: Boolean = false,
    @SerialName("next_run") val nextRun: String? = null
)

@Serializable
data class TaskDefinition(
    @SerialName("type") val type: String? = null,
    @SerialName("label") val label: String? = null,
    @SerialName("map") val map: String? = null,
    @SerialName("interval_seconds") val intervalSeconds: Long? = null
)

// ------------------------------------------------------------------
// 通知 / 公告 / 反馈
// ------------------------------------------------------------------
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
data class FeedbackTicket(
    @SerialName("id") val id: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("created_at") val createdAt: String? = null
)

// ------------------------------------------------------------------
// 聊天
// ------------------------------------------------------------------
@Serializable
data class ChatRoom(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("online") val online: Long? = null
)

@Serializable
data class ChatMessage(
    @SerialName("id") val id: String? = null,
    @SerialName("room_id") val roomId: String? = null,
    @SerialName("username") val username: String? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("created_at") val createdAt: String? = null
)

// ------------------------------------------------------------------
// AI
// ------------------------------------------------------------------
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
    @SerialName("answer") val answer: String? = null
)