package com.sniper.music.api.lastFm.dataModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Artist: Serializable {

    @SerializedName("name")
    @Expose
    lateinit var name: String

    @SerializedName("listeners")
    @Expose
    lateinit var listeners: String

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

}
