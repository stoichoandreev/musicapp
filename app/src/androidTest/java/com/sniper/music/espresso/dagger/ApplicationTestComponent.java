package com.sniper.music.espresso.dagger;

import com.sniper.music.espresso.configurator.LastFMConfigurator;
import com.sniper.music.base.di.ApplicationComponent;
import com.sniper.music.base.di.ApplicationScope;
import com.sniper.music.base.di.modules.ApplicationModule;
import com.sniper.music.base.di.modules.NetworkModule;

import dagger.Component;

@ApplicationScope
@Component(modules = { ApplicationModule.class, NetworkModule.class })
public interface ApplicationTestComponent extends ApplicationComponent {

    void inject(LastFMConfigurator configurator);

}
