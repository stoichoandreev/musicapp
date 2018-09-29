package com.sniper.music.details.di;

import android.support.annotation.NonNull;

import com.sniper.music.details.mvp.DefaultDetailsPresenter;
import com.sniper.music.details.mvp.DetailsPresenter;

import dagger.Module;
import dagger.Provides;


@Module
public class DetailsModule {

    private final DetailsPresenter.View view;

    public DetailsModule(@NonNull DetailsPresenter.View view) {
        this.view = view;
    }

//    @Provides
//    @HomeScope
//    public HomeSearchApi providesHomeSearchApi(@NonNull @Named(LastFMClient.NAME) RetrofitClient lastFMClient) {
//        return lastFMClient.api(HomeSearchApi.class);
//    }
//
//    @Provides
//    @HomeScope
//    public HomeSearchService providesHomeSearchService(@NonNull HomeSearchApi homeSearchApi,
//                                                       @NonNull @Named(NetworkModule.MAIN_THREAD) Scheduler notifications,
//                                                       @NonNull @Named(NetworkModule.BACKGROUND_THREAD) Scheduler worker) {
//        return new HomeSearchService(homeSearchApi, notifications, worker);
//    }

    @Provides
    @DetailsScope
    public DetailsPresenter providesPresenter() {
        return new DefaultDetailsPresenter(view);
    }

}
