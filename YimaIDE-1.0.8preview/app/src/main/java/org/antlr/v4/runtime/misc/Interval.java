package org.antlr.v4.runtime.misc;

import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class Interval {
    public static final int INTERVAL_POOL_MAX_VALUE = 1000;
    public static final Interval INVALID = new Interval(-1, -2);
    static final Interval[] cache = new Interval[WebSocketProtocol.CLOSE_CLIENT_GOING_AWAY];
    public int a;
    public int b;

    public Interval(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public static Interval of(int i, int i2) {
        if (i != i2 || i < 0 || i > 1000) {
            return new Interval(i, i2);
        }
        Interval[] intervalArr = cache;
        if (intervalArr[i] == null) {
            intervalArr[i] = new Interval(i, i);
        }
        return intervalArr[i];
    }

    public boolean adjacent(Interval interval) {
        return this.a == interval.b + 1 || this.b == interval.a - 1;
    }

    public Interval differenceNotProperlyContained(Interval interval) {
        if (interval.startsBeforeNonDisjoint(this)) {
            return of(Math.max(this.a, interval.b + 1), this.b);
        }
        if (interval.startsAfterNonDisjoint(this)) {
            return of(this.a, interval.a - 1);
        }
        return null;
    }

    public boolean disjoint(Interval interval) {
        return startsBeforeDisjoint(interval) || startsAfterDisjoint(interval);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Interval)) {
            Interval interval = (Interval) obj;
            if (this.a == interval.a && this.b == interval.b) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((713 + this.a) * 31) + this.b;
    }

    public Interval intersection(Interval interval) {
        return of(Math.max(this.a, interval.a), Math.min(this.b, interval.b));
    }

    public int length() {
        int i = this.b;
        int i2 = this.a;
        if (i < i2) {
            return 0;
        }
        return (i - i2) + 1;
    }

    public boolean properlyContains(Interval interval) {
        return interval.a >= this.a && interval.b <= this.b;
    }

    public boolean startsAfter(Interval interval) {
        return this.a > interval.a;
    }

    public boolean startsAfterDisjoint(Interval interval) {
        return this.a > interval.b;
    }

    public boolean startsAfterNonDisjoint(Interval interval) {
        int i = this.a;
        return i > interval.a && i <= interval.b;
    }

    public boolean startsBeforeDisjoint(Interval interval) {
        int i = this.a;
        int i2 = interval.a;
        return i < i2 && this.b < i2;
    }

    public boolean startsBeforeNonDisjoint(Interval interval) {
        int i = this.a;
        int i2 = interval.a;
        return i <= i2 && this.b >= i2;
    }

    public String toString() {
        return this.a + ".." + this.b;
    }

    public Interval union(Interval interval) {
        return of(Math.min(this.a, interval.a), Math.max(this.b, interval.b));
    }
}
