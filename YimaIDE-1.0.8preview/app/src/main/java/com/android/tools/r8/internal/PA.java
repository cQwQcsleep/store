package com.android.tools.r8.internal;

import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.graph.C0191d3;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D2;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class PA implements InterfaceC1836ja {
    public static final /* synthetic */ boolean e = true;
    public final C0333y a;
    public final int b = 2;
    public final VA c;
    public final C1077ad d;

    public PA(C0333y c0333y, Predicate predicate, VA va, int i) {
        this.a = c0333y;
        if (!e && va == null) {
            x1f.a();
            throw null;
        }
        this.c = va;
        this.d = new C1077ad(c0333y, predicate, i);
    }

    public final void a(Collection collection, final RA ra, ExecutorService executorService) throws ExecutionException {
        com.android.tools.r8.graph.Y3 y3S0;
        C0322w2 c0322w2D;
        C1086ah0.a(C2847vL.a(collection, new Predicate() { // from class: tua
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.a((D2) obj);
            }
        }), new Consumer() { // from class: uua
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(ra, (D2) obj);
            }
        }, this.a.M().N(), executorService);
        this.d.a(ra, executorService);
        VA va = this.c;
        TA taA = va.a();
        if (taA != null) {
            for (com.android.tools.r8.graph.D2 d2 : va.a.g().d()) {
                if (d2.R0() != null && d2.R0().b() != null && (y3S0 = d2.S0()) != null && y3S0.d() == null) {
                    C0191d3 c0191d3R0 = d2.R0();
                    C0322w2 c0322w2B = c0191d3R0.b();
                    if (c0322w2B == null) {
                        c0322w2D = null;
                    } else {
                        c0322w2D = (C0322w2) taA.n.c(c0322w2B);
                        if (c0322w2D == null) {
                            c0322w2D = taA.d(AbstractC3148ys.g(), c0322w2B);
                        }
                    }
                    if (c0322w2D != c0322w2B) {
                        c0191d3R0 = new C0191d3(c0322w2D);
                    }
                    d2.m = c0191d3R0;
                }
            }
        }
    }

    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        int i = this.b;
        if (this.a.b(d2)) {
            return false;
        }
        return d2.u != ProgramResource.Kind.DEX || i == 1;
    }

    public final /* synthetic */ void a(RA ra, com.android.tools.r8.graph.D2 d2) {
        this.d.a(ra, d2);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1836ja
    public final void a(Collection collection, AbstractC2350pa abstractC2350pa, ExecutorService executorService) throws ExecutionException {
        a(collection, (RA) abstractC2350pa, executorService);
    }
}
