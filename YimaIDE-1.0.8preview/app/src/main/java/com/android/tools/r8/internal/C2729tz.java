package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2729tz extends AbstractC1281d1 {
    public final /* synthetic */ C2986wz b;

    public C2729tz(C2986wz c2986wz) {
        this.b = c2986wz;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        C2986wz c2986wz;
        int i;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() != null && (entry.getKey() instanceof Integer)) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            Object value = entry.getValue();
            C2986wz c2986wz2 = this.b;
            if (iIntValue == 0) {
                return c2986wz2.f && c2986wz2.d[c2986wz2.g] == value;
            }
            int[] iArr = c2986wz2.c;
            int iA = AbstractC0938Ws.a(iIntValue);
            C2986wz c2986wz3 = this.b;
            int i2 = iA & c2986wz3.e;
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            if (iIntValue == i3) {
                return c2986wz3.d[i2] == value;
            }
            do {
                c2986wz = this.b;
                i2 = (i2 + 1) & c2986wz.e;
                i = iArr[i2];
                if (i == 0) {
                    return false;
                }
            } while (iIntValue != i);
            if (c2986wz.d[i2] == value) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C2388pz(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() == null || !(entry.getKey() instanceof Integer)) {
            return false;
        }
        int iIntValue = ((Integer) entry.getKey()).intValue();
        Object value = entry.getValue();
        C2986wz c2986wz = this.b;
        if (iIntValue == 0) {
            if (c2986wz.f) {
                Object[] objArr = c2986wz.d;
                int i = c2986wz.g;
                if (objArr[i] == value) {
                    c2986wz.f = false;
                    objArr[i] = null;
                    int i2 = c2986wz.i - 1;
                    c2986wz.i = i2;
                    if (i2 < c2986wz.h / 4 && i > 16) {
                        c2986wz.d(i / 2);
                    }
                    return true;
                }
            }
            return false;
        }
        int[] iArr = c2986wz.c;
        int iA = AbstractC0938Ws.a(iIntValue);
        C2986wz c2986wz2 = this.b;
        int i3 = iA & c2986wz2.e;
        int i4 = iArr[i3];
        if (i4 == 0) {
            return false;
        }
        if (i4 == iIntValue) {
            if (c2986wz2.d[i3] != value) {
                return false;
            }
            c2986wz2.e(i3);
            return true;
        }
        while (true) {
            C2986wz c2986wz3 = this.b;
            i3 = (i3 + 1) & c2986wz3.e;
            int i5 = iArr[i3];
            if (i5 == 0) {
                return false;
            }
            if (i5 == iIntValue && c2986wz3.d[i3] == value) {
                c2986wz3.e(i3);
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
        return new C2388pz(this.b);
    }
}
