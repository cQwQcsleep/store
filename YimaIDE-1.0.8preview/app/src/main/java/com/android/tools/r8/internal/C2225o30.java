package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.o30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2225o30 extends AbstractC1281d1 {
    public final /* synthetic */ C2481r30 b;

    public C2225o30(C2481r30 c2481r30) {
        this.b = c2481r30;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        C2481r30 c2481r30;
        Object obj2;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getValue() != null && (entry.getValue() instanceof Integer)) {
            Object key = entry.getKey();
            int iIntValue = ((Integer) entry.getValue()).intValue();
            C2481r30 c2481r31 = this.b;
            if (key == null) {
                return c2481r31.f && c2481r31.d[c2481r31.g] == iIntValue;
            }
            Object[] objArr = c2481r31.c;
            int iA = AbstractC0938Ws.a(System.identityHashCode(key));
            C2481r30 c2481r32 = this.b;
            int i = iA & c2481r32.e;
            Object obj3 = objArr[i];
            if (obj3 == null) {
                return false;
            }
            if (key == obj3) {
                return c2481r32.d[i] == iIntValue;
            }
            do {
                c2481r30 = this.b;
                i = (i + 1) & c2481r30.e;
                obj2 = objArr[i];
                if (obj2 == null) {
                    return false;
                }
            } while (key != obj2);
            if (c2481r30.d[i] == iIntValue) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C1882k30(this.b);
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
        C2481r30 c2481r30 = this.b;
        if (key == null) {
            if (c2481r30.f) {
                int[] iArr = c2481r30.d;
                int i = c2481r30.g;
                if (iArr[i] == iIntValue) {
                    c2481r30.f = false;
                    c2481r30.c[i] = null;
                    int i2 = c2481r30.i - 1;
                    c2481r30.i = i2;
                    if (i2 < c2481r30.h / 4 && i > 16) {
                        c2481r30.e(i / 2);
                    }
                    return true;
                }
            }
            return false;
        }
        Object[] objArr = c2481r30.c;
        int iA = AbstractC0938Ws.a(System.identityHashCode(key));
        C2481r30 c2481r31 = this.b;
        int i3 = iA & c2481r31.e;
        Object obj2 = objArr[i3];
        if (obj2 == null) {
            return false;
        }
        if (obj2 == key) {
            if (c2481r31.d[i3] != iIntValue) {
                return false;
            }
            c2481r31.f(i3);
            return true;
        }
        while (true) {
            C2481r30 c2481r32 = this.b;
            i3 = (i3 + 1) & c2481r32.e;
            Object obj3 = objArr[i3];
            if (obj3 == null) {
                return false;
            }
            if (obj3 == key && c2481r32.d[i3] == iIntValue) {
                c2481r32.f(i3);
                return true;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C1882k30(this.b);
    }
}
