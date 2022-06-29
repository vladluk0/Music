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
        "Authorization: Bearer BQDEEUmCB_i2z3i71ihSdziRppbH1vfDaVvMDziuPJEqLbbGDS4XZppqucsEmAXV_Ybbx5-RmAUi-C2DZ3_XvDn86LhZWvj9LFTaEUwdzGrj8IsS95AxmC4pvKJwdEdTIBx0pSp3lhmIF8OOv6urvUY6w5kV3HhnpfNSNYs1xeOzrT_CNqDVlvuHdD6cLGlCiBUy3ZJAuQ3pHKjpaGqOCuqswo-pzLQZrDwx_TRT",
        /*"X-RapidAPI-Host: spotify23.p.rapidapi.com"*/
    )
    @GET("artists/")
    suspend fun fetchArtists(@Query("ids", encoded = true) ids: String): Response<Artists>


}




