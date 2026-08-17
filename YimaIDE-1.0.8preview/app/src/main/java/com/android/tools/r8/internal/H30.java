package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H30 extends AbstractC1281d1 {
    public final /* synthetic */ K30 b;

    public H30(K30 k30) {
        this.b = k30;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        K30 k30;
        Object obj2;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        K30 k31 = this.b;
        if (key == null) {
            return k31.e && k31.c[k31.f] == value;
        }
        Object[] objArr = k31.b;
        int iA = AbstractC0938Ws.a(System.identityHashCode(key));
        K30 k32 = this.b;
        int i = iA & k32.d;
        Object obj3 = objArr[i];
        if (obj3 == null) {
            return false;
        }
        if (key == obj3) {
            return k32.c[i] == value;
        }
        do {
            k30 = this.b;
            i = (i + 1) & k30.d;
            obj2 = objArr[i];
            if (obj2 == null) {
                return false;
            }
        } while (key != obj2);
        return k30.c[i] == value;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new D30(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        K30 k30 = this.b;
        if (key == null) {
            if (k30.e) {
                Object[] objArr = k30.c;
                int i = k30.f;
                if (objArr[i] == value) {
                    k30.e = false;
                    k30.b[i] = null;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    int i2 = k30.h - 1;
                    k30.h = i2;
                    if (i2 < k30.g / 4 && i > 16) {
                        k30.d(i / 2);
                    }
                    return true;
                }
            }
            return false;
        }
        Object[] objArr2 = k30.b;
        int iA = AbstractC0938Ws.a(System.identityHashCode(key));
        K30 k31 = this.b;
        int i3 = iA & k31.d;
        Object obj3 = objArr2[i3];
        if (obj3 == null) {
            return false;
        }
        if (obj3 == key) {
            if (k31.c[i3] != value) {
                return false;
            }
            k31.e(i3);
            return true;
        }
        while (true) {
            K30 k32 = this.b;
            i3 = (i3 + 1) & k32.d;
            Object obj4 = objArr2[i3];
            if (obj4 == null) {
                return false;
            }
            if (obj4 == key && k32.c[i3] == value) {
                k32.e(i3);
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
        return new D30(this.b);
    }
}
