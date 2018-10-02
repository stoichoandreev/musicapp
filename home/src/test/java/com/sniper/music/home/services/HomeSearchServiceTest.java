package com.sniper.music.home.services;

import com.sniper.music.api.lastfm.models.AlbumResponse;
import com.sniper.music.api.lastfm.models.Artist;
import com.sniper.music.api.lastfm.models.ArtistMatches;
import com.sniper.music.api.lastfm.models.ArtistResponse;
import com.sniper.music.api.lastfm.models.ArtistResults;
import com.sniper.music.api.lastfm.models.ImageDataModel;
import com.sniper.music.api.lastfm.models.TrackResponse;
import com.sniper.music.home.api.HomeSearchApi;
import com.sniper.music.home.converter.HomeViewModelConverter;
import com.sniper.music.home.models.HomeAdapterViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomeSearchServiceTest {

    @Rule
    public RxImmediateSchedulerRule testTrampolineSchedulerRule = new RxImmediateSchedulerRule();

    @Mock
    private HomeSearchApi mockedHomeSearchApi;
    @Mock
    private HomeViewModelConverter mockedHomeViewModelConverter;

    private Scheduler notificationsScheduler = Schedulers.trampoline();
    private Scheduler workerScheduler = Schedulers.trampoline();

    private HomeSearchService tested;

    @Before
    public void setup() {
        tested = new HomeSearchService(mockedHomeSearchApi,
                mockedHomeViewModelConverter,
                notificationsScheduler,
                workerScheduler);
    }

    @Test
    public void test_artist_search_returns_correct_result() {
        //given
        final String query = "Sam Smith";
        final ArtistResponse artistResponse = getArtistResponse();
        final Observable<ArtistResponse> observableArtistResponse = Observable.just(artistResponse);
        when(mockedHomeSearchApi.artistSearch(query)).thenReturn(observableArtistResponse);

        //when
        final Observable<List<HomeAdapterViewModel>> result = tested.doArtistSearch(query);
        //test
        final TestObserver<List<HomeAdapterViewModel>> testObserver = result.test();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);

        List<HomeAdapterViewModel> listResult = testObserver.values().get(0);
        assertThat(listResult.size(), is(2));
    }

    @Test
    public void test_album_search_returns_correct_result() {
        //given
        final String query = "Sam Smith";
        final AlbumResponse albumResponse = new AlbumResponse();
        final Observable<AlbumResponse> observableAlbumResponse = Observable.just(albumResponse);
        when(mockedHomeSearchApi.albumSearch(query)).thenReturn(observableAlbumResponse);
        //when
        final Observable<AlbumResponse> result = tested.doAlbumSearch(query);
        //test
        final TestObserver<AlbumResponse> testObserver = result.test();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
    }

    @Test
    public void test_track_search_returns_correct_result() {
        //given
        final String query = "Sam Smith";
        final TrackResponse trackResponse = new TrackResponse();
        final Observable<TrackResponse> observableTrackResponse = Observable.just(trackResponse);
        when(mockedHomeSearchApi.trackSearch(query)).thenReturn(observableTrackResponse);
        //when
        final Observable<TrackResponse> result = tested.doTrackSearch(query);
        //test
        final TestObserver<TrackResponse> testObserver = result.test();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
    }

    @Test
    public void test_album_search_makes_api_call() {
        //given
        final String query = "Greatest hits";
        final Observable<AlbumResponse> resultObservableSpy = PowerMockito.spy(Observable.just(new AlbumResponse()));
        when(mockedHomeSearchApi.albumSearch(query)).thenReturn(resultObservableSpy);
        when(resultObservableSpy.subscribeOn(workerScheduler)).thenReturn(resultObservableSpy);

        //when
        tested.doAlbumSearch(query);
        //test
        verify(mockedHomeSearchApi).albumSearch(query);
    }

    @Test
    public void test_track_search_makes_api_call() {
        //given
        final String query = "Lay me down";
        final Observable<TrackResponse> resultObservableSpy = PowerMockito.spy(Observable.just(new TrackResponse()));
        when(mockedHomeSearchApi.trackSearch(query)).thenReturn(resultObservableSpy);
        when(resultObservableSpy.subscribeOn(workerScheduler)).thenReturn(resultObservableSpy);

        //when
        tested.doTrackSearch(query);
        //test
        verify(mockedHomeSearchApi).trackSearch(query);
    }

    private ArtistResponse getArtistResponse() {

        final ArtistResponse response = new ArtistResponse();
        final ArtistResults artistResults = new ArtistResults();
        final ArtistMatches artistMatches = new ArtistMatches();
        final List<Artist> artistList = new ArrayList<>();
        final List<ImageDataModel> images = new ArrayList<>();
        final ImageDataModel imageSmall = new ImageDataModel();
        final ImageDataModel imageMedium = new ImageDataModel();
        imageSmall.setSize("Small");
        imageSmall.setUrl("http://adsadsa/adasd/small");
        imageMedium.setSize("Medium");
        imageMedium.setUrl("http://adsadsa/adasd/medium");
        images.add(imageSmall);
        images.add(imageMedium);

        final Artist artist1 = new Artist();
        final Artist artist2 = new Artist();
        artist1.setImages(images);
        artist1.setMbid("mbid11");
        artist1.setListeners("100");
        artist1.setName("Artist1");
        artist1.setUrl("some_url1");

        artist2.setImages(images);
        artist2.setMbid("mbid22");
        artist2.setListeners("200");
        artist2.setName("Artist2");
        artist2.setUrl("some_url2");

        artistList.add(artist1);
        artistList.add(artist2);

        artistMatches.setArtist(artistList);
        artistResults.setArtistMatches(artistMatches);
        artistResults.setStartIndex("0");
        artistResults.setTotalResults("1000");
        response.setResult(artistResults);

        return response;
    }
}