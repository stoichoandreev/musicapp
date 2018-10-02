package com.sniper.music.details.di;

import android.support.annotation.NonNull;

import com.sniper.music.api.RetrofitClient;
import com.sniper.music.api.lastfm.LastFMClient;
import com.sniper.music.base.di.modules.NetworkModule;
import com.sniper.music.details.api.DetailsInfoApi;
import com.sniper.music.details.converter.DetailsViewModelConverter;
import com.sniper.music.details.mvp.DefaultDetailsPresenter;
import com.sniper.music.details.mvp.DetailsPresenter;
import com.sniper.music.details.services.DetailsInfoService;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;


@Module
public class DetailsModule {

    @Provides
    @DetailsScope
    static DetailsInfoApi providesDetailsInfoApi(@NonNull @Named(LastFMClient.NAME) RetrofitClient lastFMClient) {
        return lastFMClient.api(DetailsInfoApi.class);
    }

    @Provides
    @DetailsScope
    static DetailsInfoService providesHomeSearchService(@NonNull DetailsInfoApi detailsInfoApi,
                                                        @NonNull DetailsViewModelConverter converter,
                                                        @NonNull @Named(NetworkModule.MAIN_THREAD) Scheduler notifications,
                                                        @NonNull @Named(NetworkModule.BACKGROUND_THREAD) Scheduler worker) {
        return new DetailsInfoService(detailsInfoApi, converter, notifications, worker);
    }

    @Provides
    @DetailsScope
    static DetailsPresenter providesPresenter(@NonNull DetailsInfoService detailsInfoService) {
        return new DefaultDetailsPresenter(null, detailsInfoService, new CompositeDisposable());
    }

}
