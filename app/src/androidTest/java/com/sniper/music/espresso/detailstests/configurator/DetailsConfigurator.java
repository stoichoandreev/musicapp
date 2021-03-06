package com.sniper.music.espresso.detailstests.configurator;

import com.sniper.music.espresso.retrofit.MockRetrofitClient;
import com.sniper.music.espresso.retrofit.MockRetrofitResponse;
import com.sniper.music.espresso.configurator.LastFMConfigurator;

public class DetailsConfigurator {

    private LastFMConfigurator lastFMConfigurator = new LastFMConfigurator();

    public void setup() {
        lastFMConfigurator.setup();
    }

    public void tearDown() {
        lastFMConfigurator.clearResponses();
    }

    public void setDetailsInfo() {
        MockRetrofitResponse mockRetrofitResponse = new MockRetrofitResponse.Builder()
                .responseCode(200)
                .method(MockRetrofitClient.GET)
                .path("?method=artist.getinfo&artist=Cher&api_key=b9304bb9f3e9c5397188fbb0381ade7d&format=json")
                .responseFilePath("responses/artist_details_info.json")
                .build();
        lastFMConfigurator.addResponse(mockRetrofitResponse);
    }
}
