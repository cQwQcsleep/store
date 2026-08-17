package com.android.tools.r8.internal;

import java.util.Comparator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Lz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0659Lz extends U {
    public final int c;
    public final int d;
    public final boolean e;
    public final boolean f;
    public transient C0478Ez g;
    public transient C0530Gz h;
    public transient C0504Fz i;
    public final /* synthetic */ C0737Oz j;

    public C0659Lz(C0737Oz c0737Oz, int i, boolean z, int i2, boolean z2) {
        this.j = c0737Oz;
        if (!z && !z2 && c0737Oz.c(i, i2) > 0) {
            pnd.a("Start key (", i, ") is larger than end key (", i2, ")");
            throw null;
        }
        this.c = i;
        this.e = z;
        this.d = i2;
        this.f = z2;
        this.b = c0737Oz.b;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz a(int i, int i2) {
        boolean z = this.f;
        if (z && this.e) {
            return new C0659Lz(this.j, i, false, i2, false);
        }
        int i3 = (z || this.j.c(i2, this.d) < 0) ? i2 : this.d;
        int i4 = (this.e || this.j.c(i, this.c) > 0) ? i : this.c;
        return (this.f || this.e || i4 != this.c || i3 != this.d) ? new C0659Lz(this.j, i4, false, i3, false) : this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz b(int i) {
        boolean z = this.e;
        C0737Oz c0737Oz = this.j;
        if (z) {
            return new C0659Lz(c0737Oz, i, false, this.d, this.f);
        }
        return c0737Oz.c(i, this.c) > 0 ? new C0659Lz(this.j, i, false, this.d, this.f) : this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz c(int i) {
        boolean z = this.f;
        C0737Oz c0737Oz = this.j;
        if (z) {
            return new C0659Lz(c0737Oz, this.c, this.e, i, false);
        }
        return c0737Oz.c(i, this.d) < 0 ? new C0659Lz(this.j, this.c, this.e, i, false) : this;
    }

    @Override // com.android.tools.r8.internal.P, java.util.Map
    public final void clear() {
        C0582Iz c0582Iz = new C0582Iz(this);
        while (c0582Iz.hasNext()) {
            c0582Iz.a();
            c0582Iz.remove();
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final AbstractC3239zx comparator() {
        this.j.getClass();
        return null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        C0582Iz c0582Iz = new C0582Iz(this);
        while (c0582Iz.hasNext()) {
            if (c0582Iz.a().c == obj) {
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

    @Override // com.android.tools.r8.internal.InterfaceC1109az
    public final Object get(int i) {
        C0374Az c0374AzD;
        return (!d(i) || (c0374AzD = this.j.d(i)) == null) ? this.b : c0374AzD.c;
    }

    public final C0374Az i() {
        C0374Az c0374AzE;
        C0737Oz c0737Oz = this.j;
        if (c0737Oz.c == null) {
            return null;
        }
        if (this.e) {
            c0374AzE = c0737Oz.e;
        } else {
            c0374AzE = c0737Oz.e(this.c);
            if (this.j.c(c0374AzE.b, this.c) < 0) {
                c0374AzE = c0374AzE.c();
            }
        }
        if (c0374AzE == null || (!this.f && this.j.c(c0374AzE.b, this.d) >= 0)) {
            return null;
        }
        return c0374AzE;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        C0374Az c0374Az = this.j.e;
        return !(i() != null);
    }

    public final C0374Az j() {
        C0374Az c0374AzE;
        C0737Oz c0737Oz = this.j;
        if (c0737Oz.c == null) {
            return null;
        }
        if (this.f) {
            c0374AzE = c0737Oz.f;
        } else {
            c0374AzE = c0737Oz.e(this.d);
            if (this.j.c(c0374AzE.b, this.d) >= 0) {
                c0374AzE = c0374AzE.e();
            }
        }
        if (c0374AzE == null || (!this.e && this.j.c(c0374AzE.b, this.c) < 0)) {
            return null;
        }
        return c0374AzE;
    }

    @Override // com.android.tools.r8.internal.U, com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final CA keySet() {
        if (this.h == null) {
            this.h = new C0530Gz(this);
        }
        return this.h;
    }

    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC1109az
    public final Object remove(int i) {
        this.j.j = false;
        if (d(i)) {
            return this.j.j ? this.j.remove(i) : this.b;
        }
        return this.b;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        C0582Iz c0582Iz = new C0582Iz(this);
        int i = 0;
        while (c0582Iz.hasNext()) {
            i++;
            c0582Iz.a();
        }
        return i;
    }

    @Override // com.android.tools.r8.internal.S, com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final P30 values() {
        if (this.i == null) {
            this.i = new C0504Fz(this);
        }
        return this.i;
    }

    @Override // java.util.SortedMap
    public final Comparator comparator() {
        this.j.getClass();
        return null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final int d() {
        C0374Az c0374AzJ = j();
        if (c0374AzJ != null) {
            return c0374AzJ.b;
        }
        z0e.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.U, com.android.tools.r8.internal.InterfaceC2045lz
    public final NU c() {
        if (this.g == null) {
            this.g = new C0478Ez(this);
        }
        return this.g;
    }

    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC1109az
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

    @Override // com.android.tools.r8.internal.S, com.android.tools.r8.internal.InterfaceC1109az
    public final boolean a(int i) {
        return d(i) && this.j.a(i);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final int a() {
        C0374Az c0374AzI = i();
        if (c0374AzI != null) {
            return c0374AzI.b;
        }
        z0e.a();
        return 0;
    }
}
