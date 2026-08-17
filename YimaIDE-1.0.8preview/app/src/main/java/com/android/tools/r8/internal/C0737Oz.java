package com.android.tools.r8.internal;

import java.util.Comparator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Oz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0737Oz extends U implements Cloneable {
    public transient C0374Az e;
    public transient C0374Az f;
    public transient C3155yz g;
    public transient C0452Dz h;
    public transient C3241zz i;
    public transient boolean j;
    public transient boolean[] k = new boolean[64];
    public transient C0374Az[] l = new C0374Az[64];
    public transient C0374Az c = null;
    public int d = 0;

    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC1109az
    public final Object a(int i, Object obj) {
        int i2;
        C0374Az c0374Az;
        C0374Az c0374Az2;
        C0374Az c0374Az3;
        int i3 = 0;
        this.j = false;
        C0374Az c0374Az4 = this.c;
        if (c0374Az4 == null) {
            this.d++;
            c0374Az4 = new C0374Az(i, this.b);
            this.e = c0374Az4;
            this.f = c0374Az4;
            this.c = c0374Az4;
        } else {
            int i4 = 0;
            while (true) {
                int iCompare = Integer.compare(i, c0374Az4.b);
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
                    this.l[i4] = c0374Az4;
                    boolean[] zArr = this.k;
                    i2 = i4 + 1;
                    boolean z = iCompare > 0;
                    zArr[i4] = z;
                    if (z) {
                        if (c0374Az4.f()) {
                            this.d++;
                            c0374Az = new C0374Az(i, this.b);
                            C0374Az c0374Az5 = c0374Az4.e;
                            if (c0374Az5 == null) {
                                this.f = c0374Az;
                            }
                            c0374Az.d = c0374Az4;
                            c0374Az.e = c0374Az5;
                            c0374Az4.f &= Integer.MAX_VALUE;
                            c0374Az4.e = c0374Az;
                            break;
                        }
                        c0374Az4 = c0374Az4.e;
                        i4 = i2;
                    } else {
                        if (c0374Az4.d()) {
                            this.d++;
                            c0374Az = new C0374Az(i, this.b);
                            C0374Az c0374Az6 = c0374Az4.d;
                            if (c0374Az6 == null) {
                                this.e = c0374Az;
                            }
                            c0374Az.e = c0374Az4;
                            c0374Az.d = c0374Az6;
                            c0374Az4.f &= -1073741825;
                            c0374Az4.d = c0374Az;
                            break;
                        }
                        c0374Az4 = c0374Az4.d;
                        i4 = i2;
                    }
                }
                Object obj2 = c0374Az4.c;
                c0374Az4.c = obj;
                return obj2;
            }
            c0374Az4 = c0374Az;
            this.j = true;
            while (i4 > 0 && !this.l[i4].b()) {
                int i6 = i4 - 1;
                boolean z2 = this.k[i6];
                C0374Az[] c0374AzArr = this.l;
                if (z2) {
                    C0374Az c0374Az7 = c0374AzArr[i6];
                    C0374Az c0374Az8 = c0374Az7.d;
                    if (c0374Az7.d() || c0374Az8.b()) {
                        boolean z3 = this.k[i4];
                        C0374Az[] c0374AzArr2 = this.l;
                        if (z3) {
                            c0374Az2 = c0374AzArr2[i4];
                        } else {
                            C0374Az c0374Az9 = c0374AzArr2[i4];
                            C0374Az c0374Az10 = c0374Az9.d;
                            c0374Az9.d = c0374Az10.e;
                            c0374Az10.e = c0374Az9;
                            c0374AzArr2[i6].e = c0374Az10;
                            if (c0374Az10.f()) {
                                c0374Az10.f = Integer.MAX_VALUE & c0374Az10.f;
                                c0374Az9.a(c0374Az10);
                            }
                            c0374Az2 = c0374Az10;
                        }
                        C0374Az c0374Az11 = this.l[i6];
                        c0374Az11.a(false);
                        c0374Az2.a(true);
                        c0374Az11.e = c0374Az2.d;
                        c0374Az2.d = c0374Az11;
                        if (i4 < 2) {
                            this.c = c0374Az2;
                        } else {
                            int i7 = i4 - 2;
                            boolean z4 = this.k[i7];
                            C0374Az[] c0374AzArr3 = this.l;
                            if (z4) {
                                c0374AzArr3[i7].e = c0374Az2;
                            } else {
                                c0374AzArr3[i7].d = c0374Az2;
                            }
                        }
                        if (!c0374Az2.d()) {
                            break;
                        }
                        c0374Az2.b(false);
                        c0374Az11.b(c0374Az2);
                        break;
                    }
                    this.l[i4].a(true);
                    c0374Az8.a(true);
                    this.l[i6].a(false);
                    i4 -= 2;
                } else {
                    C0374Az c0374Az12 = c0374AzArr[i6];
                    C0374Az c0374Az13 = c0374Az12.e;
                    if (c0374Az12.f() || c0374Az13.b()) {
                        boolean z5 = this.k[i4];
                        C0374Az[] c0374AzArr4 = this.l;
                        if (z5) {
                            C0374Az c0374Az14 = c0374AzArr4[i4];
                            C0374Az c0374Az15 = c0374Az14.e;
                            c0374Az14.e = c0374Az15.d;
                            c0374Az15.d = c0374Az14;
                            c0374AzArr4[i6].d = c0374Az15;
                            if (c0374Az15.d()) {
                                c0374Az15.b(false);
                                c0374Az14.b(c0374Az15);
                            }
                            c0374Az3 = c0374Az15;
                        } else {
                            c0374Az3 = c0374AzArr4[i4];
                        }
                        C0374Az c0374Az16 = this.l[i6];
                        c0374Az16.a(false);
                        c0374Az3.a(true);
                        c0374Az16.d = c0374Az3.e;
                        c0374Az3.e = c0374Az16;
                        if (i4 < 2) {
                            this.c = c0374Az3;
                        } else {
                            int i8 = i4 - 2;
                            boolean z6 = this.k[i8];
                            C0374Az[] c0374AzArr5 = this.l;
                            if (z6) {
                                c0374AzArr5[i8].e = c0374Az3;
                            } else {
                                c0374AzArr5[i8].d = c0374Az3;
                            }
                        }
                        if (!c0374Az3.f()) {
                            break;
                        }
                        c0374Az3.f &= Integer.MAX_VALUE;
                        c0374Az16.a(c0374Az3);
                        break;
                    }
                    this.l[i4].a(true);
                    c0374Az13.a(true);
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
        Object obj3 = c0374Az4.c;
        c0374Az4.c = obj;
        return obj3;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz b(int i) {
        return new C0659Lz(this, i, false, 0, true);
    }

    @Override // com.android.tools.r8.internal.U, com.android.tools.r8.internal.InterfaceC2045lz
    public final NU c() {
        if (this.g == null) {
            this.g = new C3155yz(this);
        }
        return this.g;
    }

    @Override // com.android.tools.r8.internal.P, java.util.Map
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
            C0737Oz c0737Oz = (C0737Oz) super.clone();
            c0737Oz.h = null;
            c0737Oz.i = null;
            c0737Oz.g = null;
            c0737Oz.k = new boolean[64];
            c0737Oz.l = new C0374Az[64];
            if (this.d != 0) {
                C0374Az c0374Az = new C0374Az();
                C0374Az c0374Az2 = new C0374Az();
                C0374Az c0374Az3 = this.c;
                c0374Az.f &= -1073741825;
                c0374Az.d = c0374Az3;
                c0374Az2.a((C0374Az) null);
                C0374Az c0374Az4 = c0374Az2;
                loop0: while (true) {
                    if (c0374Az.d()) {
                        while (true) {
                            boolean zF = c0374Az.f();
                            c0374Az = c0374Az.e;
                            if (!zF) {
                                c0374Az4 = c0374Az4.e;
                                break;
                            }
                            if (c0374Az == null) {
                                break loop0;
                            }
                            c0374Az4 = c0374Az4.e;
                        }
                    } else {
                        C0374Az c0374AzM8clone = c0374Az.d.m8clone();
                        c0374AzM8clone.a(c0374Az4.d);
                        c0374AzM8clone.b(c0374Az4);
                        c0374Az4.f &= -1073741825;
                        c0374Az4.d = c0374AzM8clone;
                        c0374Az = c0374Az.d;
                        c0374Az4 = c0374AzM8clone;
                    }
                    if (!c0374Az.f()) {
                        C0374Az c0374AzM8clone2 = c0374Az.e.m8clone();
                        c0374AzM8clone2.b(c0374Az4.e);
                        c0374AzM8clone2.a(c0374Az4);
                        c0374Az4.f &= Integer.MAX_VALUE;
                        c0374Az4.e = c0374AzM8clone2;
                    }
                }
                c0374Az4.e = null;
                C0374Az c0374Az5 = c0374Az2.d;
                c0737Oz.c = c0374Az5;
                c0737Oz.e = c0374Az5;
                while (true) {
                    C0374Az c0374Az6 = c0737Oz.e.d;
                    if (c0374Az6 == null) {
                        break;
                    }
                    c0737Oz.e = c0374Az6;
                }
                c0737Oz.f = c0737Oz.c;
                while (true) {
                    C0374Az c0374Az7 = c0737Oz.f.e;
                    if (c0374Az7 == null) {
                        break;
                    }
                    c0737Oz.f = c0374Az7;
                }
            }
            return c0737Oz;
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
        C0711Nz c0711Nz = new C0711Nz(this);
        int i = this.d;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            if (c0711Nz.a().c == obj) {
                return true;
            }
            i = i2;
        }
    }

    public final C0374Az d(int i) {
        C0374Az c0374Az = this.c;
        while (c0374Az != null) {
            int iCompare = Integer.compare(i, c0374Az.b);
            if (iCompare == 0) {
                break;
            }
            int i2 = c0374Az.f;
            if (iCompare < 0) {
                c0374Az = (1073741824 & i2) != 0 ? null : c0374Az.d;
            } else if ((Integer.MIN_VALUE & i2) == 0) {
                c0374Az = c0374Az.e;
            }
        }
        return c0374Az;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0021  */
    public final C0374Az e(int i) {
        C0374Az c0374Az;
        C0374Az c0374Az2 = this.c;
        int iCompare = 0;
        C0374Az c0374Az3 = c0374Az2;
        while (c0374Az2 != null) {
            iCompare = Integer.compare(i, c0374Az2.b);
            if (iCompare == 0) {
                break;
            }
            int i2 = c0374Az2.f;
            if (iCompare < 0) {
                if ((i2 & 1073741824) != 0) {
                    c0374Az = null;
                } else {
                    c0374Az = c0374Az2.d;
                }
            } else if ((i2 & Integer.MIN_VALUE) != 0) {
                c0374Az = null;
            } else {
                c0374Az = c0374Az2.e;
            }
            C0374Az c0374Az4 = c0374Az;
            c0374Az3 = c0374Az2;
            c0374Az2 = c0374Az4;
        }
        return iCompare == 0 ? c0374Az2 : c0374Az3;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1109az
    public final Object get(int i) {
        C0374Az c0374AzD = d(i);
        return c0374AzD == null ? this.b : c0374AzD.c;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.d == 0;
    }

    @Override // com.android.tools.r8.internal.U, com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final CA keySet() {
        if (this.h == null) {
            this.h = new C0452Dz(this);
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
    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC1109az
    public final Object remove(int i) {
        int i2;
        C0374Az c0374Az;
        boolean z;
        C0374Az[] c0374AzArr;
        C0374Az c0374Az2;
        C0374Az c0374Az3;
        int i3;
        C0374Az[] c0374AzArr2;
        int i4;
        C0374Az[] c0374AzArr3;
        int i5;
        C0374Az c0374Az4;
        C0374Az c0374Az5;
        int i6;
        C0374Az[] c0374AzArr4;
        int i7;
        C0374Az[] c0374AzArr5;
        int i8;
        this.j = false;
        C0374Az c0374Az6 = this.c;
        if (c0374Az6 == null) {
            return this.b;
        }
        int i9 = 0;
        while (true) {
            int iCompare = Integer.compare(i, c0374Az6.b);
            if (iCompare == 0) {
                if (c0374Az6.d == null) {
                    this.e = c0374Az6.c();
                }
                if (c0374Az6.e == null) {
                    this.f = c0374Az6.e();
                }
                if (!c0374Az6.f()) {
                    C0374Az c0374Az7 = c0374Az6.e;
                    if (c0374Az7.d()) {
                        c0374Az7.d = c0374Az6.d;
                        c0374Az7.b(c0374Az6.d());
                        if (!c0374Az7.d()) {
                            c0374Az7.e().e = c0374Az7;
                        }
                        if (i9 == 0) {
                            this.c = c0374Az7;
                        } else {
                            int i10 = i9 - 1;
                            boolean z2 = this.k[i10];
                            C0374Az[] c0374AzArr6 = this.l;
                            if (z2) {
                                c0374AzArr6[i10].e = c0374Az7;
                            } else {
                                c0374AzArr6[i10].d = c0374Az7;
                            }
                        }
                        boolean zB = c0374Az7.b();
                        c0374Az7.a(c0374Az6.b());
                        c0374Az6.a(zB);
                        this.k[i9] = true;
                        this.l[i9] = c0374Az7;
                        i9++;
                    } else {
                        int i11 = i9 + 1;
                        while (true) {
                            this.k[i11] = false;
                            i2 = i11 + 1;
                            this.l[i11] = c0374Az7;
                            c0374Az = c0374Az7.d;
                            if (c0374Az.d()) {
                                break;
                            }
                            c0374Az7 = c0374Az;
                            i11 = i2;
                        }
                        this.k[i9] = true;
                        this.l[i9] = c0374Az;
                        if (c0374Az.f()) {
                            c0374Az7.a(c0374Az);
                        } else {
                            c0374Az7.d = c0374Az.e;
                        }
                        c0374Az.d = c0374Az6.d;
                        if (!c0374Az6.d()) {
                            c0374Az6.e().e = c0374Az;
                            c0374Az.b(false);
                        }
                        C0374Az c0374Az8 = c0374Az6.e;
                        c0374Az.f &= Integer.MAX_VALUE;
                        c0374Az.e = c0374Az8;
                        boolean zB2 = c0374Az.b();
                        c0374Az.a(c0374Az6.b());
                        c0374Az6.a(zB2);
                        if (i9 == 0) {
                            this.c = c0374Az;
                        } else {
                            int i12 = i9 - 1;
                            boolean z3 = this.k[i12];
                            C0374Az[] c0374AzArr7 = this.l;
                            if (z3) {
                                c0374AzArr7[i12].e = c0374Az;
                            } else {
                                c0374AzArr7[i12].d = c0374Az;
                            }
                        }
                        i9 = i2;
                    }
                } else if (!c0374Az6.d()) {
                    c0374Az6.e().e = c0374Az6.e;
                    if (i9 == 0) {
                        this.c = c0374Az6.d;
                    } else {
                        int i13 = i9 - 1;
                        boolean z4 = this.k[i13];
                        C0374Az[] c0374AzArr8 = this.l;
                        if (z4) {
                            c0374AzArr8[i13].e = c0374Az6.d;
                        } else {
                            c0374AzArr8[i13].d = c0374Az6.d;
                        }
                    }
                } else if (i9 == 0) {
                    this.c = c0374Az6.d;
                } else {
                    int i14 = i9 - 1;
                    boolean z5 = this.k[i14];
                    C0374Az[] c0374AzArr9 = this.l;
                    if (z5) {
                        c0374AzArr9[i14].b(c0374Az6.e);
                    } else {
                        c0374AzArr9[i14].a(c0374Az6.d);
                    }
                }
                if (c0374Az6.b()) {
                    int i15 = i9;
                    while (true) {
                        if (i9 > 0) {
                            int i16 = i9 - 1;
                            if ((!this.k[i16] || this.l[i16].f()) && (this.k[i16] || this.l[i16].d())) {
                                z = this.k[i16];
                                c0374AzArr = this.l;
                                if (z) {
                                    c0374Az2 = c0374AzArr[i16].d;
                                    if (!c0374Az2.b()) {
                                        c0374Az2.a(true);
                                        this.l[i16].a(false);
                                        c0374AzArr3 = this.l;
                                        C0374Az c0374Az9 = c0374AzArr3[i16];
                                        c0374Az9.d = c0374Az2.e;
                                        c0374Az2.e = c0374Az9;
                                        if (i9 < 2) {
                                            this.c = c0374Az2;
                                        } else {
                                            i5 = i9 - 2;
                                            if (this.k[i5]) {
                                                c0374AzArr3[i5].e = c0374Az2;
                                            } else {
                                                c0374AzArr3[i5].d = c0374Az2;
                                            }
                                        }
                                        c0374AzArr3[i9] = c0374Az9;
                                        this.k[i9] = true;
                                        c0374AzArr3[i16] = c0374Az2;
                                        int i17 = i9 + 1;
                                        if (i15 == i9) {
                                            i15++;
                                        }
                                        c0374Az2 = c0374AzArr3[i9].d;
                                        i9 = i17;
                                    }
                                    if ((!c0374Az2.d() || c0374Az2.d.b()) && (c0374Az2.f() || c0374Az2.e.b())) {
                                        c0374Az2.a(false);
                                        i9--;
                                    } else {
                                        if (c0374Az2.d() || c0374Az2.d.b()) {
                                            c0374Az3 = c0374Az2.e;
                                            c0374Az3.a(true);
                                            c0374Az2.a(false);
                                            c0374Az2.e = c0374Az3.d;
                                            c0374Az3.d = c0374Az2;
                                            this.l[i9 - 1].d = c0374Az3;
                                            if (c0374Az3.d()) {
                                                c0374Az3.b(false);
                                                c0374Az3.d.b(c0374Az3);
                                            }
                                            c0374Az2 = c0374Az3;
                                        }
                                        i3 = i9 - 1;
                                        c0374Az2.a(this.l[i3].b());
                                        this.l[i3].a(true);
                                        c0374Az2.d.a(true);
                                        c0374AzArr2 = this.l;
                                        C0374Az c0374Az10 = c0374AzArr2[i3];
                                        c0374Az10.d = c0374Az2.e;
                                        c0374Az2.e = c0374Az10;
                                        if (i9 < 2) {
                                            this.c = c0374Az2;
                                        } else {
                                            i4 = i9 - 2;
                                            if (this.k[i4]) {
                                                c0374AzArr2[i4].e = c0374Az2;
                                            } else {
                                                c0374AzArr2[i4].d = c0374Az2;
                                            }
                                        }
                                        if (c0374Az2.f()) {
                                            c0374Az2.f &= Integer.MAX_VALUE;
                                            this.l[i3].a(c0374Az2);
                                        }
                                    }
                                } else {
                                    c0374Az4 = c0374AzArr[i16].e;
                                    if (!c0374Az4.b()) {
                                        c0374Az4.a(true);
                                        this.l[i16].a(false);
                                        c0374AzArr5 = this.l;
                                        C0374Az c0374Az11 = c0374AzArr5[i16];
                                        c0374Az11.e = c0374Az4.d;
                                        c0374Az4.d = c0374Az11;
                                        if (i9 < 2) {
                                            this.c = c0374Az4;
                                        } else {
                                            i8 = i9 - 2;
                                            if (this.k[i8]) {
                                                c0374AzArr5[i8].e = c0374Az4;
                                            } else {
                                                c0374AzArr5[i8].d = c0374Az4;
                                            }
                                        }
                                        c0374AzArr5[i9] = c0374Az11;
                                        this.k[i9] = false;
                                        c0374AzArr5[i16] = c0374Az4;
                                        int i18 = i9 + 1;
                                        if (i15 == i9) {
                                            i15++;
                                        }
                                        c0374Az4 = c0374AzArr5[i9].e;
                                        i9 = i18;
                                    }
                                    if ((!c0374Az4.d() || c0374Az4.d.b()) && (c0374Az4.f() || c0374Az4.e.b())) {
                                        c0374Az4.a(false);
                                        i9--;
                                    } else {
                                        if (c0374Az4.f() || c0374Az4.e.b()) {
                                            c0374Az5 = c0374Az4.d;
                                            c0374Az5.a(true);
                                            c0374Az4.a(false);
                                            c0374Az4.d = c0374Az5.e;
                                            c0374Az5.e = c0374Az4;
                                            this.l[i9 - 1].e = c0374Az5;
                                            if (c0374Az5.f()) {
                                                c0374Az5.f &= Integer.MAX_VALUE;
                                                c0374Az5.e.a(c0374Az5);
                                            }
                                            c0374Az4 = c0374Az5;
                                        }
                                        i6 = i9 - 1;
                                        c0374Az4.a(this.l[i6].b());
                                        this.l[i6].a(true);
                                        c0374Az4.e.a(true);
                                        c0374AzArr4 = this.l;
                                        C0374Az c0374Az12 = c0374AzArr4[i6];
                                        c0374Az12.e = c0374Az4.d;
                                        c0374Az4.d = c0374Az12;
                                        if (i9 < 2) {
                                            this.c = c0374Az4;
                                        } else {
                                            i7 = i9 - 2;
                                            if (this.k[i7]) {
                                                c0374AzArr4[i7].e = c0374Az4;
                                            } else {
                                                c0374AzArr4[i7].d = c0374Az4;
                                            }
                                        }
                                        if (c0374Az4.d()) {
                                            c0374Az4.b(false);
                                            this.l[i6].b(c0374Az4);
                                        }
                                    }
                                }
                            } else {
                                boolean z6 = this.k[i16];
                                C0374Az[] c0374AzArr10 = this.l;
                                C0374Az c0374Az13 = z6 ? c0374AzArr10[i16].e : c0374AzArr10[i16].d;
                                if (c0374Az13.b()) {
                                    z = this.k[i16];
                                    c0374AzArr = this.l;
                                    if (z) {
                                        c0374Az4 = c0374AzArr[i16].e;
                                        if (!c0374Az4.b()) {
                                            c0374Az4.a(true);
                                            this.l[i16].a(false);
                                            c0374AzArr5 = this.l;
                                            C0374Az c0374Az14 = c0374AzArr5[i16];
                                            c0374Az14.e = c0374Az4.d;
                                            c0374Az4.d = c0374Az14;
                                            if (i9 < 2) {
                                                this.c = c0374Az4;
                                            } else {
                                                i8 = i9 - 2;
                                                if (this.k[i8]) {
                                                    c0374AzArr5[i8].e = c0374Az4;
                                                } else {
                                                    c0374AzArr5[i8].d = c0374Az4;
                                                }
                                            }
                                            c0374AzArr5[i9] = c0374Az14;
                                            this.k[i9] = false;
                                            c0374AzArr5[i16] = c0374Az4;
                                            int i19 = i9 + 1;
                                            if (i15 == i9) {
                                                i15++;
                                            }
                                            c0374Az4 = c0374AzArr5[i9].e;
                                            i9 = i19;
                                        }
                                        if (c0374Az4.d()) {
                                            c0374Az4.a(false);
                                            i9--;
                                        } else {
                                            c0374Az4.a(false);
                                            i9--;
                                        }
                                        if (c0374Az4.f()) {
                                            c0374Az5 = c0374Az4.d;
                                            c0374Az5.a(true);
                                            c0374Az4.a(false);
                                            c0374Az4.d = c0374Az5.e;
                                            c0374Az5.e = c0374Az4;
                                            this.l[i9 - 1].e = c0374Az5;
                                            if (c0374Az5.f()) {
                                                c0374Az5.f &= Integer.MAX_VALUE;
                                                c0374Az5.e.a(c0374Az5);
                                            }
                                            c0374Az4 = c0374Az5;
                                        } else {
                                            c0374Az5 = c0374Az4.d;
                                            c0374Az5.a(true);
                                            c0374Az4.a(false);
                                            c0374Az4.d = c0374Az5.e;
                                            c0374Az5.e = c0374Az4;
                                            this.l[i9 - 1].e = c0374Az5;
                                            if (c0374Az5.f()) {
                                                c0374Az5.f &= Integer.MAX_VALUE;
                                                c0374Az5.e.a(c0374Az5);
                                            }
                                            c0374Az4 = c0374Az5;
                                        }
                                        i6 = i9 - 1;
                                        c0374Az4.a(this.l[i6].b());
                                        this.l[i6].a(true);
                                        c0374Az4.e.a(true);
                                        c0374AzArr4 = this.l;
                                        C0374Az c0374Az15 = c0374AzArr4[i6];
                                        c0374Az15.e = c0374Az4.d;
                                        c0374Az4.d = c0374Az15;
                                        if (i9 < 2) {
                                            this.c = c0374Az4;
                                        } else {
                                            i7 = i9 - 2;
                                            if (this.k[i7]) {
                                                c0374AzArr4[i7].e = c0374Az4;
                                            } else {
                                                c0374AzArr4[i7].d = c0374Az4;
                                            }
                                        }
                                        if (c0374Az4.d()) {
                                            c0374Az4.b(false);
                                            this.l[i6].b(c0374Az4);
                                        }
                                    } else {
                                        c0374Az2 = c0374AzArr[i16].d;
                                        if (!c0374Az2.b()) {
                                            c0374Az2.a(true);
                                            this.l[i16].a(false);
                                            c0374AzArr3 = this.l;
                                            C0374Az c0374Az16 = c0374AzArr3[i16];
                                            c0374Az16.d = c0374Az2.e;
                                            c0374Az2.e = c0374Az16;
                                            if (i9 < 2) {
                                                this.c = c0374Az2;
                                            } else {
                                                i5 = i9 - 2;
                                                if (this.k[i5]) {
                                                    c0374AzArr3[i5].e = c0374Az2;
                                                } else {
                                                    c0374AzArr3[i5].d = c0374Az2;
                                                }
                                            }
                                            c0374AzArr3[i9] = c0374Az16;
                                            this.k[i9] = true;
                                            c0374AzArr3[i16] = c0374Az2;
                                            int i110 = i9 + 1;
                                            if (i15 == i9) {
                                                i15++;
                                            }
                                            c0374Az2 = c0374AzArr3[i9].d;
                                            i9 = i110;
                                        }
                                        if (c0374Az2.d()) {
                                            c0374Az2.a(false);
                                            i9--;
                                        } else {
                                            c0374Az2.a(false);
                                            i9--;
                                        }
                                        if (c0374Az2.d()) {
                                            c0374Az3 = c0374Az2.e;
                                            c0374Az3.a(true);
                                            c0374Az2.a(false);
                                            c0374Az2.e = c0374Az3.d;
                                            c0374Az3.d = c0374Az2;
                                            this.l[i9 - 1].d = c0374Az3;
                                            if (c0374Az3.d()) {
                                                c0374Az3.b(false);
                                                c0374Az3.d.b(c0374Az3);
                                            }
                                            c0374Az2 = c0374Az3;
                                        } else {
                                            c0374Az3 = c0374Az2.e;
                                            c0374Az3.a(true);
                                            c0374Az2.a(false);
                                            c0374Az2.e = c0374Az3.d;
                                            c0374Az3.d = c0374Az2;
                                            this.l[i9 - 1].d = c0374Az3;
                                            if (c0374Az3.d()) {
                                                c0374Az3.b(false);
                                                c0374Az3.d.b(c0374Az3);
                                            }
                                            c0374Az2 = c0374Az3;
                                        }
                                        i3 = i9 - 1;
                                        c0374Az2.a(this.l[i3].b());
                                        this.l[i3].a(true);
                                        c0374Az2.d.a(true);
                                        c0374AzArr2 = this.l;
                                        C0374Az c0374Az17 = c0374AzArr2[i3];
                                        c0374Az17.d = c0374Az2.e;
                                        c0374Az2.e = c0374Az17;
                                        if (i9 < 2) {
                                            this.c = c0374Az2;
                                        } else {
                                            i4 = i9 - 2;
                                            if (this.k[i4]) {
                                                c0374AzArr2[i4].e = c0374Az2;
                                            } else {
                                                c0374AzArr2[i4].d = c0374Az2;
                                            }
                                        }
                                        if (c0374Az2.f()) {
                                            c0374Az2.f &= Integer.MAX_VALUE;
                                            this.l[i3].a(c0374Az2);
                                        }
                                    }
                                } else {
                                    c0374Az13.a(true);
                                }
                            }
                        }
                        i9 = i15;
                        C0374Az c0374Az18 = this.c;
                        if (c0374Az18 != null) {
                            c0374Az18.a(true);
                        }
                    }
                }
                this.j = true;
                this.d--;
                while (true) {
                    int i20 = i9 - 1;
                    if (i9 == 0) {
                        return c0374Az6.c;
                    }
                    this.l[i20] = null;
                    i9 = i20;
                }
            } else {
                boolean[] zArr = this.k;
                boolean z7 = iCompare > 0;
                zArr[i9] = z7;
                this.l[i9] = c0374Az6;
                i9++;
                int i21 = c0374Az6.f;
                if (z7) {
                    c0374Az6 = (i21 & Integer.MIN_VALUE) != 0 ? null : c0374Az6.e;
                    if (c0374Az6 == null) {
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
                    c0374Az6 = (i21 & 1073741824) != 0 ? null : c0374Az6.d;
                    if (c0374Az6 == null) {
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

    @Override // com.android.tools.r8.internal.S, com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final P30 values() {
        if (this.i == null) {
            this.i = new C3241zz(this);
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

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz c(int i) {
        return new C0659Lz(this, 0, true, i, false);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final int d() {
        if (this.c != null) {
            return this.f.b;
        }
        z0e.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.S, com.android.tools.r8.internal.InterfaceC1109az
    public final boolean a(int i) {
        return d(i) != null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final int a() {
        if (this.c != null) {
            return this.e.b;
        }
        z0e.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz a(int i, int i2) {
        return new C0659Lz(this, i, false, i2, false);
    }
}
