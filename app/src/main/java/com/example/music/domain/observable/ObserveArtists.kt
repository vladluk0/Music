package com.example.music.domain.observable

import com.example.music.data.model.artist.Artist
import com.example.music.data.repository.artists.ArtistsRepositoryImpl
import com.example.music.util.SubjectInjector
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveArtists @Inject constructor(
    private val artistsRepositoryImpl: ArtistsRepositoryImpl,
) : SubjectInjector<ObserveArtists.Params, List<Artist>>() {
    override fun createObservable(params: Params): Flow<List<Artist>> {
        return artistsRepositoryImpl.getArtists(params.ids)
        }

    data class Params(val ids: String)
}

