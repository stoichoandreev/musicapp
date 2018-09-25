package com.sniper.music.base.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends BasePresenterView> {

    @NonNull
    protected V mView;
    @NonNull
    private CompositeDisposable compositeDisposable;

    protected BasePresenter() {
        compositeDisposable = new CompositeDisposable();
    }

    public BasePresenter(@NonNull V view) {
        this();
        this.mView = view;
    }

    public void addDisposable(@Nullable Disposable newDisposable) {
        if (newDisposable != null) {
            getCompositeDisposable().add(newDisposable);
        }
    }

    public void clearAllDisposables() {
        if (getCompositeDisposable() != null) {
            getCompositeDisposable().dispose();
        }
    }

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}