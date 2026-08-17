package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class R20 implements I20, Map.Entry {
    public int b;
    public final /* synthetic */ V20 c;

    public R20(V20 v20, int i) {
        this.c = v20;
        this.b = i;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return this.c.b[this.b] == entry.getKey() && this.c.c[this.b] == ((Boolean) entry.getValue()).booleanValue();
    }

    @Override // com.android.tools.r8.internal.I20
    public final boolean getBooleanValue() {
        return this.c.c[this.b];
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.c.b[this.b];
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return Boolean.valueOf(this.c.c[this.b]);
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return (this.c.c[this.b] ? 1231 : 1237) ^ System.identityHashCode(this.c.b[this.b]);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean[] zArr = this.c.c;
        int i = this.b;
        boolean z = zArr[i];
        zArr[i] = zBooleanValue;
        return Boolean.valueOf(z);
    }

    public final String toString() {
        return this.c.b[this.b] + "=>" + this.c.c[this.b];
    }
}
