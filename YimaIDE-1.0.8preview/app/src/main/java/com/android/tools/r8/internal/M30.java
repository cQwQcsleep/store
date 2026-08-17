package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class M30 extends AbstractC2990x1 implements RandomAccess, Cloneable, Serializable {
    public transient Object[] b;
    public int c;

    public M30(int i) {
        if (i >= 0) {
            this.b = new Object[i];
        } else {
            w01.a(AbstractC1784iv.a(i, "Initial capacity (", ") is negative"));
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.S30
    public final void a(int i, int i2) {
        S3.a(this.c, i, i2);
        Object[] objArr = this.b;
        System.arraycopy(objArr, i2, objArr, i, this.c - i2);
        int i3 = i2 - i;
        this.c -= i3;
        while (true) {
            int i4 = i3 - 1;
            if (i3 == 0) {
                return;
            }
            this.b[this.c + i4] = null;
            i3 = i4;
        }
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        j(i);
        int i2 = this.c + 1;
        Object[] objArr = this.b;
        if (i2 > objArr.length) {
            Object[] objArr2 = new Object[(int) Math.max(Math.min(((long) objArr.length) * 2, 2147483639L), i2)];
            System.arraycopy(this.b, 0, objArr2, 0, this.c);
            this.b = objArr2;
        }
        int i3 = this.c;
        if (i != i3) {
            Object[] objArr3 = this.b;
            System.arraycopy(objArr3, i, objArr3, i + 1, i3 - i);
        }
        this.b[i] = obj;
        this.c++;
    }

    @Override // com.android.tools.r8.internal.AbstractC2990x1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        Arrays.fill(this.b, 0, this.c, (Object) null);
        this.c = 0;
    }

    public final Object clone() {
        M30 m30 = new M30(this.c);
        System.arraycopy(this.b, 0, m30.b, 0, this.c);
        m30.c = this.c;
        return m30;
    }

    @Override // java.util.List
    public final Object get(int i) {
        if (i < this.c) {
            return this.b[i];
        }
        jb9.a(AbstractC2181nb0.a(this.c, ")", Ni0.a(i, "Index (", ") is greater than or equal to list size (")));
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC2990x1, java.util.List
    public final int indexOf(Object obj) {
        for (int i = 0; i < this.c; i++) {
            if (obj == this.b[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.c == 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC2990x1
    public final HU l(int i) {
        j(i);
        return new L30(this, i);
    }

    @Override // com.android.tools.r8.internal.AbstractC2990x1, java.util.List
    public final int lastIndexOf(Object obj) {
        int i = this.c;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return -1;
            }
            if (obj == this.b[i2]) {
                return i2;
            }
            i = i2;
        }
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        j(i);
        return new L30(this, i);
    }

    @Override // java.util.List
    public final Object remove(int i) {
        int i2 = this.c;
        if (i >= i2) {
            jb9.a(AbstractC2181nb0.a(this.c, ")", Ni0.a(i, "Index (", ") is greater than or equal to list size (")));
            return null;
        }
        Object[] objArr = this.b;
        Object obj = objArr[i];
        int i3 = i2 - 1;
        this.c = i3;
        if (i != i3) {
            System.arraycopy(objArr, i + 1, objArr, i, i3 - i);
        }
        this.b[this.c] = null;
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int i;
        Object[] objArr = this.b;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = this.c;
            if (i2 >= i) {
                break;
            }
            if (!collection.contains(objArr[i2])) {
                objArr[i3] = objArr[i2];
                i3++;
            }
            i2++;
        }
        Arrays.fill(objArr, i3, i, (Object) null);
        boolean z = this.c != i3;
        this.c = i3;
        return z;
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        if (i >= this.c) {
            jb9.a(AbstractC2181nb0.a(this.c, ")", Ni0.a(i, "Index (", ") is greater than or equal to list size (")));
            return null;
        }
        Object[] objArr = this.b;
        Object obj2 = objArr[i];
        objArr[i] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.c;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        remove(iIndexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        int i = this.c + 1;
        Object[] objArr = this.b;
        if (i > objArr.length) {
            Object[] objArr2 = new Object[(int) Math.max(Math.min(((long) objArr.length) * 2, 2147483639L), i)];
            System.arraycopy(this.b, 0, objArr2, 0, this.c);
            this.b = objArr2;
        }
        Object[] objArr3 = this.b;
        int i2 = this.c;
        this.c = i2 + 1;
        objArr3[i2] = obj;
        return true;
    }
}
