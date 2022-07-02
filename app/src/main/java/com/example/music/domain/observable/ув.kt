package com.example.music.domain.observable

import android.util.Log
import com.example.music.data.model.artist.Artist
import com.example.music.data.remote.RemoteArtistDaraSource
import com.example.music.util.SubjectInjector
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ObserveArtists @Inject constructor(
    private val remoteArtistDaraSource: RemoteArtistDaraSource,
) : SubjectInjector<ObserveArtists.Params, List<Artist>>() {

    data class Params(val ids: String)

    override fun createObservable(params: Params): Flow<List<Artist>> =
        flow {
            remoteArtistDaraSource.getArtists(params.ids).asFlow()
        }
}


