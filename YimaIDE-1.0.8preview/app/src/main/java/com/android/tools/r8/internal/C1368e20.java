package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;

/* JADX INFO: renamed from: com.android.tools.r8.internal.e20, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1368e20 extends AbstractC1454f20 {
    public final C0322w2 E;

    public C1368e20(C0322w2 c0322w2) {
        this.E = c0322w2;
    }

    @Override // com.android.tools.r8.internal.AbstractC1454f20
    public final Object a() {
        return AbstractC0551Hu.a(C1368e20.class, this.E);
    }

    public final String toString() {
        return "UnsupportedLibraryInvoke(" + this.E.m0() + ")";
    }
}
