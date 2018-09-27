package com.sniper.music.home.api;

import com.sniper.music.api.EndPoints;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeSearchApi {

    @GET(EndPoints.ArtistSearch)
    Observable<String> artistSearch(@Query("artist") String artist);

    @GET(EndPoints.AlbumSearch)
    Observable<String> albumSearch(@Query("album") String album);

    @GET(EndPoints.TrackSearch)
    Observable<String> trackSearch(@Query("track") String track);

}
