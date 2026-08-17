package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1574gU extends W0 implements Cloneable {
    public transient ST e;
    public transient ST f;
    public transient QT g;
    public transient VT h;
    public transient RT i;
    public transient boolean j;
    public transient boolean[] k = new boolean[64];
    public transient ST[] l = new ST[64];
    public transient ST c = null;
    public int d = 0;

    @Override // com.android.tools.r8.internal.W0
    /* JADX INFO: renamed from: b */
    public final W0 headMap(Object obj) {
        return new C1318dU(this, null, true, obj, false);
    }

    @Override // com.android.tools.r8.internal.W0
    /* JADX INFO: renamed from: c */
    public final W0 tailMap(Object obj) {
        return new C1318dU(this, obj, false, null, true);
    }

    @Override // com.android.tools.r8.internal.W0, java.util.Map
    public final void clear() {
        this.d = 0;
        this.c = null;
        this.g = null;
        this.i = null;
        this.h = null;
        this.f = null;
        this.e = null;
    }

    public final Object clone() {
        try {
            C1574gU c1574gU = (C1574gU) super.clone();
            c1574gU.h = null;
            c1574gU.i = null;
            c1574gU.g = null;
            c1574gU.k = new boolean[64];
            c1574gU.l = new ST[64];
            if (this.d != 0) {
                ST st = new ST();
                ST st2 = new ST();
                ST st3 = this.c;
                st.f &= -1073741825;
                st.d = st3;
                st2.a((ST) null);
                ST st4 = st2;
                loop0: while (true) {
                    if (st.d()) {
                        while (true) {
                            boolean zF = st.f();
                            st = st.e;
                            if (!zF) {
                                st4 = st4.e;
                                break;
                            }
                            if (st == null) {
                                break loop0;
                            }
                            st4 = st4.e;
                        }
                    } else {
                        ST stM14clone = st.d.m14clone();
                        stM14clone.a(st4.d);
                        stM14clone.b(st4);
                        st4.f &= -1073741825;
                        st4.d = stM14clone;
                        st = st.d;
                        st4 = stM14clone;
                    }
                    if (!st.f()) {
                        ST stM14clone2 = st.e.m14clone();
                        stM14clone2.b(st4.e);
                        stM14clone2.a(st4);
                        st4.f &= Integer.MAX_VALUE;
                        st4.e = stM14clone2;
                    }
                }
                st4.e = null;
                ST st5 = st2.d;
                c1574gU.c = st5;
                c1574gU.e = st5;
                while (true) {
                    ST st6 = c1574gU.e.d;
                    if (st6 == null) {
                        break;
                    }
                    c1574gU.e = st6;
                }
                c1574gU.f = c1574gU.c;
                while (true) {
                    ST st7 = c1574gU.f.e;
                    if (st7 == null) {
                        break;
                    }
                    c1574gU.f = st7;
                }
            }
            return c1574gU;
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
        return d(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        C1489fU c1489fU = new C1489fU(this);
        int i = this.d;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            Object obj2 = c1489fU.a().c;
            if (obj2 == null) {
                if (obj == null) {
                    return true;
                }
            } else if (obj2.equals(obj)) {
                return true;
            }
            i = i2;
        }
    }

    public final ST d(Object obj) {
        ST st = this.c;
        while (st != null) {
            int iCompareTo = ((Comparable) obj).compareTo(st.b);
            if (iCompareTo == 0) {
                break;
            }
            int i = st.f;
            if (iCompareTo < 0) {
                st = (1073741824 & i) != 0 ? null : st.d;
            } else if ((Integer.MIN_VALUE & i) == 0) {
                st = st.e;
            }
        }
        return st;
    }

    @Override // com.android.tools.r8.internal.W0
    /* JADX INFO: renamed from: e */
    public final NU keySet() {
        if (this.h == null) {
            this.h = new VT(this);
        }
        return this.h;
    }

    @Override // java.util.SortedMap
    public final Object firstKey() {
        if (this.c != null) {
            return this.e.b;
        }
        z0e.a();
        return null;
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final Object get(Object obj) {
        ST stD = d(obj);
        return stD == null ? this.b : stD.c;
    }

    @Override // com.android.tools.r8.internal.W0
    public final NU i() {
        if (this.g == null) {
            this.g = new QT(this);
        }
        return this.g;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.d == 0;
    }

    @Override // java.util.SortedMap
    public final Object lastKey() {
        if (this.c != null) {
            return this.f.b;
        }
        z0e.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.W0, java.util.Map
    public final Object put(Object obj, Object obj2) {
        int i;
        ST st;
        ST st2;
        ST st3;
        int i2 = 0;
        this.j = false;
        ST st4 = this.c;
        if (st4 == null) {
            this.d++;
            st4 = new ST(obj, this.b);
            this.e = st4;
            this.f = st4;
            this.c = st4;
        } else {
            int i3 = 0;
            while (true) {
                int iCompareTo = ((Comparable) obj).compareTo(st4.b);
                if (iCompareTo == 0) {
                    while (true) {
                        int i4 = i3 - 1;
                        if (i3 == 0) {
                            break;
                        }
                        this.l[i4] = null;
                        i3 = i4;
                    }
                } else {
                    this.l[i3] = st4;
                    boolean[] zArr = this.k;
                    i = i3 + 1;
                    boolean z = iCompareTo > 0;
                    zArr[i3] = z;
                    if (z) {
                        if (st4.f()) {
                            this.d++;
                            st = new ST(obj, this.b);
                            ST st5 = st4.e;
                            if (st5 == null) {
                                this.f = st;
                            }
                            st.d = st4;
                            st.e = st5;
                            st4.f &= Integer.MAX_VALUE;
                            st4.e = st;
                            break;
                        }
                        st4 = st4.e;
                        i3 = i;
                    } else {
                        if (st4.d()) {
                            this.d++;
                            st = new ST(obj, this.b);
                            ST st6 = st4.d;
                            if (st6 == null) {
                                this.e = st;
                            }
                            st.e = st4;
                            st.d = st6;
                            st4.f &= -1073741825;
                            st4.d = st;
                            break;
                        }
                        st4 = st4.d;
                        i3 = i;
                    }
                }
                Object obj3 = st4.c;
                st4.c = obj2;
                return obj3;
            }
            st4 = st;
            this.j = true;
            while (i3 > 0 && !this.l[i3].b()) {
                int i5 = i3 - 1;
                boolean z2 = this.k[i5];
                ST[] stArr = this.l;
                if (z2) {
                    ST st7 = stArr[i5];
                    ST st8 = st7.d;
                    if (st7.d() || st8.b()) {
                        boolean z3 = this.k[i3];
                        ST[] stArr2 = this.l;
                        if (z3) {
                            st2 = stArr2[i3];
                        } else {
                            ST st9 = stArr2[i3];
                            ST st10 = st9.d;
                            st9.d = st10.e;
                            st10.e = st9;
                            stArr2[i5].e = st10;
                            if (st10.f()) {
                                st10.f = Integer.MAX_VALUE & st10.f;
                                st9.a(st10);
                            }
                            st2 = st10;
                        }
                        ST st11 = this.l[i5];
                        st11.a(false);
                        st2.a(true);
                        st11.e = st2.d;
                        st2.d = st11;
                        if (i3 < 2) {
                            this.c = st2;
                        } else {
                            int i6 = i3 - 2;
                            boolean z4 = this.k[i6];
                            ST[] stArr3 = this.l;
                            if (z4) {
                                stArr3[i6].e = st2;
                            } else {
                                stArr3[i6].d = st2;
                            }
                        }
                        if (!st2.d()) {
                            break;
                        }
                        st2.b(false);
                        st11.b(st2);
                        break;
                    }
                    this.l[i3].a(true);
                    st8.a(true);
                    this.l[i5].a(false);
                    i3 -= 2;
                } else {
                    ST st12 = stArr[i5];
                    ST st13 = st12.e;
                    if (st12.f() || st13.b()) {
                        boolean z5 = this.k[i3];
                        ST[] stArr4 = this.l;
                        if (z5) {
                            ST st14 = stArr4[i3];
                            ST st15 = st14.e;
                            st14.e = st15.d;
                            st15.d = st14;
                            stArr4[i5].d = st15;
                            if (st15.d()) {
                                st15.b(false);
                                st14.b(st15);
                            }
                            st3 = st15;
                        } else {
                            st3 = stArr4[i3];
                        }
                        ST st16 = this.l[i5];
                        st16.a(false);
                        st3.a(true);
                        st16.d = st3.e;
                        st3.e = st16;
                        if (i3 < 2) {
                            this.c = st3;
                        } else {
                            int i7 = i3 - 2;
                            boolean z6 = this.k[i7];
                            ST[] stArr5 = this.l;
                            if (z6) {
                                stArr5[i7].e = st3;
                            } else {
                                stArr5[i7].d = st3;
                            }
                        }
                        if (!st3.f()) {
                            break;
                        }
                        st3.f &= Integer.MAX_VALUE;
                        st16.a(st3);
                        break;
                    }
                    this.l[i3].a(true);
                    st13.a(true);
                    this.l[i5].a(false);
                    i3 -= 2;
                }
            }
            i2 = i;
        }
        this.c.a(true);
        while (true) {
            int i8 = i2 - 1;
            if (i2 == 0) {
                break;
            }
            this.l[i8] = null;
            i2 = i8;
        }
        Object obj4 = st4.c;
        st4.c = obj2;
        return obj4;
    }

    /* JADX WARN: Code duplicated, block: B:109:0x020c  */
    /* JADX WARN: Code duplicated, block: B:111:0x0228  */
    /* JADX WARN: Code duplicated, block: B:115:0x0258  */
    /* JADX WARN: Code duplicated, block: B:116:0x025b  */
    /* JADX WARN: Code duplicated, block: B:118:0x0262  */
    /* JADX WARN: Code duplicated, block: B:119:0x0267  */
    /* JADX WARN: Code duplicated, block: B:122:0x0271  */
    /* JADX WARN: Code duplicated, block: B:125:0x0283  */
    /* JADX WARN: Code duplicated, block: B:127:0x028d  */
    /* JADX WARN: Code duplicated, block: B:129:0x02a3  */
    /* JADX WARN: Code duplicated, block: B:130:0x02a6  */
    /* JADX WARN: Code duplicated, block: B:132:0x02ae  */
    /* JADX WARN: Code duplicated, block: B:133:0x02b3  */
    /* JADX WARN: Code duplicated, block: B:136:0x02c3  */
    /* JADX WARN: Code duplicated, block: B:151:0x02f8  */
    /* JADX WARN: Code duplicated, block: B:153:0x0314  */
    /* JADX WARN: Code duplicated, block: B:157:0x0342  */
    /* JADX WARN: Code duplicated, block: B:158:0x0345  */
    /* JADX WARN: Code duplicated, block: B:160:0x034c  */
    /* JADX WARN: Code duplicated, block: B:161:0x0351  */
    /* JADX WARN: Code duplicated, block: B:164:0x035b  */
    /* JADX WARN: Code duplicated, block: B:81:0x018e  */
    /* JADX WARN: Code duplicated, block: B:83:0x0197  */
    /* JADX WARN: Code duplicated, block: B:85:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:87:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:88:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:90:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:91:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:94:0x01d7  */
    @Override // java.util.Map
    public final Object remove(Object obj) {
        int i;
        ST st;
        boolean z;
        ST[] stArr;
        ST st2;
        ST st3;
        int i2;
        ST[] stArr2;
        int i3;
        ST[] stArr3;
        int i4;
        ST st4;
        ST st5;
        int i5;
        ST[] stArr4;
        int i6;
        ST[] stArr5;
        int i7;
        this.j = false;
        ST st6 = this.c;
        if (st6 == null) {
            return this.b;
        }
        int i8 = 0;
        while (true) {
            int iCompareTo = ((Comparable) obj).compareTo(st6.b);
            if (iCompareTo == 0) {
                if (st6.d == null) {
                    this.e = st6.c();
                }
                if (st6.e == null) {
                    this.f = st6.e();
                }
                if (!st6.f()) {
                    ST st7 = st6.e;
                    if (st7.d()) {
                        st7.d = st6.d;
                        st7.b(st6.d());
                        if (!st7.d()) {
                            st7.e().e = st7;
                        }
                        if (i8 == 0) {
                            this.c = st7;
                        } else {
                            int i9 = i8 - 1;
                            boolean z2 = this.k[i9];
                            ST[] stArr6 = this.l;
                            if (z2) {
                                stArr6[i9].e = st7;
                            } else {
                                stArr6[i9].d = st7;
                            }
                        }
                        boolean zB = st7.b();
                        st7.a(st6.b());
                        st6.a(zB);
                        this.k[i8] = true;
                        this.l[i8] = st7;
                        i8++;
                    } else {
                        int i10 = i8 + 1;
                        while (true) {
                            this.k[i10] = false;
                            i = i10 + 1;
                            this.l[i10] = st7;
                            st = st7.d;
                            if (st.d()) {
                                break;
                            }
                            st7 = st;
                            i10 = i;
                        }
                        this.k[i8] = true;
                        this.l[i8] = st;
                        if (st.f()) {
                            st7.a(st);
                        } else {
                            st7.d = st.e;
                        }
                        st.d = st6.d;
                        if (!st6.d()) {
                            st6.e().e = st;
                            st.b(false);
                        }
                        ST st8 = st6.e;
                        st.f &= Integer.MAX_VALUE;
                        st.e = st8;
                        boolean zB2 = st.b();
                        st.a(st6.b());
                        st6.a(zB2);
                        if (i8 == 0) {
                            this.c = st;
                        } else {
                            int i11 = i8 - 1;
                            boolean z3 = this.k[i11];
                            ST[] stArr7 = this.l;
                            if (z3) {
                                stArr7[i11].e = st;
                            } else {
                                stArr7[i11].d = st;
                            }
                        }
                        i8 = i;
                    }
                } else if (!st6.d()) {
                    st6.e().e = st6.e;
                    if (i8 == 0) {
                        this.c = st6.d;
                    } else {
                        int i12 = i8 - 1;
                        boolean z4 = this.k[i12];
                        ST[] stArr8 = this.l;
                        if (z4) {
                            stArr8[i12].e = st6.d;
                        } else {
                            stArr8[i12].d = st6.d;
                        }
                    }
                } else if (i8 == 0) {
                    this.c = st6.d;
                } else {
                    int i13 = i8 - 1;
                    boolean z5 = this.k[i13];
                    ST[] stArr9 = this.l;
                    if (z5) {
                        stArr9[i13].b(st6.e);
                    } else {
                        stArr9[i13].a(st6.d);
                    }
                }
                if (st6.b()) {
                    int i14 = i8;
                    while (true) {
                        if (i8 > 0) {
                            int i15 = i8 - 1;
                            if ((!this.k[i15] || this.l[i15].f()) && (this.k[i15] || this.l[i15].d())) {
                                z = this.k[i15];
                                stArr = this.l;
                                if (z) {
                                    st2 = stArr[i15].d;
                                    if (!st2.b()) {
                                        st2.a(true);
                                        this.l[i15].a(false);
                                        stArr3 = this.l;
                                        ST st9 = stArr3[i15];
                                        st9.d = st2.e;
                                        st2.e = st9;
                                        if (i8 < 2) {
                                            this.c = st2;
                                        } else {
                                            i4 = i8 - 2;
                                            if (this.k[i4]) {
                                                stArr3[i4].e = st2;
                                            } else {
                                                stArr3[i4].d = st2;
                                            }
                                        }
                                        stArr3[i8] = st9;
                                        this.k[i8] = true;
                                        stArr3[i15] = st2;
                                        int i16 = i8 + 1;
                                        if (i14 == i8) {
                                            i14++;
                                        }
                                        st2 = stArr3[i8].d;
                                        i8 = i16;
                                    }
                                    if ((!st2.d() || st2.d.b()) && (st2.f() || st2.e.b())) {
                                        st2.a(false);
                                        i8--;
                                    } else {
                                        if (st2.d() || st2.d.b()) {
                                            st3 = st2.e;
                                            st3.a(true);
                                            st2.a(false);
                                            st2.e = st3.d;
                                            st3.d = st2;
                                            this.l[i8 - 1].d = st3;
                                            if (st3.d()) {
                                                st3.b(false);
                                                st3.d.b(st3);
                                            }
                                            st2 = st3;
                                        }
                                        i2 = i8 - 1;
                                        st2.a(this.l[i2].b());
                                        this.l[i2].a(true);
                                        st2.d.a(true);
                                        stArr2 = this.l;
                                        ST st10 = stArr2[i2];
                                        st10.d = st2.e;
                                        st2.e = st10;
                                        if (i8 < 2) {
                                            this.c = st2;
                                        } else {
                                            i3 = i8 - 2;
                                            if (this.k[i3]) {
                                                stArr2[i3].e = st2;
                                            } else {
                                                stArr2[i3].d = st2;
                                            }
                                        }
                                        if (st2.f()) {
                                            st2.f &= Integer.MAX_VALUE;
                                            this.l[i2].a(st2);
                                        }
                                    }
                                } else {
                                    st4 = stArr[i15].e;
                                    if (!st4.b()) {
                                        st4.a(true);
                                        this.l[i15].a(false);
                                        stArr5 = this.l;
                                        ST st11 = stArr5[i15];
                                        st11.e = st4.d;
                                        st4.d = st11;
                                        if (i8 < 2) {
                                            this.c = st4;
                                        } else {
                                            i7 = i8 - 2;
                                            if (this.k[i7]) {
                                                stArr5[i7].e = st4;
                                            } else {
                                                stArr5[i7].d = st4;
                                            }
                                        }
                                        stArr5[i8] = st11;
                                        this.k[i8] = false;
                                        stArr5[i15] = st4;
                                        int i17 = i8 + 1;
                                        if (i14 == i8) {
                                            i14++;
                                        }
                                        st4 = stArr5[i8].e;
                                        i8 = i17;
                                    }
                                    if ((!st4.d() || st4.d.b()) && (st4.f() || st4.e.b())) {
                                        st4.a(false);
                                        i8--;
                                    } else {
                                        if (st4.f() || st4.e.b()) {
                                            st5 = st4.d;
                                            st5.a(true);
                                            st4.a(false);
                                            st4.d = st5.e;
                                            st5.e = st4;
                                            this.l[i8 - 1].e = st5;
                                            if (st5.f()) {
                                                st5.f &= Integer.MAX_VALUE;
                                                st5.e.a(st5);
                                            }
                                            st4 = st5;
                                        }
                                        i5 = i8 - 1;
                                        st4.a(this.l[i5].b());
                                        this.l[i5].a(true);
                                        st4.e.a(true);
                                        stArr4 = this.l;
                                        ST st12 = stArr4[i5];
                                        st12.e = st4.d;
                                        st4.d = st12;
                                        if (i8 < 2) {
                                            this.c = st4;
                                        } else {
                                            i6 = i8 - 2;
                                            if (this.k[i6]) {
                                                stArr4[i6].e = st4;
                                            } else {
                                                stArr4[i6].d = st4;
                                            }
                                        }
                                        if (st4.d()) {
                                            st4.b(false);
                                            this.l[i5].b(st4);
                                        }
                                    }
                                }
                            } else {
                                boolean z6 = this.k[i15];
                                ST[] stArr10 = this.l;
                                ST st13 = z6 ? stArr10[i15].e : stArr10[i15].d;
                                if (st13.b()) {
                                    z = this.k[i15];
                                    stArr = this.l;
                                    if (z) {
                                        st4 = stArr[i15].e;
                                        if (!st4.b()) {
                                            st4.a(true);
                                            this.l[i15].a(false);
                                            stArr5 = this.l;
                                            ST st14 = stArr5[i15];
                                            st14.e = st4.d;
                                            st4.d = st14;
                                            if (i8 < 2) {
                                                this.c = st4;
                                            } else {
                                                i7 = i8 - 2;
                                                if (this.k[i7]) {
                                                    stArr5[i7].e = st4;
                                                } else {
                                                    stArr5[i7].d = st4;
                                                }
                                            }
                                            stArr5[i8] = st14;
                                            this.k[i8] = false;
                                            stArr5[i15] = st4;
                                            int i18 = i8 + 1;
                                            if (i14 == i8) {
                                                i14++;
                                            }
                                            st4 = stArr5[i8].e;
                                            i8 = i18;
                                        }
                                        if (st4.d()) {
                                            st4.a(false);
                                            i8--;
                                        } else {
                                            st4.a(false);
                                            i8--;
                                        }
                                        if (st4.f()) {
                                            st5 = st4.d;
                                            st5.a(true);
                                            st4.a(false);
                                            st4.d = st5.e;
                                            st5.e = st4;
                                            this.l[i8 - 1].e = st5;
                                            if (st5.f()) {
                                                st5.f &= Integer.MAX_VALUE;
                                                st5.e.a(st5);
                                            }
                                            st4 = st5;
                                        } else {
                                            st5 = st4.d;
                                            st5.a(true);
                                            st4.a(false);
                                            st4.d = st5.e;
                                            st5.e = st4;
                                            this.l[i8 - 1].e = st5;
                                            if (st5.f()) {
                                                st5.f &= Integer.MAX_VALUE;
                                                st5.e.a(st5);
                                            }
                                            st4 = st5;
                                        }
                                        i5 = i8 - 1;
                                        st4.a(this.l[i5].b());
                                        this.l[i5].a(true);
                                        st4.e.a(true);
                                        stArr4 = this.l;
                                        ST st15 = stArr4[i5];
                                        st15.e = st4.d;
                                        st4.d = st15;
                                        if (i8 < 2) {
                                            this.c = st4;
                                        } else {
                                            i6 = i8 - 2;
                                            if (this.k[i6]) {
                                                stArr4[i6].e = st4;
                                            } else {
                                                stArr4[i6].d = st4;
                                            }
                                        }
                                        if (st4.d()) {
                                            st4.b(false);
                                            this.l[i5].b(st4);
                                        }
                                    } else {
                                        st2 = stArr[i15].d;
                                        if (!st2.b()) {
                                            st2.a(true);
                                            this.l[i15].a(false);
                                            stArr3 = this.l;
                                            ST st16 = stArr3[i15];
                                            st16.d = st2.e;
                                            st2.e = st16;
                                            if (i8 < 2) {
                                                this.c = st2;
                                            } else {
                                                i4 = i8 - 2;
                                                if (this.k[i4]) {
                                                    stArr3[i4].e = st2;
                                                } else {
                                                    stArr3[i4].d = st2;
                                                }
                                            }
                                            stArr3[i8] = st16;
                                            this.k[i8] = true;
                                            stArr3[i15] = st2;
                                            int i19 = i8 + 1;
                                            if (i14 == i8) {
                                                i14++;
                                            }
                                            st2 = stArr3[i8].d;
                                            i8 = i19;
                                        }
                                        if (st2.d()) {
                                            st2.a(false);
                                            i8--;
                                        } else {
                                            st2.a(false);
                                            i8--;
                                        }
                                        if (st2.d()) {
                                            st3 = st2.e;
                                            st3.a(true);
                                            st2.a(false);
                                            st2.e = st3.d;
                                            st3.d = st2;
                                            this.l[i8 - 1].d = st3;
                                            if (st3.d()) {
                                                st3.b(false);
                                                st3.d.b(st3);
                                            }
                                            st2 = st3;
                                        } else {
                                            st3 = st2.e;
                                            st3.a(true);
                                            st2.a(false);
                                            st2.e = st3.d;
                                            st3.d = st2;
                                            this.l[i8 - 1].d = st3;
                                            if (st3.d()) {
                                                st3.b(false);
                                                st3.d.b(st3);
                                            }
                                            st2 = st3;
                                        }
                                        i2 = i8 - 1;
                                        st2.a(this.l[i2].b());
                                        this.l[i2].a(true);
                                        st2.d.a(true);
                                        stArr2 = this.l;
                                        ST st17 = stArr2[i2];
                                        st17.d = st2.e;
                                        st2.e = st17;
                                        if (i8 < 2) {
                                            this.c = st2;
                                        } else {
                                            i3 = i8 - 2;
                                            if (this.k[i3]) {
                                                stArr2[i3].e = st2;
                                            } else {
                                                stArr2[i3].d = st2;
                                            }
                                        }
                                        if (st2.f()) {
                                            st2.f &= Integer.MAX_VALUE;
                                            this.l[i2].a(st2);
                                        }
                                    }
                                } else {
                                    st13.a(true);
                                }
                            }
                        }
                        i8 = i14;
                        ST st18 = this.c;
                        if (st18 != null) {
                            st18.a(true);
                        }
                    }
                }
                this.j = true;
                this.d--;
                while (true) {
                    int i20 = i8 - 1;
                    if (i8 == 0) {
                        return st6.c;
                    }
                    this.l[i20] = null;
                    i8 = i20;
                }
            } else {
                boolean[] zArr = this.k;
                boolean z7 = iCompareTo > 0;
                zArr[i8] = z7;
                this.l[i8] = st6;
                i8++;
                int i21 = st6.f;
                if (z7) {
                    st6 = (i21 & Integer.MIN_VALUE) != 0 ? null : st6.e;
                    if (st6 == null) {
                        while (true) {
                            int i22 = i8 - 1;
                            if (i8 == 0) {
                                return this.b;
                            }
                            this.l[i22] = null;
                            i8 = i22;
                        }
                    }
                } else {
                    st6 = (i21 & 1073741824) != 0 ? null : st6.d;
                    if (st6 == null) {
                        while (true) {
                            int i23 = i8 - 1;
                            if (i8 == 0) {
                                return this.b;
                            }
                            this.l[i23] = null;
                            i8 = i23;
                        }
                    }
                }
            }
        }
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final int size() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.W0, java.util.SortedMap
    public final SortedMap subMap(Object obj, Object obj2) {
        return new C1318dU(this, obj, false, obj2, false);
    }

    @Override // java.util.SortedMap, java.util.Map
    public final Collection values() {
        if (this.i == null) {
            this.i = new RT(this);
        }
        return this.i;
    }

    @Override // com.android.tools.r8.internal.W0
    /* JADX INFO: renamed from: b */
    public final W0 subMap(Object obj, Object obj2) {
        return new C1318dU(this, obj, false, obj2, false);
    }
}
