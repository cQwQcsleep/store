package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0223i0;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC3143yn;
import com.android.tools.r8.internal.KL;
import com.android.tools.r8.synthesis.N;
import com.android.tools.r8.synthesis.S;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class KL extends AbstractC1011Zn {
    public static final /* synthetic */ boolean d = true;
    public final com.android.tools.r8.graph.D2 b;
    public final C2888vn c;

    public KL(com.android.tools.r8.graph.D2 d2, C2888vn c2888vn, com.android.tools.r8.graph.D2 d3) {
        super(d3);
        this.b = d2;
        this.c = c2888vn;
    }

    public final com.android.tools.r8.graph.H2 a(C0245l1 c0245l1, com.android.tools.r8.graph.B1 b1) {
        String string = c0245l1.x0().toString();
        if (c0245l1.w0() == b().getType()) {
            return b1.c("get" + Wf0.j(string.substring(0, 1)) + string.substring(1));
        }
        if (!d) {
            com.android.tools.r8.graph.K1 k1 = b1.B4;
            if (c0245l1 != k1.a && c0245l1 != k1.b) {
                x1f.a();
                return null;
            }
        }
        return c0245l1.x0();
    }

    public final com.android.tools.r8.graph.B5 b(final C0333y c0333y) {
        final com.android.tools.r8.graph.B1 b1A = c0333y.a();
        final C3039xc0 c3039xc0A = c0333y.t.a(b1A.c("null"));
        return a(c0333y, b1A.c("stringValueOf"), b1A.a(b1A.Y1, b1A.B1), new com.android.tools.r8.synthesis.M() { // from class: u58
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return this.a.a(c0333y, b1A, c3039xc0A, c0322w2);
            }
        });
    }

    public final com.android.tools.r8.graph.B5 c(final C0333y c0333y) {
        final com.android.tools.r8.graph.B1 b1A = c0333y.a();
        return a(c0333y, b1A.c("valueOf"), b1A.a(b1A.B1, b1A.Y1), new com.android.tools.r8.synthesis.M() { // from class: z58
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return this.a.a(c0333y, b1A, c0322w2);
            }
        });
    }

    public final com.android.tools.r8.graph.I2 c() {
        return this.b.getType();
    }

    public final /* synthetic */ void a(C0333y c0333y, C0245l1 c0245l1, AbstractC3143yn abstractC3143yn) {
        if (abstractC3143yn.d()) {
            a(c0333y, c0245l1);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1011Zn
    public final void a(final C0333y c0333y) {
        this.c.a.forEach(new BiConsumer() { // from class: y58
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(c0333y, (C0245l1) obj, (AbstractC3143yn) obj2);
            }
        });
        if (this.c.a.containsKey(c0333y.a().B4.a)) {
            b(c0333y);
            c(c0333y);
        }
    }

    public static void a(C0333y c0333y, com.android.tools.r8.synthesis.M m, com.android.tools.r8.synthesis.N n) {
        n.h = com.android.tools.r8.graph.F4.b(4105, false);
        com.android.tools.r8.androidapi.f fVar = c0333y.U;
        n.l = fVar;
        n.m = fVar;
        n.g = m;
        n.f = C1159bb.g;
    }

    public final com.android.tools.r8.graph.B5 a(final C0333y c0333y, final C0245l1 c0245l1) {
        com.android.tools.r8.graph.B1 b1A = c0333y.a();
        return a(c0333y, a(c0245l1, b1A), b1A.a(c0245l1.getType(), b1A.B1), new com.android.tools.r8.synthesis.M() { // from class: x58
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return this.a.a(c0333y, c0245l1, c0322w2);
            }
        });
    }

    public final AbstractC0223i0 a(C0333y c0333y, C0245l1 c0245l1, C0322w2 c0322w2) {
        return new C0751Pn(c0333y, this.b.getType(), this.c, c0245l1, null).a();
    }

    public final AbstractC0223i0 a(C0333y c0333y, com.android.tools.r8.graph.B1 b1, B1 b2, C0322w2 c0322w2) {
        return new C0751Pn(c0333y, this.b.getType(), this.c, b1.B4.a, b2).a();
    }

    public final AbstractC0223i0 a(C0333y c0333y, com.android.tools.r8.graph.B1 b1, C0322w2 c0322w2) {
        com.android.tools.r8.graph.I2 type = this.b.getType();
        com.android.tools.r8.graph.I2 type2 = this.a.getType();
        C2888vn c2888vn = this.c;
        C0245l1 c0245l1 = b1.B4.a;
        if (C2888vn.f || c2888vn.a.containsKey(c0245l1)) {
            return new C0803Rn(c0333y, type, type2, ((AbstractC3143yn) c2888vn.a.get(c0245l1)).c()).a();
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC1011Zn
    public final com.android.tools.r8.graph.D2 a() {
        return this.b;
    }

    public final com.android.tools.r8.graph.B5 a(final C0333y c0333y, com.android.tools.r8.graph.H2 h2, com.android.tools.r8.graph.E2 e2, final com.android.tools.r8.synthesis.M m) {
        com.android.tools.r8.synthesis.J jG = c0333y.a.g();
        com.android.tools.r8.synthesis.I i = new com.android.tools.r8.synthesis.I() { // from class: v58
            @Override // com.android.tools.r8.synthesis.I
            public final S.b a(S s) {
                return s.h;
            }
        };
        com.android.tools.r8.graph.D2 d2 = this.a;
        Consumer consumerB = C0822Sg.b();
        Consumer consumer = new Consumer() { // from class: w58
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                KL.a(c0333y, m, (N) obj);
            }
        };
        jG.getClass();
        return jG.a(h2, e2, i, d2, c0333y, consumerB, consumer, C0822Sg.b());
    }
}
