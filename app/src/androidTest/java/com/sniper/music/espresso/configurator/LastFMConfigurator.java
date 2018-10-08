package com.sniper.music.espresso.configurator;

import com.sniper.music.espresso.retrofit.MockRetrofitClient;
import com.sniper.music.espresso.retrofit.MockRetrofitResponse;
import com.sniper.music.api.RetrofitClient;
import com.sniper.music.api.lastfm.LastFMClient;
import com.sniper.music.espresso.dagger.ApplicationTestComponent;
import com.sniper.music.base.di.ComponentsManager;

import javax.inject.Inject;
import javax.inject.Named;

public class LastFMConfigurator {

    @Inject
    @Named(LastFMClient.NAME)
    RetrofitClient lastFMClient;

    public void setup() {
        ((ApplicationTestComponent) ComponentsManager.get()
                .getAppComponent())
                .inject(this);
        if (lastFMClient instanceof MockRetrofitClient) {
            ((MockRetrofitClient) lastFMClient).setup();
        }
    }

    public void addResponse(MockRetrofitResponse response) {
        ((MockRetrofitClient) lastFMClient).addResponse(response);
    }

    public void clearResponses() {
        ((MockRetrofitClient) lastFMClient).clearResponseMap();
    }

}
