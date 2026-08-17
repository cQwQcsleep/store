package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class JT implements OT, Map.Entry {
    public int b;
    public final /* synthetic */ NT c;

    public JT(NT nt, int i) {
        this.c = nt;
        this.b = i;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        NT nt = this.c;
        if (nt.g.a(nt.c[this.b], entry.getKey())) {
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
        return this.c.c[this.b];
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.c.d[this.b];
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        NT nt = this.c;
        int iA = nt.g.a(nt.c[this.b]);
        Object obj = this.c.d[this.b];
        return (obj == null ? 0 : obj.hashCode()) ^ iA;
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
