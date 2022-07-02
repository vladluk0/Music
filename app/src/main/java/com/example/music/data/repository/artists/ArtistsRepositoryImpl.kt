package com.example.music.data.repository.artists

import com.example.music.data.model.artist.Artist
import com.example.music.data.model.artist.Artists
import com.example.music.data.remote.RemoteArtistDaraSource
import com.example.music.ui.theme.MusicResult
import com.example.music.ui.theme.asyncRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtistsRepositoryImpl @Inject constructor(
    private val remoteArtistDaraSource: RemoteArtistDaraSource
) : ArtistsRepository {

    /*suspend fun findArtists(artistsId: String, query: String): Result<Artists?> {
        return remoteArtistDaraSource.getArtists(artistsId)
        return artists.map {
            it.filter { artist ->
                artist.name.contains(query, true)
            }
        }
    }*/

    override suspend fun getArtists(artistsId: String): Flow<MusicResult<List<Artist>>> {
        return asyncRequest {
            remoteArtistDaraSource.getArtists(artistsId)
        }
    }
}