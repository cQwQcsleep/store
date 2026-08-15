package com.skyauto.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyauto.app.data.model.Account
import com.skyauto.app.data.model.AccountConfigRulesResponse
import com.skyauto.app.data.model.AnnouncementFull
import com.skyauto.app.data.model.ChatMessage
import com.skyauto.app.data.model.ChatRoom
import com.skyauto.app.data.model.DoneTodayResponse
import com.skyauto.app.data.model.ForgeOption
import com.skyauto.app.data.model.FriendAbilityNode
import com.skyauto.app.data.model.FriendCodeInvite
import com.skyauto.app.data.model.FriendRelation
import com.skyauto.app.data.model.GameInsightsPublicResponse
import com.skyauto.app.data.model.GameInsightsResponse
import com.skyauto.app.data.model.HeartFriendsResponse
import com.skyauto.app.data.model.InvitationsResponse
import com.skyauto.app.data.model.OperationsResponse
import com.skyauto.app.data.model.SpiritIntimacyItem
import com.skyauto.app.data.model.TaskRecord
import com.skyauto.app.data.model.WorldQuest
import com.skyauto.app.data.repository.SkyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementsViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val announcements = MutableStateFlow<List<AnnouncementFull>>(emptyList())
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { load() }
    fun load() {
        loading.value = true
        viewModelScope.launch {
            repo.systemAnnouncements()
                .onSuccess { announcements.value = it }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }
}

@HiltViewModel
class InvitationsViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val data = MutableStateFlow<InvitationsResponse?>(null)
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { load() }
    fun load() {
        loading.value = true
        viewModelScope.launch {
            repo.myInvitations()
                .onSuccess { data.value = it }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }
}

@HiltViewModel
class WorldQuestsViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val quests = MutableStateFlow<List<WorldQuest>>(emptyList())
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { load() }
    fun load() {
        loading.value = true
        viewModelScope.launch {
            repo.worldQuests()
                .onSuccess { quests.value = it }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }
}

@HiltViewModel
class OperationsViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val stats = MutableStateFlow<OperationsResponse?>(null)
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { load() }
    fun load() {
        loading.value = true
        viewModelScope.launch {
            repo.operationsStats()
                .onSuccess { stats.value = it }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }
}

@HiltViewModel
class GameInsightsViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val accounts = MutableStateFlow<List<Account>>(emptyList())
    val selectedAccountId = MutableStateFlow<Long?>(null)
    val publicInsights = MutableStateFlow<GameInsightsPublicResponse?>(null)
    val insights = MutableStateFlow<GameInsightsResponse?>(null)
    val loading = MutableStateFlow(false)
    val refreshing = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { loadAll() }

    fun loadAll() {
        loading.value = true
        viewModelScope.launch {
            repo.gameInsightsPublic()
                .onSuccess { publicInsights.value = it }
                .onFailure { message.value = it.message }
            repo.accounts()
                .onSuccess { accountsList ->
                    accounts.value = accountsList
                    val target = selectedAccountId.value ?: accountsList.firstOrNull()?.id
                    if (target != null) loadAccount(target, false)
                }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }

    fun loadAccount(id: Long, refresh: Boolean) {
        if (refresh) refreshing.value = true
        viewModelScope.launch {
            repo.gameInsights(id, refresh)
                .onSuccess { insights.value = it }
                .onFailure { message.value = it.message }
            refreshing.value = false
        }
    }

    fun selectAccount(id: Long) {
        selectedAccountId.value = id
        loadAccount(id, false)
    }

    fun refresh() {
        selectedAccountId.value?.let { loadAccount(it, true) }
    }
}

