package com.sniper.music.espresso.hometests.robot;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.sniper.music.R;
import com.sniper.music.home.HomeActivity;
import com.sniper.music.utils.RecyclerViewMatcher;
import com.sniper.music.utils.SpecTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class HomeScreenRobot {

    private SpecTest specTest = new SpecTest();

    public HomeScreenRobot launchHomeScreen(ActivityTestRule<HomeActivity> testRule) {
        testRule.launchActivity(null);
        return this;
    }

    public HomeScreenRobot isSearchIconVisible() {
        onView(withId(R.id.action_search)).check(matches(isDisplayed()));
        return this;
    }

    public HomeScreenRobot isSearchIconClickable() {
        onView(withId(R.id.action_search)).check(matches(isClickable()));
        return this;
    }

    public HomeScreenRobot clickSearchIcon() {
        onView(withId(R.id.action_search)).perform(click());
        return this;
    }

    public HomeScreenRobot enterSearchQuery(String query) {
        onView(withHint(InstrumentationRegistry.getTargetContext().getString(R.string.search_hint))).perform(typeText(query));
        return this;
    }

    public HomeScreenRobot closeKeyboard() {
        specTest.closeSoftKeyboard();
        return this;
    }

    public HomeScreenRobot isArtistNameDisplayed(int position, String artistName) {
        final RecyclerViewMatcher recyclerViewMatcher = specTest.withRecyclerView(R.id.search_results_recycler_view);
        onView(recyclerViewMatcher.atPosition(position)).check(matches(hasDescendant(withText(artistName))));
        return this;
    }

    public HomeScreenRobot selectItemAt(int position) {
        onView(withId(R.id.search_results_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
        return this;
    }
}
