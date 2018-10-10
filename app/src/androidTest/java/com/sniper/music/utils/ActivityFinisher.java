package com.sniper.music.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitor;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class ActivityFinisher implements Runnable {

    @Nonnull
    private final ActivityLifecycleMonitor activityLifecycleMonitor;
    @Nullable
    private CountDownLatch latch;
    @Nullable
    private List<Activity> activities;

    private ActivityFinisher() {
        this.activityLifecycleMonitor = ActivityLifecycleMonitorRegistry.getInstance();
    }

    public ActivityFinisher(@Nullable CountDownLatch latch, @Nullable List<Activity> activities) {
        this();
        this.latch = latch;
        this.activities = activities;
    }

    /**
     * Use this option to finish activities if you can't use {@link FinishOpenActivitiesRule}
     * For example in Cucumber tests where the runner is {@link com.sniper.music.cucumber.runner.CucumberTestRunner}
     * and we have multiple Activities open at the sane test
     */
    public static void finishOpenActivities() {
        new Handler(Looper.getMainLooper()).post(new ActivityFinisher());
    }

    @Override
    public void run() {
        final List<Activity> activities = this.activities != null ? this.activities : new ArrayList<>();

        for (final Stage stage : EnumSet.range(Stage.CREATED, Stage.STOPPED)) {
            activities.addAll(activityLifecycleMonitor.getActivitiesInStage(stage));
        }

        if (latch != null) {
            latch.countDown();
        } else {
            for (final Activity activity : activities) {
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
    }
}
