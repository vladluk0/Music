package com.example.music.data.repository.artists

import com.example.music.data.model.artist.Artist
import com.example.music.data.model.artist.Artists
import com.example.music.ui.theme.MusicResult
import kotlinx.coroutines.flow.Flow


interface ArtistsRepository {
    suspend fun getArtists(artistsId: String): Flow<MusicResult<List<Artist>>>
}