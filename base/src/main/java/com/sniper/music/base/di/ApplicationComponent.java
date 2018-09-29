package com.sniper.music.base.di;

import android.content.Context;

import com.sniper.music.api.RetrofitClient;
import com.sniper.music.api.lastFm.LastFMClient;
import com.sniper.music.base.MusicApplication;
import com.sniper.music.base.di.modules.ApplicationModule;
import com.sniper.music.base.di.modules.NetworkModule;

import javax.inject.Named;

import dagger.Component;
import io.reactivex.Scheduler;

@ApplicationScope
@Component(modules = { ApplicationModule.class, NetworkModule.class })
public interface ApplicationComponent extends BaseComponent {

    Context context();

    @Named(LastFMClient.NAME)
    RetrofitClient lastFMClient();

    @Named(NetworkModule.MAIN_THREAD)
    Scheduler mainScheduler();

    @Named(NetworkModule.BACKGROUND_THREAD)
    Scheduler backgroundScheduler();

    void inject(MusicApplication application);

}
