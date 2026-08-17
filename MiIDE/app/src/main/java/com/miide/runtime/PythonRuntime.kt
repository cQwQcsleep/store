package com.miide.runtime

import android.util.Base64
import com.chaquo.python.Python
import com.miide.core.runtime.CodeRuntime
import com.miide.core.runtime.RunOptions
import com.miide.core.runtime.RunResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Python 运行时，基于 Chaquopy。
 *
 * 将代码 base64 编码后交给 `python/main.py` 的 `execute()` 执行，
 * 返回 JSON（stdout / stderr），从而在隔离的输出环境中运行用户代码。
 */
class PythonRuntime(
    private val json: Json = Json { ignoreUnknownKeys = true }
) : CodeRuntime {

    override val id = "python"
    override val displayName = "Python (Chaquopy)"
    override val fileExtensions = listOf(".py")

    /** 初始化成功才标记可用（原生库缺失时返回 false，UI 不展示该运行时）。 */
    override val available: Boolean =
        runCatching { Python.getInstance() }.isSuccess

    override suspend fun execute(code: String, options: RunOptions): RunResult =
        withContext(Dispatchers.IO) {
            val start = System.currentTimeMillis()
            val elapsed = { System.currentTimeMillis() - start }
            try {
                val py = Python.getInstance()
                val main = py.getModule("main")
                val b64 = Base64.encodeToString(
                    code.toByteArray(Charsets.UTF_8),
                    Base64.NO_WRAP
                )
                val result = main.callAttr("execute", b64).toString()
                val obj = json.parseToJsonElement(result).jsonObject
                val stdout = obj["stdout"]?.jsonPrimitive?.content.orEmpty()
                val stderr = obj["stderr"]?.jsonPrimitive?.content.orEmpty()
                RunResult(
                    exitCode = if (stderr.isBlank()) 0 else 1,
                    stdout = stdout,
                    stderr = stderr,
                    durationMs = elapsed()
                )
            } catch (e: Exception) {
                RunResult(exitCode = 1, stdout = "", stderr = e.message ?: "Python 执行异常", durationMs = elapsed())
            }
        }
}
