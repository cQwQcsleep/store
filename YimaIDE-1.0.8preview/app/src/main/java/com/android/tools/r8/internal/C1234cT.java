package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1234cT extends U0 implements Cloneable {
    public transient Object[] c;
    public transient int[] d;
    public int e;

    public C1234cT(Map map) {
        int size = map.size();
        this.c = new Object[size];
        this.d = new int[size];
        putAll(map);
    }

    @Override // com.android.tools.r8.internal.U0
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

    @Override // com.android.tools.r8.internal.U0
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
            C1234cT c1234cT = (C1234cT) super.clone();
            c1234cT.c = (Object[]) this.c.clone();
            c1234cT.d = (int[]) this.d.clone();
            return c1234cT;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final boolean containsKey(Object obj) {
        return d(obj) != -1;
    }

    public final int d(Object obj) {
        Object[] objArr = this.c;
        int i = this.e;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return -1;
            }
            Object obj2 = objArr[i2];
            if (obj2 == null) {
                if (obj == null) {
                    return i2;
                }
                i = i2;
            } else {
                if (obj2.equals(obj)) {
                    return i2;
                }
                i = i2;
            }
        }
    }

    @Override // com.android.tools.r8.internal.U0
    public final JU i() {
        return new C1150bT(this);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.e == 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        return new C2685tU(this.e, this.c);
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final int size() {
        return this.e;
    }

    @Override // java.util.Map
    public final Collection values() {
        return new C1383eA(new C0997Yz(this.d, this.e));
    }

    @Override // com.android.tools.r8.internal.U0
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

    @Override // com.android.tools.r8.internal.U0
    public final int b(Object obj) {
        Object[] objArr = this.c;
        int i = this.e;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return this.b;
            }
            Object obj2 = objArr[i2];
            if (obj2 == null) {
                if (obj == null) {
                    return this.d[i2];
                }
                i = i2;
            } else {
                if (obj2.equals(obj)) {
                    return this.d[i2];
                }
                i = i2;
            }
        }
    }
}
