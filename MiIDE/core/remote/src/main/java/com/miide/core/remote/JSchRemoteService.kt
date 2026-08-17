package com.miide.core.remote

import com.jcraft.jsch.ChannelExec
import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.FileInputStream

/**
 * 基于 JSch 的 SSH/SFTP 实现（IO 线程执行，避免阻塞主线程）。
 *
 * 每次操作独立建立会话与通道，操作完成即断开，避免长连接占用。
 */
class JSchRemoteService : RemoteService {

    override suspend fun testConnection(profile: RemoteProfile): RemoteResult<Unit> = withContext(Dispatchers.IO) {
        remoteRun {
            withSession(profile) { /* 建立即断开即可验证连通性 */ }
        }
    }

    override suspend fun list(profile: RemoteProfile, path: String): RemoteResult<List<RemoteEntry>> =
        withContext(Dispatchers.IO) {
            remoteRun {
                withSession(profile) { session ->
                    val channel = session.openChannel("sftp") as ChannelSftp
                    channel.connect(CONNECT_TIMEOUT)
                    try {
                        val normalized = normalizePath(path)
                        val raw = channel.ls(normalized)
                        val entries = ArrayList<RemoteEntry>()
                        for (item in raw) {
                            if (item is com.jcraft.jsch.ChannelSftp.LsEntry) {
                                val name = item.filename
                                if (name == "." || name == "..") continue
                                val attrs = item.attrs
                                val fullPath = if (normalized == "/") "/$name" else "$normalized/$name"
                                entries.add(
                                    RemoteEntry(
                                        name = name,
                                        path = fullPath,
                                        isDir = attrs.isDir,
                                        size = attrs.getSize(),
                                        lastModified = attrs.getMTime() * 1000L,
                                        permissions = attrs.getPermissionsString()
                                    )
                                )
                            }
                        }
                        entries.sortedWith(compareByDescending<RemoteEntry> { it.isDir }.thenBy { it.name })
                    } finally {
                        channel.disconnect()
                    }
                }
            }
        }

    override suspend fun read(profile: RemoteProfile, path: String): RemoteResult<String> =
        withContext(Dispatchers.IO) {
            remoteRun {
                withSession(profile) { session ->
                    val channel = session.openChannel("sftp") as ChannelSftp
                    channel.connect(CONNECT_TIMEOUT)
                    try {
                        channel.get(path).use { input ->
                            input.readBytes().toString(Charsets.UTF_8)
                        }
                    } finally {
                        channel.disconnect()
                    }
                }
            }
        }

    override suspend fun write(profile: RemoteProfile, path: String, content: String): RemoteResult<Unit> =
        withContext(Dispatchers.IO) {
            remoteRun {
                withSession(profile) { session ->
                    val channel = session.openChannel("sftp") as ChannelSftp
                    channel.connect(CONNECT_TIMEOUT)
                    try {
                        channel.put(ByteArrayInputStream(content.toByteArray(Charsets.UTF_8)), path)
                    } finally {
                        channel.disconnect()
                    }
                }
            }
        }

    override suspend fun upload(profile: RemoteProfile, localPath: String, remotePath: String): RemoteResult<Unit> =
        withContext(Dispatchers.IO) {
            remoteRun {
                withSession(profile) { session ->
                    val channel = session.openChannel("sftp") as ChannelSftp
                    channel.connect(CONNECT_TIMEOUT)
                    try {
                        FileInputStream(localPath).use { input ->
                            channel.put(input, remotePath)
                        }
                    } finally {
                        channel.disconnect()
                    }
                }
            }
        }

    override suspend fun exec(profile: RemoteProfile, command: String): RemoteResult<String> =
        withContext(Dispatchers.IO) {
            remoteRun {
                withSession(profile) { session ->
                    val channel = session.openChannel("exec") as ChannelExec
                    try {
                        channel.setCommand(command)
                        channel.setInputStream(null)
                        val out = ByteArrayOutputStream()
                        channel.setOutputStream(out)
                        channel.setErrStream(out, true)
                        channel.connect(CONNECT_TIMEOUT)
                        while (!channel.isClosed) {
                            Thread.sleep(50)
                        }
                        out.toString(Charsets.UTF_8.name())
                    } finally {
                        channel.disconnect()
                    }
                }
            }
        }

    // ---- 内部 ----

    private inline fun <T> withSession(profile: RemoteProfile, block: (Session) -> T): T {
        val session = createSession(profile)
        try {
            return block(session)
        } finally {
            session.disconnect()
        }
    }

    private fun createSession(profile: RemoteProfile): Session {
        val jsch = JSch()
        if (profile.authType == RemoteAuth.PRIVATE_KEY && profile.privateKey.isNotBlank()) {
            jsch.addIdentity(
                "miide",
                profile.privateKey.toByteArray(Charsets.UTF_8),
                null,
                profile.passphrase.ifBlank { null }?.toByteArray(Charsets.UTF_8)
            )
        }
        val session = jsch.getSession(profile.username, profile.host, profile.port)
        if (profile.authType == RemoteAuth.PASSWORD && profile.password.isNotBlank()) {
            session.setPassword(profile.password)
        }
        session.setConfig("StrictHostKeyChecking", "no")
        session.setConfig("PreferredAuthentications", "publickey,password,keyboard-interactive")
        session.connect(CONNECT_TIMEOUT)
        return session
    }

    private fun normalizePath(path: String): String {
        val p = path.trim()
        if (p.isEmpty()) return "/"
        val joined = if (p.startsWith("/")) p else "/$p"
        val parts = joined.split('/').filter { it.isNotEmpty() && it != "." }
        return if (parts.isEmpty()) "/" else "/" + parts.joinToString("/")
    }

    private companion object {
        const val CONNECT_TIMEOUT = 15_000
    }
}
