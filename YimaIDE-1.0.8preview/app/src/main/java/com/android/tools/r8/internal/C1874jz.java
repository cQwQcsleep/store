package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1874jz extends U implements Cloneable {
    public transient int[] c;
    public transient Object[] d;
    public transient int e;
    public transient boolean f;
    public transient int g = -1;
    public transient int h = -1;
    public transient long[] i;
    public transient int j;
    public transient int k;
    public int l;
    public transient C1618gz m;
    public transient C1447ez n;
    public transient C1194bz o;

    public C1874jz() {
        int iA = AbstractC0938Ws.a(16, 0.75f);
        this.j = iA;
        this.e = iA - 1;
        this.k = AbstractC0938Ws.b(iA, 0.75f);
        int i = this.j + 1;
        this.c = new int[i];
        this.d = new Object[i];
        this.i = new long[i];
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0038  */
    /* JADX WARN: Code duplicated, block: B:20:0x0043  */
    /* JADX WARN: Code duplicated, block: B:23:0x0067  */
    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC1109az
    public final Object a(int i, Object obj) {
        int i2;
        int i3;
        int i4;
        if (i != 0) {
            int[] iArr = this.c;
            int iA = AbstractC0938Ws.a(i) & this.e;
            int i5 = iArr[iA];
            if (i5 != 0) {
                if (i5 != i) {
                    while (true) {
                        iA = (iA + 1) & this.e;
                        int i6 = iArr[iA];
                        if (i6 != 0) {
                            if (i6 == i) {
                            }
                        }
                    }
                }
                i3 = iA;
            }
            i2 = iA;
            this.c[i2] = i;
            this.d[i2] = obj;
            i4 = this.l;
            if (i4 == 0) {
                this.h = i2;
                this.g = i2;
                this.i[i2] = -1;
            } else {
                long[] jArr = this.i;
                int i7 = this.h;
                long j = jArr[i7];
                jArr[i7] = j ^ (((((long) i2) & 4294967295L) ^ j) & 4294967295L);
                jArr[i2] = ((((long) i7) & 4294967295L) << 32) | 4294967295L;
                this.h = i2;
            }
            this.l = i4 + 1;
            if (i4 >= this.k) {
                e(AbstractC0938Ws.a(i4 + 2, 0.75f));
            }
            i3 = -1;
        } else if (this.f) {
            i3 = this.j;
        } else {
            this.f = true;
            i2 = this.j;
            this.c[i2] = i;
            this.d[i2] = obj;
            i4 = this.l;
            if (i4 == 0) {
                this.h = i2;
                this.g = i2;
                this.i[i2] = -1;
            } else {
                long[] jArr2 = this.i;
                int i8 = this.h;
                long j2 = jArr2[i8];
                jArr2[i8] = j2 ^ (((((long) i2) & 4294967295L) ^ j2) & 4294967295L);
                jArr2[i2] = ((((long) i8) & 4294967295L) << 32) | 4294967295L;
                this.h = i2;
            }
            this.l = i4 + 1;
            if (i4 >= this.k) {
                e(AbstractC0938Ws.a(i4 + 2, 0.75f));
            }
            i3 = -1;
        }
        if (i3 < 0) {
            return this.b;
        }
        Object[] objArr = this.d;
        Object obj2 = objArr[i3];
        objArr[i3] = obj;
        return obj2;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz b(int i) {
        throw new UnsupportedOperationException();
    }

    public final void c(int i, int i2) {
        if (this.l == 1) {
            this.h = i2;
            this.g = i2;
            this.i[i2] = -1;
            return;
        }
        if (this.g == i) {
            this.g = i2;
            long[] jArr = this.i;
            int i3 = (int) jArr[i];
            long j = jArr[i3];
            jArr[i3] = ((-4294967296L) & (((4294967295L & ((long) i2)) << 32) ^ j)) ^ j;
            jArr[i2] = jArr[i];
            return;
        }
        if (this.h == i) {
            this.h = i2;
            long[] jArr2 = this.i;
            int i4 = (int) (jArr2[i] >>> 32);
            long j2 = jArr2[i4];
            jArr2[i4] = j2 ^ (((((long) i2) & 4294967295L) ^ j2) & 4294967295L);
            jArr2[i2] = jArr2[i];
            return;
        }
        long[] jArr3 = this.i;
        long j3 = jArr3[i];
        int i5 = (int) (j3 >>> 32);
        int i6 = (int) j3;
        long j4 = jArr3[i5];
        long j5 = ((long) i2) & 4294967295L;
        jArr3[i5] = (4294967295L & (j4 ^ j5)) ^ j4;
        long j6 = jArr3[i6];
        jArr3[i6] = ((-4294967296L) & ((j5 << 32) ^ j6)) ^ j6;
        jArr3[i2] = j3;
    }

    @Override // com.android.tools.r8.internal.P, java.util.Map
    public final void clear() {
        if (this.l == 0) {
            return;
        }
        this.l = 0;
        this.f = false;
        Arrays.fill(this.c, 0);
        Arrays.fill(this.d, (Object) null);
        this.h = -1;
        this.g = -1;
    }

    public final Object clone() {
        try {
            C1874jz c1874jz = (C1874jz) super.clone();
            c1874jz.n = null;
            c1874jz.o = null;
            c1874jz.m = null;
            c1874jz.f = this.f;
            c1874jz.c = (int[]) this.c.clone();
            c1874jz.d = (Object[]) this.d.clone();
            c1874jz.i = (long[]) this.i.clone();
            return c1874jz;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final AbstractC3239zx comparator() {
        return null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        Object[] objArr = this.d;
        int[] iArr = this.c;
        if (this.f && objArr[this.j] == obj) {
            return true;
        }
        int i = this.j;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            if (iArr[i2] != 0 && objArr[i2] == obj) {
                return true;
            }
            i = i2;
        }
    }

    public final void d(int i) {
        if (this.l == 0) {
            this.h = -1;
            this.g = -1;
            return;
        }
        if (this.g == i) {
            long[] jArr = this.i;
            int i2 = (int) jArr[i];
            this.g = i2;
            if (i2 >= 0) {
                jArr[i2] = (-4294967296L) | jArr[i2];
                return;
            }
            return;
        }
        int i3 = this.h;
        long[] jArr2 = this.i;
        if (i3 == i) {
            int i4 = (int) (jArr2[i] >>> 32);
            this.h = i4;
            if (i4 >= 0) {
                jArr2[i4] = jArr2[i4] | 4294967295L;
                return;
            }
            return;
        }
        long j = jArr2[i];
        int i5 = (int) (j >>> 32);
        int i6 = (int) j;
        long j2 = jArr2[i5];
        jArr2[i5] = (4294967295L & ((j & 4294967295L) ^ j2)) ^ j2;
        long j3 = jArr2[i6];
        jArr2[i6] = (((j & (-4294967296L)) ^ j3) & (-4294967296L)) ^ j3;
    }

    public final void e(int i) {
        int i2;
        int[] iArr = this.c;
        Object[] objArr = this.d;
        int i3 = i - 1;
        int i4 = i + 1;
        int[] iArr2 = new int[i4];
        Object[] objArr2 = new Object[i4];
        int i5 = this.g;
        long[] jArr = this.i;
        long[] jArr2 = new long[i4];
        int i6 = -1;
        this.g = -1;
        int i7 = this.l;
        int i8 = -1;
        int i9 = -1;
        while (true) {
            int i10 = i7 - 1;
            if (i7 == 0) {
                break;
            }
            int i11 = iArr[i5];
            if (i11 != 0) {
                int iA = AbstractC0938Ws.a(i11);
                while (true) {
                    i2 = iA & i3;
                    if (iArr2[i2] == 0) {
                        break;
                    } else {
                        iA = i2 + 1;
                    }
                }
            } else {
                i2 = i;
            }
            iArr2[i2] = iArr[i5];
            objArr2[i2] = objArr[i5];
            if (i9 != i6) {
                long j = jArr2[i8];
                jArr2[i8] = j ^ ((j ^ (((long) i2) & 4294967295L)) & 4294967295L);
                long j2 = jArr2[i2];
                int i12 = i2;
                jArr2[i12] = j2 ^ ((((((long) i8) & 4294967295L) << 32) ^ j2) & (-4294967296L));
                i2 = i12;
            } else {
                this.g = i2;
                jArr2[i2] = -1;
            }
            int i13 = i5;
            i5 = (int) jArr[i5];
            iArr = iArr;
            i9 = i13;
            i8 = i2;
            i7 = i10;
            objArr = objArr;
            i6 = -1;
        }
        this.i = jArr2;
        this.h = i8;
        if (i8 != -1) {
            jArr2[i8] = jArr2[i8] | 4294967295L;
        }
        this.j = i;
        this.e = i3;
        this.k = AbstractC0938Ws.b(i, 0.75f);
        this.c = iArr2;
        this.d = objArr2;
    }

    public final Object f(int i) {
        int i2;
        int i3;
        Object[] objArr = this.d;
        Object obj = objArr[i];
        objArr[i] = null;
        this.l--;
        d(i);
        int[] iArr = this.c;
        loop0: while (true) {
            int i4 = (i + 1) & this.e;
            while (true) {
                i2 = iArr[i4];
                if (i2 != 0) {
                    int iA = AbstractC0938Ws.a(i2);
                    int i5 = this.e;
                    int i6 = iA & i5;
                    if (i > i4) {
                        if (i >= i6 && i6 > i4) {
                            break;
                        }
                        i4 = (i4 + 1) & i5;
                    } else {
                        if (i >= i6 || i6 > i4) {
                            break;
                        }
                        i4 = (i4 + 1) & i5;
                    }
                } else {
                    break loop0;
                }
            }
            iArr[i] = i2;
            Object[] objArr2 = this.d;
            objArr2[i] = objArr2[i4];
            c(i4, i);
            i = i4;
        }
        iArr[i] = 0;
        this.d[i] = null;
        if (this.l < this.k / 4 && (i3 = this.j) > 16) {
            e(i3 / 2);
        }
        return obj;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1109az
    public final Object get(int i) {
        int i2;
        if (i == 0) {
            return this.f ? this.d[this.j] : this.b;
        }
        int[] iArr = this.c;
        int iA = AbstractC0938Ws.a(i) & this.e;
        int i3 = iArr[iA];
        if (i3 == 0) {
            return this.b;
        }
        if (i == i3) {
            return this.d[iA];
        }
        do {
            iA = (iA + 1) & this.e;
            i2 = iArr[iA];
            if (i2 == 0) {
                return this.b;
            }
        } while (i != i2);
        return this.d[iA];
    }

    @Override // com.android.tools.r8.internal.S, java.util.Map
    public final int hashCode() {
        int iIdentityHashCode;
        boolean z = this.f;
        int i = this.l;
        if (z) {
            i--;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i - 1;
            if (i == 0) {
                break;
            }
            while (true) {
                iIdentityHashCode = this.c[i2];
                if (iIdentityHashCode != 0) {
                    break;
                }
                i2++;
            }
            Object obj = this.d[i2];
            if (this != obj) {
                iIdentityHashCode ^= obj == null ? 0 : System.identityHashCode(obj);
            }
            i3 += iIdentityHashCode;
            i2++;
            i = i4;
        }
        if (!this.f) {
            return i3;
        }
        Object obj2 = this.d[this.j];
        return i3 + (obj2 != null ? System.identityHashCode(obj2) : 0);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.l == 0;
    }

    @Override // com.android.tools.r8.internal.U, com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final CA keySet() {
        if (this.n == null) {
            this.n = new C1447ez(this);
        }
        return this.n;
    }

    @Override // com.android.tools.r8.internal.S, java.util.Map
    public final void putAll(Map map) {
        int iMin = (int) Math.min(1073741824L, Math.max(2L, AbstractC0938Ws.b((long) Math.ceil((map.size() + this.l) / 0.75f))));
        if (iMin > this.j) {
            e(iMin);
        }
        super.putAll(map);
    }

    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC1109az
    public final Object remove(int i) {
        int i2;
        int i3;
        if (i == 0) {
            if (!this.f) {
                return this.b;
            }
            this.f = false;
            Object[] objArr = this.d;
            int i4 = this.j;
            Object obj = objArr[i4];
            objArr[i4] = null;
            this.l--;
            d(i4);
            if (this.l < this.k / 4 && (i3 = this.j) > 16) {
                e(i3 / 2);
            }
            return obj;
        }
        int[] iArr = this.c;
        int iA = AbstractC0938Ws.a(i) & this.e;
        int i5 = iArr[iA];
        if (i5 == 0) {
            return this.b;
        }
        if (i == i5) {
            return f(iA);
        }
        do {
            iA = (iA + 1) & this.e;
            i2 = iArr[iA];
            if (i2 == 0) {
                return this.b;
            }
        } while (i != i2);
        return f(iA);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        return this.l;
    }

    @Override // com.android.tools.r8.internal.S, com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final P30 values() {
        if (this.o == null) {
            this.o = new C1194bz(this);
        }
        return this.o;
    }

    @Override // java.util.SortedMap
    public final /* bridge */ /* synthetic */ Comparator comparator() {
        return null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final int d() {
        if (this.l != 0) {
            return this.c[this.h];
        }
        z0e.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz c(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.U, com.android.tools.r8.internal.InterfaceC2045lz
    public final JU c() {
        if (this.m == null) {
            this.m = new C1618gz(this);
        }
        return this.m;
    }

    @Override // com.android.tools.r8.internal.U, com.android.tools.r8.internal.InterfaceC2045lz
    public final NU c() {
        if (this.m == null) {
            this.m = new C1618gz(this);
        }
        return this.m;
    }

    @Override // com.android.tools.r8.internal.S, com.android.tools.r8.internal.InterfaceC1109az
    public final boolean a(int i) {
        int i2;
        if (i == 0) {
            return this.f;
        }
        int[] iArr = this.c;
        int iA = AbstractC0938Ws.a(i) & this.e;
        int i3 = iArr[iA];
        if (i3 == 0) {
            return false;
        }
        if (i == i3) {
            return true;
        }
        do {
            iA = (iA + 1) & this.e;
            i2 = iArr[iA];
            if (i2 == 0) {
                return false;
            }
        } while (i != i2);
        return true;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final int a() {
        if (this.l != 0) {
            return this.c[this.g];
        }
        z0e.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz a(int i, int i2) {
        throw new UnsupportedOperationException();
    }
}
