package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Collection;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Vz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0919Vz extends Z implements RandomAccess, Cloneable, Serializable {
    public transient int[] b;
    public int c;

    public C0919Vz(int i) {
        if (i >= 0) {
            this.b = new int[i];
        } else {
            w01.a(AbstractC1784iv.a(i, "Initial capacity (", ") is negative"));
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final void a(int i, int[] iArr, int i2, int i3) {
        int length = iArr.length;
        if (i2 < 0) {
            throw new ArrayIndexOutOfBoundsException(AbstractC1784iv.a(i2, "Offset (", ") is negative"));
        }
        if (i3 < 0) {
            w01.a(AbstractC1784iv.a(i3, "Length (", ") is negative"));
            return;
        }
        int i4 = i2 + i3;
        if (i4 <= length) {
            System.arraycopy(this.b, i, iArr, i2, i3);
        } else {
            sg0.a("Last index (", i4, length);
        }
    }

    @Override // com.android.tools.r8.internal.Z, com.android.tools.r8.internal.V, com.android.tools.r8.internal.InterfaceC1215cA
    public final boolean add(int i) {
        p(this.c + 1);
        int[] iArr = this.b;
        int i2 = this.c;
        this.c = i2 + 1;
        iArr[i2] = i;
        return true;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final void b(int i, int i2) {
        k(i);
        p(this.c + 1);
        int i3 = this.c;
        if (i != i3) {
            int[] iArr = this.b;
            System.arraycopy(iArr, i, iArr, i + 1, i3 - i);
        }
        this.b[i] = i2;
        this.c++;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final int c(int i, int i2) {
        if (i >= this.c) {
            jb9.a(AbstractC2181nb0.a(this.c, ")", Ni0.a(i, "Index (", ") is greater than or equal to list size (")));
            return 0;
        }
        int[] iArr = this.b;
        int i3 = iArr[i];
        iArr[i] = i2;
        return i3;
    }

    @Override // com.android.tools.r8.internal.Z, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        this.c = 0;
    }

    public final Object clone() {
        C0919Vz c0919Vz = new C0919Vz(this.c);
        System.arraycopy(this.b, 0, c0919Vz.b, 0, this.c);
        c0919Vz.c = this.c;
        return c0919Vz;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final int h(int i) {
        int i2 = this.c;
        if (i >= i2) {
            jb9.a(AbstractC2181nb0.a(this.c, ")", Ni0.a(i, "Index (", ") is greater than or equal to list size (")));
            return 0;
        }
        int[] iArr = this.b;
        int i3 = iArr[i];
        int i4 = i2 - 1;
        this.c = i4;
        if (i != i4) {
            System.arraycopy(iArr, i + 1, iArr, i, i4 - i);
        }
        return i3;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final int i(int i) {
        if (i < this.c) {
            return this.b[i];
        }
        jb9.a(AbstractC2181nb0.a(this.c, ")", Ni0.a(i, "Index (", ") is greater than or equal to list size (")));
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.c == 0;
    }

    @Override // com.android.tools.r8.internal.V
    public final boolean j(int i) {
        int iM = m(i);
        if (iM == -1) {
            return false;
        }
        h(iM);
        return true;
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        k(i);
        return new C0893Uz(this, i);
    }

    @Override // com.android.tools.r8.internal.Z
    public final int m(int i) {
        for (int i2 = 0; i2 < this.c; i2++) {
            if (i == this.b[i2]) {
                return i2;
            }
        }
        return -1;
    }

    @Override // com.android.tools.r8.internal.Z
    public final int n(int i) {
        int i2 = this.c;
        while (true) {
            int i3 = i2 - 1;
            if (i2 == 0) {
                return -1;
            }
            if (i == this.b[i3]) {
                return i3;
            }
            i2 = i3;
        }
    }

    @Override // com.android.tools.r8.internal.Z
    public final InterfaceC2067mA o(int i) {
        k(i);
        return new C0893Uz(this, i);
    }

    public final void p(int i) {
        int[] iArr = this.b;
        int i2 = this.c;
        if (i > iArr.length) {
            int[] iArr2 = new int[(int) Math.max(Math.min(((long) iArr.length) * 2, 2147483639L), i)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            iArr = iArr2;
        }
        this.b = iArr;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int i;
        int[] iArr = this.b;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = this.c;
            if (i2 >= i) {
                break;
            }
            if (!collection.contains(Integer.valueOf(iArr[i2]))) {
                iArr[i3] = iArr[i2];
                i3++;
            }
            i2++;
        }
        boolean z = i != i3;
        this.c = i3;
        return z;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.c;
    }

    public C0919Vz(int[] iArr, int i) {
        this.b = iArr;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public C0919Vz(int[] iArr) {
        int length = iArr.length;
        this(length);
        System.arraycopy(iArr, 0, this.b, 0, length);
        this.c = length;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final void a(int i, int i2) {
        S3.a(this.c, i, i2);
        int[] iArr = this.b;
        System.arraycopy(iArr, i2, iArr, i, this.c - i2);
        this.c -= i2 - i;
    }

    @Override // com.android.tools.r8.internal.V
    public final int[] a(int[] iArr) {
        if (iArr == null || iArr.length < this.c) {
            iArr = new int[this.c];
        }
        System.arraycopy(this.b, 0, iArr, 0, this.c);
        return iArr;
    }

    @Override // com.android.tools.r8.internal.Z
    public final boolean a(int i, InterfaceC1981lA interfaceC1981lA) {
        k(i);
        int size = interfaceC1981lA.size();
        if (size == 0) {
            return false;
        }
        p(this.c + size);
        int i2 = this.c;
        if (i != i2) {
            int[] iArr = this.b;
            System.arraycopy(iArr, i, iArr, i + size, i2 - i);
        }
        interfaceC1981lA.a(0, this.b, i, size);
        this.c += size;
        return true;
    }
}
