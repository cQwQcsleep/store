package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1532fy implements InterfaceC0943Wx, Map.Entry {
    public int b;
    public final /* synthetic */ C1873jy c;

    public C1532fy(C1873jy c1873jy, int i) {
        this.c = c1873jy;
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0943Wx
    public final int a() {
        return this.c.c[this.b];
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (this.c.c[this.b] == ((Integer) entry.getKey()).intValue()) {
            Object obj2 = this.c.d[this.b];
            if (obj2 == null) {
                if (entry.getValue() == null) {
                    return true;
                }
            } else if (obj2.equals(entry.getValue())) {
                return true;
            }
        }
        return false;
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
        C1873jy c1873jy = this.c;
        int[] iArr = c1873jy.c;
        int i = this.b;
        int i2 = iArr[i];
        Object obj = c1873jy.d[i];
        return (obj == null ? 0 : obj.hashCode()) ^ i2;
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
