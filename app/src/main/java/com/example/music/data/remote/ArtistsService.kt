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
        "Authorization: Bearer BQBF74NXO6PyfROdIrDbNR2-Z2CW83iheTuhmX4ExBuxCftITvZ4mQsroBan5qeRCgq3sEGyNsw2Kz8HLsPatkXjm0NAOHQC1V2rk8iXQgoYZRj49QV8PikHJQxXaUTokiZNmKKG1PQV5J-lU9omfXzE7UtcwvQPghd-OK_qiidz6xxOthp0_j6xtwEchmWKJVkjVdrr",
        /*"X-RapidAPI-Host: spotify23.p.rapidapi.com"*/
    )
    @GET("artists/")
    suspend fun fetchArtists(@Query("ids", encoded = true) ids: String): Response<Artists>


}




