package com.miide.core.editor.theme

import android.content.Context
import io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry
import io.github.rosemoe.sora.langs.textmate.registry.model.ThemeModel
import org.eclipse.tm4e.core.registry.IThemeSource

/**
 * 编辑器主题管理器：负责从 assets 加载 HyperOS TextMate 主题模型，
 * 并基于 [ThemeRegistry] 创建 [MiEditorTheme] 或在深浅色之间切换。
 *
 * 生命周期约定：
 * - 应用启动时由单例持有，首次访问时惰性加载主题模型；
 * - [createTheme] 供编辑器组件按需创建配色方案；
 * - [setTheme] 通过 [ThemeRegistry] 广播切换，已注册的编辑器自动跟随。
 */
class ThemeManager(private val context: Context) {

    private val themeRegistry = ThemeRegistry.getInstance()

    private var darkModel: ThemeModel? = null
    private var lightModel: ThemeModel? = null

    /** 创建指定模式的编辑器主题（框体 + token 融合的 HyperOS 主题）。 */
    fun createTheme(dark: Boolean): MiEditorTheme =
        MiEditorTheme(themeRegistry, themeModel(dark))

    /** 切换全局主题并广播给所有已注册的编辑器。 */
    fun setTheme(dark: Boolean) {
        themeRegistry.setTheme(themeModel(dark))
    }

    /** 当前主题对应的 ThemeModel（供外部查询，如语法高亮器同步）。 */
    fun currentModel(): ThemeModel = themeRegistry.getCurrentThemeModel()

    private fun themeModel(dark: Boolean): ThemeModel {
        val cached = if (dark) darkModel else lightModel
        if (cached != null) return cached
        val path = if (dark) DARK_THEME_PATH else LIGHT_THEME_PATH
        val source = IThemeSource.fromInputStream(
            context.assets.open(path),
            path,
            Charsets.UTF_8
        )
        val model = ThemeModel(source).also {
            it.setDark(dark)
            // 注册进 ThemeRegistry 并设为当前主题（幂等：同名已注册时仅切换）
            themeRegistry.loadTheme(it)
        }
        if (dark) darkModel = model else lightModel = model
        return model
    }

    companion object {
        /** 内置主题资源路径（assets）。 */
        const val DARK_THEME_PATH = "textmate/miide-dark.json"
        const val LIGHT_THEME_PATH = "textmate/miide-light.json"
    }
}
