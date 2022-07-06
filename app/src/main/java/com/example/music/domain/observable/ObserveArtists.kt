package com.example.music.domain.observable

import com.example.music.data.model.artist.Artist
import com.example.music.data.remote.RemoteArtistDaraSource
import com.example.music.ui.theme.MusicResult
import com.example.music.util.SubjectInjector
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class ObserveArtists @Inject constructor(
    private val remoteArtistDaraSource: RemoteArtistDaraSource,
) : SubjectInjector<ObserveArtists.Params, List<Artist>>() {

    override fun createObservable(params: Params): Flow<List<Artist>> {
        return remoteArtistDaraSource.searchArtists(params.ids, params.query)
    }

    data class Params(val ids: String, val query: String)
}


