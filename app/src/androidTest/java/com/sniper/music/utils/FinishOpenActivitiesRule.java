package com.sniper.music.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.test.runner.lifecycle.ActivityLifecycleCallback;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitor;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import com.sniper.music.utils.ActivityFinisher;

import org.junit.rules.ExternalResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FinishOpenActivitiesRule extends ExternalResource {

    private static final int TIMEOUT_GET_OPEN_ACTIVITIES_SECONDS = 10;
    private static final int TIMEOUT_ACTIVITY_FINISH_SECONDS = 10;

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
        final List<Activity> activities = Collections.synchronizedList(new ArrayList<>());

        new Handler(Looper.getMainLooper()).post(new ActivityFinisher(latch, activities));

        try {
            latch.await(TIMEOUT_GET_OPEN_ACTIVITIES_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (activities.size() > 0) {
                finishActivity(ActivityLifecycleMonitorRegistry.getInstance(), activities.get(0));
                finishOpenActivities();
            }
        }
    }

    private void finishActivity(ActivityLifecycleMonitor activityLifecycleMonitor, Activity activity) {

        if (activity != null) {
            final CountDownLatch latch = new CountDownLatch(1);
            final int activityHash = activity.hashCode();
            activity.finish();

            final ActivityLifecycleCallback callback = (activity1, stage) -> {
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
}
