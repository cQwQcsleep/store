package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Mc0 implements Map.Entry, Comparable {
    public final Comparable b;
    public Object c;
    public final /* synthetic */ Rc0 d;

    public Mc0(Rc0 rc0, Map.Entry entry) {
        Comparable comparable = (Comparable) entry.getKey();
        Object value = entry.getValue();
        this.d = rc0;
        this.b = comparable;
        this.c = value;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.b.compareTo(((Mc0) obj).b);
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        boolean zEquals;
        boolean zEquals2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Comparable comparable = this.b;
        Object key = entry.getKey();
        if (comparable == null) {
            zEquals = key == null;
        } else {
            zEquals = comparable.equals(key);
        }
        if (zEquals) {
            Object obj2 = this.c;
            Object value = entry.getValue();
            if (obj2 == null) {
                zEquals2 = value == null;
            } else {
                zEquals2 = obj2.equals(value);
            }
            if (zEquals2) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.b;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.c;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.b;
        int iHashCode = comparable == null ? 0 : comparable.hashCode();
        Object obj = this.c;
        return iHashCode ^ (obj != null ? obj.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        this.d.e();
        Object obj2 = this.c;
        this.c = obj;
        return obj2;
    }

    public final String toString() {
        return this.b + "=" + this.c;
    }

    public Mc0(Rc0 rc0, Comparable comparable, Object obj) {
        this.d = rc0;
        this.b = comparable;
        this.c = obj;
    }
}
