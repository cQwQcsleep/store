package com.miide.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.miide.core.data.local.entity.ProviderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProviderDao {

    @Query("SELECT * FROM providers ORDER BY createdAt ASC")
    fun observeAll(): Flow<List<ProviderEntity>>

    @Query("SELECT * FROM providers ORDER BY createdAt ASC")
    suspend fun getAll(): List<ProviderEntity>

    @Query("SELECT * FROM providers WHERE id = :id")
    suspend fun getById(id: String): ProviderEntity?

    @Query("SELECT * FROM providers WHERE enabled = 1 ORDER BY createdAt ASC")
    suspend fun getEnabled(): List<ProviderEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: ProviderEntity)

    @Update
    suspend fun update(entity: ProviderEntity)

    @Query("UPDATE providers SET enabled = :enabled WHERE id = :id")
    suspend fun setEnabled(id: String, enabled: Boolean)

    @Query("DELETE FROM providers WHERE id = :id")
    suspend fun deleteById(id: String)

    @Delete
    suspend fun delete(entity: ProviderEntity)
}
