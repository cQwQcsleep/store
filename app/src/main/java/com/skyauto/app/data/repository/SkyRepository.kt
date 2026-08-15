package com.skyauto.app.data.repository

import com.skyauto.app.data.model.Account
import com.skyauto.app.data.model.AccountTaskStatus
import com.skyauto.app.data.model.AiConfig
import com.skyauto.app.data.model.ChatRoom
import com.skyauto.app.data.model.CurrencyInfo
import com.skyauto.app.data.model.DashboardStats
import com.skyauto.app.data.model.Device
import com.skyauto.app.data.model.DeviceModel
import com.skyauto.app.data.model.FeedbackTicket
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
            // 站点登录响应不含用户数据，需再调用 me() 获取真实用户
            val me = api.me()
            val user = me.user ?: throw SkyApiError(me.message ?: "获取用户信息失败")
            session.onLogin(user)
            user
        }, "登录失败") { it }

    suspend fun register(email: String, username: String, password: String, verifyCode: String? = null, inviteCode: String? = null): Result<User> =
        safeSuspend({
            val resp = api.register(RegisterRequest(email, username, password, verifyCode, inviteCode))
            if (!resp.success) throw SkyApiError(resp.message ?: "注册失败")
            // 注册成功即自动登录，拉取用户信息
            val me = api.me()
            val user = me.user ?: throw SkyApiError(me.message ?: "获取用户信息失败")
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
        api.siteConfig().let { if (it.success) Result.success(it.data ?: SiteConfig()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    // ---- 工作台 ----
    suspend fun dashboardStats(): Result<DashboardStats> =
        api.taskStats().let { if (it.success) Result.success(it.data ?: DashboardStats()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    suspend fun onlineCount(): Result<Long> = runCatching { api.onlineCount().count }

    // ---- 身高 ----
    suspend fun myHeight(): Result<HeightInfo> =
        api.myHeight().let { if (it.success) Result.success(it.data ?: HeightInfo()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    suspend fun heightRanking(): Result<List<HeightRankingEntry>> =
        api.heightRanking().let { if (it.success) Result.success(it.data ?: emptyList()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    suspend fun submitHeight(height: Double): Result<Unit> =
        api.submitHeight(HeightSubmitRequest(height)).let { if (it.success) Result.success(Unit) else Result.failure(SkyApiError(it.message ?: "提交失败")) }

    // ---- 货币 ----
    suspend fun currency(): Result<CurrencyInfo> =
        api.currency().let { if (it.success) Result.success(it.data ?: CurrencyInfo()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    suspend fun refreshCurrency(): Result<CurrencyInfo> =
        api.refreshCurrency().let { if (it.success) Result.success(it.data ?: CurrencyInfo()) else Result.failure(SkyApiError(it.message ?: "刷新失败")) }

    // ---- 账号 ----
    suspend fun accounts(): Result<List<Account>> =
        api.accounts().let { if (it.success) Result.success(it.data ?: emptyList()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    suspend fun accountTaskStatuses(): Result<List<AccountTaskStatus>> =
        api.accountTaskStatuses().let { if (it.success) Result.success(it.data ?: emptyList()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    // ---- 设备 ----
    suspend fun devices(): Result<List<Device>> =
        api.devices().let { if (it.success) Result.success(it.data ?: emptyList()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    suspend fun deviceModels(): Result<List<DeviceModel>> =
        api.deviceModels().let { if (it.success) Result.success(it.data ?: emptyList()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    suspend fun createDevice(name: String?, model: String?): Result<Device> =
        api.createDevice(mapOf("name" to name, "model" to model)).let {
            if (it.success) Result.success(it.data ?: Device()) else Result.failure(SkyApiError(it.message ?: "创建失败"))
        }

    // ---- 好友 ----
    suspend fun friends(): Result<List<Friend>> =
        api.friends().let { if (it.success) Result.success(it.data ?: emptyList()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    suspend fun friendCodes(): Result<List<FriendCode>> =
        api.friendCodes().let { if (it.success) Result.success(it.data ?: emptyList()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    // ---- 灵犀 / 心火 ----
    suspend fun intimacyOptions(): Result<List<IntimacyOption>> =
        api.intimacyOptions().let { if (it.success) Result.success(it.data ?: emptyList()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    suspend fun spiritShop(): Result<SpiritShop> =
        api.spiritShop().let { if (it.success) Result.success(it.data ?: SpiritShop()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    suspend fun heartTradeBatches(): Result<List<com.skyauto.app.data.model.HeartTradeBatch>> =
        api.heartTradeBatches().let { if (it.success) Result.success(it.data ?: emptyList()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    // ---- 任务 / 排期 ----
    suspend fun schedules(): Result<List<Schedule>> =
        api.schedules().let { if (it.success) Result.success(it.data ?: emptyList()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    suspend fun submitTask(accountId: String, type: String): Result<Unit> =
        api.submitTask(accountId, type).let { if (it.success) Result.success(Unit) else Result.failure(SkyApiError(it.message ?: "提交失败")) }

    // ---- 通知 / 反馈 ----
    suspend fun notifications(): Result<List<NotificationItem>> =
        api.notifications().let { if (it.success) Result.success(it.data ?: emptyList()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    suspend fun readAllNotifications(): Result<Unit> =
        api.readAllNotifications().let { if (it.success) Result.success(Unit) else Result.failure(SkyApiError(it.message ?: "失败")) }

    suspend fun feedbackTickets(): Result<List<FeedbackTicket>> =
        api.feedbackTickets().let { if (it.success) Result.success(it.data ?: emptyList()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    // ---- 聊天 ----
    suspend fun chatRooms(): Result<List<ChatRoom>> =
        api.chatRooms().let { if (it.success) Result.success(it.data ?: emptyList()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    // ---- AI ----
    suspend fun aiConfig(): Result<AiConfig> =
        api.aiConfig().let { if (it.success) Result.success(it.data ?: AiConfig()) else Result.failure(SkyApiError(it.message ?: "加载失败")) }

    suspend fun aiDiagnose(question: String): Result<com.skyauto.app.data.model.AiDiagnoseResponse> =
        api.aiDiagnose(question).let { if (it.success) Result.success(it.data ?: com.skyauto.app.data.model.AiDiagnoseResponse()) else Result.failure(SkyApiError(it.message ?: "无回复")) }
}

class SkyApiError(message: String) : Exception(message)