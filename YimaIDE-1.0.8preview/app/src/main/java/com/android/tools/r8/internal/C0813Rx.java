package com.android.tools.r8.internal;

import java.util.Comparator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Rx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0813Rx extends O implements Cloneable {
    public transient C0450Dx c;
    public int d;
    public transient C0450Dx e;
    public transient C0450Dx f;
    public transient C0398Bx g;
    public transient C0528Gx h;
    public transient C0424Cx i;
    public transient boolean j;
    public final Comparator k;
    public transient boolean[] l;

    public C0813Rx(int[] iArr, Object[] objArr) {
        this(null);
        if (iArr.length == objArr.length) {
            for (int i = 0; i < iArr.length; i++) {
                a(iArr[i], objArr[i]);
            }
            return;
        }
        StringBuilder sb = new StringBuilder("The key array and the value array have different lengths (");
        sb.append(iArr.length);
        sb.append(" and ");
        w01.a(AbstractC2181nb0.a(objArr.length, ")", sb));
        throw null;
    }

    /* JADX WARN: Code duplicated, block: B:86:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:87:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:89:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:90:0x01aa  */
    @Override // com.android.tools.r8.internal.K, com.android.tools.r8.internal.InterfaceC0917Vx
    public final Object a(int i, Object obj) {
        C0450Dx c0450Dx;
        C0450Dx c0450Dx2;
        C0450Dx c0450Dx3;
        C0450Dx c0450Dx4;
        C0450Dx c0450Dx5;
        this.j = false;
        C0450Dx c0450Dx6 = this.c;
        if (c0450Dx6 == null) {
            this.d++;
            c0450Dx = new C0450Dx(i, this.b);
            this.e = c0450Dx;
            this.f = c0450Dx;
            this.c = c0450Dx;
            this.j = true;
        } else {
            int i2 = 0;
            C0450Dx c0450Dx7 = null;
            C0450Dx c0450Dx8 = null;
            C0450Dx c0450Dx9 = c0450Dx6;
            while (true) {
                int iC = c(i, c0450Dx6.b);
                if (iC == 0) {
                    c0450Dx = c0450Dx6;
                } else {
                    if (((byte) c0450Dx6.f) != 0) {
                        i2 = 0;
                        c0450Dx9 = c0450Dx6;
                        c0450Dx7 = c0450Dx8;
                    }
                    boolean[] zArr = this.l;
                    int i3 = i2 + 1;
                    boolean z = iC > 0;
                    zArr[i2] = z;
                    if (z) {
                        if (c0450Dx6.e()) {
                            this.d++;
                            c0450Dx2 = new C0450Dx(i, this.b);
                            this.j = true;
                            C0450Dx c0450Dx10 = c0450Dx6.e;
                            if (c0450Dx10 == null) {
                                this.f = c0450Dx2;
                            }
                            c0450Dx2.d = c0450Dx6;
                            c0450Dx2.e = c0450Dx10;
                            c0450Dx6.f &= Integer.MAX_VALUE;
                            c0450Dx6.e = c0450Dx2;
                            break;
                        }
                        c0450Dx3 = c0450Dx6.e;
                        C0450Dx c0450Dx11 = c0450Dx3;
                        c0450Dx8 = c0450Dx6;
                        c0450Dx6 = c0450Dx11;
                        i2 = i3;
                    } else {
                        if (c0450Dx6.c()) {
                            this.d++;
                            c0450Dx2 = new C0450Dx(i, this.b);
                            this.j = true;
                            C0450Dx c0450Dx12 = c0450Dx6.d;
                            if (c0450Dx12 == null) {
                                this.e = c0450Dx2;
                            }
                            c0450Dx2.e = c0450Dx6;
                            c0450Dx2.d = c0450Dx12;
                            c0450Dx6.f &= -1073741825;
                            c0450Dx6.d = c0450Dx2;
                            break;
                        }
                        c0450Dx3 = c0450Dx6.d;
                        C0450Dx c0450Dx13 = c0450Dx3;
                        c0450Dx8 = c0450Dx6;
                        c0450Dx6 = c0450Dx13;
                        i2 = i3;
                    }
                }
            }
            int i4 = 0;
            C0450Dx c0450Dx14 = c0450Dx9;
            while (c0450Dx14 != c0450Dx2) {
                boolean z2 = this.l[i4];
                if (z2) {
                    int i5 = c0450Dx14.f;
                    c0450Dx14.f = ((((byte) i5) + 1) & 255) | (i5 & (-256));
                } else {
                    int i6 = c0450Dx14.f;
                    c0450Dx14.f = ((((byte) i6) - 1) & 255) | (i6 & (-256));
                }
                i4++;
                c0450Dx14 = z2 ? c0450Dx14.e : c0450Dx14.d;
            }
            byte b = (byte) c0450Dx9.f;
            if (b == -2) {
                c0450Dx4 = c0450Dx9.d;
                if (((byte) c0450Dx4.f) == -1) {
                    if (c0450Dx4.e()) {
                        c0450Dx4.b(false);
                        c0450Dx9.a(c0450Dx4);
                    } else {
                        c0450Dx9.d = c0450Dx4.e;
                    }
                    c0450Dx4.e = c0450Dx9;
                    c0450Dx4.a(0);
                    c0450Dx9.a(0);
                } else {
                    c0450Dx5 = c0450Dx4.e;
                    c0450Dx4.e = c0450Dx5.d;
                    c0450Dx5.d = c0450Dx4;
                    c0450Dx9.d = c0450Dx5.e;
                    c0450Dx5.e = c0450Dx9;
                    byte b2 = (byte) c0450Dx5.f;
                    if (b2 == -1) {
                        c0450Dx4.a(0);
                        c0450Dx9.a(1);
                    } else if (b2 == 0) {
                        c0450Dx4.a(0);
                        c0450Dx9.a(0);
                    } else {
                        c0450Dx4.a(-1);
                        c0450Dx9.a(0);
                    }
                    c0450Dx5.a(0);
                    if (c0450Dx5.c()) {
                        c0450Dx4.b(c0450Dx5);
                        c0450Dx5.a(false);
                    }
                    if (c0450Dx5.e()) {
                        c0450Dx9.a(c0450Dx5);
                        c0450Dx5.b(false);
                    }
                    c0450Dx4 = c0450Dx5;
                }
                if (c0450Dx7 == null) {
                    this.c = c0450Dx4;
                } else if (c0450Dx7.d == c0450Dx9) {
                    c0450Dx7.d = c0450Dx4;
                } else {
                    c0450Dx7.e = c0450Dx4;
                }
            } else if (b == 2) {
                c0450Dx4 = c0450Dx9.e;
                if (((byte) c0450Dx4.f) == 1) {
                    if (c0450Dx4.c()) {
                        c0450Dx4.a(false);
                        c0450Dx9.b(c0450Dx4);
                    } else {
                        c0450Dx9.e = c0450Dx4.d;
                    }
                    c0450Dx4.d = c0450Dx9;
                    c0450Dx4.a(0);
                    c0450Dx9.a(0);
                } else {
                    c0450Dx5 = c0450Dx4.d;
                    c0450Dx4.d = c0450Dx5.e;
                    c0450Dx5.e = c0450Dx4;
                    c0450Dx9.e = c0450Dx5.d;
                    c0450Dx5.d = c0450Dx9;
                    byte b3 = (byte) c0450Dx5.f;
                    if (b3 == 1) {
                        c0450Dx4.a(0);
                        c0450Dx9.a(-1);
                    } else if (b3 == 0) {
                        c0450Dx4.a(0);
                        c0450Dx9.a(0);
                    } else {
                        c0450Dx4.a(1);
                        c0450Dx9.a(0);
                    }
                    c0450Dx5.a(0);
                    if (c0450Dx5.c()) {
                        c0450Dx9.b(c0450Dx5);
                        c0450Dx5.a(false);
                    }
                    if (c0450Dx5.e()) {
                        c0450Dx4.a(c0450Dx5);
                        c0450Dx5.b(false);
                    }
                    c0450Dx4 = c0450Dx5;
                }
                if (c0450Dx7 == null) {
                    this.c = c0450Dx4;
                } else if (c0450Dx7.d == c0450Dx9) {
                    c0450Dx7.d = c0450Dx4;
                } else {
                    c0450Dx7.e = c0450Dx4;
                }
            }
            c0450Dx = c0450Dx2;
        }
        Object obj2 = c0450Dx.c;
        c0450Dx.c = obj;
        return obj2;
    }

    @Override // com.android.tools.r8.internal.O, com.android.tools.r8.internal.InterfaceC0969Xx, com.android.tools.r8.internal.InterfaceC0425Cy
    public final NU b() {
        if (this.g == null) {
            this.g = new C0398Bx(this);
        }
        return this.g;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final InterfaceC0425Cy c(int i) {
        return new C0735Ox(this, 0, true, i, false);
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

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final C0813Rx m13clone() {
        try {
            C0813Rx c0813Rx = (C0813Rx) super.clone();
            c0813Rx.h = null;
            c0813Rx.i = null;
            c0813Rx.g = null;
            c0813Rx.l = new boolean[48];
            if (this.d != 0) {
                C0450Dx c0450Dx = new C0450Dx();
                C0450Dx c0450Dx2 = new C0450Dx();
                C0450Dx c0450Dx3 = this.c;
                c0450Dx.f &= -1073741825;
                c0450Dx.d = c0450Dx3;
                c0450Dx2.a((C0450Dx) null);
                C0450Dx c0450Dx4 = c0450Dx2;
                loop0: while (true) {
                    if (c0450Dx.c()) {
                        while (true) {
                            boolean zE = c0450Dx.e();
                            c0450Dx = c0450Dx.e;
                            if (!zE) {
                                c0450Dx4 = c0450Dx4.e;
                                break;
                            }
                            if (c0450Dx == null) {
                                break loop0;
                            }
                            c0450Dx4 = c0450Dx4.e;
                        }
                    } else {
                        C0450Dx c0450DxM9clone = c0450Dx.d.m9clone();
                        c0450DxM9clone.a(c0450Dx4.d);
                        c0450DxM9clone.b(c0450Dx4);
                        c0450Dx4.f &= -1073741825;
                        c0450Dx4.d = c0450DxM9clone;
                        c0450Dx = c0450Dx.d;
                        c0450Dx4 = c0450DxM9clone;
                    }
                    if (!c0450Dx.e()) {
                        C0450Dx c0450DxM9clone2 = c0450Dx.e.m9clone();
                        c0450DxM9clone2.b(c0450Dx4.e);
                        c0450DxM9clone2.a(c0450Dx4);
                        c0450Dx4.f &= Integer.MAX_VALUE;
                        c0450Dx4.e = c0450DxM9clone2;
                    }
                }
                c0450Dx4.e = null;
                C0450Dx c0450Dx5 = c0450Dx2.d;
                c0813Rx.c = c0450Dx5;
                c0813Rx.e = c0450Dx5;
                while (true) {
                    C0450Dx c0450Dx6 = c0813Rx.e.d;
                    if (c0450Dx6 == null) {
                        break;
                    }
                    c0813Rx.e = c0450Dx6;
                }
                c0813Rx.f = c0813Rx.c;
                while (true) {
                    C0450Dx c0450Dx7 = c0813Rx.f.e;
                    if (c0450Dx7 == null) {
                        break;
                    }
                    c0813Rx.f = c0450Dx7;
                }
            }
            return c0813Rx;
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
        C0787Qx c0787Qx = new C0787Qx(this);
        int i = this.d;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            Object obj2 = c0787Qx.a().c;
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

    public final C0450Dx d(int i) {
        C0450Dx c0450Dx = this.c;
        while (c0450Dx != null) {
            int iC = c(i, c0450Dx.b);
            if (iC == 0) {
                break;
            }
            int i2 = c0450Dx.f;
            if (iC < 0) {
                c0450Dx = (1073741824 & i2) != 0 ? null : c0450Dx.d;
            } else if ((Integer.MIN_VALUE & i2) == 0) {
                c0450Dx = c0450Dx.e;
            }
        }
        return c0450Dx;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0917Vx
    public final Object get(int i) {
        C0450Dx c0450DxD = d(i);
        return c0450DxD == null ? this.b : c0450DxD.c;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.d == 0;
    }

    @Override // com.android.tools.r8.internal.O, java.util.Map, com.android.tools.r8.internal.InterfaceC0425Cy, java.util.SortedMap
    public final CA keySet() {
        if (this.h == null) {
            this.h = new C0528Gx(this);
        }
        return this.h;
    }

    @Override // com.android.tools.r8.internal.K
    public final Object remove(int i) {
        C0450Dx c0450Dx;
        C0450Dx c0450Dx2;
        boolean zC;
        boolean zE;
        C0450Dx c0450Dx3;
        this.j = false;
        C0450Dx c0450Dx4 = this.c;
        if (c0450Dx4 == null) {
            return this.b;
        }
        boolean z = false;
        C0450Dx c0450Dx5 = null;
        while (true) {
            int iC = c(i, c0450Dx4.b);
            if (iC == 0) {
                if (c0450Dx4.d == null) {
                    this.e = c0450Dx4.b();
                }
                if (c0450Dx4.e == null) {
                    this.f = c0450Dx4.d();
                }
                if (!c0450Dx4.e()) {
                    C0450Dx c0450Dx6 = c0450Dx4.e;
                    if (c0450Dx6.c()) {
                        c0450Dx6.d = c0450Dx4.d;
                        c0450Dx6.a(c0450Dx4.c());
                        if (!c0450Dx6.c()) {
                            c0450Dx6.d().e = c0450Dx6;
                        }
                        if (c0450Dx5 == null) {
                            this.c = c0450Dx6;
                        } else if (z) {
                            c0450Dx5.e = c0450Dx6;
                        } else {
                            c0450Dx5.d = c0450Dx6;
                        }
                        c0450Dx6.a((byte) c0450Dx4.f);
                        c0450Dx5 = c0450Dx6;
                        z = true;
                    } else {
                        while (true) {
                            c0450Dx = c0450Dx6.d;
                            if (c0450Dx.c()) {
                                break;
                            }
                            c0450Dx6 = c0450Dx;
                        }
                        if (c0450Dx.e()) {
                            c0450Dx6.a(c0450Dx);
                        } else {
                            c0450Dx6.d = c0450Dx.e;
                        }
                        c0450Dx.d = c0450Dx4.d;
                        if (!c0450Dx4.c()) {
                            c0450Dx4.d().e = c0450Dx;
                            c0450Dx.a(false);
                        }
                        c0450Dx.e = c0450Dx4.e;
                        c0450Dx.b(false);
                        if (c0450Dx5 == null) {
                            this.c = c0450Dx;
                        } else if (z) {
                            c0450Dx5.e = c0450Dx;
                        } else {
                            c0450Dx5.d = c0450Dx;
                        }
                        c0450Dx.a((byte) c0450Dx4.f);
                        c0450Dx5 = c0450Dx6;
                        z = false;
                    }
                } else if (!c0450Dx4.c()) {
                    c0450Dx4.d().e = c0450Dx4.e;
                    if (c0450Dx5 != null) {
                        C0450Dx c0450Dx7 = c0450Dx4.d;
                        if (z) {
                            c0450Dx5.e = c0450Dx7;
                        } else {
                            c0450Dx5.d = c0450Dx7;
                        }
                    } else {
                        this.c = c0450Dx4.d;
                    }
                } else if (c0450Dx5 == null) {
                    this.c = z ? c0450Dx4.e : c0450Dx4.d;
                } else if (z) {
                    c0450Dx5.b(c0450Dx4.e);
                } else {
                    c0450Dx5.a(c0450Dx4.d);
                }
                while (c0450Dx5 != null) {
                    if (c0450Dx5 == this.c) {
                        c0450Dx2 = null;
                    } else {
                        C0450Dx c0450Dx8 = c0450Dx5;
                        c0450Dx2 = c0450Dx8;
                        while (true) {
                            if (c0450Dx8.e()) {
                                c0450Dx8 = c0450Dx8.e;
                                if (c0450Dx8 != null && c0450Dx8.d == c0450Dx5) {
                                    c0450Dx2 = c0450Dx8;
                                    break;
                                }
                                do {
                                    zC = c0450Dx2.c();
                                    c0450Dx2 = c0450Dx2.d;
                                } while (!zC);
                            } else {
                                boolean zC2 = c0450Dx2.c();
                                c0450Dx2 = c0450Dx2.d;
                                if (!zC2) {
                                    c0450Dx8 = c0450Dx8.e;
                                } else if (c0450Dx2 == null || c0450Dx2.e != c0450Dx5) {
                                    do {
                                        zE = c0450Dx8.e();
                                        c0450Dx8 = c0450Dx8.e;
                                    } while (!zE);
                                    c0450Dx2 = c0450Dx8;
                                    break;
                                }
                            }
                        }
                    }
                    if (!z) {
                        z = (c0450Dx2 == null || c0450Dx2.d == c0450Dx5) ? false : true;
                        int i2 = c0450Dx5.f;
                        int i3 = ((((byte) i2) + 1) & 255) | (i2 & (-256));
                        c0450Dx5.f = i3;
                        byte b = (byte) i3;
                        if (b == 1) {
                            break;
                        }
                        if (b == 2) {
                            C0450Dx c0450Dx9 = c0450Dx5.e;
                            byte b2 = (byte) c0450Dx9.f;
                            if (b2 == -1) {
                                C0450Dx c0450Dx10 = c0450Dx9.d;
                                c0450Dx9.d = c0450Dx10.e;
                                c0450Dx10.e = c0450Dx9;
                                c0450Dx5.e = c0450Dx10.d;
                                c0450Dx10.d = c0450Dx5;
                                byte b3 = (byte) c0450Dx10.f;
                                if (b3 == 1) {
                                    c0450Dx9.a(0);
                                    c0450Dx5.a(-1);
                                } else if (b3 == 0) {
                                    c0450Dx9.a(0);
                                    c0450Dx5.a(0);
                                } else {
                                    c0450Dx9.a(1);
                                    c0450Dx5.a(0);
                                }
                                c0450Dx10.a(0);
                                if (c0450Dx10.c()) {
                                    c0450Dx5.b(c0450Dx10);
                                    c0450Dx10.a(false);
                                }
                                if (c0450Dx10.e()) {
                                    c0450Dx9.a(c0450Dx10);
                                    c0450Dx10.b(false);
                                }
                                if (c0450Dx2 == null) {
                                    this.c = c0450Dx10;
                                } else if (z) {
                                    c0450Dx2.e = c0450Dx10;
                                } else {
                                    c0450Dx2.d = c0450Dx10;
                                }
                            } else {
                                if (c0450Dx2 == null) {
                                    this.c = c0450Dx9;
                                } else if (z) {
                                    c0450Dx2.e = c0450Dx9;
                                } else {
                                    c0450Dx2.d = c0450Dx9;
                                }
                                if (b2 == 0) {
                                    c0450Dx5.e = c0450Dx9.d;
                                    c0450Dx9.d = c0450Dx5;
                                    c0450Dx9.a(-1);
                                    c0450Dx5.a(1);
                                    break;
                                }
                                if (c0450Dx9.c()) {
                                    c0450Dx5.b(true);
                                    c0450Dx9.a(false);
                                } else {
                                    c0450Dx5.e = c0450Dx9.d;
                                }
                                c0450Dx9.d = c0450Dx5;
                                c0450Dx5.a(0);
                                c0450Dx9.a(0);
                            }
                        } else {
                            continue;
                        }
                        c0450Dx5 = c0450Dx2;
                    } else {
                        z = (c0450Dx2 == null || c0450Dx2.d == c0450Dx5) ? false : true;
                        int i4 = c0450Dx5.f;
                        int i5 = ((((byte) i4) - 1) & 255) | (i4 & (-256));
                        c0450Dx5.f = i5;
                        byte b4 = (byte) i5;
                        if (b4 == -1) {
                            break;
                        }
                        if (b4 == -2) {
                            C0450Dx c0450Dx11 = c0450Dx5.d;
                            byte b5 = (byte) c0450Dx11.f;
                            if (b5 == 1) {
                                C0450Dx c0450Dx12 = c0450Dx11.e;
                                c0450Dx11.e = c0450Dx12.d;
                                c0450Dx12.d = c0450Dx11;
                                c0450Dx5.d = c0450Dx12.e;
                                c0450Dx12.e = c0450Dx5;
                                byte b6 = (byte) c0450Dx12.f;
                                if (b6 == -1) {
                                    c0450Dx11.a(0);
                                    c0450Dx5.a(1);
                                } else if (b6 == 0) {
                                    c0450Dx11.a(0);
                                    c0450Dx5.a(0);
                                } else {
                                    c0450Dx11.a(-1);
                                    c0450Dx5.a(0);
                                }
                                c0450Dx12.a(0);
                                if (c0450Dx12.c()) {
                                    c0450Dx11.b(c0450Dx12);
                                    c0450Dx12.a(false);
                                }
                                if (c0450Dx12.e()) {
                                    c0450Dx5.a(c0450Dx12);
                                    c0450Dx12.b(false);
                                }
                                if (c0450Dx2 == null) {
                                    this.c = c0450Dx12;
                                } else if (z) {
                                    c0450Dx2.e = c0450Dx12;
                                } else {
                                    c0450Dx2.d = c0450Dx12;
                                }
                            } else {
                                if (c0450Dx2 == null) {
                                    this.c = c0450Dx11;
                                } else if (z) {
                                    c0450Dx2.e = c0450Dx11;
                                } else {
                                    c0450Dx2.d = c0450Dx11;
                                }
                                if (b5 == 0) {
                                    c0450Dx5.d = c0450Dx11.e;
                                    c0450Dx11.e = c0450Dx5;
                                    c0450Dx11.a(1);
                                    c0450Dx5.a(-1);
                                    break;
                                }
                                if (c0450Dx11.e()) {
                                    c0450Dx5.a(true);
                                    c0450Dx11.b(false);
                                } else {
                                    c0450Dx5.d = c0450Dx11.e;
                                }
                                c0450Dx11.e = c0450Dx5;
                                c0450Dx5.a(0);
                                c0450Dx11.a(0);
                            }
                        } else {
                            continue;
                        }
                        c0450Dx5 = c0450Dx2;
                    }
                }
                this.j = true;
                this.d--;
                return c0450Dx4.c;
            }
            z = iC > 0;
            int i6 = c0450Dx4.f;
            if (z) {
                c0450Dx3 = (i6 & Integer.MIN_VALUE) != 0 ? null : c0450Dx4.e;
                if (c0450Dx3 == null) {
                    return this.b;
                }
            } else {
                c0450Dx3 = (i6 & 1073741824) != 0 ? null : c0450Dx4.d;
                if (c0450Dx3 == null) {
                    return this.b;
                }
            }
            C0450Dx c0450Dx13 = c0450Dx3;
            c0450Dx5 = c0450Dx4;
            c0450Dx4 = c0450Dx13;
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.M, com.android.tools.r8.internal.InterfaceC0969Xx, java.util.Map, com.android.tools.r8.internal.InterfaceC0425Cy, java.util.SortedMap
    public final InterfaceC3028xU values() {
        if (this.i == null) {
            this.i = new C0424Cx(this);
        }
        return this.i;
    }

    @Override // java.util.SortedMap
    public final Comparator comparator() {
        return null;
    }

    public final int c(int i, int i2) {
        return Integer.compare(i, i2);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final InterfaceC0425Cy b(int i) {
        return new C0735Ox(this, i, false, 0, true);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final int d() {
        if (this.c != null) {
            return this.f.b;
        }
        z0e.a();
        return 0;
    }

    public C0813Rx(AbstractC3239zx abstractC3239zx) {
        this();
        this.k = abstractC3239zx;
    }

    public C0813Rx() {
        this.l = new boolean[48];
        this.c = null;
        this.d = 0;
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
        return new C0735Ox(this, i, false, i2, false);
    }
}
