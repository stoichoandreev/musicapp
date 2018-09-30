package com.sniper.music.api.lastFm.dataModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class ArtistResults: Serializable {

    @SerializedName("opensearch: Query")
    @Expose
    lateinit var query: Query

    @SerializedName("opensearch:totalResults")
    @Expose
    lateinit var totalResults: String

    @SerializedName("opensearch:startIndex")
    @Expose
    lateinit var startIndex: String

    @SerializedName("artistmatches")
    @Expose
    lateinit var artistMatches: ArtistMatches

}
