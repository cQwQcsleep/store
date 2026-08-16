package com.miide.core.data.local

import androidx.room.TypeConverter
import com.miide.core.model.ChatRole
import com.miide.core.model.MessageStatus
import com.miide.core.model.ModelConfig
import com.miide.core.model.ProviderProtocol
import com.miide.core.model.ProviderType
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

/**
 * Room 类型转换器：枚举按 name 存取，复杂对象按 JSON 存取。
 */
class Converters {

    private val json = Json { ignoreUnknownKeys = true }

    // ---- 枚举 ----
    @TypeConverter
    fun providerTypeToString(value: ProviderType?): String? = value?.name

    @TypeConverter
    fun stringToProviderType(value: String?): ProviderType? =
        value?.let { runCatching { ProviderType.valueOf(it) }.getOrNull() }

    @TypeConverter
    fun providerProtocolToString(value: ProviderProtocol?): String? = value?.name

    @TypeConverter
    fun stringToProviderProtocol(value: String?): ProviderProtocol? =
        value?.let { runCatching { ProviderProtocol.valueOf(it) }.getOrNull() }

    @TypeConverter
    fun chatRoleToString(value: ChatRole?): String? = value?.name

    @TypeConverter
    fun stringToChatRole(value: String?): ChatRole? =
        value?.let { runCatching { ChatRole.valueOf(it) }.getOrNull() }

    @TypeConverter
    fun messageStatusToString(value: MessageStatus?): String? = value?.name

    @TypeConverter
    fun stringToMessageStatus(value: String?): MessageStatus? =
        value?.let { runCatching { MessageStatus.valueOf(it) }.getOrNull() }

    // ---- 集合 / 对象（JSON） ----
    @TypeConverter
    fun mapToString(value: Map<String, String>?): String =
        json.encodeToString(MapSerializer(String.serializer(), String.serializer()), value ?: emptyMap())

    @TypeConverter
    fun stringToMap(value: String?): Map<String, String> =
        value?.takeIf { it.isNotBlank() }?.let {
            runCatching {
                json.decodeFromString(MapSerializer(String.serializer(), String.serializer()), it)
            }.getOrDefault(emptyMap())
        } ?: emptyMap()

    @TypeConverter
    fun stringListToString(value: List<String>?): String =
        json.encodeToString(ListSerializer(String.serializer()), value ?: emptyList())

    @TypeConverter
    fun stringToStringList(value: String?): List<String> =
        value?.takeIf { it.isNotBlank() }?.let {
            runCatching {
                json.decodeFromString(ListSerializer(String.serializer()), it)
            }.getOrDefault(emptyList())
        } ?: emptyList()

    @TypeConverter
    fun modelConfigListToString(value: List<ModelConfig>?): String =
        json.encodeToString(ListSerializer(ModelConfig.serializer()), value ?: emptyList())

    @TypeConverter
    fun stringToModelConfigList(value: String?): List<ModelConfig> =
        value?.takeIf { it.isNotBlank() }?.let {
            runCatching {
                json.decodeFromString(ListSerializer(ModelConfig.serializer()), it)
            }.getOrDefault(emptyList())
        } ?: emptyList()
}
