package com.sniper.music.home.converter;

import android.support.annotation.NonNull;

import com.sniper.music.api.lastfm.models.Artist;
import com.sniper.music.api.lastfm.models.ArtistResponse;
import com.sniper.music.home.adapter.HomeAdapterType;
import com.sniper.music.home.models.HomeAdapterViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class HomeViewModelConverter {

    @Inject
    public HomeViewModelConverter() {
        //used by Dagger
    }

    public List<HomeAdapterViewModel> extractListFromArtistResponse(@NonNull ArtistResponse response) {
        final List<HomeAdapterViewModel> convertedList = new ArrayList<>();
        if (response.result != null && response.result.artistMatches != null && response.result.artistMatches.artist != null) {
            for (Artist artist : response.result.artistMatches.artist) {
                final HomeAdapterViewModel viewModel = new HomeAdapterViewModel.Builder(artist.mbid)
                        .imageUrl(artist.getImages().size() > 1 ? artist.getImages().get(1).url : artist.getImages().get(0).url)
                        .url(artist.getUrl())
                        .itemType(HomeAdapterType.ARTIST)
                        .name(artist.getName())
                        .additionalInformation("Artist Listeners : " + artist.getListeners())
                        .build();

                convertedList.add(viewModel);
            }
        }
        return convertedList;
    }

}
