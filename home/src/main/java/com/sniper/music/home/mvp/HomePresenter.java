package com.sniper.music.home.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sniper.music.base.mvp.BasePresenterView;
import com.sniper.music.base.mvp.Presenter;

public interface HomePresenter<T extends BasePresenterView> extends Presenter<T> {

    void fetchSearchResults(@Nullable String newQuery);

    void doRefresh();

    void saveRecentSearch(@NonNull Context context, @Nullable String query);

    interface View extends BasePresenterView {
        void showArtistSearchResults();
    }
}
