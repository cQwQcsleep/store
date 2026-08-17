package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Yy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0996Yy extends AbstractC1281d1 {
    public final /* synthetic */ C1022Zy b;

    public C0996Yy(C1022Zy c1022Zy) {
        this.b = c1022Zy;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() != null && (entry.getKey() instanceof Integer)) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            if (this.b.a(iIntValue) && this.b.get(iIntValue) == entry.getValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C0970Xy(this);
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
        int iD = this.b.d(iIntValue);
        if (iD == -1) {
            return false;
        }
        C1022Zy c1022Zy = this.b;
        if (value != c1022Zy.d[iD]) {
            return false;
        }
        int i = (c1022Zy.e - iD) - 1;
        int[] iArr = c1022Zy.c;
        int i2 = iD + 1;
        System.arraycopy(iArr, i2, iArr, iD, i);
        Object[] objArr = this.b.d;
        System.arraycopy(objArr, i2, objArr, iD, i);
        C1022Zy c1022Zy2 = this.b;
        int i3 = c1022Zy2.e - 1;
        c1022Zy2.e = i3;
        c1022Zy2.d[i3] = null;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.e;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C0970Xy(this);
    }
}
