package com.sniper.music.base.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.sniper.music.base.di.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @NonNull
    private final Context appContext;

    public ApplicationModule(@NonNull Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @ApplicationScope
    public Context provideAppContext() {
        return this.appContext;
    }

}
