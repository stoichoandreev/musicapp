package com.sniper.music.base.mvp;

public interface Presenter<T extends BasePresenterView> {

    void attachView(T view);

    void detachView();

    void destroy();

}
