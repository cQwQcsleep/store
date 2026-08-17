package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.La, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0635La extends AbstractC0660Ma {
    public final com.android.tools.r8.graph.I2 a;

    public C0635La(com.android.tools.r8.graph.I2 i2) {
        this.a = i2;
    }

    @Override // com.android.tools.r8.internal.AbstractC0660Ma
    public final El0 a() {
        return El0.a(this.a);
    }

    @Override // com.android.tools.r8.internal.AbstractC0660Ma
    public final com.android.tools.r8.graph.I2 b() {
        return this.a;
    }

    public final String toString() {
        return "Precise(" + this.a + ")";
    }
}
