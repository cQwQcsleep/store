package com.google.common.collect;

import java.time.Duration;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
final class Internal {
    private Internal() {
    }

    public static long toNanosSaturated(Duration duration) {
        try {
            return duration.toNanos();
        } catch (ArithmeticException unused) {
            return duration.isNegative() ? Long.MIN_VALUE : Long.MAX_VALUE;
        }
    }
}
