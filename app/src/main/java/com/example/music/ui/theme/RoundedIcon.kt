package com.example.music.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

data class RoundedIcon(
    val small: Dp = 50.dp,
    val medium: Dp = 110.dp,
)

val LocalRoundedIcon = compositionLocalOf { RoundedIcon() }

val MaterialTheme.roundedIcon: RoundedIcon
    @Composable
    @ReadOnlyComposable
    get() = LocalRoundedIcon.current


sealed class MusicResult<out T> {
    sealed class Success<T> : MusicResult<T>() {
        data class Value<T>(val data: T) : Success<T>()
        object Empty : Success<Nothing>()
    }

    data class Error(val code: Int? = null, val message: String) : MusicResult<Nothing>()
    object Started : MusicResult<Nothing>()
    object Finished : MusicResult<Nothing>()
}

@OptIn(FlowPreview::class)
suspend inline fun <reified T> asyncRequest(
    noinline block: suspend () -> T
): Flow<MusicResult<T>> = flow {
    emit(MusicResult.Started)
    try {
        if (null is T)
            emit(MusicResult.Success.Empty)
        else
            block.asFlow().collect {
                emit(MusicResult.Success.Value(it))
            }
    } catch (throwable: Throwable) {
        val exception = when (throwable) {
            is HttpException -> MusicResult.Error(throwable.code(), "Http Exception")
            else -> MusicResult.Error(0, "Error")
        }
        emit(exception)
    }
    emit(MusicResult.Finished)
}.flowOn(Dispatchers.IO)



