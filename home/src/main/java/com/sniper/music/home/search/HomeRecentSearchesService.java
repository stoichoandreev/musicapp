package com.sniper.music.home.search;

import android.content.Context;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.sniper.music.base.services.RecentSearchService;
import com.sniper.music.home.mvp.DefaultHomePresenter;

import javax.inject.Inject;

public class HomeRecentSearchesService implements RecentSearchService {

    @Inject
    public HomeRecentSearchesService() {
        //used by dagger
    }

    @Override
    public void storeRecentSearch(@Nullable Context context,
                                  @Nullable String query) {
        if (!TextUtils.isEmpty(query) && query.length() >= DefaultHomePresenter.KEY_WORD_MIN_SIZE) {
            final SearchRecentSuggestions suggestions = getRecentSearchesStorage(context,
                    KeywordSuggestionProvider.AUTHORITY,
                    KeywordSuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);
        }
    }

    SearchRecentSuggestions getRecentSearchesStorage(@Nullable Context context,
                                                     @NonNull String authority,
                                                     int mode) {
        return new SearchRecentSuggestions(context, authority, mode);
    }
}
