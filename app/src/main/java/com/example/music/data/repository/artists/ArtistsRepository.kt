package com.example.music.data.repository.artists

import com.example.music.data.model.artist.Artist
import kotlinx.coroutines.flow.Flow


interface ArtistsRepository {
    fun getArtists(artistsId: String): Flow<List<Artist>>
}