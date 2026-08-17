package com.miide.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.os.SystemClock
import com.miide.core.data.model.AppPreferences
import com.miide.core.data.repository.ConversationRepository
import com.miide.core.data.repository.ProviderRepository
import com.miide.core.data.settings.PreferencesManager
import com.miide.core.model.BudgetPolicy
import com.miide.core.model.ChatConversation
import com.miide.core.model.ChatMessage
import com.miide.core.model.ChatRole
import com.miide.core.model.MessageStatus
import com.miide.core.model.ProviderConfig
import com.miide.core.model.ToolCall
import com.miide.core.network.AggregateGateway
import com.miide.core.network.ProviderEvent
import com.miide.core.network.ProviderRequest
import com.miide.core.network.ProviderResult
import com.miide.tools.ToolExecutor
import com.miide.ui.editor.EditorBridge
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/** 一次工具调用的过程状态（用于过程可见）。 */
data class ToolCallInfo(
    val id: String?,
    val name: String?,
    val arguments: String,
    /** 执行状态：等待 / 运行中 / 成功 / 失败。 */
    val status: ToolCallStatus = ToolCallStatus.PENDING,
    /** 执行结果预览（过程可见）。 */
    val result: String? = null
)

/** 工具调用执行状态。 */
enum class ToolCallStatus { PENDING, RUNNING, SUCCESS, FAILED }

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
    val pendingSuggestions: List<String> = emptyList(),
    /** 实时动作流：AI 正在/最近做了什么（现场直播，防发呆）。 */
    val activityLog: List<ActivityEntry> = emptyList(),
    /** 本轮耗时（毫秒）。 */
    val elapsedMs: Long = 0,
    /** 跑马灯：AI 全部输出（思考+正文+动作）实时滚过。 */
    val tickerText: String = "",
    /** 待确认的 diff 预览（AI 建议的编辑）。 */
    val pendingDiff: PendingDiff? = null,
    /** 是否已暂停生成（暂停/继续控制）。 */
    val isPaused: Boolean = false
) {
    val activeModelId: String?
        get() = activeProvider?.defaultModelId
            ?: activeProvider?.models?.firstOrNull { it.enabled }?.id

    /** 工作台状态文案：正在调用工具 / 正在思考 / 正在回复 / 已暂停 / 空闲。 */
    val currentStatus: String
        get() = if (isPaused) {
            "已暂停"
        } else if (isStreaming) {
            if (currentToolCalls.any { it.status == ToolCallStatus.RUNNING }) "正在调用工具"
            else if (!messages.lastOrNull()?.reasoning.isNullOrEmpty()) "正在思考"
            else "正在回复"
        } else {
            "空闲"
        }
}

