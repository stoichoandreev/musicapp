package com.sniper.music.home.api;

import com.sniper.music.api.EndPoints;
import com.sniper.music.api.lastFm.dataModels.AlbumResponse;
import com.sniper.music.api.lastFm.dataModels.ArtistResponse;
import com.sniper.music.api.lastFm.dataModels.TrackResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeSearchApi {

    @GET(EndPoints.ArtistSearch)
    Observable<ArtistResponse> artistSearch(@Query("artist") String artist);

    @GET(EndPoints.AlbumSearch)
    Observable<AlbumResponse> albumSearch(@Query("album") String album);

    @GET(EndPoints.TrackSearch)
    Observable<TrackResponse> trackSearch(@Query("track") String track);

}
