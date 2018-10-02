package com.sniper.music.base.di.modules;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sniper.music.api.ApiConfiguration;
import com.sniper.music.api.RetrofitClient;
import com.sniper.music.api.lastfm.LastFMApiConfiguration;
import com.sniper.music.api.lastfm.LastFMClient;
import com.sniper.music.api.lastfm.LastFMClientInterceptor;
import com.sniper.music.api.okhttp.DefaultOkHttpConfig;
import com.sniper.music.api.okhttp.OKHttpConfig;
import com.sniper.music.base.BuildConfig;
import com.sniper.music.base.di.ApplicationScope;

import java.text.DateFormat;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Provider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    public static final String HTTP_BODY_LOGGING = "HttpBodyLogging";
    public static final String DEBUG_OK_HTTP_CLIENT = "DebugOkHttpClient";
    public static final String RELEASE_OK_HTTP_CLIENT = "ReleaseOkHttp3Client";
    public static final String BACKGROUND_THREAD = "BackgroundThread";
    public static final String MAIN_THREAD = "MainThread";

    @Provides
    @ApplicationScope
    public OKHttpConfig providesOkHttpConfig() {
        return new DefaultOkHttpConfig();
    }

    @Provides
    @ApplicationScope
    @Named(HTTP_BODY_LOGGING)
    public HttpLoggingInterceptor provideHttpBodyLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @ApplicationScope
    public LastFMClientInterceptor provideUserAgentInterceptor() {
        return new LastFMClientInterceptor();
    }

    @Provides
    @ApplicationScope
    @Named(DEBUG_OK_HTTP_CLIENT)
    public OkHttpClient provideDebugOkHttpClient(@NonNull OKHttpConfig okHttpConfig,
                                                 @NonNull @Named(HTTP_BODY_LOGGING) HttpLoggingInterceptor loggingInterceptor,
                                                 @NonNull LastFMClientInterceptor clientInterceptor) {
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
    public OkHttpClient provideSafeOkHttpClient(@NonNull OKHttpConfig okHttpConfig,
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
    public OkHttpClient provideOkHttpClient(
            @Named(RELEASE_OK_HTTP_CLIENT) Provider<OkHttpClient> releaseClient,
            @Named(DEBUG_OK_HTTP_CLIENT) Provider<OkHttpClient> debugClient) {
        return BuildConfig.DEBUG ? debugClient.get() : releaseClient.get();
    }

    @Provides
    @ApplicationScope
    @Named("DefaultGSONSetup")
    public Gson provideGSON() {
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
    public Converter.Factory provideGsonConverterFactory(@NonNull @Named("DefaultGSONSetup") Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @ApplicationScope
    @Named(LastFMClient.RX_CALL_ADAPTER_FACTORY)
    public CallAdapter.Factory provideRxCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @ApplicationScope
    @Named(LastFMClient.API_CONFIGURATION)
    public ApiConfiguration provideApiConfiguration() {
        return new LastFMApiConfiguration(BuildConfig.DEBUG);
    }

    @Provides
    @ApplicationScope
    @Named(LastFMClient.NAME)
    public RetrofitClient provideLastFMClient(@NonNull @Named(LastFMClient.GSON_CONVERTER_FACTORY) Converter.Factory converterFactory,
                                              @NonNull @Named(LastFMClient.RX_CALL_ADAPTER_FACTORY) CallAdapter.Factory callAdapterFactory,
                                              @NonNull OkHttpClient okHttpClient,
                                              @NonNull @Named(LastFMClient.API_CONFIGURATION) ApiConfiguration apiConfig) {
        return new LastFMClient(converterFactory, callAdapterFactory, okHttpClient, apiConfig);
    }

    @Provides
    @ApplicationScope
    @Named(BACKGROUND_THREAD)
    public Scheduler provideBackgroundScheduler() {
        return Schedulers.io();
    }

    @Provides
    @ApplicationScope
    @Named(MAIN_THREAD)
    public Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
