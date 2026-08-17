package org.jetbrains.kotlin.util;

import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/util/TimeRatio;", "", "nanos", "", "userNanos", "cpuNanos", "<init>", "(DDD)V", "getNanos", "()D", "getUserNanos", "getCpuNanos", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final /* data */ class TimeRatio {
    private final double cpuNanos;
    private final double nanos;
    private final double userNanos;

    public TimeRatio(double d, double d2, double d3) {
        this.nanos = d;
        this.userNanos = d2;
        this.cpuNanos = d3;
    }

    public static /* synthetic */ TimeRatio copy$default(TimeRatio timeRatio, double d, double d2, double d3, int i, Object obj) {
        if ((i & 1) != 0) {
            d = timeRatio.nanos;
        }
        double d4 = d;
        if ((i & 2) != 0) {
            d2 = timeRatio.userNanos;
        }
        double d5 = d2;
        if ((i & 4) != 0) {
            d3 = timeRatio.cpuNanos;
        }
        return timeRatio.copy(d4, d5, d3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final double getNanos() {
        return this.nanos;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final double getUserNanos() {
        return this.userNanos;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final double getCpuNanos() {
        return this.cpuNanos;
    }

    public final TimeRatio copy(double nanos, double userNanos, double cpuNanos) {
        return new TimeRatio(nanos, userNanos, cpuNanos);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TimeRatio)) {
            return false;
        }
        TimeRatio timeRatio = (TimeRatio) other;
        return Double.compare(this.nanos, timeRatio.nanos) == 0 && Double.compare(this.userNanos, timeRatio.userNanos) == 0 && Double.compare(this.cpuNanos, timeRatio.cpuNanos) == 0;
    }

    public final double getCpuNanos() {
        return this.cpuNanos;
    }

    public final double getNanos() {
        return this.nanos;
    }

    public final double getUserNanos() {
        return this.userNanos;
    }

    public int hashCode() {
        return (((Double.hashCode(this.nanos) * 31) + Double.hashCode(this.userNanos)) * 31) + Double.hashCode(this.cpuNanos);
    }

    public String toString() {
        return "TimeRatio(nanos=" + this.nanos + ", userNanos=" + this.userNanos + ", cpuNanos=" + this.cpuNanos + ')';
    }
}