/**
 * AI 对话 ViewModel（Activity 级共享，底部面板与全屏共用）。
 *
 * 负责：选择活动供应商 → 发送消息 → 流式接收（正文/思考/工具/用量）→ 执行工具调用循环 → 持久化。
 * AI 工作过程全程可见：思考增量、工具调用及结果、用量实时更新。
 *
 * function calling 循环：
 *   模型返回工具调用 → 执行工具 → 把工具结果回传给模型 → 模型继续回复，直到无更多工具调用。
 */
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val providerRepository: ProviderRepository,
    private val conversationRepository: ConversationRepository,
    preferencesManager: PreferencesManager,
    private val aggregateGateway: AggregateGateway,
    private val toolExecutor: ToolExecutor
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    private var streamingJob: Job? = null
    private var conversationId: String? = null
    private var assistantId: String? = null

    /** 完整模型上下文（含工具消息，与展示用的 messages 分离）。 */
    private val contextHistory = mutableListOf<ChatMessage>()

    /** 本轮开始时的时间戳（用于耗时计算）。 */
    private var elapsedStartMs: Long = 0L

    /** 耗时刷新定时器（每秒更新一次 elapsedMs）。 */
    private var elapsedJob: Job? = null

    /** 跑马灯累积器（本轮全部输出）。 */
    private val tickerAccumulator = StringBuilder()

    /** 本轮开始前编辑器内容的快照（用于 diff 预览的对比与回滚）。 */
    private var editorSnapshot: String? = null

    /** 向动作流和跑马灯追加一条记录。 */
    private fun addActivity(kind: ActivityKind, text: String) {
        val entry = ActivityEntry(kind, text)
        _uiState.update { it.copy(activityLog = (it.activityLog + entry).takeLast(50)) }
        appendTicker("[${kind.name}] $text")
    }

    private fun appendTicker(text: String) {
        if (text.isBlank()) return
        tickerAccumulator.append(text).append('\n')
        if (tickerAccumulator.length > TICKER_MAX_CHARS) {
            // 只保留尾部（跑马灯始终滚动最近输出）
            val keep = tickerAccumulator.substring(tickerAccumulator.length - TICKER_MAX_CHARS)
            tickerAccumulator.setLength(0)
            tickerAccumulator.append(keep)
        }
        _uiState.update { it.copy(tickerText = tickerAccumulator.toString()) }
    }

    private fun startElapsedTimer() {
        elapsedStartMs = SystemClock.elapsedRealtime()
        elapsedJob?.cancel()
        elapsedJob = viewModelScope.launch {
            while (true) {
                kotlinx.coroutines.delay(1000)
                val elapsed = SystemClock.elapsedRealtime() - elapsedStartMs
                _uiState.update { it.copy(elapsedMs = elapsed) }
            }
        }
    }

    private fun stopElapsedTimer() {
        elapsedJob?.cancel()
        elapsedJob = null
        val elapsed = SystemClock.elapsedRealtime() - elapsedStartMs
        _uiState.update { it.copy(elapsedMs = elapsed) }
    }

    /** 当前限额策略（从偏好读取，随配置更新）。 */
    private var currentBudgetPolicy: BudgetPolicy = BudgetPolicy.Disabled

    init {
        viewModelScope.launch {
            combine(
                providerRepository.observeAll(),
                preferencesManager.preferences
            ) { providers, prefs ->
                Triple(
                    providers,
                    prefs.defaultProviderId,
                    BudgetPolicy(prefs.budgetDailyTokenLimit, prefs.budgetMonthlyCostUsd)
                )
            }.collect { (providers, defaultId, budget) ->
                val active = providers.firstOrNull { it.id == defaultId && it.enabled }
                    ?: providers.firstOrNull { it.enabled }
                currentBudgetPolicy = budget
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
     * AI 正在工作时（流式进行中）：
     * - 若内容命中重定向指令（如「别改 A，先改 B」「换个方案」）→ 中途打断并重定向，立即响应；
     * - 否则作为普通意见进入建议队列（非阻塞、不打断），本轮结束后按顺序自动处理。
     */
    fun send(text: String) {
        val trimmed = text.trim()
        if (trimmed.isEmpty()) return
        if (_uiState.value.isStreaming) {
            if (isRedirectDirective(trimmed)) {
                redirect(trimmed)
            } else {
                // AI 工作中（普通意见）：入队，不打断
                _uiState.update {
                    it.copy(pendingSuggestions = it.pendingSuggestions + trimmed, error = null)
                }
                addActivity(ActivityKind.QUEUE, "意见已入队：${trimmed.take(40)}")
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

            // 用户消息（持久化 + 入上下文）
            val userMsg = ChatMessage(role = ChatRole.USER, content = text)
            conversationRepository.upsertMessage(cid, userMsg)
            contextHistory.add(userMsg)
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
                    isPaused = false,
                    currentToolCalls = emptyList(),
                    usage = null,
                    error = null,
                    elapsedMs = 0L,
                    tickerText = ""
                )
            }
            tickerAccumulator.setLength(0)
            // 快照编辑器内容（用于 diff 预览和回滚）
            editorSnapshot = editorCurrentText()
            startElapsedTimer()

            streamingJob = viewModelScope.launch {
                val result = try {
                    runToolLoop(provider, modelId, cid)
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    ProviderResult(false, e.message ?: "请求失败")
                }
                finishStream(result, cid)
            }
        }
    }

    /**
     * function calling 循环：请求模型 → 有工具调用则执行并回传 → 继续，直到无工具调用或超轮次。
     */
    private suspend fun runToolLoop(
        provider: ProviderConfig,
        modelId: String,
        cid: String
    ): ProviderResult {
        val allToolCalls = mutableListOf<ToolCall>()

        repeat(MAX_TOOL_ROUNDS) { round ->
            val roundCalls = mutableListOf<ToolCall>()

            val result = try {
                // 聚合网关：候选列表（active 优先 + 其余启用供应商后备），失败自动切换，超限直接拒绝
                aggregateGateway.chat(
                    candidates = candidateProviders(provider),
                    request = ProviderRequest(
                        modelId = modelId,
                        messages = contextHistory.toList(),
                        tools = toolExecutor.specs(),
                        stream = true
                    ),
                    policy = currentBudgetPolicy,
                    onEvent = { event ->
                        // 暂停闸门：暂停期间挂起，网络读取随之暂停（先看完现状再决定）
                        awaitResumeIfPaused()
                        when (event) {
                            is ProviderEvent.ToolCall -> {
                                val tc = ToolCall(event.id, event.name, event.arguments)
                                roundCalls.add(tc)
                                allToolCalls.add(tc)
                                val (kind, desc) = describeTool(event.name.orEmpty())
                                addActivity(kind, desc)
                                _uiState.update {
                                    it.copy(currentToolCalls = it.currentToolCalls + ToolCallInfo(tc.id, tc.name, tc.arguments))
                                }
                            }
                            else -> handleEvent(event)
                        }
                    }
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                ProviderResult(false, e.message ?: "请求失败")
            }

            if (!result.success) return result

            if (roundCalls.isEmpty()) {
                // 本轮无工具调用：结束
                return result
            }

            // 助手消息（含工具调用）入上下文并持久化
            val assistantWithCalls = _uiState.value.messages
                .firstOrNull { it.id == assistantId }
                ?.copy(toolCalls = allToolCalls.toList())
                ?: return result
            contextHistory.add(assistantWithCalls)
            conversationRepository.upsertMessage(cid, assistantWithCalls)

            // 执行工具，把结果作为 tool 消息回传
            for (tc in roundCalls) {
                markToolRunning(tc)
                val output = toolExecutor.execute(tc.name.orEmpty(), tc.arguments)
                markToolDone(tc, output)
                val succeeded = !output.startsWith("错误") && !output.startsWith("[工具")
                addActivity(
                    if (succeeded) ActivityKind.DONE else ActivityKind.STOPPED,
                    if (succeeded) "✓ ${tc.name} 完成" else "✗ ${tc.name} 失败"
                )
                appendTicker(output.take(200))

                val toolMsg = ChatMessage(
                    role = ChatRole.TOOL,
                    content = output,
                    toolCallId = tc.id
                )
                contextHistory.add(toolMsg)
                conversationRepository.upsertMessage(cid, toolMsg)
            }
            // 下一轮：同一占位继续累积正文
        }
        return ProviderResult(success = true)
    }

    /** 中断生成（标记为 INTERRUPTED 并落库）。 */
    fun stop() {
        if (!_uiState.value.isStreaming) return
        streamingJob?.cancel()
        streamingJob = null
        stopElapsedTimer()
        addActivity(ActivityKind.STOPPED, "已停止生成")
        val id = assistantId ?: run {
            _uiState.update { it.copy(isStreaming = false, isPaused = false) }
            return
        }
        finalizeAssistant(id) { it.copy(status = MessageStatus.INTERRUPTED) }
        _uiState.update { it.copy(isStreaming = false, isPaused = false) }
        // 已停止生成：不再自动消费建议队列，让用户决定是否清空或另发新指令
    }

    /** 从建议队列移除指定位置的意见（用于队列明细逐条删除）。 */
    fun removeSuggestion(index: Int) {
        _uiState.update { st ->
            if (index in st.pendingSuggestions.indices) {
                st.copy(pendingSuggestions = st.pendingSuggestions.toMutableList().also { it.removeAt(index) })
            } else st
        }
    }

    /** 清空建议队列。 */
    fun clearPending() {
        _uiState.update { it.copy(pendingSuggestions = emptyList()) }
        addActivity(ActivityKind.STOPPED, "已清空排队中的意见")
    }

    /** 暂停生成：流式事件在此挂起，网络读取一并暂停（协作式闸门）。 */
    fun pause() {
        if (!_uiState.value.isStreaming || _uiState.value.isPaused) return
        _uiState.update { it.copy(isPaused = true) }
        addActivity(ActivityKind.STOPPED, "已暂停，可继续")
    }

    /** 继续生成：解除暂停标记，唤醒挂起的事件处理。 */
    fun resume() {
        if (!_uiState.value.isPaused) return
        _uiState.update { it.copy(isPaused = false) }
        addActivity(ActivityKind.THINK, "继续生成…")
    }

    /**
     * 中途打断并重定向：立即中断当前生成（保留已输出部分并标记为已中断），
     * 用新指令开启新一轮。适用于「别改 A，先改 B」「换个方案」等即时指令。
     * 建议队列保持不变，待本轮结束后按顺序处理。
     */
    private fun redirect(text: String) {
        streamingJob?.cancel()
        streamingJob = null
        stopElapsedTimer()
        addActivity(ActivityKind.STOPPED, "已打断，按新指令重定向")
        val id = assistantId
        if (id != null) {
            finalizeAssistant(id) { it.copy(status = MessageStatus.INTERRUPTED) }
        }
        assistantId = null
        _uiState.update {
            it.copy(
                isStreaming = false,
                isPaused = false,
                pendingDiff = null,
                currentToolCalls = emptyList()
            )
        }
        // 立即以新指令开启新一轮
        doSend(text)
    }

    /** 是否命中「中途打断并重定向」指令（启发式关键词匹配）。 */
    private fun isRedirectDirective(text: String): Boolean {
        val t = text
            .removePrefix("请").removePrefix("帮我").removePrefix("麻烦")
            .trim()
        return REDIRECT_KEYWORDS.any { t.contains(it) }
    }

    /** 暂停闸门：暂停期间阻塞事件处理（网络读取随之暂停），直到 resume() 唤醒。 */
    private suspend fun awaitResumeIfPaused() {
        while (_uiState.value.isPaused) {
            delay(50)
        }
    }

    /** 把 AI 生成的文本插入到编辑器光标处；返回是否已插入。 */
    fun insertToEditor(text: String): Boolean {
        if (!_uiState.value.editorAttached || text.isBlank()) return false
        EditorBridge.insertAtCursor(text)
        return true
    }

    /**
     * 候选供应商列表（聚合网关路由用）：
     * 当前活动供应商优先，其余启用供应商按序作为后备（失败自动切换）。
     */
    private fun candidateProviders(preferred: ProviderConfig): List<ProviderConfig> =
        buildList {
            add(preferred)
            _uiState.value.providers
                .filter { it.id != preferred.id && it.enabled }
                .forEach { add(it) }
        }

    private fun handleEvent(event: ProviderEvent) {
        when (event) {
            is ProviderEvent.Delta -> {
                updateAssistant { msg ->
                    msg.copy(content = msg.content + event.content)
                }
                appendTicker(event.content)
            }
            is ProviderEvent.ReasoningDelta -> {
                val first = _uiState.value.messages
                    .firstOrNull { it.id == assistantId }
                    ?.reasoning.isNullOrEmpty()
                if (first) addActivity(ActivityKind.THINK, "正在思考…")
                updateAssistant { msg ->
                    msg.copy(reasoning = (msg.reasoning ?: "") + event.content)
                }
                appendTicker("[思考] ${event.content}")
            }
            is ProviderEvent.ToolCall -> Unit // 工具调用在 runToolLoop 中单独收集
            is ProviderEvent.Usage -> _uiState.update {
                it.copy(usage = UsageInfo(event.promptTokens, event.completionTokens, event.cachedTokens, event.totalTokens))
            }
            is ProviderEvent.Error -> {
                addActivity(ActivityKind.STOPPED, "出错：${event.message}")
                _uiState.update { it.copy(error = event.message) }
            }
            ProviderEvent.Done -> Unit
        }
    }

    private fun markToolRunning(tc: ToolCall) {
        _uiState.update { st ->
            st.copy(currentToolCalls = st.currentToolCalls.map {
                if (it.id == tc.id) it.copy(status = ToolCallStatus.RUNNING) else it
            })
        }
    }

    private fun markToolDone(tc: ToolCall, output: String) {
        _uiState.update { st ->
            st.copy(currentToolCalls = st.currentToolCalls.map {
                if (it.id == tc.id) {
                    it.copy(
                        status = if (output.startsWith("错误") || output.startsWith("[工具")) ToolCallStatus.FAILED else ToolCallStatus.SUCCESS,
                        result = output.take(120)
                    )
                } else it
            })
        }
    }

    private fun updateAssistant(transform: (ChatMessage) -> ChatMessage) {
        val id = assistantId ?: return
        _uiState.update { st ->
            val idx = st.messages.indexOfLast { it.id == id }
            if (idx < 0) st else st.copy(messages = st.messages.toMutableList().also { it[idx] = transform(it[idx]) })
        }
    }

    /**
     * diff 预览：把「本轮开始前编辑器快照」与「AI 修改后当前内容」做对比，
     * 生成待确认的 diff。仅当编辑器已连接且内容确实发生变化时才有意义。
     */
    private suspend fun generatePendingDiff() {
        val before = editorSnapshot
        editorSnapshot = null
        if (!_uiState.value.editorAttached || before == null) return
        val after = editorCurrentText() ?: return
        if (before == after) return
        val summary = "AI 修改了当前文件（${countDiff(DiffUtils.compute(before, after))}）"
        _uiState.update { it.copy(pendingDiff = PendingDiff(summary, before, after)) }
    }

    /** 接受 diff：保留 AI 的修改。 */
    fun acceptDiff() {
        _uiState.update { it.copy(pendingDiff = null) }
    }

    /** 拒绝 diff：把编辑器回滚到本轮开始前的内容。 */
    fun rejectDiff() {
        val pending = _uiState.value.pendingDiff ?: return
        _uiState.update { it.copy(pendingDiff = null) }
        EditorBridge.setText(pending.before)
        addActivity(ActivityKind.STOPPED, "已拒绝 AI 的改动")
    }

    private fun countDiff(lines: List<DiffLine>): String {
        var add = 0
        var remove = 0
        for (line in lines) {
            if (line.kind == DiffLineKind.ADD) add++
            else if (line.kind == DiffLineKind.REMOVE) remove++
        }
        return "+$add -$remove 行"
    }

    private suspend fun finishStream(result: ProviderResult, cid: String) {
        stopElapsedTimer()
        if (result.success) {
            addActivity(ActivityKind.DONE, "本轮完成")
            generatePendingDiff()
            finalizeAssistant(assistantId ?: return) { it.copy(status = MessageStatus.COMPLETED) }
        } else {
            addActivity(ActivityKind.STOPPED, "本轮失败")
            _uiState.update { it.copy(error = result.error ?: "请求失败") }
            finalizeAssistant(assistantId ?: return) {
                it.copy(status = MessageStatus.ERROR, error = result.error ?: "请求失败")
            }
        }
        _uiState.update { it.copy(isStreaming = false, isPaused = false) }
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

    private companion object {
        const val MAX_TOOL_ROUNDS = 8

        /** 跑马灯保留的最大字符数（超出只留尾部）。 */
        const val TICKER_MAX_CHARS = 4000

        /** 触发「中途打断并重定向」的指令关键词（命中即立即打断并重开一轮）。 */
        val REDIRECT_KEYWORDS = listOf(
            "打断", "重定向",
            "换个方案", "换方案", "换个思路", "换一种",
            "别改", "先改", "别这么做", "别继续", "别做",
            "停下来", "停一下",
            "方向错了", "方向不对", "跑偏了", "重新来", "重来"
        )
    }
}
