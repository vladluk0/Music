package com.example.music.data.remote

import android.util.Log
import com.example.music.data.model.artist.Artist
import com.example.music.data.model.artist.Artists
import com.example.music.ui.theme.asyncRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteArtistDaraSource @Inject constructor(
    private val artistsService: ArtistsService
) {

    suspend fun getArtists(artistsId: String): List<Artist> {
        Log.d("zxc", "getArtists")
        return artistsService.fetchArtists(artistsId).body()?.artists ?: listOf()

    }
}