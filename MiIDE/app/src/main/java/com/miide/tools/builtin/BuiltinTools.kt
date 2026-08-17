package com.miide.tools.builtin

import com.miide.browser.WebFetchTool
import com.miide.tools.AgentTool
import io.ktor.client.HttpClient
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.File

/**
 * 内置文件系统工具：让 AI 能读写设备上的文本文件。
 *
 * 受 Android 沙箱权限限制，路径通常限于应用可访问目录（如项目目录、应用私有目录）；
 * 不可访问时返回明确错误，由模型自行调整。
 */
class ReadFileTool : AgentTool {
    override val name: String = "filesystem.read_file"
    override val description: String = "读取指定路径的文本文件内容。返回文件内容；文件过大时返回截断预览。"
    override val parameters: String = """
        {
          "type": "object",
          "properties": {
            "path": {"type": "string", "description": "要读取的文件的绝对路径"}
          },
          "required": ["path"]
        }
    """.trimIndent()

    override suspend fun execute(arguments: JsonObject): String {
        val path = arguments["path"]?.jsonPrimitive?.content ?: return "错误：缺少 path 参数"
        val file = File(path)
        return try {
            if (!file.exists()) "错误：文件不存在：$path"
            else if (!file.isFile) "错误：不是文件：$path"
            else if (!file.canRead()) "错误：无读取权限：$path"
            else {
                val text = file.readText(Charsets.UTF_8)
                if (text.length > MAX_CHARS) text.take(MAX_CHARS) + "\n…（内容过长，已截断，共 ${text.length} 字符）"
                else text
            }
        } catch (e: Exception) {
            "错误：读取失败 ${e.message ?: e::class.simpleName}"
        }
    }

    private companion object {
        const val MAX_CHARS = 200_000
    }
}

/**
 * 写入文本文件（覆盖）。父目录不存在时自动创建。
 */
class WriteFileTool : AgentTool {
    override val name: String = "filesystem.write_file"
    override val description: String = "向指定路径写入文本内容（覆盖已存在文件）。用于生成 / 修改代码文件。"
    override val parameters: String = """
        {
          "type": "object",
          "properties": {
            "path": {"type": "string", "description": "要写入的文件的绝对路径"},
            "content": {"type": "string", "description": "要写入的完整文件内容"}
          },
          "required": ["path", "content"]
        }
    """.trimIndent()

    override suspend fun execute(arguments: JsonObject): String {
        val path = arguments["path"]?.jsonPrimitive?.content ?: return "错误：缺少 path 参数"
        val content = arguments["content"]?.jsonPrimitive?.content ?: return "错误：缺少 content 参数"
        return try {
            val file = File(path)
            file.parentFile?.mkdirs()
            file.writeText(content, Charsets.UTF_8)
            "已写入 ${file.length()} 字节到 $path"
        } catch (e: Exception) {
            "错误：写入失败 ${e.message ?: e::class.simpleName}"
        }
    }
}

/**
 * 列出目录内容（含子目录）。
 */
class ListDirTool : AgentTool {
    override val name: String = "filesystem.list_dir"
    override val description: String = "列出指定目录下的文件和子目录名称。"
    override val parameters: String = """
        {
          "type": "object",
          "properties": {
            "path": {"type": "string", "description": "要列出的目录的绝对路径"}
          },
          "required": ["path"]
        }
    """.trimIndent()

    override suspend fun execute(arguments: JsonObject): String {
        val path = arguments["path"]?.jsonPrimitive?.content ?: return "错误：缺少 path 参数"
        val dir = File(path)
        return try {
            if (!dir.exists()) "错误：目录不存在：$path"
            else if (!dir.isDirectory) "错误：不是目录：$path"
            else {
                val entries = dir.listFiles()?.sortedBy { it.name }.orEmpty()
                if (entries.isEmpty()) "（空目录）"
                else entries.joinToString("\n") { if (it.isDirectory) "[${it.name}/]" else it.name }
            }
        } catch (e: Exception) {
            "错误：读取目录失败 ${e.message ?: e::class.simpleName}"
        }
    }
}

/** 注册全部内置工具。 */
fun registerBuiltinTools(
    registry: com.miide.tools.ToolRegistry,
    runtimeRegistry: com.miide.core.runtime.RuntimeRegistry,
    httpClient: io.ktor.client.HttpClient
) {
    registry.register(ReadFileTool())
    registry.register(WriteFileTool())
    registry.register(ListDirTool())
    registry.register(RunCodeTool(runtimeRegistry))
    registry.register(WebFetchTool(httpClient))
}
