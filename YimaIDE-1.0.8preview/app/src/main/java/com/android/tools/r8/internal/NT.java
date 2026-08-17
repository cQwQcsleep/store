package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class NT extends W0 implements Cloneable {
    public transient Object[] c;
    public transient Object[] d;
    public transient int e;
    public transient boolean f;
    public InterfaceC0549Hs g;
    public transient int h = -1;
    public transient int i = -1;
    public transient long[] j;
    public transient int k;
    public transient int l;
    public int m;
    public transient KT n;
    public transient IT o;
    public transient FT p;

    public NT(InterfaceC0549Hs interfaceC0549Hs) {
        this.g = interfaceC0549Hs;
        int iA = AbstractC0938Ws.a(16, 0.75f);
        this.k = iA;
        this.e = iA - 1;
        this.l = AbstractC0938Ws.b(iA, 0.75f);
        int i = this.k + 1;
        this.c = new Object[i];
        this.d = new Object[i];
        this.j = new long[i];
    }

    @Override // com.android.tools.r8.internal.W0
    /* JADX INFO: renamed from: b */
    public final W0 headMap(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final void c(int i, int i2) {
        if (this.m == 1) {
            this.i = i2;
            this.h = i2;
            this.j[i2] = -1;
            return;
        }
        if (this.h == i) {
            this.h = i2;
            long[] jArr = this.j;
            int i3 = (int) jArr[i];
            long j = jArr[i3];
            jArr[i3] = ((-4294967296L) & (((4294967295L & ((long) i2)) << 32) ^ j)) ^ j;
            jArr[i2] = jArr[i];
            return;
        }
        if (this.i == i) {
            this.i = i2;
            long[] jArr2 = this.j;
            int i4 = (int) (jArr2[i] >>> 32);
            long j2 = jArr2[i4];
            jArr2[i4] = j2 ^ (((((long) i2) & 4294967295L) ^ j2) & 4294967295L);
            jArr2[i2] = jArr2[i];
            return;
        }
        long[] jArr3 = this.j;
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

    @Override // com.android.tools.r8.internal.W0, java.util.Map
    public final void clear() {
        if (this.m == 0) {
            return;
        }
        this.m = 0;
        this.f = false;
        Arrays.fill(this.c, (Object) null);
        Arrays.fill(this.d, (Object) null);
        this.i = -1;
        this.h = -1;
    }

    public final Object clone() {
        try {
            NT nt = (NT) super.clone();
            nt.o = null;
            nt.p = null;
            nt.n = null;
            nt.f = this.f;
            nt.c = (Object[]) this.c.clone();
            nt.d = (Object[]) this.d.clone();
            nt.j = (long[]) this.j.clone();
            nt.g = this.g;
            return nt;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // com.android.tools.r8.internal.W0, java.util.SortedMap
    public final Comparator comparator() {
        return null;
    }

    @Override // com.android.tools.r8.internal.W0, java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final boolean containsKey(Object obj) {
        Object obj2;
        if (this.g.a(obj, null)) {
            return this.f;
        }
        Object[] objArr = this.c;
        int iA = AbstractC0938Ws.a(this.g.a(obj)) & this.e;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return false;
        }
        if (this.g.a(obj, obj3)) {
            return true;
        }
        do {
            iA = (iA + 1) & this.e;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return false;
            }
        } while (!this.g.a(obj, obj2));
        return true;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        Object obj2;
        Object[] objArr = this.d;
        Object[] objArr2 = this.c;
        if (this.f && ((obj2 = objArr[this.k]) != null ? obj2.equals(obj) : obj == null)) {
            return true;
        }
        int i = this.k;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            if (objArr2[i2] != null) {
                Object obj3 = objArr[i2];
                if (obj3 == null) {
                    if (obj == null) {
                        return true;
                    }
                } else if (obj3.equals(obj)) {
                    return true;
                }
            }
            i = i2;
        }
    }

    public final void d(int i) {
        if (this.m == 0) {
            this.i = -1;
            this.h = -1;
            return;
        }
        if (this.h == i) {
            long[] jArr = this.j;
            int i2 = (int) jArr[i];
            this.h = i2;
            if (i2 >= 0) {
                jArr[i2] = (-4294967296L) | jArr[i2];
                return;
            }
            return;
        }
        int i3 = this.i;
        long[] jArr2 = this.j;
        if (i3 == i) {
            int i4 = (int) (jArr2[i] >>> 32);
            this.i = i4;
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
        Object[] objArr = this.c;
        Object[] objArr2 = this.d;
        int i3 = i - 1;
        int i4 = i + 1;
        Object[] objArr3 = new Object[i4];
        Object[] objArr4 = new Object[i4];
        int i5 = this.h;
        long[] jArr = this.j;
        long[] jArr2 = new long[i4];
        this.h = -1;
        int i6 = this.m;
        int i7 = -1;
        int i8 = -1;
        while (true) {
            int i9 = i6 - 1;
            if (i6 == 0) {
                break;
            }
            if (!this.g.a(objArr[i5], null)) {
                int iA = AbstractC0938Ws.a(this.g.a(objArr[i5]));
                while (true) {
                    i2 = iA & i3;
                    if (objArr3[i2] == null) {
                        break;
                    } else {
                        iA = i2 + 1;
                    }
                }
            } else {
                i2 = i;
            }
            objArr3[i2] = objArr[i5];
            objArr4[i2] = objArr2[i5];
            if (i8 != -1) {
                long j = jArr2[i7];
                jArr2[i7] = j ^ ((j ^ (((long) i2) & 4294967295L)) & 4294967295L);
                long j2 = jArr2[i2];
                jArr2[i2] = j2 ^ ((j2 ^ ((((long) i7) & 4294967295L) << 32)) & (-4294967296L));
            } else {
                this.h = i2;
                jArr2[i2] = -1;
            }
            i7 = i2;
            objArr2 = objArr2;
            i8 = i5;
            i5 = (int) jArr[i5];
            objArr = objArr;
            i6 = i9;
        }
        this.j = jArr2;
        this.i = i7;
        if (i7 != -1) {
            jArr2[i7] = jArr2[i7] | 4294967295L;
        }
        this.k = i;
        this.e = i3;
        this.l = AbstractC0938Ws.b(i, 0.75f);
        this.c = objArr3;
        this.d = objArr4;
    }

    public final Object f(int i) {
        Object obj;
        int i2;
        Object[] objArr = this.d;
        Object obj2 = objArr[i];
        objArr[i] = null;
        this.m--;
        d(i);
        Object[] objArr2 = this.c;
        loop0: while (true) {
            int i3 = (i + 1) & this.e;
            while (true) {
                obj = objArr2[i3];
                if (obj != null) {
                    int iA = AbstractC0938Ws.a(this.g.a(obj));
                    int i4 = this.e;
                    int i5 = iA & i4;
                    if (i > i3) {
                        if (i >= i5 && i5 > i3) {
                            break;
                        }
                        i3 = (i3 + 1) & i4;
                    } else {
                        if (i >= i5 || i5 > i3) {
                            break;
                        }
                        i3 = (i3 + 1) & i4;
                    }
                } else {
                    break loop0;
                }
            }
            objArr2[i] = obj;
            Object[] objArr3 = this.d;
            objArr3[i] = objArr3[i3];
            c(i3, i);
            i = i3;
        }
        objArr2[i] = null;
        this.d[i] = null;
        if (this.m < this.l / 4 && (i2 = this.k) > 16) {
            e(i2 / 2);
        }
        return obj2;
    }

    @Override // java.util.SortedMap
    public final Object firstKey() {
        if (this.m != 0) {
            return this.c[this.h];
        }
        z0e.a();
        return null;
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final Object get(Object obj) {
        Object obj2;
        if (this.g.a(obj, null)) {
            return this.f ? this.d[this.k] : this.b;
        }
        Object[] objArr = this.c;
        int iA = AbstractC0938Ws.a(this.g.a(obj)) & this.e;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return this.b;
        }
        if (this.g.a(obj, obj3)) {
            return this.d[iA];
        }
        do {
            iA = (iA + 1) & this.e;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return this.b;
            }
        } while (!this.g.a(obj, obj2));
        return this.d[iA];
    }

    @Override // com.android.tools.r8.internal.W0, java.util.Map
    public final int hashCode() {
        Object obj;
        boolean z = this.f;
        int i = this.m;
        if (z) {
            i--;
        }
        int i2 = 0;
        int i3 = 0;
        int iHashCode = 0;
        while (true) {
            int i4 = i - 1;
            if (i == 0) {
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
                iHashCode = this.g.a(obj);
            }
            Object obj2 = this.d[i2];
            if (this != obj2) {
                iHashCode = (obj2 == null ? 0 : obj2.hashCode()) ^ iHashCode;
            }
            i3 += iHashCode;
            i2++;
            i = i4;
        }
        if (!this.f) {
            return i3;
        }
        Object obj3 = this.d[this.k];
        return i3 + (obj3 != null ? obj3.hashCode() : 0);
    }

    @Override // com.android.tools.r8.internal.W0, java.util.SortedMap
    public final SortedMap headMap(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.W0
    public final NU i() {
        if (this.n == null) {
            this.n = new KT(this);
        }
        return this.n;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.m == 0;
    }

    public final Object j() {
        int i;
        this.f = false;
        Object[] objArr = this.c;
        int i2 = this.k;
        objArr[i2] = null;
        Object[] objArr2 = this.d;
        Object obj = objArr2[i2];
        objArr2[i2] = null;
        this.m--;
        d(i2);
        if (this.m < this.l / 4 && (i = this.k) > 16) {
            e(i / 2);
        }
        return obj;
    }

    @Override // java.util.SortedMap
    public final Object lastKey() {
        if (this.m != 0) {
            return this.c[this.i];
        }
        z0e.a();
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0052  */
    /* JADX WARN: Code duplicated, block: B:22:0x005d  */
    /* JADX WARN: Code duplicated, block: B:25:0x0081  */
    @Override // com.android.tools.r8.internal.W0, java.util.Map
    public final Object put(Object obj, Object obj2) {
        int i;
        int i2;
        int i3;
        if (!this.g.a(obj, null)) {
            Object[] objArr = this.c;
            int iA = AbstractC0938Ws.a(this.g.a(obj)) & this.e;
            Object obj3 = objArr[iA];
            if (obj3 != null) {
                if (!this.g.a(obj3, obj)) {
                    while (true) {
                        iA = (iA + 1) & this.e;
                        Object obj4 = objArr[iA];
                        if (obj4 != null) {
                            if (this.g.a(obj4, obj)) {
                            }
                        }
                    }
                }
                i2 = iA;
            }
            i = iA;
            this.c[i] = obj;
            this.d[i] = obj2;
            i3 = this.m;
            if (i3 == 0) {
                this.i = i;
                this.h = i;
                this.j[i] = -1;
            } else {
                long[] jArr = this.j;
                int i4 = this.i;
                long j = jArr[i4];
                jArr[i4] = j ^ (((((long) i) & 4294967295L) ^ j) & 4294967295L);
                jArr[i] = ((((long) i4) & 4294967295L) << 32) | 4294967295L;
                this.i = i;
            }
            this.m = i3 + 1;
            if (i3 >= this.l) {
                e(AbstractC0938Ws.a(i3 + 2, 0.75f));
            }
            i2 = -1;
        } else if (this.f) {
            i2 = this.k;
        } else {
            this.f = true;
            i = this.k;
            this.c[i] = obj;
            this.d[i] = obj2;
            i3 = this.m;
            if (i3 == 0) {
                this.i = i;
                this.h = i;
                this.j[i] = -1;
            } else {
                long[] jArr2 = this.j;
                int i5 = this.i;
                long j2 = jArr2[i5];
                jArr2[i5] = j2 ^ (((((long) i) & 4294967295L) ^ j2) & 4294967295L);
                jArr2[i] = ((((long) i5) & 4294967295L) << 32) | 4294967295L;
                this.i = i;
            }
            this.m = i3 + 1;
            if (i3 >= this.l) {
                e(AbstractC0938Ws.a(i3 + 2, 0.75f));
            }
            i2 = -1;
        }
        if (i2 < 0) {
            return this.b;
        }
        Object[] objArr2 = this.d;
        Object obj5 = objArr2[i2];
        objArr2[i2] = obj2;
        return obj5;
    }

    @Override // com.android.tools.r8.internal.W0, java.util.Map
    public final void putAll(Map map) {
        int iMin = (int) Math.min(1073741824L, Math.max(2L, AbstractC0938Ws.b((long) Math.ceil((map.size() + this.m) / 0.75f))));
        if (iMin > this.k) {
            e(iMin);
        }
        super.putAll(map);
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        Object obj2;
        if (this.g.a(obj, null)) {
            return this.f ? j() : this.b;
        }
        Object[] objArr = this.c;
        int iA = AbstractC0938Ws.a(this.g.a(obj)) & this.e;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return this.b;
        }
        if (this.g.a(obj, obj3)) {
            return f(iA);
        }
        do {
            iA = (iA + 1) & this.e;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return this.b;
            }
        } while (!this.g.a(obj, obj2));
        return f(iA);
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final int size() {
        return this.m;
    }

    @Override // com.android.tools.r8.internal.W0, java.util.SortedMap
    public final SortedMap subMap(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.W0, java.util.SortedMap
    public final SortedMap tailMap(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.SortedMap, java.util.Map
    public final Collection values() {
        if (this.p == null) {
            this.p = new FT(this);
        }
        return this.p;
    }

    @Override // com.android.tools.r8.internal.W0
    /* JADX INFO: renamed from: b */
    public final W0 subMap(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.W0
    /* JADX INFO: renamed from: c */
    public final W0 tailMap(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.W0
    /* JADX INFO: renamed from: e */
    public final NU keySet() {
        if (this.o == null) {
            this.o = new IT(this);
        }
        return this.o;
    }
}
