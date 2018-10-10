package com.sniper.music.home.search;

import android.content.Context;
import android.provider.SearchRecentSuggestions;
import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class HomeRecentSearchesServiceTest {

    private HomeRecentSearchesService tested = new HomeRecentSearchesService();

    @Test
    public void test_recent_searches_service_does_not_store_new_recent_search_item_if_query_is_not_right() {
        //given
        final String query = "";
        final Context context = Mockito.mock(Context.class);
        final HomeRecentSearchesService testedSpy = Mockito.spy(tested);
        PowerMockito.mockStatic(TextUtils.class);

        when(TextUtils.isEmpty(query)).thenReturn(true);
        //when
        testedSpy.storeRecentSearch(context, query);
        //test
        verify(testedSpy, times(0)).getRecentSearchesStorage(context, KeywordSuggestionProvider.AUTHORITY, KeywordSuggestionProvider.MODE);
    }

    @Test
    public void test_recent_searches_service_stores_new_recent_search_item() {
        //given
        final String query = "boom";
        final Context context = Mockito.mock(Context.class);
        final SearchRecentSuggestions recentSearchesStorage = Mockito.mock(SearchRecentSuggestions.class);
        final HomeRecentSearchesService testedSpy = Mockito.spy(tested);
        when(testedSpy.getRecentSearchesStorage(context, KeywordSuggestionProvider.AUTHORITY, KeywordSuggestionProvider.MODE)).thenReturn(recentSearchesStorage);
        PowerMockito.mockStatic(TextUtils.class);

        when(TextUtils.isEmpty(query)).thenReturn(false);
        //when
        testedSpy.storeRecentSearch(context, query);
        //test
        verify(testedSpy).getRecentSearchesStorage(context, KeywordSuggestionProvider.AUTHORITY, KeywordSuggestionProvider.MODE);
        verify(recentSearchesStorage).saveRecentQuery(query, null);
    }
}
