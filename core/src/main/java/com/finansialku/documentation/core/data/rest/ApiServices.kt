/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.finansialku.documentation.core.data.rest

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.finansialku.documentation.core.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiServices(context: Context) {
    private val chuckerCollector = ChuckerCollector(
        context = context,
        showNotification = BuildConfig.DEBUG,
        retentionPeriod = RetentionManager.Period.ONE_HOUR
    )
    private val chuckerInterceptor = ChuckerInterceptor.Builder(context).apply {
        collector(chuckerCollector)
        maxContentLength(MAX_LENGTH)
        alwaysReadResponseBody(true)
        if (!BuildConfig.DEBUG) redactHeaders("Authorization", "Bearer")
    }.build()

    private val okHttpClient: OkHttpClient
        get() {
            return OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .addInterceptor { chain ->
                    chain.request().let { original ->
                        val httpUrl = original.url
                        val newHttpUrl = httpUrl.newBuilder()
                            .build()
                        val requestBuilder = original.newBuilder().url(newHttpUrl)
                            .addHeader("Content-Type", "application/json")
                        val request = requestBuilder.build()
                        chain.proceed(request)
                    }
                }
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .addInterceptor(chuckerInterceptor)
                .build()
        }
    private val contentType = "application/json".toMediaType()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object {
        private const val MAX_LENGTH = 250_000L
    }
}
