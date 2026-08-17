package com.miide.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 远程主机配置实体。password / privateKey 为 Keystore 加密后的 Base64 密文。
 */
@Entity(tableName = "remote_profiles")
data class RemoteProfileEntity(
    @PrimaryKey val id: String,
    val name: String,
    val host: String,
    val port: Int,
    val username: String,
    val authType: String,
    val passwordEnc: String? = null,
    val privateKeyEnc: String? = null,
    val passphraseEnc: String? = null,
    val basePath: String = "/",
    val createdAt: Long = System.currentTimeMillis()
)
