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
        "Authorization: Bearer BQCI_yX6u-z8XsS9JTtIBG5YrG5e_nVgUvJiuwmDvIRkYBxghZ0ABBouBC0XflXHQl61t0q2o9lT7RiNaqEs1qsrOVW4h1Uo5kzFu3OaNu3DGVJD9I2ayXoufVniAsy6uqSxvpoHObzjMqaAZRvuWnbItlv5uRUKWYFmbXDRpwqK7xbZWJQD3GTXdvnFUfs4hhiJb86DUOh_u6wwoeaZzBr-1ILh2mlG7WPVtGl2",
        /*"X-RapidAPI-Host: spotify23.p.rapidapi.com"*/
    )
    @GET("artists/")
    suspend fun fetchArtists(@Query("ids", encoded = true) ids: String): Response<Artists>


}




