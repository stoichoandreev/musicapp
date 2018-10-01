package com.sniper.music.details.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sniper.music.base.mvp.BasePresenterView;
import com.sniper.music.base.mvp.Presenter;
import com.sniper.music.details.models.DetailsViewModel;

public interface DetailsPresenter<T extends BasePresenterView> extends Presenter<T> {

    void fetchDetails(@Nullable String query);

    interface View extends BasePresenterView {

        void showDetails(@NonNull DetailsViewModel detailsInfo);

    }
}
