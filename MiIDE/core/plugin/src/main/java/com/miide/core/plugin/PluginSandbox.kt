package com.miide.core.plugin

import com.quickjs.QuickJS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 脚本插件沙箱：在 QuickJS 独立运行时中执行插件脚本，
 * 注入 `MiIDE` 受限 API 与 console 捕获，执行结束后关闭释放原生资源。
 *
 * 说明：QuickJS 为同步原生执行，基础版不强制硬中断（无限循环无法被外部取消），
 * 通过后台调度避免阻塞 UI。
 */
class PluginSandbox {

    private val consolePreamble = """
        (function () {
          var __miide_out = [];
          function writer() {
            return function () {
              var a = Array.prototype.slice.call(arguments);
              var line = a.map(function (x) {
                if (x === undefined) return 'undefined';
                if (x === null) return 'null';
                try { return typeof x === 'object' ? JSON.stringify(x) : String(x); }
                catch (e) { return String(x); }
              }).join(' ');
              __miide_out.push(line);
            };
          }
          globalThis.console = {
            log: writer(), info: writer(), warn: writer(), error: writer(), debug: writer()
          };
          globalThis.__miide_console = __miide_out;
        })();
    """.trimIndent()

    /** 在沙箱中执行插件脚本；异常时返回 exitCode=1。 */
    suspend fun execute(script: String, api: MiIDEPluginApi = MiIDEPluginApi()): PluginRunResult =
        withContext(Dispatchers.Default) {
            val start = System.currentTimeMillis()
            val elapsed = { System.currentTimeMillis() - start }
            val quickJS = QuickJS.createRuntime()
            try {
                val context = quickJS.createContext()
                try {
                    // 暴露受限 API：全局 MiIDE 对象
                    context.addJavascriptInterface(api, "MiIDE")
                    context.executeScript(consolePreamble, "<miide-plugin-preamble>")
                    context.executeScript(script, "<miide-plugin>")
                    val consoleOut = context.executeStringScript("__miide_console.join('\n')", "<miide-plugin-dump>")
                    val stdout = listOf(consoleOut, api.logLines.joinToString("\n"))
                        .filter { it.isNotBlank() }
                        .joinToString("\n")
                    PluginRunResult(
                        exitCode = 0,
                        stdout = stdout,
                        stderr = "",
                        emits = api.emitList,
                        durationMs = elapsed()
                    )
                } catch (e: Throwable) {
                    PluginRunResult(
                        exitCode = 1,
                        stdout = "",
                        stderr = e.message ?: "插件执行异常",
                        emits = api.emitList,
                        durationMs = elapsed()
                    )
                } finally {
                    runCatching { context.close() }
                }
            } catch (e: Throwable) {
                PluginRunResult(
                    exitCode = 1,
                    stdout = "",
                    stderr = e.message ?: "插件运行时不可用",
                    emits = emptyList(),
                    durationMs = elapsed()
                )
            } finally {
                runCatching { quickJS.close() }
            }
        }
}
