package com.sniper.music.api.lastfm.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class ArtistInfoResponse: Serializable {

    @SerializedName("artist")
    @Expose
    lateinit var artistInfo: ArtistInfo

}
