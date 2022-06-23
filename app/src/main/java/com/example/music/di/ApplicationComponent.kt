package com.example.music.di

import com.example.music.MainActivity
import com.example.music.data.remote.ArtistsService
import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun artistService(): ArtistsService

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
    }

}

@Module(
    includes = [NetworkModule::class]
)
interface AppModule