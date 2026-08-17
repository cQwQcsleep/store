package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2598sT extends AbstractC1281d1 {
    public final /* synthetic */ C2855vT b;

    public C2598sT(C2855vT c2855vT) {
        this.b = c2855vT;
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
        if (entry.getValue() != null && (entry.getValue() instanceof Integer)) {
            Object key = entry.getKey();
            int iIntValue = ((Integer) entry.getValue()).intValue();
            C2855vT c2855vT = this.b;
            if (key == null) {
                return c2855vT.f && c2855vT.d[c2855vT.g] == iIntValue;
            }
            Object[] objArr = c2855vT.c;
            int iA = AbstractC0938Ws.a(key.hashCode()) & this.b.e;
            Object obj3 = objArr[iA];
            if (obj3 == null) {
                return false;
            }
            if (key.equals(obj3)) {
                return this.b.d[iA] == iIntValue;
            }
            do {
                iA = (iA + 1) & this.b.e;
                obj2 = objArr[iA];
                if (obj2 == null) {
                    return false;
                }
            } while (!key.equals(obj2));
            if (this.b.d[iA] == iIntValue) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C2257oT(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getValue() == null || !(entry.getValue() instanceof Integer)) {
            return false;
        }
        Object key = entry.getKey();
        int iIntValue = ((Integer) entry.getValue()).intValue();
        C2855vT c2855vT = this.b;
        if (key == null) {
            if (c2855vT.f) {
                int[] iArr = c2855vT.d;
                int i = c2855vT.g;
                if (iArr[i] == iIntValue) {
                    c2855vT.f = false;
                    c2855vT.c[i] = null;
                    int i2 = c2855vT.i - 1;
                    c2855vT.i = i2;
                    if (i2 < c2855vT.h / 4 && i > 16) {
                        c2855vT.e(i / 2);
                    }
                    return true;
                }
            }
            return false;
        }
        Object[] objArr = c2855vT.c;
        int iA = AbstractC0938Ws.a(key.hashCode()) & this.b.e;
        Object obj2 = objArr[iA];
        if (obj2 == null) {
            return false;
        }
        if (obj2.equals(key)) {
            C2855vT c2855vT2 = this.b;
            if (c2855vT2.d[iA] != iIntValue) {
                return false;
            }
            c2855vT2.f(iA);
            return true;
        }
        while (true) {
            iA = (iA + 1) & this.b.e;
            Object obj3 = objArr[iA];
            if (obj3 == null) {
                return false;
            }
            if (obj3.equals(key)) {
                C2855vT c2855vT3 = this.b;
                if (c2855vT3.d[iA] == iIntValue) {
                    c2855vT3.f(iA);
                    return true;
                }
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C2257oT(this.b);
    }
}
