package com.aidaole.utils.ext

import com.aidaole.aimusic.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

internal fun <T> Flow<T>.toStateFlow(initialValue: T, scope: CoroutineScope = App.mainScope): StateFlow<T> =
    this.onStart { emit(initialValue) }
        .stateIn(scope, SharingStarted.Eagerly, initialValue)