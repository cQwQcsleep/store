package com.miide.core.runtime

import java.util.concurrent.ConcurrentHashMap

/**
 * 运行时注册表：管理所有可用的 [CodeRuntime] 实现。
 *
 * 供「一键运行」、AI 工具等统一查找匹配的运行时。
 */
class RuntimeRegistry {

    private val runtimes = ConcurrentHashMap<String, CodeRuntime>()

    /** 注册一个运行时。 */
    fun register(runtime: CodeRuntime) {
        runtimes[runtime.id] = runtime
    }

    /** 注销一个运行时。 */
    fun unregister(id: String) {
        runtimes.remove(id)
    }

    /** 按运行时 id 查找。 */
    fun find(id: String): CodeRuntime? = runtimes[id]

    /** 按文件扩展名查找匹配的运行时。 */
    fun findByExtension(ext: String): CodeRuntime? =
        runtimes.values.firstOrNull { ext.lowercase() in it.fileExtensions }

    /** 所有已注册的运行时。 */
    fun all(): List<CodeRuntime> = runtimes.values.toList()

    /** 所有可用的运行时。 */
    fun available(): List<CodeRuntime> = runtimes.values.filter { it.available }

    /** 清空注册表。 */
    fun clear() = runtimes.clear()
}