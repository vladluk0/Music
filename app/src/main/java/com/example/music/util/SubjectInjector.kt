package com.example.music.util

import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

abstract class SubjectInjector<P, T> {

    private val paramState = MutableSharedFlow<P>(
        replay = 1,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val flow: Flow<T> = paramState.flatMapLatest {
        createObservable(it)
    }.distinctUntilChanged()

    operator fun invoke(params: P) {
        paramState.tryEmit(params)
    }

    abstract fun createObservable(params: P): Flow<T>
}