package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class S20 extends AbstractC1281d1 {
    public final /* synthetic */ V20 b;

    public S20(V20 v20) {
        this.b = v20;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        V20 v20;
        Object obj2;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getValue() != null && (entry.getValue() instanceof Boolean)) {
            Object key = entry.getKey();
            boolean zBooleanValue = ((Boolean) entry.getValue()).booleanValue();
            V20 v21 = this.b;
            if (key == null) {
                return v21.e && v21.c[v21.f] == zBooleanValue;
            }
            Object[] objArr = v21.b;
            int iA = AbstractC0938Ws.a(System.identityHashCode(key));
            V20 v22 = this.b;
            int i = iA & v22.d;
            Object obj3 = objArr[i];
            if (obj3 == null) {
                return false;
            }
            if (key == obj3) {
                return v22.c[i] == zBooleanValue;
            }
            do {
                v20 = this.b;
                i = (i + 1) & v20.d;
                obj2 = objArr[i];
                if (obj2 == null) {
                    return false;
                }
            } while (key != obj2);
            if (v20.c[i] == zBooleanValue) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new O20(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getValue() == null || !(entry.getValue() instanceof Boolean)) {
            return false;
        }
        Object key = entry.getKey();
        boolean zBooleanValue = ((Boolean) entry.getValue()).booleanValue();
        V20 v20 = this.b;
        if (key == null) {
            if (v20.e) {
                boolean[] zArr = v20.c;
                int i = v20.f;
                if (zArr[i] == zBooleanValue) {
                    v20.e = false;
                    v20.b[i] = null;
                    int i2 = v20.h - 1;
                    v20.h = i2;
                    if (i2 < v20.g / 4 && i > 16) {
                        v20.d(i / 2);
                    }
                    return true;
                }
            }
            return false;
        }
        Object[] objArr = v20.b;
        int iA = AbstractC0938Ws.a(System.identityHashCode(key));
        V20 v21 = this.b;
        int i3 = iA & v21.d;
        Object obj2 = objArr[i3];
        if (obj2 == null) {
            return false;
        }
        if (obj2 == key) {
            if (v21.c[i3] != zBooleanValue) {
                return false;
            }
            v21.e(i3);
            return true;
        }
        while (true) {
            V20 v22 = this.b;
            i3 = (i3 + 1) & v22.d;
            Object obj3 = objArr[i3];
            if (obj3 == null) {
                return false;
            }
            if (obj3 == key && v22.c[i3] == zBooleanValue) {
                v22.e(i3);
                return true;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.h;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new O20(this.b);
    }
}