@HiltViewModel
class ForgeViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val accounts = MutableStateFlow<List<Account>>(emptyList())
    val selectedAccountId = MutableStateFlow<Long?>(null)
    val options = MutableStateFlow<List<ForgeOption>>(emptyList())
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { loadAll() }

    fun loadAll() {
        loading.value = true
        viewModelScope.launch {
            repo.accounts()
                .onSuccess { accountsList ->
                    accounts.value = accountsList
                    val target = selectedAccountId.value ?: accountsList.firstOrNull()?.id
                    if (target != null) loadAccount(target)
                }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }

    fun loadAccount(id: Long) {
        selectedAccountId.value = id
        viewModelScope.launch {
            repo.forgeOptions(id)
                .onSuccess { options.value = it.options }
                .onFailure { message.value = it.message }
        }
    }

    fun forge(key: String, count: Long) {
        val id = selectedAccountId.value ?: return
        viewModelScope.launch {
            repo.forge(id, key, count)
                .onSuccess { message.value = "合成成功"; loadAccount(id) }
                .onFailure { message.value = it.message }
        }
    }
}

@HiltViewModel
class ConfigRulesViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val data = MutableStateFlow<AccountConfigRulesResponse?>(null)
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { load() }
    fun load() {
        loading.value = true
        viewModelScope.launch {
            repo.accountConfigRules()
                .onSuccess { data.value = it }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }
}

@HiltViewModel
class TaskHistoryViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val accounts = MutableStateFlow<List<Account>>(emptyList())
    val selectedAccountId = MutableStateFlow<Long?>(null)
    val records = MutableStateFlow<List<TaskRecord>>(emptyList())
    val doneToday = MutableStateFlow<DoneTodayResponse?>(null)
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { loadAll() }

    fun loadAll() {
        loading.value = true
        viewModelScope.launch {
            repo.accounts()
                .onSuccess { accountsList ->
                    accounts.value = accountsList
                    val target = selectedAccountId.value ?: accountsList.firstOrNull()?.id
                    if (target != null) loadAccount(target)
                }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }

    fun loadAccount(id: Long) {
        selectedAccountId.value = id
        viewModelScope.launch {
            repo.taskHistory(id)
                .onSuccess { records.value = it }
                .onFailure { message.value = it.message }
            repo.doneToday(id)
                .onSuccess { doneToday.value = it }
        }
    }
}

@HiltViewModel
class FriendDetailViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val accounts = MutableStateFlow<List<Account>>(emptyList())
    val selectedAccountId = MutableStateFlow<Long?>(null)
    val relations = MutableStateFlow<List<FriendRelation>>(emptyList())
    val heartFriends = MutableStateFlow<HeartFriendsResponse?>(null)
    val friendCodes = MutableStateFlow<List<FriendCodeInvite>>(emptyList())
    val spiritOptions = MutableStateFlow<List<SpiritIntimacyItem>>(emptyList())
    val spiritNotice = MutableStateFlow<String?>(null)
    val abilityNodes = MutableStateFlow<List<FriendAbilityNode>>(emptyList())
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { loadAll() }

    fun loadAll() {
        loading.value = true
        viewModelScope.launch {
            repo.accounts()
                .onSuccess { accountsList ->
                    accounts.value = accountsList
                    val target = selectedAccountId.value ?: accountsList.firstOrNull()?.id
                    if (target != null) loadAccount(target)
                }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }

    fun loadAccount(id: Long) {
        selectedAccountId.value = id
        viewModelScope.launch {
            repo.friendRelations(id).onSuccess { relations.value = it }.onFailure { message.value = it.message }
            repo.heartFriends(id).onSuccess { heartFriends.value = it }
            repo.friendCodeList(id).onSuccess { friendCodes.value = it }
            repo.spiritIntimacyOptions(id)
                .onSuccess { spiritOptions.value = it.spirits + it.options; spiritNotice.value = it.notice }
            repo.friendAbilityOptions(id).onSuccess { abilityNodes.value = it.nodes }
        }
    }

    fun setSpirit(key: String, value: Long) {
        val id = selectedAccountId.value ?: return
        viewModelScope.launch {
            repo.spiritIntimacySet(id, key, value)
                .onSuccess { message.value = "已设置"; loadAccount(id) }
                .onFailure { message.value = it.message }
        }
    }

    fun unlockAbility(nodeId: String) {
        val id = selectedAccountId.value ?: return
        viewModelScope.launch {
            repo.friendAbilityUnlock(id, nodeId)
                .onSuccess { message.value = "已解锁"; loadAccount(id) }
                .onFailure { message.value = it.message }
        }
    }
}

