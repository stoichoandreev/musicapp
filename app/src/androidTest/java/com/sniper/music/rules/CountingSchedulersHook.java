package com.sniper.music.rules;


import static org.apache.commons.lang3.Validate.notNull;


public class CountingSchedulersHook {

}
//public class CountingSchedulersHook extends RxJavaSchedulersHook {
//
//    @NonNull
//    private final CountingIdlingResource counter;
//
//    public CountingSchedulersHook(@NonNull CountingIdlingResource counter) {
//        this.counter = notNull(counter);
//    }
//
//    @Override
//    public Action0 onSchedule(Action0 action) {
//        return new Wrapped(action, counter);
//    }
//
//    /**
//     * Keeps the same counter we start the action with.
//     */
//    private static class Wrapped implements Action0 {
//
//        private final CountingIdlingResource counter;
//        private final Action0 delegate;
//
//        private Wrapped(Action0 delegate, CountingIdlingResource counter) {
//            this.counter = counter;
//            this.delegate = delegate;
//        }
//
//        @Override
//        public void call() {
//            try {
//                counter.increment();
//                delegate.call();
//            } finally {
//                counter.decrement();
//            }
//        }
//    }
//}
