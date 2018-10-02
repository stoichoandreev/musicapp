package com.sniper.music.details.converter;

import android.support.annotation.NonNull;

import com.sniper.music.api.lastfm.models.ArtistInfoResponse;
import com.sniper.music.api.lastfm.models.Tag;
import com.sniper.music.details.models.DetailsViewModel;

import java.util.List;

import javax.inject.Inject;


public class DetailsViewModelConverter {

    @Inject
    public DetailsViewModelConverter() {
        //used by Dagger
    }

    public DetailsViewModel extractFromArtistInfoResponse(@NonNull ArtistInfoResponse response) {
        final DetailsViewModel.Builder detailsViewModelBuilder = new DetailsViewModel.Builder(response.getArtistInfo().getMbid());
        detailsViewModelBuilder.name(response.getArtistInfo().getName());
        detailsViewModelBuilder.url(response.getArtistInfo().getUrl());
        detailsViewModelBuilder.imageUrl(response.getArtistInfo().getImages().size() > 2 ? response.getArtistInfo().getImages().get(2).getUrl() : response.getArtistInfo().getImages().get(2).getUrl());
        detailsViewModelBuilder.listeners("Artist Listeners : " + response.getArtistInfo().getStats().getListeners());
        detailsViewModelBuilder.playCount("Was Played : " + response.getArtistInfo().getStats().getPlaycount());
        if (response.getArtistInfo().getTags().getTagList() != null && response.getArtistInfo().getTags().getTagList().size() > 0) {
            final List<Tag> allTags = response.getArtistInfo().getTags().getTagList();
            final String[] tagList = new String[response.getArtistInfo().getTags().getTagList().size()];
            for (int i = 0; i < tagList.length; i++) {
                tagList[i] = allTags.get(i).getName();
            }
            detailsViewModelBuilder.tags(tagList);
        }
        detailsViewModelBuilder.summary(response.getArtistInfo().getBio().getSummary());

        return detailsViewModelBuilder.build();
    }

}
