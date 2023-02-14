package com.aidaole.base.datas

import com.aidaole.base.datas.network.NeteaseApi
import com.aidaole.base.datas.network.NeteaseApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    fun provideNeteaseApi(
        okhttp: OkHttpClient
    ): NeteaseApi {
        return NeteaseApiImpl(okhttp)
    }
}