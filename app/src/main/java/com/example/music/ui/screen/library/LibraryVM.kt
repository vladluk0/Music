package com.example.music.ui.screen.library

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LibraryState(
    val scrollPosition: Float = 0f,

)

class LibraryVM @Inject constructor(

): ViewModel() {

    private val _state = MutableStateFlow(LibraryState())
    val state: StateFlow<LibraryState> = _state.asStateFlow()

    private val scrollPosition = MutableStateFlow(0f)

    init {
        viewModelScope.launch {
            scrollPosition.collect { scrollPosition ->
                _state.update {
                    it.copy(
                        scrollPosition = scrollPosition
                    )
                }
            }
        }
    }

    fun scroll(position: Float) {
        scrollPosition.value = position
        Log.d("zxc", position.toString())
    }
}