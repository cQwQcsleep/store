package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2981wu {
    public static int a(int i, int i2) {
        if (i2 < 0) {
            x01.a("cannot store more than MAX_VALUE elements");
            return 0;
        }
        int iHighestOneBit = i + (i >> 1) + 1;
        if (iHighestOneBit < i2) {
            iHighestOneBit = Integer.highestOneBit(i2 - 1) << 1;
        }
        if (iHighestOneBit < 0) {
            return Integer.MAX_VALUE;
        }
        return iHighestOneBit;
    }

    public abstract AbstractC2981wu a(Object obj);

    public final void a(Iterable iterable) {
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }
}
