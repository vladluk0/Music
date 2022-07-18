package com.example.music.data.repository.artists

import com.example.music.data.model.artist.Artist
import com.example.music.data.remote.RemoteArtistDaraSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
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

sealed class MusicResult<out T> {
    sealed class Success<T> : MusicResult<T>() {
        data class Value<T>(val data: T) : Success<T>()
        object Empty : Success<Nothing>()
    }

    data class Error(val code: Int? = null, val message: String) : MusicResult<Nothing>()
    object Started : MusicResult<Nothing>()
    object Finished : MusicResult<Nothing>()
}

@OptIn(FlowPreview::class)
suspend inline fun <reified T> asyncRequest(
    noinline block: suspend () -> T
): Flow<MusicResult<T>> = flow {
    emit(MusicResult.Started)
    try {
        if (null is T)
            emit(MusicResult.Success.Empty)
        else
            block.asFlow().collect {
                emit(MusicResult.Success.Value(it))
            }
    } catch (throwable: Throwable) {
        val exception = when (throwable) {
            is HttpException -> MusicResult.Error(throwable.code(), "Http Exception")
            else -> MusicResult.Error(0, "Error")
        }
        emit(exception)
    }
    emit(MusicResult.Finished)
}.flowOn(Dispatchers.IO)