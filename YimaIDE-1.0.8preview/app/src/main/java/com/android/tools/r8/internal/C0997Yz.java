package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Yz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0997Yz extends AbstractC1111b0 implements Serializable {
    public transient int[] b;
    public int c;

    public C0997Yz(int[] iArr, int i) {
        this.b = iArr;
        this.c = i;
        if (i <= iArr.length) {
            return;
        }
        w01.a(AbstractC2181nb0.a(iArr.length, ")", Ni0.a(i, "The provided size (", ") is larger than or equal to the array size (")));
        throw null;
    }

    @Override // com.android.tools.r8.internal.V, com.android.tools.r8.internal.InterfaceC1215cA
    public final boolean add(int i) {
        int i2;
        int i3 = this.c;
        while (true) {
            i2 = i3 - 1;
            if (i3 == 0) {
                i2 = -1;
                break;
            }
            if (this.b[i2] == i) {
                break;
            }
            i3 = i2;
        }
        if (i2 != -1) {
            return false;
        }
        int i4 = this.c;
        if (i4 == this.b.length) {
            int[] iArr = new int[i4 == 0 ? 2 : i4 * 2];
            while (true) {
                int i5 = i4 - 1;
                if (i4 == 0) {
                    break;
                }
                iArr[i5] = this.b[i5];
                i4 = i5;
            }
            this.b = iArr;
        }
        int[] iArr2 = this.b;
        int i6 = this.c;
        this.c = i6 + 1;
        iArr2[i6] = i;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.c = 0;
    }

    public final Object clone() {
        try {
            C0997Yz c0997Yz = (C0997Yz) super.clone();
            c0997Yz.b = (int[]) this.b.clone();
            return c0997Yz;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1215cA
    public final boolean f(int i) {
        int i2;
        int i3 = this.c;
        while (true) {
            i2 = i3 - 1;
            if (i3 == 0) {
                i2 = -1;
                break;
            }
            if (this.b[i2] == i) {
                break;
            }
            i3 = i2;
        }
        return i2 != -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.c == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1640hA iterator() {
        return new C0971Xz(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC1111b0
    public final boolean k(int i) {
        int i2;
        int i3 = this.c;
        while (true) {
            i2 = i3 - 1;
            if (i3 == 0) {
                i2 = -1;
                break;
            }
            if (this.b[i2] == i) {
                break;
            }
            i3 = i2;
        }
        if (i2 == -1) {
            return false;
        }
        int i4 = (this.c - i2) - 1;
        for (int i5 = 0; i5 < i4; i5++) {
            int[] iArr = this.b;
            int i6 = i2 + i5;
            iArr[i6] = iArr[i6 + 1];
        }
        this.c--;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.c;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C0971Xz(this);
    }

    public C0997Yz(int i) {
        this.b = new int[i];
    }

    public C0997Yz(Set set) {
        this.b = new int[set.size()];
        addAll(set);
    }

    public C0997Yz() {
        this.b = AbstractC1023Zz.a;
    }

    public C0997Yz(C0997Yz c0997Yz) {
        this.b = new int[c0997Yz.c];
        a(c0997Yz);
    }
}
