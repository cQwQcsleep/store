package com.android.tools.r8.internal;

import java.util.Comparator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ox, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0735Ox extends O {
    public final int c;
    public final int d;
    public final boolean e;
    public final boolean f;
    public transient C0554Hx g;
    public transient C0606Jx h;
    public transient C0580Ix i;
    public final /* synthetic */ C0813Rx j;

    public C0735Ox(C0813Rx c0813Rx, int i, boolean z, int i2, boolean z2) {
        this.j = c0813Rx;
        if (!z && !z2 && c0813Rx.c(i, i2) > 0) {
            pnd.a("Start key (", i, ") is larger than end key (", i2, ")");
            throw null;
        }
        this.c = i;
        this.e = z;
        this.d = i2;
        this.f = z2;
        this.b = c0813Rx.b;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final InterfaceC0425Cy a(int i, int i2) {
        boolean z = this.f;
        if (z && this.e) {
            return new C0735Ox(this.j, i, false, i2, false);
        }
        int i3 = (z || this.j.c(i2, this.d) < 0) ? i2 : this.d;
        int i4 = (this.e || this.j.c(i, this.c) > 0) ? i : this.c;
        return (this.f || this.e || i4 != this.c || i3 != this.d) ? new C0735Ox(this.j, i4, false, i3, false) : this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final InterfaceC0425Cy b(int i) {
        boolean z = this.e;
        C0813Rx c0813Rx = this.j;
        if (z) {
            return new C0735Ox(c0813Rx, i, false, this.d, this.f);
        }
        return c0813Rx.c(i, this.c) > 0 ? new C0735Ox(this.j, i, false, this.d, this.f) : this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final InterfaceC0425Cy c(int i) {
        boolean z = this.f;
        C0813Rx c0813Rx = this.j;
        if (z) {
            return new C0735Ox(c0813Rx, this.c, this.e, i, false);
        }
        return c0813Rx.c(i, this.d) < 0 ? new C0735Ox(this.j, this.c, this.e, i, false) : this;
    }

    @Override // com.android.tools.r8.internal.K, java.util.Map
    public final void clear() {
        C0657Lx c0657Lx = new C0657Lx(this);
        while (c0657Lx.hasNext()) {
            c0657Lx.a();
            c0657Lx.remove();
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy, java.util.SortedMap
    public final AbstractC3239zx comparator() {
        this.j.getClass();
        return null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        C0657Lx c0657Lx = new C0657Lx(this);
        while (c0657Lx.hasNext()) {
            Object obj2 = c0657Lx.a().c;
            if (obj2 == null) {
                if (obj == null) {
                    return true;
                }
            } else if (obj2.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public final boolean d(int i) {
        if (this.e || this.j.c(i, this.c) >= 0) {
            return this.f || this.j.c(i, this.d) < 0;
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0917Vx
    public final Object get(int i) {
        C0450Dx c0450DxD;
        return (!d(i) || (c0450DxD = this.j.d(i)) == null) ? this.b : c0450DxD.c;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0030  */
    public final C0450Dx i() {
        C0450Dx c0450DxB;
        C0450Dx c0450Dx;
        C0813Rx c0813Rx = this.j;
        C0450Dx c0450Dx2 = c0813Rx.c;
        if (c0450Dx2 == null) {
            return null;
        }
        if (this.e) {
            c0450DxB = c0813Rx.e;
        } else {
            int i = this.c;
            int iC = 0;
            C0450Dx c0450Dx3 = c0450Dx2;
            while (c0450Dx2 != null) {
                iC = c0813Rx.c(i, c0450Dx2.b);
                if (iC == 0) {
                    break;
                }
                int i2 = c0450Dx2.f;
                if (iC < 0) {
                    if ((i2 & 1073741824) != 0) {
                        c0450Dx = null;
                    } else {
                        c0450Dx = c0450Dx2.d;
                    }
                } else if ((i2 & Integer.MIN_VALUE) != 0) {
                    c0450Dx = null;
                } else {
                    c0450Dx = c0450Dx2.e;
                }
                C0450Dx c0450Dx4 = c0450Dx;
                c0450Dx3 = c0450Dx2;
                c0450Dx2 = c0450Dx4;
            }
            c0450DxB = iC == 0 ? c0450Dx2 : c0450Dx3;
            if (this.j.c(c0450DxB.b, this.c) < 0) {
                c0450DxB = c0450DxB.b();
            }
        }
        if (c0450DxB == null || (!this.f && this.j.c(c0450DxB.b, this.d) >= 0)) {
            return null;
        }
        return c0450DxB;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        C0450Dx c0450Dx = this.j.e;
        return !(i() != null);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0030  */
    public final C0450Dx j() {
        C0450Dx c0450DxD;
        C0450Dx c0450Dx;
        C0813Rx c0813Rx = this.j;
        C0450Dx c0450Dx2 = c0813Rx.c;
        if (c0450Dx2 == null) {
            return null;
        }
        if (this.f) {
            c0450DxD = c0813Rx.f;
        } else {
            int i = this.d;
            int iC = 0;
            C0450Dx c0450Dx3 = c0450Dx2;
            while (c0450Dx2 != null) {
                iC = c0813Rx.c(i, c0450Dx2.b);
                if (iC == 0) {
                    break;
                }
                int i2 = c0450Dx2.f;
                if (iC < 0) {
                    if ((i2 & 1073741824) != 0) {
                        c0450Dx = null;
                    } else {
                        c0450Dx = c0450Dx2.d;
                    }
                } else if ((i2 & Integer.MIN_VALUE) != 0) {
                    c0450Dx = null;
                } else {
                    c0450Dx = c0450Dx2.e;
                }
                C0450Dx c0450Dx4 = c0450Dx;
                c0450Dx3 = c0450Dx2;
                c0450Dx2 = c0450Dx4;
            }
            c0450DxD = iC == 0 ? c0450Dx2 : c0450Dx3;
            if (this.j.c(c0450DxD.b, this.d) >= 0) {
                c0450DxD = c0450DxD.d();
            }
        }
        if (c0450DxD == null || (!this.e && this.j.c(c0450DxD.b, this.c) < 0)) {
            return null;
        }
        return c0450DxD;
    }

    @Override // com.android.tools.r8.internal.O, java.util.Map, com.android.tools.r8.internal.InterfaceC0425Cy, java.util.SortedMap
    public final CA keySet() {
        if (this.h == null) {
            this.h = new C0606Jx(this);
        }
        return this.h;
    }

    @Override // com.android.tools.r8.internal.K
    public final Object remove(int i) {
        this.j.j = false;
        if (d(i)) {
            return this.j.j ? this.j.remove(i) : this.b;
        }
        return this.b;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        C0657Lx c0657Lx = new C0657Lx(this);
        int i = 0;
        while (c0657Lx.hasNext()) {
            i++;
            c0657Lx.a();
        }
        return i;
    }

    @Override // com.android.tools.r8.internal.M, com.android.tools.r8.internal.InterfaceC0969Xx, java.util.Map, com.android.tools.r8.internal.InterfaceC0425Cy, java.util.SortedMap
    public final InterfaceC3028xU values() {
        if (this.i == null) {
            this.i = new C0580Ix(this);
        }
        return this.i;
    }

    @Override // java.util.SortedMap
    public final Comparator comparator() {
        this.j.getClass();
        return null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final int d() {
        C0450Dx c0450DxJ = j();
        if (c0450DxJ != null) {
            return c0450DxJ.b;
        }
        z0e.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.O, com.android.tools.r8.internal.InterfaceC0969Xx, com.android.tools.r8.internal.InterfaceC0425Cy
    public final NU b() {
        if (this.g == null) {
            this.g = new C0554Hx(this);
        }
        return this.g;
    }

    @Override // com.android.tools.r8.internal.K, com.android.tools.r8.internal.InterfaceC0917Vx
    public final Object a(int i, Object obj) {
        this.j.j = false;
        if (d(i)) {
            return this.j.j ? this.b : this.j.a(i, obj);
        }
        StringBuilder sbA = Ni0.a(i, "Key (", ") out of range [");
        sbA.append(this.e ? "-" : String.valueOf(this.c));
        sbA.append(", ");
        sbA.append(this.f ? "-" : String.valueOf(this.d));
        sbA.append(")");
        throw new IllegalArgumentException(sbA.toString());
    }

    @Override // com.android.tools.r8.internal.M, com.android.tools.r8.internal.InterfaceC0917Vx
    public final boolean a(int i) {
        return d(i) && this.j.a(i);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final int a() {
        C0450Dx c0450DxI = i();
        if (c0450DxI != null) {
            return c0450DxI.b;
        }
        z0e.a();
        return 0;
    }
}
