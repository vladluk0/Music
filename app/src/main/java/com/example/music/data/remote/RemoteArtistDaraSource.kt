package com.example.music.data.remote

import androidx.annotation.NonNull
import com.example.music.data.model.artist.Artists
import com.example.music.ui.theme.Result
import com.example.music.ui.theme.asyncRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RemoteArtistDaraSource @Inject constructor(
    private val artistsService: ArtistsService
) {

    suspend fun getArtists(artistsId: String): Result<Artists?> {
        return asyncRequest(Dispatchers.IO) { artistsService.fetchArtists(artistsId).body() }
    }
}