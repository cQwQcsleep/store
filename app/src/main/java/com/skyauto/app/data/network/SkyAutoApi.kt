package com.skyauto.app.data.network

import com.skyauto.app.data.model.Account
import com.skyauto.app.data.model.AccountAddStatus
import com.skyauto.app.data.model.AccountTaskStatus
import com.skyauto.app.data.model.AiConfig
import com.skyauto.app.data.model.AiDiagnoseResponse
import com.skyauto.app.data.model.ApiResponse
import com.skyauto.app.data.model.ChatMessage
import com.skyauto.app.data.model.ChatRoom
import com.skyauto.app.data.model.CurrencyInfo
import com.skyauto.app.data.model.DashboardStats
import com.skyauto.app.data.model.Device
import com.skyauto.app.data.model.DeviceModel
import com.skyauto.app.data.model.FeedbackTicket
import com.skyauto.app.data.model.ForgeOptions
import com.skyauto.app.data.model.Friend
import com.skyauto.app.data.model.FriendCode
import com.skyauto.app.data.model.HeightInfo
import com.skyauto.app.data.model.HeightRankingEntry
import com.skyauto.app.data.model.HeightSubmitRequest
import com.skyauto.app.data.model.IntimacyOption
import com.skyauto.app.data.model.LoginRequest
import com.skyauto.app.data.model.NotificationItem
import com.skyauto.app.data.model.OnlineCount
import com.skyauto.app.data.model.RegisterRequest
import com.skyauto.app.data.model.ResetPasswordRequest
import com.skyauto.app.data.model.Schedule
import com.skyauto.app.data.model.SendResetCodeRequest
import com.skyauto.app.data.model.SiteConfig
import com.skyauto.app.data.model.SpiritShop
import com.skyauto.app.data.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SkyAutoApi {

    // ---- 站点配置（公开） ----
    @GET("config/site")
    suspend fun siteConfig(): ApiResponse<SiteConfig>

    // ---- 认证 ----
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequest): ApiResponse<User>

    @POST("auth/register")
    suspend fun register(@Body body: RegisterRequest): ApiResponse<User>

    @POST("auth/logout")
    suspend fun logout(): ApiResponse<Unit>

    @GET("auth/me")
    suspend fun me(): ApiResponse<User>

    @POST("auth/send-reset-code")
    suspend fun sendResetCode(@Body body: SendResetCodeRequest): ApiResponse<Unit>

    @POST("auth/reset-password")
    suspend fun resetPassword(@Body body: ResetPasswordRequest): ApiResponse<Unit>

    // ---- 工作台 ----
    @GET("tasks/stats")
    suspend fun taskStats(): ApiResponse<DashboardStats>

    @GET("online-count")
    suspend fun onlineCount(): OnlineCount

    @GET("devices/models")
    suspend fun deviceModels(): ApiResponse<List<DeviceModel>>

    // ---- 身高 ----
    @GET("height")
    suspend fun myHeight(): ApiResponse<HeightInfo>

    @GET("height-ranking")
    suspend fun heightRanking(@Query("page") page: Int = 1): ApiResponse<List<HeightRankingEntry>>

    @POST("height-ranking/submit")
    suspend fun submitHeight(@Body body: HeightSubmitRequest): ApiResponse<Unit>

    // ---- 货币 ----
    @GET("currency")
    suspend fun currency(): ApiResponse<CurrencyInfo>

    @POST("currency/refresh")
    suspend fun refreshCurrency(): ApiResponse<CurrencyInfo>

    @GET("currency/forge-options")
    suspend fun forgeOptions(): ApiResponse<ForgeOptions>

    // ---- 账号 ----
    @GET("accounts")
    suspend fun accounts(): ApiResponse<List<Account>>

    @GET("accounts/add/status/{reference}")
    suspend fun accountAddStatus(@Path("reference") reference: String): ApiResponse<AccountAddStatus>

    @POST("accounts/add/start")
    suspend fun accountAddStart(@Body body: Map<String, Any?>): ApiResponse<AccountAddStatus>

    @GET("accounts/task-statuses")
    suspend fun accountTaskStatuses(): ApiResponse<List<AccountTaskStatus>>

    // ---- 设备 ----
    @GET("devices")
    suspend fun devices(): ApiResponse<List<Device>>

    @POST("devices/create")
    suspend fun createDevice(@Body body: Map<String, Any?>): ApiResponse<Device>

    // ---- 好友 ----
    @GET("friend-relations")
    suspend fun friends(): ApiResponse<List<Friend>>

    @GET("friend-code/list")
    suspend fun friendCodes(): ApiResponse<List<FriendCode>>

    // ---- 灵犀 / 心火 ----
    @GET("spirit-intimacy/options")
    suspend fun intimacyOptions(): ApiResponse<List<IntimacyOption>>

    @GET("spirit-shop/load-all")
    suspend fun spiritShop(): ApiResponse<SpiritShop>

    @GET("heart-trade/batches")
    suspend fun heartTradeBatches(): ApiResponse<List<com.skyauto.app.data.model.HeartTradeBatch>>

    // ---- 任务 / 排期 ----
    @GET("schedules")
    suspend fun schedules(): ApiResponse<List<Schedule>>

    @GET("tasks/submit")
    suspend fun submitTask(@Query("account_id") accountId: String, @Query("type") type: String): ApiResponse<Unit>

    // ---- 通知 / 反馈 ----
    @GET("notifications")
    suspend fun notifications(): ApiResponse<List<NotificationItem>>

    @POST("notifications/read-all")
    suspend fun readAllNotifications(): ApiResponse<Unit>

    @GET("feedback/tickets")
    suspend fun feedbackTickets(): ApiResponse<List<FeedbackTicket>>

    @GET("activities/active")
    suspend fun activeActivities(): ApiResponse<Unit>

    // ---- 聊天 ----
    @GET("chat/rooms")
    suspend fun chatRooms(): ApiResponse<List<ChatRoom>>

    @GET("chat/rooms/{roomId}/messages")
    suspend fun chatMessages(@Path("roomId") roomId: String): ApiResponse<List<ChatMessage>>

    // ---- AI ----
    @GET("deepseek/config")
    suspend fun aiConfig(): ApiResponse<AiConfig>

    @GET("deepseek/diagnose")
    suspend fun aiDiagnose(@Query("question") question: String): ApiResponse<AiDiagnoseResponse>
}