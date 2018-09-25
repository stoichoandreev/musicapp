package com.sniper.music.base.di.modules;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sniper.music.api.ApiConfiguration;
import com.sniper.music.api.DefaultOkHttpConfig;
import com.sniper.music.api.LastFMClient;
import com.sniper.music.api.LastFMClientInterceptor;
import com.sniper.music.api.OKHttpConfig;
import com.sniper.music.api.RetrofitClient;
import com.sniper.music.base.BuildConfig;
import com.sniper.music.base.di.ApplicationScope;

import java.text.DateFormat;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Provider;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    public static final String HTTP_BODY_LOGGING = "HttpBodyLogging";
    public static final String DEBUG_OK_HTTP_CLIENT = "DebugOkHttpClient";
    public static final String RELEASE_OK_HTTP_CLIENT = "ReleaseOkHttp3Client";

    @Provides
    @ApplicationScope
    static OKHttpConfig providesOkHttpConfig() {
        return new DefaultOkHttpConfig();
    }

    @Provides
    @ApplicationScope
    @Named(HTTP_BODY_LOGGING)
    static HttpLoggingInterceptor provideHttpBodyLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @ApplicationScope
    static LastFMClientInterceptor provideUserAgentInterceptor() {
        return new LastFMClientInterceptor();
    }

    @Provides
    @ApplicationScope
    @Named(DEBUG_OK_HTTP_CLIENT)
    static OkHttpClient provideDebugOkHttpClient(@NonNull OKHttpConfig okHttpConfig,
                                                 @NonNull @Named(HTTP_BODY_LOGGING) HttpLoggingInterceptor loggingInterceptor,
                                                 @NonNull LastFMClientInterceptor clientInterceptor) {
        //we can add here httpsTrustManager as well
        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(okHttpConfig.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(okHttpConfig.getReadTimeout(), TimeUnit.SECONDS)
                .writeTimeout(okHttpConfig.getWriteTimeout(), TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor);
        //we can add Stetho Interceptor here as well
        builder.interceptors().add(clientInterceptor);
        return builder.build();
    }

    @Provides
    @ApplicationScope
    @Named(RELEASE_OK_HTTP_CLIENT)
    static OkHttpClient provideSafeOkHttpClient(@NonNull OKHttpConfig okHttpConfig,
                                                @NonNull LastFMClientInterceptor clientInterceptor) {

        return new OkHttpClient.Builder()
                .connectTimeout(okHttpConfig.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(okHttpConfig.getReadTimeout(), TimeUnit.SECONDS)
                .writeTimeout(okHttpConfig.getWriteTimeout(), TimeUnit.SECONDS)
                .addInterceptor(clientInterceptor)
                .build();
    }

    /**
     * Uses provider to avoid unnecessary instantiation of the unused one.
     */
    @Provides
    @ApplicationScope
    static OkHttpClient provideOkHttpClient(
            @Named(RELEASE_OK_HTTP_CLIENT) Provider<OkHttpClient> releaseClient,
            @Named(DEBUG_OK_HTTP_CLIENT) Provider<OkHttpClient> debugClient) {
        return BuildConfig.DEBUG ? debugClient.get() : releaseClient.get();
    }


    @Provides
    @ApplicationScope
    @Named("DefaultGSONSetup")
    static Gson provideGSON() {
        return new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .setVersion(1.0)
                .create();
    }

    @Provides
    @ApplicationScope
    @Named(LastFMClient.GSON_CONVERTER_FACTORY)
    static Converter.Factory provideGsonConverterFactory(@NonNull @Named("DefaultGSONSetup") Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @ApplicationScope
    @Named(LastFMClient.NAME)
    static RetrofitClient provideLastFMClient(@NonNull @Named(LastFMClient.GSON_CONVERTER_FACTORY) Converter.Factory converterFactory,
                                              @NonNull @Named(LastFMClient.RX_CALL_ADAPTER_FACTORY) CallAdapter.Factory callAdapterFactory,
                                              @NonNull OkHttpClient okHttpClient,
                                              @NonNull ApiConfiguration apiConfig) {
        return new LastFMClient(converterFactory, callAdapterFactory, okHttpClient, apiConfig);
    }
}
