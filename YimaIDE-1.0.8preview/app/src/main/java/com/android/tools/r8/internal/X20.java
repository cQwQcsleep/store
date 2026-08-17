package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class X20 extends AbstractC1281d1 {
    public final /* synthetic */ Y20 b;

    public X20(Y20 y20) {
        this.b = y20;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getValue() != null && (entry.getValue() instanceof Integer)) {
            Object key = entry.getKey();
            if (this.b.containsKey(key) && this.b.b(key) == ((Integer) entry.getValue()).intValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new W20(this);
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
        int iD = this.b.d(key);
        if (iD == -1) {
            return false;
        }
        Y20 y20 = this.b;
        if (iIntValue != y20.d[iD]) {
            return false;
        }
        int i = (y20.e - iD) - 1;
        Object[] objArr = y20.c;
        int i2 = iD + 1;
        System.arraycopy(objArr, i2, objArr, iD, i);
        int[] iArr = this.b.d;
        System.arraycopy(iArr, i2, iArr, iD, i);
        Y20 y21 = this.b;
        int i3 = y21.e - 1;
        y21.e = i3;
        y21.c[i3] = null;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.e;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new W20(this);
    }
}
