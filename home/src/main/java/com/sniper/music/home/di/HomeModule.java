package com.sniper.music.home.di;

import android.support.annotation.NonNull;

import com.sniper.music.home.mvp.DefaultHomePresenter;
import com.sniper.music.home.mvp.HomePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    private final HomePresenter.View view;

    public HomeModule(@NonNull HomePresenter.View view) {
        this.view = view;
    }

    @Provides
    @HomeScope
    public HomePresenter providesPresenter() {
        return new DefaultHomePresenter(view);
    }

}
