package com.sniper.music.home.converter;

import android.support.annotation.NonNull;

import com.sniper.music.api.lastFm.dataModels.ArtistResponse;
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
        HomeAdapterViewModel viewModel = new HomeAdapterViewModel.Builder("dads")
                .imageUrl("http://i.imgur.com/DvpvklR.png")
                .url("https://www.google.com")
                .itemType(HomeAdapterType.ARTIST)
                .name("Filip")
                .build();

        convertedList.add(viewModel);

        return convertedList;
    }

}
