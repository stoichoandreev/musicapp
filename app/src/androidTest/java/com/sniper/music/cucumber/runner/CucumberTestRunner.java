package com.sniper.music.cucumber.runner;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;

import com.sniper.music.TestMusicApplication;
import com.sniper.music.debug.test.BuildConfig;

import cucumber.api.android.CucumberInstrumentationCore;

@SuppressWarnings("unused")
public class CucumberTestRunner extends AndroidJUnitRunner {
//    public class CucumberTestRunner extends MonitoringInstrumentation {

    private static final String CUCUMBER_TAGS_KEY = "tags";
    private static final String CUCUMBER_SCENARIO_KEY = "name";

    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        return super.newApplication(cl, TestMusicApplication.class.getName(), context);
    }

    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
//        Context context = InstrumentationRegistry.getContext();//getTargetContext();
//        Log.d("Cucumber: ", "Instrumentation Context Package " + context.getPackageName());
        String tags = BuildConfig.TEST_TAGS;
        if (!tags.isEmpty()) {
            bundle.putString(CUCUMBER_TAGS_KEY, tags.replaceAll("\\s", ""));
        }

        String scenario = BuildConfig.TEST_SCENARIO;
        if (!scenario.isEmpty()) {
            scenario = scenario.replaceAll(" ", "\\\\s");
            bundle.putString(CUCUMBER_SCENARIO_KEY, scenario);
        }
        instrumentationCore.create(bundle);
//        super.onCreate(bundle);
    }

    @Override
    public void onStart() {
        waitForIdleSync();
        instrumentationCore.start();
    }
}
