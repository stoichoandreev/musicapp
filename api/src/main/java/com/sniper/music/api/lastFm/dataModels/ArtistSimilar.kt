package com.sniper.music.api.lastFm.dataModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class ArtistSimilar: Serializable {

    @SerializedName("artist")
    @Expose
    lateinit var artistList: List<Artist>

}
