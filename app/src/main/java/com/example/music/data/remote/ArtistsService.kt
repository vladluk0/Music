package com.example.music.data.remote

import com.example.music.data.model.artist.Artists
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/*interface ArtistsService {
    @Headers(
        "X-RapidAPI-Key: 18e41a160amsh1fdbd34e757ce8dp1e6c22jsn0b8cf7a18a1f",
        "X-RapidAPI-Host: spotify23.p.rapidapi.com"
    )
    @GET("artists/")
    suspend fun fetchArtists(@Query("ids", encoded = true) ids: String): Response<Artists>
}*/

interface ArtistsService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "Authorization: Bearer BQB0MBqSkCjOog0S0g5pe-kLv0EBqjfD9FUmPzUvhrgPm4i8tuCxWtVHPTZKB2VQe4pjZBOij6NihZBlHgeYQ8c2pSRhBe70gFR1ZWOBxxGtjSzVY8mLO-XtsRNFg0OcsHD0Damg83KkKrUVa1JL8ZHE1uhxAa5RjfrEnlgWXCRUPMqi2v3zDLkCJpXyeHYXHycUyjXJr-mrJm50DsSIobX_E5y2eNgCcTVtISxj",
        /*"X-RapidAPI-Host: spotify23.p.rapidapi.com"*/
    )
    @GET("artists/")
    suspend fun fetchArtists(@Query("ids", encoded = true) ids: String): Response<Artists>


}




