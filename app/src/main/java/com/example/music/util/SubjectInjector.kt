package com.example.music.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

abstract class SubjectInjector<P, T> {

    private val paramState = MutableSharedFlow<P>(
        replay = 1,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val flow: Flow<T> = paramState.flatMapLatest {
        createObservable(it)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val stateFlow: Flow<InvokeStatus> = paramState.flatMapLatest {
        flow {
            emit(InvokeStarted)
            createObservable(it)
            emit(InvokeFinished)
        }
    }

    operator fun invoke(params: P) {
        paramState.tryEmit(params)
    }

    abstract fun createObservable(params: P): Flow<T>
}