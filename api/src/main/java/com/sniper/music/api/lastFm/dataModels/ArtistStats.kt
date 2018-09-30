package com.sniper.music.api.lastFm.dataModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ArtistStats: Serializable {

    @SerializedName("listeners")
    @Expose
    lateinit var listeners: String

    @SerializedName("playcount")
    @Expose
    lateinit var playcount: String

}
