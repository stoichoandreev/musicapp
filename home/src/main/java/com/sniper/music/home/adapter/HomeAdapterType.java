package com.sniper.music.home.adapter;

import android.support.annotation.IntDef;

public interface HomeAdapterType {
    int ARTIST = 1;
    int ALBUM = 2;
    int TRACK = 3;
    int EMPTY = 99;

    @IntDef({ARTIST,
            ALBUM,
            TRACK,
            EMPTY
    })
    @interface Values {
        //unused
    }
}
