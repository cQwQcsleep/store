package com.miide.core.data.settings

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.miide.core.data.model.AppPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.preferencesDataStore

private val Context.preferencesDataStore by preferencesDataStore(name = "miide_preferences")

/** 偏好设置键。 */
private object PrefKeys {
    val themeMode = stringPreferencesKey("theme_mode")
    val dynamicColorEnabled = booleanPreferencesKey("dynamic_color_enabled")
    val fontSize = intPreferencesKey("font_size")
    val lineWrap = booleanPreferencesKey("line_wrap")
    val tabSize = intPreferencesKey("tab_size")
    val autoSave = booleanPreferencesKey("auto_save")
    val autoSaveIntervalMs = longPreferencesKey("auto_save_interval_ms")
    val defaultProviderId = stringPreferencesKey("default_provider_id")
    val defaultModelId = stringPreferencesKey("default_model_id")
    val firstLaunch = booleanPreferencesKey("first_launch")
    val editorFontLigatures = booleanPreferencesKey("editor_font_ligatures")
    val editorShowLineNumbers = booleanPreferencesKey("editor_show_line_numbers")
    val editorShowWhitespace = booleanPreferencesKey("editor_show_whitespace")
    val aiAutoComplete = booleanPreferencesKey("ai_auto_complete")
    val aiAutoCompleteMode = stringPreferencesKey("ai_auto_complete_mode")
    val aiStreamResponse = booleanPreferencesKey("ai_stream_response")
    val aiShowReasoning = booleanPreferencesKey("ai_show_reasoning")
    val aiShowTokenUsage = booleanPreferencesKey("ai_show_token_usage")
    val aiMaxRetries = intPreferencesKey("ai_max_retries")
    val terminalShell = stringPreferencesKey("terminal_shell")
    val lastOpenedProjectId = stringPreferencesKey("last_opened_project_id")
    val lastOpenedFilePath = stringPreferencesKey("last_opened_file_path")
}

/**
 * 应用偏好设置管理器。
 * 所有读写通过 DataStore 进行，默认值集中定义在 [AppPreferences] 中。
 */
class PreferencesManager(private val context: Context) {

    /** 观察完整偏好。 */
    val preferences: Flow<AppPreferences> = context.preferencesDataStore.data.map { prefs ->
        AppPreferences(
            themeMode = prefs[PrefKeys.themeMode] ?: AppPreferences().themeMode,
            dynamicColorEnabled = prefs[PrefKeys.dynamicColorEnabled] ?: AppPreferences().dynamicColorEnabled,
            fontSize = prefs[PrefKeys.fontSize] ?: AppPreferences().fontSize,
            lineWrap = prefs[PrefKeys.lineWrap] ?: AppPreferences().lineWrap,
            tabSize = prefs[PrefKeys.tabSize] ?: AppPreferences().tabSize,
            autoSave = prefs[PrefKeys.autoSave] ?: AppPreferences().autoSave,
            autoSaveIntervalMs = prefs[PrefKeys.autoSaveIntervalMs] ?: AppPreferences().autoSaveIntervalMs,
            defaultProviderId = prefs[PrefKeys.defaultProviderId],
            defaultModelId = prefs[PrefKeys.defaultModelId],
            firstLaunch = prefs[PrefKeys.firstLaunch] ?: AppPreferences().firstLaunch,
            editorFontLigatures = prefs[PrefKeys.editorFontLigatures] ?: AppPreferences().editorFontLigatures,
            editorShowLineNumbers = prefs[PrefKeys.editorShowLineNumbers] ?: AppPreferences().editorShowLineNumbers,
            editorShowWhitespace = prefs[PrefKeys.editorShowWhitespace] ?: AppPreferences().editorShowWhitespace,
            aiAutoComplete = prefs[PrefKeys.aiAutoComplete] ?: AppPreferences().aiAutoComplete,
            aiAutoCompleteMode = prefs[PrefKeys.aiAutoCompleteMode] ?: AppPreferences().aiAutoCompleteMode,
            aiStreamResponse = prefs[PrefKeys.aiStreamResponse] ?: AppPreferences().aiStreamResponse,
            aiShowReasoning = prefs[PrefKeys.aiShowReasoning] ?: AppPreferences().aiShowReasoning,
            aiShowTokenUsage = prefs[PrefKeys.aiShowTokenUsage] ?: AppPreferences().aiShowTokenUsage,
            aiMaxRetries = prefs[PrefKeys.aiMaxRetries] ?: AppPreferences().aiMaxRetries,
            terminalShell = prefs[PrefKeys.terminalShell] ?: AppPreferences().terminalShell,
            lastOpenedProjectId = prefs[PrefKeys.lastOpenedProjectId],
            lastOpenedFilePath = prefs[PrefKeys.lastOpenedFilePath]
        )
    }

