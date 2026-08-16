package com.miide.core.editor

import android.content.Context
import io.github.rosemoe.sora.lang.Language
import io.github.rosemoe.sora.langs.textmate.TextMateLanguage
import io.github.rosemoe.sora.langs.textmate.registry.FileProviderRegistry
import io.github.rosemoe.sora.langs.textmate.registry.GrammarRegistry
import io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry
import io.github.rosemoe.sora.langs.textmate.registry.dsl.languages
import io.github.rosemoe.sora.langs.textmate.registry.model.GrammarDefinition
import io.github.rosemoe.sora.langs.textmate.registry.provider.AssetsFileResolver

/**
 * 语法高亮管理器：基于 TextMate 语法（assets 内置语法包）。
 *
 * - 向 [FileProviderRegistry] 注册 assets 文件解析器；
 * - 定义支持的语言清单，并在 [GrammarRegistry] 中加载语法；
 * - 按文件扩展名返回对应 [TextMateLanguage]，找不到时返回 null（回退纯文本）。
 *
 * 语法资产位于 `assets/textmate/<lang>/` 目录，尚未内置时 [ready] 为 false，
 * 编辑器不启用高亮，不影响正常编辑。
 */
class SyntaxHighlighter(context: Context) {

    private val grammarRegistry = GrammarRegistry.getInstance()
    private val themeRegistry = ThemeRegistry.getInstance()

    /** 扩展名(小写) -> 作用域名(source.kotlin 等) */
    private val extensionToScope: Map<String, String>

    /** 作用域名 -> 语法定义 */
    private val definitions: Map<String, GrammarDefinition>

    /** 语法资产是否加载成功 */
    val ready: Boolean

    init {
        FileProviderRegistry.getInstance().addFileProvider(AssetsFileResolver(context.assets))
        val loaded = runCatching {
            val defs = languages {
                GrammarSpec.entries.forEach { spec ->
                    language(spec.configName) {
                        grammar = spec.grammarPath
                        scopeName = spec.scopeName
                        languageConfiguration = spec.languageConfigurationPath
                    }
                }
            }.build()
            grammarRegistry.loadGrammars(defs)
            defs
        }
        definitions = loaded.getOrDefault(emptyList()).mapNotNull { def ->
            val scope = def.scopeName ?: return@mapNotNull null
            scope to def
        }.toMap()
        extensionToScope = GrammarSpec.entries
            .flatMap { spec -> spec.extensions.map { it to spec.scopeName } }
            .toMap()
        ready = loaded.isSuccess
    }

    /**
     * 根据文件名推断语言。
     *
     * @param fileName 如 "Main.kt"、"index.js"；无扩展名时返回 null。
     */
    fun languageFor(fileName: String): Language? {
        if (fileName.isBlank()) return null
        val scope = extensionToScope[fileName.substringAfterLast('.', "").lowercase()] ?: return null
        val definition = definitions[scope] ?: return null
        return runCatching {
            TextMateLanguage.create(definition, grammarRegistry, themeRegistry, true)
        }.getOrNull()
    }

    /** 已注册的扩展名集合（用于文件类型图标 / 过滤器等）。 */
    fun supportedExtensions(): Set<String> = extensionToScope.keys

    private enum class GrammarSpec(
        val configName: String,
        val scopeName: String,
        val grammarPath: String,
        val languageConfigurationPath: String,
        val extensions: List<String>
    ) {
        Kotlin(
            "kotlin", "source.kotlin",
            "textmate/kotlin/syntaxes/Kotlin.tmLanguage.json",
            "textmate/kotlin/language-configuration.json",
            listOf("kt", "kts")
        ),
        Java(
            "java", "source.java",
            "textmate/java/syntaxes/java.tmLanguage.json",
            "textmate/java/language-configuration.json",
            listOf("java")
        ),
        JavaScript(
            "javascript", "source.js",
            "textmate/javascript/syntaxes/JavaScript.tmLanguage.json",
            "textmate/javascript/language-configuration.json",
            listOf("js", "mjs", "cjs")
        ),
        TypeScript(
            "typescript", "source.ts",
            "textmate/typescript/syntaxes/TypeScript.tmLanguage.json",
            "textmate/typescript/language-configuration.json",
            listOf("ts")
        ),
        Python(
            "python", "source.python",
            "textmate/python/syntaxes/python.tmLanguage.json",
            "textmate/python/language-configuration.json",
            listOf("py", "pyw")
        ),
        Go(
            "go", "source.go",
            "textmate/go/syntaxes/go.tmLanguage.json",
            "textmate/go/language-configuration.json",
            listOf("go")
        ),
        Rust(
            "rust", "source.rust",
            "textmate/rust/syntaxes/rust.tmLanguage.json",
            "textmate/rust/language-configuration.json",
            listOf("rs")
        ),
        C(
            "c", "source.c",
            "textmate/c/syntaxes/c.tmLanguage.json",
            "textmate/c/language-configuration.json",
            listOf("c", "h")
        ),
        Cpp(
            "cpp", "source.cpp",
            "textmate/cpp/syntaxes/cpp.tmLanguage.json",
            "textmate/cpp/language-configuration.json",
            listOf("cpp", "cc", "cxx", "hpp")
        ),
        Swift(
            "swift", "source.swift",
            "textmate/swift/syntaxes/swift.tmLanguage.json",
            "textmate/swift/language-configuration.json",
            listOf("swift")
        ),
        Json(
            "json", "source.json",
            "textmate/json/syntaxes/JSON.tmLanguage.json",
            "textmate/json/language-configuration.json",
            listOf("json", "jsonc")
        ),
        Xml(
            "xml", "text.xml",
            "textmate/xml/syntaxes/xml.tmLanguage.json",
            "textmate/xml/language-configuration.json",
            listOf("xml", "html", "htm", "svg")
        ),
        Css(
            "css", "source.css",
            "textmate/css/syntaxes/css.tmLanguage.json",
            "textmate/css/language-configuration.json",
            listOf("css")
        ),
        Markdown(
            "markdown", "text.html.markdown",
            "textmate/markdown/syntaxes/markdown.tmLanguage.json",
            "textmate/markdown/language-configuration.json",
            listOf("md", "markdown")
        ),
        Sql(
            "sql", "source.sql",
            "textmate/sql/syntaxes/sql.tmLanguage.json",
            "textmate/sql/language-configuration.json",
            listOf("sql")
        ),
        Shell(
            "shell", "source.shell",
            "textmate/shellscript/syntaxes/shell-unix-bash.tmLanguage.json",
            "textmate/shellscript/language-configuration.json",
            listOf("sh", "bash", "zsh")
        ),
        Yaml(
            "yaml", "source.yaml",
            "textmate/yaml/syntaxes/yaml.tmLanguage.json",
            "textmate/yaml/language-configuration.json",
            listOf("yaml", "yml")
        ),
        Toml(
            "toml", "source.toml",
            "textmate/toml/syntaxes/toml.tmLanguage.json",
            "textmate/toml/language-configuration.json",
            listOf("toml")
        ),
        KotlinScriptMarkup(
            "gradle", "source.groovy",
            "textmate/groovy/syntaxes/Groovy.tmLanguage.json",
            "textmate/groovy/language-configuration.json",
            listOf("gradle", "groovy")
        ),
    }
}