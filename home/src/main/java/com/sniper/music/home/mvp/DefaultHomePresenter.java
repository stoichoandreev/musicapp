package com.sniper.music.home.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sniper.music.api.lastFm.dataModels.ArtistResponse;
import com.sniper.music.base.mvp.BasePresenter;
import com.sniper.music.home.services.HomeSearchService;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class DefaultHomePresenter extends BasePresenter<HomePresenter.View> implements HomePresenter {

    private static final int KEY_WORD_MIN_SIZE = 2;

    private PublishSubject<Boolean> onInitialiseSubject =  PublishSubject.create();
    private PublishSubject<Boolean> onRefreshSubject =  PublishSubject.create();
    private PublishSubject<Boolean> onBottomPageReachedSubject =  PublishSubject.create();
    private BehaviorSubject<ArtistResponse> artistResponseSubject =  BehaviorSubject.create();
    private BehaviorSubject<String> searchParamsSubject =  BehaviorSubject.create();

    public DefaultHomePresenter(@NonNull HomePresenter.View view,
                                @NonNull HomeSearchService searchService) {
        super(view);
        setup(searchService);
    }

    private void setup(@NonNull HomeSearchService searchService) {

        final Observable<Boolean> mergedObservable =
                Observable.merge(onInitialiseSubject, onRefreshSubject, onBottomPageReachedSubject)
                        .doOnNext (newSearch -> {
                            if (newSearch) {
                                mView.showLoading();
                            }});

        addDisposable(mergedObservable
                .withLatestFrom(searchParamsSubject, Pair::of)
                .filter(pair -> pair.getRight() != null && pair.getRight().length() > KEY_WORD_MIN_SIZE)
                .flatMap(pair -> searchService.doArtistSearch(pair.getRight()))
                .subscribe(response -> artistResponseSubject.onNext(response)));
//                .flatMap(isNewSearch -> {
//            if (isNewSearch) {
////                pagingManager.reset()
//            }
//    })
//            searchParamsSubject.take(1)
//                    .map<SearchParams>({ searchParams -> presenterUtils.updateSearchParams(searchParams, timeService, isNewSearch = isNewSearch) })
//        )
//                .flatMap { _ ->
//                currentLocationService.forCurrentLocationLatLng
//                        .doOnError { error -> errorSubject.onNext(error.message) }
//        }
//                .flatMap({ currentLocationCoordinates ->
//                presenterUtils
//                        .fetchHomeFeed(searchParamsSubject.value, currentLocationCoordinates, pagingManager)
//                        .onErrorReturn { error ->
//                errorSubject.onNext(error.message)
//                null
//        }
//                .doOnError({ error -> errorSubject.onNext(error.message) })
//        })
//                .doOnNext { _ ->
//                view.showRefreshLoading(false)
//        }
//                .filter { response -> response != null && response.adverts != null }
//                .subscribe { searchResponse ->
//                val clearData = searchParamsSubject.value.isNewSearch
//            val advertising = searchResponse.advertising
//            val previousAds = presenterUtils.getPreviousAds(clearData, searchItems.value)
//            presenterUtils.updatePageManager(pagingManager, searchResponse.total)
//            prepareSearchItemsForDisplay(previousAds, searchResponse, advertising, clearData)
//            searchTracking.onNext(searchResponse.tracking)
//        })



        addDisposable(artistResponseSubject.subscribe(artistResponse -> {
            mView.showArtistSearchResults();
        }));
    }

    @Override
    public void destroy() {
        clearAllDisposables();
    }

    @Override
    public void fetchSearchResults(@Nullable String newText) {
        searchParamsSubject.onNext(newText);
    }
}
