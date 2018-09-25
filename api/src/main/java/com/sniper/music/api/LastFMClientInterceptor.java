package com.sniper.music.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LastFMClientInterceptor implements Interceptor {

    private static final String HEADER = "header";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request originRequest = chain.request();
        final Request.Builder requestBuilderWithUserAgent = originRequest.newBuilder()
                .addHeader(HEADER, "some value");

        return chain.proceed(requestBuilderWithUserAgent.build());
    }
}
