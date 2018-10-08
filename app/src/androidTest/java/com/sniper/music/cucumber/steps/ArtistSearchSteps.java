package com.sniper.music.cucumber.steps;

import android.support.test.rule.ActivityTestRule;

import com.sniper.music.espresso.hometests.configurator.HomeConfigurator;
import com.sniper.music.espresso.hometests.robot.HomeScreenRobot;
import com.sniper.music.home.HomeActivity;
import com.sniper.music.utils.rules.FinishOpenActivitiesRule;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;

import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

@SuppressWarnings("unused")
public class ArtistSearchSteps {

    private static HomeConfigurator homeConfigurator = new HomeConfigurator();

//    @Rule
//    public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);

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

    @Given("^I start the application$")
    public void I_start_app() {
        robot.launchHomeScreen(new ActivityTestRule<>(HomeActivity.class));
//        robot.launchHomeScreen(activityRule);
    }

    @And("^I click search icon")
    public void I_click_search_icon() {
        robot.clickSearchIcon();
    }

//    @Then("^I expect to see the following artist search results$")
//    public void I_expect_to_see_the_following_artist_search_results() {
//        robot.isExpectedSearchResultDisplayed();
//    }

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



//
//    @Given("^the following remote movie posters exist$")
//    public void the_following_remote_movie_poster_exist(final DataTable dataTable) {
//        extractPostersFromDataTable(dataTable);
//    }
//
//    private void extractPostersFromDataTable(DataTable dataTable) {
//        ConfigurableBackend configurableBackend = getConfigurableBackend();
//
//        for (final Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
//            Long movieId = Long.valueOf(row.get("movieId"));
//            String posterPath = row.get("posterPath");
//
//            configurableBackend.addToPopularStream(createApiMoviePoster(movieId, posterPath));
//        }
//    }
//
//    private ApiMoviePoster createApiMoviePoster(long movieId, String posterPath) {
//        return new ApiMoviePoster(movieId, posterPath);
//    }
//
//    @When("^I select the poster at position (\\d+)$")
//    public void I_select_a_poster_at(final int position) {
//        popularMoviesRobot.selectPosterAtPosition(position);
//    }
//
//    @When("^I expect to see the following movie posters$")
//    public void I_expect_to_see_movie_posters(final DataTable dataTable) {
//        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
//            int position = Integer.valueOf(row.get("position"));
//            String posterPath = row.get("posterPath");
//
//            popularMoviesRobot.checkPosterWithPathIsDisplayedAtPosition(position, posterPath);
//        }
//    }
//
//    private ConfigurableBackend getConfigurableBackend() {
//        MovieApplication movieApplication = (MovieApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
//        return (ConfigurableBackend) ((TestPopularMoviesComponent) movieApplication.getPopularMoviesComponent()).backend();
//    }


}

