package com.sniper.music.details.robot;

import com.sniper.music.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class DetailsScreenRobot {

    public DetailsScreenRobot areAllViewsVisible() {
//        String modalTitle = InstrumentationRegistry.getInstrumentation().getTargetContext().getString(R.string.gdpr_ad_consent_dialog_title);
//        String modalMessage = Html.fromHtml(InstrumentationRegistry.getInstrumentation().getTargetContext().getString(R.string.gdpr_ad_consent_dialog_message)).toString();

//        onView(withId(R.id.details_image_view)).check(matches(withText(modalButtonText)));
        onView(withId(R.id.details_image_view)).check(matches(isDisplayed()));
        onView(withId(R.id.details_title_view)).check(matches(isDisplayed()));
        onView(withId(R.id.details_listeners_view)).check(matches(isDisplayed()));
        onView(withId(R.id.details_play_count_view)).check(matches(isDisplayed()));
        onView(withId(R.id.details_url_view)).check(matches(isDisplayed()));
        onView(withId(R.id.details_description_view)).check(matches(isDisplayed()));
        return this;
    }

    public DetailsScreenRobot areAllTextViewsWithExpectedText() {
        onView(withId(R.id.details_title_view)).check(matches(withText("Cher")));
//        onView(withId(R.id.details_listeners_view)).check(matches(withText(modalButtonText)));
//        onView(withId(R.id.details_play_count_view)).check(matches(withText(modalButtonText)));
//        onView(withId(R.id.details_url_view)).check(matches(withText(modalButtonText)));
//        onView(withId(R.id.details_description_view)).check(matches(withText(modalButtonText)));
        return this;
    }

}
