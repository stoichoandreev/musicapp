package com.sniper.music.api.lastFm

import com.sniper.music.api.ApiConfiguration
import com.sniper.music.api.Environment

class LastFMApiConfiguration(private val isDebug: Boolean): ApiConfiguration {
    override val baseURL: String
        get() = Environment.LastFMAPI.getBuildUrl(isDebug)
}