package com.sniper.music.api

import okhttp3.Interceptor
import okhttp3.Response

class LastFMClientInterceptor : Interceptor {

    companion object {
        private const val HEADER = "header"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val requestBuilderWithUserAgent = originRequest.newBuilder()
                .addHeader(HEADER, "some value")

        return chain.proceed(requestBuilderWithUserAgent.build())
    }
}
