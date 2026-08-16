package com.miide.core.data.repository

import com.miide.core.data.local.dao.ProjectDao
import com.miide.core.data.local.entity.ProjectEntity
import com.miide.core.model.ProjectInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * 项目仓库。
 */
class ProjectRepository(
    private val dao: ProjectDao
) {

    fun observeAll(): Flow<List<ProjectInfo>> =
        dao.observeAll().map { list -> list.map { it.toModel() } }

    suspend fun getById(id: String): ProjectInfo? =
        dao.getById(id)?.toModel()

    suspend fun getByRootPath(rootPath: String): ProjectInfo? =
        dao.getByRootPath(rootPath)?.toModel()

    suspend fun upsert(project: ProjectInfo) {
        dao.upsert(project.toEntity())
    }

    suspend fun deleteById(id: String) {
        dao.deleteById(id)
    }

    private fun ProjectInfo.toEntity() = ProjectEntity(
        id = id,
        name = name,
        rootPath = rootPath,
        createdAt = createdAt
    )

    private fun ProjectEntity.toModel() = ProjectInfo(
        id = id,
        name = name,
        rootPath = rootPath,
        createdAt = createdAt
    )
}
