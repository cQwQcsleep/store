package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class SR extends AbstractC1832jW {
    public static final /* synthetic */ boolean g = true;
    public final Set a;
    public final Set b;
    public final InterfaceC1231cQ c;
    public final boolean d;
    public final boolean e;
    public final boolean f;

    public SR(Set set, Set set2, AbstractC1529fv abstractC1529fv, boolean z, boolean z2, boolean z3) {
        if (!g && set.isEmpty() && set2.isEmpty() && abstractC1529fv.isEmpty() && !z && !z2 && !z3) {
            x1f.a();
            throw null;
        }
        this.a = set;
        this.b = set2;
        this.c = abstractC1529fv;
        this.d = z;
        this.e = z2;
        this.f = z3;
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW a(com.android.tools.r8.graph.I2 i2) {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final SR b() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW c() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final boolean d() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW e() {
        throw new Kk0();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == SR.class) {
            SR sr = (SR) obj;
            if (this.d == sr.d && this.e == sr.e && this.f == sr.f && this.a.equals(sr.a) && this.b.equals(sr.b) && this.c.equals(sr.c)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW f() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW g() {
        throw new Kk0();
    }

    public final int hashCode() {
        int iHashCode = this.c.hashCode() + ((this.b.hashCode() + ((this.a.hashCode() + 31) * 31)) * 31);
        if (g || iHashCode == Objects.hash(this.a, this.b, this.c)) {
            return Y6.a(this.f) | (((((iHashCode << 1) | Y6.a(this.d)) << 1) | Y6.a(this.e)) << 1);
        }
        x1f.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW a(C0245l1 c0245l1) {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW a(AbstractC1133bC abstractC1133bC) {
        throw new Kk0();
    }
}
