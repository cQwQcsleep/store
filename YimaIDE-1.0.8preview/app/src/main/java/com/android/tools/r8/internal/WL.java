package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class WL extends AbstractC1281d1 {
    public final /* synthetic */ ZL b;

    public WL(ZL zl) {
        this.b = zl;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        ZL zl;
        long j;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() != null && (entry.getKey() instanceof Long)) {
            long jLongValue = ((Long) entry.getKey()).longValue();
            Object value = entry.getValue();
            ZL zl2 = this.b;
            if (jLongValue == 0) {
                return zl2.e && zl2.c[zl2.f] == value;
            }
            long[] jArr = zl2.b;
            int iA = (int) AbstractC0938Ws.a(jLongValue);
            ZL zl3 = this.b;
            int i = iA & zl3.d;
            long j2 = jArr[i];
            if (j2 == 0) {
                return false;
            }
            if (jLongValue == j2) {
                return zl3.c[i] == value;
            }
            do {
                zl = this.b;
                i = (i + 1) & zl.d;
                j = jArr[i];
                if (j == 0) {
                    return false;
                }
            } while (jLongValue != j);
            if (zl.c[i] == value) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new SL(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() == null || !(entry.getKey() instanceof Long)) {
            return false;
        }
        long jLongValue = ((Long) entry.getKey()).longValue();
        Object value = entry.getValue();
        ZL zl = this.b;
        if (jLongValue == 0) {
            if (zl.e) {
                Object[] objArr = zl.c;
                int i = zl.f;
                if (objArr[i] == value) {
                    zl.e = false;
                    objArr[i] = null;
                    int i2 = zl.h - 1;
                    zl.h = i2;
                    if (i2 < zl.g / 4 && i > 16) {
                        zl.d(i / 2);
                    }
                    return true;
                }
            }
            return false;
        }
        long[] jArr = zl.b;
        int iA = (int) AbstractC0938Ws.a(jLongValue);
        ZL zl2 = this.b;
        int i3 = iA & zl2.d;
        long j = jArr[i3];
        if (j == 0) {
            return false;
        }
        if (j == jLongValue) {
            if (zl2.c[i3] != value) {
                return false;
            }
            zl2.e(i3);
            return true;
        }
        while (true) {
            ZL zl3 = this.b;
            i3 = (i3 + 1) & zl3.d;
            long j2 = jArr[i3];
            if (j2 == 0) {
                return false;
            }
            if (j2 == jLongValue && zl3.c[i3] == value) {
                zl3.e(i3);
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
        return new SL(this.b);
    }
}
