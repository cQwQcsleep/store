package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class G30 implements Map.Entry {
    public int b;
    public final /* synthetic */ K30 c;

    public G30(K30 k30, int i) {
        this.c = k30;
        this.b = i;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return this.c.b[this.b] == entry.getKey() && this.c.c[this.b] == entry.getValue();
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.c.b[this.b];
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.c.c[this.b];
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        int iIdentityHashCode = System.identityHashCode(this.c.b[this.b]);
        Object obj = this.c.c[this.b];
        return (obj == null ? 0 : System.identityHashCode(obj)) ^ iIdentityHashCode;
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
