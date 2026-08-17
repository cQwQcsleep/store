package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2214nx extends J implements Cloneable, InterfaceC2386px, SortedMap {
    public transient int[] c;
    public transient int[] d;
    public transient int e;
    public transient boolean f;
    public transient int g;
    public transient int h;
    public transient long[] i;
    public transient int j;
    public transient int k;
    public int l;
    public transient C1957kx m;
    public transient C1786ix n;
    public transient C1531fx o;

    public C2214nx(int i) {
        this.g = -1;
        this.h = -1;
        if (i < 0) {
            w01.a("The expected number of elements must be nonnegative");
            throw null;
        }
        int iA = AbstractC0938Ws.a(i, 0.75f);
        this.j = iA;
        this.e = iA - 1;
        this.k = AbstractC0938Ws.b(iA, 0.75f);
        int i2 = this.j + 1;
        this.c = new int[i2];
        this.d = new int[i2];
        this.i = new long[i2];
    }

    @Override // com.android.tools.r8.internal.InterfaceC1445ex
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

    /* JADX WARN: Code duplicated, block: B:19:0x0038  */
    /* JADX WARN: Code duplicated, block: B:20:0x0043  */
    /* JADX WARN: Code duplicated, block: B:23:0x0067  */
    @Override // com.android.tools.r8.internal.J, com.android.tools.r8.internal.InterfaceC1445ex
    public final int b(int i, int i2) {
        int i3;
        int i4;
        int i5;
        if (i != 0) {
            int[] iArr = this.c;
            int iA = AbstractC0938Ws.a(i) & this.e;
            int i6 = iArr[iA];
            if (i6 != 0) {
                if (i6 != i) {
                    while (true) {
                        iA = (iA + 1) & this.e;
                        int i7 = iArr[iA];
                        if (i7 != 0) {
                            if (i7 == i) {
                            }
                        }
                    }
                }
                i4 = iA;
            }
            i3 = iA;
            this.c[i3] = i;
            this.d[i3] = i2;
            i5 = this.l;
            if (i5 == 0) {
                this.h = i3;
                this.g = i3;
                this.i[i3] = -1;
            } else {
                long[] jArr = this.i;
                int i8 = this.h;
                long j = jArr[i8];
                jArr[i8] = j ^ (((((long) i3) & 4294967295L) ^ j) & 4294967295L);
                jArr[i3] = ((((long) i8) & 4294967295L) << 32) | 4294967295L;
                this.h = i3;
            }
            this.l = i5 + 1;
            if (i5 >= this.k) {
                f(AbstractC0938Ws.a(i5 + 2, 0.75f));
            }
            i4 = -1;
        } else if (this.f) {
            i4 = this.j;
        } else {
            this.f = true;
            i3 = this.j;
            this.c[i3] = i;
            this.d[i3] = i2;
            i5 = this.l;
            if (i5 == 0) {
                this.h = i3;
                this.g = i3;
                this.i[i3] = -1;
            } else {
                long[] jArr2 = this.i;
                int i9 = this.h;
                long j2 = jArr2[i9];
                jArr2[i9] = j2 ^ (((((long) i3) & 4294967295L) ^ j2) & 4294967295L);
                jArr2[i3] = ((((long) i9) & 4294967295L) << 32) | 4294967295L;
                this.h = i3;
            }
            this.l = i5 + 1;
            if (i5 >= this.k) {
                f(AbstractC0938Ws.a(i5 + 2, 0.75f));
            }
            i4 = -1;
        }
        if (i4 < 0) {
            return this.b;
        }
        int[] iArr2 = this.d;
        int i10 = iArr2[i4];
        iArr2[i4] = i2;
        return i10;
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
        Arrays.fill(this.c, 0);
        this.h = -1;
        this.g = -1;
    }

    public final Object clone() {
        try {
            C2214nx c2214nx = (C2214nx) super.clone();
            c2214nx.n = null;
            c2214nx.o = null;
            c2214nx.m = null;
            c2214nx.f = this.f;
            c2214nx.c = (int[]) this.c.clone();
            c2214nx.d = (int[]) this.d.clone();
            c2214nx.i = (long[]) this.i.clone();
            return c2214nx;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // java.util.SortedMap
    public final /* bridge */ /* synthetic */ Comparator comparator() {
        return null;
    }

    @Override // com.android.tools.r8.internal.J
    public final boolean d(int i) {
        int[] iArr = this.d;
        int[] iArr2 = this.c;
        if (this.f && iArr[this.j] == i) {
            return true;
        }
        int i2 = this.j;
        while (true) {
            int i3 = i2 - 1;
            if (i2 == 0) {
                return false;
            }
            if (iArr2[i3] != 0 && iArr[i3] == i) {
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

    @Override // com.android.tools.r8.internal.J, java.util.Map
    public final Set entrySet() {
        if (this.m == null) {
            this.m = new C1957kx(this);
        }
        return this.m;
    }

    public final void f(int i) {
        int i2;
        int[] iArr = this.c;
        int[] iArr2 = this.d;
        int i3 = i - 1;
        int i4 = i + 1;
        int[] iArr3 = new int[i4];
        int[] iArr4 = new int[i4];
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
                    if (iArr3[i2] == 0) {
                        break;
                    } else {
                        iA = i2 + 1;
                    }
                }
            } else {
                i2 = i;
            }
            iArr3[i2] = iArr[i5];
            iArr4[i2] = iArr2[i5];
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
            iArr2 = iArr2;
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
        this.c = iArr3;
        this.d = iArr4;
    }

    @Override // java.util.SortedMap
    public final Object firstKey() {
        if (this.l != 0) {
            return Integer.valueOf(this.c[this.g]);
        }
        z0e.a();
        return null;
    }

    public final int g(int i) {
        int i2;
        int i3;
        int i4 = this.d[i];
        this.l--;
        e(i);
        int[] iArr = this.c;
        loop0: while (true) {
            int i5 = (i + 1) & this.e;
            while (true) {
                i2 = iArr[i5];
                if (i2 != 0) {
                    int iA = AbstractC0938Ws.a(i2);
                    int i6 = this.e;
                    int i7 = iA & i6;
                    if (i > i5) {
                        if (i >= i7 && i7 > i5) {
                            break;
                        }
                        i5 = (i5 + 1) & i6;
                    } else {
                        if (i >= i7 || i7 > i5) {
                            break;
                        }
                        i5 = (i5 + 1) & i6;
                    }
                } else {
                    break loop0;
                }
            }
            iArr[i] = i2;
            int[] iArr2 = this.d;
            iArr2[i] = iArr2[i5];
            c(i5, i);
            i = i5;
        }
        iArr[i] = 0;
        if (this.l < this.k / 4 && (i3 = this.j) > 16) {
            f(i3 / 2);
        }
        return i4;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1445ex
    public final int get(int i) {
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

    @Override // com.android.tools.r8.internal.InterfaceC2386px
    public final JU h() {
        if (this.m == null) {
            this.m = new C1957kx(this);
        }
        return this.m;
    }

    @Override // com.android.tools.r8.internal.J, java.util.Map
    public final int hashCode() {
        int i;
        boolean z = this.f;
        int i2 = this.l;
        if (z) {
            i2--;
        }
        int i3 = 0;
        int i4 = i2;
        int i5 = 0;
        while (true) {
            int i6 = i4 - 1;
            if (i4 == 0) {
                break;
            }
            while (true) {
                i = this.c[i5];
                if (i == 0) {
                    i5++;
                }
            }
            i3 += i ^ this.d[i5];
            i5++;
            i4 = i6;
        }
        return this.f ? i3 + this.d[this.j] : i3;
    }

    @Override // java.util.SortedMap
    public final SortedMap headMap(Object obj) {
        ((Integer) obj).getClass();
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.l == 0;
    }

    @Override // java.util.Map, java.util.SortedMap
    public final CA keySet() {
        if (this.n == null) {
            this.n = new C1786ix(this);
        }
        return this.n;
    }

    @Override // java.util.SortedMap
    public final Object lastKey() {
        if (this.l != 0) {
            return Integer.valueOf(this.c[this.h]);
        }
        z0e.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.J, java.util.Map
    public final void putAll(Map map) {
        int iMin = (int) Math.min(1073741824L, Math.max(2L, AbstractC0938Ws.b((long) Math.ceil((map.size() + this.l) / 0.75f))));
        if (iMin > this.j) {
            f(iMin);
        }
        super.putAll(map);
    }

    @Override // com.android.tools.r8.internal.J, com.android.tools.r8.internal.InterfaceC1445ex
    public final int remove(int i) {
        int i2;
        int i3;
        if (i == 0) {
            if (!this.f) {
                return this.b;
            }
            this.f = false;
            int[] iArr = this.d;
            int i4 = this.j;
            int i5 = iArr[i4];
            this.l--;
            e(i4);
            if (this.l < this.k / 4 && (i3 = this.j) > 16) {
                f(i3 / 2);
            }
            return i5;
        }
        int[] iArr2 = this.c;
        int iA = AbstractC0938Ws.a(i) & this.e;
        int i6 = iArr2[iA];
        if (i6 == 0) {
            return this.b;
        }
        if (i == i6) {
            return g(iA);
        }
        do {
            iA = (iA + 1) & this.e;
            i2 = iArr2[iA];
            if (i2 == 0) {
                return this.b;
            }
        } while (i != i2);
        return g(iA);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        return this.l;
    }

    @Override // java.util.SortedMap
    public final SortedMap subMap(Object obj, Object obj2) {
        ((Integer) obj).getClass();
        ((Integer) obj2).getClass();
        throw new UnsupportedOperationException();
    }

    @Override // java.util.SortedMap
    public final SortedMap tailMap(Object obj) {
        ((Integer) obj).getClass();
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, java.util.SortedMap
    public final Collection values() {
        if (this.o == null) {
            this.o = new C1531fx(this);
        }
        return this.o;
    }

    public C2214nx() {
        this(16);
    }

    @Override // com.android.tools.r8.internal.J
    /* JADX INFO: renamed from: e */
    public final JU entrySet() {
        if (this.m == null) {
            this.m = new C1957kx(this);
        }
        return this.m;
    }
}
