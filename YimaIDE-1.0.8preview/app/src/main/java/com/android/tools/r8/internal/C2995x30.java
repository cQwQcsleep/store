package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.x30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2995x30 implements Map.Entry {
    public int b;
    public final /* synthetic */ B30 c;

    public C2995x30(B30 b30, int i) {
        this.c = b30;
        this.b = i;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return this.c.b[this.b] == entry.getKey() && this.c.c[this.b] == ((Long) entry.getValue()).longValue();
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
        int iIdentityHashCode = System.identityHashCode(this.c.b[this.b]);
        long j = this.c.c[this.b];
        return ((int) (j ^ (j >>> 32))) ^ iIdentityHashCode;
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
