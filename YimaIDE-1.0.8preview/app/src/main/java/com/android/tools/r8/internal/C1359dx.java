package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1359dx extends J implements Cloneable {
    public transient int[] c;
    public transient int[] d;
    public int e;

    public C1359dx(int i) {
        this.c = new int[i];
        this.d = new int[i];
    }

    @Override // com.android.tools.r8.internal.InterfaceC1445ex
    public final boolean a(int i) {
        return e(i) != -1;
    }

    @Override // com.android.tools.r8.internal.J, com.android.tools.r8.internal.InterfaceC1445ex
    public final int b(int i, int i2) {
        int iE = e(i);
        if (iE != -1) {
            int[] iArr = this.d;
            int i3 = iArr[iE];
            iArr[iE] = i2;
            return i3;
        }
        int i4 = this.e;
        if (i4 == this.c.length) {
            int[] iArr2 = new int[i4 == 0 ? 2 : i4 * 2];
            int[] iArr3 = new int[i4 != 0 ? i4 * 2 : 2];
            while (true) {
                int i5 = i4 - 1;
                if (i4 == 0) {
                    break;
                }
                iArr2[i5] = this.c[i5];
                iArr3[i5] = this.d[i5];
                i4 = i5;
            }
            this.c = iArr2;
            this.d = iArr3;
        }
        int[] iArr4 = this.c;
        int i6 = this.e;
        iArr4[i6] = i;
        this.d[i6] = i2;
        this.e = i6 + 1;
        return this.b;
    }

    @Override // java.util.Map
    public final void clear() {
        this.e = 0;
    }

    public final Object clone() {
        try {
            C1359dx c1359dx = (C1359dx) super.clone();
            c1359dx.c = (int[]) this.c.clone();
            c1359dx.d = (int[]) this.d.clone();
            return c1359dx;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // com.android.tools.r8.internal.J
    public final boolean d(int i) {
        int i2 = this.e;
        while (true) {
            int i3 = i2 - 1;
            if (i2 == 0) {
                return false;
            }
            if (this.d[i3] == i) {
                return true;
            }
            i2 = i3;
        }
    }

    public final int e(int i) {
        int[] iArr = this.c;
        int i2 = this.e;
        while (true) {
            int i3 = i2 - 1;
            if (i2 == 0) {
                return -1;
            }
            if (iArr[i3] == i) {
                return i3;
            }
            i2 = i3;
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1445ex
    public final int get(int i) {
        int[] iArr = this.c;
        int i2 = this.e;
        while (true) {
            int i3 = i2 - 1;
            if (i2 == 0) {
                return this.b;
            }
            if (iArr[i3] == i) {
                return this.d[i3];
            }
            i2 = i3;
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC2386px
    public final JU h() {
        return new C1275cx(this);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.e == 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        return new C0997Yz(this.c, this.e);
    }

    @Override // com.android.tools.r8.internal.J, com.android.tools.r8.internal.InterfaceC1445ex
    public final int remove(int i) {
        int iE = e(i);
        if (iE == -1) {
            return this.b;
        }
        int i2 = this.d[iE];
        int i3 = (this.e - iE) - 1;
        int[] iArr = this.c;
        int i4 = iE + 1;
        System.arraycopy(iArr, i4, iArr, iE, i3);
        int[] iArr2 = this.d;
        System.arraycopy(iArr2, i4, iArr2, iE, i3);
        this.e--;
        return i2;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        return this.e;
    }

    @Override // java.util.Map
    public final Collection values() {
        return new C1383eA(new C0997Yz(this.d, this.e));
    }

    public C1359dx() {
        int[] iArr = AbstractC1023Zz.a;
        this.c = iArr;
        this.d = iArr;
    }
}
