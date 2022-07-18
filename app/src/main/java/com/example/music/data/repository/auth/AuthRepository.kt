package com.example.music.data.repository.auth

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val oneTapSignInClient: SignInClient,
    private val signInRequest: BeginSignInRequest,
    private val auth: FirebaseAuth
){
    suspend fun onTapSignInWithGoogle() = flow {
        try {
            val result = oneTapSignInClient.beginSignIn(signInRequest).await()
            emit(AuthResult.Success(result))
        } catch (exception: Exception) {
            emit(AuthResult.Fail)
        }

    }.flowOn(Dispatchers.IO)

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential) = flow {
        try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser
            emit(AuthResult.Success(isNewUser))
        } catch (e: Exception) {
            emit(AuthResult.Fail)
        }
    }
}

sealed class AuthResult<out T> {
    class Success<T>(val data: T?) : AuthResult<T>()
    object Fail: AuthResult<Nothing>()
}