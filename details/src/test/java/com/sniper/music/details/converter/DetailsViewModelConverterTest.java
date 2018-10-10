package com.sniper.music.details.converter;

import com.sniper.music.api.lastfm.models.ArtistBio;
import com.sniper.music.api.lastfm.models.ArtistInfo;
import com.sniper.music.api.lastfm.models.ArtistInfoResponse;
import com.sniper.music.api.lastfm.models.ArtistStats;
import com.sniper.music.api.lastfm.models.ImageDataModel;
import com.sniper.music.api.lastfm.models.Tag;
import com.sniper.music.api.lastfm.models.Tags;
import com.sniper.music.details.models.DetailsViewModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DetailsViewModelConverterTest {

    private DetailsViewModelConverter tested = new DetailsViewModelConverter();

    @Test
    public void test_details_view_model_converter_converts_details_info_response_to_correct_object_without_tags_data() {
        //given
        final ArtistInfoResponse artistInfoResponse = getArtistInfoResponse(false);
        //when
        final DetailsViewModel detailsViewModel = tested.extractFromArtistInfoResponse(artistInfoResponse);
        //test
        assertThat(detailsViewModel.getMbID(), is("mbid11"));
        assertThat(detailsViewModel.getSummary(), is("Some summary text"));
        assertThat(detailsViewModel.getImageUrl(), is("http://adsadsa/adasd/medium"));
        assertThat(detailsViewModel.getName(), is("Sting"));
        assertThat(detailsViewModel.getUrl(), is("http://wertt"));
        assertThat(detailsViewModel.getListeners(), is("Artist Listeners : 123"));
        assertThat(detailsViewModel.getPlayCount(), is("Was Played : 345"));
    }

    @Test
    public void test_details_view_model_converter_converts_details_info_response_to_correct_object_with_tags_data() {
        //given
        final ArtistInfoResponse artistInfoResponse = getArtistInfoResponse(true);
        //when
        final DetailsViewModel detailsViewModel = tested.extractFromArtistInfoResponse(artistInfoResponse);
        //test
        assertThat(detailsViewModel.getMbID(), is("mbid11"));
        assertThat(detailsViewModel.getSummary(), is("Some summary text"));
        assertThat(detailsViewModel.getImageUrl(), is("http://adsadsa/adasd/medium"));
        assertThat(detailsViewModel.getName(), is("Sting"));
        assertThat(detailsViewModel.getUrl(), is("http://wertt"));
        assertThat(detailsViewModel.getListeners(), is("Artist Listeners : 123"));
        assertThat(detailsViewModel.getPlayCount(), is("Was Played : 345"));
        assertThat(detailsViewModel.getTags().length, is(3));
    }

    private ArtistInfoResponse getArtistInfoResponse(boolean withTags) {

        final ArtistInfoResponse response = new ArtistInfoResponse();
        final ArtistInfo artistInfo = new ArtistInfo();
        final List<ImageDataModel> images = new ArrayList<>();
        final ArtistStats artistStats = new ArtistStats();
        final ArtistBio artistBio = new ArtistBio();
        final Tags tags = new Tags();
        final ImageDataModel imageSmall = new ImageDataModel();
        final ImageDataModel imageMedium = new ImageDataModel();
        imageSmall.setSize("Small");
        imageSmall.setUrl("http://adsadsa/adasd/small");
        imageMedium.setSize("Medium");
        imageMedium.setUrl("http://adsadsa/adasd/medium");
        images.add(imageSmall);
        images.add(imageMedium);

        artistStats.setListeners("123");
        artistStats.setPlaycount("345");

        artistBio.setSummary("Some summary text");

        if (withTags) {
            List<Tag> tagList = new ArrayList<>(3);
            tagList.add(new Tag());
            tagList.add(new Tag());
            tagList.add(new Tag());
            tags.setTagList(tagList);
        }

        artistInfo.setImages(images);
        artistInfo.setMbid("mbid11");
        artistInfo.setName("Sting");
        artistInfo.setStats(artistStats);
        artistInfo.setBio(artistBio);
        artistInfo.setUrl("http://wertt");
        artistInfo.setTags(tags);

        response.setArtistInfo(artistInfo);

        return response;
    }

}