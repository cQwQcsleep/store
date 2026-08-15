package com.skyauto.app.data.repository

import com.skyauto.app.data.model.Account
import com.skyauto.app.data.model.AccountTaskStatus
import com.skyauto.app.data.model.AiConfig
import com.skyauto.app.data.model.AiDiagnoseResponse
import com.skyauto.app.data.model.ChatRoom
import com.skyauto.app.data.model.CurrencyInfo
import com.skyauto.app.data.model.DashboardStats
import com.skyauto.app.data.model.Device
import com.skyauto.app.data.model.DeviceModel
import com.skyauto.app.data.model.FeedbackTicket
import com.skyauto.app.data.model.Friend
import com.skyauto.app.data.model.HeartTradeBatch
import com.skyauto.app.data.model.HeartTradeResponse
import com.skyauto.app.data.model.HeightInfo
import com.skyauto.app.data.model.HeightRankingEntry
import com.skyauto.app.data.model.HeightSubmitRequest
import com.skyauto.app.data.model.LoginRequest
import com.skyauto.app.data.model.NotificationItem
import com.skyauto.app.data.model.RegisterRequest
import com.skyauto.app.data.model.ResetPasswordRequest
import com.skyauto.app.data.model.Schedule
import com.skyauto.app.data.model.SendResetCodeRequest
import com.skyauto.app.data.model.SiteConfig
import com.skyauto.app.data.model.User
import com.skyauto.app.data.network.SkyAutoApi
import com.skyauto.app.data.session.PersistentCookieJar
import com.skyauto.app.data.session.SessionManager
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SkyRepository @Inject constructor(
    private val api: SkyAutoApi,
    private val session: SessionManager,
    private val cookieJar: PersistentCookieJar
) {

    // ---- 认证 ----
    suspend fun login(email: String, password: String): Result<User> =
        safeSuspend({
            val resp = api.login(LoginRequest(email, password))
            if (!resp.success) throw SkyApiError(resp.message ?: "登录失败")
            val user = resp.user ?: run {
                val me = api.me()
                me.user ?: throw SkyApiError(me.message ?: "获取用户信息失败")
            }
            session.onLogin(user)
            user
        }, "登录失败") { it }

    suspend fun register(email: String, username: String, password: String, verifyCode: String? = null, inviteCode: String? = null): Result<User> =
        safeSuspend({
            val resp = api.register(RegisterRequest(email, username, password, verifyCode, inviteCode))
            if (!resp.success) throw SkyApiError(resp.message ?: "注册失败")
            val user = resp.user ?: run {
                val me = api.me()
                me.user ?: throw SkyApiError(me.message ?: "获取用户信息失败")
            }
            session.onLogin(user)
            user
        }, "注册失败") { it }

    suspend fun sendResetCode(email: String): Result<Unit> =
        safeSuspend({ api.sendResetCode(SendResetCodeRequest(email)) }, "发送失败") { resp ->
            if (resp.success) Unit else throw SkyApiError(resp.message ?: "发送失败")
        }

    suspend fun resetPassword(email: String, code: String, password: String): Result<Unit> =
        safeSuspend({ api.resetPassword(ResetPasswordRequest(email, code, password)) }, "重置失败") { resp ->
            if (resp.success) Unit else throw SkyApiError(resp.message ?: "重置失败")
        }

    suspend fun me(): Result<User> =
        safeSuspend({
            val resp = api.me()
            val user = resp.user ?: throw SkyApiError(resp.message ?: "未登录")
            session.onLogin(user)
            user
        }, "未登录") { it }

    /**
     * 统一异常安全的网络调用：业务失败抛 [SkyApiError]；
     * 非 2xx（如 401）由 Retrofit 抛 [HttpException]，此处解析其响应体中的 message，
     * 避免用户看到生硬的 "HTTP 401"，而是服务端返回的中文提示（如"账号或密码错误"）。
     */
    private suspend fun <T, R> safeSuspend(
        block: suspend () -> T,
        defaultMsg: String,
        onOk: (T) -> R
    ): Result<R> = try {
        Result.success(onOk(block()))
    } catch (e: SkyApiError) {
        Result.failure(e)
    } catch (e: Throwable) {
        Result.failure(SkyApiError(extractMessage(e, defaultMsg)))
    }

    private fun extractMessage(e: Throwable, default: String): String {
        if (e is HttpException) {
            runCatching {
                val body = e.response()?.errorBody()?.string()
                if (body != null) {
                    val msg = Json.parseToJsonElement(body)
                        .jsonObject["message"]?.jsonPrimitive?.contentOrNull
                    if (!msg.isNullOrBlank()) return msg
                }
            }
        }
        return e.message ?: default
    }

    suspend fun logout(): Result<Unit> {
        runCatching { api.logout() }
        session.onLogout()
        cookieJar.clear()
        return Result.success(Unit)
    }

    // ---- 配置 ----
    suspend fun siteConfig(): Result<SiteConfig> =
        api.siteConfig().let {
            if (it.success) Result.success(it.config ?: SiteConfig())
            else Result.failure(SkyApiError("加载失败"))
        }

    // ---- 工作台 ----
    suspend fun dashboardStats(): Result<DashboardStats> =
        api.taskStats().let {
            if (it.success) Result.success(it.stats ?: DashboardStats())
            else Result.failure(SkyApiError("加载失败"))
        }

    suspend fun onlineCount(): Result<Long> = runCatching { api.onlineCount().count }

    // ---- 身高 ----
    suspend fun myHeight(): Result<HeightInfo> = Result.success(
        session.currentUser.value?.let {
            HeightInfo(
                height = it.heightValue,
                label = it.heightLabel
            )
        } ?: HeightInfo()
    )

    suspend fun heightRanking(): Result<List<HeightRankingEntry>> =
        runCatching { api.heightRanking().records }

    suspend fun submitHeight(height: Double): Result<Unit> =
        api.submitHeight(HeightSubmitRequest(height)).let {
            if (it.success) Result.success(Unit)
            else Result.failure(SkyApiError(it.message ?: "提交失败"))
        }

    // ---- 货币（按账号） ----
    suspend fun accountCurrency(accountId: Long): Result<CurrencyInfo> =
        api.accountCurrency(accountId).let {
            if (it.success) Result.success(it.currency ?: CurrencyInfo())
            else Result.failure(SkyApiError("加载失败"))
        }

    // ---- 账号 ----
    suspend fun accounts(): Result<List<Account>> =
        api.accounts().let {
            if (it.success) Result.success(it.accounts)
            else Result.failure(SkyApiError("加载失败"))
        }

    suspend fun accountTaskStatuses(): Result<List<AccountTaskStatus>> =
        api.accountTaskStatuses().let {
            if (it.success) Result.success(it.statuses.values.toList())
            else Result.failure(SkyApiError("加载失败"))
        }

    // ---- 设备 ----
    suspend fun devices(): Result<List<Device>> =
        api.devices().let {
            if (it.success) Result.success(it.devices)
            else Result.failure(SkyApiError("加载失败"))
        }

    suspend fun deviceModels(): Result<List<DeviceModel>> =
        api.deviceModels().let {
            if (it.success) Result.success(it.android + it.ios)
            else Result.failure(SkyApiError("加载失败"))
        }

    suspend fun createDevice(name: String?, model: String?): Result<Unit> =
        api.createDevice(com.skyauto.app.data.model.CreateDeviceRequest(name, model)).let {
            if (it.success) Result.success(Unit)
            else Result.failure(SkyApiError(it.message ?: "创建失败"))
        }

    // ---- 好友 ----
    suspend fun friends(): Result<List<Friend>> =
        api.friends().let {
            if (it.success) Result.success(it.friends)
            else Result.failure(SkyApiError("加载失败"))
        }

    // ---- 心火交易 ----
    suspend fun heartTrade(): Result<HeartTradeResponse> =
        api.heartTrade().let {
            if (it.success) Result.success(it)
            else Result.failure(SkyApiError("加载失败"))
        }

    // ---- 任务 / 排期 ----
    suspend fun schedules(): Result<List<Schedule>> =
        api.schedules().let {
            if (it.success) Result.success(it.schedules)
            else Result.failure(SkyApiError("加载失败"))
        }

    suspend fun submitTask(accountId: Long, type: String): Result<Unit> =
        api.submitTask(mapOf("account_id" to accountId, "task_type" to type)).let {
            if (it.success) Result.success(Unit)
            else Result.failure(SkyApiError(it.message ?: "提交失败"))
        }

    // ---- 通知 / 反馈 / 活动 ----
    suspend fun notifications(): Result<List<NotificationItem>> =
        api.notifications().let {
            if (it.success) Result.success(it.notifications)
            else Result.failure(SkyApiError("加载失败"))
        }

    suspend fun readAllNotifications(): Result<Unit> =
        api.readAllNotifications().let {
            if (it.success) Result.success(Unit)
            else Result.failure(SkyApiError(it.message ?: "失败"))
        }

    suspend fun feedbackTickets(): Result<List<FeedbackTicket>> =
        api.feedbackTickets().let {
            if (it.success) Result.success(it.tickets)
            else Result.failure(SkyApiError("加载失败"))
        }

    suspend fun activeActivities(): Result<com.skyauto.app.data.model.ActivityItem> =
        api.activeActivities().let { resp ->
            if (resp.success) Result.success(resp.activities.firstOrNull() ?: com.skyauto.app.data.model.ActivityItem())
            else Result.failure(SkyApiError("加载失败"))
        }

    // ---- 聊天 ----
    suspend fun chatRooms(): Result<List<ChatRoom>> =
        api.chatRooms().let {
            if (it.success) Result.success(it.rooms)
            else Result.failure(SkyApiError("加载失败"))
        }

    // ---- AI ----
    suspend fun aiConfig(): Result<AiConfig> =
        api.aiConfig().let { Result.success(AiConfig(enabled = it.hasKey, model = it.model)) }

    suspend fun aiDiagnose(question: String): Result<AiDiagnoseResponse> =
        api.aiDiagnose(com.skyauto.app.data.model.AiDiagnoseRequest(question)).let {
            if (it.success) Result.success(it)
            else Result.failure(SkyApiError(it.message ?: "无回复"))
        }

    // ---- 公告 ----
    suspend fun systemAnnouncements(): Result<List<com.skyauto.app.data.model.AnnouncementFull>> =
        api.systemAnnouncements().let {
            if (it.success) Result.success(it.announcements)
            else Result.failure(SkyApiError("加载公告失败"))
        }

    // ---- 邀请 ----
    suspend fun myInvitations(): Result<com.skyauto.app.data.model.InvitationsResponse> =
        api.myInvitations().let {
            if (it.success) Result.success(it)
            else Result.failure(SkyApiError("加载邀请失败"))
        }

    // ---- 世界任务 ----
    suspend fun worldQuests(): Result<List<com.skyauto.app.data.model.WorldQuest>> =
        api.worldQuests().let {
            if (it.success) Result.success(it.quests)
            else Result.failure(SkyApiError("加载世界任务失败"))
        }

    // ---- 运营数据 ----
    suspend fun operationsStats(): Result<com.skyauto.app.data.model.OperationsResponse> =
        api.operationsStats().let {
            if (it.success) Result.success(it)
            else Result.failure(SkyApiError("加载运营数据失败"))
        }

    // ---- 游戏情报 ----
    suspend fun gameInsightsPublic(): Result<com.skyauto.app.data.model.GameInsightsPublicResponse> =
        api.gameInsightsPublic().let {
            if (it.success) Result.success(it)
            else Result.failure(SkyApiError("加载公开情报失败"))
        }

    suspend fun gameInsights(accountId: Long, refresh: Boolean = false): Result<com.skyauto.app.data.model.GameInsightsResponse> =
        api.gameInsights(accountId, if (refresh) 1 else null).let {
            if (it.success) Result.success(it)
            else Result.failure(SkyApiError("加载游戏情报失败"))
        }

    // ---- 货币合成 ----
    suspend fun forgeOptions(accountId: Long): Result<com.skyauto.app.data.model.ForgeOptionsResponse> =
        api.forgeOptions(accountId).let {
            if (it.success) Result.success(it)
            else Result.failure(SkyApiError("加载合成选项失败"))
        }

    suspend fun forge(accountId: Long, optionKey: String, count: Long): Result<Unit> =
        api.forge(accountId, mapOf("option_key" to optionKey, "count" to count)).let {
            if (it.success) Result.success(Unit)
            else Result.failure(SkyApiError(it.message ?: "合成失败"))
        }

    suspend fun refreshCurrency(accountId: Long): Result<CurrencyInfo> =
        api.refreshCurrency(accountId).let {
            if (it.success) Result.success(it.currency ?: CurrencyInfo())
            else Result.failure(SkyApiError("刷新失败"))
        }

    // ---- 账号配置规则 ----
    suspend fun accountConfigRules(): Result<com.skyauto.app.data.model.AccountConfigRulesResponse> =
        api.accountConfigRules().let {
            if (it.success) Result.success(it)
            else Result.failure(SkyApiError("加载配置规则失败"))
        }

    // ---- 任务执行详情 ----
    suspend fun taskHistory(accountId: Long): Result<List<com.skyauto.app.data.model.TaskRecord>> =
        api.taskHistory(accountId).let {
            if (it.success) Result.success(it.records)
            else Result.failure(SkyApiError("加载任务历史失败"))
        }

    suspend fun taskStatus(accountId: Long): Result<com.skyauto.app.data.model.TaskStatusResponse> =
        runCatching { api.taskStatus(accountId) }

    suspend fun doneToday(accountId: Long): Result<com.skyauto.app.data.model.DoneTodayResponse> =
        runCatching { api.doneToday(accountId) }

    suspend fun runtaskConfig(accountId: Long): Result<com.skyauto.app.data.model.RuntaskConfigResponse> =
        runCatching { api.runtaskConfig(accountId) }

    // ---- 身高（按账号） ----
    suspend fun accountHeight(accountId: Long): Result<com.skyauto.app.data.model.HeightQueryResponse> =
        api.accountHeight(accountId).let {
            if (it.success) Result.success(it)
            else Result.failure(SkyApiError("加载身高查询失败"))
        }

    suspend fun accountHeightApiConfig(accountId: Long): Result<com.skyauto.app.data.model.HeightApiConfigResponse> =
        runCatching { api.accountHeightApiConfig(accountId) }

    // ---- 好友深度 ----
    suspend fun friendRelations(accountId: Long): Result<List<com.skyauto.app.data.model.FriendRelation>> =
        api.friendRelations(accountId).let {
            if (it.success) Result.success(it.friends)
            else Result.failure(SkyApiError("加载好友关系失败"))
        }

    suspend fun friendCodeList(accountId: Long): Result<List<com.skyauto.app.data.model.FriendCodeInvite>> =
        api.friendCodeList(accountId).let { resp ->
            if (resp.success) Result.success(resp.items + (resp.data?.invites ?: emptyList()))
            else Result.failure(SkyApiError("加载好友码失败"))
        }

    suspend fun heartFriends(accountId: Long): Result<com.skyauto.app.data.model.HeartFriendsResponse> =
        api.heartFriends(accountId).let {
            if (it.success) Result.success(it)
            else Result.failure(SkyApiError("加载送心好友失败"))
        }

    suspend fun spiritIntimacyOptions(accountId: Long): Result<com.skyauto.app.data.model.SpiritIntimacyResponse> =
        api.spiritIntimacyOptions(accountId).let {
            if (it.success) Result.success(it)
            else Result.failure(SkyApiError("加载亲密选项失败"))
        }

    suspend fun spiritIntimacySet(accountId: Long, key: String, value: Long): Result<Unit> =
        api.spiritIntimacySet(accountId, mapOf("key" to key, "value" to value)).let {
            if (it.success) Result.success(Unit)
            else Result.failure(SkyApiError(it.message ?: "设置失败"))
        }

    suspend fun friendAbilityOptions(accountId: Long): Result<com.skyauto.app.data.model.FriendAbilityResponse> =
        api.friendAbilityOptions(accountId).let {
            if (it.success) Result.success(it)
            else Result.failure(SkyApiError("加载能力节点失败"))
        }

    suspend fun friendAbilityUnlock(accountId: Long, nodeId: String): Result<Unit> =
        api.friendAbilityUnlock(accountId, mapOf("node_id" to nodeId)).let {
            if (it.success) Result.success(Unit)
            else Result.failure(SkyApiError(it.message ?: "解锁失败"))
        }

    // ---- 聊天消息 ----
    suspend fun chatMessages(roomId: Long): Result<List<com.skyauto.app.data.model.ChatMessage>> =
        api.chatMessages(roomId).let {
            if (it.success) Result.success(it.messages)
            else Result.failure(SkyApiError("加载聊天消息失败"))
        }

    // ---- 订单 / 微信绑定 ----
    suspend fun orders(): Result<List<com.skyauto.app.data.model.Order>> =
        api.orders().let {
            if (it.success) Result.success(it.orders)
            else Result.failure(SkyApiError("加载订单失败"))
        }

    suspend fun wechatStatus(): Result<com.skyauto.app.data.model.WechatStatusResponse> =
        runCatching { api.wechatStatus() }

    // ---- 账号多渠道添加 ----
    suspend fun accountAddStart(
        platform: String,
        account: String? = null,
        password: String? = null,
        loginType: String? = null,
        extra: Map<String, Any?> = emptyMap()
    ): Result<com.skyauto.app.data.model.AccountAddStatus> =
        api.accountAddStart(
            mapOf(
                "platform" to platform,
                "account" to (account ?: ""),
                "password" to (password ?: ""),
                "login_type" to (loginType ?: ""),
            ) + extra
        ).let {
            if (it.success) Result.success(it.data ?: com.skyauto.app.data.model.AccountAddStatus())
            else Result.failure(SkyApiError(it.message ?: "发起添加失败"))
        }

    suspend fun accountAddStatus(taskId: String): Result<com.skyauto.app.data.model.AccountAddTask> =
        api.accountAddStatus(taskId).let { resp ->
            if (resp.success) {
                if (resp.data != null) Result.success(resp.data)
                else Result.failure(SkyApiError(resp.message ?: "任务处理中"))
            } else Result.failure(SkyApiError(resp.message ?: "查询失败"))
        }

    suspend fun accountAddSubmitSms(taskId: String, code: String): Result<Unit> =
        api.accountAddSubmitSms(mapOf("task_id" to taskId, "code" to code)).let {
            if (it.success) Result.success(Unit)
            else Result.failure(SkyApiError(it.message ?: "提交失败"))
        }

    suspend fun accountAddCancel(taskId: String): Result<Unit> =
        api.accountAddCancel(taskId).let {
            if (it.success) Result.success(Unit)
            else Result.failure(SkyApiError(it.message ?: "取消失败"))
        }
}

class SkyApiError(message: String) : Exception(message)
