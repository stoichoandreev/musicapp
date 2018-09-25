package com.sniper.music.base.mvp;

public interface BasePresenterView {
    void showError(Throwable error);
    void showLoading();
    void hideLoading();
}
