package com.sniper.music.home.search;

import android.content.Context;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.sniper.music.base.services.RecentSearchService;

import javax.inject.Inject;

public class HomeRecentSearchesService implements RecentSearchService {

    @Inject
    public HomeRecentSearchesService() {
        //used by dagger
    }

    @Override
    public void storeRecentSearch(@Nullable Context context,
                                  @Nullable String query) {
        if (!TextUtils.isEmpty(query) && query.length() > 2) {
            final SearchRecentSuggestions suggestions = new SearchRecentSuggestions(context,
                    KeywordSuggestionProvider.AUTHORITY,
                    KeywordSuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);
        }
    }
}
