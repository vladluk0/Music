package com.example.music.data.model.artist

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("external_urls")
    var external_urls: ExternalUrls = ExternalUrls(),
    @SerializedName("followers")
    var followers: Followers = Followers(),
    @SerializedName("genress")
    var genres: List<String> = listOf(),
    @SerializedName("id")
    var id: String = "",
    @SerializedName("images")
    var images: List<Image> = listOf(),
    @SerializedName("name")
    var name: String = "",
    @SerializedName("popularity")
    var popularity: Int = -1,
    @SerializedName("type")
    var type: String = "",
    @SerializedName("uri")
    var uri: String = ""
)