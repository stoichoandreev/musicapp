package com.sniper.music.espresso.detailstests.robot;

import com.sniper.music.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class DetailsScreenRobot {

    public DetailsScreenRobot areAllViewsVisible() {
        onView(withId(R.id.details_image_view)).check(matches(isDisplayed()));
        onView(withId(R.id.details_title_view)).check(matches(isDisplayed()));
        onView(withId(R.id.details_listeners_view)).check(matches(isDisplayed()));
        onView(withId(R.id.details_play_count_view)).check(matches(isDisplayed()));
        onView(withId(R.id.details_url_view)).check(matches(isDisplayed()));
        onView(withId(R.id.details_description_view)).check(matches(isDisplayed()));
        return this;
    }

    public DetailsScreenRobot areAllViewsWithExpectedInformation() {
        onView(withId(R.id.details_image_view)).check(matches(withContentDescription("https://lastfm-img2.akamaized.net/i/u/174s/879a88760860cc472d826ca4e7fc5ad6.png")));
        onView(withId(R.id.details_title_view)).check(matches(withText("Cher")));
        onView(withId(R.id.details_listeners_view)).check(matches(withText("Artist Listeners : 1076938")));
        onView(withId(R.id.details_play_count_view)).check(matches(withText("Was Played : 14952381")));
        onView(withId(R.id.details_url_view)).check(matches(withText("https://www.last.fm/music/Cher")));
        onView(withId(R.id.details_description_view)).check(matches(withText("Cher (born Cherilyn Sarkisian; May 20, 1946) is an Oscar- and Grammy-winning American singer and actress. A major figure for over five decades in the world of popular culture, she is often referred to as the Goddess of Pop for having first brought the sense of female autonomy and self-actualization into the entertainment industry.She is known for her distinctive contralto and for having worked extensively across media, as well as for continuously reinventing both her music and image, the latter of which has been known to induce controversy.")));
        return this;
    }

}
