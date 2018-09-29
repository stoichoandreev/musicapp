package com.sniper.music.details.mvp;

import android.support.annotation.Nullable;

import com.sniper.music.base.mvp.BasePresenterView;
import com.sniper.music.base.mvp.Presenter;

public interface DetailsPresenter extends Presenter {

    void fetchDetails(@Nullable String newText);

    interface View extends BasePresenterView {
        void showDetails();
    }
}
