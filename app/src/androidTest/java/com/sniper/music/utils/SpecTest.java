package com.sniper.music.utils;

import android.support.test.espresso.Espresso;

import static android.os.SystemClock.sleep;

public class SpecTest {

    public void closeSoftKeyboard() {
        Espresso.closeSoftKeyboard();
        sleep(100);
    }

    public void pressBack() {
        Espresso.pressBack();
        sleep(1000);
    }

    public RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

}
