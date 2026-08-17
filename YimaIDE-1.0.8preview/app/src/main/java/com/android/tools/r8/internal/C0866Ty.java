package com.android.tools.r8.internal;

import java.util.Comparator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ty, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0866Ty extends U {
    public final int c;
    public final int d;
    public final boolean e;
    public final boolean f;
    public transient C0684My g;
    public transient C0736Oy h;
    public transient C0710Ny i;
    public final /* synthetic */ C0944Wy j;

    public C0866Ty(C0944Wy c0944Wy, int i, boolean z, int i2, boolean z2) {
        this.j = c0944Wy;
        if (!z && !z2 && c0944Wy.c(i, i2) > 0) {
            pnd.a("Start key (", i, ") is larger than end key (", i2, ")");
            throw null;
        }
        this.c = i;
        this.e = z;
        this.d = i2;
        this.f = z2;
        this.b = c0944Wy.b;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz a(int i, int i2) {
        boolean z = this.f;
        if (z && this.e) {
            return new C0866Ty(this.j, i, false, i2, false);
        }
        int i3 = (z || this.j.c(i2, this.d) < 0) ? i2 : this.d;
        int i4 = (this.e || this.j.c(i, this.c) > 0) ? i : this.c;
        return (this.f || this.e || i4 != this.c || i3 != this.d) ? new C0866Ty(this.j, i4, false, i3, false) : this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz b(int i) {
        boolean z = this.e;
        C0944Wy c0944Wy = this.j;
        if (z) {
            return new C0866Ty(c0944Wy, i, false, this.d, this.f);
        }
        return c0944Wy.c(i, this.c) > 0 ? new C0866Ty(this.j, i, false, this.d, this.f) : this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz c(int i) {
        boolean z = this.f;
        C0944Wy c0944Wy = this.j;
        if (z) {
            return new C0866Ty(c0944Wy, this.c, this.e, i, false);
        }
        return c0944Wy.c(i, this.d) < 0 ? new C0866Ty(this.j, this.c, this.e, i, false) : this;
    }

    @Override // com.android.tools.r8.internal.P, java.util.Map
    public final void clear() {
        C0788Qy c0788Qy = new C0788Qy(this);
        while (c0788Qy.hasNext()) {
            c0788Qy.a();
            c0788Qy.remove();
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final AbstractC3239zx comparator() {
        this.j.getClass();
        return null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        C0788Qy c0788Qy = new C0788Qy(this);
        while (c0788Qy.hasNext()) {
            if (c0788Qy.a().c == obj) {
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
        C0581Iy c0581IyD;
        return (!d(i) || (c0581IyD = this.j.d(i)) == null) ? this.b : c0581IyD.c;
    }

    public final C0581Iy i() {
        C0581Iy c0581IyE;
        C0944Wy c0944Wy = this.j;
        if (c0944Wy.c == null) {
            return null;
        }
        if (this.e) {
            c0581IyE = c0944Wy.e;
        } else {
            c0581IyE = c0944Wy.e(this.c);
            if (this.j.c(c0581IyE.b, this.c) < 0) {
                c0581IyE = c0581IyE.b();
            }
        }
        if (c0581IyE == null || (!this.f && this.j.c(c0581IyE.b, this.d) >= 0)) {
            return null;
        }
        return c0581IyE;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        C0581Iy c0581Iy = this.j.e;
        return !(i() != null);
    }

    public final C0581Iy j() {
        C0581Iy c0581IyE;
        C0944Wy c0944Wy = this.j;
        if (c0944Wy.c == null) {
            return null;
        }
        if (this.f) {
            c0581IyE = c0944Wy.f;
        } else {
            c0581IyE = c0944Wy.e(this.d);
            if (this.j.c(c0581IyE.b, this.d) >= 0) {
                c0581IyE = c0581IyE.d();
            }
        }
        if (c0581IyE == null || (!this.e && this.j.c(c0581IyE.b, this.c) < 0)) {
            return null;
        }
        return c0581IyE;
    }

    @Override // com.android.tools.r8.internal.U, com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final CA keySet() {
        if (this.h == null) {
            this.h = new C0736Oy(this);
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
        C0788Qy c0788Qy = new C0788Qy(this);
        int i = 0;
        while (c0788Qy.hasNext()) {
            i++;
            c0788Qy.a();
        }
        return i;
    }

    @Override // com.android.tools.r8.internal.S, com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final P30 values() {
        if (this.i == null) {
            this.i = new C0710Ny(this);
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
        C0581Iy c0581IyJ = j();
        if (c0581IyJ != null) {
            return c0581IyJ.b;
        }
        z0e.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.U, com.android.tools.r8.internal.InterfaceC2045lz
    public final NU c() {
        if (this.g == null) {
            this.g = new C0684My(this);
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
        C0581Iy c0581IyI = i();
        if (c0581IyI != null) {
            return c0581IyI.b;
        }
        z0e.a();
        return 0;
    }
}
