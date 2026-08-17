package com.android.tools.r8.internal;

import java.util.Comparator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3154yy extends O {
    public final int c;
    public final int d;
    public final boolean e;
    public final boolean f;
    public transient C2557ry g;
    public transient C2728ty h;
    public transient C2642sy i;
    public final /* synthetic */ C0399By j;

    public C3154yy(C0399By c0399By, int i, boolean z, int i2, boolean z2) {
        this.j = c0399By;
        if (!z && !z2 && c0399By.c(i, i2) > 0) {
            pnd.a("Start key (", i, ") is larger than end key (", i2, ")");
            throw null;
        }
        this.c = i;
        this.e = z;
        this.d = i2;
        this.f = z2;
        this.b = c0399By.b;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final InterfaceC0425Cy a(int i, int i2) {
        boolean z = this.f;
        if (z && this.e) {
            return new C3154yy(this.j, i, false, i2, false);
        }
        int i3 = (z || this.j.c(i2, this.d) < 0) ? i2 : this.d;
        int i4 = (this.e || this.j.c(i, this.c) > 0) ? i : this.c;
        return (this.f || this.e || i4 != this.c || i3 != this.d) ? new C3154yy(this.j, i4, false, i3, false) : this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final InterfaceC0425Cy b(int i) {
        boolean z = this.e;
        C0399By c0399By = this.j;
        if (z) {
            return new C3154yy(c0399By, i, false, this.d, this.f);
        }
        return c0399By.c(i, this.c) > 0 ? new C3154yy(this.j, i, false, this.d, this.f) : this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy
    public final InterfaceC0425Cy c(int i) {
        boolean z = this.f;
        C0399By c0399By = this.j;
        if (z) {
            return new C3154yy(c0399By, this.c, this.e, i, false);
        }
        return c0399By.c(i, this.d) < 0 ? new C3154yy(this.j, this.c, this.e, i, false) : this;
    }

    @Override // com.android.tools.r8.internal.K, java.util.Map
    public final void clear() {
        C2899vy c2899vy = new C2899vy(this);
        while (c2899vy.hasNext()) {
            c2899vy.a();
            c2899vy.remove();
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC0425Cy, java.util.SortedMap
    public final AbstractC3239zx comparator() {
        this.j.getClass();
        return null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        C2899vy c2899vy = new C2899vy(this);
        while (c2899vy.hasNext()) {
            Object obj2 = c2899vy.a().c;
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
        C2215ny c2215nyD;
        return (!d(i) || (c2215nyD = this.j.d(i)) == null) ? this.b : c2215nyD.c;
    }

    public final C2215ny i() {
        C2215ny c2215nyE;
        C0399By c0399By = this.j;
        if (c0399By.c == null) {
            return null;
        }
        if (this.e) {
            c2215nyE = c0399By.e;
        } else {
            c2215nyE = c0399By.e(this.c);
            if (this.j.c(c2215nyE.b, this.c) < 0) {
                c2215nyE = c2215nyE.c();
            }
        }
        if (c2215nyE == null || (!this.f && this.j.c(c2215nyE.b, this.d) >= 0)) {
            return null;
        }
        return c2215nyE;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        C2215ny c2215ny = this.j.e;
        return !(i() != null);
    }

    public final C2215ny j() {
        C2215ny c2215nyE;
        C0399By c0399By = this.j;
        if (c0399By.c == null) {
            return null;
        }
        if (this.f) {
            c2215nyE = c0399By.f;
        } else {
            c2215nyE = c0399By.e(this.d);
            if (this.j.c(c2215nyE.b, this.d) >= 0) {
                c2215nyE = c2215nyE.e();
            }
        }
        if (c2215nyE == null || (!this.e && this.j.c(c2215nyE.b, this.c) < 0)) {
            return null;
        }
        return c2215nyE;
    }

    @Override // com.android.tools.r8.internal.O, java.util.Map, com.android.tools.r8.internal.InterfaceC0425Cy, java.util.SortedMap
    public final CA keySet() {
        if (this.h == null) {
            this.h = new C2728ty(this);
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
        C2899vy c2899vy = new C2899vy(this);
        int i = 0;
        while (c2899vy.hasNext()) {
            i++;
            c2899vy.a();
        }
        return i;
    }

    @Override // com.android.tools.r8.internal.M, com.android.tools.r8.internal.InterfaceC0969Xx, java.util.Map, com.android.tools.r8.internal.InterfaceC0425Cy, java.util.SortedMap
    public final InterfaceC3028xU values() {
        if (this.i == null) {
            this.i = new C2642sy(this);
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
        C2215ny c2215nyJ = j();
        if (c2215nyJ != null) {
            return c2215nyJ.b;
        }
        z0e.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.O, com.android.tools.r8.internal.InterfaceC0969Xx, com.android.tools.r8.internal.InterfaceC0425Cy
    public final NU b() {
        if (this.g == null) {
            this.g = new C2557ry(this);
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
        C2215ny c2215nyI = i();
        if (c2215nyI != null) {
            return c2215nyI.b;
        }
        z0e.a();
        return 0;
    }
}
