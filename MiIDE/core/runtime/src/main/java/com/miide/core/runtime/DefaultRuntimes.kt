package com.miide.core.runtime

/**
 * 默认运行时装配：创建包含内置 JS / Lua 运行时的注册表。
 *
 * Python（Chaquopy）运行时由 app 模块在启动时额外注册，避免核心库依赖
 * Chaquopy 生成的代码。
 */
object DefaultRuntimes {

    fun create(): RuntimeRegistry = RuntimeRegistry().apply {
        register(JsQuickRuntime())
        register(LuaRuntime())
    }
}
