package com.aidaole.base.datas

import com.aidaole.base.datas.network.RetrofitNeteaseApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    fun provideNeteaseRepo(
        retrofitNeteaseApi: RetrofitNeteaseApi
    ): NeteaseRepo {
        return NeteaseRepo(retrofitNeteaseApi)
    }
}