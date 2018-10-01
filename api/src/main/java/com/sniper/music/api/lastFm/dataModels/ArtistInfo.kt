package com.sniper.music.api.lastFm.dataModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class ArtistInfo: Serializable {

    @SerializedName("name")
    @Expose
    lateinit var name: String

    @SerializedName("stats")
    @Expose
    lateinit var stats: ArtistStats

    @SerializedName("mbid")
    @Expose
    lateinit var mbid: String

    @SerializedName("url")
    @Expose
    lateinit var url: String

    @SerializedName("streamable")
    @Expose
    lateinit var streamable: String

    @SerializedName("image")
    @Expose
    lateinit var images: List<ImageDataModel>

    @SerializedName("similar")
    @Expose
    lateinit var similar: ArtistSimilar

    @SerializedName("tags")
    @Expose
    lateinit var tags: Tags

    @SerializedName("bio")
    @Expose
    lateinit var bio: ArtistBio

}
