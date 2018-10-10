package com.sniper.music.utils.rules;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.test.runner.lifecycle.ActivityLifecycleCallback;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitor;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import org.junit.rules.ExternalResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FinishOpenActivitiesRule extends ExternalResource {

    public static final int TIMEOUT_GET_OPEN_ACTIVITIES_SECONDS = 10;
    public static final int TIMEOUT_ACTIVITY_FINISH_SECONDS = 10;

    @Override
    protected void before() {
        //unused
    }

    @Override
    protected void after() {
        finishOpenActivities();
    }

    private void finishOpenActivities() {

        final CountDownLatch latch = new CountDownLatch(1);
        ActivityLifecycleMonitor activityLifecycleMonitor = ActivityLifecycleMonitorRegistry.getInstance();
        List<Activity> activities = Collections.synchronizedList(new ArrayList<Activity>());

        new Handler(Looper.getMainLooper()).post(new OpenActivitiesGetter(latch, activities, activityLifecycleMonitor));

        try {
            latch.await(TIMEOUT_GET_OPEN_ACTIVITIES_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (activities.size() > 0) {
                finishActivity(activityLifecycleMonitor, activities.get(0));
                finishOpenActivities();
            }
        }
    }

    private void finishActivity(ActivityLifecycleMonitor activityLifecycleMonitor, Activity activity) {

        if (activity != null) {
            final CountDownLatch latch = new CountDownLatch(1);
            final int activityHash = activity.hashCode();
            activity.finish();

            ActivityLifecycleCallback callback = (activity1, stage) -> {
                if (activity1.hashCode() == activityHash) {
                    if (stage == Stage.DESTROYED) {
                        latch.countDown();
                    }
                }
            };

            activityLifecycleMonitor.addLifecycleCallback(callback);

            try {
                latch.await(TIMEOUT_ACTIVITY_FINISH_SECONDS, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

                activityLifecycleMonitor.removeLifecycleCallback(callback);
            }
        }
    }

    private class OpenActivitiesGetter implements Runnable {

        private ActivityLifecycleMonitor activityLifecycleMonitor;
        private CountDownLatch latch;
        private List<Activity> activities;

        public OpenActivitiesGetter(CountDownLatch latch,
                                    List<Activity> activities,
                                    ActivityLifecycleMonitor activityLifecycleMonitor) {
            this.latch = latch;
            this.activities = activities;
            this.activityLifecycleMonitor = activityLifecycleMonitor;
        }

        @Override
        public void run() {
            for (final Stage stage : EnumSet.range(Stage.PRE_ON_CREATE, Stage.RESTARTED)) {
                activities.addAll(activityLifecycleMonitor.getActivitiesInStage(stage));
            }
            latch.countDown();
        }
    }

}
