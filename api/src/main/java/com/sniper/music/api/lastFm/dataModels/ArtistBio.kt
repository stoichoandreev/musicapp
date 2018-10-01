package com.sniper.music.api.lastFm.dataModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class ArtistBio: Serializable {

    @SerializedName("summary")
    @Expose
    lateinit var summary: String

}
