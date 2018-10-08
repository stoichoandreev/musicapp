package com.sniper.music.test;

import cucumber.api.CucumberOptions;

@CucumberOptions(
        features = "features",
        glue = { "com.sniper.music.cucumber.steps" },
        tags = { "@smoke" }
)
@SuppressWarnings("unused")
public class CucumberTestCase {
    //no implementation
}
