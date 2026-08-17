package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2898vx extends AbstractC1281d1 {
    public final /* synthetic */ C3153yx b;

    public C2898vx(C3153yx c3153yx) {
        this.b = c3153yx;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        C3153yx c3153yx;
        int i;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() != null && (entry.getKey() instanceof Integer) && entry.getValue() != null && (entry.getValue() instanceof Integer)) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            C3153yx c3153yx2 = this.b;
            if (iIntValue == 0) {
                return c3153yx2.f && c3153yx2.d[c3153yx2.g] == iIntValue2;
            }
            int[] iArr = c3153yx2.c;
            int iA = AbstractC0938Ws.a(iIntValue);
            C3153yx c3153yx3 = this.b;
            int i2 = iA & c3153yx3.e;
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            if (iIntValue == i3) {
                return c3153yx3.d[i2] == iIntValue2;
            }
            do {
                c3153yx = this.b;
                i2 = (i2 + 1) & c3153yx.e;
                i = iArr[i2];
                if (i == 0) {
                    return false;
                }
            } while (iIntValue != i);
            if (c3153yx.d[i2] == iIntValue2) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C2556rx(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() == null || !(entry.getKey() instanceof Integer) || entry.getValue() == null || !(entry.getValue() instanceof Integer)) {
            return false;
        }
        int iIntValue = ((Integer) entry.getKey()).intValue();
        int iIntValue2 = ((Integer) entry.getValue()).intValue();
        C3153yx c3153yx = this.b;
        if (iIntValue == 0) {
            if (c3153yx.f) {
                int[] iArr = c3153yx.d;
                int i = c3153yx.g;
                if (iArr[i] == iIntValue2) {
                    c3153yx.f = false;
                    int i2 = c3153yx.i - 1;
                    c3153yx.i = i2;
                    if (i2 < c3153yx.h / 4 && i > 16) {
                        c3153yx.e(i / 2);
                    }
                    return true;
                }
            }
            return false;
        }
        int[] iArr2 = c3153yx.c;
        int iA = AbstractC0938Ws.a(iIntValue);
        C3153yx c3153yx2 = this.b;
        int i3 = iA & c3153yx2.e;
        int i4 = iArr2[i3];
        if (i4 == 0) {
            return false;
        }
        if (i4 == iIntValue) {
            if (c3153yx2.d[i3] != iIntValue2) {
                return false;
            }
            c3153yx2.f(i3);
            return true;
        }
        while (true) {
            C3153yx c3153yx3 = this.b;
            i3 = (i3 + 1) & c3153yx3.e;
            int i5 = iArr2[i3];
            if (i5 == 0) {
                return false;
            }
            if (i5 == iIntValue && c3153yx3.d[i3] == iIntValue2) {
                c3153yx3.f(i3);
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
        return new C2556rx(this.b);
    }
}
