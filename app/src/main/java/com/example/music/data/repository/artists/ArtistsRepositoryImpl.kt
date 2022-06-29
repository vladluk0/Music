package com.example.music.data.repository.artists

import android.util.Log
import com.example.music.data.model.artist.Artist
import com.example.music.data.remote.RemoteArtistDaraSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArtistsRepositoryImpl @Inject constructor(
    private val remoteArtistDaraSource: RemoteArtistDaraSource
) : ArtistsRepository {

    /*suspend fun findArtists(artistsId: String, query: String): Flow<List<Artist>> {
        val artists = remoteArtistDaraSource.getArtists(artistsId)
        /*return artists.map {
            it.filter { artist ->
                artist.name.contains(query, true)
            }
        }*/
    }*/

    override fun getArtists(artistsId: String): Flow<List<Artist>> =
        remoteArtistDaraSource.getArtists(artistsId)
}