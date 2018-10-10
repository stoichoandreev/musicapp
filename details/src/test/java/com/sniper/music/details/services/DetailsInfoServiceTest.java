package com.sniper.music.details.services;

import com.sniper.music.api.lastfm.models.ArtistInfoResponse;
import com.sniper.music.details.api.DetailsInfoApi;
import com.sniper.music.details.converter.DetailsViewModelConverter;
import com.sniper.music.details.models.DetailsViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailsInfoServiceTest {

    @Mock
    private DetailsInfoApi mockedDetailsInfoApi;
    @Mock
    private DetailsViewModelConverter mockedDetailsViewModelConverter;

    private Scheduler notificationsScheduler = Schedulers.trampoline();
    private Scheduler workerScheduler = Schedulers.trampoline();

    private DetailsInfoService tested;

    @Before
    public void setup() {
        tested = new DetailsInfoService(mockedDetailsInfoApi,
                mockedDetailsViewModelConverter,
                notificationsScheduler,
                workerScheduler);
    }

    @Test
    public void test_details_info_returns_correct_result() {
        //given
        final String artist = "Sam Smith";
        final ArtistInfoResponse artistInfoResponse = new ArtistInfoResponse();
        final DetailsViewModel convertedArtistInfoResponseToViewModel = getArtistInfoConvertedResponse(artist);
        final Observable<ArtistInfoResponse> observableArtistResponse = Observable.just(artistInfoResponse);
        when(mockedDetailsInfoApi.artistInfo(artist)).thenReturn(observableArtistResponse);
        when(mockedDetailsViewModelConverter.extractFromArtistInfoResponse(artistInfoResponse)).thenReturn(convertedArtistInfoResponseToViewModel);

        //when
        final Observable<DetailsViewModel> result = tested.getArtistInformation(artist);
        //test
        final TestObserver<DetailsViewModel> testObserver = result.test();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);

        DetailsViewModel detailsInfoViewModel = testObserver.values().get(0);
        assertEquals(detailsInfoViewModel.getName(), artist);
        assertEquals(detailsInfoViewModel.getListeners(), "Listeners : 12345");
        assertEquals(detailsInfoViewModel.getPlayCount(), "Played : 54321");
    }

    private DetailsViewModel getArtistInfoConvertedResponse(String artist) {
        final DetailsViewModel convertedResponse = new DetailsViewModel.Builder("mbId")
                .name(artist)
                .listeners("Listeners : 12345")
                .playCount("Played : 54321")
                .build();
        return convertedResponse;
    }
}