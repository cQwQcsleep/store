package com.miide.core.data.repository

import com.miide.core.data.local.dao.PluginDao
import com.miide.core.data.local.entity.PluginEntity
import com.miide.core.plugin.InstalledPlugin
import com.miide.core.plugin.PluginManifest
import com.miide.core.plugin.PluginPackage
import com.miide.core.plugin.PluginType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

/**
 * 已安装插件仓库。
 */
class PluginRepository(
    private val dao: PluginDao,
    private val json: Json
) {

    fun observeInstalled(): Flow<List<InstalledPlugin>> =
        dao.observeAll().map { entities -> entities.map { it.toInstalled() } }

    suspend fun getInstalled(id: String): InstalledPlugin? =
        dao.getById(id)?.toInstalled()

    suspend fun install(pkg: PluginPackage) {
        val m = pkg.manifest
        dao.upsert(
            PluginEntity(
                id = m.id,
                name = m.name,
                version = m.version,
                description = m.description,
                author = m.author,
                type = m.type.name,
                entry = m.entry,
                script = pkg.code,
                permissionsJson = json.encodeToString(ListSerializer(String.serializer()), m.permissions),
                icon = m.icon,
                homepage = m.homepage,
                enabled = true
            )
        )
    }

    suspend fun uninstall(id: String) {
        dao.deleteById(id)
    }

    suspend fun setEnabled(id: String, enabled: Boolean) {
        dao.setEnabled(id, enabled)
    }

    private fun PluginEntity.toInstalled() = InstalledPlugin(
        manifest = PluginManifest(
            id = id,
            name = name,
            version = version,
            description = description,
            author = author,
            type = runCatching { PluginType.valueOf(type) }.getOrDefault(PluginType.SCRIPT),
            entry = entry,
            script = script,
            permissions = runCatching { json.decodeFromString<List<String>>(permissionsJson) }.getOrDefault(emptyList()),
            icon = icon,
            homepage = homepage
        ),
        enabled = enabled,
        installedAt = installedAt
    )
}
