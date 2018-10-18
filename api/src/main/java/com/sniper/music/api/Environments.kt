package com.sniper.music.api

sealed class Environment {

    object LastFMAPI: Environment() {
        override val productionUrl: String
            get() = "http://ws.audioscrobbler.com/2.0/"

        override val testUrl: String
            get() = "http://ws.audioscrobbler.com/2.0/"
    }

    fun getBuildUrl(isDebug: Boolean): String =
            when {
                isDebug -> testUrl
                else -> productionUrl
            }

    abstract val productionUrl: String
    abstract val testUrl: String
}
