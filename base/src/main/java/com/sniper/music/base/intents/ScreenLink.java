package com.sniper.music.base.intents;

import android.support.annotation.StringDef;

import com.sniper.music.base.BuildConfig;

public interface ScreenLink {

    String HOME = BuildConfig.APP_LINK_HOME;
    String DETAILS = BuildConfig.APP_LINK_DETAILS;

    @StringDef({ HOME, DETAILS })
    @interface Values {
        //unused
    }
}
