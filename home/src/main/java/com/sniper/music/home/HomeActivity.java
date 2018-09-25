package com.sniper.music.home;

import android.os.Bundle;

import com.sniper.music.base.di.ApplicationComponent;
import com.sniper.music.base.di.ComponentsManager;
import com.sniper.music.base.ui.BaseActivity;
import com.sniper.music.home.di.DaggerHomeComponent;
import com.sniper.music.home.di.HomeComponent;
import com.sniper.music.home.di.HomeModule;
import com.sniper.music.home.mvp.HomePresenter;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity<HomePresenter, HomeComponent> implements HomePresenter.View {

    @Inject
    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getScreenComponent().inject(this);
    }

    @Override
    public void showError(Throwable error) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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
}
