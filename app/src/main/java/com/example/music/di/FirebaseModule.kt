package com.example.music.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides

@Module
class FirebaseModule {

    @Provides
    fun provideAuth(): FirebaseAuth = Firebase.auth
}