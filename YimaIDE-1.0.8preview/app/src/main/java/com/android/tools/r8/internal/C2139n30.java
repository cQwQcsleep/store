package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.n30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2139n30 implements InterfaceC1713i30, Map.Entry {
    public int b;
    public final /* synthetic */ C2481r30 c;

    public C2139n30(C2481r30 c2481r30, int i) {
        this.c = c2481r30;
        this.b = i;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return this.c.c[this.b] == entry.getKey() && this.c.d[this.b] == ((Integer) entry.getValue()).intValue();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1713i30
    public final int getIntValue() {
        return this.c.d[this.b];
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.c.c[this.b];
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return Integer.valueOf(this.c.d[this.b]);
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return this.c.d[this.b] ^ System.identityHashCode(this.c.c[this.b]);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        int iIntValue = ((Integer) obj).intValue();
        int[] iArr = this.c.d;
        int i = this.b;
        int i2 = iArr[i];
        iArr[i] = iIntValue;
        return Integer.valueOf(i2);
    }

    public final String toString() {
        return this.c.c[this.b] + "=>" + this.c.d[this.b];
    }
}
