package com.sniper.music.rules;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.idling.CountingIdlingResource;
import org.junit.rules.ExternalResource;


public class RxIdlingResourceRule extends ExternalResource {

    private CountingIdlingResource counter;

    /**
     * Counter is re-allocated each time in case it wasn't fully idle the previous time.
     */
    @Override
    protected void before() {
        counter = new CountingIdlingResource("RxJavaCountingResource", true);
//        CountingSchedulersHook schedulerHook = new CountingSchedulersHook(counter);
        Espresso.registerIdlingResources(counter);
//        RxSchedulersHookHost.getInstance().setDelegate(schedulerHook);
    }

    @Override
    protected void after() {
        logIfNotIdle();
        Espresso.unregisterIdlingResources(counter);
//        RxSchedulersHookHost.getInstance().setDelegate(null);
        counter = null;
    }

    private void logIfNotIdle() {
        if (!counter.isIdleNow()) {
            counter.dumpStateToLogs();
        }
    }
}
