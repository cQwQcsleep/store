package com.miide.core.network.provider

import com.miide.core.model.ChatRole
import com.miide.core.model.ModelConfig
import com.miide.core.model.ProviderConfig
import com.miide.core.model.ProviderType
import com.miide.core.network.AiProvider
import com.miide.core.network.ModelInfo
import com.miide.core.network.ProviderEvent
import com.miide.core.network.ProviderRequest
import com.miide.core.network.ProviderResult
import com.miide.core.network.ToolSpec
import com.miide.core.network.dto.OpenAiChatChunk
import com.miide.core.network.dto.OpenAiChatRequest
import com.miide.core.network.dto.OpenAiChatResponse
import com.miide.core.network.dto.OpenAiErrorResponse
import com.miide.core.network.dto.OpenAiFunction
import com.miide.core.network.dto.OpenAiMessage
import com.miide.core.network.dto.OpenAiModelsResponse
import com.miide.core.network.dto.OpenAiTool
import com.miide.core.network.dto.OpenAiToolCall
import com.miide.core.network.dto.OpenAiFunctionDelta
import com.miide.core.network.dto.OpenAiToolCallDelta
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.preparePost
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsChannel
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.utils.io.readUTF8Line
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * OpenAI Chat Completions 兼容适配器。
 * 覆盖 OpenAI / DeepSeek / 智谱 GLM / Kimi / 通义千问 / 任意三方聚合商。
 */
