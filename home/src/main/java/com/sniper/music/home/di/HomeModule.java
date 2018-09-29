package com.sniper.music.home.di;

import android.support.annotation.NonNull;

import com.sniper.music.api.RetrofitClient;
import com.sniper.music.api.lastFm.LastFMClient;
import com.sniper.music.base.di.modules.NetworkModule;
import com.sniper.music.home.api.HomeSearchApi;
import com.sniper.music.home.mvp.DefaultHomePresenter;
import com.sniper.music.home.mvp.HomePresenter;
import com.sniper.music.home.services.HomeSearchService;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;


@Module
public class HomeModule {

    private final HomePresenter.View view;

    public HomeModule(@NonNull HomePresenter.View view) {
        this.view = view;
    }

    @Provides
    @HomeScope
    public HomeSearchApi providesHomeSearchApi(@NonNull @Named(LastFMClient.NAME) RetrofitClient lastFMClient) {
        return lastFMClient.api(HomeSearchApi.class);
    }

    @Provides
    @HomeScope
    public HomeSearchService providesHomeSearchService(@NonNull HomeSearchApi homeSearchApi,
                                                       @NonNull @Named(NetworkModule.MAIN_THREAD) Scheduler notifications,
                                                       @NonNull @Named(NetworkModule.BACKGROUND_THREAD) Scheduler worker) {
        return new HomeSearchService(homeSearchApi, notifications, worker);
    }

    @Provides
    @HomeScope
    public HomePresenter providesPresenter(HomeSearchService homeSearchService) {
        return new DefaultHomePresenter(view, homeSearchService);
    }

}
