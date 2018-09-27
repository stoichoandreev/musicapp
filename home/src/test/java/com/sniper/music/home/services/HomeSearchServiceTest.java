package com.sniper.music.home.services;

import com.sniper.music.home.api.HomeSearchApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class HomeSearchServiceTest {

    @Mock
    private HomeSearchApi mockedHomeSearchApi;
    @Mock
    private Scheduler mockedNotificationsScheduler;
    @Mock
    private Scheduler mockedWorkerScheduler;

    private HomeSearchService tested;

    @Before
    public void setup() {
        tested = new HomeSearchService(mockedHomeSearchApi, mockedNotificationsScheduler, mockedWorkerScheduler);
    }

    @Test
    public void test_artist_search_returns_result_subscribe_and_notify() {
        //given
        final String query = "Sam Smith";
        final Observable<String> resultObservableSpy = PowerMockito.spy(Observable.just(""));
        when(mockedHomeSearchApi.artistSearch(query)).thenReturn(resultObservableSpy);
        when(resultObservableSpy.subscribeOn(mockedWorkerScheduler)).thenReturn(resultObservableSpy);

        //when
        tested.doArtistSearch(query);
        //test
        verify(mockedHomeSearchApi).artistSearch(query);
        verify(resultObservableSpy).subscribeOn(mockedWorkerScheduler);
        verify(resultObservableSpy).observeOn(mockedNotificationsScheduler);
    }

    @Test
    public void test_album_search_returns_result_subscribe_and_notify() {
        //given
        final String query = "Greatest hits";
        final Observable<String> resultObservableSpy = PowerMockito.spy(Observable.just(""));
        when(mockedHomeSearchApi.albumSearch(query)).thenReturn(resultObservableSpy);
        when(resultObservableSpy.subscribeOn(mockedWorkerScheduler)).thenReturn(resultObservableSpy);

        //when
        tested.doAlbumSearch(query);
        //test
        verify(mockedHomeSearchApi).albumSearch(query);
        verify(resultObservableSpy).subscribeOn(mockedWorkerScheduler);
        verify(resultObservableSpy).observeOn(mockedNotificationsScheduler);
    }

    @Test
    public void test_track_search_returns_result_subscribe_and_notify() {
        //given
        final String query = "Lay me down";
        final Observable<String> resultObservableSpy = PowerMockito.spy(Observable.just(""));
        when(mockedHomeSearchApi.trackSearch(query)).thenReturn(resultObservableSpy);
        when(resultObservableSpy.subscribeOn(mockedWorkerScheduler)).thenReturn(resultObservableSpy);

        //when
        tested.doTrackSearch(query);
        //test
        verify(mockedHomeSearchApi).trackSearch(query);
        verify(resultObservableSpy).subscribeOn(mockedWorkerScheduler);
        verify(resultObservableSpy).observeOn(mockedNotificationsScheduler);
    }
}