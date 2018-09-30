package com.sniper.music.base.services;

import android.support.annotation.NonNull;

import com.sniper.music.base.BuildConfig;
import com.sniper.music.base.intents.ScreenLink;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppLinksServiceTest {

    @NonNull
    private final AppLinksService tested = new AppLinksService(BuildConfig.APP_LINK_BASE_URL);

    @Test
    public void test_app_link_service_returns_home_screen_app_link() {
        //given
        final String expected = BuildConfig.APP_LINK_BASE_URL + ScreenLink.HOME;
        //when
        final String result = tested.generateScreenLink(ScreenLink.HOME);
        //test
        assertEquals(expected, result);
    }

    @Test
    public void test_app_link_service_returns_details_screen_app_link() {
        //given
        final String expected = BuildConfig.APP_LINK_BASE_URL + ScreenLink.DETAILS;
        //when
        final String result = tested.generateScreenLink(ScreenLink.DETAILS);
        //test
        assertEquals(expected, result);
    }
}