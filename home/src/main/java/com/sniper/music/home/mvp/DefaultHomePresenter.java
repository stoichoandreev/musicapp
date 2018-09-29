package com.sniper.music.home.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sniper.music.home.models.HomeAdapterViewModel;
import com.sniper.music.home.search.HomeRecentSearchesService;
import com.sniper.music.home.services.HomeSearchService;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class DefaultHomePresenter implements HomePresenter<HomePresenter.View> {

    private static final int KEY_WORD_MIN_SIZE = 2;
    private static final int DEBOUNCE_TIMEOUT = 400;

    private PublishSubject<Boolean> onSearchSubject = PublishSubject.create();
    private PublishSubject<Boolean> onRefreshSubject = PublishSubject.create();
    private PublishSubject<Boolean> onBottomPageReachedSubject = PublishSubject.create();
    private PublishSubject<Throwable> onErrorSubject = PublishSubject.create();
    private PublishSubject<Boolean> showHideLoadingSubject = PublishSubject.create();
    private BehaviorSubject<List<HomeAdapterViewModel>> viewModelListSubject = BehaviorSubject.create();
    private BehaviorSubject<String> searchParamsSubject = BehaviorSubject.create();

    @Nullable
    private HomePresenter.View view;
    @NonNull
    private HomeSearchService searchService;
    @NonNull
    private HomeRecentSearchesService recentSearchesService;
    @NonNull
    private CompositeDisposable disposables;
    @NonNull
    private Scheduler debounceWorker;

    public DefaultHomePresenter(@NonNull HomeSearchService searchService,
                                @NonNull HomeRecentSearchesService recentSearchesService,
                                @NonNull CompositeDisposable compositeDisposable,
                                @NonNull Scheduler debounceWorker) {
        this.searchService = searchService;
        this.recentSearchesService = recentSearchesService;
        this.disposables = compositeDisposable;
        this.debounceWorker = debounceWorker;
        setup();
    }

    private void setup() {

        final Observable<Boolean> mergedObservable =
                Observable.merge(onSearchSubject, onRefreshSubject, onBottomPageReachedSubject)
                        .doOnNext(newSearch -> showHideLoadingSubject.onNext(newSearch));

        disposables.add(viewModelListSubject.subscribe(items -> {
            if (view != null) {
                showHideLoadingSubject.onNext(false);
                view.showSearchResults(items);
            }
        }));

        disposables.add(onErrorSubject.subscribe(error -> {
            if (view != null) {
                view.showError(error.getMessage());
            }
        }));

        disposables.add(showHideLoadingSubject.subscribe(show -> {
            if (view != null) {
                view.showLoading(show);
            }
        }));

        disposables.add(mergedObservable
                .withLatestFrom(searchParamsSubject, Pair::of)
                .debounce(DEBOUNCE_TIMEOUT, TimeUnit.MILLISECONDS, debounceWorker)
                .filter(pair -> {
                    final boolean shouldFilter = pair.getRight() != null && pair.getRight().length() >= KEY_WORD_MIN_SIZE;
                    showHideLoadingSubject.onNext(shouldFilter);
                    return shouldFilter;
                })
                .switchMap(pair -> searchService.doArtistSearch(pair.getRight())
                        .onExceptionResumeNext(Observable.empty()))
                .subscribe(response -> viewModelListSubject.onNext(response)));
    }

    @Override
    public void attachView(HomePresenter.View homeView) {
        view = homeView;
        fetchSearchResults(searchParamsSubject.getValue());
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void destroy() {
        disposables.dispose();
    }

    @Override
    public void fetchSearchResults(@Nullable String newQuery) {
        if (newQuery != null) {
            searchParamsSubject.onNext(newQuery);
            onSearchSubject.onNext(true);
        }
    }

    @Override
    public void doRefresh() {
        onRefreshSubject.onNext(true);
    }

    @Override
    public void saveRecentSearch(@NonNull Context context,
                                 @Nullable String query) {
        recentSearchesService.storeRecentSearch(context, query);
    }
}
