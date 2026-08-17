package com.miide.core.runtime

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.luaj.vm2.LuaError
import org.luaj.vm2.lib.jse.JsePlatform
import java.io.ByteArrayOutputStream
import java.io.PrintStream

/**
 * Lua 运行时，基于 LuaJ（纯 JVM 实现，无需原生库）。
 *
 * 通过重定向 [org.luaj.vm2.Globals.STDOUT] 捕获 print 输出，捕获运行时错误并返回。
 */
class LuaRuntime : CodeRuntime {

    override val id = "lua"
    override val displayName = "Lua (LuaJ)"
    override val fileExtensions = listOf(".lua")
    override val available: Boolean = true

    override suspend fun execute(code: String, options: RunOptions): RunResult =
        withContext(Dispatchers.Default) {
            val start = System.currentTimeMillis()
            val elapsed = { System.currentTimeMillis() - start }
            val outBytes = ByteArrayOutputStream()
            val globals = JsePlatform.standardGlobals()
            globals.STDOUT = PrintStream(outBytes, true, Charsets.UTF_8.name())
            globals.STDERR = globals.STDOUT

            try {
                globals.load(code, "@miide.lua").call()
                RunResult(exitCode = 0, stdout = outBytes.toString(Charsets.UTF_8.name()), stderr = "", durationMs = elapsed())
            } catch (e: LuaError) {
                val msg = e.message?.takeIf { it.isNotBlank() } ?: "Lua 执行异常"
                RunResult(exitCode = 1, stdout = outBytes.toString(Charsets.UTF_8.name()), stderr = msg, durationMs = elapsed())
            } catch (e: Exception) {
                RunResult(exitCode = 1, stdout = outBytes.toString(Charsets.UTF_8.name()), stderr = e.message ?: "Lua 执行异常", durationMs = elapsed())
            }
        }
}
