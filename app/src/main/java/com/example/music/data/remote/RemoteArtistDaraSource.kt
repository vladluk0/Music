package com.example.music.data.remote

import com.example.music.data.model.artist.Artist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteArtistDaraSource @Inject constructor(
    private val artistsService: ArtistsService
) {

    fun getArtists(artistsId: String): Flow<List<Artist>> = flow {
        val artists: List<Artist> =
            artistsService.fetchArtists(artistsId).body()?.artists ?: listOf()
        emit(artists)
    }.flowOn(Dispatchers.IO)
}