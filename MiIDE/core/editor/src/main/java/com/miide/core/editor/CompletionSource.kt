package com.miide.core.editor

/**
 * 补全上下文：一次建议计算所需的全部输入。
 */
data class CompletionContext(
    val text: String,
    val cursorLine: Int,
    val cursorColumn: Int,
    val fileExtension: String,
    val prefix: String,
)

/**
 * 补全建议来源：给定上下文返回候选词列表（按优先级排序，首个为最佳建议）。
 */
interface CompletionSource {
    fun suggestions(context: CompletionContext): List<String>
}

/**
 * 文档标识符 + 语言关键字补全。
 *
 * - 从当前文档统计出现的标识符（[A-Za-z_][A-Za-z0-9_]*），按出现频次优先、长度次之排序；
 * - 过滤掉与光标前缀相同的单词，保留前缀匹配的候选；
 * - 追加少量常用语言关键字（按扩展名）。
 *
 * 该来源无状态、不依赖外部语言服务，适合作为 M2 内置的第一版补全来源。
 */
class DocumentCompletionSource : CompletionSource {

    override fun suggestions(context: CompletionContext): List<String> {
        val prefix = context.prefix
        val freq = identifierFrequency(context.text)
        val candidates = sortedSetOf<String>()

        // 文档标识符优先（频次高者在前）
        for ((word, _) in freq) {
            if (word != prefix && word.startsWith(prefix, ignoreCase = true)) {
                candidates.add(word)
            }
        }
        // 关键字兜底
        for (word in keywordsFor(context.fileExtension)) {
            if (word != prefix && word.startsWith(prefix, ignoreCase = true)) {
                candidates.add(word)
            }
        }

        return candidates
            .sortedWith(
                compareByDescending<String> { freq[it] ?: 0 }
                    .thenBy { it.length }
            )
            .take(MAX_CANDIDATES)
    }

    /** 统计文档中标识符出现频次。 */
    private fun identifierFrequency(text: String): Map<String, Int> {
        val freq = HashMap<String, Int>()
        for (m in IDENTIFIER.findAll(text)) {
            freq.merge(m.value, 1, Int::plus)
        }
        return freq
    }

    /** 按文件扩展名返回常用关键字。 */
    private fun keywordsFor(ext: String): List<String> =
        KEYWORDS[ext] ?: emptyList()

    private companion object {
        val IDENTIFIER = Regex("[A-Za-z_][A-Za-z0-9_]*")

        const val MAX_CANDIDATES = 20

        /** 常用语言关键字（按小写扩展名）。 */
        val KEYWORDS: Map<String, List<String>> = mapOf(
            "kt" to listOf(
                "fun", "val", "var", "class", "object", "interface", "data", "sealed",
                "enum", "when", "if", "else", "for", "while", "return", "true", "false",
                "null", "private", "public", "internal", "protected", "override", "open",
                "abstract", "import", "package", "suspend", "companion", "init", "this", "super",
                "try", "catch", "finally", "throw", "is", "in", "as", "by", "lateinit"
            ),
            "java" to listOf(
                "public", "private", "protected", "class", "interface", "extends", "implements",
                "static", "final", "void", "int", "long", "double", "boolean", "String",
                "new", "return", "if", "else", "for", "while", "switch", "case", "break",
                "try", "catch", "finally", "throw", "throws", "import", "package", "this",
                "super", "null", "true", "false", "abstract", "enum", "synchronized"
            ),
            "js" to listOf(
                "function", "const", "let", "var", "return", "if", "else", "for", "while",
                "class", "new", "import", "export", "default", "from", "async", "await",
                "try", "catch", "finally", "throw", "typeof", "instanceof", "null", "true",
                "false", "undefined", "this", "switch", "case", "break", "continue", "do",
                "extends", "super", "yield", "delete", "in", "of"
            ),
            "ts" to listOf(
                "function", "const", "let", "var", "return", "if", "else", "for", "while",
                "class", "interface", "type", "enum", "new", "import", "export", "default",
                "from", "async", "await", "try", "catch", "finally", "throw", "null", "true",
                "false", "undefined", "this", "switch", "case", "break", "continue", "extends",
                "implements", "readonly", "private", "public", "protected", "static", "as",
                "in", "of", "keyof", "namespace"
            ),
            "py" to listOf(
                "def", "class", "return", "if", "elif", "else", "for", "while", "import",
                "from", "as", "with", "try", "except", "finally", "raise", "yield", "lambda",
                "pass", "break", "continue", "global", "nonlocal", "del", "assert", "None",
                "True", "False", "and", "or", "not", "is", "in", "async", "await"
            ),
            "go" to listOf(
                "func", "package", "import", "var", "const", "type", "struct", "interface",
                "map", "chan", "if", "else", "for", "range", "switch", "case", "return",
                "go", "defer", "select", "break", "continue", "fallthrough", "nil", "true",
                "false", "make", "new", "len", "cap", "append"
            ),
            "rs" to listOf(
                "fn", "let", "mut", "const", "static", "struct", "enum", "trait", "impl",
                "mod", "use", "pub", "crate", "self", "Self", "if", "else", "match", "for",
                "while", "loop", "return", "break", "continue", "async", "await", "move",
                "ref", "type", "where", "unsafe", "true", "false", "None", "Some", "Ok", "Err"
            ),
            "c" to listOf(
                "int", "char", "float", "double", "long", "short", "unsigned", "signed",
                "void", "struct", "union", "enum", "typedef", "static", "extern", "const",
                "volatile", "register", "auto", "return", "if", "else", "for", "while",
                "do", "switch", "case", "default", "break", "continue", "goto", "sizeof",
                "include", "define"
            ),
            "cpp" to listOf(
                "int", "char", "float", "double", "long", "short", "unsigned", "signed",
                "void", "struct", "union", "enum", "class", "namespace", "template", "typename",
                "typedef", "using", "static", "extern", "const", "constexpr", "inline", "virtual",
                "override", "final", "return", "if", "else", "for", "while", "do", "switch",
                "case", "default", "break", "continue", "new", "delete", "this", "true", "false",
                "include", "define", "public", "private", "protected"
            ),
            "swift" to listOf(
                "func", "var", "let", "class", "struct", "enum", "protocol", "extension",
                "if", "else", "guard", "for", "while", "repeat", "switch", "case", "return",
                "import", "public", "private", "internal", "fileprivate", "static", "final",
                "override", "init", "deinit", "self", "super", "true", "false", "nil", "try",
                "catch", "throw", "throws", "in", "as", "is", "where", "open"
            ),
            "sql" to listOf(
                "SELECT", "FROM", "WHERE", "INSERT", "INTO", "UPDATE", "SET", "DELETE",
                "CREATE", "TABLE", "ALTER", "DROP", "INDEX", "VIEW", "JOIN", "LEFT", "RIGHT",
                "INNER", "OUTER", "ON", "GROUP", "ORDER", "BY", "HAVING", "LIMIT", "OFFSET",
                "VALUES", "PRIMARY", "KEY", "FOREIGN", "REFERENCES", "UNIQUE", "NULL", "AND",
                "OR", "NOT", "AS", "DISTINCT", "COUNT", "SUM", "AVG", "MIN", "MAX"
            ),
            "sh" to listOf(
                "if", "then", "else", "elif", "fi", "for", "while", "until", "do", "done",
                "case", "esac", "function", "return", "exit", "export", "local", "readonly",
                "echo", "true", "false", "break", "continue", "shift", "set", "unset", "test"
            ),
        )
    }
}
