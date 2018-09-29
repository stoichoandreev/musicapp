package com.sniper.music.details.di;

import com.sniper.music.base.di.ApplicationComponent;
import com.sniper.music.base.di.BaseComponent;
import com.sniper.music.details.DetailsActivity;

import dagger.Component;

@DetailsScope
@Component(dependencies = ApplicationComponent.class, modules = DetailsModule.class)
public interface DetailsComponent extends BaseComponent {

    String KEY = DetailsComponent.class.getSimpleName();

    void inject(DetailsActivity activity);

}
