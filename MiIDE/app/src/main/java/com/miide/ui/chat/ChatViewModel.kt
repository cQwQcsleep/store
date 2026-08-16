package com.miide.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miide.core.data.repository.ConversationRepository
import com.miide.core.data.repository.ProviderRepository
import com.miide.core.data.settings.PreferencesManager
import com.miide.core.model.ChatConversation
import com.miide.core.model.ChatMessage
import com.miide.core.model.ChatRole
import com.miide.core.model.MessageStatus
import com.miide.core.model.ProviderConfig
import com.miide.core.network.ProviderEvent
import com.miide.core.network.ProviderGateway
import com.miide.core.network.ProviderRequest
import com.miide.core.network.ProviderResult
import com.miide.ui.editor.EditorBridge
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/** 一次工具调用（用于过程可见）。 */
data class ToolCallInfo(val id: String?, val name: String?, val arguments: String)

/** 用量信息。 */
data class UsageInfo(
    val promptTokens: Long,
    val completionTokens: Long,
    val cachedTokens: Long,
    val totalTokens: Long
)

/** 对话界面状态。 */
data class ChatUiState(
    val providers: List<ProviderConfig> = emptyList(),
    val activeProvider: ProviderConfig? = null,
    val messages: List<ChatMessage> = emptyList(),
    val isStreaming: Boolean = false,
    val currentToolCalls: List<ToolCallInfo> = emptyList(),
    val usage: UsageInfo? = null,
    val error: String? = null,
    /** 是否已连接编辑器（决定是否显示「插入编辑器」按钮）。 */
    val editorAttached: Boolean = false,
    /** 建议队列：AI 工作时用户发送的修改意见，本轮结束后自动处理。 */
    val pendingSuggestions: List<String> = emptyList()
) {
    val activeModelId: String?
        get() = activeProvider?.defaultModelId
            ?: activeProvider?.models?.firstOrNull { it.enabled }?.id
}

