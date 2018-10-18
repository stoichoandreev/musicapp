package com.sniper.music.home.mvp;

import android.content.Context;

import com.sniper.music.home.models.HomeAdapterViewModel;
import com.sniper.music.home.search.HomeRecentSearchesService;
import com.sniper.music.home.services.HomeSearchService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultHomePresenterTest {

    @Mock
    private HomePresenter.View mockedView;
    @Mock
    private HomeSearchService mockSearchService;
    @Mock
    private HomeRecentSearchesService mockRecentSearchesService;
    @Mock
    private CompositeDisposable mockCompositeDisposable;

    private Scheduler mockDebounceWorker = Schedulers.trampoline();

    private HomePresenter tested;

    @Before
    public void setUp() {
        tested = new DefaultHomePresenter(mockedView,
                mockSearchService,
                mockRecentSearchesService,
                mockCompositeDisposable,
                mockDebounceWorker);
    }

    @Test
    public void test_presenter_attaches_view() {
        //given
        final HomePresenter.View view = Mockito.mock(HomePresenter.View.class);
        //when
        tested.attachView(view);
        //test
        assertNotNull(mockedView);
    }

    @Test
    public void test_presenter_attaches_view_and_reuse_cache_when_screen_rotate() {
        //given
        final List<HomeAdapterViewModel> list = new ArrayList<>();
        when(mockSearchService.doArtistSearch("some")).thenReturn(Observable.just(list));
        final HomePresenter testedPresenterSpy = Mockito.spy(tested);
        //when
        testedPresenterSpy.fetchSearchResults("some");
        testedPresenterSpy.attachView(mockedView);
        //test
        Mockito.verify(mockedView).showSearchResults(list);
    }

    @Test
    public void test_presenter_destroys_all_subscriptions() {
        //when
        tested.destroy();
        //test
        Mockito.verify(mockCompositeDisposable).dispose();
    }

    @Test
    public void test_presenter_displays_and_hide_progress_loader_when_fetches_search_results() {
        //given
        final String query = "Cher";
        when(mockSearchService.doArtistSearch(query)).thenReturn(Observable.just(new ArrayList<>()));
        //when
        tested.attachView(mockedView);
        tested.fetchSearchResults(query);
        //test
        Mockito.verify(mockedView).showLoading(true);
        Mockito.verify(mockedView).showLoading(false);
    }

    @Test
    public void test_presenter_does_not_display_progress_loader_when_fetches_search_results() {
        //given
        final String query = "C";
        //when
        tested.fetchSearchResults(query);
        //test
        Mockito.verify(mockedView, times(0)).showLoading(true);
    }

    @Test
    public void test_presenter_displays_error_message_when_fetches_search_results() {
        //given
        final String query = "Metal";
        final String errorMessage = "Some Error";
        when(mockSearchService.doArtistSearch(query)).thenReturn(Observable.error(new Throwable(errorMessage)));
        //when
        tested.fetchSearchResults(query);
        //test
        Mockito.verify(mockedView).showLoading(false);
        Mockito.verify(mockedView).showError(errorMessage);
    }

    @Test
    public void test_presenter_saves_recent_search() {
        //given
        final String query = "Hello";
        final Context context = Mockito.mock(Context.class);
        //when
        tested.saveRecentSearch(context, query);
        //test
        Mockito.verify(mockRecentSearchesService).storeRecentSearch(context, query);
    }
}