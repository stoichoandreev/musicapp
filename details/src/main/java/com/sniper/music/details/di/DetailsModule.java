package com.sniper.music.details.di;

import com.sniper.music.details.mvp.DefaultDetailsPresenter;
import com.sniper.music.details.mvp.DetailsPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module
public class DetailsModule {

    @Provides
    @DetailsScope
    static DetailsPresenter providesPresenter() {
        return new DefaultDetailsPresenter(new CompositeDisposable());
    }

}
