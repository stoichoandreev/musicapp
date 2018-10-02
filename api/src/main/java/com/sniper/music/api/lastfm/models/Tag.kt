package com.sniper.music.api.lastfm.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Tag: Serializable {

    @SerializedName("name")
    @Expose
    lateinit var name: String

    @SerializedName("url")
    @Expose
    lateinit var url: String

}
