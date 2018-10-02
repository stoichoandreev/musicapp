package com.sniper.music.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sniper.music.base.di.ApplicationComponent;
import com.sniper.music.base.di.ComponentsManager;
import com.sniper.music.base.intents.IntentExtras;
import com.sniper.music.base.ui.BaseActivity;
import com.sniper.music.base.wrappers.ImageLoaderWrapper;
import com.sniper.music.details.di.DaggerDetailsComponent;
import com.sniper.music.details.di.DetailsComponent;
import com.sniper.music.details.models.DetailsViewModel;
import com.sniper.music.details.mvp.DetailsPresenter;

import java.util.Arrays;

import javax.inject.Inject;

public class DetailsActivity extends BaseActivity<DetailsPresenter, DetailsComponent> implements DetailsPresenter.View {

    @Inject
    DetailsPresenter presenter;

    @NonNull
    private ProgressBar progressBar;
    @NonNull
    private ImageView imageView;
    @NonNull
    private TextView titleView;
    @NonNull
    private TextView urlView;
    @NonNull
    private TextView listenersView;
    @NonNull
    private TextView playCountView;
    @NonNull
    private TextView tagsView;
    @NonNull
    private TextView descriptionView;

    @Nullable
    private String mbId;
    @Nullable
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getScreenComponent().inject(this);
        if (getIntent().getExtras() != null) {
            mbId = getIntent().getExtras().getString(IntentExtras.EXTRA_MB_ID);
            name = getIntent().getExtras().getString(IntentExtras.EXTRA_NAME);
        }
        initViews();
        initToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this, wasSavedInstanceState);
        presenter.fetchDetails(name);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected DetailsComponent getScreenComponent() {
        final ComponentsManager componentsManager = ComponentsManager.get();
        DetailsComponent component = componentsManager.getBaseComponent(getComponentKey());
        if (component == null) {
            final ApplicationComponent appComponent = componentsManager.getAppComponent();
            component = DaggerDetailsComponent.builder()
                    .applicationComponent(appComponent)
                    .build();
            componentsManager.putBaseComponent(getComponentKey(), component);
        }
        return component;
    }

    @Override
    protected String getComponentKey() {
        return DetailsComponent.KEY;
    }

    @Override
    protected DetailsPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showError(String errorMessage) {
        displayErrors(errorMessage);
    }

    @Override
    public void showLoading(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showDetails(@NonNull DetailsViewModel detailsInfo) {
        imageView.setContentDescription(detailsInfo.getImageUrl());
        ImageLoaderWrapper.loadImageFromUrl(imageView, detailsInfo.getImageUrl());
        titleView.setText(detailsInfo.getName());
        urlView.setText(Html.fromHtml(detailsInfo.getUrl()));
        urlView.setMovementMethod(LinkMovementMethod.getInstance());
        listenersView.setText(detailsInfo.getListeners());
        playCountView.setText(detailsInfo.getPlayCount());
        tagsView.setText(Arrays.toString(detailsInfo.getTags()));
        descriptionView.setText(Html.fromHtml(detailsInfo.getSummary()));
        descriptionView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initViews() {
        progressBar = findViewById(R.id.progress_indicator);
        imageView = findViewById(R.id.details_image_view);
        titleView = findViewById(R.id.details_title_view);
        urlView = findViewById(R.id.details_url_view);
        listenersView = findViewById(R.id.details_listeners_view);
        playCountView = findViewById(R.id.details_play_count_view);
        tagsView = findViewById(R.id.details_tags_view);
        descriptionView = findViewById(R.id.details_description_view);
    }

    private void initToolbar() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(String.format(getString(R.string.details_screen_title), name));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
