package com.sniper.music.api;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class LastFMClient implements RetrofitClient {

    public static final String NAME = "LastFMClient";
    public static final String GSON_CONVERTER_FACTORY = "GSONConverterFactory";
    public static final String RX_CALL_ADAPTER_FACTORY = "RXLastFMCallAdapterFactory";

    @NonNull
    private final Retrofit restAdapter;

    public LastFMClient(@NonNull Converter.Factory converterFactory,
                        @NonNull CallAdapter.Factory callAdapterFactory,
                        @NonNull OkHttpClient okHttpClient,
                        @NonNull ApiConfiguration config) {

        restAdapter = new Retrofit.Builder()
                .baseUrl(config.getBaseURL().toExternalForm())
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .validateEagerly(true)
                .build();
    }

    @Override
    public <T> T api(Class<T> service) {
        return restAdapter.create(service);
    }
}
