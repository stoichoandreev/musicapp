package com.sniper.music.home.mvp;

import android.support.annotation.Nullable;

import com.sniper.music.base.mvp.Presenter;
import com.sniper.music.base.mvp.BasePresenterView;

public interface HomePresenter extends Presenter {

    void fetchSearchResults(@Nullable String newText);

    interface View extends BasePresenterView {

    }
}
