package com.android.tools.r8.internal;

import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class RR extends AbstractC1831jV {
    public static final /* synthetic */ boolean c = true;
    public final Set b;

    public RR(Set<com.android.tools.r8.graph.I2> set) {
        this.b = set;
    }

    @Override // com.android.tools.r8.internal.AbstractC1831jV
    public final AbstractC1831jV a(com.android.tools.r8.graph.I5 i5, Ch0 ch0) {
        if (!i5.c()) {
            return this;
        }
        ch0.a("Prune NonEmptyOpenClosedInterfacesCollection");
        Set setB = C1755ib0.b(this.b.size());
        for (com.android.tools.r8.graph.I2 i2 : this.b) {
            if (!i5.e.contains(i2)) {
                setB.add(i2);
            }
        }
        RR rr = new RR(setB);
        ch0.b();
        return rr;
    }

    @Override // com.android.tools.r8.internal.AbstractC1831jV
    public final boolean a() {
        return this.b.isEmpty();
    }

    @Override // com.android.tools.r8.internal.AbstractC1831jV
    public final AbstractC1831jV a(final AbstractC3148ys abstractC3148ys, Ch0 ch0) {
        return (AbstractC1831jV) ch0.a("Rewrite NonEmptyOpenClosedInterfacesCollection", new InterfaceC2706th0() { // from class: r5c
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.a(abstractC3148ys);
            }
        });
    }

    public final AbstractC1831jV a(AbstractC3148ys abstractC3148ys) {
        Set setB = C1755ib0.b(this.b.size());
        for (com.android.tools.r8.graph.I2 i2 : this.b) {
            abstractC3148ys.getClass();
            setB.add(abstractC3148ys.c(AbstractC3148ys.g(), i2));
        }
        return new RR(setB);
    }

    @Override // com.android.tools.r8.internal.AbstractC1831jV
    public final boolean a(com.android.tools.r8.graph.E0 e0) {
        if (c || e0.isInterface()) {
            return !this.b.contains(e0.getType());
        }
        x1f.a();
        return false;
    }
}
