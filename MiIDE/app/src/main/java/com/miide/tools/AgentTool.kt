package com.miide.tools

import com.miide.core.network.ToolSpec
import kotlinx.serialization.json.JsonObject

/**
 * 一个可被 AI 调用的工具（function calling）。
 *
 * [parameters] 为 JSON Schema 描述（OpenAI `function.parameters`），
 * 执行时收到已解析的参数对象，返回字符串结果（将作为 tool 角色消息回传给模型）。
 */
interface AgentTool {
    val name: String
    val description: String
    val parameters: String

    /** 执行工具；异常会被调用方捕获并转为错误文本回传。 */
    suspend fun execute(arguments: JsonObject): String

    /** 转换为请求体中的工具定义。 */
    fun toSpec(): ToolSpec = ToolSpec(
        name = name,
        description = description,
        parametersJson = parameters
    )
}
