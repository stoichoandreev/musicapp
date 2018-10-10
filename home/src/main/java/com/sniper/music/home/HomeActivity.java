package com.sniper.music.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.sniper.music.base.adapter.OnItemClickListener;
import com.sniper.music.base.di.ApplicationComponent;
import com.sniper.music.base.di.ComponentsManager;
import com.sniper.music.base.intents.IntentExtras;
import com.sniper.music.base.intents.ScreenLink;
import com.sniper.music.base.services.AppLinksService;
import com.sniper.music.base.ui.BaseActivity;
import com.sniper.music.home.adapter.HomeAdapter;
import com.sniper.music.home.di.DaggerHomeComponent;
import com.sniper.music.home.di.HomeComponent;
import com.sniper.music.home.models.HomeAdapterViewModel;
import com.sniper.music.home.mvp.HomePresenter;

import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity<HomePresenter, HomeComponent> implements HomePresenter.View, OnItemClickListener<HomeAdapterViewModel> {

    @Inject
    HomePresenter presenter;
    @Inject
    AppLinksService appLinksService;
    @Nullable
    private SearchView searchView;
    @NonNull
    private ProgressBar progressBar;
    @NonNull
    private RecyclerView recyclerView;

    @NonNull
    private final HomeAdapter homeAdapter = new HomeAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getScreenComponent().inject(this);
        initViews();
        initToolbar();
        initScreenAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this, wasSavedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);

        final MenuItem searchAction = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchAction.getActionView();
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    presenter.saveRecentSearch(HomeActivity.this, query);
                    if (searchView != null) {
                        searchView.clearFocus();
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    presenter.fetchSearchResults(newText);
                    return false;
                }
            });

            searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
                @Override
                public boolean onSuggestionSelect(int position) {
                    return false;
                }

                @Override
                public boolean onSuggestionClick(int position) {
                    final CursorAdapter selectedView = searchView.getSuggestionsAdapter();
                    final Cursor cursor = (Cursor) selectedView.getItem(position);
                    final int index = cursor.getColumnIndexOrThrow(SearchManager.SUGGEST_COLUMN_TEXT_1);
                    if (searchView != null) {
                        searchView.setQuery(cursor.getString(index), true);
                    }
                    return false;
                }
            });
        }

        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null && searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }

        return true;
    }

    @Override
    protected void onNewIntent(@NonNull Intent intent) {
        //Usually these are queries from the voice search.
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            final String query = intent.getStringExtra(SearchManager.QUERY);
            presenter.saveRecentSearch(this, query);
            if (searchView != null) {
                searchView.setQuery(query, true);
            }
            presenter.fetchSearchResults(query);
        }
    }

    @Override
    protected HomeComponent getScreenComponent() {
        final ComponentsManager componentsManager = ComponentsManager.get();
        HomeComponent component = componentsManager.getBaseComponent(getComponentKey());
        if (component == null) {
            ApplicationComponent appComponent = componentsManager.getAppComponent();
            component = DaggerHomeComponent.builder()
                    .applicationComponent(appComponent)
                    .build();
            componentsManager.putBaseComponent(getComponentKey(), component);
        }
        return component;
    }

    @Override
    protected String getComponentKey() {
        return HomeComponent.KEY;
    }

    @Override
    protected HomePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showError(String errorMessage) {
        displayError(errorMessage);
    }

    @Override
    public void showLoading(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showSearchResults(@NonNull List<HomeAdapterViewModel> newItems) {
        homeAdapter.setNewItems(newItems);
    }

    @Override
    public void onItemClick(HomeAdapterViewModel selectedItem) {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(appLinksService.generateScreenLink(ScreenLink.DETAILS)));
        intent.putExtra(IntentExtras.EXTRA_MB_ID, selectedItem.getMbID());
        intent.putExtra(IntentExtras.EXTRA_NAME, selectedItem.getName());
        startActivity(intent);
    }

    private void initViews() {
        progressBar = findViewById(R.id.progress_indicator);
        recyclerView = findViewById(R.id.search_results_recycler_view);
    }

    private void initToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.home_screen_title);
        }
    }

    private void initScreenAdapter() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(homeAdapter);
    }
}
