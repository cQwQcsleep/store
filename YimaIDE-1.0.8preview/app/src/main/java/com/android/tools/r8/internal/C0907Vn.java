package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Vn, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0907Vn extends DQ {
    public static final /* synthetic */ boolean t = true;
    public final C1 p;
    public final Map q;
    public final C2974wn r;
    public final Set s;

    public C0907Vn(C0333y c0333y, C1291d6 c1291d6, C1207c6 c1207c6, Z5 z5, IdentityHashMap identityHashMap, AbstractC0706Nu abstractC0706Nu, Set set) {
        super(c0333y, c1291d6, identityHashMap, z5, c1207c6);
        if (!t && c0333y.R().b()) {
            x1f.a();
            throw null;
        }
        this.p = c0333y.t;
        this.q = abstractC0706Nu;
        this.r = c0333y.R();
        this.s = set;
    }

    @Override // com.android.tools.r8.internal.CQ, com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public final C2850vO a(C2850vO c2850vO, C0322w2 c0322w2, AbstractC3148ys abstractC3148ys) {
        C0322w2 c0322w3;
        boolean z = t;
        if (!z && c0322w2 == null && !f(abstractC3148ys, (C0322w2) c2850vO.a)) {
            x1f.a();
            return null;
        }
        if (!z && c0322w2 != null && c2850vO.c == null) {
            x1f.a();
            return null;
        }
        if (c2850vO.c != EnumC2326pC.g) {
            c0322w3 = (C0322w2) this.g.apply((C0322w2) c2850vO.a);
        } else {
            if (!z && c0322w2 == null) {
                x1f.a();
                return null;
            }
            C0322w2 c0322w4 = (C0322w2) this.i.c(c0322w2, c0322w2);
            com.android.tools.r8.graph.I2 i2C = this.r.c(c0322w4.w0());
            if (!this.r.b(i2C)) {
                c0322w3 = (C0322w2) this.g.apply((C0322w2) c2850vO.a);
            } else {
                if (!i2C.b(c0322w4.w0())) {
                    return c2850vO.a(this);
                }
                C0322w2 c0322w2A = (C0322w2) c2850vO.a;
                if (c0322w2A.w0().b(i2C)) {
                    c0322w2A = c0322w2A.a(i2C, this.c);
                }
                c0322w3 = (C0322w2) this.i.c(c0322w2A);
            }
        }
        if (c0322w3 == null) {
            return c2850vO.a(this);
        }
        com.android.tools.r8.graph.proto.j jVar = com.android.tools.r8.graph.proto.j.d;
        return new C2850vO(c0322w3, null, a(c0322w3, (C0322w2) c2850vO.a, c2850vO.c), a(c2850vO.d, (C0322w2) c2850vO.a, c0322w3)).a(this);
    }

    @Override // com.android.tools.r8.internal.CQ, com.android.tools.r8.internal.AbstractC3148ys
    public final boolean f(AbstractC3148ys abstractC3148ys, C0322w2 c0322w2) {
        if (abstractC3148ys == this) {
            return true;
        }
        boolean z = t;
        if (!z && !this.d.f(abstractC3148ys, (C0322w2) this.i.c(c0322w2, c0322w2))) {
            x1f.a();
            return false;
        }
        C0322w2 c0322w3 = (C0322w2) this.d.a((C0322w2) this.i.c(c0322w2, c0322w2), (C0322w2) null, (EnumC2326pC) null, abstractC3148ys).a;
        if (z || this.r.c(c0322w3.w0()) == c0322w3.w0()) {
            return true;
        }
        x1f.a();
        return false;
    }

    @Override // com.android.tools.r8.internal.XR, com.android.tools.r8.internal.AbstractC3148ys
    public final C2850vO a(C0322w2 c0322w2, C0322w2 c0322w3, EnumC2326pC enumC2326pC, AbstractC3148ys abstractC3148ys, InterfaceC3064xs interfaceC3064xs) {
        if (this == abstractC3148ys) {
            return interfaceC3064xs.a(new C2850vO(c0322w2, c0322w2, enumC2326pC, com.android.tools.r8.graph.proto.j.d).a(this));
        }
        return super.a(c0322w2, c0322w3, enumC2326pC, abstractC3148ys, interfaceC3064xs);
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.AbstractC3148ys
    public final boolean a(AbstractC3148ys abstractC3148ys) {
        if (abstractC3148ys == this) {
            return true;
        }
        return this.r.b.isEmpty() && this.d.a(abstractC3148ys);
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final C0907Vn a() {
        return this;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0034  */
    @Override // com.android.tools.r8.internal.CQ
    public final com.android.tools.r8.graph.proto.j a(com.android.tools.r8.graph.proto.j jVar, C0322w2 c0322w2, C0322w2 c0322w3) {
        com.android.tools.r8.graph.proto.k kVar;
        AbstractC3122yc0 abstractC3122yc0;
        AbstractC3122yc0 abstractC3122yc0A;
        if (jVar.f() && (abstractC3122yc0 = (kVar = jVar.c).e) != null) {
            if (abstractC3122yc0.K()) {
                AbstractC2269oc0 abstractC2269oc0R = abstractC3122yc0.r();
                if (this.r.b(abstractC2269oc0R.b)) {
                    abstractC3122yc0A = this.p.a(this.r.a(abstractC2269oc0R.b), AbstractC2624sj0.k());
                } else {
                    abstractC3122yc0A = abstractC3122yc0;
                }
            } else {
                abstractC3122yc0A = abstractC3122yc0;
            }
            if (abstractC3122yc0A != abstractC3122yc0) {
                com.android.tools.r8.graph.proto.k.a aVarE = com.android.tools.r8.graph.proto.k.e();
                aVarE.a = kVar.b;
                com.android.tools.r8.graph.proto.k.a aVarA = aVarE.b(kVar.g()).a(kVar.f());
                aVarA.d = abstractC3122yc0A;
                com.android.tools.r8.graph.proto.k kVarA = aVarA.a();
                if (!Objects.equals(jVar.c, kVarA)) {
                    jVar = new com.android.tools.r8.graph.proto.j(jVar.a, kVarA, jVar.b);
                }
            }
        }
        return jVar.a((com.android.tools.r8.graph.proto.j) this.q.getOrDefault(c0322w3, com.android.tools.r8.graph.proto.j.d));
    }

    @Override // com.android.tools.r8.internal.CQ
    public final EnumC2326pC a(C0322w2 c0322w2, C0322w2 c0322w3, EnumC2326pC enumC2326pC) {
        if (!this.h.containsKey(c0322w3.w0())) {
            return enumC2326pC;
        }
        if (t || c0322w2 != c0322w3) {
            return EnumC2326pC.f;
        }
        x1f.a();
        return null;
    }
}
