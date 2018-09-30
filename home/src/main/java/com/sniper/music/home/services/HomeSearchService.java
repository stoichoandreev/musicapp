package com.sniper.music.home.services;

import android.support.annotation.NonNull;

import com.sniper.music.api.lastFm.dataModels.AlbumResponse;
import com.sniper.music.api.lastFm.dataModels.TrackResponse;
import com.sniper.music.home.api.HomeSearchApi;
import com.sniper.music.home.converter.HomeViewModelConverter;
import com.sniper.music.home.models.HomeAdapterViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;


public class HomeSearchService {
    @NonNull
    private HomeSearchApi api;
    @NonNull
    private HomeViewModelConverter converter;
    @NonNull
    private Scheduler notifications;
    @NonNull
    private Scheduler worker;

    public HomeSearchService(@NonNull HomeSearchApi api,
                             @NonNull HomeViewModelConverter converter,
                             @NonNull Scheduler notifications,
                             @NonNull Scheduler worker) {
        this.api = api;
        this.converter = converter;
        this.notifications = notifications;
        this.worker = worker;
    }

    public Observable<List<HomeAdapterViewModel>> doArtistSearch(@NonNull String artist) {
        return api.artistSearch(artist)
                .map(response -> converter.extractListFromArtistResponse(response))
                .subscribeOn(worker)
                .observeOn(notifications);
    }

    public Observable<AlbumResponse> doAlbumSearch(@NonNull String album) {
        return api.albumSearch(album)
                .subscribeOn(worker)
                .observeOn(notifications);
    }

    public Observable<TrackResponse> doTrackSearch(@NonNull String track) {
        return api.trackSearch(track)
                .subscribeOn(worker)
                .observeOn(notifications);
    }
}
