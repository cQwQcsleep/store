package com.miide.core.data.repository

import com.miide.core.data.local.dao.RemoteProfileDao
import com.miide.core.data.local.entity.RemoteProfileEntity
import com.miide.core.data.security.SecureKeyStore
import com.miide.core.remote.RemoteProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * 远程主机配置仓库。
 *
 * 密码 / 私钥 / 口令采用 Keystore 加密后落库；读取时自动解密。
 */
class RemoteRepository(
    private val dao: RemoteProfileDao,
    private val secureKeyStore: SecureKeyStore
) {

    fun observeAll(): Flow<List<RemoteProfile>> =
        dao.observeAll().map { entities -> entities.map { it.toProfile() } }

    suspend fun getAll(): List<RemoteProfile> =
        dao.getAll().map { it.toProfile() }

    suspend fun getById(id: String): RemoteProfile? =
        dao.getById(id)?.toProfile()

    suspend fun upsert(profile: RemoteProfile) {
        dao.upsert(profile.toEntity())
    }

    suspend fun deleteById(id: String) {
        dao.deleteById(id)
    }

    private fun RemoteProfile.toEntity() = RemoteProfileEntity(
        id = id,
        name = name,
        host = host,
        port = port,
        username = username,
        authType = authType,
        passwordEnc = password.takeIf { it.isNotBlank() }?.let { secureKeyStore.encrypt(it) },
        privateKeyEnc = privateKey.takeIf { it.isNotBlank() }?.let { secureKeyStore.encrypt(it) },
        passphraseEnc = passphrase.takeIf { it.isNotBlank() }?.let { secureKeyStore.encrypt(it) },
        basePath = basePath,
        createdAt = createdAt
    )

    private fun RemoteProfileEntity.toProfile() = RemoteProfile(
        id = id,
        name = name,
        host = host,
        port = port,
        username = username,
        authType = authType,
        password = passwordEnc?.let { secureKeyStore.decrypt(it) } ?: "",
        privateKey = privateKeyEnc?.let { secureKeyStore.decrypt(it) } ?: "",
        passphrase = passphraseEnc?.let { secureKeyStore.decrypt(it) } ?: "",
        basePath = basePath,
        createdAt = createdAt
    )
}
