package com.miide.core.model

/**
 * 文件树节点（供文件浏览器使用）。
 */
data class FileNode(
    val name: String,
    val path: String,
    val isDirectory: Boolean,
    val size: Long = 0,
    val modifiedAt: Long = 0
) {
    val isCodeFile: Boolean
        get() = CODE_EXTENSIONS.any { name.endsWith(it, ignoreCase = true) }

    companion object {
        private val CODE_EXTENSIONS = listOf(
            ".kt", ".kts", ".java", ".py", ".js", ".ts", ".jsx", ".tsx",
            ".c", ".h", ".cpp", ".hpp", ".cs", ".go", ".rs", ".swift",
            ".html", ".css", ".scss", ".json", ".xml", ".yaml", ".yml",
            ".toml", ".md", ".txt", ".sh", ".sql", ".php", ".rb", ".lua"
        )
    }
}
