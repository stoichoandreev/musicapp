package com.sniper.music.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import com.sniper.music.base.di.ApplicationComponent;
import com.sniper.music.base.di.ComponentsManager;
import com.sniper.music.base.ui.BaseActivity;
import com.sniper.music.home.di.DaggerHomeComponent;
import com.sniper.music.home.di.HomeComponent;
import com.sniper.music.home.di.HomeModule;
import com.sniper.music.home.mvp.HomePresenter;
import com.sniper.music.home.search.KeywordSuggestionProvider;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity<HomePresenter, HomeComponent> implements HomePresenter.View {

    @Inject
    HomePresenter presenter;
    @Nullable
    private SearchView searchView;
    @NonNull
    private ProgressBar progressBar;
    @NonNull
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        getScreenComponent().inject(this);
        setupToolBar();
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
                    storeToRecentSearches(query);
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
            storeToRecentSearches(query);
            if (searchView != null) {
                searchView.setQuery(query, true);
            }
            presenter.fetchSearchResults(query);
        }
    }

    @Override
    public void showError(Throwable error) {

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected HomeComponent getScreenComponent() {
        final ComponentsManager componentsManager = ComponentsManager.get();
        HomeComponent component = componentsManager.getBaseComponent(getComponentKey());
        if (component == null) {
            ApplicationComponent appComponent = componentsManager.getAppComponent();
            component = DaggerHomeComponent.builder()
                    .applicationComponent(appComponent)
                    .homeModule(new HomeModule(this))
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

    private void initViews() {
        progressBar = findViewById(R.id.progress_indicator);
        recyclerView = findViewById(R.id.search_results_recycler_view);
    }

    private void setupToolBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.home_screen_title);
        }
    }

    private void storeToRecentSearches(@Nullable String query) {
        if (!TextUtils.isEmpty(query) && query.length() > 2) {
            final SearchRecentSuggestions suggestions = new SearchRecentSuggestions(HomeActivity.this,
                    KeywordSuggestionProvider.AUTHORITY,
                    KeywordSuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);
        }
    }
}
