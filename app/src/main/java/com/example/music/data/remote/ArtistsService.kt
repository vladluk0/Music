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
        "Authorization: Bearer BQDCrdldlybZCUJ4nQNolB83Nna9RE7DCzUUuQPjWsxjlXaCJLFmMZyNc-46weTX41uaZt3RalxYRpJ_H27jPNvss5dGJgUx5X7mGtHQ84yNuDIRDoAGJ691Y_UG4avmRFGjsOTTrjffzuPJJ_Hmxq-pbXrO7ljeAY10LQGLyDiyKYM5cXIwCqmIO-YF9LLmZ955ON1bGB96FiV-BfrJlsH3yHuXd_cWt1Eu1axq",
        /*"X-RapidAPI-Host: spotify23.p.rapidapi.com"*/
    )
    @GET("artists/")
    suspend fun fetchArtists(@Query("ids", encoded = true) ids: String): Response<Artists>


}




