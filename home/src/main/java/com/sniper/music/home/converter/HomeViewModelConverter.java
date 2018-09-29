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
        HomeAdapterViewModel model1 = new HomeAdapterViewModel.Builder("dads")
                .imageUrl("http://i.imgur.com/DvpvklR.png")
                .url("https://www.google.com")
                .itemType(HomeAdapterType.ARTIST)
                .name("Filip")
                .build();

        HomeAdapterViewModel model2 = new HomeAdapterViewModel.Builder("aasd")
                .imageUrl("http://getdrawings.com/simple-house-clipart#simple-house-clipart-2.jpg")
                .url("https://www.yahoo.co.uk")
                .itemType(HomeAdapterType.ARTIST)
                .name("Irmena")
                .build();

        HomeAdapterViewModel model3 = new HomeAdapterViewModel.Builder("alkfsfmkfasd")
                .imageUrl("http://i.imgur.com/DvpvklR.png")
                .url("https://www.yahoo.co.uk")
                .itemType(HomeAdapterType.ARTIST)
                .name("Bubiiii")
                .build();

        HomeAdapterViewModel model4 = new HomeAdapterViewModel.Builder("alkfsfmkfioirweriiasd")
                .imageUrl("http://getdrawings.com/simple-house-clipart#simple-house-clipart-2.jpg")
                .url("https://www.yahoo.co.uk")
                .itemType(HomeAdapterType.ARTIST)
                .name("Tiago Silva")
                .build();

        convertedList.add(model1);
        convertedList.add(model2);
        convertedList.add(model3);
        convertedList.add(model4);

        return convertedList;
    }

}