/**
 * AI 对话 ViewModel（Activity 级共享，底部面板与全屏共用）。
 *
 * 负责：选择活动供应商 → 发送消息 → 流式接收（正文/思考/工具/用量）→ 持久化到 Room。
 * AI 工作过程全程可见：思考增量、工具调用、用量实时更新。
 */
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val providerRepository: ProviderRepository,
    private val conversationRepository: ConversationRepository,
    preferencesManager: PreferencesManager,
    private val gateway: ProviderGateway
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    private var streamingJob: Job? = null
    private var conversationId: String? = null
    private var assistantId: String? = null

    init {
        viewModelScope.launch {
            combine(
                providerRepository.observeAll(),
                preferencesManager.preferences
            ) { providers, prefs -> providers to prefs.defaultProviderId }
                .collect { (providers, defaultId) ->
                    val active = providers.firstOrNull { it.id == defaultId && it.enabled }
                        ?: providers.firstOrNull { it.enabled }
                    _uiState.update { it.copy(providers = providers, activeProvider = active) }
                }
        }
        // 同步编辑器连接状态（用于「插入编辑器」按钮显隐）
        viewModelScope.launch {
            EditorBridge.attached.collect { attached ->
                _uiState.update { it.copy(editorAttached = attached) }
            }
        }
    }

    /**
     * 发送用户消息。
     *
     * AI 正在工作时（流式进行中）发送的内容会进入建议队列（非阻塞、不打断），
     * 本轮结束后按顺序自动处理。
     */
    fun send(text: String) {
        val trimmed = text.trim()
        if (trimmed.isEmpty()) return
        if (_uiState.value.isStreaming) {
            // AI 工作中：入队，不打断
            _uiState.update {
                it.copy(pendingSuggestions = it.pendingSuggestions + trimmed, error = null)
            }
            return
        }
        doSend(trimmed)
    }

    /** 实际发送一条消息并启动流式回复。 */
    private fun doSend(text: String) {
        val st = _uiState.value
        val provider = st.activeProvider ?: run {
            _uiState.update { it.copy(error = "未配置可用的 AI 供应商，请先到「AI 设置」中添加") }
            return
        }
        val modelId = st.activeModelId ?: run {
            _uiState.update { it.copy(error = "供应商未配置模型，请到「AI 设置」中补充") }
            return
        }

        viewModelScope.launch {
            // 首次发送时创建会话
            val cid = conversationId ?: run {
                val conv = ChatConversation(
                    title = text.take(20),
                    providerConfigId = provider.id,
                    modelId = modelId
                )
                conversationRepository.upsertConversation(conv)
                conversationId = conv.id
                conv.id
            }

            // 用户消息（持久化）
            val userMsg = ChatMessage(role = ChatRole.USER, content = text)
            conversationRepository.upsertMessage(cid, userMsg)
            _uiState.update { it.copy(messages = it.messages + userMsg, error = null) }

            // 助手占位（流式期间不落库，完成后落库）
            val assistant = ChatMessage(
                role = ChatRole.ASSISTANT,
                content = "",
                status = MessageStatus.STREAMING,
                modelId = modelId
            )
            assistantId = assistant.id
            _uiState.update {
                it.copy(
                    messages = it.messages + assistant,
                    isStreaming = true,
                    currentToolCalls = emptyList(),
                    usage = null,
                    error = null
                )
            }

            val history = _uiState.value.messages
            streamingJob = viewModelScope.launch {
                val result = try {
                    gateway.chat(
                        provider,
                        ProviderRequest(modelId = modelId, messages = history, stream = true),
                        onEvent = { event -> handleEvent(event) }
                    )
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    ProviderResult(false, e.message ?: "请求失败")
                }
                finishStream(result, cid)
            }
        }
    }

    /** 中断生成（标记为 INTERRUPTED 并落库）。 */
    fun stop() {
        if (!_uiState.value.isStreaming) return
        streamingJob?.cancel()
        streamingJob = null
        val id = assistantId ?: run {
            _uiState.update { it.copy(isStreaming = false) }
            return
        }
        finalizeAssistant(id) { it.copy(status = MessageStatus.INTERRUPTED) }
        _uiState.update { it.copy(isStreaming = false) }
        drainPending()
    }

    /** 把 AI 生成的文本插入到编辑器光标处；返回是否已插入。 */
    fun insertToEditor(text: String): Boolean {
        if (!_uiState.value.editorAttached || text.isBlank()) return false
        EditorBridge.insertAtCursor(text)
        return true
    }

    private fun handleEvent(event: ProviderEvent) {
        when (event) {
            is ProviderEvent.Delta -> updateAssistant { msg ->
                msg.copy(content = msg.content + event.content)
            }
            is ProviderEvent.ReasoningDelta -> updateAssistant { msg ->
                msg.copy(reasoning = (msg.reasoning ?: "") + event.content)
            }
            is ProviderEvent.ToolCall -> _uiState.update {
                it.copy(currentToolCalls = it.currentToolCalls + ToolCallInfo(event.id, event.name, event.arguments))
            }
            is ProviderEvent.Usage -> _uiState.update {
                it.copy(usage = UsageInfo(event.promptTokens, event.completionTokens, event.cachedTokens, event.totalTokens))
            }
            is ProviderEvent.Error -> _uiState.update { it.copy(error = event.message) }
            ProviderEvent.Done -> Unit
        }
    }

    private fun updateAssistant(transform: (ChatMessage) -> ChatMessage) {
        val id = assistantId ?: return
        _uiState.update { st ->
            val idx = st.messages.indexOfLast { it.id == id }
            if (idx < 0) st else st.copy(messages = st.messages.toMutableList().also { it[idx] = transform(it[idx]) })
        }
    }

    private fun finishStream(result: ProviderResult, cid: String) {
        if (result.success) {
            finalizeAssistant(assistantId ?: return) { it.copy(status = MessageStatus.COMPLETED) }
        } else {
            _uiState.update { it.copy(error = result.error ?: "请求失败") }
            finalizeAssistant(assistantId ?: return) {
                it.copy(status = MessageStatus.ERROR, error = result.error ?: "请求失败")
            }
        }
        _uiState.update { it.copy(isStreaming = false) }
        assistantId = null
        // 本轮结束，按顺序处理建议队列
        drainPending()
    }

    /** 本轮结束后处理建议队列：取出第一条按顺序发送。 */
    private fun drainPending() {
        val pending = _uiState.value.pendingSuggestions
        if (pending.isEmpty() || _uiState.value.isStreaming) return
        _uiState.update { it.copy(pendingSuggestions = it.pendingSuggestions.drop(1)) }
        doSend(pending.first())
    }

    /** 将流式消息落库并标记完成。 */
    private fun finalizeAssistant(id: String, transform: (ChatMessage) -> ChatMessage) {
        val final = _uiState.value.messages
            .firstOrNull { it.id == id }
            ?.let(transform) ?: return
        _uiState.update { st ->
            st.copy(messages = st.messages.map { if (it.id == id) final else it })
        }
        viewModelScope.launch {
            conversationId?.let { cid -> conversationRepository.upsertMessage(cid, final) }
        }
    }
}
