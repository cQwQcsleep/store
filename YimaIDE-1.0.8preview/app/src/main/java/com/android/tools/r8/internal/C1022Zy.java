package com.android.tools.r8.internal;

import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Zy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1022Zy extends S implements Cloneable {
    public transient int[] c;
    public transient Object[] d;
    public int e;

    public C1022Zy() {
        this.c = AbstractC1023Zz.a;
        this.d = AbstractC2771uU.a;
    }

    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC1109az
    public final Object a(int i, Object obj) {
        int iD = d(i);
        if (iD != -1) {
            Object[] objArr = this.d;
            Object obj2 = objArr[iD];
            objArr[iD] = obj;
            return obj2;
        }
        int i2 = this.e;
        if (i2 == this.c.length) {
            int[] iArr = new int[i2 == 0 ? 2 : i2 * 2];
            Object[] objArr2 = new Object[i2 != 0 ? i2 * 2 : 2];
            while (true) {
                int i3 = i2 - 1;
                if (i2 == 0) {
                    break;
                }
                iArr[i3] = this.c[i3];
                objArr2[i3] = this.d[i3];
                i2 = i3;
            }
            this.c = iArr;
            this.d = objArr2;
        }
        int[] iArr2 = this.c;
        int i4 = this.e;
        iArr2[i4] = i;
        this.d[i4] = obj;
        this.e = i4 + 1;
        return this.b;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2045lz
    public final JU c() {
        return new C0996Yy(this);
    }

    @Override // com.android.tools.r8.internal.P, java.util.Map
    public final void clear() {
        int i = this.e;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                this.e = 0;
                return;
            } else {
                this.d[i2] = null;
                i = i2;
            }
        }
    }

    public final Object clone() {
        try {
            C1022Zy c1022Zy = (C1022Zy) super.clone();
            c1022Zy.c = (int[]) this.c.clone();
            c1022Zy.d = (Object[]) this.d.clone();
            return c1022Zy;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        int i = this.e;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            if (this.d[i2] == obj) {
                return true;
            }
            i = i2;
        }
    }

    public final int d(int i) {
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

    @Override // com.android.tools.r8.internal.InterfaceC1109az
    public final Object get(int i) {
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

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.e == 0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final InterfaceC3177zA keySet() {
        return new C0997Yz(this.c, this.e);
    }

    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC1109az
    public final Object remove(int i) {
        int iD = d(i);
        if (iD == -1) {
            return this.b;
        }
        Object obj = this.d[iD];
        int i2 = (this.e - iD) - 1;
        int[] iArr = this.c;
        int i3 = iD + 1;
        System.arraycopy(iArr, i3, iArr, iD, i2);
        Object[] objArr = this.d;
        System.arraycopy(objArr, i3, objArr, iD, i2);
        int i4 = this.e - 1;
        this.e = i4;
        this.d[i4] = null;
        return obj;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.S, com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final P30 values() {
        return new R30(new O30(this.e, this.d));
    }

    @Override // java.util.Map
    public final Set keySet() {
        return new C0997Yz(this.c, this.e);
    }

    public C1022Zy(int i) {
        this.c = new int[i];
        this.d = new Object[i];
    }

    @Override // com.android.tools.r8.internal.S, com.android.tools.r8.internal.InterfaceC1109az
    public final boolean a(int i) {
        return d(i) != -1;
    }
}
