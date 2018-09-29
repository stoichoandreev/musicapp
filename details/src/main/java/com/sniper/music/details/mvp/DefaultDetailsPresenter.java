package com.sniper.music.details.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sniper.music.api.lastFm.dataModels.ArtistResponse;
import com.sniper.music.base.mvp.BasePresenter;

import io.reactivex.subjects.BehaviorSubject;

public class DefaultDetailsPresenter extends BasePresenter<DetailsPresenter.View> implements DetailsPresenter {

    private BehaviorSubject<ArtistResponse> detailsResponseSubject =  BehaviorSubject.create();
    private BehaviorSubject<String> selectedItemIdSubject =  BehaviorSubject.create();

    public DefaultDetailsPresenter(@NonNull DetailsPresenter.View view) {
        super(view);
    }

    @Override
    public void destroy() {
        clearAllDisposables();
    }

    @Override
    public void fetchDetails(@Nullable String newText) {
        //unused
    }
}
