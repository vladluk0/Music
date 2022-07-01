package com.example.music.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import java.util.concurrent.atomic.AtomicInteger

class ObserveLoadingCounter {

    private val counter = AtomicInteger()

    private val flowCounter = MutableStateFlow(counter.get())

    val flow: Flow<Boolean>
        get() = flowCounter.map { it > 0 }

    fun increase() {
        flowCounter.value = counter.incrementAndGet()
    }

    fun decrease() {
        flowCounter.value = counter.decrementAndGet()
    }
}

sealed class InvokeStatus
object InvokeStarted : InvokeStatus()
object InvokeLoading : InvokeStatus()
object InvokeFinished : InvokeStatus()
object InvokeError : InvokeStatus()
