package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Collection;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bM, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1143bM extends AbstractC1961l0 implements RandomAccess, Cloneable, Serializable {
    public transient long[] b;
    public int c;

    public C1143bM(int i) {
        if (i >= 0) {
            this.b = new long[i];
        } else {
            w01.a(AbstractC1784iv.a(i, "Initial capacity (", ") is negative"));
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1566gM
    public final void a(int i, long j) {
        j(i);
        int i2 = this.c;
        int i3 = i2 + 1;
        long[] jArr = this.b;
        if (i3 > jArr.length) {
            long[] jArr2 = new long[(int) Math.max(Math.min(((long) jArr.length) * 2, 2147483639L), i3)];
            System.arraycopy(jArr, 0, jArr2, 0, i2);
            jArr = jArr2;
        }
        this.b = jArr;
        int i4 = this.c;
        if (i != i4) {
            System.arraycopy(jArr, i, jArr, i + 1, i4 - i);
        }
        this.b[i] = j;
        this.c++;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1566gM
    public final long b(int i, long j) {
        if (i >= this.c) {
            jb9.a(AbstractC2181nb0.a(this.c, ")", Ni0.a(i, "Index (", ") is greater than or equal to list size (")));
            return 0L;
        }
        long[] jArr = this.b;
        long j2 = jArr[i];
        jArr[i] = j;
        return j2;
    }

    @Override // com.android.tools.r8.internal.AbstractC1706i0
    public final boolean c(long j) {
        int iD = d(j);
        if (iD == -1) {
            return false;
        }
        d(iD);
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC1961l0, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        this.c = 0;
    }

    public final Object clone() {
        C1143bM c1143bM = new C1143bM(this.c);
        System.arraycopy(this.b, 0, c1143bM.b, 0, this.c);
        c1143bM.c = this.c;
        return c1143bM;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1566gM
    public final long d(int i) {
        int i2 = this.c;
        if (i >= i2) {
            jb9.a(AbstractC2181nb0.a(this.c, ")", Ni0.a(i, "Index (", ") is greater than or equal to list size (")));
            return 0L;
        }
        long[] jArr = this.b;
        long j = jArr[i];
        int i3 = i2 - 1;
        this.c = i3;
        if (i != i3) {
            System.arraycopy(jArr, i + 1, jArr, i, i3 - i);
        }
        return j;
    }

    @Override // com.android.tools.r8.internal.AbstractC1961l0
    public final int e(long j) {
        int i = this.c;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return -1;
            }
            if (j == this.b[i2]) {
                return i2;
            }
            i = i2;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.c == 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC1961l0
    public final AbstractC2047m0 l(int i) {
        j(i);
        return new C1057aM(this, i);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        j(i);
        return new C1057aM(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int i;
        long[] jArr = this.b;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = this.c;
            if (i2 >= i) {
                break;
            }
            if (!collection.contains(Long.valueOf(jArr[i2]))) {
                jArr[i3] = jArr[i2];
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

    @Override // com.android.tools.r8.internal.AbstractC1961l0
    public final int d(long j) {
        for (int i = 0; i < this.c; i++) {
            if (j == this.b[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC1706i0
    public final boolean a(long j) {
        int i = this.c;
        int i2 = i + 1;
        long[] jArr = this.b;
        if (i2 > jArr.length) {
            long[] jArr2 = new long[(int) Math.max(Math.min(((long) jArr.length) * 2, 2147483639L), i2)];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            jArr = jArr2;
        }
        this.b = jArr;
        int i3 = this.c;
        this.c = i3 + 1;
        jArr[i3] = j;
        return true;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1566gM
    public final long a(int i) {
        if (i < this.c) {
            return this.b[i];
        }
        jb9.a(AbstractC2181nb0.a(this.c, ")", Ni0.a(i, "Index (", ") is greater than or equal to list size (")));
        return 0L;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1566gM
    public final void a(int i, int i2) {
        S3.a(this.c, i, i2);
        long[] jArr = this.b;
        System.arraycopy(jArr, i2, jArr, i, this.c - i2);
        this.c -= i2 - i;
    }
}
