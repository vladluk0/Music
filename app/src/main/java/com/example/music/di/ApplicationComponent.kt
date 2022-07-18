package com.example.music.di

import android.content.Context
import com.example.music.MainActivity
import com.example.music.data.remote.ArtistsService
import com.example.music.di.registration.RegistrationComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun artistService(): ArtistsService

    fun registrationComponent() : RegistrationComponent.Builder

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
    }
}

@Module(
    includes = [
        NetworkModule::class,
        FirebaseModule::class,
        AppSubComponent::class
    ]
)
interface AppModule

@Module(
    subcomponents = [RegistrationComponent::class]
)
interface AppSubComponent