package com.sniper.music.retrofit;

import java.util.concurrent.TimeUnit;

public class MockRetrofitResponseThrottle {

    private long throttleBytesPerPeriod;
    private long throttlePeriodAmount;
    private TimeUnit throttlePeriodUnit;

    MockRetrofitResponseThrottle(long throttleBytesPerPeriod, long throttlePeriodAmount, TimeUnit throttlePeriodUnit) {
        this.throttleBytesPerPeriod = throttleBytesPerPeriod;
        this.throttlePeriodAmount = throttlePeriodAmount;
        this.throttlePeriodUnit = throttlePeriodUnit;
    }

    public static MockRetrofitResponseThrottleBuilder builder() {
        return new MockRetrofitResponseThrottleBuilder();
    }

    public long getThrottleBytesPerPeriod() {
        return this.throttleBytesPerPeriod;
    }

    public long getThrottlePeriodAmount() {
        return this.throttlePeriodAmount;
    }

    public TimeUnit getThrottlePeriodUnit() {
        return this.throttlePeriodUnit;
    }

    public void setThrottleBytesPerPeriod(long throttleBytesPerPeriod) {
        this.throttleBytesPerPeriod = throttleBytesPerPeriod;
    }

    public void setThrottlePeriodAmount(long throttlePeriodAmount) {
        this.throttlePeriodAmount = throttlePeriodAmount;
    }

    public void setThrottlePeriodUnit(TimeUnit throttlePeriodUnit) {
        this.throttlePeriodUnit = throttlePeriodUnit;
    }

    @SuppressWarnings("all")
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof MockRetrofitResponseThrottle)) return false;
        final MockRetrofitResponseThrottle other = (MockRetrofitResponseThrottle) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getThrottleBytesPerPeriod() != other.getThrottleBytesPerPeriod()) return false;
        if (this.getThrottlePeriodAmount() != other.getThrottlePeriodAmount()) return false;
        final Object this$throttlePeriodUnit = this.getThrottlePeriodUnit();
        final Object other$throttlePeriodUnit = other.getThrottlePeriodUnit();
        if (this$throttlePeriodUnit == null ? other$throttlePeriodUnit != null : !this$throttlePeriodUnit.equals(other$throttlePeriodUnit))
            return false;
        return true;
    }

    @SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $throttleBytesPerPeriod = this.getThrottleBytesPerPeriod();
        result = result * PRIME + (int) ($throttleBytesPerPeriod >>> 32 ^ $throttleBytesPerPeriod);
        final long $throttlePeriodAmount = this.getThrottlePeriodAmount();
        result = result * PRIME + (int) ($throttlePeriodAmount >>> 32 ^ $throttlePeriodAmount);
        final Object $throttlePeriodUnit = this.getThrottlePeriodUnit();
        result = result * PRIME + ($throttlePeriodUnit == null ? 43 : $throttlePeriodUnit.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof MockRetrofitResponseThrottle;
    }

    @SuppressWarnings("all")
    public String toString() {
        return "MockRetrofitResponseThrottle(throttleBytesPerPeriod=" + this.getThrottleBytesPerPeriod() + ", throttlePeriodAmount=" + this.getThrottlePeriodAmount() + ", throttlePeriodUnit=" + this.getThrottlePeriodUnit() + ")";
    }

    public static class MockRetrofitResponseThrottleBuilder {
        private long throttleBytesPerPeriod;
        private long throttlePeriodAmount;
        private TimeUnit throttlePeriodUnit;

        MockRetrofitResponseThrottleBuilder() {
        }

        public MockRetrofitResponseThrottleBuilder throttleBytesPerPeriod(long throttleBytesPerPeriod) {
            this.throttleBytesPerPeriod = throttleBytesPerPeriod;
            return this;
        }

        public MockRetrofitResponseThrottleBuilder throttlePeriodAmount(long throttlePeriodAmount) {
            this.throttlePeriodAmount = throttlePeriodAmount;
            return this;
        }

        public MockRetrofitResponseThrottleBuilder throttlePeriodUnit(TimeUnit throttlePeriodUnit) {
            this.throttlePeriodUnit = throttlePeriodUnit;
            return this;
        }

        public MockRetrofitResponseThrottle build() {
            return new MockRetrofitResponseThrottle(throttleBytesPerPeriod, throttlePeriodAmount, throttlePeriodUnit);
        }
    }
}
