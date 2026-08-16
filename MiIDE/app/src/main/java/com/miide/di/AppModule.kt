package com.miide.di

import android.content.Context
import androidx.room.Room
import com.miide.core.data.local.MiDatabase
import com.miide.core.data.local.dao.ConversationDao
import com.miide.core.data.local.dao.MessageDao
import com.miide.core.data.local.dao.ProjectDao
import com.miide.core.data.local.dao.ProviderDao
import com.miide.core.data.local.dao.UsageDao
import com.miide.core.data.repository.ProviderRepository
import com.miide.core.data.repository.UsageRepository
import com.miide.core.data.security.SecureKeyStore
import com.miide.core.data.settings.PreferencesManager
import com.miide.core.network.HttpClientFactory
import com.miide.core.network.ProviderFactory
import com.miide.core.network.ProviderGateway
import com.miide.core.network.RetryPolicy
import com.miide.core.network.UsageSink
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJson(): Json = HttpClientFactory.JsonConfig

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClientFactory.create()

    @Provides
    @Singleton
    fun provideSecureKeyStore(): SecureKeyStore = SecureKeyStore()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MiDatabase =
        Room.databaseBuilder(context, MiDatabase::class.java, "miide.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideProviderDao(db: MiDatabase): ProviderDao = db.providerDao()

    @Provides
    fun provideConversationDao(db: MiDatabase): ConversationDao = db.conversationDao()

    @Provides
    fun provideMessageDao(db: MiDatabase): MessageDao = db.messageDao()

    @Provides
    fun provideUsageDao(db: MiDatabase): UsageDao = db.usageDao()

    @Provides
    fun provideProjectDao(db: MiDatabase): ProjectDao = db.projectDao()

    @Provides
    @Singleton
    fun provideProviderRepository(
        dao: ProviderDao,
        keyStore: SecureKeyStore,
        json: Json
    ): ProviderRepository = ProviderRepository(dao, keyStore, json)

    @Provides
    @Singleton
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager =
        PreferencesManager(context)

    @Provides
    @Singleton
    fun provideUsageRepository(dao: UsageDao): UsageRepository = UsageRepository(dao)

    @Provides
    @Singleton
    fun provideUsageSink(repo: UsageRepository): UsageSink = repo

    @Provides
    @Singleton
    fun provideProviderFactory(client: HttpClient, json: Json): ProviderFactory =
        ProviderFactory(client, json)

    @Provides
    @Singleton
    fun provideRetryPolicy(): RetryPolicy = RetryPolicy()

    @Provides
    @Singleton
    fun provideProviderGateway(
        factory: ProviderFactory,
        retry: RetryPolicy,
        usageSink: UsageSink
    ): ProviderGateway = ProviderGateway(factory, retry, usageSink)
}
