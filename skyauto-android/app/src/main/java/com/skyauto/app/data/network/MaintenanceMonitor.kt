package com.skyauto.app.data.network

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * 全局服务器维护状态监测。
 *
 * 当接口返回维护特征（405/503/504、text/html、维护页关键词）时置为 true，
 * UI 层据此全屏展示"服务器维护中"页面，阻断进入功能区。
 */
object MaintenanceMonitor {

    private val _underMaintenance = MutableStateFlow(false)
    val underMaintenance: StateFlow<Boolean> = _underMaintenance.asStateFlow()

    private val markers = listOf(
        "服务器更新", "服务器正在更新", "开放通知", "预计下午开放", "服务状态中心"
    )

    /** 标记进入维护状态。 */
    fun markMaintenance() {
        _underMaintenance.value = true
    }

    /** 复位维护状态（重试成功后调用）。 */
    fun clear() {
        _underMaintenance.value = false
    }

    /** 判断响应是否命中维护特征。 */
    fun isMaintenanceResponse(statusCode: Int, contentType: String?, body: String?): Boolean {
        if (statusCode == 405 || statusCode == 503 || statusCode == 504) return true
        if (contentType?.startsWith("text/html") == true) return true
        if (body != null) {
            return markers.any { body.contains(it) }
        }
        return false
    }
}