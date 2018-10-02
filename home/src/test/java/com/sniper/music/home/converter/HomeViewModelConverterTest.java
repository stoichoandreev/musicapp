package com.sniper.music.home.converter;

import com.sniper.music.api.lastfm.models.Artist;
import com.sniper.music.api.lastfm.models.ArtistMatches;
import com.sniper.music.api.lastfm.models.ArtistResponse;
import com.sniper.music.api.lastfm.models.ArtistResults;
import com.sniper.music.api.lastfm.models.ImageDataModel;
import com.sniper.music.home.adapter.HomeAdapterType;
import com.sniper.music.home.models.HomeAdapterViewModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HomeViewModelConverterTest {

    private HomeViewModelConverter tested = new HomeViewModelConverter();

    @Test
    public void test_home_view_model_converter_returns_empty_list() {
        //given
        final ArtistResponse artistResponse = new ArtistResponse();
        //when
        final List<HomeAdapterViewModel> listResult = tested.extractListFromArtistResponse(artistResponse);
        //test
        assertThat(listResult.size(), is(0));
    }

    @Test
    public void test_home_view_model_converter_converts_artist_response_to_correct_list() {
        //given
        final ArtistResponse artistResponse = getArtistResponse();
        //when
        final List<HomeAdapterViewModel> listResult = tested.extractListFromArtistResponse(artistResponse);
        //test
        assertThat(listResult.size(), is(2));
        assertThat(listResult.get(0).getMbID(), is("mbid11"));
        assertThat(listResult.get(0).getAdditionalInformation(), is("Artist Listeners : 100"));
        assertThat(listResult.get(0).getImageUrl(), is("http://adsadsa/adasd/medium"));
        assertThat(listResult.get(0).getItemType(), is(HomeAdapterType.ARTIST));
        assertThat(listResult.get(0).getName(), is("Artist1"));
        assertThat(listResult.get(0).getUrl(), is("some_url1"));

        assertThat(listResult.get(1).getMbID(), is("mbid22"));
        assertThat(listResult.get(1).getAdditionalInformation(), is("Artist Listeners : 200"));
        assertThat(listResult.get(1).getImageUrl(), is("http://adsadsa/adasd/medium"));
        assertThat(listResult.get(1).getItemType(), is(HomeAdapterType.ARTIST));
        assertThat(listResult.get(1).getName(), is("Artist2"));
        assertThat(listResult.get(1).getUrl(), is("some_url2"));
    }

    private ArtistResponse getArtistResponse() {

        final ArtistResponse response = new ArtistResponse();
        final ArtistResults artistResults = new ArtistResults();
        final ArtistMatches artistMatches = new ArtistMatches();
        final List<Artist> artistList = new ArrayList<>();
        final List<ImageDataModel> images = new ArrayList<>();
        final ImageDataModel imageSmall = new ImageDataModel();
        final ImageDataModel imageMedium = new ImageDataModel();
        imageSmall.setSize("Small");
        imageSmall.setUrl("http://adsadsa/adasd/small");
        imageMedium.setSize("Medium");
        imageMedium.setUrl("http://adsadsa/adasd/medium");
        images.add(imageSmall);
        images.add(imageMedium);

        final Artist artist1 = new Artist();
        final Artist artist2 = new Artist();
        artist1.setImages(images);
        artist1.setMbid("mbid11");
        artist1.setListeners("100");
        artist1.setName("Artist1");
        artist1.setUrl("some_url1");

        artist2.setImages(images);
        artist2.setMbid("mbid22");
        artist2.setListeners("200");
        artist2.setName("Artist2");
        artist2.setUrl("some_url2");

        artistList.add(artist1);
        artistList.add(artist2);

        artistMatches.setArtist(artistList);
        artistResults.setArtistMatches(artistMatches);
        artistResults.setStartIndex("0");
        artistResults.setTotalResults("1000");
        response.setResult(artistResults);

        return response;
    }

}