package com.sniper.music.api

interface OKHttpConfig {

    val connectTimeout: Long

    val readTimeout: Long

    val writeTimeout: Long
}