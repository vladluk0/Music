package com.example.music.di

import androidx.lifecycle.ViewModel
import com.example.music.data.repository.artists.ArtistsRepository
import com.example.music.data.repository.artists.ArtistsRepositoryImpl
import com.example.music.ui.screen.add_artist.AddArtistVM
import com.example.music.ui.screen.library.LibraryVM
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@Component(modules = [ScreenModule::class],
    dependencies = [ApplicationComponent::class])
@ScreenScope
interface ScreenComponent {

    @Component.Factory
    interface Builder {
        fun create(
            applicationComponent: ApplicationComponent
        ): ScreenComponent
    }

    val factory: MultiViewModelFactory
}

@Module(
    includes = [ScreenBindModule::class]
)
interface ScreenModule

@Module
interface ScreenBindModule {

    @Binds
    @[IntoMap ViewModelKey(AddArtistVM::class)]
    fun provideAddArtistViewModel(addArtistVM: AddArtistVM): ViewModel

    @Binds
    @[IntoMap ViewModelKey(LibraryVM::class)]
    fun provideLibraryViewModel(libraryVM: LibraryVM): ViewModel

    @Binds
    fun bindArtistRepositoryImpl_to_ArtistRepository(repositoryImpl: ArtistsRepositoryImpl): ArtistsRepository
}