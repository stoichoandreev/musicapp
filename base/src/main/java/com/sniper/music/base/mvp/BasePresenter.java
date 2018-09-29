package com.sniper.music.base.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends BasePresenterView> {

    @Nullable
    protected V mView;
    @NonNull
    private CompositeDisposable compositeDisposable;

    private BasePresenter() {
        compositeDisposable = new CompositeDisposable();
    }

    public BasePresenter(@Nullable V view) {
        this();
        this.mView = view;
    }

    public void setView(@Nullable V view) {
        this.mView = view;
    }

    public void addDisposable(@Nullable Disposable newDisposable) {
        if (newDisposable != null) {
            compositeDisposable.add(newDisposable);
        }
    }

    public void clearAllDisposables() {
        compositeDisposable.dispose();
    }

}
