package com.miide.core.remote

/** 远程目录条目。 */
data class RemoteEntry(
    val name: String,
    val path: String,
    val isDir: Boolean,
    val size: Long,
    val lastModified: Long,
    val permissions: String
)

/** 统一结果封装：成功带数据，失败带可读错误信息。 */
sealed class RemoteResult<out T> {
    data class Success<T>(val data: T) : RemoteResult<T>()
    data class Failure(val message: String) : RemoteResult<Nothing>()
}

inline fun <T> remoteRun(block: () -> T): RemoteResult<T> = try {
    RemoteResult.Success(block())
} catch (e: Exception) {
    RemoteResult.Failure(e.message ?: e.javaClass.simpleName)
}

/**
 * 远程 SSH/SFTP 能力接口。
 * 所有方法均为挂起函数；实现内部切到 IO 线程执行网络阻塞操作。
 */
interface RemoteService {

    /** 测试连接是否可用（建立会话即断）。 */
    suspend fun testConnection(profile: RemoteProfile): RemoteResult<Unit>

    /** 列出远程目录内容（目录优先，按名称排序）。 */
    suspend fun list(profile: RemoteProfile, path: String): RemoteResult<List<RemoteEntry>>

    /** 读取远程文本文件内容（UTF-8）。 */
    suspend fun read(profile: RemoteProfile, path: String): RemoteResult<String>

    /** 写入远程文本文件（UTF-8，覆盖）。 */
    suspend fun write(profile: RemoteProfile, path: String, content: String): RemoteResult<Unit>

    /** 上传本地文件到远程路径。 */
    suspend fun upload(profile: RemoteProfile, localPath: String, remotePath: String): RemoteResult<Unit>

    /** 执行远程 shell 命令，返回合并输出（stdout + stderr）。 */
    suspend fun exec(profile: RemoteProfile, command: String): RemoteResult<String>
}
