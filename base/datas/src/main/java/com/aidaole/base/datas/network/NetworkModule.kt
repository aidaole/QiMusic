package com.aidaole.base.datas.network

import android.content.Context
import com.aidaole.base.datas.CookieManager
import com.aidaole.base.utils.logi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkhttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .cookieJar(cookieJar = object : CookieJar {
                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                    "saveFromResponse-> url: ${url.host}".logi()
                    cookies.forEachIndexed { index, cookie ->
                        "saveFromResponse->$index cookie: $cookie".logi()
                    }
                    CookieManager.saveCookies(url, cookies, context)
                }

                override fun loadForRequest(url: HttpUrl): List<Cookie> {
                    val cookies = CookieManager.getCookies(context, url.host)
                    "loadForRequest-> url: ${url.host}, cookies: $cookies".logi()
                    cookies?.forEachIndexed { index, cookie ->
                        "loadForRequest->$index cookie: $cookie".logi()
                    }
                    return cookies ?: emptyList()
                }
            })
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}