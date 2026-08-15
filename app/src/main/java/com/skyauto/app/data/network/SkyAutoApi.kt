package com.skyauto.app.data.network

import com.skyauto.app.data.model.AccountAddStatus
import com.skyauto.app.data.model.AccountCurrencyResponse
import com.skyauto.app.data.model.AccountTaskStatusesResponse
import com.skyauto.app.data.model.AccountsResponse
import com.skyauto.app.data.model.ActivitiesResponse
import com.skyauto.app.data.model.AiConfigResponse
import com.skyauto.app.data.model.AiDiagnoseRequest
import com.skyauto.app.data.model.AiDiagnoseResponse
import com.skyauto.app.data.model.ApiResponse
import com.skyauto.app.data.model.AuthUserResponse
import com.skyauto.app.data.model.ChatRoomsResponse
import com.skyauto.app.data.model.CreateDeviceRequest
import com.skyauto.app.data.model.DeviceModelsResponse
import com.skyauto.app.data.model.DevicesResponse
import com.skyauto.app.data.model.FeedbackTicketsResponse
import com.skyauto.app.data.model.FriendsResponse
import com.skyauto.app.data.model.HeartTradeResponse
import com.skyauto.app.data.model.HeightRankingResponse
import com.skyauto.app.data.model.HeightSubmitRequest
import com.skyauto.app.data.model.LoginRequest
import com.skyauto.app.data.model.NotificationsResponse
import com.skyauto.app.data.model.OnlineCount
import com.skyauto.app.data.model.RegisterRequest
import com.skyauto.app.data.model.ResetPasswordRequest
import com.skyauto.app.data.model.SchedulesResponse
import com.skyauto.app.data.model.SendResetCodeRequest
import com.skyauto.app.data.model.SiteConfigResponse
import com.skyauto.app.data.model.TaskStatsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SkyAutoApi {

    // ---- 站点配置（公开） ----
    @GET("config/site")
    suspend fun siteConfig(): SiteConfigResponse

    // ---- 认证 ----
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequest): AuthUserResponse

    @POST("auth/register")
    suspend fun register(@Body body: RegisterRequest): AuthUserResponse

    @POST("auth/logout")
    suspend fun logout(): ApiResponse<Unit>

    @GET("auth/me")
    suspend fun me(): AuthUserResponse

    @POST("auth/send-reset-code")
    suspend fun sendResetCode(@Body body: SendResetCodeRequest): ApiResponse<Unit>

    @POST("auth/reset-password")
    suspend fun resetPassword(@Body body: ResetPasswordRequest): ApiResponse<Unit>

    // ---- 工作台 ----
    @GET("tasks/stats")
    suspend fun taskStats(): TaskStatsResponse

    @GET("online-count")
    suspend fun onlineCount(): OnlineCount

    // ---- 身高 ----
    @GET("height-ranking")
    suspend fun heightRanking(@Query("page") page: Int = 1): HeightRankingResponse

    @POST("height-ranking/submit")
    suspend fun submitHeight(@Body body: HeightSubmitRequest): ApiResponse<Unit>

    // ---- 货币（按账号） ----
    @GET("accounts/{id}/currency")
    suspend fun accountCurrency(@Path("id") id: Long): AccountCurrencyResponse

    // ---- 账号 ----
    @GET("accounts")
    suspend fun accounts(): AccountsResponse

    @POST("accounts/add/start")
    suspend fun accountAddStart(@Body body: Map<String, Any?>): ApiResponse<AccountAddStatus>

    @GET("accounts/task-statuses")
    suspend fun accountTaskStatuses(): AccountTaskStatusesResponse

    // ---- 设备 ----
    @GET("devices")
    suspend fun devices(): DevicesResponse

    @GET("devices/models")
    suspend fun deviceModels(): DeviceModelsResponse

    @POST("devices/create")
    suspend fun createDevice(@Body body: CreateDeviceRequest): ApiResponse<Unit>

    // ---- 好友 ----
    @GET("friends")
    suspend fun friends(): FriendsResponse

    // ---- 心火交易 ----
    @GET("heart-trade")
    suspend fun heartTrade(): HeartTradeResponse

    // ---- 任务 / 排期 ----
    @GET("schedules")
    suspend fun schedules(): SchedulesResponse

    @POST("tasks/submit")
    suspend fun submitTask(@Body body: Map<String, Any?>): ApiResponse<Unit>

    // ---- 通知 / 反馈 / 活动 ----
    @GET("notifications")
    suspend fun notifications(): NotificationsResponse

    @POST("notifications/read-all")
    suspend fun readAllNotifications(): ApiResponse<Unit>

    @GET("feedback/tickets")
    suspend fun feedbackTickets(): FeedbackTicketsResponse

    @GET("activities/active")
    suspend fun activeActivities(): ActivitiesResponse

    // ---- 聊天 ----
    @GET("chat/rooms")
    suspend fun chatRooms(): ChatRoomsResponse

    // ---- AI ----
    @GET("deepseek/config")
    suspend fun aiConfig(): AiConfigResponse

    @POST("deepseek/diagnose")
    suspend fun aiDiagnose(@Body body: AiDiagnoseRequest): AiDiagnoseResponse
}
