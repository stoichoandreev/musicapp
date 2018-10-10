package com.sniper.music.cucumber.steps;

import com.sniper.music.espresso.detailstests.configurator.DetailsConfigurator;
import com.sniper.music.espresso.detailstests.robot.DetailsScreenRobot;
import com.sniper.music.utils.ActivityFinisher;

import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;

@SuppressWarnings("unused")
public class DetailsDisplaySteps {

    private static DetailsConfigurator detailsConfigurator = new DetailsConfigurator();

    private DetailsScreenRobot robot = new DetailsScreenRobot();

    @Before
    public void setup() {
        detailsConfigurator.setup();
        detailsConfigurator.setDetailsInfo();
    }

    @After
    public void tearDown() {
        detailsConfigurator.tearDown();
        ActivityFinisher.finishOpenActivities(); // Required for test scenarios with multiple activities
    }

    @Then("^I expect to see the following artist details$")
    public void I_expect_to_see_the_following_artist_details(final DataTable dataTable) {
        final Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);

        final String artistTitle = row.get("artistTitle");
        final String artistListenersInfo = row.get("artistListeners");
        final String artistPlayedInfo = row.get("artistPlayed");
        robot.isArtistTitleWithExpectedInformation(artistTitle)
                .isArtistListenersWithExpectedInformation(artistListenersInfo)
                .isArtistPlayedWithExpectedInformation(artistPlayedInfo);
    }

}
