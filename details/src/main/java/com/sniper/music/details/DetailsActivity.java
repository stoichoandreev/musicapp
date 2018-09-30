package com.sniper.music.details;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sniper.music.base.di.ApplicationComponent;
import com.sniper.music.base.di.ComponentsManager;
import com.sniper.music.base.intents.IntentExtras;
import com.sniper.music.base.ui.BaseActivity;
import com.sniper.music.details.di.DaggerDetailsComponent;
import com.sniper.music.details.di.DetailsComponent;
import com.sniper.music.details.mvp.DetailsPresenter;

import javax.inject.Inject;

public class DetailsActivity extends BaseActivity<DetailsPresenter, DetailsComponent> implements DetailsPresenter.View {

    @Inject
    DetailsPresenter presenter;

    @Nullable
    private String mbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getScreenComponent().inject(this);
        if (getIntent().getExtras() != null) {
            mbId = getIntent().getExtras().getString(IntentExtras.EXTRA_MB_ID);
        }
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
//        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showDetails() {

    }
}
