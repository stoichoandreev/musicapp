package com.sniper.music.details;

import android.os.Bundle;

import com.sniper.music.base.di.ApplicationComponent;
import com.sniper.music.base.di.ComponentsManager;
import com.sniper.music.base.ui.BaseActivity;
import com.sniper.music.details.di.DaggerDetailsComponent;
import com.sniper.music.details.di.DetailsComponent;
import com.sniper.music.details.di.DetailsModule;
import com.sniper.music.details.mvp.DetailsPresenter;

import javax.inject.Inject;

public class DetailsActivity extends BaseActivity<DetailsPresenter, DetailsComponent> implements DetailsPresenter.View {

    @Inject
    DetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getScreenComponent().inject(this);
    }

    @Override
    protected DetailsComponent getScreenComponent() {
        final ComponentsManager componentsManager = ComponentsManager.get();
        DetailsComponent component = componentsManager.getBaseComponent(getComponentKey());
        if (component == null) {
            ApplicationComponent appComponent = componentsManager.getAppComponent();
            component = DaggerDetailsComponent.builder()
                    .applicationComponent(appComponent)
                    .detailsModule(new DetailsModule(this))
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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(Throwable error) {

    }

    @Override
    public void showDetails() {

    }
}