@HiltViewModel
class ChatMessagesViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val rooms = MutableStateFlow<List<ChatRoom>>(emptyList())
    val selectedRoomId = MutableStateFlow<Long?>(null)
    val messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val loading = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)

    init { loadAll() }

    fun loadAll() {
        loading.value = true
        viewModelScope.launch {
            repo.chatRooms()
                .onSuccess { roomsList ->
                    rooms.value = roomsList
                    val target = selectedRoomId.value ?: roomsList.firstOrNull()?.id
                    if (target != null) loadRoom(target)
                }
                .onFailure { message.value = it.message }
            loading.value = false
        }
    }

    fun loadRoom(id: Long) {
        selectedRoomId.value = id
        viewModelScope.launch {
            repo.chatMessages(id)
                .onSuccess { messages.value = it }
                .onFailure { message.value = it.message }
        }
    }
}

@HiltViewModel
class AccountAddViewModel @Inject constructor(private val repo: SkyRepository) : ViewModel() {
    val adding = MutableStateFlow(false)
    val taskId = MutableStateFlow<String?>(null)
    val logs = MutableStateFlow<List<com.skyauto.app.data.model.AccountAddLog>>(emptyList())
    val smsTarget = MutableStateFlow<String?>(null)
    val smsContent = MutableStateFlow<String?>(null)
    val needHuaweiTrust = MutableStateFlow(false)
    val needHuaweiCaptcha = MutableStateFlow(false)
    val needBilibiliBrowser = MutableStateFlow(false)
    val needHonorBrowser = MutableStateFlow(false)
    val needVivoSubaccount = MutableStateFlow(false)
    val needUcAccount = MutableStateFlow(false)
    val image = MutableStateFlow<String?>(null)
    val message = MutableStateFlow<String?>(null)

    fun start(platform: String, account: String, password: String, loginType: String?) {
        adding.value = true
        message.value = null
        viewModelScope.launch {
            repo.accountAddStart(platform, account.ifBlank { null }, password.ifBlank { null }, loginType)
                .onSuccess { status ->
                    message.value = status.message ?: "添加任务已发起"
                    status.reference?.let { taskId.value = it }
                    poll()
                }
                .onFailure { message.value = it.message; adding.value = false }
        }
    }

    fun poll() {
        val tid = taskId.value ?: return
        viewModelScope.launch {
            repo.accountAddStatus(tid)
                .onSuccess { task ->
                    logs.value = task.logs
                    smsTarget.value = task.smsTarget
                    smsContent.value = task.smsContent
                    needHuaweiTrust.value = task.needHuaweiTrust
                    needHuaweiCaptcha.value = task.needHuaweiCaptcha
                    needBilibiliBrowser.value = task.needBilibiliBrowser
                    needHonorBrowser.value = task.needHonorBrowser
                    needVivoSubaccount.value = task.needVivoSubaccount
                    needUcAccount.value = task.needUcAccount
                    image.value = task.image
                    message.value = task.message
                    adding.value = task.status == "processing" || task.status == "pending"
                }
                .onFailure { message.value = it.message; adding.value = false }
        }
    }

    fun submitSms(code: String) {
        val tid = taskId.value ?: return
        viewModelScope.launch {
            repo.accountAddSubmitSms(tid, code)
                .onSuccess { message.value = "验证码已提交"; poll() }
                .onFailure { message.value = it.message }
        }
    }

    fun cancel() {
        val tid = taskId.value ?: return
        viewModelScope.launch {
            repo.accountAddCancel(tid)
                .onSuccess { message.value = "已取消"; adding.value = false; taskId.value = null }
                .onFailure { message.value = it.message }
        }
    }
}
