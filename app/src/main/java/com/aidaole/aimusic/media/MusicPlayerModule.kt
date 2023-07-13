package com.aidaole.aimusic.media

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MusicPlayerModule {

    @Provides
    fun providerMusicPlayer(): MusicPlayer {
        return MusicPlayer()
    }
}