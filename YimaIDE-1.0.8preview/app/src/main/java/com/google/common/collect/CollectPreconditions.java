package com.google.common.collect;

import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
final class CollectPreconditions {
    private CollectPreconditions() {
    }

    public static void checkEntryNotNull(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("null key in entry: null=" + obj2);
        }
        if (obj2 != null) {
            return;
        }
        vye.a("null value in entry: ", obj, "=null");
    }

    public static long checkNonnegative(long j, String str) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException(str + " cannot be negative but was: " + j);
    }

    public static void checkPositive(int i, String str) {
        if (i > 0) {
            return;
        }
        do4.a(str, " must be positive but was: ", i);
    }

    public static void checkRemove(boolean z) {
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
    }

    public static int checkNonnegative(int i, String str) {
        if (i >= 0) {
            return i;
        }
        do4.a(str, " cannot be negative but was: ", i);
        return 0;
    }
}
