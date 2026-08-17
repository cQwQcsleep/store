package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class YS extends AbstractC1281d1 {
    public final /* synthetic */ ZS b;

    public YS(ZS zs) {
        this.b = zs;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getValue() != null && (entry.getValue() instanceof Boolean)) {
            Object key = entry.getKey();
            if (this.b.containsKey(key) && this.b.a(key) == ((Boolean) entry.getValue()).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new XS(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getValue() == null || !(entry.getValue() instanceof Boolean)) {
            return false;
        }
        Object key = entry.getKey();
        boolean zBooleanValue = ((Boolean) entry.getValue()).booleanValue();
        int iB = this.b.b(key);
        if (iB == -1) {
            return false;
        }
        ZS zs = this.b;
        if (zBooleanValue != zs.c[iB]) {
            return false;
        }
        int i = (zs.d - iB) - 1;
        Object[] objArr = zs.b;
        int i2 = iB + 1;
        System.arraycopy(objArr, i2, objArr, iB, i);
        boolean[] zArr = this.b.c;
        System.arraycopy(zArr, i2, zArr, iB, i);
        ZS zs2 = this.b;
        int i3 = zs2.d - 1;
        zs2.d = i3;
        zs2.b[i3] = null;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.d;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new XS(this);
    }
}
