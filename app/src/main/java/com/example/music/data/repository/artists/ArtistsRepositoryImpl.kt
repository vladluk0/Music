package com.example.music.data.repository.artists

import android.util.Log
import com.example.music.data.model.artist.Artist
import com.example.music.data.model.artist.Artists
import com.example.music.data.remote.RemoteArtistDaraSource
import com.example.music.ui.theme.MusicResult
import com.example.music.ui.theme.asyncRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
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

    override fun getArtists(artistsId: String): Flow<MusicResult<List<Artist>>> {
        return flow {

        }
    }

    fun searchArtists(artistsId: String, query: String): Flow<MusicResult<List<Artist>>> {
        return flow {
            val artistsList: Flow<List<Artist>> = remoteArtistDaraSource.searchArtists(artistsId, "")
            Log.d("zxc", "artistsList " + artistsList.toString())
            artistsList.map { artists ->
                if (artists.isEmpty())
                    emit(MusicResult.Error(message = "No data"))
                else {
                    emit(MusicResult.Success.Value(artists))
                }
            }
        }
    }
}