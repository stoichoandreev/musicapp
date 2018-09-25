package com.sniper.music.home.di;

import com.sniper.music.base.di.ApplicationComponent;
import com.sniper.music.home.HomeActivity;
import com.sniper.music.base.di.BaseComponent;

import dagger.Component;

@HomeScope
@Component(dependencies = ApplicationComponent.class, modules = HomeModule.class)
public interface HomeComponent extends BaseComponent {

    String KEY = HomeComponent.class.getSimpleName();

    void inject(HomeActivity activity);

}
