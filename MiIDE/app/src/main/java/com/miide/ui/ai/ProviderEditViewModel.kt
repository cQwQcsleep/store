package com.miide.ui.ai

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miide.core.data.repository.ProviderRepository
import com.miide.core.model.ChatMessage
import com.miide.core.model.ChatRole
import com.miide.core.model.ModelConfig
import com.miide.core.model.ProviderConfig
import com.miide.core.model.ProviderProtocol
import com.miide.core.model.ProviderType
import com.miide.core.network.ProviderFactory
import com.miide.core.network.ProviderRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

/** 测试连接结果。 */
data class TestOutcome(val success: Boolean, val message: String)

@HiltViewModel
class ProviderEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val providerRepository: ProviderRepository,
    private val providerFactory: ProviderFactory
) : ViewModel() {

    private val providerId: String? = savedStateHandle["providerId"]

    // ---- 表单状态 ----
    var name by mutableStateOf("")
    var type by mutableStateOf(ProviderType.CUSTOM)
    var protocol by mutableStateOf(ProviderProtocol.OPENAI_COMPAT)
    var baseUrl by mutableStateOf("")
    var apiKey by mutableStateOf("")
    var modelsText by mutableStateOf("")
    var defaultModelId by mutableStateOf<String?>(null)
    var enabled by mutableStateOf(true)

    var saving by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    var testing by mutableStateOf(false)
    var testOutcome by mutableStateOf<TestOutcome?>(null)

    private var existingId: String? = providerId
    private var createdAt: Long = System.currentTimeMillis()

    init {
        if (providerId != null) {
            viewModelScope.launch {
                providerRepository.getById(providerId)?.let { c ->
                    name = c.name
                    type = c.type
                    protocol = c.protocol
                    baseUrl = c.baseUrl
                    apiKey = c.apiKey
                    modelsText = c.models.joinToString("\n") { it.id }
                    defaultModelId = c.defaultModelId
                    enabled = c.enabled
                    existingId = c.id
                    createdAt = c.createdAt
                }
            }
        }
    }

    /** 从表单构建 [ProviderConfig]。 */
    fun buildConfig(): ProviderConfig {
        val models = modelsText.lines()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .distinct()
            .map { ModelConfig(id = it, displayName = it) }
        val modelIds = models.map { it.id }
        val default = defaultModelId?.takeIf { it in modelIds } ?: models.firstOrNull()?.id
        return ProviderConfig(
            id = existingId ?: UUID.randomUUID().toString(),
            name = name.trim(),
            type = type,
            protocol = protocol,
            baseUrl = baseUrl.trim().trimEnd('/'),
            apiKey = apiKey.trim(),
            models = models,
            defaultModelId = default,
            enabled = enabled,
            createdAt = createdAt
        )
    }

    private fun validate(): String? = when {
        name.isBlank() -> "请填写供应商名称"
        protocol != ProviderProtocol.CUSTOM_TEMPLATE && baseUrl.isBlank() -> "请填写 Base URL"
        modelsText.lines().none { it.isNotBlank() } -> "请至少填写一个模型 ID（每行一个）"
        else -> null
    }

    fun save(onSaved: () -> Unit) {
        val error = validate()
        if (error != null) {
            errorMessage = error
            return
        }
        viewModelScope.launch {
            saving = true
            errorMessage = null
            providerRepository.upsert(buildConfig())
            saving = false
            onSaved()
        }
    }

    /** 测试连接：向首个模型发送一条最小请求，验证鉴权与可达性。 */
    fun testConnection() {
        val error = validate()
        if (error != null) {
            testOutcome = TestOutcome(false, error)
            return
        }
        val config = buildConfig()
        val model = config.models.firstOrNull { it.enabled } ?: run {
            testOutcome = TestOutcome(false, "请至少配置一个可用模型")
            return
        }
        viewModelScope.launch {
            testing = true
            testOutcome = null
            val outcome = try {
                val provider = providerFactory.create(config)
                val result = provider.chat(
                    ProviderRequest(
                        modelId = model.id,
                        messages = listOf(ChatMessage(role = ChatRole.USER, content = "ping")),
                        stream = false
                    )
                ) {}
                if (result.success) {
                    TestOutcome(true, "连接成功（${result.latencyMs} ms）")
                } else {
                    TestOutcome(false, result.error ?: "连接失败")
                }
            } catch (e: kotlinx.coroutines.CancellationException) {
                throw e
            } catch (e: Exception) {
                TestOutcome(false, e.message ?: "连接失败")
            }
            testOutcome = outcome
            testing = false
        }
    }
}
