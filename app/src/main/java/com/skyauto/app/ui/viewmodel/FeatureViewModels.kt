package com.skyauto.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyauto.app.data.model.Account
import com.skyauto.app.data.model.CurrencyInfo
import com.skyauto.app.data.model.DashboardStats
import com.skyauto.app.data.model.Device
import com.skyauto.app.data.model.DeviceModel
import com.skyauto.app.data.model.Friend
import com.skyauto.app.data.model.HeightInfo
import com.skyauto.app.data.model.HeightRankingEntry
import com.skyauto.app.data.model.NotificationItem
import com.skyauto.app.data.model.Schedule
import com.skyauto.app.data.repository.SkyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    private val _stats = MutableStateFlow<DashboardStats?>(null)
    val stats: StateFlow<DashboardStats?> = _stats.asStateFlow()
    private val _online = MutableStateFlow<Long?>(null)
    val online: StateFlow<Long?> = _online.asStateFlow()
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    init { load() }
    fun load() {
        _loading.value = true
        viewModelScope.launch {
            repo.dashboardStats().onSuccess { _stats.value = it }
            repo.onlineCount().onSuccess { _online.value = it }
            _loading.value = false
        }
    }
}

@HiltViewModel
class HeightViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val myHeight = MutableStateFlow<HeightInfo?>(null)
    val ranking = MutableStateFlow<List<HeightRankingEntry>>(emptyList())
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    fun load() {
        loading.value = true
        viewModelScope.launch {
            repo.myHeight().onSuccess { myHeight.value = it }
            repo.heightRanking().onSuccess { ranking.value = it }
            loading.value = false
        }
    }
    fun submit(height: Double) {
        viewModelScope.launch {
            repo.submitHeight(height)
                .onSuccess { message.value = "提交成功" }
                .onFailure { message.value = it.message ?: "提交失败" }
        }
    }
}

@HiltViewModel
class EconomyViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val currency = MutableStateFlow<CurrencyInfo?>(null)
    val accountName = MutableStateFlow<String?>(null)
    val hasAccount = MutableStateFlow(false)
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { load() }
    fun load() {
        loading.value = true
        viewModelScope.launch {
            repo.accounts()
                .onSuccess { accounts ->
                    val first = accounts.firstOrNull()
                    hasAccount.value = first != null
                    if (first != null) {
                        accountName.value = first.displayUsername ?: first.account ?: "账号 #${first.id}"
                        repo.accountCurrency(first.id ?: 0L)
                            .onSuccess { currency.value = it }
                            .onFailure { message.value = it.message }
                    } else {
                        currency.value = null
                    }
                }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }
    fun refresh() = load()
}

@HiltViewModel
class AccountsViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val accounts = MutableStateFlow<List<Account>>(emptyList())
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { load() }
    fun load() {
        loading.value = true
        viewModelScope.launch {
            repo.accounts()
                .onSuccess { accounts.value = it }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }
}

@HiltViewModel
class DevicesViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val devices = MutableStateFlow<List<Device>>(emptyList())
    val models = MutableStateFlow<List<DeviceModel>>(emptyList())
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { load() }
    fun load() {
        loading.value = true
        viewModelScope.launch {
            repo.devices().onSuccess { devices.value = it }
            repo.deviceModels().onSuccess { models.value = it }
            loading.value = false
        }
    }
    fun create(name: String?, model: String?) {
        viewModelScope.launch {
            repo.createDevice(name, model)
                .onSuccess { message.value = "设备创建成功"; load() }
                .onFailure { message.value = it.message ?: "创建失败" }
        }
    }
}

@HiltViewModel
class FriendsViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val friends = MutableStateFlow<List<Friend>>(emptyList())
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { load() }
    fun load() {
        loading.value = true
        viewModelScope.launch {
            repo.friends()
                .onSuccess { friends.value = it }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }
}

@HiltViewModel
class SpiritsViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val heartTrade = MutableStateFlow<com.skyauto.app.data.model.HeartTradeResponse?>(null)
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { load() }
    fun load() {
        loading.value = true
        viewModelScope.launch {
            repo.heartTrade()
                .onSuccess { heartTrade.value = it }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }
}

@HiltViewModel
class TasksViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val schedules = MutableStateFlow<List<Schedule>>(emptyList())
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { load() }
    fun load() {
        loading.value = true
        viewModelScope.launch {
            repo.schedules()
                .onSuccess { schedules.value = it }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }
    fun submit(accountId: Long, type: String) {
        viewModelScope.launch {
            repo.submitTask(accountId, type)
                .onSuccess { message.value = "任务已提交" }
                .onFailure { message.value = it.message }
        }
    }
}

@HiltViewModel
class NotificationsViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val items = MutableStateFlow<List<NotificationItem>>(emptyList())
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { load() }
    fun load() {
        loading.value = true
        viewModelScope.launch {
            repo.notifications()
                .onSuccess { items.value = it }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }
    fun readAll() {
        viewModelScope.launch {
            repo.readAllNotifications().onSuccess { load() }
        }
    }
}

@HiltViewModel
class ChatViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val rooms = MutableStateFlow<List<com.skyauto.app.data.model.ChatRoom>>(emptyList())
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { load() }
    fun load() {
        loading.value = true
        viewModelScope.launch {
            repo.chatRooms()
                .onSuccess { rooms.value = it }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repo: SkyRepository
) : ViewModel() {
    val message = MutableStateFlow<String?>(null)
    fun logout(onDone: () -> Unit) {
        viewModelScope.launch { repo.logout(); onDone() }
    }
}

data class AiChatMessage(val isUser: Boolean, val content: String)

@HiltViewModel
class AiViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val messages = MutableStateFlow<List<AiChatMessage>>(emptyList())
    val thinking = MutableStateFlow(false)

    fun send(text: String) {
        if (text.isBlank()) return
        messages.value = messages.value + AiChatMessage(isUser = true, content = text)
        thinking.value = true
        viewModelScope.launch {
            repo.aiDiagnose(text)
                .onSuccess { messages.value = messages.value + AiChatMessage(false, it.answer ?: "（无回复）") }
                .onFailure { messages.value = messages.value + AiChatMessage(false, "出错了：${it.message}") }
            thinking.value = false
        }
    }
}