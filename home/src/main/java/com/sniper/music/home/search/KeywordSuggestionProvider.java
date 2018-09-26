package com.sniper.music.home.search;

import android.content.SearchRecentSuggestionsProvider;

public class KeywordSuggestionProvider extends SearchRecentSuggestionsProvider {

    public final static String AUTHORITY = KeywordSuggestionProvider.class.getName();
    public final static int MODE = DATABASE_MODE_QUERIES;

    public KeywordSuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
