package com.miide.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.miide.core.data.local.dao.ConversationDao
import com.miide.core.data.local.dao.MessageDao
import com.miide.core.data.local.dao.ProjectDao
import com.miide.core.data.local.dao.ProviderDao
import com.miide.core.data.local.dao.UsageDao
import com.miide.core.data.local.entity.ConversationEntity
import com.miide.core.data.local.entity.MessageEntity
import com.miide.core.data.local.entity.ProjectEntity
import com.miide.core.data.local.entity.ProviderEntity
import com.miide.core.data.local.entity.UsageRecordEntity

@Database(
    entities = [
        ProviderEntity::class,
        ConversationEntity::class,
        MessageEntity::class,
        UsageRecordEntity::class,
        ProjectEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MiDatabase : RoomDatabase() {
    abstract fun providerDao(): ProviderDao
    abstract fun conversationDao(): ConversationDao
    abstract fun messageDao(): MessageDao
    abstract fun usageDao(): UsageDao
    abstract fun projectDao(): ProjectDao
}
