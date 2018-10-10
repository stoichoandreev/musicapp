package com.sniper.music.espresso.detailstests;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sniper.music.espresso.annotations.DetailsScreenTest;
import com.sniper.music.espresso.annotations.SmokeTest;
import com.sniper.music.espresso.annotations.StableTest;
import com.sniper.music.base.intents.IntentExtras;
import com.sniper.music.details.DetailsActivity;
import com.sniper.music.espresso.detailstests.configurator.DetailsConfigurator;
import com.sniper.music.espresso.detailstests.robot.DetailsScreenRobot;
import com.sniper.music.utils.rules.FinishOpenActivitiesRule;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@SmokeTest
@StableTest
@DetailsScreenTest
@RunWith(AndroidJUnit4.class)
public class DetailsScreenUseCaseTests {

    private static DetailsConfigurator detailsConfigurator = new DetailsConfigurator();

    @Rule
    public ActivityTestRule<DetailsActivity> activityRule = new ActivityTestRule<DetailsActivity>(DetailsActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            final Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            final Intent result = new Intent(targetContext, DetailsActivity.class);
            result.putExtra(IntentExtras.EXTRA_MB_ID, "bfcc6d75-a6a5-4bc6-8282-47aec8531818");
            result.putExtra(IntentExtras.EXTRA_NAME, "Cher");
            return result;
        }
    };

    @Rule
    public FinishOpenActivitiesRule finishOpenActivitiesRule = new FinishOpenActivitiesRule();

    private final DetailsScreenRobot robot = new DetailsScreenRobot();

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
    public void test_user_sees_selected_artist_information() {
        robot.areAllViewsVisible()
                .areAllViewsWithExpectedInformation();
    }
}
