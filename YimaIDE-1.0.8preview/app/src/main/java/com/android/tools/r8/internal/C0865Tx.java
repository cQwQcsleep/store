package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Tx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0865Tx extends AbstractC1281d1 {
    public final /* synthetic */ C0891Ux b;

    public C0865Tx(C0891Ux c0891Ux) {
        this.b = c0891Ux;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() != null && (entry.getKey() instanceof Integer)) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            if (this.b.a(iIntValue)) {
                if (this.b.get(iIntValue) == null) {
                    if (entry.getValue() == null) {
                        return true;
                    }
                } else if (this.b.get(iIntValue).equals(entry.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C0839Sx(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() != null && (entry.getKey() instanceof Integer)) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            Object value = entry.getValue();
            int iD = this.b.d(iIntValue);
            if (iD != -1) {
                C0891Ux c0891Ux = this.b;
                if (value != null ? value.equals(c0891Ux.d[iD]) : c0891Ux.d[iD] == null) {
                    C0891Ux c0891Ux2 = this.b;
                    int i = (c0891Ux2.e - iD) - 1;
                    int[] iArr = c0891Ux2.c;
                    int i2 = iD + 1;
                    System.arraycopy(iArr, i2, iArr, iD, i);
                    Object[] objArr = this.b.d;
                    System.arraycopy(objArr, i2, objArr, iD, i);
                    C0891Ux c0891Ux3 = this.b;
                    int i3 = c0891Ux3.e - 1;
                    c0891Ux3.e = i3;
                    c0891Ux3.d[i3] = null;
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.e;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C0839Sx(this);
    }
}
