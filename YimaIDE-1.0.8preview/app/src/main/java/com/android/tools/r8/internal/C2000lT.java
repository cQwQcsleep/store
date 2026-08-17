package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2000lT extends U0 implements Cloneable, SortedMap {
    public transient Object[] c;
    public transient int[] d;
    public transient int e;
    public transient boolean f;
    public transient int g = -1;
    public transient int h = -1;
    public transient long[] i;
    public transient int j;
    public transient int k;
    public int l;
    public transient C1745iT m;
    public transient C1573gT n;
    public transient C1317dT o;

    public C2000lT() {
        int iA = AbstractC0938Ws.a(16, 0.75f);
        this.j = iA;
        this.e = iA - 1;
        this.k = AbstractC0938Ws.b(iA, 0.75f);
        int i = this.j + 1;
        this.c = new Object[i];
        this.d = new int[i];
        this.i = new long[i];
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0045  */
    /* JADX WARN: Code duplicated, block: B:22:0x0050  */
    /* JADX WARN: Code duplicated, block: B:25:0x0074  */
    @Override // com.android.tools.r8.internal.U0
    public final int b(int i, Object obj) {
        int i2;
        int i3;
        int i4;
        if (obj != null) {
            Object[] objArr = this.c;
            int iA = AbstractC0938Ws.a(obj.hashCode()) & this.e;
            Object obj2 = objArr[iA];
            if (obj2 != null) {
                if (!obj2.equals(obj)) {
                    while (true) {
                        iA = (iA + 1) & this.e;
                        Object obj3 = objArr[iA];
                        if (obj3 != null) {
                            if (obj3.equals(obj)) {
                            }
                        }
                    }
                }
                i3 = iA;
            }
            i2 = iA;
            this.c[i2] = obj;
            this.d[i2] = i;
            i4 = this.l;
            if (i4 == 0) {
                this.h = i2;
                this.g = i2;
                this.i[i2] = -1;
            } else {
                long[] jArr = this.i;
                int i5 = this.h;
                long j = jArr[i5];
                jArr[i5] = j ^ (((((long) i2) & 4294967295L) ^ j) & 4294967295L);
                jArr[i2] = ((((long) i5) & 4294967295L) << 32) | 4294967295L;
                this.h = i2;
            }
            this.l = i4 + 1;
            if (i4 >= this.k) {
                f(AbstractC0938Ws.a(i4 + 2, 0.75f));
            }
            i3 = -1;
        } else if (this.f) {
            i3 = this.j;
        } else {
            this.f = true;
            i2 = this.j;
            this.c[i2] = obj;
            this.d[i2] = i;
            i4 = this.l;
            if (i4 == 0) {
                this.h = i2;
                this.g = i2;
                this.i[i2] = -1;
            } else {
                long[] jArr2 = this.i;
                int i6 = this.h;
                long j2 = jArr2[i6];
                jArr2[i6] = j2 ^ (((((long) i2) & 4294967295L) ^ j2) & 4294967295L);
                jArr2[i2] = ((((long) i6) & 4294967295L) << 32) | 4294967295L;
                this.h = i2;
            }
            this.l = i4 + 1;
            if (i4 >= this.k) {
                f(AbstractC0938Ws.a(i4 + 2, 0.75f));
            }
            i3 = -1;
        }
        if (i3 < 0) {
            return this.b;
        }
        int[] iArr = this.d;
        int i7 = iArr[i3];
        iArr[i3] = i;
        return i7;
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

    @Override // java.util.Map
    public final void clear() {
        if (this.l == 0) {
            return;
        }
        this.l = 0;
        this.f = false;
        Arrays.fill(this.c, (Object) null);
        this.h = -1;
        this.g = -1;
    }

    public final Object clone() {
        try {
            C2000lT c2000lT = (C2000lT) super.clone();
            c2000lT.n = null;
            c2000lT.o = null;
            c2000lT.m = null;
            c2000lT.f = this.f;
            c2000lT.c = (Object[]) this.c.clone();
            c2000lT.d = (int[]) this.d.clone();
            c2000lT.i = (long[]) this.i.clone();
            return c2000lT;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // java.util.SortedMap
    public final Comparator comparator() {
        return null;
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final boolean containsKey(Object obj) {
        Object obj2;
        if (obj == null) {
            return this.f;
        }
        Object[] objArr = this.c;
        int iA = AbstractC0938Ws.a(obj.hashCode()) & this.e;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return false;
        }
        if (obj.equals(obj3)) {
            return true;
        }
        do {
            iA = (iA + 1) & this.e;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return false;
            }
        } while (!obj.equals(obj2));
        return true;
    }

    @Override // com.android.tools.r8.internal.U0
    public final boolean d(int i) {
        int[] iArr = this.d;
        Object[] objArr = this.c;
        if (this.f && iArr[this.j] == i) {
            return true;
        }
        int i2 = this.j;
        while (true) {
            int i3 = i2 - 1;
            if (i2 == 0) {
                return false;
            }
            if (objArr[i3] != null && iArr[i3] == i) {
                return true;
            }
            i2 = i3;
        }
    }

    public final void e(int i) {
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

    @Override // com.android.tools.r8.internal.U0, java.util.Map
    public final Set entrySet() {
        if (this.m == null) {
            this.m = new C1745iT(this);
        }
        return this.m;
    }

    public final void f(int i) {
        int i2;
        Object[] objArr = this.c;
        int[] iArr = this.d;
        int i3 = i - 1;
        int i4 = i + 1;
        Object[] objArr2 = new Object[i4];
        int[] iArr2 = new int[i4];
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
            Object obj = objArr[i5];
            if (obj != null) {
                int iA = AbstractC0938Ws.a(obj.hashCode());
                while (true) {
                    i2 = iA & i3;
                    if (objArr2[i2] == null) {
                        break;
                    } else {
                        iA = i2 + 1;
                    }
                }
            } else {
                i2 = i;
            }
            objArr2[i2] = objArr[i5];
            iArr2[i2] = iArr[i5];
            if (i9 != i6) {
                long j = jArr2[i8];
                jArr2[i8] = j ^ ((j ^ (((long) i2) & 4294967295L)) & 4294967295L);
                long j2 = jArr2[i2];
                int i11 = i2;
                jArr2[i11] = j2 ^ ((((((long) i8) & 4294967295L) << 32) ^ j2) & (-4294967296L));
                i2 = i11;
            } else {
                this.g = i2;
                jArr2[i2] = -1;
            }
            int i12 = i5;
            i5 = (int) jArr[i5];
            objArr = objArr;
            i9 = i12;
            i8 = i2;
            i7 = i10;
            iArr = iArr;
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
        this.c = objArr2;
        this.d = iArr2;
    }

    @Override // java.util.SortedMap
    public final Object firstKey() {
        if (this.l != 0) {
            return this.c[this.g];
        }
        z0e.a();
        return null;
    }

    public final int g(int i) {
        Object obj;
        int i2;
        int i3 = this.d[i];
        this.l--;
        e(i);
        Object[] objArr = this.c;
        loop0: while (true) {
            int i4 = (i + 1) & this.e;
            while (true) {
                obj = objArr[i4];
                if (obj != null) {
                    int iA = AbstractC0938Ws.a(obj.hashCode());
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
            objArr[i] = obj;
            int[] iArr = this.d;
            iArr[i] = iArr[i4];
            c(i4, i);
            i = i4;
        }
        objArr[i] = null;
        if (this.l < this.k / 4 && (i2 = this.j) > 16) {
            f(i2 / 2);
        }
        return i3;
    }

    @Override // com.android.tools.r8.internal.U0, java.util.Map
    public final int hashCode() {
        Object obj;
        boolean z = this.f;
        int i = this.l;
        if (z) {
            i--;
        }
        int i2 = 0;
        int iHashCode = 0;
        int i3 = i;
        int i4 = 0;
        while (true) {
            int i5 = i3 - 1;
            if (i3 == 0) {
                break;
            }
            while (true) {
                obj = this.c[i2];
                if (obj != null) {
                    break;
                }
                i2++;
            }
            if (this != obj) {
                iHashCode = obj.hashCode();
            }
            iHashCode ^= this.d[i2];
            i4 += iHashCode;
            i2++;
            i3 = i5;
        }
        return this.f ? i4 + this.d[this.j] : i4;
    }

    @Override // java.util.SortedMap
    public final SortedMap headMap(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.U0
    public final JU i() {
        if (this.m == null) {
            this.m = new C1745iT(this);
        }
        return this.m;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.l == 0;
    }

    @Override // java.util.Map, java.util.SortedMap
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public final NU keySet() {
        if (this.n == null) {
            this.n = new C1573gT(this);
        }
        return this.n;
    }

    @Override // java.util.SortedMap
    public final Object lastKey() {
        if (this.l != 0) {
            return this.c[this.h];
        }
        z0e.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.U0, java.util.Map
    public final void putAll(Map map) {
        int iMin = (int) Math.min(1073741824L, Math.max(2L, AbstractC0938Ws.b((long) Math.ceil((map.size() + this.l) / 0.75f))));
        if (iMin > this.j) {
            f(iMin);
        }
        super.putAll(map);
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final int size() {
        return this.l;
    }

    @Override // java.util.SortedMap
    public final SortedMap subMap(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.SortedMap
    public final SortedMap tailMap(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, java.util.SortedMap
    public final Collection values() {
        if (this.o == null) {
            this.o = new C1317dT(this);
        }
        return this.o;
    }

    @Override // com.android.tools.r8.internal.U0
    /* JADX INFO: renamed from: e */
    public final JU entrySet() {
        if (this.m == null) {
            this.m = new C1745iT(this);
        }
        return this.m;
    }

    @Override // com.android.tools.r8.internal.U0
    public final int c(Object obj) {
        Object obj2;
        int i;
        if (obj == null) {
            if (this.f) {
                this.f = false;
                Object[] objArr = this.c;
                int i2 = this.j;
                objArr[i2] = null;
                int i3 = this.d[i2];
                this.l--;
                e(i2);
                if (this.l < this.k / 4 && (i = this.j) > 16) {
                    f(i / 2);
                }
                return i3;
            }
            return this.b;
        }
        Object[] objArr2 = this.c;
        int iA = AbstractC0938Ws.a(obj.hashCode()) & this.e;
        Object obj3 = objArr2[iA];
        if (obj3 == null) {
            return this.b;
        }
        if (obj.equals(obj3)) {
            return g(iA);
        }
        do {
            iA = (iA + 1) & this.e;
            obj2 = objArr2[iA];
            if (obj2 == null) {
                return this.b;
            }
        } while (!obj.equals(obj2));
        return g(iA);
    }

    @Override // com.android.tools.r8.internal.U0
    public final int b(Object obj) {
        Object obj2;
        if (obj == null) {
            return this.f ? this.d[this.j] : this.b;
        }
        Object[] objArr = this.c;
        int iA = AbstractC0938Ws.a(obj.hashCode()) & this.e;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return this.b;
        }
        if (obj.equals(obj3)) {
            return this.d[iA];
        }
        do {
            iA = (iA + 1) & this.e;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return this.b;
            }
        } while (!obj.equals(obj2));
        return this.d[iA];
    }
}
