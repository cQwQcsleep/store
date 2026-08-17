package com.miide.browser

import com.miide.tools.AgentTool
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.net.URI

/**
 * AI 网页抓取工具：在 AI 对话内抓取指定 URL 的内容摘要与页面预览。
 *
 * - 通过 Ktor 直接抓取 HTML / 纯文本 / JSON；
 * - 提取标题与正文可读文本（剥离脚本、样式、标签）；
 * - 返回截断后的内容，供模型总结 / 引用 / 回答问题。
 *
 * 配合内置浏览器使用：浏览器页面地址可直接交给该工具抓取（与 WebView 同网络环境）。
 */
class WebFetchTool(
    private val client: HttpClient
) : AgentTool {

    override val name: String = "browser.fetch_url"
    override val description: String = "抓取指定 URL 的网页内容（标题 + 可读正文），供 AI 阅读、总结或回答关于该页面的问题。返回内容过长时自动截断。"
    override val parameters: String = """
        {
          "type": "object",
          "properties": {
            "url": {"type": "string", "description": "要抓取的完整 URL，如 https://example.com"},
            "maxChars": {"type": "integer", "description": "返回内容的最大字符数，默认 8000"}
          },
          "required": ["url"]
        }
    """.trimIndent()

    override suspend fun execute(arguments: JsonObject): String {
        val url = arguments["url"]?.jsonPrimitive?.content?.trim().orEmpty()
        if (url.isEmpty()) return "错误：缺少 url 参数"
        val maxChars = arguments["maxChars"]?.jsonPrimitive?.content
            ?.toIntOrNull()?.coerceIn(500, 50_000) ?: DEFAULT_MAX_CHARS

        val normalized = try {
            normalize(url)
        } catch (e: Exception) {
            return "错误：URL 格式无效：$url"
        }

        return try {
            val response = client.get(normalized) {
                header("User-Agent", USER_AGENT)
                header("Accept", "text/html,application/xhtml+xml,application/json,text/plain,*/*")
            }
            if (response.status.value >= 400) {
                return "错误：抓取失败 HTTP ${response.status.value}：$url"
            }
            val contentType = response.headers["Content-Type"].orEmpty()
            val body = response.bodyAsText()
            val text = if (contentType.contains("json", ignoreCase = true) ||
                body.trimStart().startsWith("{") || body.trimStart().startsWith("[")) {
                body
            } else {
                val (title, readable) = htmlToText(body)
                buildString {
                    title?.takeIf { it.isNotBlank() }?.let { append("标题：").append(it.trim()).append("\n\n") }
                    append(readable)
                }
            }
            val final = text.trim()
            if (final.isEmpty()) "（该页面无可读文本内容）"
            else if (final.length > maxChars) final.take(maxChars) + "\n…（内容过长，已截断，共 ${final.length} 字符）"
            else final
        } catch (e: Exception) {
            "错误：抓取失败 ${e.message ?: e::class.simpleName}：$url"
        }
    }

    /** 规范化 URL：缺协议时补 https://。 */
    private fun normalize(url: String): String {
        if (url.startsWith("http://") || url.startsWith("https://")) return url
        val withScheme = "https://$url"
        // 校验主机格式，非法即抛错
        URI(withScheme).run {
            if (host.isNullOrBlank()) throw IllegalArgumentException("empty host")
        }
        return withScheme
    }

    /** 轻量 HTML → 可读文本：剥离脚本/样式/标签，解码实体，保留段落结构。 */
    private fun htmlToText(html: String): Pair<String?, String> {
        val title = TITLE_REGEX.find(html)?.groupValues?.get(1)?.trim()

        var text = html
        text = SCRIPT_STYLE_REGEX.replace(text, " ")
        text = COMMENT_REGEX.replace(text, " ")
        text = BLOCK_REGEX.replace(text, "\n")
        text = TAG_REGEX.replace(text, " ")
        text = decodeEntities(text)
        text = text
            .replace(Regex("[ \\t\\x0B\\f\\r]+"), " ")
            .replace(Regex("\\n\\s*\\n+"), "\n")
            .trim()
        return title to text
    }

    /** 解码常见 HTML 实体（覆盖常用字符实体 + 数字实体）。 */
    private fun decodeEntities(input: String): String {
        var out = input
        out = out.replace("&lt;", "<").replace("&gt;", ">")
            .replace("&quot;", "\"").replace("&#39;", "'").replace("&apos;", "'")
            .replace("&nbsp;", " ").replace("&amp;", "&")
        // 数字实体 &#123; / &#x1F; 
        out = NUM_ENTITY_REGEX.replace(out) { m ->
            val raw = m.groupValues[1]
            try {
                val cp = if (raw.startsWith("x") || raw.startsWith("X")) {
                    raw.substring(1).toInt(16)
                } else {
                    raw.toInt()
                }
                String(Character.toChars(cp))
            } catch (e: Exception) {
                ""
            }
        }
        return out
    }

    private companion object {
        const val DEFAULT_MAX_CHARS = 8_000
        const val USER_AGENT = "MiIDE/0.1 (AI IDE; +https://github.com/cQwQcsleep)"
        val TITLE_REGEX = Regex("<title[^>]*>(.*?)</title>", RegexOption.IGNORE_CASE)
        val SCRIPT_STYLE_REGEX = Regex(
            "<(script|style|noscript|template|svg)[^>]*>.*?</\\1>",
            setOf(RegexOption.IGNORE_CASE, RegexOption.DOT_MATCHES_ALL)
        )
        val COMMENT_REGEX = Regex("<!--.*?-->", RegexOption.DOT_MATCHES_ALL)
        val BLOCK_REGEX = Regex(
            "</?(p|div|br|li|h[1-6]|tr|td|th|section|article|blockquote|pre|header|footer|ul|ol)[^>]*>",
            RegexOption.IGNORE_CASE
        )
        val TAG_REGEX = Regex("<[^>]+>")
        val NUM_ENTITY_REGEX = Regex("&#([0-9]+|x[0-9a-fA-F]+);")
    }
}
