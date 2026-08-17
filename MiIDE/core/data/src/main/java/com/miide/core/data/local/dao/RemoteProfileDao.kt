package com.miide.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miide.core.data.local.entity.RemoteProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RemoteProfileDao {

    @Query("SELECT * FROM remote_profiles ORDER BY createdAt ASC")
    fun observeAll(): Flow<List<RemoteProfileEntity>>

    @Query("SELECT * FROM remote_profiles ORDER BY createdAt ASC")
    suspend fun getAll(): List<RemoteProfileEntity>

    @Query("SELECT * FROM remote_profiles WHERE id = :id")
    suspend fun getById(id: String): RemoteProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: RemoteProfileEntity)

    @Query("DELETE FROM remote_profiles WHERE id = :id")
    suspend fun deleteById(id: String)
}
