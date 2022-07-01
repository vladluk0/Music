package com.example.music.data.repository.artists

import com.example.music.data.model.artist.Artists
import com.example.music.ui.theme.Result
import kotlinx.coroutines.flow.Flow


interface ArtistsRepository {
    suspend fun getArtists(artistsId: String): Flow<Result<Artists?>>
}