package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C2035lp;
import com.android.tools.r8.internal.C2850vO;
import com.android.tools.r8.internal.XR;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class XR extends AbstractC3148ys {
    public static final /* synthetic */ boolean e = true;
    public final C0333y b;
    public final com.android.tools.r8.graph.B1 c;
    public AbstractC3148ys d;

    public XR(C0333y c0333y, AbstractC3148ys abstractC3148ys) {
        this.b = c0333y;
        this.c = c0333y.a();
        this.d = abstractC3148ys;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final C2850vO a(C0322w2 c0322w2, C0322w2 c0322w3, EnumC2326pC enumC2326pC, final AbstractC3148ys abstractC3148ys) {
        if (!c0322w2.w0().I0()) {
            if (e || c0322w2.w0().M0()) {
                return a(c0322w2, c0322w3, enumC2326pC, abstractC3148ys, new InterfaceC3064xs() { // from class: hyf
                    @Override // com.android.tools.r8.internal.InterfaceC3064xs
                    public final C2850vO a(C2850vO c2850vO) {
                        return XR.a(c2850vO);
                    }
                });
            }
            x1f.a();
            return null;
        }
        if (e || De0.a(c0322w2.a(this.c)).allMatch(new Predicate() { // from class: gyf
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.f(abstractC3148ys, (I2) obj);
            }
        })) {
            return new C2850vO(c0322w2.a(c(abstractC3148ys, c0322w2.w0()), this.c), null, enumC2326pC, com.android.tools.r8.graph.proto.j.d).a(this);
        }
        x1f.a();
        return null;
    }

    public abstract C2850vO a(C2850vO c2850vO, C0322w2 c0322w2, AbstractC3148ys abstractC3148ys);

    public abstract C0245l1 b(C0245l1 c0245l1);

    public abstract C2035lp b(C2035lp c2035lp);

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public boolean b(AbstractC3148ys abstractC3148ys) {
        return this == abstractC3148ys;
    }

    public abstract C0245l1 c(C0245l1 c0245l1);

    public abstract com.android.tools.r8.graph.I2 d(com.android.tools.r8.graph.I2 i2);

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final XR d() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    /* JADX INFO: renamed from: e */
    public final com.android.tools.r8.graph.I2 c(AbstractC3148ys abstractC3148ys, com.android.tools.r8.graph.I2 i2) {
        if (i2.M0()) {
            return d(abstractC3148ys, i2);
        }
        if (i2.I0()) {
            com.android.tools.r8.graph.I2 i2A = i2.a(this.c);
            com.android.tools.r8.graph.I2 i2C = c(abstractC3148ys, i2A);
            i2A.getClass();
            if (!com.android.tools.r8.graph.I2.a(i2A, i2C)) {
                return i2.a(this.c, i2C);
            }
        } else if (!e && !i2.R0() && !i2.T0() && !i2.W0()) {
            x1f.a();
            return null;
        }
        return i2;
    }

    public abstract C0322w2 e(C0322w2 c0322w2);

    public abstract com.android.tools.r8.graph.I2 f(com.android.tools.r8.graph.I2 i2);

    public abstract C0322w2 f(C0322w2 c0322w2);

    public final boolean f(AbstractC3148ys abstractC3148ys, com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.I2 i2D = d(abstractC3148ys, i2);
        i2.getClass();
        return com.android.tools.r8.graph.I2.a(i2, i2D);
    }

    public C0322w2 g(C0322w2 c0322w2) {
        return f(c0322w2);
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final boolean l() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final boolean n() {
        return true;
    }

    public final AbstractC3148ys p() {
        return this.d;
    }

    public final com.android.tools.r8.graph.I2 e(com.android.tools.r8.graph.I2 i2) {
        if (i2.I0()) {
            com.android.tools.r8.graph.I2 i2A = i2.a(this.c);
            com.android.tools.r8.graph.I2 i2D = d(i2A);
            if (i2D.b(i2A)) {
                return i2.a(this.c, i2D);
            }
        } else if (i2.M0()) {
            return d(i2);
        }
        return i2;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public String a(String str) {
        return this.d.a(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.tools.r8.internal.ys] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.function.Predicate, java.util.function.Predicate<com.android.tools.r8.internal.XR>] */
    public final <T extends XR> T a(Predicate<XR> predicate) {
        ?? r1 = this;
        while (r1.n()) {
            T t = (T) r1.d();
            if (predicate.test(t)) {
                return t;
            }
            r1 = t.d;
        }
        return null;
    }

    public static /* synthetic */ C2850vO a(C2850vO c2850vO) {
        return c2850vO;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public C2035lp a(C0245l1 c0245l1, AbstractC3148ys abstractC3148ys, final InterfaceC2979ws interfaceC2979ws) {
        if (this == abstractC3148ys) {
            return AbstractC3148ys.g().a(c0245l1, abstractC3148ys, interfaceC2979ws);
        }
        return this.d.a(c0245l1, abstractC3148ys, new InterfaceC2979ws() { // from class: jyf
            @Override // com.android.tools.r8.internal.InterfaceC2979ws
            public final C2035lp a(C2035lp c2035lp) {
                return this.a.a(interfaceC2979ws, c2035lp);
            }
        });
    }

    public final /* synthetic */ C2035lp a(InterfaceC2979ws interfaceC2979ws, C2035lp c2035lp) {
        return interfaceC2979ws.a(b(c2035lp));
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public C2850vO a(C0322w2 c0322w2, final C0322w2 c0322w3, EnumC2326pC enumC2326pC, final AbstractC3148ys abstractC3148ys, final InterfaceC3064xs interfaceC3064xs) {
        if (this == abstractC3148ys) {
            AbstractC3148ys abstractC3148ysG = AbstractC3148ys.g();
            return abstractC3148ysG.a(c0322w2, c0322w3, enumC2326pC, abstractC3148ysG, interfaceC3064xs);
        }
        return this.d.a(c0322w2, f(c0322w3), enumC2326pC, abstractC3148ys, new InterfaceC3064xs() { // from class: iyf
            @Override // com.android.tools.r8.internal.InterfaceC3064xs
            public final C2850vO a(C2850vO c2850vO) {
                return this.a.a(interfaceC3064xs, c0322w3, abstractC3148ys, c2850vO);
            }
        });
    }

    public final /* synthetic */ C2850vO a(InterfaceC3064xs interfaceC3064xs, C0322w2 c0322w2, AbstractC3148ys abstractC3148ys, C2850vO c2850vO) {
        return interfaceC3064xs.a(a(c2850vO, c0322w2, abstractC3148ys));
    }
}
