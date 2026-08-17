package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1275cx extends AbstractC1281d1 {
    public final /* synthetic */ C1359dx b;

    public C1275cx(C1359dx c1359dx) {
        this.b = c1359dx;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() != null && (entry.getKey() instanceof Integer) && entry.getValue() != null && (entry.getValue() instanceof Integer)) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            if (this.b.a(iIntValue) && this.b.get(iIntValue) == ((Integer) entry.getValue()).intValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C1193bx(this);
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
        int iE = this.b.e(iIntValue);
        if (iE == -1) {
            return false;
        }
        C1359dx c1359dx = this.b;
        if (iIntValue2 != c1359dx.d[iE]) {
            return false;
        }
        int i = (c1359dx.e - iE) - 1;
        int[] iArr = c1359dx.c;
        int i2 = iE + 1;
        System.arraycopy(iArr, i2, iArr, iE, i);
        int[] iArr2 = this.b.d;
        System.arraycopy(iArr2, i2, iArr2, iE, i);
        this.b.e--;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.e;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C1193bx(this);
    }
}
