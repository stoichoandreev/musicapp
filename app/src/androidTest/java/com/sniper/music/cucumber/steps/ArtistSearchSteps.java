package com.sniper.music.cucumber.steps;

import android.support.test.rule.ActivityTestRule;

import com.sniper.music.espresso.hometests.configurator.HomeConfigurator;
import com.sniper.music.espresso.hometests.robot.HomeScreenRobot;
import com.sniper.music.home.HomeActivity;

import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

@SuppressWarnings("unused")
public class ArtistSearchSteps {

    private static HomeConfigurator homeConfigurator = new HomeConfigurator();

    private ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class, false, false);

    private HomeScreenRobot robot = new HomeScreenRobot();

    @Before
    public void setup() {
        homeConfigurator.setup();
        homeConfigurator.setSearchResponse();
    }

    @After
    public void tearDown() {
        homeConfigurator.tearDown();
    }

    @Given("^I start the application$")
    public void I_start_app() {
        robot.launchHomeScreen(activityRule);
    }

    @And("^I click search icon$")
    public void I_click_search_icon() {
        robot.clickSearchIcon();
    }

    @And("^I enter search query (\\S+)$")
    public void I_enter_search_query(String query) {
        robot.enterSearchQuery(query);
    }

    @And("^I close the keyboard$")
    public void I_close_the_keyboard() {
        robot.closeKeyboard();
    }

    @Then("^I expect to see the following artist search results$")
    public void I_expect_to_see_the_following_artist_search_results(final DataTable dataTable) {
        final Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);

        String position = row.get("position");
        String artistName = row.get("name");
        robot.isArtistNameDisplayed(Integer.parseInt(position), artistName);
    }

    @And("^I select artist at (\\d+)$")
    public void I_select_artist_at_position(int position) {
        robot.selectItemAt(position);
    }
}
