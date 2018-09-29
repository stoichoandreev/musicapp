package com.sniper.music.api.lastFm.dataModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Query: Serializable {
    @SerializedName("#text")
    @Expose
    lateinit var text: String

    @SerializedName("role")
    @Expose
    lateinit var role: String

    @SerializedName("searchTerms")
    @Expose
    lateinit var searchTerms: String

    @SerializedName("startPage")
    @Expose
    lateinit var startPage: String

}