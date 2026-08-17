package com.android.tools.r8.internal;

import java.util.Comparator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Wy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0944Wy extends U implements Cloneable {
    public transient C0581Iy e;
    public transient C0581Iy f;
    public transient C0529Gy g;
    public transient C0658Ly h;
    public transient C0555Hy i;
    public transient boolean j;
    public transient boolean[] k = new boolean[48];
    public transient C0581Iy c = null;
    public int d = 0;

    /* JADX WARN: Code duplicated, block: B:86:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:87:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:89:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:90:0x01aa  */
    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC1109az
    public final Object a(int i, Object obj) {
        C0581Iy c0581Iy;
        C0581Iy c0581Iy2;
        C0581Iy c0581Iy3;
        C0581Iy c0581Iy4;
        C0581Iy c0581Iy5;
        this.j = false;
        C0581Iy c0581Iy6 = this.c;
        if (c0581Iy6 == null) {
            this.d++;
            c0581Iy = new C0581Iy(i, this.b);
            this.e = c0581Iy;
            this.f = c0581Iy;
            this.c = c0581Iy;
            this.j = true;
        } else {
            int i2 = 0;
            C0581Iy c0581Iy7 = null;
            C0581Iy c0581Iy8 = null;
            C0581Iy c0581Iy9 = c0581Iy6;
            while (true) {
                int iCompare = Integer.compare(i, c0581Iy6.b);
                if (iCompare == 0) {
                    c0581Iy = c0581Iy6;
                } else {
                    if (((byte) c0581Iy6.f) != 0) {
                        i2 = 0;
                        c0581Iy9 = c0581Iy6;
                        c0581Iy7 = c0581Iy8;
                    }
                    boolean[] zArr = this.k;
                    int i3 = i2 + 1;
                    boolean z = iCompare > 0;
                    zArr[i2] = z;
                    if (z) {
                        if (c0581Iy6.e()) {
                            this.d++;
                            c0581Iy2 = new C0581Iy(i, this.b);
                            this.j = true;
                            C0581Iy c0581Iy10 = c0581Iy6.e;
                            if (c0581Iy10 == null) {
                                this.f = c0581Iy2;
                            }
                            c0581Iy2.d = c0581Iy6;
                            c0581Iy2.e = c0581Iy10;
                            c0581Iy6.f &= Integer.MAX_VALUE;
                            c0581Iy6.e = c0581Iy2;
                            break;
                        }
                        c0581Iy3 = c0581Iy6.e;
                        C0581Iy c0581Iy11 = c0581Iy3;
                        c0581Iy8 = c0581Iy6;
                        c0581Iy6 = c0581Iy11;
                        i2 = i3;
                    } else {
                        if (c0581Iy6.c()) {
                            this.d++;
                            c0581Iy2 = new C0581Iy(i, this.b);
                            this.j = true;
                            C0581Iy c0581Iy12 = c0581Iy6.d;
                            if (c0581Iy12 == null) {
                                this.e = c0581Iy2;
                            }
                            c0581Iy2.e = c0581Iy6;
                            c0581Iy2.d = c0581Iy12;
                            c0581Iy6.f &= -1073741825;
                            c0581Iy6.d = c0581Iy2;
                            break;
                        }
                        c0581Iy3 = c0581Iy6.d;
                        C0581Iy c0581Iy13 = c0581Iy3;
                        c0581Iy8 = c0581Iy6;
                        c0581Iy6 = c0581Iy13;
                        i2 = i3;
                    }
                }
            }
            int i4 = 0;
            C0581Iy c0581Iy14 = c0581Iy9;
            while (c0581Iy14 != c0581Iy2) {
                boolean z2 = this.k[i4];
                if (z2) {
                    int i5 = c0581Iy14.f;
                    c0581Iy14.f = ((((byte) i5) + 1) & 255) | (i5 & (-256));
                } else {
                    int i6 = c0581Iy14.f;
                    c0581Iy14.f = ((((byte) i6) - 1) & 255) | (i6 & (-256));
                }
                i4++;
                c0581Iy14 = z2 ? c0581Iy14.e : c0581Iy14.d;
            }
            byte b = (byte) c0581Iy9.f;
            if (b == -2) {
                c0581Iy4 = c0581Iy9.d;
                if (((byte) c0581Iy4.f) == -1) {
                    if (c0581Iy4.e()) {
                        c0581Iy4.b(false);
                        c0581Iy9.a(c0581Iy4);
                    } else {
                        c0581Iy9.d = c0581Iy4.e;
                    }
                    c0581Iy4.e = c0581Iy9;
                    c0581Iy4.a(0);
                    c0581Iy9.a(0);
                } else {
                    c0581Iy5 = c0581Iy4.e;
                    c0581Iy4.e = c0581Iy5.d;
                    c0581Iy5.d = c0581Iy4;
                    c0581Iy9.d = c0581Iy5.e;
                    c0581Iy5.e = c0581Iy9;
                    byte b2 = (byte) c0581Iy5.f;
                    if (b2 == -1) {
                        c0581Iy4.a(0);
                        c0581Iy9.a(1);
                    } else if (b2 == 0) {
                        c0581Iy4.a(0);
                        c0581Iy9.a(0);
                    } else {
                        c0581Iy4.a(-1);
                        c0581Iy9.a(0);
                    }
                    c0581Iy5.a(0);
                    if (c0581Iy5.c()) {
                        c0581Iy4.b(c0581Iy5);
                        c0581Iy5.a(false);
                    }
                    if (c0581Iy5.e()) {
                        c0581Iy9.a(c0581Iy5);
                        c0581Iy5.b(false);
                    }
                    c0581Iy4 = c0581Iy5;
                }
                if (c0581Iy7 == null) {
                    this.c = c0581Iy4;
                } else if (c0581Iy7.d == c0581Iy9) {
                    c0581Iy7.d = c0581Iy4;
                } else {
                    c0581Iy7.e = c0581Iy4;
                }
            } else if (b == 2) {
                c0581Iy4 = c0581Iy9.e;
                if (((byte) c0581Iy4.f) == 1) {
                    if (c0581Iy4.c()) {
                        c0581Iy4.a(false);
                        c0581Iy9.b(c0581Iy4);
                    } else {
                        c0581Iy9.e = c0581Iy4.d;
                    }
                    c0581Iy4.d = c0581Iy9;
                    c0581Iy4.a(0);
                    c0581Iy9.a(0);
                } else {
                    c0581Iy5 = c0581Iy4.d;
                    c0581Iy4.d = c0581Iy5.e;
                    c0581Iy5.e = c0581Iy4;
                    c0581Iy9.e = c0581Iy5.d;
                    c0581Iy5.d = c0581Iy9;
                    byte b3 = (byte) c0581Iy5.f;
                    if (b3 == 1) {
                        c0581Iy4.a(0);
                        c0581Iy9.a(-1);
                    } else if (b3 == 0) {
                        c0581Iy4.a(0);
                        c0581Iy9.a(0);
                    } else {
                        c0581Iy4.a(1);
                        c0581Iy9.a(0);
                    }
                    c0581Iy5.a(0);
                    if (c0581Iy5.c()) {
                        c0581Iy9.b(c0581Iy5);
                        c0581Iy5.a(false);
                    }
                    if (c0581Iy5.e()) {
                        c0581Iy4.a(c0581Iy5);
                        c0581Iy5.b(false);
                    }
                    c0581Iy4 = c0581Iy5;
                }
                if (c0581Iy7 == null) {
                    this.c = c0581Iy4;
                } else if (c0581Iy7.d == c0581Iy9) {
                    c0581Iy7.d = c0581Iy4;
                } else {
                    c0581Iy7.e = c0581Iy4;
                }
            }
            c0581Iy = c0581Iy2;
        }
        Object obj2 = c0581Iy.c;
        c0581Iy.c = obj;
        return obj2;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz b(int i) {
        return new C0866Ty(this, i, false, 0, true);
    }

    @Override // com.android.tools.r8.internal.U, com.android.tools.r8.internal.InterfaceC2045lz
    public final NU c() {
        if (this.g == null) {
            this.g = new C0529Gy(this);
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
            C0944Wy c0944Wy = (C0944Wy) super.clone();
            c0944Wy.h = null;
            c0944Wy.i = null;
            c0944Wy.g = null;
            c0944Wy.k = new boolean[48];
            if (this.d != 0) {
                C0581Iy c0581Iy = new C0581Iy();
                C0581Iy c0581Iy2 = new C0581Iy();
                C0581Iy c0581Iy3 = this.c;
                c0581Iy.f &= -1073741825;
                c0581Iy.d = c0581Iy3;
                c0581Iy2.a((C0581Iy) null);
                C0581Iy c0581Iy4 = c0581Iy2;
                loop0: while (true) {
                    if (c0581Iy.c()) {
                        while (true) {
                            boolean zE = c0581Iy.e();
                            c0581Iy = c0581Iy.e;
                            if (!zE) {
                                c0581Iy4 = c0581Iy4.e;
                                break;
                            }
                            if (c0581Iy == null) {
                                break loop0;
                            }
                            c0581Iy4 = c0581Iy4.e;
                        }
                    } else {
                        C0581Iy c0581IyM12clone = c0581Iy.d.m12clone();
                        c0581IyM12clone.a(c0581Iy4.d);
                        c0581IyM12clone.b(c0581Iy4);
                        c0581Iy4.f &= -1073741825;
                        c0581Iy4.d = c0581IyM12clone;
                        c0581Iy = c0581Iy.d;
                        c0581Iy4 = c0581IyM12clone;
                    }
                    if (!c0581Iy.e()) {
                        C0581Iy c0581IyM12clone2 = c0581Iy.e.m12clone();
                        c0581IyM12clone2.b(c0581Iy4.e);
                        c0581IyM12clone2.a(c0581Iy4);
                        c0581Iy4.f &= Integer.MAX_VALUE;
                        c0581Iy4.e = c0581IyM12clone2;
                    }
                }
                c0581Iy4.e = null;
                C0581Iy c0581Iy5 = c0581Iy2.d;
                c0944Wy.c = c0581Iy5;
                c0944Wy.e = c0581Iy5;
                while (true) {
                    C0581Iy c0581Iy6 = c0944Wy.e.d;
                    if (c0581Iy6 == null) {
                        break;
                    }
                    c0944Wy.e = c0581Iy6;
                }
                c0944Wy.f = c0944Wy.c;
                while (true) {
                    C0581Iy c0581Iy7 = c0944Wy.f.e;
                    if (c0581Iy7 == null) {
                        break;
                    }
                    c0944Wy.f = c0581Iy7;
                }
            }
            return c0944Wy;
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
        C0918Vy c0918Vy = new C0918Vy(this);
        int i = this.d;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            if (c0918Vy.a().c == obj) {
                return true;
            }
            i = i2;
        }
    }

    public final C0581Iy d(int i) {
        C0581Iy c0581Iy = this.c;
        while (c0581Iy != null) {
            int iCompare = Integer.compare(i, c0581Iy.b);
            if (iCompare == 0) {
                break;
            }
            int i2 = c0581Iy.f;
            if (iCompare < 0) {
                c0581Iy = (1073741824 & i2) != 0 ? null : c0581Iy.d;
            } else if ((Integer.MIN_VALUE & i2) == 0) {
                c0581Iy = c0581Iy.e;
            }
        }
        return c0581Iy;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0021  */
    public final C0581Iy e(int i) {
        C0581Iy c0581Iy;
        C0581Iy c0581Iy2 = this.c;
        int iCompare = 0;
        C0581Iy c0581Iy3 = c0581Iy2;
        while (c0581Iy2 != null) {
            iCompare = Integer.compare(i, c0581Iy2.b);
            if (iCompare == 0) {
                break;
            }
            int i2 = c0581Iy2.f;
            if (iCompare < 0) {
                if ((i2 & 1073741824) != 0) {
                    c0581Iy = null;
                } else {
                    c0581Iy = c0581Iy2.d;
                }
            } else if ((i2 & Integer.MIN_VALUE) != 0) {
                c0581Iy = null;
            } else {
                c0581Iy = c0581Iy2.e;
            }
            C0581Iy c0581Iy4 = c0581Iy;
            c0581Iy3 = c0581Iy2;
            c0581Iy2 = c0581Iy4;
        }
        return iCompare == 0 ? c0581Iy2 : c0581Iy3;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1109az
    public final Object get(int i) {
        C0581Iy c0581IyD = d(i);
        return c0581IyD == null ? this.b : c0581IyD.c;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.d == 0;
    }

    @Override // com.android.tools.r8.internal.U, com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final CA keySet() {
        if (this.h == null) {
            this.h = new C0658Ly(this);
        }
        return this.h;
    }

    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC1109az
    public final Object remove(int i) {
        C0581Iy c0581Iy;
        C0581Iy c0581Iy2;
        boolean zC;
        boolean zE;
        C0581Iy c0581Iy3;
        this.j = false;
        C0581Iy c0581Iy4 = this.c;
        if (c0581Iy4 == null) {
            return this.b;
        }
        boolean z = false;
        C0581Iy c0581Iy5 = null;
        while (true) {
            int iCompare = Integer.compare(i, c0581Iy4.b);
            if (iCompare == 0) {
                if (c0581Iy4.d == null) {
                    this.e = c0581Iy4.b();
                }
                if (c0581Iy4.e == null) {
                    this.f = c0581Iy4.d();
                }
                if (!c0581Iy4.e()) {
                    C0581Iy c0581Iy6 = c0581Iy4.e;
                    if (c0581Iy6.c()) {
                        c0581Iy6.d = c0581Iy4.d;
                        c0581Iy6.a(c0581Iy4.c());
                        if (!c0581Iy6.c()) {
                            c0581Iy6.d().e = c0581Iy6;
                        }
                        if (c0581Iy5 == null) {
                            this.c = c0581Iy6;
                        } else if (z) {
                            c0581Iy5.e = c0581Iy6;
                        } else {
                            c0581Iy5.d = c0581Iy6;
                        }
                        c0581Iy6.a((byte) c0581Iy4.f);
                        c0581Iy5 = c0581Iy6;
                        z = true;
                    } else {
                        while (true) {
                            c0581Iy = c0581Iy6.d;
                            if (c0581Iy.c()) {
                                break;
                            }
                            c0581Iy6 = c0581Iy;
                        }
                        if (c0581Iy.e()) {
                            c0581Iy6.a(c0581Iy);
                        } else {
                            c0581Iy6.d = c0581Iy.e;
                        }
                        c0581Iy.d = c0581Iy4.d;
                        if (!c0581Iy4.c()) {
                            c0581Iy4.d().e = c0581Iy;
                            c0581Iy.a(false);
                        }
                        c0581Iy.e = c0581Iy4.e;
                        c0581Iy.b(false);
                        if (c0581Iy5 == null) {
                            this.c = c0581Iy;
                        } else if (z) {
                            c0581Iy5.e = c0581Iy;
                        } else {
                            c0581Iy5.d = c0581Iy;
                        }
                        c0581Iy.a((byte) c0581Iy4.f);
                        c0581Iy5 = c0581Iy6;
                        z = false;
                    }
                } else if (!c0581Iy4.c()) {
                    c0581Iy4.d().e = c0581Iy4.e;
                    if (c0581Iy5 != null) {
                        C0581Iy c0581Iy7 = c0581Iy4.d;
                        if (z) {
                            c0581Iy5.e = c0581Iy7;
                        } else {
                            c0581Iy5.d = c0581Iy7;
                        }
                    } else {
                        this.c = c0581Iy4.d;
                    }
                } else if (c0581Iy5 == null) {
                    this.c = z ? c0581Iy4.e : c0581Iy4.d;
                } else if (z) {
                    c0581Iy5.b(c0581Iy4.e);
                } else {
                    c0581Iy5.a(c0581Iy4.d);
                }
                while (c0581Iy5 != null) {
                    if (c0581Iy5 == this.c) {
                        c0581Iy2 = null;
                    } else {
                        C0581Iy c0581Iy8 = c0581Iy5;
                        c0581Iy2 = c0581Iy8;
                        while (true) {
                            if (c0581Iy8.e()) {
                                c0581Iy8 = c0581Iy8.e;
                                if (c0581Iy8 != null && c0581Iy8.d == c0581Iy5) {
                                    c0581Iy2 = c0581Iy8;
                                    break;
                                }
                                do {
                                    zC = c0581Iy2.c();
                                    c0581Iy2 = c0581Iy2.d;
                                } while (!zC);
                            } else {
                                boolean zC2 = c0581Iy2.c();
                                c0581Iy2 = c0581Iy2.d;
                                if (!zC2) {
                                    c0581Iy8 = c0581Iy8.e;
                                } else if (c0581Iy2 == null || c0581Iy2.e != c0581Iy5) {
                                    do {
                                        zE = c0581Iy8.e();
                                        c0581Iy8 = c0581Iy8.e;
                                    } while (!zE);
                                    c0581Iy2 = c0581Iy8;
                                    break;
                                }
                            }
                        }
                    }
                    if (!z) {
                        z = (c0581Iy2 == null || c0581Iy2.d == c0581Iy5) ? false : true;
                        int i2 = c0581Iy5.f;
                        int i3 = ((((byte) i2) + 1) & 255) | (i2 & (-256));
                        c0581Iy5.f = i3;
                        byte b = (byte) i3;
                        if (b == 1) {
                            break;
                        }
                        if (b == 2) {
                            C0581Iy c0581Iy9 = c0581Iy5.e;
                            byte b2 = (byte) c0581Iy9.f;
                            if (b2 == -1) {
                                C0581Iy c0581Iy10 = c0581Iy9.d;
                                c0581Iy9.d = c0581Iy10.e;
                                c0581Iy10.e = c0581Iy9;
                                c0581Iy5.e = c0581Iy10.d;
                                c0581Iy10.d = c0581Iy5;
                                byte b3 = (byte) c0581Iy10.f;
                                if (b3 == 1) {
                                    c0581Iy9.a(0);
                                    c0581Iy5.a(-1);
                                } else if (b3 == 0) {
                                    c0581Iy9.a(0);
                                    c0581Iy5.a(0);
                                } else {
                                    c0581Iy9.a(1);
                                    c0581Iy5.a(0);
                                }
                                c0581Iy10.a(0);
                                if (c0581Iy10.c()) {
                                    c0581Iy5.b(c0581Iy10);
                                    c0581Iy10.a(false);
                                }
                                if (c0581Iy10.e()) {
                                    c0581Iy9.a(c0581Iy10);
                                    c0581Iy10.b(false);
                                }
                                if (c0581Iy2 == null) {
                                    this.c = c0581Iy10;
                                } else if (z) {
                                    c0581Iy2.e = c0581Iy10;
                                } else {
                                    c0581Iy2.d = c0581Iy10;
                                }
                            } else {
                                if (c0581Iy2 == null) {
                                    this.c = c0581Iy9;
                                } else if (z) {
                                    c0581Iy2.e = c0581Iy9;
                                } else {
                                    c0581Iy2.d = c0581Iy9;
                                }
                                if (b2 == 0) {
                                    c0581Iy5.e = c0581Iy9.d;
                                    c0581Iy9.d = c0581Iy5;
                                    c0581Iy9.a(-1);
                                    c0581Iy5.a(1);
                                    break;
                                }
                                if (c0581Iy9.c()) {
                                    c0581Iy5.b(true);
                                    c0581Iy9.a(false);
                                } else {
                                    c0581Iy5.e = c0581Iy9.d;
                                }
                                c0581Iy9.d = c0581Iy5;
                                c0581Iy5.a(0);
                                c0581Iy9.a(0);
                            }
                        } else {
                            continue;
                        }
                        c0581Iy5 = c0581Iy2;
                    } else {
                        z = (c0581Iy2 == null || c0581Iy2.d == c0581Iy5) ? false : true;
                        int i4 = c0581Iy5.f;
                        int i5 = ((((byte) i4) - 1) & 255) | (i4 & (-256));
                        c0581Iy5.f = i5;
                        byte b4 = (byte) i5;
                        if (b4 == -1) {
                            break;
                        }
                        if (b4 == -2) {
                            C0581Iy c0581Iy11 = c0581Iy5.d;
                            byte b5 = (byte) c0581Iy11.f;
                            if (b5 == 1) {
                                C0581Iy c0581Iy12 = c0581Iy11.e;
                                c0581Iy11.e = c0581Iy12.d;
                                c0581Iy12.d = c0581Iy11;
                                c0581Iy5.d = c0581Iy12.e;
                                c0581Iy12.e = c0581Iy5;
                                byte b6 = (byte) c0581Iy12.f;
                                if (b6 == -1) {
                                    c0581Iy11.a(0);
                                    c0581Iy5.a(1);
                                } else if (b6 == 0) {
                                    c0581Iy11.a(0);
                                    c0581Iy5.a(0);
                                } else {
                                    c0581Iy11.a(-1);
                                    c0581Iy5.a(0);
                                }
                                c0581Iy12.a(0);
                                if (c0581Iy12.c()) {
                                    c0581Iy11.b(c0581Iy12);
                                    c0581Iy12.a(false);
                                }
                                if (c0581Iy12.e()) {
                                    c0581Iy5.a(c0581Iy12);
                                    c0581Iy12.b(false);
                                }
                                if (c0581Iy2 == null) {
                                    this.c = c0581Iy12;
                                } else if (z) {
                                    c0581Iy2.e = c0581Iy12;
                                } else {
                                    c0581Iy2.d = c0581Iy12;
                                }
                            } else {
                                if (c0581Iy2 == null) {
                                    this.c = c0581Iy11;
                                } else if (z) {
                                    c0581Iy2.e = c0581Iy11;
                                } else {
                                    c0581Iy2.d = c0581Iy11;
                                }
                                if (b5 == 0) {
                                    c0581Iy5.d = c0581Iy11.e;
                                    c0581Iy11.e = c0581Iy5;
                                    c0581Iy11.a(1);
                                    c0581Iy5.a(-1);
                                    break;
                                }
                                if (c0581Iy11.e()) {
                                    c0581Iy5.a(true);
                                    c0581Iy11.b(false);
                                } else {
                                    c0581Iy5.d = c0581Iy11.e;
                                }
                                c0581Iy11.e = c0581Iy5;
                                c0581Iy5.a(0);
                                c0581Iy11.a(0);
                            }
                        } else {
                            continue;
                        }
                        c0581Iy5 = c0581Iy2;
                    }
                }
                this.j = true;
                this.d--;
                return c0581Iy4.c;
            }
            z = iCompare > 0;
            int i6 = c0581Iy4.f;
            if (z) {
                c0581Iy3 = (i6 & Integer.MIN_VALUE) != 0 ? null : c0581Iy4.e;
                if (c0581Iy3 == null) {
                    return this.b;
                }
            } else {
                c0581Iy3 = (i6 & 1073741824) != 0 ? null : c0581Iy4.d;
                if (c0581Iy3 == null) {
                    return this.b;
                }
            }
            C0581Iy c0581Iy13 = c0581Iy3;
            c0581Iy5 = c0581Iy4;
            c0581Iy4 = c0581Iy13;
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.S, com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final P30 values() {
        if (this.i == null) {
            this.i = new C0555Hy(this);
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
        return new C0866Ty(this, 0, true, i, false);
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
        return new C0866Ty(this, i, false, i2, false);
    }
}
