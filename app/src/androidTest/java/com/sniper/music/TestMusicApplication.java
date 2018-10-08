package com.sniper.music;

import android.support.test.InstrumentationRegistry;

import com.sniper.music.espresso.dagger.ApplicationTestComponent;
import com.sniper.music.espresso.dagger.DaggerApplicationTestComponent;
import com.sniper.music.espresso.dagger.modules.NetworkTestModule;

import com.sniper.music.base.MusicApplication;
import com.sniper.music.base.di.ComponentsManager;
import com.sniper.music.base.di.modules.ApplicationModule;

public class TestMusicApplication extends MusicApplication {

    @Override
    protected void initComponents() {
        final ApplicationTestComponent applicationComponent = DaggerApplicationTestComponent.builder()
                .applicationModule(new ApplicationModule(InstrumentationRegistry.getTargetContext()))
                .networkModule(NetworkTestModule.getInstance())
                .build();
        ComponentsManager.get().setAppComponent(applicationComponent);
        applicationComponent.inject(this);
    }
}
