package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C6 extends AbstractC1875k implements Serializable {
    public transient boolean[] b;
    public int c;

    public C6(boolean[] zArr, int i) {
        this.b = zArr;
        this.c = i;
        if (i <= zArr.length) {
            return;
        }
        w01.a(AbstractC2181nb0.a(zArr.length, ")", Ni0.a(i, "The provided size (", ") is larger than or equal to the array size (")));
        throw null;
    }

    @Override // com.android.tools.r8.internal.AbstractC1619h
    public final boolean a(boolean z) {
        int i;
        int i2 = this.c;
        while (true) {
            i = i2 - 1;
            if (i2 == 0) {
                i = -1;
                break;
            }
            if (this.b[i] == z) {
                break;
            }
            i2 = i;
        }
        if (i != -1) {
            return false;
        }
        int i3 = this.c;
        if (i3 == this.b.length) {
            boolean[] zArr = new boolean[i3 == 0 ? 2 : i3 * 2];
            while (true) {
                int i4 = i3 - 1;
                if (i3 == 0) {
                    break;
                }
                zArr[i4] = this.b[i4];
                i3 = i4;
            }
            this.b = zArr;
        }
        boolean[] zArr2 = this.b;
        int i5 = this.c;
        this.c = i5 + 1;
        zArr2[i5] = z;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC1619h
    public final boolean b(boolean z) {
        int i;
        int i2 = this.c;
        while (true) {
            i = i2 - 1;
            if (i2 == 0) {
                i = -1;
                break;
            }
            if (this.b[i] == z) {
                break;
            }
            i2 = i;
        }
        return i != -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.c = 0;
    }

    public final Object clone() {
        try {
            C6 c6 = (C6) super.clone();
            c6.b = (boolean[]) this.b.clone();
            return c6;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1875k
    public final boolean d(boolean z) {
        int i;
        int i2 = this.c;
        while (true) {
            i = i2 - 1;
            if (i2 == 0) {
                i = -1;
                break;
            }
            if (this.b[i] == z) {
                break;
            }
            i2 = i;
        }
        if (i == -1) {
            return false;
        }
        int i3 = (this.c - i) - 1;
        for (int i4 = 0; i4 < i3; i4++) {
            boolean[] zArr = this.b;
            int i5 = i + i4;
            zArr[i5] = zArr[i5 + 1];
        }
        this.c--;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.c == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.F6, java.util.Set
    public final K6 iterator() {
        return new B6(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.c;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new B6(this);
    }
}
