package com.skyauto.app.data.network

import com.skyauto.app.data.model.AccountAddStatus
import com.skyauto.app.data.model.AccountAddTask
import com.skyauto.app.data.model.AccountConfigRulesResponse
import com.skyauto.app.data.model.AccountCurrencyResponse
import com.skyauto.app.data.model.AccountTaskStatusesResponse
import com.skyauto.app.data.model.AccountsResponse
import com.skyauto.app.data.model.ActivitiesResponse
import com.skyauto.app.data.model.AiConfigResponse
import com.skyauto.app.data.model.AiDiagnoseRequest
import com.skyauto.app.data.model.AiDiagnoseResponse
import com.skyauto.app.data.model.AnnouncementsResponse
import com.skyauto.app.data.model.ApiResponse
import com.skyauto.app.data.model.AuthUserResponse
import com.skyauto.app.data.model.ChatMessagesResponse
import com.skyauto.app.data.model.ChatRoomsResponse
import com.skyauto.app.data.model.CreateDeviceRequest
import com.skyauto.app.data.model.DeviceModelsResponse
import com.skyauto.app.data.model.DevicesResponse
import com.skyauto.app.data.model.DoneTodayResponse
import com.skyauto.app.data.model.FeedbackTicketsResponse
import com.skyauto.app.data.model.ForgeOptionsResponse
import com.skyauto.app.data.model.FriendAbilityResponse
import com.skyauto.app.data.model.FriendCodesResponse
import com.skyauto.app.data.model.FriendRelationsResponse
import com.skyauto.app.data.model.FriendsResponse
import com.skyauto.app.data.model.GameInsightsPublicResponse
import com.skyauto.app.data.model.GameInsightsResponse
import com.skyauto.app.data.model.HeartFriendsResponse
import com.skyauto.app.data.model.HeartTradeResponse
import com.skyauto.app.data.model.HeightApiConfigResponse
import com.skyauto.app.data.model.HeightQueryResponse
import com.skyauto.app.data.model.HeightRankingResponse
import com.skyauto.app.data.model.HeightSubmitRequest
import com.skyauto.app.data.model.InvitationsResponse
import com.skyauto.app.data.model.LoginRequest
import com.skyauto.app.data.model.NotificationsResponse
import com.skyauto.app.data.model.OnlineCount
import com.skyauto.app.data.model.OperationsResponse
import com.skyauto.app.data.model.OrdersResponse
import com.skyauto.app.data.model.RegisterRequest
import com.skyauto.app.data.model.ResetPasswordRequest
import com.skyauto.app.data.model.RuntaskConfigResponse
import com.skyauto.app.data.model.SchedulesResponse
import com.skyauto.app.data.model.SendResetCodeRequest
import com.skyauto.app.data.model.SiteConfigResponse
import com.skyauto.app.data.model.SpiritIntimacyResponse
import com.skyauto.app.data.model.TaskHistoryResponse
import com.skyauto.app.data.model.TaskStatsResponse
import com.skyauto.app.data.model.TaskStatusResponse
import com.skyauto.app.data.model.WechatBindingCodeResponse
import com.skyauto.app.data.model.WechatStatusResponse
import com.skyauto.app.data.model.WorldQuestsResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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

    // ---- 公告 / 邀请 / 世界任务 / 运营数据 ----
    @GET("system-announcements")
    suspend fun systemAnnouncements(): AnnouncementsResponse

    @GET("invitations/me")
    suspend fun myInvitations(): InvitationsResponse

    @GET("world-quests")
    suspend fun worldQuests(): WorldQuestsResponse

    @GET("stats")
    suspend fun operationsStats(): OperationsResponse

    // ---- 游戏情报 ----
    @GET("game-insights/public")
    suspend fun gameInsightsPublic(): GameInsightsPublicResponse

    @GET("accounts/{id}/game-insights")
    suspend fun gameInsights(@Path("id") id: Long, @Query("refresh") refresh: Int? = null): GameInsightsResponse

    // ---- 货币合成 ----
    @GET("accounts/{id}/currency/forge-options")
    suspend fun forgeOptions(@Path("id") id: Long): ForgeOptionsResponse

    @POST("accounts/{id}/currency/forge")
    suspend fun forge(@Path("id") id: Long, @Body body: Map<String, Any?>): ApiResponse<Unit>

    @POST("accounts/{id}/currency/refresh")
    suspend fun refreshCurrency(@Path("id") id: Long): AccountCurrencyResponse

    // ---- 账号配置规则 ----
    @GET("account-config-rules")
    suspend fun accountConfigRules(): AccountConfigRulesResponse

    // ---- 任务执行详情 ----
    @GET("accounts/{id}/task-history")
    suspend fun taskHistory(@Path("id") id: Long): TaskHistoryResponse

    @GET("accounts/{id}/task-status")
    suspend fun taskStatus(@Path("id") id: Long): TaskStatusResponse

    @GET("accounts/{id}/done-today")
    suspend fun doneToday(@Path("id") id: Long): DoneTodayResponse

    @GET("accounts/{id}/runtask-config")
    suspend fun runtaskConfig(@Path("id") id: Long): RuntaskConfigResponse

    // ---- 身高（按账号） ----
    @GET("accounts/{id}/height")
    suspend fun accountHeight(@Path("id") id: Long): HeightQueryResponse

    @GET("accounts/{id}/height-api-config")
    suspend fun accountHeightApiConfig(@Path("id") id: Long): HeightApiConfigResponse

    // ---- 好友深度 ----
    @GET("accounts/{id}/friend-relations")
    suspend fun friendRelations(@Path("id") id: Long): FriendRelationsResponse

    @GET("accounts/{id}/friend-code/list")
    suspend fun friendCodeList(@Path("id") id: Long): FriendCodesResponse

    @GET("accounts/{id}/heart-friends")
    suspend fun heartFriends(@Path("id") id: Long): HeartFriendsResponse

    @GET("accounts/{id}/spirit-intimacy/options")
    suspend fun spiritIntimacyOptions(@Path("id") id: Long): SpiritIntimacyResponse

    @POST("accounts/{id}/spirit-intimacy/set")
    suspend fun spiritIntimacySet(@Path("id") id: Long, @Body body: Map<String, Any?>): ApiResponse<Unit>

    @GET("accounts/{id}/friend-ability/options")
    suspend fun friendAbilityOptions(@Path("id") id: Long): FriendAbilityResponse

    @POST("accounts/{id}/friend-ability/unlock-batch")
    suspend fun friendAbilityUnlock(@Path("id") id: Long, @Body body: Map<String, Any?>): ApiResponse<Unit>

    // ---- 聊天消息 ----
    @GET("chat/rooms/{id}/messages")
    suspend fun chatMessages(@Path("id") id: Long): ChatMessagesResponse

    // ---- 订单 / 微信绑定 ----
    @GET("orders")
    suspend fun orders(): OrdersResponse

    @GET("wechat/binding/status")
    suspend fun wechatStatus(): WechatStatusResponse

    // ---- 账号多渠道添加 ----
    @GET("accounts/add/status/{taskId}")
    suspend fun accountAddStatus(@Path("taskId") taskId: String): ApiResponse<AccountAddTask>

    @POST("accounts/add/submit_sms")
    suspend fun accountAddSubmitSms(@Body body: Map<String, Any?>): ApiResponse<Unit>

    @POST("accounts/add/cancel/{taskId}")
    suspend fun accountAddCancel(@Path("taskId") taskId: String): ApiResponse<Unit>

    // ---- 任务控制（按账号） ----
    @POST("accounts/{id}/tasks/stop_all")
    suspend fun stopAllTasks(@Path("id") id: Long): ApiResponse<Unit>

    @POST("accounts/{id}/tasks/reset")
    suspend fun resetTasks(@Path("id") id: Long, @Body body: Map<String, Any?>): ApiResponse<Unit>

    // ---- 定时计划（增 / 改 / 删） ----
    @POST("schedules")
    suspend fun createSchedule(@Body body: Map<String, Any?>): ApiResponse<Unit>

    @PUT("schedules/{id}")
    suspend fun updateSchedule(@Path("id") id: Long, @Body body: Map<String, Any?>): ApiResponse<Unit>

    @DELETE("schedules/{id}")
    suspend fun deleteSchedule(@Path("id") id: Long): ApiResponse<Unit>

    // ---- 微信绑定码 ----
    @POST("wechat/binding/code")
    suspend fun wechatBindingCode(): WechatBindingCodeResponse

    // ---- 好友码（生成 / 接受） ----
    @POST("accounts/{id}/friend-code")
    suspend fun generateFriendCode(@Path("id") id: Long, @Body body: Map<String, Any?>): ApiResponse<Unit>

    @POST("accounts/{id}/friend-code/accept")
    suspend fun acceptFriendCode(@Path("id") id: Long, @Body body: Map<String, Any?>): ApiResponse<Unit>

    // ---- 心火批次 ----
    @POST("heart-trade/batches")
    suspend fun createHeartBatch(@Body body: Map<String, Any?>): ApiResponse<Unit>

    // ---- 反馈工单（回复 / 关闭） ----
    @POST("feedback/tickets/{id}/messages")
    suspend fun replyTicket(@Path("id") id: String, @Body body: Map<String, Any?>): ApiResponse<Unit>

    @POST("feedback/tickets/{id}/close")
    suspend fun closeTicket(@Path("id") id: String): ApiResponse<Unit>
}
