package com.sniper.music.rules;

import android.support.test.InstrumentationRegistry;

import com.sniper.music.TestMusicApplication;
import com.sniper.music.dagger.ApplicationTestComponent;
import com.sniper.music.dagger.modules.NetworkTestModule;
import com.sniper.music.base.di.ComponentsManager;
import com.sniper.music.base.di.modules.ApplicationModule;

import org.junit.rules.ExternalResource;

import com.sniper.music.dagger.DaggerApplicationTestComponent;

public class ResetEnvironmentRule extends ExternalResource {

    @Override
    protected void before() throws Throwable {
        ApplicationTestComponent applicationComponent = DaggerApplicationTestComponent.builder()
                .applicationModule(new ApplicationModule(InstrumentationRegistry.getTargetContext()))
                .networkModule(NetworkTestModule.getInstance())
                .build();
        ComponentsManager.get().setAppComponent(applicationComponent);
        TestMusicApplication application = (TestMusicApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        applicationComponent.inject(application);
    }
}
