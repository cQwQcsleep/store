package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class BT extends AbstractC1281d1 {
    public final /* synthetic */ ET b;

    public BT(ET et) {
        this.b = et;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        Object obj2;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getValue() != null && (entry.getValue() instanceof Long)) {
            Object key = entry.getKey();
            long jLongValue = ((Long) entry.getValue()).longValue();
            ET et = this.b;
            if (key == null) {
                return et.e && et.c[et.f] == jLongValue;
            }
            Object[] objArr = et.b;
            int iA = AbstractC0938Ws.a(key.hashCode()) & this.b.d;
            Object obj3 = objArr[iA];
            if (obj3 == null) {
                return false;
            }
            if (key.equals(obj3)) {
                return this.b.c[iA] == jLongValue;
            }
            do {
                iA = (iA + 1) & this.b.d;
                obj2 = objArr[iA];
                if (obj2 == null) {
                    return false;
                }
            } while (!key.equals(obj2));
            if (this.b.c[iA] == jLongValue) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C3027xT(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getValue() == null || !(entry.getValue() instanceof Long)) {
            return false;
        }
        Object key = entry.getKey();
        long jLongValue = ((Long) entry.getValue()).longValue();
        ET et = this.b;
        if (key == null) {
            if (et.e) {
                long[] jArr = et.c;
                int i = et.f;
                if (jArr[i] == jLongValue) {
                    et.e = false;
                    et.b[i] = null;
                    int i2 = et.h - 1;
                    et.h = i2;
                    if (i2 < et.g / 4 && i > 16) {
                        et.d(i / 2);
                    }
                    return true;
                }
            }
            return false;
        }
        Object[] objArr = et.b;
        int iA = AbstractC0938Ws.a(key.hashCode()) & this.b.d;
        Object obj2 = objArr[iA];
        if (obj2 == null) {
            return false;
        }
        if (obj2.equals(key)) {
            ET et2 = this.b;
            if (et2.c[iA] != jLongValue) {
                return false;
            }
            et2.e(iA);
            return true;
        }
        while (true) {
            iA = (iA + 1) & this.b.d;
            Object obj3 = objArr[iA];
            if (obj3 == null) {
                return false;
            }
            if (obj3.equals(key)) {
                ET et3 = this.b;
                if (et3.c[iA] == jLongValue) {
                    et3.e(iA);
                    return true;
                }
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.h;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C3027xT(this.b);
    }
}
