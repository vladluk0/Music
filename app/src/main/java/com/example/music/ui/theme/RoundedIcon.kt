package com.example.music.ui.theme

import android.util.Log
import androidx.annotation.NonNull
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.music.data.model.artist.Artists
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
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


sealed class Result<out T> {
    sealed class Success<T> : Result<T>() {
        data class Value<T>(val data: T) : Success<T>()
        object Empty : Success<Nothing>()
    }

    data class Error(val code: Int) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

@OptIn(FlowPreview::class)
suspend inline fun <reified T> asyncRequest(
    noinline block: suspend () -> T
): Flow<Result<T>> = flow {
    emit(Result.Loading)
    try {
        if (null is T)
            emit(Result.Success.Empty)
        else
            block.asFlow().collect {
                emit(Result.Success.Value(it))
            }
    } catch (throwable: Throwable) {
        val exception = when (throwable) {
            is HttpException -> Result.Error(throwable.code())
            else -> Result.Error(0)
        }
        emit(exception)
    }
}.flowOn(Dispatchers.IO)



