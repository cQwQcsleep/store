package com.android.tools.r8.internal;

import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Wz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0945Wz extends AbstractC2220o1 implements InterfaceC1216cB {
    public static final C0945Wz e;
    public int[] c;
    public int d;

    static {
        C0945Wz c0945Wz = new C0945Wz(new int[0], 0);
        e = c0945Wz;
        c0945Wz.b = false;
    }

    public C0945Wz() {
        this.c = new int[10];
        this.d = 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int i2;
        int iIntValue = ((Integer) obj).intValue();
        a();
        if (i < 0 || i > (i2 = this.d)) {
            StringBuilder sbA = Ni0.a(i, "Index:", ", Size:");
            sbA.append(this.d);
            throw new IndexOutOfBoundsException(sbA.toString());
        }
        int[] iArr = this.c;
        if (i2 < iArr.length) {
            System.arraycopy(iArr, i, iArr, i + 1, i2 - i);
        } else {
            int[] iArr2 = new int[((i2 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            System.arraycopy(this.c, i, iArr2, i + 1, this.d - i);
            this.c = iArr2;
        }
        this.c[i] = iIntValue;
        this.d++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.android.tools.r8.internal.AbstractC2220o1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        a();
        Charset charset = AbstractC1556gB.a;
        collection.getClass();
        if (!(collection instanceof C0945Wz)) {
            return super.addAll(collection);
        }
        C0945Wz c0945Wz = (C0945Wz) collection;
        int i = c0945Wz.d;
        if (i == 0) {
            return false;
        }
        int i2 = this.d;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        int[] iArr = this.c;
        if (i3 > iArr.length) {
            this.c = Arrays.copyOf(iArr, i3);
        }
        System.arraycopy(c0945Wz.c, 0, this.c, this.d, c0945Wz.d);
        this.d = i3;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC2220o1, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0945Wz)) {
            return super.equals(obj);
        }
        C0945Wz c0945Wz = (C0945Wz) obj;
        if (this.d != c0945Wz.d) {
            return false;
        }
        int[] iArr = c0945Wz.c;
        for (int i = 0; i < this.d; i++) {
            if (this.c[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        k(i);
        return Integer.valueOf(this.c[i]);
    }

    @Override // com.android.tools.r8.internal.AbstractC2220o1, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.d; i2++) {
            i = (i * 31) + this.c[i2];
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int iIntValue = ((Integer) obj).intValue();
        int i = this.d;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.c[i2] == iIntValue) {
                return i2;
            }
        }
        return -1;
    }

    public final void j(int i) {
        a();
        int i2 = this.d;
        int[] iArr = this.c;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[((i2 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.c = iArr2;
        }
        int[] iArr3 = this.c;
        int i3 = this.d;
        this.d = i3 + 1;
        iArr3[i3] = i;
    }

    public final void k(int i) {
        if (i < 0 || i >= this.d) {
            StringBuilder sbA = Ni0.a(i, "Index:", ", Size:");
            sbA.append(this.d);
            throw new IndexOutOfBoundsException(sbA.toString());
        }
    }

    public final C0945Wz l(int i) {
        if (i >= this.d) {
            return new C0945Wz(Arrays.copyOf(this.c, i), this.d);
        }
        j2d.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC2220o1, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        a();
        k(i);
        int[] iArr = this.c;
        int i2 = iArr[i];
        int i3 = this.d;
        if (i < i3 - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (i3 - i) - 1);
        }
        this.d--;
        ((AbstractList) this).modCount++;
        return Integer.valueOf(i2);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        a();
        if (i2 < i) {
            jb9.a("toIndex < fromIndex");
            return;
        }
        int[] iArr = this.c;
        System.arraycopy(iArr, i2, iArr, i, this.d - i2);
        this.d -= i2 - i;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        int iIntValue = ((Integer) obj).intValue();
        a();
        k(i);
        int[] iArr = this.c;
        int i2 = iArr[i];
        iArr[i] = iIntValue;
        return Integer.valueOf(i2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.d;
    }

    public C0945Wz(int[] iArr, int i) {
        this.c = iArr;
        this.d = i;
    }

    @Override // com.android.tools.r8.internal.AbstractC2220o1, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        j(((Integer) obj).intValue());
        return true;
    }
}
