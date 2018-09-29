package com.sniper.music.details.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sniper.music.api.lastFm.dataModels.ArtistResponse;
import com.sniper.music.base.mvp.BasePresenter;
import com.sniper.music.base.mvp.BasePresenterView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;

public class DefaultDetailsPresenter implements DetailsPresenter<DetailsPresenter.View> {

    private BehaviorSubject<ArtistResponse> detailsResponseSubject =  BehaviorSubject.create();
    private BehaviorSubject<String> selectedItemIdSubject =  BehaviorSubject.create();

    @Nullable
    private DetailsPresenter.View view;
    @NonNull
    private CompositeDisposable disposables;

    public DefaultDetailsPresenter(@NonNull CompositeDisposable compositeDisposable) {
        disposables = compositeDisposable;
    }

    @Override
    public void attachView(DetailsPresenter.View detailsView) {
        view = detailsView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void destroy() {
        disposables.dispose();
    }

    @Override
    public void fetchDetails(@Nullable String newText) {
        //unused
    }
}
