package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class VL implements Map.Entry {
    public int b;
    public final /* synthetic */ ZL c;

    public VL(ZL zl, int i) {
        this.c = zl;
        this.b = i;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return this.c.b[this.b] == ((Long) entry.getKey()).longValue() && this.c.c[this.b] == entry.getValue();
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return Long.valueOf(this.c.b[this.b]);
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.c.c[this.b];
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        ZL zl = this.c;
        long[] jArr = zl.b;
        int i = this.b;
        long j = jArr[i];
        int i2 = (int) (j ^ (j >>> 32));
        Object obj = zl.c[i];
        return (obj == null ? 0 : System.identityHashCode(obj)) ^ i2;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object[] objArr = this.c.c;
        int i = this.b;
        Object obj2 = objArr[i];
        objArr[i] = obj;
        return obj2;
    }

    public final String toString() {
        return this.c.b[this.b] + "=>" + this.c.c[this.b];
    }
}
