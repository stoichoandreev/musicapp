package com.sniper.music.base;

import android.app.Application;

import com.sniper.music.base.di.ApplicationComponent;
import com.sniper.music.base.di.BaseComponent;
import com.sniper.music.base.di.ComponentsManager;
import com.sniper.music.base.di.DaggerApplicationComponent;
import com.sniper.music.base.di.modules.ApplicationModule;

public class MusicApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initComponents();
    }

    private void initComponents() {
        final ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        ComponentsManager.get().setAppComponent(applicationComponent);
        applicationComponent.inject(this);
    }

    public void setComponent(BaseComponent component) {
        ComponentsManager.get().setAppComponent(component);
    }
}
