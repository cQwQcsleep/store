package com.miide.core.model

import java.util.UUID

/**
 * 项目信息。
 */
data class ProjectInfo(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val rootPath: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
