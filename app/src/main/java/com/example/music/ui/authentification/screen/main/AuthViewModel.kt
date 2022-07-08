package com.example.music.ui.authentification.screen.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.music.data.repository.auth.AuthRepository
import com.example.music.data.repository.auth.AuthResult
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    val oneTapClient: SignInClient
) : ViewModel() {

    private val _signInState =
        mutableStateOf<AuthResult<Boolean>>(AuthResult.Success(null))
    val signInState: State<AuthResult<Boolean>> = _signInState

    private val _oneTapSignInState =
        mutableStateOf<AuthResult<BeginSignInResult>>(AuthResult.Success(null))
    val oneTapSignInState: State<AuthResult<BeginSignInResult>> = _oneTapSignInState

    fun singInRequest() = viewModelScope.launch {
        authRepository.onTapSignInWithGoogle().collect { response ->
            _oneTapSignInState.value = response
        }
    }

    fun signInWithGoogle(googleCredential: AuthCredential) = viewModelScope.launch {
        authRepository.firebaseSignInWithGoogle(googleCredential).collect { response ->
            _signInState.value = response
        }
    }
}