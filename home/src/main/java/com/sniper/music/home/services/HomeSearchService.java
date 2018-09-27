package com.sniper.music.home.services;

import android.support.annotation.NonNull;

import com.sniper.music.home.api.HomeSearchApi;

import io.reactivex.Observable;
import io.reactivex.Scheduler;


public class HomeSearchService {
    @NonNull
    private HomeSearchApi api;
    @NonNull
    private Scheduler notifications;
    @NonNull
    private Scheduler worker;

    public HomeSearchService(@NonNull HomeSearchApi api,
                             @NonNull Scheduler notifications,
                             @NonNull Scheduler worker) {
        this.api = api;
        this.notifications = notifications;
        this.worker = worker;
    }

    public Observable<String> doArtistSearch(@NonNull String artist) {
        return api.artistSearch(artist)
                .subscribeOn(worker)
                .observeOn(notifications);
    }

    public Observable<String> doAlbumSearch(@NonNull String album) {
        return api.albumSearch(album)
                .subscribeOn(worker)
                .observeOn(notifications);
    }

    public Observable<String> doTrackSearch(@NonNull String track) {
        return api.trackSearch(track)
                .subscribeOn(worker)
                .observeOn(notifications);
    }
}
