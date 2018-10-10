package com.sniper.music.test;

import cucumber.api.CucumberOptions;

@CucumberOptions(
        features = "features",
        glue = { "com.sniper.music.cucumber.steps" },
        tags = { "@e2e", "@smoke", "home", "details" }
)
@SuppressWarnings("unused")
public class CucumberTestCase {
    //no implementation needed
}
