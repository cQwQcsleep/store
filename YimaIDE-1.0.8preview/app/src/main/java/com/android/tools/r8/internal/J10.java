package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class J10 extends AbstractC2943wV implements Serializable {
    public static final J10 b = new J10();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        K10 k10 = (K10) obj;
        K10 k11 = (K10) obj2;
        return AbstractC0353Ae.a.a(k10.b, k11.b).a(k10.c, k11.c).a();
    }
}
