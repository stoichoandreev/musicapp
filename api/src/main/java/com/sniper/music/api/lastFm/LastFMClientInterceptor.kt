package com.sniper.music.api.lastFm

import okhttp3.Interceptor
import okhttp3.Response

class LastFMClientInterceptor : Interceptor {

    companion object {
//        private const val HEADER = "header"
        private const val API_KEY = "api_key"
        private const val FORMAT = "format"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder()
                //Must be stored on better place
                .addQueryParameter(API_KEY, "")
//                .addQueryParameter(API_KEY, "b9304bb9f3e9c5397188fbb0381ade7d")
                .addQueryParameter(FORMAT, "json")
                .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)

//        val requestBuilderWithUserAgent = originRequest.newBuilder()
//                .addHeader(HEADER, "some value")
//        return chain.proceed(requestBuilderWithUserAgent.build())
    }
}
