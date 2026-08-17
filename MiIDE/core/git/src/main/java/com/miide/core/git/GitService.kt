package com.miide.core.git

/** 分支信息。 */
data class GitBranch(
    val name: String,
    val isCurrent: Boolean
)

/** 文件变更状态。 */
data class GitStatusEntry(
    val path: String,
    val status: StatusKind
)

enum class StatusKind {
    ADDED, MODIFIED, DELETED, UNTRACKED, CONFLICTING
}

/** 提交日志。 */
data class GitCommit(
    val id: String,
    val shortId: String,
    val message: String,
    val author: String,
    val time: Long
)

/** 远程仓库信息。 */
data class GitRemote(
    val name: String,
    val url: String
)

/** Git 操作结果。 */
data class GitResult<T>(
    val success: Boolean,
    val data: T? = null,
    val error: String? = null
)

/**
 * Git 操作服务接口。
 * 所有路径均为本地文件系统绝对路径，操作在 IO 线程执行。
 */
interface GitService {

    /** 检查目录是否已是 Git 仓库。 */
    suspend fun isRepository(repoDir: String): Boolean

    /** 克隆远程仓库到本地目录。 */
    suspend fun clone(url: String, dest: String, branch: String? = null): GitResult<Unit>

    /** 初始化新仓库。 */
    suspend fun init(repoDir: String): GitResult<Unit>

    /** 获取当前分支列表。 */
    suspend fun branches(repoDir: String): GitResult<List<GitBranch>>

    /** 切换分支（已存在则 checkout，不存在则新建）。 */
    suspend fun checkout(repoDir: String, branch: String, create: Boolean = false): GitResult<Unit>

    /** 获取文件变更状态。 */
    suspend fun status(repoDir: String, paths: List<String>? = null): GitResult<List<GitStatusEntry>>

    /** 暂存文件。 */
    suspend fun add(repoDir: String, paths: List<String> = listOf(".")): GitResult<Unit>

    /** 取消暂存。 */
    suspend fun reset(repoDir: String, paths: List<String> = listOf(".")): GitResult<Unit>

    /** 提交暂存内容。 */
    suspend fun commit(repoDir: String, message: String, amend: Boolean = false): GitResult<Unit>

    /** 拉取远程变更。 */
    suspend fun pull(repoDir: String, remote: String = "origin", branch: String? = null): GitResult<String>

    /** 推送本地提交到远程。 */
    suspend fun push(repoDir: String, remote: String = "origin", branch: String? = null): GitResult<String>

    /** 获取提交日志。 */
    suspend fun log(repoDir: String, maxCount: Int = 20): GitResult<List<GitCommit>>

    /** 获取远程仓库列表。 */
    suspend fun remotes(repoDir: String): GitResult<List<GitRemote>>

    /** 添加远程仓库。 */
    suspend fun addRemote(repoDir: String, name: String, url: String): GitResult<Unit>

    /** 移除远程仓库。 */
    suspend fun removeRemote(repoDir: String, name: String): GitResult<Unit>

    /** 对比工作区与暂存区/HEAD 的差异（返回统一 diff 格式文本）。 */
    suspend fun diff(repoDir: String, paths: List<String>? = null): GitResult<String>
}