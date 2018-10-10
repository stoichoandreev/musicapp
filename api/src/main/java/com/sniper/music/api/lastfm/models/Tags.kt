package com.sniper.music.api.lastfm.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Tags: Serializable {

    @SerializedName("tag")
    @Expose
    var tagList: List<Tag>? = null

}
