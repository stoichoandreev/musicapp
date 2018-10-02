package com.sniper.music.home;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sniper.music.annotations.HomeScreenTest;
import com.sniper.music.annotations.SmokeTest;
import com.sniper.music.annotations.StableTest;
import com.sniper.music.home.configurator.HomeConfigurator;
import com.sniper.music.home.robot.HomeScreenRobot;
import com.sniper.music.rules.FinishOpenActivitiesRule;

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
    public void test_user_clicks_on_search_icon_enters_search_query_and_sees_expected_search_results() {
        robot.clickSearchIcon().enterSearchQuery("Cher").isExpectedSearchResultDisplayed();
    }
}
