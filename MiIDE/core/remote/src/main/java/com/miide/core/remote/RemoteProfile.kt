package com.miide.core.remote

import java.util.UUID

/** 认证方式。 */
object RemoteAuth {
    const val PASSWORD = "password"
    const val PRIVATE_KEY = "key"
}

/**
 * 远程主机连接配置（SSH/SFTP）。
 * 密码 / 私钥以明文保存在本模块模型中，落库时由数据层做 Keystore 加密。
 */
data class RemoteProfile(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val host: String = "",
    val port: Int = 22,
    val username: String = "",
    val authType: String = RemoteAuth.PASSWORD,
    val password: String = "",
    /** PEM 私钥内容（authType = key 时）。 */
    val privateKey: String = "",
    /** 私钥口令（可选）。 */
    val passphrase: String = "",
    /** 初始浏览路径。 */
    val basePath: String = "/",
    val createdAt: Long = System.currentTimeMillis()
) {
    val displayName: String
        get() = name.ifBlank { "$username@$host:$port" }
}
