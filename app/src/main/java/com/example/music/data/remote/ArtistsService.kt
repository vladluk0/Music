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
        "Authorization: Bearer BQDI6_BpN9f-7csebhfRdpWG9PRzTBNaAZTsalFHOj-lUGCaO26AQ1tdx8TB17UAxpaDXadpDEzR9ttW2U3cmGpJ5zQvBDINahDOFYpnwP-mdimHS6p0UHz9Wfq3zyF39oWQsNYrNzYqt64ljTWm6NRWoFE9Fd78muBuB0xY-X0OkdAL4qebrcx2VwVE-d_TycCm4K__AejI-64aOHcppallGoOleyJpfvIsTZ0W",
        /*"X-RapidAPI-Host: spotify23.p.rapidapi.com"*/
    )
    @GET("artists/")
    suspend fun fetchArtists(@Query("ids", encoded = true) ids: String): Response<Artists>


}