    /** 单项观察。 */
    fun observeString(key: String): Flow<String?> =
        context.preferencesDataStore.data.map { it[stringPreferencesKey(key)] }

    fun observeBoolean(key: String, default: Boolean = false): Flow<Boolean> =
        context.preferencesDataStore.data.map { it[booleanPreferencesKey(key)] ?: default }

    // ---- 写入方法 ----

    suspend fun setThemeMode(mode: String) {
        context.preferencesDataStore.edit { it[PrefKeys.themeMode] = mode }
    }

    suspend fun setDynamicColorEnabled(enabled: Boolean) {
        context.preferencesDataStore.edit { it[PrefKeys.dynamicColorEnabled] = enabled }
    }

    suspend fun setFontSize(size: Int) {
        context.preferencesDataStore.edit { it[PrefKeys.fontSize] = size }
    }

    suspend fun setLineWrap(enabled: Boolean) {
        context.preferencesDataStore.edit { it[PrefKeys.lineWrap] = enabled }
    }

    suspend fun setTabSize(size: Int) {
        context.preferencesDataStore.edit { it[PrefKeys.tabSize] = size }
    }

    suspend fun setAutoSave(enabled: Boolean) {
        context.preferencesDataStore.edit { it[PrefKeys.autoSave] = enabled }
    }

    suspend fun setDefaultProviderId(id: String?) {
        context.preferencesDataStore.edit {
            if (id == null) it.remove(PrefKeys.defaultProviderId)
            else it[PrefKeys.defaultProviderId] = id
        }
    }

    suspend fun setDefaultModelId(id: String?) {
        context.preferencesDataStore.edit {
            if (id == null) it.remove(PrefKeys.defaultModelId)
            else it[PrefKeys.defaultModelId] = id
        }
    }

    suspend fun setFirstLaunch(first: Boolean) {
        context.preferencesDataStore.edit { it[PrefKeys.firstLaunch] = first }
    }

    suspend fun setAiAutoComplete(enabled: Boolean) {
        context.preferencesDataStore.edit { it[PrefKeys.aiAutoComplete] = enabled }
    }

    suspend fun setAiAutoCompleteMode(mode: String) {
        context.preferencesDataStore.edit { it[PrefKeys.aiAutoCompleteMode] = mode }
    }

    suspend fun setAiMaxRetries(retries: Int) {
        context.preferencesDataStore.edit { it[PrefKeys.aiMaxRetries] = retries }
    }

    suspend fun setLastOpenedProjectId(id: String?) {
        context.preferencesDataStore.edit {
            if (id == null) it.remove(PrefKeys.lastOpenedProjectId)
            else it[PrefKeys.lastOpenedProjectId] = id
        }
    }

    suspend fun setLastOpenedFilePath(path: String?) {
        context.preferencesDataStore.edit {
            if (path == null) it.remove(PrefKeys.lastOpenedFilePath)
            else it[PrefKeys.lastOpenedFilePath] = path
        }
    }

    /** 清除所有偏好。 */
    suspend fun clear() {
        context.preferencesDataStore.edit { it.clear() }
    }
}