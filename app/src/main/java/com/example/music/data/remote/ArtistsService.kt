package com.example.music.data.remote

import com.example.music.data.model.artist.Artists
import kotlinx.coroutines.flow.Flow
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
        "Authorization: Bearer BQCnxTrFso1_qnjAF1Z-pgcyJhyJNEjIJKLjVx2d1qgfYzeTjgbrZPcqf9g4ptuJIJrNMHKiLFD0LEdI3G0-kr7vk46dyDyxrPPfSlPypUGIjqYfttOHY563CMJs4S3cSJE2VbcVAwWsnqSgMHRAI5WXsrDuCrPYP9qNZQzK12PkSKkivfPhMxq69PcMSoNV5vASRfYtYHl2pxv5p3d1hbe0IN_sGvaMZnGw7fbb",
        /*"X-RapidAPI-Host: spotify23.p.rapidapi.com"*/
    )
    @GET("artists/")
    suspend fun fetchArtists(@Query("ids", encoded = true) ids: String): Response<Artists>


}




