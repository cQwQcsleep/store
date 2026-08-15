package com.skyauto.app.data.network

import java.security.SecureRandom
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * AES-256-GCM 加解密，与站点前端保持一致。
 *
 * 站点前端逻辑：
 *  - GET {base}/crypto/key 返回 base64(raw 32字节AES密钥)
 *  - 请求体：随机12字节IV + 密文，整体 base64 编码
 *  - 响应体：若响应头 X-Encrypted==1，同样为 base64(IV + 密文)
 *
 * 本类为无状态工具，密钥由 [ApiKeys] 负责缓存。
 */
object Crypto {

    private const val IV_LENGTH = 12
    private const val TAG_LENGTH_BITS = 128

    private val random = SecureRandom()

    fun encrypt(key: ByteArray, plaintext: ByteArray): ByteArray {
        val iv = ByteArray(IV_LENGTH).also(random::nextBytes)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(key, "AES"), GCMParameterSpec(TAG_LENGTH_BITS, iv))
        val cipherText = cipher.doFinal(plaintext)
        return iv + cipherText
    }

    fun decrypt(key: ByteArray, payload: ByteArray): ByteArray {
        require(payload.size > IV_LENGTH) { "payload too short" }
        val iv = payload.copyOfRange(0, IV_LENGTH)
        val cipherText = payload.copyOfRange(IV_LENGTH, payload.size)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(key, "AES"), GCMParameterSpec(TAG_LENGTH_BITS, iv))
        return cipher.doFinal(cipherText)
    }

    fun encryptToBase64(key: ByteArray, plaintext: ByteArray): String =
        Base64.getEncoder().encodeToString(encrypt(key, plaintext))

    fun decryptFromBase64(key: ByteArray, payloadB64: String): ByteArray =
        decrypt(key, Base64.getDecoder().decode(payloadB64))
}