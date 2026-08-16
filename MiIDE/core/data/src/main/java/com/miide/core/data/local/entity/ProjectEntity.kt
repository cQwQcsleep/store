package com.miide.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey val id: String,
    val name: String = "",
    val rootPath: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
