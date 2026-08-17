package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2685tU extends AbstractC1281d1 implements Serializable {
    public transient Object[] b;
    public int c;

    public C2685tU(int i, Object[] objArr) {
        this.b = objArr;
        this.c = i;
        if (i <= objArr.length) {
            return;
        }
        w01.a(AbstractC2181nb0.a(objArr.length, ")", Ni0.a(i, "The provided size (", ") is larger than or equal to the array size (")));
        throw null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        if (c(obj) != -1) {
            return false;
        }
        int i = this.c;
        if (i == this.b.length) {
            Object[] objArr = new Object[i == 0 ? 2 : i * 2];
            while (true) {
                int i2 = i - 1;
                if (i == 0) {
                    break;
                }
                objArr[i2] = this.b[i2];
                i = i2;
            }
            this.b = objArr;
        }
        Object[] objArr2 = this.b;
        int i3 = this.c;
        this.c = i3 + 1;
        objArr2[i3] = obj;
        return true;
    }

    public final int c(Object obj) {
        int i = this.c;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return -1;
            }
            Object obj2 = this.b[i2];
            if (obj2 == null) {
                if (obj == null) {
                    return i2;
                }
                i = i2;
            } else {
                if (obj2.equals(obj)) {
                    return i2;
                }
                i = i2;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        Arrays.fill(this.b, 0, this.c, (Object) null);
        this.c = 0;
    }

    public final Object clone() {
        try {
            C2685tU c2685tU = (C2685tU) super.clone();
            c2685tU.b = (Object[]) this.b.clone();
            return c2685tU;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return c(obj) != -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.c == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C2599sU(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int iC = c(obj);
        if (iC == -1) {
            return false;
        }
        int i = (this.c - iC) - 1;
        for (int i2 = 0; i2 < i; i2++) {
            Object[] objArr = this.b;
            int i3 = iC + i2;
            objArr[i3] = objArr[i3 + 1];
        }
        int i4 = this.c - 1;
        this.c = i4;
        this.b[i4] = null;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.c;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C2599sU(this);
    }
}
