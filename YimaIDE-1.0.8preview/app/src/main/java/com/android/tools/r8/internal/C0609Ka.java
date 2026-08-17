package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ka, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0609Ka extends AbstractC0660Ma {
    public final El0 a;

    public C0609Ka(El0 el0) {
        this.a = el0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0660Ma
    public final El0 a() {
        return this.a;
    }

    @Override // com.android.tools.r8.internal.AbstractC0660Ma
    public final com.android.tools.r8.graph.I2 b() {
        return null;
    }

    public final String toString() {
        return "Imprecise(" + this.a + ")";
    }
}
