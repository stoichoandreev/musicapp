package com.sniper.music.base.services;

import android.support.annotation.NonNull;

import com.sniper.music.base.intents.ScreenLink;

public class AppLinksService {

    @NonNull
    private final String baseAppLinkUrl;

    public AppLinksService(@NonNull String appUrl) {
        baseAppLinkUrl = appUrl;
    }

    public String generateScreenLink(@NonNull @ScreenLink.Values String screenLink) {
        return baseAppLinkUrl + screenLink;
    }
}
