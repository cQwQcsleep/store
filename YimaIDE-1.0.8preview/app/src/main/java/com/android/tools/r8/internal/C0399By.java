package com.android.tools.r8.internal;

import java.util.Comparator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.By, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0399By extends O implements Cloneable {
    public transient C2215ny e;
    public transient C2215ny f;
    public transient C2044ly g;
    public transient C2472qy h;
    public transient C2129my i;
    public transient boolean j;
    public transient boolean[] k = new boolean[64];
    public transient C2215ny[] l = new C2215ny[64];
    public transient C2215ny c = null;
    public int d = 0;

    @Override // com.android.tools.r8.internal.K, com.android.tools.r8.internal.InterfaceC0917Vx
    public final Object a(int i, Object obj) {
        int i2;
        C2215ny c2215ny;
        C2215ny c2215ny2;
        C2215ny c2215ny3;
        int i3 = 0;
        this.j = false;
        C2215ny c2215ny4 = this.c;
        if (c2215ny4 == null) {
            this.d++;
            c2215ny4 = new C2215ny(i, this.b);
            this.e = c2215ny4;
            this.f = c2215ny4;
            this.c = c2215ny4;
        } else {
            int i4 = 0;
            while (true) {
                int iCompare = Integer.compare(i, c2215ny4.b);
                if (iCompare == 0) {
                    while (true) {
                        int i5 = i4 - 1;
                        if (i4 == 0) {
                            break;
                        }
                        this.l[i5] = null;
                        i4 = i5;
                    }
                } else {
                    this.l[i4] = c2215ny4;
                    boolean[] zArr = this.k;
                    i2 = i4 + 1;
                    boolean z = iCompare > 0;
                    zArr[i4] = z;
                    if (z) {
                        if (c2215ny4.f()) {
                            this.d++;
                            c2215ny = new C2215ny(i, this.b);
                            C2215ny c2215ny5 = c2215ny4.e;
                            if (c2215ny5 == null) {
                                this.f = c2215ny;
                            }
                            c2215ny.d = c2215ny4;
                            c2215ny.e = c2215ny5;
                            c2215ny4.f &= Integer.MAX_VALUE;
                            c2215ny4.e = c2215ny;
                            break;
                        }
                        c2215ny4 = c2215ny4.e;
                        i4 = i2;
                    } else {
                        if (c2215ny4.d()) {
                            this.d++;
                            c2215ny = new C2215ny(i, this.b);
                            C2215ny c2215ny6 = c2215ny4.d;
                            if (c2215ny6 == null) {
                                this.e = c2215ny;
                            }
                            c2215ny.e = c2215ny4;
                            c2215ny.d = c2215ny6;
                            c2215ny4.f &= -1073741825;
                            c2215ny4.d = c2215ny;
                            break;
                        }
                        c2215ny4 = c2215ny4.d;
                        i4 = i2;
                    }
                }
                Object obj2 = c2215ny4.c;
                c2215ny4.c = obj;
                return obj2;
            }
            c2215ny4 = c2215ny;
            this.j = true;
            while (i4 > 0 && !this.l[i4].b()) {
                int i6 = i4 - 1;
                boolean z2 = this.k[i6];
                C2215ny[] c2215nyArr = this.l;
                if (z2) {
                    C2215ny c2215ny7 = c2215nyArr[i6];
                    C2215ny c2215ny8 = c2215ny7.d;
                    if (c2215ny7.d() || c2215ny8.b()) {
                        boolean z3 = this.k[i4];
                        C2215ny[] c2215nyArr2 = this.l;
                        if (z3) {
                            c2215ny2 = c2215nyArr2[i4];
                        } else {
                            C2215ny c2215ny9 = c2215nyArr2[i4];
                            C2215ny c2215ny10 = c2215ny9.d;
                            c2215ny9.d = c2215ny10.e;
                            c2215ny10.e = c2215ny9;
                            c2215nyArr2[i6].e = c2215ny10;
                            if (c2215ny10.f()) {
                                c2215ny10.f = Integer.MAX_VALUE & c2215ny10.f;
                                c2215ny9.a(c2215ny10);
                            }
                            c2215ny2 = c2215ny10;
                        }
                        C2215ny c2215ny11 = this.l[i6];
                        c2215ny11.a(false);
                        c2215ny2.a(true);
                        c2215ny11.e = c2215ny2.d;
                        c2215ny2.d = c2215ny11;
                        if (i4 < 2) {
                            this.c = c2215ny2;
                        } else {
                            int i7 = i4 - 2;
                            boolean z4 = this.k[i7];
                            C2215ny[] c2215nyArr3 = this.l;
                            if (z4) {
                                c2215nyArr3[i7].e = c2215ny2;
                            } else {
                                c2215nyArr3[i7].d = c2215ny2;
                            }
                        }
                        if (!c2215ny2.d()) {
                            break;
                        }
                        c2215ny2.b(false);
                        c2215ny11.b(c2215ny2);
                        break;
                    }
                    this.l[i4].a(true);
                    c2215ny8.a(true);
                    this.l[i6].a(false);
                    i4 -= 2;
                } else {
                    C2215ny c2215ny12 = c2215nyArr[i6];
                    C2215ny c2215ny13 = c2215ny12.e;
                    if (c2215ny12.f() || c2215ny13.b()) {
                        boolean z5 = this.k[i4];
                        C2215ny[] c2215nyArr4 = this.l;
                        if (z5) {
                            C2215ny c2215ny14 = c2215nyArr4[i4];
                            C2215ny c2215ny15 = c2215ny14.e;
                            c2215ny14.e = c2215ny15.d;
                            c2215ny15.d = c2215ny14;
                            c2215nyArr4[i6].d = c2215ny15;
                            if (c2215ny15.d()) {
                                c2215ny15.b(false);
                                c2215ny14.b(c2215ny15);
                            }
                            c2215ny3 = c2215ny15;
                        } else {
                            c2215ny3 = c2215nyArr4[i4];
                        }
                        C2215ny c2215ny16 = this.l[i6];
                        c2215ny16.a(false);
                        c2215ny3.a(true);
                        c2215ny16.d = c2215ny3.e;
                        c2215ny3.e = c2215ny16;
                        if (i4 < 2) {
                            this.c = c2215ny3;
                        } else {
                            int i8 = i4 - 2;
                            boolean z6 = this.k[i8];
                            C2215ny[] c2215nyArr5 = this.l;
                            if (z6) {
                                c2215nyArr5[i8].e = c2215ny3;
                            } else {
                                c2215nyArr5[i8].d = c2215ny3;
                            }
                        }
                        if (!c2215ny3.f()) {
                            break;
                        }
                        c2215ny3.f &= Integer.MAX_VALUE;
                        c2215ny16.a(c2215ny3);
                        break;
                    }
                    this.l[i4].a(true);
                    c2215ny13.a(true);
                    this.l[i6].a(false);
                    i4 -= 2;
                }
            }
            i3 = i2;
        }
        this.c.a(true);
        while (true) {
            int i9 = i3 - 1;
            if (i3 == 0) {
                break;
            }
            this.l[i9] = null;
            i3 = i9;
        }
        Object obj3 = c2215ny4.c;
        c2215ny4.c = obj;
        return obj3;
    }

    @Override // com.android.tools.r8.internal.O, com.android.tools.r8.internal.InterfaceC0969Xx, com.android.tools.r8.internal.InterfaceC0425Cy
    public final NU b() {
        if (this.g == null) {
            this.g = new C2044ly(this);
        }
        return this.g;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final InterfaceC0425Cy c(int i) {
        return new C3154yy(this, 0, true, i, false);
    }

    @Override // com.android.tools.r8.internal.K, java.util.Map
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
            C0399By c0399By = (C0399By) super.clone();
            c0399By.h = null;
            c0399By.i = null;
            c0399By.g = null;
            c0399By.k = new boolean[64];
            c0399By.l = new C2215ny[64];
            if (this.d != 0) {
                C2215ny c2215ny = new C2215ny();
                C2215ny c2215ny2 = new C2215ny();
                C2215ny c2215ny3 = this.c;
                c2215ny.f &= -1073741825;
                c2215ny.d = c2215ny3;
                c2215ny2.a((C2215ny) null);
                C2215ny c2215ny4 = c2215ny2;
                loop0: while (true) {
                    if (c2215ny.d()) {
                        while (true) {
                            boolean zF = c2215ny.f();
                            c2215ny = c2215ny.e;
                            if (!zF) {
                                c2215ny4 = c2215ny4.e;
                                break;
                            }
                            if (c2215ny == null) {
                                break loop0;
                            }
                            c2215ny4 = c2215ny4.e;
                        }
                    } else {
                        C2215ny c2215nyM17clone = c2215ny.d.m17clone();
                        c2215nyM17clone.a(c2215ny4.d);
                        c2215nyM17clone.b(c2215ny4);
                        c2215ny4.f &= -1073741825;
                        c2215ny4.d = c2215nyM17clone;
                        c2215ny = c2215ny.d;
                        c2215ny4 = c2215nyM17clone;
                    }
                    if (!c2215ny.f()) {
                        C2215ny c2215nyM17clone2 = c2215ny.e.m17clone();
                        c2215nyM17clone2.b(c2215ny4.e);
                        c2215nyM17clone2.a(c2215ny4);
                        c2215ny4.f &= Integer.MAX_VALUE;
                        c2215ny4.e = c2215nyM17clone2;
                    }
                }
                c2215ny4.e = null;
                C2215ny c2215ny5 = c2215ny2.d;
                c0399By.c = c2215ny5;
                c0399By.e = c2215ny5;
                while (true) {
                    C2215ny c2215ny6 = c0399By.e.d;
                    if (c2215ny6 == null) {
                        break;
                    }
                    c0399By.e = c2215ny6;
                }
                c0399By.f = c0399By.c;
                while (true) {
                    C2215ny c2215ny7 = c0399By.f.e;
                    if (c2215ny7 == null) {
                        break;
                    }
                    c0399By.f = c2215ny7;
                }
            }
            return c0399By;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy, java.util.SortedMap
    public final AbstractC3239zx comparator() {
        return null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        C0373Ay c0373Ay = new C0373Ay(this);
        int i = this.d;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            Object obj2 = c0373Ay.a().c;
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

    public final C2215ny d(int i) {
        C2215ny c2215ny = this.c;
        while (c2215ny != null) {
            int iCompare = Integer.compare(i, c2215ny.b);
            if (iCompare == 0) {
                break;
            }
            int i2 = c2215ny.f;
            if (iCompare < 0) {
                c2215ny = (1073741824 & i2) != 0 ? null : c2215ny.d;
            } else if ((Integer.MIN_VALUE & i2) == 0) {
                c2215ny = c2215ny.e;
            }
        }
        return c2215ny;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0021  */
    public final C2215ny e(int i) {
        C2215ny c2215ny;
        C2215ny c2215ny2 = this.c;
        int iCompare = 0;
        C2215ny c2215ny3 = c2215ny2;
        while (c2215ny2 != null) {
            iCompare = Integer.compare(i, c2215ny2.b);
            if (iCompare == 0) {
                break;
            }
            int i2 = c2215ny2.f;
            if (iCompare < 0) {
                if ((i2 & 1073741824) != 0) {
                    c2215ny = null;
                } else {
                    c2215ny = c2215ny2.d;
                }
            } else if ((i2 & Integer.MIN_VALUE) != 0) {
                c2215ny = null;
            } else {
                c2215ny = c2215ny2.e;
            }
            C2215ny c2215ny4 = c2215ny;
            c2215ny3 = c2215ny2;
            c2215ny2 = c2215ny4;
        }
        return iCompare == 0 ? c2215ny2 : c2215ny3;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0917Vx
    public final Object get(int i) {
        C2215ny c2215nyD = d(i);
        return c2215nyD == null ? this.b : c2215nyD.c;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.d == 0;
    }

    @Override // com.android.tools.r8.internal.O, java.util.Map, com.android.tools.r8.internal.InterfaceC0425Cy, java.util.SortedMap
    public final CA keySet() {
        if (this.h == null) {
            this.h = new C2472qy(this);
        }
        return this.h;
    }

    /* JADX WARN: Code duplicated, block: B:109:0x0209  */
    /* JADX WARN: Code duplicated, block: B:111:0x0225  */
    /* JADX WARN: Code duplicated, block: B:115:0x0255  */
    /* JADX WARN: Code duplicated, block: B:116:0x0258  */
    /* JADX WARN: Code duplicated, block: B:118:0x025f  */
    /* JADX WARN: Code duplicated, block: B:119:0x0264  */
    /* JADX WARN: Code duplicated, block: B:122:0x026e  */
    /* JADX WARN: Code duplicated, block: B:125:0x0280  */
    /* JADX WARN: Code duplicated, block: B:127:0x028a  */
    /* JADX WARN: Code duplicated, block: B:129:0x02a0  */
    /* JADX WARN: Code duplicated, block: B:130:0x02a3  */
    /* JADX WARN: Code duplicated, block: B:132:0x02ab  */
    /* JADX WARN: Code duplicated, block: B:133:0x02b0  */
    /* JADX WARN: Code duplicated, block: B:136:0x02c0  */
    /* JADX WARN: Code duplicated, block: B:151:0x02f5  */
    /* JADX WARN: Code duplicated, block: B:153:0x0311  */
    /* JADX WARN: Code duplicated, block: B:157:0x033f  */
    /* JADX WARN: Code duplicated, block: B:158:0x0342  */
    /* JADX WARN: Code duplicated, block: B:160:0x0349  */
    /* JADX WARN: Code duplicated, block: B:161:0x034e  */
    /* JADX WARN: Code duplicated, block: B:164:0x0358  */
    /* JADX WARN: Code duplicated, block: B:81:0x018b  */
    /* JADX WARN: Code duplicated, block: B:83:0x0194  */
    /* JADX WARN: Code duplicated, block: B:85:0x019e  */
    /* JADX WARN: Code duplicated, block: B:87:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:88:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:90:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:91:0x01c4  */
    /* JADX WARN: Code duplicated, block: B:94:0x01d4  */
    @Override // com.android.tools.r8.internal.K
    public final Object remove(int i) {
        int i2;
        C2215ny c2215ny;
        boolean z;
        C2215ny[] c2215nyArr;
        C2215ny c2215ny2;
        C2215ny c2215ny3;
        int i3;
        C2215ny[] c2215nyArr2;
        int i4;
        C2215ny[] c2215nyArr3;
        int i5;
        C2215ny c2215ny4;
        C2215ny c2215ny5;
        int i6;
        C2215ny[] c2215nyArr4;
        int i7;
        C2215ny[] c2215nyArr5;
        int i8;
        this.j = false;
        C2215ny c2215ny6 = this.c;
        if (c2215ny6 == null) {
            return this.b;
        }
        int i9 = 0;
        while (true) {
            int iCompare = Integer.compare(i, c2215ny6.b);
            if (iCompare == 0) {
                if (c2215ny6.d == null) {
                    this.e = c2215ny6.c();
                }
                if (c2215ny6.e == null) {
                    this.f = c2215ny6.e();
                }
                if (!c2215ny6.f()) {
                    C2215ny c2215ny7 = c2215ny6.e;
                    if (c2215ny7.d()) {
                        c2215ny7.d = c2215ny6.d;
                        c2215ny7.b(c2215ny6.d());
                        if (!c2215ny7.d()) {
                            c2215ny7.e().e = c2215ny7;
                        }
                        if (i9 == 0) {
                            this.c = c2215ny7;
                        } else {
                            int i10 = i9 - 1;
                            boolean z2 = this.k[i10];
                            C2215ny[] c2215nyArr6 = this.l;
                            if (z2) {
                                c2215nyArr6[i10].e = c2215ny7;
                            } else {
                                c2215nyArr6[i10].d = c2215ny7;
                            }
                        }
                        boolean zB = c2215ny7.b();
                        c2215ny7.a(c2215ny6.b());
                        c2215ny6.a(zB);
                        this.k[i9] = true;
                        this.l[i9] = c2215ny7;
                        i9++;
                    } else {
                        int i11 = i9 + 1;
                        while (true) {
                            this.k[i11] = false;
                            i2 = i11 + 1;
                            this.l[i11] = c2215ny7;
                            c2215ny = c2215ny7.d;
                            if (c2215ny.d()) {
                                break;
                            }
                            c2215ny7 = c2215ny;
                            i11 = i2;
                        }
                        this.k[i9] = true;
                        this.l[i9] = c2215ny;
                        if (c2215ny.f()) {
                            c2215ny7.a(c2215ny);
                        } else {
                            c2215ny7.d = c2215ny.e;
                        }
                        c2215ny.d = c2215ny6.d;
                        if (!c2215ny6.d()) {
                            c2215ny6.e().e = c2215ny;
                            c2215ny.b(false);
                        }
                        C2215ny c2215ny8 = c2215ny6.e;
                        c2215ny.f &= Integer.MAX_VALUE;
                        c2215ny.e = c2215ny8;
                        boolean zB2 = c2215ny.b();
                        c2215ny.a(c2215ny6.b());
                        c2215ny6.a(zB2);
                        if (i9 == 0) {
                            this.c = c2215ny;
                        } else {
                            int i12 = i9 - 1;
                            boolean z3 = this.k[i12];
                            C2215ny[] c2215nyArr7 = this.l;
                            if (z3) {
                                c2215nyArr7[i12].e = c2215ny;
                            } else {
                                c2215nyArr7[i12].d = c2215ny;
                            }
                        }
                        i9 = i2;
                    }
                } else if (!c2215ny6.d()) {
                    c2215ny6.e().e = c2215ny6.e;
                    if (i9 == 0) {
                        this.c = c2215ny6.d;
                    } else {
                        int i13 = i9 - 1;
                        boolean z4 = this.k[i13];
                        C2215ny[] c2215nyArr8 = this.l;
                        if (z4) {
                            c2215nyArr8[i13].e = c2215ny6.d;
                        } else {
                            c2215nyArr8[i13].d = c2215ny6.d;
                        }
                    }
                } else if (i9 == 0) {
                    this.c = c2215ny6.d;
                } else {
                    int i14 = i9 - 1;
                    boolean z5 = this.k[i14];
                    C2215ny[] c2215nyArr9 = this.l;
                    if (z5) {
                        c2215nyArr9[i14].b(c2215ny6.e);
                    } else {
                        c2215nyArr9[i14].a(c2215ny6.d);
                    }
                }
                if (c2215ny6.b()) {
                    int i15 = i9;
                    while (true) {
                        if (i9 > 0) {
                            int i16 = i9 - 1;
                            if ((!this.k[i16] || this.l[i16].f()) && (this.k[i16] || this.l[i16].d())) {
                                z = this.k[i16];
                                c2215nyArr = this.l;
                                if (z) {
                                    c2215ny2 = c2215nyArr[i16].d;
                                    if (!c2215ny2.b()) {
                                        c2215ny2.a(true);
                                        this.l[i16].a(false);
                                        c2215nyArr3 = this.l;
                                        C2215ny c2215ny9 = c2215nyArr3[i16];
                                        c2215ny9.d = c2215ny2.e;
                                        c2215ny2.e = c2215ny9;
                                        if (i9 < 2) {
                                            this.c = c2215ny2;
                                        } else {
                                            i5 = i9 - 2;
                                            if (this.k[i5]) {
                                                c2215nyArr3[i5].e = c2215ny2;
                                            } else {
                                                c2215nyArr3[i5].d = c2215ny2;
                                            }
                                        }
                                        c2215nyArr3[i9] = c2215ny9;
                                        this.k[i9] = true;
                                        c2215nyArr3[i16] = c2215ny2;
                                        int i17 = i9 + 1;
                                        if (i15 == i9) {
                                            i15++;
                                        }
                                        c2215ny2 = c2215nyArr3[i9].d;
                                        i9 = i17;
                                    }
                                    if ((!c2215ny2.d() || c2215ny2.d.b()) && (c2215ny2.f() || c2215ny2.e.b())) {
                                        c2215ny2.a(false);
                                        i9--;
                                    } else {
                                        if (c2215ny2.d() || c2215ny2.d.b()) {
                                            c2215ny3 = c2215ny2.e;
                                            c2215ny3.a(true);
                                            c2215ny2.a(false);
                                            c2215ny2.e = c2215ny3.d;
                                            c2215ny3.d = c2215ny2;
                                            this.l[i9 - 1].d = c2215ny3;
                                            if (c2215ny3.d()) {
                                                c2215ny3.b(false);
                                                c2215ny3.d.b(c2215ny3);
                                            }
                                            c2215ny2 = c2215ny3;
                                        }
                                        i3 = i9 - 1;
                                        c2215ny2.a(this.l[i3].b());
                                        this.l[i3].a(true);
                                        c2215ny2.d.a(true);
                                        c2215nyArr2 = this.l;
                                        C2215ny c2215ny10 = c2215nyArr2[i3];
                                        c2215ny10.d = c2215ny2.e;
                                        c2215ny2.e = c2215ny10;
                                        if (i9 < 2) {
                                            this.c = c2215ny2;
                                        } else {
                                            i4 = i9 - 2;
                                            if (this.k[i4]) {
                                                c2215nyArr2[i4].e = c2215ny2;
                                            } else {
                                                c2215nyArr2[i4].d = c2215ny2;
                                            }
                                        }
                                        if (c2215ny2.f()) {
                                            c2215ny2.f &= Integer.MAX_VALUE;
                                            this.l[i3].a(c2215ny2);
                                        }
                                    }
                                } else {
                                    c2215ny4 = c2215nyArr[i16].e;
                                    if (!c2215ny4.b()) {
                                        c2215ny4.a(true);
                                        this.l[i16].a(false);
                                        c2215nyArr5 = this.l;
                                        C2215ny c2215ny11 = c2215nyArr5[i16];
                                        c2215ny11.e = c2215ny4.d;
                                        c2215ny4.d = c2215ny11;
                                        if (i9 < 2) {
                                            this.c = c2215ny4;
                                        } else {
                                            i8 = i9 - 2;
                                            if (this.k[i8]) {
                                                c2215nyArr5[i8].e = c2215ny4;
                                            } else {
                                                c2215nyArr5[i8].d = c2215ny4;
                                            }
                                        }
                                        c2215nyArr5[i9] = c2215ny11;
                                        this.k[i9] = false;
                                        c2215nyArr5[i16] = c2215ny4;
                                        int i18 = i9 + 1;
                                        if (i15 == i9) {
                                            i15++;
                                        }
                                        c2215ny4 = c2215nyArr5[i9].e;
                                        i9 = i18;
                                    }
                                    if ((!c2215ny4.d() || c2215ny4.d.b()) && (c2215ny4.f() || c2215ny4.e.b())) {
                                        c2215ny4.a(false);
                                        i9--;
                                    } else {
                                        if (c2215ny4.f() || c2215ny4.e.b()) {
                                            c2215ny5 = c2215ny4.d;
                                            c2215ny5.a(true);
                                            c2215ny4.a(false);
                                            c2215ny4.d = c2215ny5.e;
                                            c2215ny5.e = c2215ny4;
                                            this.l[i9 - 1].e = c2215ny5;
                                            if (c2215ny5.f()) {
                                                c2215ny5.f &= Integer.MAX_VALUE;
                                                c2215ny5.e.a(c2215ny5);
                                            }
                                            c2215ny4 = c2215ny5;
                                        }
                                        i6 = i9 - 1;
                                        c2215ny4.a(this.l[i6].b());
                                        this.l[i6].a(true);
                                        c2215ny4.e.a(true);
                                        c2215nyArr4 = this.l;
                                        C2215ny c2215ny12 = c2215nyArr4[i6];
                                        c2215ny12.e = c2215ny4.d;
                                        c2215ny4.d = c2215ny12;
                                        if (i9 < 2) {
                                            this.c = c2215ny4;
                                        } else {
                                            i7 = i9 - 2;
                                            if (this.k[i7]) {
                                                c2215nyArr4[i7].e = c2215ny4;
                                            } else {
                                                c2215nyArr4[i7].d = c2215ny4;
                                            }
                                        }
                                        if (c2215ny4.d()) {
                                            c2215ny4.b(false);
                                            this.l[i6].b(c2215ny4);
                                        }
                                    }
                                }
                            } else {
                                boolean z6 = this.k[i16];
                                C2215ny[] c2215nyArr10 = this.l;
                                C2215ny c2215ny13 = z6 ? c2215nyArr10[i16].e : c2215nyArr10[i16].d;
                                if (c2215ny13.b()) {
                                    z = this.k[i16];
                                    c2215nyArr = this.l;
                                    if (z) {
                                        c2215ny4 = c2215nyArr[i16].e;
                                        if (!c2215ny4.b()) {
                                            c2215ny4.a(true);
                                            this.l[i16].a(false);
                                            c2215nyArr5 = this.l;
                                            C2215ny c2215ny14 = c2215nyArr5[i16];
                                            c2215ny14.e = c2215ny4.d;
                                            c2215ny4.d = c2215ny14;
                                            if (i9 < 2) {
                                                this.c = c2215ny4;
                                            } else {
                                                i8 = i9 - 2;
                                                if (this.k[i8]) {
                                                    c2215nyArr5[i8].e = c2215ny4;
                                                } else {
                                                    c2215nyArr5[i8].d = c2215ny4;
                                                }
                                            }
                                            c2215nyArr5[i9] = c2215ny14;
                                            this.k[i9] = false;
                                            c2215nyArr5[i16] = c2215ny4;
                                            int i19 = i9 + 1;
                                            if (i15 == i9) {
                                                i15++;
                                            }
                                            c2215ny4 = c2215nyArr5[i9].e;
                                            i9 = i19;
                                        }
                                        if (c2215ny4.d()) {
                                            c2215ny4.a(false);
                                            i9--;
                                        } else {
                                            c2215ny4.a(false);
                                            i9--;
                                        }
                                        if (c2215ny4.f()) {
                                            c2215ny5 = c2215ny4.d;
                                            c2215ny5.a(true);
                                            c2215ny4.a(false);
                                            c2215ny4.d = c2215ny5.e;
                                            c2215ny5.e = c2215ny4;
                                            this.l[i9 - 1].e = c2215ny5;
                                            if (c2215ny5.f()) {
                                                c2215ny5.f &= Integer.MAX_VALUE;
                                                c2215ny5.e.a(c2215ny5);
                                            }
                                            c2215ny4 = c2215ny5;
                                        } else {
                                            c2215ny5 = c2215ny4.d;
                                            c2215ny5.a(true);
                                            c2215ny4.a(false);
                                            c2215ny4.d = c2215ny5.e;
                                            c2215ny5.e = c2215ny4;
                                            this.l[i9 - 1].e = c2215ny5;
                                            if (c2215ny5.f()) {
                                                c2215ny5.f &= Integer.MAX_VALUE;
                                                c2215ny5.e.a(c2215ny5);
                                            }
                                            c2215ny4 = c2215ny5;
                                        }
                                        i6 = i9 - 1;
                                        c2215ny4.a(this.l[i6].b());
                                        this.l[i6].a(true);
                                        c2215ny4.e.a(true);
                                        c2215nyArr4 = this.l;
                                        C2215ny c2215ny15 = c2215nyArr4[i6];
                                        c2215ny15.e = c2215ny4.d;
                                        c2215ny4.d = c2215ny15;
                                        if (i9 < 2) {
                                            this.c = c2215ny4;
                                        } else {
                                            i7 = i9 - 2;
                                            if (this.k[i7]) {
                                                c2215nyArr4[i7].e = c2215ny4;
                                            } else {
                                                c2215nyArr4[i7].d = c2215ny4;
                                            }
                                        }
                                        if (c2215ny4.d()) {
                                            c2215ny4.b(false);
                                            this.l[i6].b(c2215ny4);
                                        }
                                    } else {
                                        c2215ny2 = c2215nyArr[i16].d;
                                        if (!c2215ny2.b()) {
                                            c2215ny2.a(true);
                                            this.l[i16].a(false);
                                            c2215nyArr3 = this.l;
                                            C2215ny c2215ny16 = c2215nyArr3[i16];
                                            c2215ny16.d = c2215ny2.e;
                                            c2215ny2.e = c2215ny16;
                                            if (i9 < 2) {
                                                this.c = c2215ny2;
                                            } else {
                                                i5 = i9 - 2;
                                                if (this.k[i5]) {
                                                    c2215nyArr3[i5].e = c2215ny2;
                                                } else {
                                                    c2215nyArr3[i5].d = c2215ny2;
                                                }
                                            }
                                            c2215nyArr3[i9] = c2215ny16;
                                            this.k[i9] = true;
                                            c2215nyArr3[i16] = c2215ny2;
                                            int i110 = i9 + 1;
                                            if (i15 == i9) {
                                                i15++;
                                            }
                                            c2215ny2 = c2215nyArr3[i9].d;
                                            i9 = i110;
                                        }
                                        if (c2215ny2.d()) {
                                            c2215ny2.a(false);
                                            i9--;
                                        } else {
                                            c2215ny2.a(false);
                                            i9--;
                                        }
                                        if (c2215ny2.d()) {
                                            c2215ny3 = c2215ny2.e;
                                            c2215ny3.a(true);
                                            c2215ny2.a(false);
                                            c2215ny2.e = c2215ny3.d;
                                            c2215ny3.d = c2215ny2;
                                            this.l[i9 - 1].d = c2215ny3;
                                            if (c2215ny3.d()) {
                                                c2215ny3.b(false);
                                                c2215ny3.d.b(c2215ny3);
                                            }
                                            c2215ny2 = c2215ny3;
                                        } else {
                                            c2215ny3 = c2215ny2.e;
                                            c2215ny3.a(true);
                                            c2215ny2.a(false);
                                            c2215ny2.e = c2215ny3.d;
                                            c2215ny3.d = c2215ny2;
                                            this.l[i9 - 1].d = c2215ny3;
                                            if (c2215ny3.d()) {
                                                c2215ny3.b(false);
                                                c2215ny3.d.b(c2215ny3);
                                            }
                                            c2215ny2 = c2215ny3;
                                        }
                                        i3 = i9 - 1;
                                        c2215ny2.a(this.l[i3].b());
                                        this.l[i3].a(true);
                                        c2215ny2.d.a(true);
                                        c2215nyArr2 = this.l;
                                        C2215ny c2215ny17 = c2215nyArr2[i3];
                                        c2215ny17.d = c2215ny2.e;
                                        c2215ny2.e = c2215ny17;
                                        if (i9 < 2) {
                                            this.c = c2215ny2;
                                        } else {
                                            i4 = i9 - 2;
                                            if (this.k[i4]) {
                                                c2215nyArr2[i4].e = c2215ny2;
                                            } else {
                                                c2215nyArr2[i4].d = c2215ny2;
                                            }
                                        }
                                        if (c2215ny2.f()) {
                                            c2215ny2.f &= Integer.MAX_VALUE;
                                            this.l[i3].a(c2215ny2);
                                        }
                                    }
                                } else {
                                    c2215ny13.a(true);
                                }
                            }
                        }
                        i9 = i15;
                        C2215ny c2215ny18 = this.c;
                        if (c2215ny18 != null) {
                            c2215ny18.a(true);
                        }
                    }
                }
                this.j = true;
                this.d--;
                while (true) {
                    int i20 = i9 - 1;
                    if (i9 == 0) {
                        return c2215ny6.c;
                    }
                    this.l[i20] = null;
                    i9 = i20;
                }
            } else {
                boolean[] zArr = this.k;
                boolean z7 = iCompare > 0;
                zArr[i9] = z7;
                this.l[i9] = c2215ny6;
                i9++;
                int i21 = c2215ny6.f;
                if (z7) {
                    c2215ny6 = (i21 & Integer.MIN_VALUE) != 0 ? null : c2215ny6.e;
                    if (c2215ny6 == null) {
                        while (true) {
                            int i22 = i9 - 1;
                            if (i9 == 0) {
                                return this.b;
                            }
                            this.l[i22] = null;
                            i9 = i22;
                        }
                    }
                } else {
                    c2215ny6 = (i21 & 1073741824) != 0 ? null : c2215ny6.d;
                    if (c2215ny6 == null) {
                        while (true) {
                            int i23 = i9 - 1;
                            if (i9 == 0) {
                                return this.b;
                            }
                            this.l[i23] = null;
                            i9 = i23;
                        }
                    }
                }
            }
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.M, com.android.tools.r8.internal.InterfaceC0969Xx, java.util.Map, com.android.tools.r8.internal.InterfaceC0425Cy, java.util.SortedMap
    public final InterfaceC3028xU values() {
        if (this.i == null) {
            this.i = new C2129my(this);
        }
        return this.i;
    }

    @Override // java.util.SortedMap
    public final /* bridge */ /* synthetic */ Comparator comparator() {
        return null;
    }

    public final int c(int i, int i2) {
        return Integer.compare(i, i2);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final InterfaceC0425Cy b(int i) {
        return new C3154yy(this, i, false, 0, true);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final int d() {
        if (this.c != null) {
            return this.f.b;
        }
        z0e.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.M, com.android.tools.r8.internal.InterfaceC0917Vx
    public final boolean a(int i) {
        return d(i) != null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final int a() {
        if (this.c != null) {
            return this.e.b;
        }
        z0e.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final InterfaceC0425Cy a(int i, int i2) {
        return new C3154yy(this, i, false, i2, false);
    }
}
