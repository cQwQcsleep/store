package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1533fz implements InterfaceC1959kz, Map.Entry {
    public int b;
    public final /* synthetic */ C1874jz c;

    public C1533fz(C1874jz c1874jz, int i) {
        this.c = c1874jz;
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1959kz
    public final int a() {
        return this.c.c[this.b];
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return this.c.c[this.b] == ((Integer) entry.getKey()).intValue() && this.c.d[this.b] == entry.getValue();
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return Integer.valueOf(this.c.c[this.b]);
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.c.d[this.b];
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        C1874jz c1874jz = this.c;
        int[] iArr = c1874jz.c;
        int i = this.b;
        int i2 = iArr[i];
        Object obj = c1874jz.d[i];
        return (obj == null ? 0 : System.identityHashCode(obj)) ^ i2;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object[] objArr = this.c.d;
        int i = this.b;
        Object obj2 = objArr[i];
        objArr[i] = obj;
        return obj2;
    }

    public final String toString() {
        return this.c.c[this.b] + "=>" + this.c.d[this.b];
    }
}
