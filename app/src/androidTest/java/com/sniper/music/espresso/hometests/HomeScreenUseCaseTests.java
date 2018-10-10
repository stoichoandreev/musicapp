package com.sniper.music.espresso.hometests;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sniper.music.espresso.annotations.HomeScreenTest;
import com.sniper.music.espresso.annotations.SmokeTest;
import com.sniper.music.espresso.annotations.StableTest;
import com.sniper.music.home.HomeActivity;
import com.sniper.music.espresso.hometests.configurator.HomeConfigurator;
import com.sniper.music.espresso.hometests.robot.HomeScreenRobot;
import com.sniper.music.utils.rules.FinishOpenActivitiesRule;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@SmokeTest
@StableTest
@HomeScreenTest
@RunWith(AndroidJUnit4.class)
public class HomeScreenUseCaseTests {

    private static HomeConfigurator homeConfigurator = new HomeConfigurator();

    @Rule
    public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);

    @Rule
    public FinishOpenActivitiesRule finishOpenActivitiesRule = new FinishOpenActivitiesRule();

    private HomeScreenRobot robot = new HomeScreenRobot();

    @BeforeClass
    public static void beforeClass() {
        homeConfigurator.setup();
        homeConfigurator.setSearchResponse();
    }

    @AfterClass
    public static void afterClass() {
        homeConfigurator.tearDown();
    }

    @Test
    public void test_user_sees_search_icon_and_can_click_on_it() {
        robot.isSearchIconVisible().isSearchIconClickable();
    }

    @Test
    public void test_user_can_click_on_search_icon_and_can_enter_search_query() {
        robot.clickSearchIcon().enterSearchQuery("Cher");
    }

    @Test
    public void test_user_enters_search_query_and_sees_expected_search_results() {
        robot.clickSearchIcon().enterSearchQuery("Cher")
                .closeKeyboard()
                .isArtistNameDisplayed(0, "Cher")
                .isArtistNameDisplayed(1, "Cheryl Cole")
                .isArtistNameDisplayed(2, "Cher Lloyd")
                .isArtistNameDisplayed(3, "Black Stone Cherry");
    }
}
