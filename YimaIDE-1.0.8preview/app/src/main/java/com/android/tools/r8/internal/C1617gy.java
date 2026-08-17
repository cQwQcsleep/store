package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1617gy extends AbstractC1281d1 {
    public final /* synthetic */ C1873jy b;

    public C1617gy(C1873jy c1873jy) {
        this.b = c1873jy;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        C1873jy c1873jy;
        int i;
        Object obj2;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() == null || !(entry.getKey() instanceof Integer)) {
            return false;
        }
        int iIntValue = ((Integer) entry.getKey()).intValue();
        Object value = entry.getValue();
        C1873jy c1873jy2 = this.b;
        if (iIntValue == 0) {
            return c1873jy2.f && ((obj2 = c1873jy2.d[c1873jy2.g]) != null ? obj2.equals(value) : value == null);
        }
        int[] iArr = c1873jy2.c;
        int iA = AbstractC0938Ws.a(iIntValue);
        C1873jy c1873jy3 = this.b;
        int i2 = iA & c1873jy3.e;
        int i3 = iArr[i2];
        if (i3 == 0) {
            return false;
        }
        if (iIntValue == i3) {
            Object obj3 = c1873jy3.d[i2];
            if (obj3 == null) {
                return value == null;
            }
            return obj3.equals(value);
        }
        do {
            c1873jy = this.b;
            i2 = (i2 + 1) & c1873jy.e;
            i = iArr[i2];
            if (i == 0) {
                return false;
            }
        } while (iIntValue != i);
        Object obj4 = c1873jy.d[i2];
        if (obj4 == null) {
            return value == null;
        }
        return obj4.equals(value);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C1276cy(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        Object obj2;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() == null || !(entry.getKey() instanceof Integer)) {
            return false;
        }
        int iIntValue = ((Integer) entry.getKey()).intValue();
        Object value = entry.getValue();
        C1873jy c1873jy = this.b;
        if (iIntValue == 0) {
            if (!c1873jy.f || ((obj2 = c1873jy.d[c1873jy.g]) != null ? !obj2.equals(value) : value != null)) {
                return false;
            }
            C1873jy c1873jy2 = this.b;
            c1873jy2.f = false;
            Object[] objArr = c1873jy2.d;
            int i = c1873jy2.g;
            Object obj3 = objArr[i];
            objArr[i] = null;
            int i2 = c1873jy2.i - 1;
            c1873jy2.i = i2;
            if (i2 < c1873jy2.h / 4 && i > 16) {
                c1873jy2.d(i / 2);
            }
            return true;
        }
        int[] iArr = c1873jy.c;
        int iA = AbstractC0938Ws.a(iIntValue);
        C1873jy c1873jy3 = this.b;
        int i3 = iA & c1873jy3.e;
        int i4 = iArr[i3];
        if (i4 == 0) {
            return false;
        }
        if (i4 == iIntValue) {
            Object obj4 = c1873jy3.d[i3];
            if (obj4 != null ? !obj4.equals(value) : value != null) {
                return false;
            }
            this.b.e(i3);
            return true;
        }
        while (true) {
            C1873jy c1873jy4 = this.b;
            i3 = (i3 + 1) & c1873jy4.e;
            int i5 = iArr[i3];
            if (i5 == 0) {
                return false;
            }
            if (i5 == iIntValue) {
                Object obj5 = c1873jy4.d[i3];
                if (obj5 == null) {
                    if (value == null) {
                        this.b.e(i3);
                        return true;
                    }
                } else if (obj5.equals(value)) {
                    this.b.e(i3);
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
        return new C1276cy(this.b);
    }
}
