package com.sniper.music.api.lastfm

import com.sniper.music.api.ApiConfiguration
import com.sniper.music.api.RetrofitClient
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

class LastFMClient(converterFactory: Converter.Factory,
                   callAdapterFactory: CallAdapter.Factory,
                   okHttpClient: OkHttpClient,
                   config: ApiConfiguration) : RetrofitClient {

    private val restAdapter: Retrofit = Retrofit.Builder()
            .baseUrl(config.baseURL)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(okHttpClient)
            .validateEagerly(true)
            .build()

    companion object {
        const val NAME = "LastFMClient"
        const val GSON_CONVERTER_FACTORY = "LastFMGSONConverterFactory"
        const val RX_CALL_ADAPTER_FACTORY = "LastFMRXLastFMCallAdapterFactory"
        const val API_CONFIGURATION = "LastFMApiConfiguration"
        const val CERTIFICATE_PINNER = "LastFMApiCertificatePinner"
    }

    override fun <T> api(service: Class<T>): T {
        return restAdapter.create(service)
    }
}
