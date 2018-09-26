package com.sniper.music.api

import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

class LastFMClient(converterFactory: Converter.Factory,
                   callAdapterFactory: CallAdapter.Factory,
                   okHttpClient: OkHttpClient,
                   config: ApiConfiguration) : RetrofitClient {

    private val restAdapter: Retrofit = Retrofit.Builder()
            .baseUrl(config.baseURL.toExternalForm())
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(okHttpClient)
            .validateEagerly(true)
            .build()

    companion object {
        public const val NAME = "LastFMClient"
        public const val GSON_CONVERTER_FACTORY = "GSONConverterFactory"
        public const val RX_CALL_ADAPTER_FACTORY = "RXLastFMCallAdapterFactory"
    }

    override fun <T> api(service: Class<T>): T {
        return restAdapter.create(service)
    }
}
