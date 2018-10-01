package com.sniper.music.details.services;

import android.support.annotation.NonNull;

import com.sniper.music.details.api.DetailsInfoApi;
import com.sniper.music.details.converter.DetailsViewModelConverter;
import com.sniper.music.details.models.DetailsViewModel;

import io.reactivex.Observable;
import io.reactivex.Scheduler;


public class DetailsInfoService {
    @NonNull
    private DetailsInfoApi api;
    @NonNull
    private DetailsViewModelConverter converter;
    @NonNull
    private Scheduler notifications;
    @NonNull
    private Scheduler worker;

    public DetailsInfoService(@NonNull DetailsInfoApi api,
                              @NonNull DetailsViewModelConverter converter,
                              @NonNull Scheduler notifications,
                              @NonNull Scheduler worker) {
        this.api = api;
        this.converter = converter;
        this.notifications = notifications;
        this.worker = worker;
    }

    public Observable<DetailsViewModel> getArtistInformation(@NonNull String artist) {
        return api.artistInfo(artist)
                .map(response -> converter.extractFromArtistInfoResponse(response))
                .subscribeOn(worker)
                .observeOn(notifications);
    }
}
