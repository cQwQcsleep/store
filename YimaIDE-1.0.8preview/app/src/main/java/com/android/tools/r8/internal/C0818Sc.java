package com.android.tools.r8.internal;

import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Sc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0818Sc {
    public static final C0818Sc b;
    public final Set a;

    static {
        int i = AbstractC2554rv.c;
        b = new C0818Sc(W40.j);
    }

    public C0818Sc(com.android.tools.r8.graph.I2 i2) {
        int i = AbstractC2554rv.c;
        this.a = new Cc0(i2);
    }

    public C0818Sc(Set set) {
        this.a = set;
    }
}
