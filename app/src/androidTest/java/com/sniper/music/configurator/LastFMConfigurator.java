package com.sniper.music.configurator;

import com.sniper.music.MockRetrofitClient;
import com.sniper.music.MockRetrofitResponse;
import com.sniper.music.api.RetrofitClient;
import com.sniper.music.api.lastFm.LastFMClient;
import com.sniper.music.dagger.ApplicationTestComponent;
import com.sniper.music.base.di.ComponentsManager;

import javax.inject.Inject;
import javax.inject.Named;

public class LastFMConfigurator {
    @Inject
    @Named(LastFMClient.NAME)
    RetrofitClient lastFMClient;

    public void setup() {
        ((ApplicationTestComponent) ComponentsManager.get().getAppComponent()).inject(this);
        if (lastFMClient instanceof MockRetrofitClient) {
            ((MockRetrofitClient) lastFMClient).setup();
        }
    }

    public void teardown() {
        if (lastFMClient instanceof MockRetrofitClient) {
            ((MockRetrofitClient) lastFMClient).clearResponseMap();
        }
    }

    public void addResponse(MockRetrofitResponse response) {
        ((MockRetrofitClient) lastFMClient).addResponse(response);
    }

    public void clearResponses() {
        ((MockRetrofitClient) lastFMClient).clearResponseMap();
    }

}
