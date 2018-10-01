package com.sniper.music.details;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sniper.music.annotations.SmokeTest;
import com.sniper.music.annotations.StableTest;
import com.sniper.music.base.intents.IntentExtras;
import com.sniper.music.details.configurator.DetailsConfigurator;
import com.sniper.music.details.robot.DetailsScreenRobot;
import com.sniper.music.rules.FinishOpenActivitiesRule;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@SmokeTest
@StableTest
@RunWith(AndroidJUnit4.class)
public class DetailsScreenUseCaseTests {

    private static DetailsConfigurator detailsConfigurator = new DetailsConfigurator();

//    @Rule
//    public RxIdlingResourceRule rxIdlingResourceRule = new RxIdlingResourceRule();
    @Rule
    public ActivityTestRule<DetailsActivity> activityRule = new ActivityTestRule<DetailsActivity>(DetailsActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Intent result = new Intent(targetContext, DetailsActivity.class);
            result.putExtra(IntentExtras.EXTRA_MB_ID, "bfcc6d75-a6a5-4bc6-8282-47aec8531818");
            result.putExtra(IntentExtras.EXTRA_NAME, "Cher");
            return result;
        }
    };

    @Rule
    public FinishOpenActivitiesRule finishOpenActivitiesRule = new FinishOpenActivitiesRule();
    private DetailsScreenRobot robot = new DetailsScreenRobot();

    @BeforeClass
    public static void beforeClass() {
        detailsConfigurator.setup();
        detailsConfigurator.setDetailsInfo();
    }

    @AfterClass
    public static void afterClass() {
        detailsConfigurator.tearDown();
    }

    @Test
    public void test_all_expected_views_are_visible() {
        robot.areAllViewsVisible();
    }

    @Test
    public void test_all_views_are_with_expected_text_information() {
        robot.areAllTextViewsWithExpectedText();
    }
}
