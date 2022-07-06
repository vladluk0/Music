package com.example.music.data.remote

import com.example.music.data.model.artist.Artist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RemoteArtistDaraSource @Inject constructor(
    private val artistsService: ArtistsService
) {

    suspend fun getArtists(artistsId: String): Flow<List<Artist>> {
        return searchArtists(artistsId, "")
    }

    fun searchArtists(artistsId: String, query: String): Flow<List<Artist>> {
        return flow {

        }
    }
    /*fun searchArtists(artistsId: String, query: String): Flow<List<Artist>> {
        return artistsService.fetchArtists(artistsId).map { artists ->
            artists.artists.filter { artist ->
                artist.name.contains(query, ignoreCase = true)
            }
        }.flowOn(Dispatchers.IO)
    }*/

}