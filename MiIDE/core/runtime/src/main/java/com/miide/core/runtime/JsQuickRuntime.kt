package com.miide.core.runtime

import com.quickjs.QuickJS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * JavaScript 运行时，基于 QuickJS（taoweiji 绑定）。
 *
 * 每次执行创建独立运行时与上下文，注入 console 捕获，执行结束后关闭释放原生资源。
 *
 * 说明：QuickJS 为同步原生执行，v1 不强制硬中断（无限循环无法被外部取消），
 * 通过后台调度避免阻塞 UI，由调用方按 [RunOptions.timeoutMs] 自行处理。
 */
class JsQuickRuntime : CodeRuntime {

    override val id = "js"
    override val displayName = "JavaScript (QuickJS)"
    override val fileExtensions = listOf(".js", ".mjs", ".cjs")
    override val available: Boolean = true

    /** 预置脚本：注入 console 捕获，避免原生 console 丢失输出。 */
    private val consolePreamble = """
        (function () {
          var __miide_out = [];
          function makeWriter() {
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
            log: makeWriter(),
            info: makeWriter(),
            warn: makeWriter(),
            error: makeWriter(),
            debug: makeWriter()
          };
          globalThis.__miide_console = __miide_out;
        })();
    """.trimIndent()

    override suspend fun execute(code: String, options: RunOptions): RunResult =
        withContext(Dispatchers.Default) {
            val start = System.currentTimeMillis()
            val quickJS = QuickJS.createRuntime()
            val elapsed = { System.currentTimeMillis() - start }
            try {
                val context = quickJS.createContext()
                try {
                    context.executeScript(consolePreamble, "<miide-preamble>")
                    context.executeScript(code, "<miide-code>")
                    val stdout = context.executeStringScript(JS_CONSOLE_DUMP, "<miide-dump>")
                    RunResult(exitCode = 0, stdout = stdout, stderr = "", durationMs = elapsed())
                } catch (e: Throwable) {
                    RunResult(exitCode = 1, stdout = "", stderr = e.message ?: "JS 执行异常", durationMs = elapsed())
                } finally {
                    runCatching { context.close() }
                }
            } catch (e: Throwable) {
                RunResult(exitCode = 1, stdout = "", stderr = e.message ?: "JS 运行时不可用", durationMs = elapsed())
            } finally {
                runCatching { quickJS.close() }
            }
        }

    private companion object {
        const val JS_CONSOLE_DUMP = "__miide_console.join('\n')"
    }
}
