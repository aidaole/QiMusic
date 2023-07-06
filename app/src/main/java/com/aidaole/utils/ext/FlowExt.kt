package com.aidaole.utils.ext

import com.aidaole.aimusic.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal fun <T> Flow<T>.toStateFlow(initialValue: T, scope: CoroutineScope = App.mainScope): StateFlow<T> =
    this.onStart { emit(initialValue) }
        .stateIn(scope, SharingStarted.Eagerly, initialValue)

internal fun <T> Flow<T>.mutableStateIn(initialValue: T, scope: CoroutineScope = App.mainScope): MutableStateFlow<T> {
    val flow = MutableStateFlow(initialValue)
    scope.launch {
        this@mutableStateIn.collect(flow)
    }
    return flow
}