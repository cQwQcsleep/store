package com.android.tools.r8.internal;

import java.util.Comparator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bW, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1153bW implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Integer numValueOf = Integer.valueOf(((Number) ((C1491fW) obj).b).intValue());
        Integer numValueOf2 = Integer.valueOf(((Number) ((C1491fW) obj2).b).intValue());
        if (numValueOf == numValueOf2) {
            return 0;
        }
        return numValueOf.compareTo(numValueOf2);
    }
}
