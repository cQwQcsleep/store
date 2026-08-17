package com.miide.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miide.core.data.local.entity.PluginEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PluginDao {

    @Query("SELECT * FROM plugins ORDER BY installedAt ASC")
    fun observeAll(): Flow<List<PluginEntity>>

    @Query("SELECT * FROM plugins WHERE id = :id")
    suspend fun getById(id: String): PluginEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: PluginEntity)

    @Query("DELETE FROM plugins WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("UPDATE plugins SET enabled = :enabled WHERE id = :id")
    suspend fun setEnabled(id: String, enabled: Boolean)
}
