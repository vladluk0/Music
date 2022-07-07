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

data class Padding(
    val start: Dp = 10.dp,
    val end: Dp = 10.dp,
)

val LocalRoundedIcon = compositionLocalOf { RoundedIcon() }
val LocalPadding = compositionLocalOf { Padding() }


val MaterialTheme.roundedIcon: RoundedIcon
    @Composable
    @ReadOnlyComposable
    get() = LocalRoundedIcon.current

val MaterialTheme.padding: Padding
    @Composable
    @ReadOnlyComposable
    get() = LocalPadding.current






