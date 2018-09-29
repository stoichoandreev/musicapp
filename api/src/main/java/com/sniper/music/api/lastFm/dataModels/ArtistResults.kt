package com.sniper.music.api.lastFm.dataModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class ArtistResults: Serializable {
    @SerializedName("opensearch: Query")
    @Expose
    lateinit var query: Query

}