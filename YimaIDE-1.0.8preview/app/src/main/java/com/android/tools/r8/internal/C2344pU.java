package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2344pU implements Cloneable, SortedMap, InterfaceC2294or, Serializable, Map {
    public transient Object[] b;
    public transient Object[] c;
    public transient int d;
    public transient boolean e;
    public transient int f = -1;
    public transient int g = -1;
    public transient long[] h;
    public transient int i;
    public transient int j;
    public int k;
    public transient C2087mU l;
    public transient C1915kU m;
    public transient C1660hU n;

    public C2344pU(int i) {
        if (i < 0) {
            w01.a("The expected number of elements must be nonnegative");
            throw null;
        }
        int iA = AbstractC0938Ws.a(i, 0.75f);
        this.i = iA;
        this.d = iA - 1;
        this.j = AbstractC0938Ws.b(iA, 0.75f);
        int i2 = this.i + 1;
        this.b = new Object[i2];
        this.c = new Object[i2];
        this.h = new long[i2];
    }

    public final void a(Map map) {
        int size = map.size();
        Iterator it = map.entrySet().iterator();
        if (map instanceof C2344pU) {
            while (true) {
                int i = size - 1;
                if (size == 0) {
                    return;
                }
                C2001lU c2001lU = (C2001lU) it.next();
                C2344pU c2344pU = c2001lU.c;
                Object[] objArr = c2344pU.b;
                int i2 = c2001lU.b;
                put(objArr[i2], c2344pU.c[i2]);
                size = i;
            }
        } else {
            while (true) {
                int i3 = size - 1;
                if (size == 0) {
                    return;
                }
                Map.Entry entry = (Map.Entry) it.next();
                put(entry.getKey(), entry.getValue());
                size = i3;
            }
        }
    }

    public final void c(int i, int i2) {
        if (this.k == 1) {
            this.g = i2;
            this.f = i2;
            this.h[i2] = -1;
            return;
        }
        if (this.f == i) {
            this.f = i2;
            long[] jArr = this.h;
            int i3 = (int) jArr[i];
            long j = jArr[i3];
            jArr[i3] = ((-4294967296L) & (((4294967295L & ((long) i2)) << 32) ^ j)) ^ j;
            jArr[i2] = jArr[i];
            return;
        }
        if (this.g == i) {
            this.g = i2;
            long[] jArr2 = this.h;
            int i4 = (int) (jArr2[i] >>> 32);
            long j2 = jArr2[i4];
            jArr2[i4] = j2 ^ (((((long) i2) & 4294967295L) ^ j2) & 4294967295L);
            jArr2[i2] = jArr2[i];
            return;
        }
        long[] jArr3 = this.h;
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
        if (this.k == 0) {
            return;
        }
        this.k = 0;
        this.e = false;
        Arrays.fill(this.b, (Object) null);
        Arrays.fill(this.c, (Object) null);
        this.g = -1;
        this.f = -1;
    }

    public final Object clone() {
        try {
            C2344pU c2344pU = (C2344pU) super.clone();
            c2344pU.m = null;
            c2344pU.n = null;
            c2344pU.l = null;
            c2344pU.e = this.e;
            c2344pU.b = (Object[]) this.b.clone();
            c2344pU.c = (Object[]) this.c.clone();
            c2344pU.h = (long[]) this.h.clone();
            return c2344pU;
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
            return this.e;
        }
        Object[] objArr = this.b;
        int iA = AbstractC0938Ws.a(obj.hashCode()) & this.d;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return false;
        }
        if (obj.equals(obj3)) {
            return true;
        }
        do {
            iA = (iA + 1) & this.d;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return false;
            }
        } while (!obj.equals(obj2));
        return true;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        Object[] objArr = this.c;
        Object[] objArr2 = this.b;
        if (this.e && objArr[this.i] == obj) {
            return true;
        }
        int i = this.i;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            if (objArr2[i2] != null && objArr[i2] == obj) {
                return true;
            }
            i = i2;
        }
    }

    public final void d(int i) {
        if (this.k == 0) {
            this.g = -1;
            this.f = -1;
            return;
        }
        if (this.f == i) {
            long[] jArr = this.h;
            int i2 = (int) jArr[i];
            this.f = i2;
            if (i2 >= 0) {
                jArr[i2] = (-4294967296L) | jArr[i2];
                return;
            }
            return;
        }
        int i3 = this.g;
        long[] jArr2 = this.h;
        if (i3 == i) {
            int i4 = (int) (jArr2[i] >>> 32);
            this.g = i4;
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
        Object[] objArr = this.b;
        Object[] objArr2 = this.c;
        int i3 = i - 1;
        int i4 = i + 1;
        Object[] objArr3 = new Object[i4];
        Object[] objArr4 = new Object[i4];
        int i5 = this.f;
        long[] jArr = this.h;
        long[] jArr2 = new long[i4];
        int i6 = -1;
        this.f = -1;
        int i7 = this.k;
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
            if (i9 != i6) {
                long j = jArr2[i8];
                jArr2[i8] = j ^ ((j ^ (((long) i2) & 4294967295L)) & 4294967295L);
                long j2 = jArr2[i2];
                int i11 = i2;
                jArr2[i11] = j2 ^ ((((((long) i8) & 4294967295L) << 32) ^ j2) & (-4294967296L));
                i2 = i11;
            } else {
                this.f = i2;
                jArr2[i2] = -1;
            }
            int i12 = i5;
            i5 = (int) jArr[i5];
            objArr = objArr;
            i9 = i12;
            i8 = i2;
            i7 = i10;
            objArr2 = objArr2;
            i6 = -1;
        }
        this.h = jArr2;
        this.g = i8;
        if (i8 != -1) {
            jArr2[i8] = jArr2[i8] | 4294967295L;
        }
        this.i = i;
        this.d = i3;
        this.j = AbstractC0938Ws.b(i, 0.75f);
        this.b = objArr3;
        this.c = objArr4;
    }

    @Override // java.util.SortedMap, java.util.Map
    public final Set entrySet() {
        if (this.l == null) {
            this.l = new C2087mU(this);
        }
        return this.l;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (map.size() != this.k) {
            return false;
        }
        if (this.l == null) {
            this.l = new C2087mU(this);
        }
        return this.l.containsAll(map.entrySet());
    }

    public final Object f(int i) {
        Object obj;
        int i2;
        Object[] objArr = this.c;
        Object obj2 = objArr[i];
        objArr[i] = null;
        this.k--;
        d(i);
        Object[] objArr2 = this.b;
        loop0: while (true) {
            int i3 = (i + 1) & this.d;
            while (true) {
                obj = objArr2[i3];
                if (obj != null) {
                    int iA = AbstractC0938Ws.a(obj.hashCode());
                    int i4 = this.d;
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
            Object[] objArr3 = this.c;
            objArr3[i] = objArr3[i3];
            c(i3, i);
            i = i3;
        }
        objArr2[i] = null;
        this.c[i] = null;
        if (this.k < this.j / 4 && (i2 = this.i) > 16) {
            e(i2 / 2);
        }
        return obj2;
    }

    @Override // java.util.SortedMap
    public final Object firstKey() {
        if (this.k != 0) {
            return this.b[this.f];
        }
        z0e.a();
        return null;
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final Object get(Object obj) {
        Object obj2;
        if (obj == null) {
            if (this.e) {
                return this.c[this.i];
            }
            return null;
        }
        Object[] objArr = this.b;
        int iA = AbstractC0938Ws.a(obj.hashCode()) & this.d;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return null;
        }
        if (obj.equals(obj3)) {
            return this.c[iA];
        }
        do {
            iA = (iA + 1) & this.d;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return null;
            }
        } while (!obj.equals(obj2));
        return this.c[iA];
    }

    @Override // java.util.Map
    public final int hashCode() {
        Object obj;
        boolean z = this.e;
        int i = this.k;
        if (z) {
            i--;
        }
        int i2 = 0;
        int i3 = 0;
        int iIdentityHashCode = 0;
        while (true) {
            int i4 = i - 1;
            if (i == 0) {
                break;
            }
            while (true) {
                obj = this.b[i2];
                if (obj != null) {
                    break;
                }
                i2++;
            }
            if (this != obj) {
                iIdentityHashCode = obj.hashCode();
            }
            Object obj2 = this.c[i2];
            if (this != obj2) {
                iIdentityHashCode = (obj2 == null ? 0 : System.identityHashCode(obj2)) ^ iIdentityHashCode;
            }
            i3 += iIdentityHashCode;
            i2++;
            i = i4;
        }
        if (!this.e) {
            return i3;
        }
        Object obj3 = this.c[this.i];
        return i3 + (obj3 != null ? System.identityHashCode(obj3) : 0);
    }

    @Override // java.util.SortedMap
    public final SortedMap headMap(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.k == 0;
    }

    @Override // java.util.SortedMap, java.util.Map
    public final Set keySet() {
        if (this.m == null) {
            this.m = new C1915kU(this);
        }
        return this.m;
    }

    @Override // java.util.SortedMap
    public final Object lastKey() {
        if (this.k != 0) {
            return this.b[this.g];
        }
        z0e.a();
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0045  */
    /* JADX WARN: Code duplicated, block: B:22:0x0050  */
    /* JADX WARN: Code duplicated, block: B:25:0x0074  */
    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        int i;
        int i2;
        int i3;
        if (obj != null) {
            Object[] objArr = this.b;
            int iA = AbstractC0938Ws.a(obj.hashCode()) & this.d;
            Object obj3 = objArr[iA];
            if (obj3 != null) {
                if (!obj3.equals(obj)) {
                    while (true) {
                        iA = (iA + 1) & this.d;
                        Object obj4 = objArr[iA];
                        if (obj4 != null) {
                            if (obj4.equals(obj)) {
                            }
                        }
                    }
                }
                i2 = iA;
            }
            i = iA;
            this.b[i] = obj;
            this.c[i] = obj2;
            i3 = this.k;
            if (i3 == 0) {
                this.g = i;
                this.f = i;
                this.h[i] = -1;
            } else {
                long[] jArr = this.h;
                int i4 = this.g;
                long j = jArr[i4];
                jArr[i4] = j ^ (((((long) i) & 4294967295L) ^ j) & 4294967295L);
                jArr[i] = ((((long) i4) & 4294967295L) << 32) | 4294967295L;
                this.g = i;
            }
            this.k = i3 + 1;
            if (i3 >= this.j) {
                e(AbstractC0938Ws.a(i3 + 2, 0.75f));
            }
            i2 = -1;
        } else if (this.e) {
            i2 = this.i;
        } else {
            this.e = true;
            i = this.i;
            this.b[i] = obj;
            this.c[i] = obj2;
            i3 = this.k;
            if (i3 == 0) {
                this.g = i;
                this.f = i;
                this.h[i] = -1;
            } else {
                long[] jArr2 = this.h;
                int i5 = this.g;
                long j2 = jArr2[i5];
                jArr2[i5] = j2 ^ (((((long) i) & 4294967295L) ^ j2) & 4294967295L);
                jArr2[i] = ((((long) i5) & 4294967295L) << 32) | 4294967295L;
                this.g = i;
            }
            this.k = i3 + 1;
            if (i3 >= this.j) {
                e(AbstractC0938Ws.a(i3 + 2, 0.75f));
            }
            i2 = -1;
        }
        if (i2 < 0) {
            return null;
        }
        Object[] objArr2 = this.c;
        Object obj5 = objArr2[i2];
        objArr2[i2] = obj2;
        return obj5;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        int iMin = (int) Math.min(1073741824L, Math.max(2L, AbstractC0938Ws.b((long) Math.ceil((map.size() + this.k) / 0.75f))));
        if (iMin > this.i) {
            e(iMin);
        }
        a(map);
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        Object obj2;
        if (obj == null) {
            if (this.e) {
                return e();
            }
            return null;
        }
        Object[] objArr = this.b;
        int iA = AbstractC0938Ws.a(obj.hashCode()) & this.d;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return null;
        }
        if (obj.equals(obj3)) {
            return f(iA);
        }
        do {
            iA = (iA + 1) & this.d;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return null;
            }
        } while (!obj.equals(obj2));
        return f(iA);
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final int size() {
        return this.k;
    }

    @Override // java.util.SortedMap
    public final SortedMap subMap(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.SortedMap
    public final SortedMap tailMap(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (this.l == null) {
            this.l = new C2087mU(this);
        }
        BU it = this.l.iterator();
        int i = this.k;
        boolean z = true;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                sb.append("}");
                return sb.toString();
            }
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            C2001lU c2001lU = (C2001lU) ((C1746iU) it).next();
            Object obj = c2001lU.c.b[c2001lU.b];
            if (this == obj) {
                sb.append("(this map)");
            } else {
                sb.append(String.valueOf(obj));
            }
            sb.append("=>");
            Object obj2 = c2001lU.c.c[c2001lU.b];
            if (this == obj2) {
                sb.append("(this map)");
            } else {
                sb.append(String.valueOf(obj2));
            }
            i = i2;
        }
    }

    @Override // java.util.SortedMap, java.util.Map
    public final Collection values() {
        if (this.n == null) {
            this.n = new C1660hU(this);
        }
        return this.n;
    }

    public final Object e() {
        int i;
        this.e = false;
        Object[] objArr = this.b;
        int i2 = this.i;
        objArr[i2] = null;
        Object[] objArr2 = this.c;
        Object obj = objArr2[i2];
        objArr2[i2] = null;
        this.k--;
        d(i2);
        if (this.k < this.j / 4 && (i = this.i) > 16) {
            e(i / 2);
        }
        return obj;
    }
}
