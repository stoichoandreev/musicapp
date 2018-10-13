package com.sniper.music.details.mvp;

import com.sniper.music.details.models.DetailsViewModel;
import com.sniper.music.details.services.DetailsInfoService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DefaultDetailsPresenterTest {

    @Mock
    private DetailsPresenter.View mockedView;
    @Mock
    private DetailsInfoService mockDetailsInfoService;
    @Mock
    private CompositeDisposable mockCompositeDisposable;

    private DetailsPresenter tested;

    @Before
    public void setUp() {
        tested = new DefaultDetailsPresenter(mockedView,
                mockDetailsInfoService,
                mockCompositeDisposable);
    }

    @Test
    public void test_presenter_attaches_view() {
        //given
        final DetailsPresenter.View view = Mockito.mock(DetailsPresenter.View.class);
        //when
        tested.attachView(view, false);
        //test
        assertNotNull(mockedView);
    }

    @Test
    public void test_presenter_attaches_view_and_reuse_cache_when_screen_rotate() {
        //given
        final DetailsPresenter.View view = Mockito.mock(DetailsPresenter.View.class);
        final DetailsViewModel viewModel = new DetailsViewModel.Builder("dad").build();
        when(mockDetailsInfoService.getArtistInformation("some")).thenReturn(Observable.just(viewModel));
        final DetailsPresenter testedPresenterSpy = Mockito.spy(tested);
        //when
        testedPresenterSpy.fetchDetails("some");
        testedPresenterSpy.attachView(view, true);
        //test
        Mockito.verify(mockedView, times(2)).showDetails(viewModel);
    }

    @Test
    public void test_presenter_attaches_view_and_does_not_use_cache() {
        //given
        final DetailsPresenter.View view = Mockito.mock(DetailsPresenter.View.class);
        final DetailsPresenter testedPresenterSpy = Mockito.spy(tested);
        //when
        testedPresenterSpy.attachView(view, false);
        //test
        Mockito.verify(testedPresenterSpy, times(0)).fetchDetails(null);
        Mockito.verify(view, times(0)).showDetails(any());
    }

    @Test
    public void test_presenter_destroys_all_subscriptions() {
        //when
        tested.destroy();
        //test
        Mockito.verify(mockCompositeDisposable).dispose();
    }

    @Test
    public void test_presenter_displays_and_hides_progress_loader_when_fetches_details_info() {
        //given
        final String query = "Cher";
        final DetailsViewModel viewModel = new DetailsViewModel.Builder("dad").build();
        when(mockDetailsInfoService.getArtistInformation(query)).thenReturn(Observable.just(viewModel));
        //when
        tested.fetchDetails(query);
        //test
        Mockito.verify(mockedView).showLoading(true);
        Mockito.verify(mockedView).showLoading(false);
    }

    @Test
    public void test_presenter_does_not_display_progress_loader_when_fetches_details_info() {
        //given
        final String query = null;
        //when
        tested.fetchDetails(query);
        //test
        Mockito.verify(mockedView, times(0)).showLoading(true);
    }

    @Test
    public void test_presenter_displays_error_message_when_fetches_details_info() {
        //given
        final String query = "Metal";
        final String errorMessage = "Some Error";
        when(mockDetailsInfoService.getArtistInformation(query)).thenReturn(Observable.error(new Throwable(errorMessage)));
        //when
        tested.fetchDetails(query);
        //test
        Mockito.verify(mockedView).showLoading(false);
        Mockito.verify(mockedView).showError(errorMessage);
    }

}