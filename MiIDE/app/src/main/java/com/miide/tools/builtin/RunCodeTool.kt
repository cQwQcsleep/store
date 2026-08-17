package com.miide.tools.builtin

import com.miide.core.runtime.RuntimeRegistry
import com.miide.tools.AgentTool
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * 运行代码工具：让 AI 执行生成的代码并查看结果（stdout / stderr / 退出码 / 耗时）。
 *
 * 支持语言由运行时注册表决定（js / python / lua）。
 */
class RunCodeTool(
    private val runtimeRegistry: RuntimeRegistry
) : AgentTool {

    override val name: String = "code.run"
    override val description: String =
        "执行一段代码并返回运行结果（退出码、stdout、stderr、耗时）。用于验证 AI 生成的代码。支持语言：js、python、lua。"
    override val parameters: String = """
        {
          "type": "object",
          "properties": {
            "language": {"type": "string", "description": "代码语言，如 js / python / lua"},
            "code": {"type": "string", "description": "要执行的完整代码"}
          },
          "required": ["language", "code"]
        }
    """.trimIndent()

    override suspend fun execute(arguments: JsonObject): String {
        val language = arguments["language"]?.jsonPrimitive?.content?.lowercase()
            ?: return "错误：缺少 language 参数"
        val code = arguments["code"]?.jsonPrimitive?.content
            ?: return "错误：缺少 code 参数"

        val runtime = runtimeRegistry.find(language)
            ?: return "错误：不支持的语言「$language」。可用：${runtimeRegistry.available().joinToString(", ") { it.id }}"
        if (!runtime.available) return "错误：运行时「${runtime.displayName}」不可用"

        val result = runtime.execute(code)
        return buildString {
            append("退出码: ${result.exitCode}    耗时: ${result.durationMs}ms")
            if (result.timedOut) append("    [超时]")
            if (result.stdout.isNotBlank()) {
                append("\n--- stdout ---\n").append(result.stdout.trimEnd())
            }
            if (result.stderr.isNotBlank()) {
                append("\n--- stderr ---\n").append(result.stderr.trimEnd())
            }
        }
    }
}
