package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class AK implements Map.Entry {
    public AK b;
    public AK c;
    public AK d;
    public AK e;
    public AK f;
    public final Object g;
    public final boolean h;
    public Object i;
    public int j;

    public AK(boolean z, AK ak, Object obj, AK ak2, AK ak3) {
        this.b = ak;
        this.g = obj;
        this.h = z;
        this.j = 1;
        this.e = ak2;
        this.f = ak3;
        ak3.e = this;
        ak2.f = this;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = this.g;
            if (obj2 != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) {
                Object obj3 = this.i;
                if (obj3 == null) {
                    if (entry.getValue() == null) {
                        return true;
                    }
                } else if (obj3.equals(entry.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.g;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.i;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.g;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.i;
        return iHashCode ^ (obj2 != null ? obj2.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (obj == null && !this.h) {
            x0e.a("value == null");
            return null;
        }
        Object obj2 = this.i;
        this.i = obj;
        return obj2;
    }

    public final String toString() {
        return this.g + "=" + this.i;
    }

    public AK(boolean z) {
        this.g = null;
        this.h = z;
        this.f = this;
        this.e = this;
    }
}
