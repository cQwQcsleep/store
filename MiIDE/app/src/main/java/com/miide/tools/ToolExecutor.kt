package com.miide.tools

import com.miide.core.network.ToolSpec
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

/**
 * 工具执行器：查找工具 → 解析参数 → 执行 → 返回文本结果。
 *
 * 所有异常都会转为可回传给模型的错误文本，保证 function calling 循环不被中断。
 */
class ToolExecutor(
    private val registry: ToolRegistry,
    private val json: Json = Json { ignoreUnknownKeys = true }
) {

    /** 当前可提供给模型的工具定义列表。 */
    fun specs(): List<ToolSpec> = registry.all().map { it.toSpec() }

    /**
     * 执行指定工具并返回文本结果（成功或错误说明）。
     */
    suspend fun execute(name: String, argumentsJson: String): String {
        val tool = registry.find(name)
            ?: return "错误：未找到工具「$name」。可用工具：${registry.all().joinToString(", ") { it.name }}"
        val args: JsonObject = if (argumentsJson.isBlank()) {
            JsonObject(emptyMap())
        } else {
            runCatching { json.parseToJsonElement(argumentsJson).jsonObject }
                .getOrElse { JsonObject(emptyMap()) }
        }
        return try {
            tool.execute(args)
        } catch (e: Exception) {
            "错误：工具「$name」执行失败：${e.message ?: e::class.simpleName}"
        }
    }
}
