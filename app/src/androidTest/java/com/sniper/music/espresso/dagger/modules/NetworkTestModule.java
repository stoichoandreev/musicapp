package com.sniper.music.espresso.dagger.modules;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.annotation.NonNull;

import com.sniper.music.api.ApiConfiguration;
import com.sniper.music.api.RetrofitClient;
import com.sniper.music.espresso.retrofit.MockRetrofitClient;
import com.sniper.music.base.di.modules.NetworkModule;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;

public class NetworkTestModule extends NetworkModule {

    private static NetworkTestModule instance = new NetworkTestModule();

    private static RetrofitClient lastFMRetrofitClient;

    public static NetworkTestModule getInstance() {
        return instance;
    }

    public RetrofitClient provideLastFMClient(@NonNull Converter.Factory converterFactory,
                                              @NonNull CallAdapter.Factory callAdapterFactory,
                                              @NonNull OkHttpClient okHttpClient,
                                              @NonNull ApiConfiguration callistoConfig) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (lastFMRetrofitClient == null) {
            lastFMRetrofitClient = new MockRetrofitClient(converterFactory, okHttpClient);
        }

        return lastFMRetrofitClient;
    }


    public Scheduler provideBackgroundScheduler() {
        return Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
