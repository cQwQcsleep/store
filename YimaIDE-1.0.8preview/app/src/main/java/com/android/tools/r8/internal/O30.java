package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O30 extends AbstractC3075y1 implements Serializable {
    public transient Object[] b;
    public int c;

    public O30(int i, Object[] objArr) {
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
        int i;
        int i2 = this.c;
        while (true) {
            i = i2 - 1;
            if (i2 == 0) {
                i = -1;
                break;
            }
            if (this.b[i] == obj) {
                break;
            }
            i2 = i;
        }
        if (i != -1) {
            return false;
        }
        int i3 = this.c;
        if (i3 == this.b.length) {
            Object[] objArr = new Object[i3 == 0 ? 2 : i3 * 2];
            while (true) {
                int i4 = i3 - 1;
                if (i3 == 0) {
                    break;
                }
                objArr[i4] = this.b[i4];
                i3 = i4;
            }
            this.b = objArr;
        }
        Object[] objArr2 = this.b;
        int i5 = this.c;
        this.c = i5 + 1;
        objArr2[i5] = obj;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        Arrays.fill(this.b, 0, this.c, (Object) null);
        this.c = 0;
    }

    public final Object clone() {
        try {
            O30 o30 = (O30) super.clone();
            o30.b = (Object[]) this.b.clone();
            return o30;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        int i;
        int i2 = this.c;
        while (true) {
            i = i2 - 1;
            if (i2 == 0) {
                i = -1;
                break;
            }
            if (this.b[i] == obj) {
                break;
            }
            i2 = i;
        }
        return i != -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.c == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.P30
    public final BU iterator() {
        return new N30(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int i;
        int i2 = this.c;
        while (true) {
            i = i2 - 1;
            if (i2 == 0) {
                i = -1;
                break;
            }
            if (this.b[i] == obj) {
                break;
            }
            i2 = i;
        }
        if (i == -1) {
            return false;
        }
        int i3 = (this.c - i) - 1;
        for (int i4 = 0; i4 < i3; i4++) {
            Object[] objArr = this.b;
            int i5 = i + i4;
            objArr[i5] = objArr[i5 + 1];
        }
        int i6 = this.c - 1;
        this.c = i6;
        this.b[i6] = null;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.c;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new N30(this);
    }
}
