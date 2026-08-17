package com.android.tools.r8.internal;

import java.util.Comparator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eQ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1399eQ implements Comparator {
    public static final C1399eQ b = new C1399eQ();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ((AbstractC1314dQ) obj2).a() - ((AbstractC1314dQ) obj).a();
    }
}
