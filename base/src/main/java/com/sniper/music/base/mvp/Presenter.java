package com.sniper.music.base.mvp;

public interface Presenter<T extends BasePresenterView> {

    void attachView(T view, boolean wasSavedInstanceState);

    void detachView();

    void destroy();

}
