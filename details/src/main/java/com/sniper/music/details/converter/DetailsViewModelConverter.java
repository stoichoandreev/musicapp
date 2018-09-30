package com.sniper.music.details.converter;

import android.support.annotation.NonNull;

import com.sniper.music.api.lastFm.dataModels.ArtistInfoResponse;
import com.sniper.music.details.models.DetailsViewModel;

import javax.inject.Inject;


public class DetailsViewModelConverter {

    @Inject
    public DetailsViewModelConverter() {
        //used by Dagger
    }

    public DetailsViewModel extractFromArtistInfoResponse(@NonNull ArtistInfoResponse response) {
        final DetailsViewModel.Builder detailsViewModelBuilder = new DetailsViewModel.Builder(response.artistInfo.mbid);
        //Todo

        return detailsViewModelBuilder.build();
    }

}
