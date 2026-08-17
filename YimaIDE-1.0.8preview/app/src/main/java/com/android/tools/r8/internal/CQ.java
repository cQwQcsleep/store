package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0287r2;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CQ extends AbstractC1511fi {
    public static final C0698Nm j = new C0698Nm();
    public static final C0698Nm k = new C0698Nm();
    public static final C0698Nm l = new C0698Nm();
    public static final /* synthetic */ boolean m = true;
    public final InterfaceC1037a6 f;
    public final Function g;
    public final W5 h;
    public final W5 i;

    public CQ(C0333y c0333y, InterfaceC1037a6 interfaceC1037a6, final Map map, W5 w5, W5 w6) {
        Objects.requireNonNull(map);
        Function function = new Function() { // from class: d81
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (C0322w2) map.get((C0322w2) obj);
            }
        };
        super(c0333y);
        this.f = interfaceC1037a6;
        this.g = function;
        this.h = w5;
        this.i = w6;
        if (!m && w5.isEmpty() && map.isEmpty() && interfaceC1037a6.isEmpty() && !q()) {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public C2850vO a(C2850vO c2850vO, C0322w2 c0322w2, AbstractC3148ys abstractC3148ys) {
        boolean zB = c2850vO.b();
        Function function = this.g;
        if (zB) {
            AbstractC0287r2 abstractC0287r2 = (AbstractC0287r2) function.apply(c2850vO.b);
            if (abstractC0287r2 == null) {
                abstractC0287r2 = c2850vO.b;
            }
            C0322w2 c0322w3 = (C0322w2) abstractC0287r2;
            AbstractC0287r2 abstractC0287r3 = c2850vO.a;
            C0322w2 c0322w2A = abstractC0287r3 == c2850vO.b ? c0322w3 : c0322w3.a(d(((C0322w2) abstractC0287r3).w0()), this.c);
            com.android.tools.r8.graph.proto.j jVar = com.android.tools.r8.graph.proto.j.d;
            return new C2850vO(c0322w2A, c0322w3, a(c0322w3, (C0322w2) c2850vO.a, c2850vO.c), a(c2850vO.d, (C0322w2) c2850vO.b, c0322w3)).a(this);
        }
        C0322w2 c0322w4 = (C0322w2) function.apply((C0322w2) c2850vO.a);
        if (c0322w4 == null) {
            c0322w4 = (C0322w2) c2850vO.a;
        }
        com.android.tools.r8.graph.proto.j jVarA = a(c2850vO.d, (C0322w2) c2850vO.a, c0322w4);
        AbstractC0287r2 abstractC0287r4 = c2850vO.a;
        if (c0322w4 == abstractC0287r4 && jVarA == c2850vO.d) {
            return c2850vO.a(this);
        }
        com.android.tools.r8.graph.proto.j jVar2 = com.android.tools.r8.graph.proto.j.d;
        return new C2850vO(c0322w4, null, a(c0322w4, (C0322w2) abstractC0287r4, c2850vO.c), jVarA).a(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public C2035lp b(C2035lp c2035lp) {
        boolean zB = c2035lp.b();
        InterfaceC1037a6 interfaceC1037a6 = this.f;
        if (!zB) {
            AbstractC0287r2 abstractC0287r2 = c2035lp.a;
            return new C2035lp((C0245l1) ((AbstractC0287r2) interfaceC1037a6.getOrDefault(abstractC0287r2, abstractC0287r2)), null, c2035lp.a(new Function() { // from class: e81
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.d((I2) obj);
                }
            }), c2035lp.b(new Function() { // from class: e81
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.d((I2) obj);
                }
            }));
        }
        AbstractC0287r2 abstractC0287r3 = c2035lp.b;
        C0245l1 c0245l1 = (C0245l1) ((AbstractC0287r2) interfaceC1037a6.b(abstractC0287r3, abstractC0287r3));
        AbstractC0287r2 abstractC0287r4 = c2035lp.a;
        return new C2035lp(abstractC0287r4 == c2035lp.b ? c0245l1 : c0245l1.a(d(((C0245l1) abstractC0287r4).w0()), this.c), c0245l1, c2035lp.a(new Function() { // from class: e81
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.d((I2) obj);
            }
        }), c2035lp.b(new Function() { // from class: e81
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.d((I2) obj);
            }
        }));
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public final C0245l1 c(C0245l1 c0245l1) {
        return (C0245l1) this.f.c(c0245l1, c0245l1);
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public com.android.tools.r8.graph.I2 d(com.android.tools.r8.graph.I2 i2) {
        return (com.android.tools.r8.graph.I2) this.h.b(i2, i2);
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.AbstractC3148ys
    public com.android.tools.r8.graph.proto.j e(AbstractC3148ys abstractC3148ys, C0322w2 c0322w2) {
        if (this == abstractC3148ys) {
            return AbstractC3148ys.g().e(abstractC3148ys, c0322w2);
        }
        C0322w2 c0322w2F = f(c0322w2);
        return a(this.d.e(abstractC3148ys, c0322w2F), c0322w2F, c0322w2);
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public boolean f(AbstractC3148ys abstractC3148ys, C0322w2 c0322w2) {
        if (m || abstractC3148ys == this || this.d.f(abstractC3148ys, f(c0322w2))) {
            return true;
        }
        x1f.a();
        return false;
    }

    public Iterable g(com.android.tools.r8.graph.I2 i2) {
        return C2753uC.b(f(i2));
    }

    public boolean q() {
        return this instanceof TA;
    }

    public final String toString() {
        return getClass().getTypeName();
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public C0322w2 e(C0322w2 c0322w2) {
        return (C0322w2) this.i.b(c0322w2, c0322w2);
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public C0322w2 f(C0322w2 c0322w2) {
        return (C0322w2) this.i.c(c0322w2, c0322w2);
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public com.android.tools.r8.graph.I2 f(com.android.tools.r8.graph.I2 i2) {
        return (com.android.tools.r8.graph.I2) this.h.c(i2, i2);
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.AbstractC3148ys
    public final Iterable b(com.android.tools.r8.graph.I2 i2) {
        Iterable iterableG = g(i2);
        final AbstractC3148ys abstractC3148ys = this.d;
        Objects.requireNonNull(abstractC3148ys);
        return C2753uC.b(iterableG, new Function() { // from class: f81
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return abstractC3148ys.b((I2) obj);
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public C0245l1 b(C0245l1 c0245l1) {
        return (C0245l1) this.f.getOrDefault(c0245l1, c0245l1);
    }

    public EnumC2326pC a(C0322w2 c0322w2, C0322w2 c0322w3, EnumC2326pC enumC2326pC) {
        return enumC2326pC;
    }

    public com.android.tools.r8.graph.proto.j a(com.android.tools.r8.graph.proto.j jVar, C0322w2 c0322w2, C0322w2 c0322w3) {
        return jVar;
    }
}
