package com.sniper.music.base.di;

import android.content.Context;

import com.sniper.music.base.MusicApplication;
import com.sniper.music.base.di.modules.ApplicationModule;
import com.sniper.music.base.di.modules.NetworkModule;

import dagger.Component;

@ApplicationScope
@Component(modules = { ApplicationModule.class, NetworkModule.class })
public interface ApplicationComponent extends BaseComponent {

    Context context();

    void inject(MusicApplication application);

}
