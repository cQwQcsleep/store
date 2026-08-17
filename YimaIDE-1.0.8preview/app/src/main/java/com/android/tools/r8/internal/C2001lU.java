package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2001lU implements Map.Entry {
    public int b;
    public final /* synthetic */ C2344pU c;

    public C2001lU(C2344pU c2344pU, int i) {
        this.c = c2344pU;
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
            if (this.c.c[this.b] == entry.getValue()) {
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
        return this.c.c[this.b];
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.c.b[this.b];
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.c.c[this.b];
        return iHashCode ^ (obj2 != null ? System.identityHashCode(obj2) : 0);
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
