package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Y20 extends AbstractC2647t1 implements Cloneable {
    public transient Object[] c = AbstractC2771uU.a;
    public transient int[] d = AbstractC1023Zz.a;
    public int e;

    @Override // com.android.tools.r8.internal.AbstractC2647t1
    public final int b(int i, Object obj) {
        int iD = d(obj);
        if (iD != -1) {
            int[] iArr = this.d;
            int i2 = iArr[iD];
            iArr[iD] = i;
            return i2;
        }
        int i3 = this.e;
        if (i3 == this.c.length) {
            Object[] objArr = new Object[i3 == 0 ? 2 : i3 * 2];
            int[] iArr2 = new int[i3 != 0 ? i3 * 2 : 2];
            while (true) {
                int i4 = i3 - 1;
                if (i3 == 0) {
                    break;
                }
                objArr[i4] = this.c[i4];
                iArr2[i4] = this.d[i4];
                i3 = i4;
            }
            this.c = objArr;
            this.d = iArr2;
        }
        Object[] objArr2 = this.c;
        int i5 = this.e;
        objArr2[i5] = obj;
        this.d[i5] = i;
        this.e = i5 + 1;
        return this.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC2647t1
    public final int c(Object obj) {
        int iD = d(obj);
        if (iD == -1) {
            return this.b;
        }
        int i = this.d[iD];
        int i2 = (this.e - iD) - 1;
        Object[] objArr = this.c;
        int i3 = iD + 1;
        System.arraycopy(objArr, i3, objArr, iD, i2);
        int[] iArr = this.d;
        System.arraycopy(iArr, i3, iArr, iD, i2);
        int i4 = this.e - 1;
        this.e = i4;
        this.c[i4] = null;
        return i;
    }

    @Override // java.util.Map
    public final void clear() {
        int i = this.e;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                this.e = 0;
                return;
            } else {
                this.c[i2] = null;
                i = i2;
            }
        }
    }

    public final Object clone() {
        try {
            Y20 y20 = (Y20) super.clone();
            y20.c = (Object[]) this.c.clone();
            y20.d = (int[]) this.d.clone();
            return y20;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final boolean containsKey(Object obj) {
        return d(obj) != -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC2647t1
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

    @Override // com.android.tools.r8.internal.AbstractC2647t1
    public final JU i() {
        return new X20(this);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.e == 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        return new O30(this.e, this.c);
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final int size() {
        return this.e;
    }

    @Override // java.util.Map
    public final Collection values() {
        return new C1383eA(new C0997Yz(this.d, this.e));
    }

    public final int d(Object obj) {
        Object[] objArr = this.c;
        int i = this.e;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return -1;
            }
            if (objArr[i2] == obj) {
                return i2;
            }
            i = i2;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2647t1
    public final int b(Object obj) {
        Object[] objArr = this.c;
        int i = this.e;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return this.b;
            }
            if (objArr[i2] == obj) {
                return this.d[i2];
            }
            i = i2;
        }
    }
}
