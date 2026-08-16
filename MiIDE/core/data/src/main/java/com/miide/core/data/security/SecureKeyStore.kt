package com.miide.core.data.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

/**
 * API Key 加密存储。
 *
 * 使用 Android Keystore 中的 AES-GCM 主密钥对敏感字符串加密，
 * 密文以 Base64 落库。密钥本体不可导出，即使数据库被提取也无法还原明文。
 */
class SecureKeyStore {

    companion object {
        private const val ANDROID_KEYSTORE = "AndroidKeyStore"
        private const val MASTER_KEY_ALIAS = "miide_master_key_v1"
        private const val TRANSFORMATION = "AES/GCM/NoPadding"
        private const val GCM_TAG_BITS = 128
        private const val IV_LENGTH_BYTES = 12
    }

    private val keyStore: KeyStore =
        KeyStore.getInstance(ANDROID_KEYSTORE).apply { load(null) }

    /** 加密明文，返回 Base64(iv + cipherText)，可安全落库。 */
    @Synchronized
    fun encrypt(plainText: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, getOrCreateMasterKey())
        val iv = cipher.iv
        val cipherText = cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))
        return Base64.encodeToString(iv + cipherText, Base64.NO_WRAP)
    }

    /** 解密由 [encrypt] 生成的密文；失败时返回 null（如密钥被清除）。 */
    @Synchronized
    fun decrypt(payload: String): String? = try {
        val raw = Base64.decode(payload, Base64.NO_WRAP)
        val iv = raw.copyOfRange(0, IV_LENGTH_BYTES)
        val cipherText = raw.copyOfRange(IV_LENGTH_BYTES, raw.size)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(
            Cipher.DECRYPT_MODE,
            getOrCreateMasterKey(),
            GCMParameterSpec(GCM_TAG_BITS, iv)
        )
        String(cipher.doFinal(cipherText), Charsets.UTF_8)
    } catch (e: Exception) {
        null
    }

    /** 清除主密钥（用于"清除全部敏感数据"）。 */
    @Synchronized
    fun clear() {
        keyStore.deleteEntry(MASTER_KEY_ALIAS)
    }

    private fun getOrCreateMasterKey(): SecretKey {
        (keyStore.getKey(MASTER_KEY_ALIAS, null) as? SecretKey)?.let { return it }
        val generator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEYSTORE)
        generator.init(
            KeyGenParameterSpec.Builder(
                MASTER_KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(256)
                .build()
        )
        return generator.generateKey()
    }
}
