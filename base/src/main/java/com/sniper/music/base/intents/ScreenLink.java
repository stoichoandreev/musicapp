package com.sniper.music.base.intents;

import android.support.annotation.StringDef;

public interface ScreenLink {

    String HOME = "/home";
    String DETAILS = "/details";

    @StringDef({ HOME, DETAILS })
    @interface Values {
        //unused
    }
}
