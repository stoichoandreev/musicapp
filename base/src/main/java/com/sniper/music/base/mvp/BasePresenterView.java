package com.sniper.music.base.mvp;

public interface BasePresenterView {
    void showError(String errorMessage);
    void showLoading(boolean show);
}
