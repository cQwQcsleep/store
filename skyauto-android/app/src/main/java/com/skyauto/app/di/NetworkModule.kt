package com.skyauto.app.di

import com.skyauto.app.BuildConfig
import com.skyauto.app.data.network.ApiKeys
import com.skyauto.app.data.network.CryptoInterceptor
import com.skyauto.app.data.network.SkyAutoApi
import com.skyauto.app.data.session.PersistentCookieJar
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        explicitNulls = false
    }

    @Provides
    @Singleton
    fun provideCookieJar(@ApplicationContext context: Context): PersistentCookieJar =
        PersistentCookieJar(context)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        cookieJar: PersistentCookieJar
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .followRedirects(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiKeys(okHttp: OkHttpClient): ApiKeys =
        ApiKeys(BuildConfig.API_BASE_URL, okHttp)

    @Provides
    @Singleton
    fun provideCryptoInterceptor(keys: ApiKeys): CryptoInterceptor =
        CryptoInterceptor(BuildConfig.API_BASE_URL, keys)

    @Provides
    @Singleton
    fun provideRetrofit(okHttp: OkHttpClient, json: Json, crypto: CryptoInterceptor): Retrofit {
        val client = okHttp.newBuilder()
            .addInterceptor(crypto)
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL + "/")
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideSkyAutoApi(retrofit: Retrofit): SkyAutoApi =
        retrofit.create(SkyAutoApi::class.java)
}