package com.sniper.music.details.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sniper.music.details.models.DetailsViewModel;
import com.sniper.music.details.services.DetailsInfoService;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class DefaultDetailsPresenter implements DetailsPresenter<DetailsPresenter.View> {

    private BehaviorSubject<DetailsViewModel> detailsViewModelSubject =  BehaviorSubject.create();
    private BehaviorSubject<String> paramSubject = BehaviorSubject.create();
    private PublishSubject<Throwable> onErrorSubject = PublishSubject.create();
    private PublishSubject<Boolean> showHideLoadingSubject = PublishSubject.create();

    @Nullable
    private DetailsPresenter.View view;
    @NonNull
    private DetailsInfoService detailsInfoService;
    @NonNull
    private CompositeDisposable disposables;

    public DefaultDetailsPresenter(@Nullable DetailsPresenter.View view,
                                   @NonNull DetailsInfoService detailsInfoService,
                                   @NonNull CompositeDisposable compositeDisposable) {
        this.view = view;
        this.detailsInfoService = detailsInfoService;
        disposables = compositeDisposable;
        setup();
    }

    private void setup() {

        disposables.add(paramSubject.flatMap(param -> detailsInfoService.getArtistInformation(param))
                .subscribe(viewModel -> detailsViewModelSubject.onNext(viewModel), error -> onErrorSubject.onNext(error)));

        disposables.add(detailsViewModelSubject.subscribe(viewModel -> {
            if (view != null) {
                showHideLoadingSubject.onNext(false);
                view.showDetails(viewModel);
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
    }

    @Override
    public void attachView(@NonNull DetailsPresenter.View detailsView, boolean wasSavedInstanceState) {
        view = detailsView;
        if (wasSavedInstanceState && detailsViewModelSubject.getValue() != null) {
            detailsViewModelSubject.onNext(detailsViewModelSubject.getValue());
        }
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
    public void fetchDetails(@Nullable String query) {
        if (query != null && detailsViewModelSubject.getValue() == null) {
            paramSubject.onNext(query);
        }
    }
}
