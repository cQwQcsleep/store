package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Nv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0707Nv implements Iterable {
    public final InterfaceC1270cr b;

    public C0707Nv(C2528re c2528re) {
        this.b = c2528re;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new C0733Ov((Iterator) this.b.a());
    }
}
