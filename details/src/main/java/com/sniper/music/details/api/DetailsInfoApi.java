package com.sniper.music.details.api;

import com.sniper.music.api.EndPoints;
import com.sniper.music.api.lastfm.models.ArtistInfoResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DetailsInfoApi {

    @GET(EndPoints.ArtistInfo)
    Observable<ArtistInfoResponse> artistInfo(@Query("artist") String artist);

}