open class OpenAiCompatProvider(
    override val config: ProviderConfig,
    private val client: HttpClient,
    private val json: Json
) : AiProvider {

    override val protocolName: String = "OpenAI 兼容"

    private fun base(): String = config.baseUrl.trimEnd('/')
    protected open fun chatUrl(): String = base() + "/chat/completions"
    protected open fun modelsUrl(): String = base() + "/models"

    /** 鉴权头；默认 Bearer，子类可覆盖（如自定义供应商用 x-api-key） */
    protected open fun authHeaders(key: String): Map<String, String> =
        if (key.isNotBlank()) mapOf(HttpHeaders.Authorization to "Bearer $key") else emptyMap()

    /**
     * 最终请求头：鉴权 + 用户自定义头，并补齐供应商必需头。
     * 通义千问 DashScope 的 OpenAI 兼容模式必须以 `X-DashScope-SSE: enable`
     * 才会以 SSE 流式返回；用户未显式配置时自动补上。
     */
    protected open fun effectiveHeaders(key: String): Map<String, String> {
        val extra = buildMap {
            putAll(config.extraHeaders)
            if (config.type == ProviderType.QWEN && !containsKey("X-DashScope-SSE")) {
                put("X-DashScope-SSE", "enable")
            }
        }
        return authHeaders(key) + extra
    }

    override suspend fun chat(
        request: ProviderRequest,
        onEvent: suspend (ProviderEvent) -> Unit
    ): ProviderResult {
        val started = System.currentTimeMillis()
        val model = config.model(request.modelId)
            ?: return ProviderResult(false, "未配置可用模型：${request.modelId}", retryable = false)

        val body = buildRequest(request, model)
        val key = config.allActiveKeys.firstOrNull().orEmpty()

        return try {
            val response = client.preparePost(chatUrl()) {
                contentType(ContentType.Application.Json)
                setBody(json.encodeToString(OpenAiChatRequest.serializer(), body))
                effectiveHeaders(key).forEach { (k, v) -> header(k, v) }
            }.execute { it }

            if (!response.status.isSuccess()) {
                val text = runCatching { response.bodyAsText() }.getOrDefault("")
                val msg = runCatching {
                    json.decodeFromString<OpenAiErrorResponse>(text).error?.message
                }.getOrNull() ?: text.take(300)
                return ProviderResult(
                    success = false,
                    error = "HTTP ${response.status.value}: $msg",
                    retryable = response.status.value >= 500 || response.status.value == 429
                )
            }

            var prompt = 0L
            var completion = 0L
            var cached = 0L

            if (request.stream) {
                val toolAccum = if (request.tools != null) mutableMapOf<Int, OpenAiToolCallDelta>() else null
                val channel = response.bodyAsChannel()
                while (!channel.isClosedForRead) {
                    val line = channel.readUTF8Line() ?: break
                    if (!line.startsWith("data:")) continue
                    val payload = line.removePrefix("data:").trim()
                    if (payload == "[DONE]") break
                    val chunk = runCatching {
                        json.decodeFromString<OpenAiChatChunk>(payload)
                    }.getOrNull() ?: continue

                    chunk.usage?.let {
                        prompt = it.promptTokens
                        completion = it.completionTokens
                        cached = it.promptCacheHitTokens ?: it.cachedTokens ?: 0
                    }

                    chunk.choices.forEach { choice ->
                        val delta = choice.delta
                        if (!delta.content.isNullOrBlank()) {
                            onEvent(ProviderEvent.Delta(delta.content))
                        }
                        val reasoning = delta.reasoningContent ?: delta.reasoning
                        if (!reasoning.isNullOrBlank()) {
                            onEvent(ProviderEvent.ReasoningDelta(reasoning))
                        }
                        delta.toolCalls?.forEach { tc ->
                            val acc = toolAccum?.getOrPut(tc.id?.hashCode() ?: 0) {
                                OpenAiToolCallDelta(id = tc.id, function = tc.function)
                            }
                            if (acc != null && tc.function != null) {
                                val name = acc.function?.name.orEmpty() + (tc.function?.name.orEmpty())
                                val args = acc.function?.arguments.orEmpty() + (tc.function?.arguments.orEmpty())
                                toolAccum!![tc.id?.hashCode() ?: 0] = OpenAiToolCallDelta(
                                    id = acc.id,
                                    function = OpenAiFunctionDelta(name = name.ifEmpty { null }, arguments = args)
                                )
                            }
                        }
                    }
                }
                toolAccum?.values?.forEach { tc ->
                    onEvent(
                        ProviderEvent.ToolCall(
                            id = tc.id,
                            name = tc.function?.name,
                            arguments = tc.function?.arguments.orEmpty()
                        )
                    )
                }
            } else {
                val text = response.bodyAsText()
                val full = runCatching {
                    json.decodeFromString<OpenAiChatResponse>(text)
                }.getOrNull()
                full?.choices?.firstOrNull()?.message?.let { msg ->
                    if (!msg.content.isNullOrBlank()) onEvent(ProviderEvent.Delta(msg.content))
                    val reasoning = msg.reasoningContent ?: msg.reasoning
                    if (!reasoning.isNullOrBlank()) onEvent(ProviderEvent.ReasoningDelta(reasoning))
                    msg.toolCalls?.forEach { tc ->
                        onEvent(
                            ProviderEvent.ToolCall(
                                id = tc.id,
                                name = tc.function?.name,
                                arguments = tc.function?.arguments.orEmpty()
                            )
                        )
                    }
                }
                full?.usage?.let {
                    prompt = it.promptTokens
                    completion = it.completionTokens
                    cached = it.promptCacheHitTokens ?: it.cachedTokens ?: 0
                }
            }

            onEvent(ProviderEvent.Usage(prompt, completion, cached, prompt + completion))
            onEvent(ProviderEvent.Done)
            ProviderResult(
                success = true,
                promptTokens = prompt,
                completionTokens = completion,
                cachedTokens = cached,
                latencyMs = System.currentTimeMillis() - started
            )
        } catch (e: kotlinx.coroutines.CancellationException) {
            throw e
        } catch (e: Exception) {
            ProviderResult(
                success = false,
                error = e.message ?: "网络错误",
                retryable = true
            )
        }
    }

    override suspend fun listModels(): List<ModelInfo> {
        val configured = config.models
            .filter { it.enabled }
            .map { ModelInfo(it.id, it.displayName, it.contextWindow) }
        if (config.baseUrl.isBlank()) return configured

        return try {
            val response = client.get(modelsUrl()) {
                effectiveHeaders(config.allActiveKeys.firstOrNull().orEmpty()).forEach { (k, v) -> header(k, v) }
            }
            if (response.status.isSuccess()) {
                val text = response.bodyAsText()
                val parsed = runCatching {
                    json.decodeFromString<OpenAiModelsResponse>(text)
                }.getOrNull()
                val remote = parsed?.data.orEmpty().map {
                    ModelInfo(it.id, it.id, it.contextWindow)
                }
                if (remote.isNotEmpty()) remote else configured
            } else configured
        } catch (e: Exception) {
            configured
        }
    }

    private fun buildRequest(request: ProviderRequest, model: ModelConfig): OpenAiChatRequest {
        val messages = buildList {
            request.systemPrompt?.takeIf { it.isNotBlank() }?.let {
                add(OpenAiMessage("system", it))
            }
            request.messages.forEach { m ->
                when (m.role) {
                    ChatRole.TOOL -> add(
                        OpenAiMessage(
                            role = "tool",
                            content = m.content,
                            toolCallId = m.toolCallId ?: ""
                        )
                    )
                    ChatRole.ASSISTANT -> add(
                        OpenAiMessage(
                            role = "assistant",
                            content = m.content.ifEmpty { null },
                            toolCalls = m.toolCalls?.map { tc ->
                                OpenAiToolCall(
                                    id = tc.id,
                                    function = OpenAiFunctionDelta(name = tc.name, arguments = tc.arguments)
                                )
                            }
                        )
                    )
                    else -> add(OpenAiMessage(m.role.name.lowercase(), m.content))
                }
            }
        }
        val tools = request.tools?.map { t ->
            OpenAiTool(
                type = "function",
                function = OpenAiFunction(
                    name = t.name,
                    description = t.description,
                    parameters = t.parametersJson?.let { runCatching { json.parseToJsonElement(it).jsonObject }.getOrNull() }
                )
            )
        }
        // 合并模型级参数与请求级参数（请求级优先）
        val temperature = request.temperature ?: model.temperature
        val maxTokens = request.maxTokens?.toLong() ?: model.maxTokens
        val reasoning = request.reasoningEffort ?: model.reasoningEffort

        // 通义千问 Qwen3：思考模式用 enable_thinking 开关，不支持 reasoning_effort
        val isQwen = config.type == ProviderType.QWEN
        val enableThinking = if (isQwen) reasoning?.let { it != "low" && it != "none" } else null

        return OpenAiChatRequest(
            model = model.id,
            messages = messages,
            temperature = temperature,
            maxTokens = maxTokens?.toInt(),
            maxCompletionTokens = null,
            stream = request.stream,
            reasoningEffort = if (isQwen) null else reasoning,
            enableThinking = enableThinking,
            tools = tools?.takeIf { model.toolsEnabled }
        )
    }
}
