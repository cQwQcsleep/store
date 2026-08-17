package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class AT implements Map.Entry {
    public int b;
    public final /* synthetic */ ET c;

    public AT(ET et, int i) {
        this.c = et;
        this.b = i;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.c.b[this.b];
        if (obj2 != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) {
            if (this.c.c[this.b] == ((Long) entry.getValue()).longValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.c.b[this.b];
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return Long.valueOf(this.c.c[this.b]);
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.c.b[this.b];
        int iHashCode = obj == null ? 0 : obj.hashCode();
        long j = this.c.c[this.b];
        return ((int) (j ^ (j >>> 32))) ^ iHashCode;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        long jLongValue = ((Long) obj).longValue();
        long[] jArr = this.c.c;
        int i = this.b;
        long j = jArr[i];
        jArr[i] = jLongValue;
        return Long.valueOf(j);
    }

    public final String toString() {
        return this.c.b[this.b] + "=>" + this.c.c[this.b];
    }
}
