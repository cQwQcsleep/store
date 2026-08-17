package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0327x0;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.I2;
import defpackage.r2i;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sM, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2592sM implements InterfaceC0852Tk {
    public final boolean a;
    public final C3189zM b;
    public final C3020xM c;
    public int d = -1;

    public C2592sM(boolean z, C3189zM c3189zM, C3020xM c3020xM) {
        this.a = z;
        this.b = c3189zM;
        this.c = c3020xM;
    }

    public static C2506rM g() {
        EnumC3077y2 enumC3077y2 = EnumC3077y2.c;
        int i = AbstractC0551Hu.c;
        C3189zM c3189zM = new C3189zM(enumC3077y2, "unused", null, null, false, P40.e);
        boolean z = C3020xM.s;
        return new C2506rM(c3189zM, new C2934wM().a());
    }

    public final C2592sM a(String str, com.android.tools.r8.graph.B1 b1) {
        String strE = this.b.e();
        return new C2592sM(this.a, this.b.a(str), this.c.c(strE, b1, strE + C0929Wj.o(str)));
    }

    public boolean b(com.android.tools.r8.graph.F2 f2) {
        if (a(f2)) {
            return true;
        }
        if (!f2.u0()) {
            return false;
        }
        C0322w2 c0322w2Q0 = f2.q0();
        if (this.c.p().containsKey(c0322w2Q0) || this.c.l().containsKey(c0322w2Q0) || this.c.h().containsKey(c0322w2Q0)) {
            return true;
        }
        Iterator<C1777in> it = this.c.g().values().iterator();
        while (it.hasNext()) {
            if (it.next().b.containsKey(c0322w2Q0)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public final EnumC3077y2 c() {
        return this.b.d();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public final boolean d() {
        return this.a;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public final List e() {
        return this.b.a();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public final String f() {
        return this.b.e();
    }

    public Map<C0322w2, C0322w2[]> h() {
        return this.c.c();
    }

    public final Map i() {
        return this.c.d();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public final boolean isEmpty() {
        C3020xM c3020xM = this.c;
        return c3020xM.a.isEmpty() && c3020xM.b.isEmpty() && c3020xM.c.isEmpty() && !c3020xM.r() && c3020xM.k.isEmpty() && c3020xM.m.isEmpty();
    }

    public Map<com.android.tools.r8.graph.I2, C1850jh> j() {
        return this.c.e();
    }

    public final Map k() {
        return this.c.g();
    }

    public Set<com.android.tools.r8.graph.I2> l() {
        return this.c.k();
    }

    public Map<com.android.tools.r8.graph.I2, com.android.tools.r8.graph.I2> m() {
        return this.c.n();
    }

    public C3020xM n() {
        return this.c;
    }

    public final Map o() {
        return this.c.o();
    }

    public C3189zM p() {
        return this.b;
    }

    public Map<com.android.tools.r8.graph.I2, Tm0> q() {
        return this.c.q();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public final Set a() {
        return (Set) this.c.k().stream().map(new r2i()).collect(Collectors.toSet());
    }

    public final C1606gn a(C0322w2 c0322w2) {
        C3020xM c3020xM = this.c;
        if (c3020xM.k.containsKey(c0322w2.w0())) {
            return (C1606gn) ((C1777in) c3020xM.k.get(c0322w2.w0())).b.get(c0322w2);
        }
        return null;
    }

    public final boolean a(com.android.tools.r8.graph.I2 i2) {
        return this.c.a(i2);
    }

    public final boolean a(com.android.tools.r8.graph.F2 f2) {
        return m().containsKey(f2.z()) || l().contains(f2.z());
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public final C2592sM a(AbstractC0327x0 abstractC0327x0, Ch0 ch0) {
        AbstractC1225cK.a(abstractC0327x0, this.a, this.b.d());
        return this;
    }

    public static C2592sM a(Map<com.android.tools.r8.graph.I2, com.android.tools.r8.graph.I2> map) {
        boolean z = C3020xM.s;
        final C2934wM c2934wM = new C2934wM();
        map.forEach(new BiConsumer() { // from class: rai
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                c2934wM.b((I2) obj, (I2) obj2);
            }
        });
        EnumC3077y2 enumC3077y2 = EnumC3077y2.c;
        int i = AbstractC0551Hu.c;
        return new C2592sM(true, new C3189zM(enumC3077y2, "unused", null, null, false, P40.e), c2934wM.a());
    }

    public final boolean b(com.android.tools.r8.graph.I2 i2) {
        return this.c.b(i2);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public final String b() {
        return this.b.d;
    }
}
