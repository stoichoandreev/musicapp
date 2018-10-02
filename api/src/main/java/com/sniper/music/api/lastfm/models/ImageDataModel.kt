package com.sniper.music.api.lastfm.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class ImageDataModel : Serializable {

    @SerializedName("#text")
    @Expose
    lateinit var url: String

    @SerializedName("size")
    @Expose
    lateinit var size: String

}
