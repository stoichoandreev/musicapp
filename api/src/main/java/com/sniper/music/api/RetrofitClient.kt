package com.sniper.music.api

interface RetrofitClient {
    fun <T> api(service: Class<T>): T
}
