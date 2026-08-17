package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ux, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2812ux implements InterfaceC2300ox, Map.Entry {
    public int b;
    public final /* synthetic */ C3153yx c;

    public C2812ux(C3153yx c3153yx, int i) {
        this.c = c3153yx;
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2300ox
    public final int a() {
        return this.c.c[this.b];
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return this.c.c[this.b] == ((Integer) entry.getKey()).intValue() && this.c.d[this.b] == ((Integer) entry.getValue()).intValue();
    }

    @Override // com.android.tools.r8.internal.InterfaceC2300ox
    public final int getIntValue() {
        return this.c.d[this.b];
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return Integer.valueOf(this.c.c[this.b]);
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return Integer.valueOf(this.c.d[this.b]);
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        C3153yx c3153yx = this.c;
        int[] iArr = c3153yx.c;
        int i = this.b;
        return c3153yx.d[i] ^ iArr[i];
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
