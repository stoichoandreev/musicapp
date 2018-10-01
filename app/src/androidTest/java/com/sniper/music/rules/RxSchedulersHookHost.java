package com.sniper.music.rules;

//import rx.functions.Action0;
//import rx.plugins.RxJavaSchedulersHook;

public class RxSchedulersHookHost {

}

//public class RxSchedulersHookHost extends RxJavaSchedulersHook {
//
//    private static RxSchedulersHookHost instance;
//    private RxJavaSchedulersHook delegate;
//
//    public static synchronized RxSchedulersHookHost getInstance() {
//        if (instance == null) {
//            instance = new RxSchedulersHookHost();
//        }
//        return instance;
//    }
//
//    @Override
//    public Action0 onSchedule(Action0 action) {
//        RxJavaSchedulersHook snapShotDelegate = getDelegate();
//        return snapShotDelegate == null ? super.onSchedule(action) : snapShotDelegate.onSchedule(action);
//    }
//
//    private synchronized RxJavaSchedulersHook getDelegate() {
//        return delegate;
//    }
//
//    /**
//     * Note that this delegate is only used to override {@link #onSchedule(Action0)}.
//     */
//    public synchronized void setDelegate(@Nullable RxJavaSchedulersHook newValue) {
//        this.delegate = newValue;
//    }
//}
