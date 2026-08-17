package com.android.tools.r8.graph;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.internal.AbstractC3175z9;
import com.android.tools.r8.internal.H9;
import com.android.tools.r8.internal.UK;
import defpackage.ngh;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L5 {
    public static final /* synthetic */ boolean c = true;
    public final C0333y a;
    public final K5 b;

    public L5(C0333y c0333y, K5 k5) {
        this.a = c0333y;
        this.b = k5;
    }

    public final void a(B5 b5) {
        AbstractC0223i0 abstractC0223i0U0 = b5.e().U0();
        if (abstractC0223i0U0.w0()) {
            if (!c && !this.a.a(b5.e())) {
                x1f.a();
                return;
            }
            for (AbstractC3175z9 abstractC3175z9 : abstractC0223i0U0.H().H0()) {
                if (abstractC3175z9.E()) {
                    this.b.a(abstractC3175z9.f().getField());
                } else if (abstractC3175z9.H()) {
                    this.b.a(abstractC3175z9.k().T());
                } else if (abstractC3175z9 instanceof H9) {
                    a(abstractC3175z9.l().c, b5);
                }
            }
            return;
        }
        if (abstractC0223i0U0 instanceof C0244l0) {
            this.b.a(C0244l0.a((H0) b5, this.a.a()));
            return;
        }
        if (!abstractC0223i0U0.D0()) {
            if ((abstractC0223i0U0 instanceof W5) || c) {
                return;
            }
            x01.a(abstractC0223i0U0.getClass().getTypeName());
            return;
        }
        for (UK uk : abstractC0223i0U0.r0().g) {
            if (uk instanceof C0245l1) {
                this.b.a((C0245l1) uk);
            } else if (uk instanceof D0) {
                a((D0) uk, b5);
            } else if (uk instanceof C0322w2) {
                this.b.a((C0322w2) uk);
            } else if (uk instanceof C0336y2) {
                a((C0336y2) uk, b5);
            }
        }
    }

    public final void a(D2 d2) {
        d2.h(new Consumer() { // from class: ml8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((B5) obj);
            }
        }, new ngh());
    }

    public final void a(ExecutorService executorService) {
        com.android.tools.r8.K.a(this.a, ((C0229j) this.a.g()).d(), new Consumer() { // from class: ol8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((D2) obj);
            }
        }, executorService);
    }

    public final void a(D0 d0, B5 b5) {
        a(d0.g, b5);
        for (O2 o2 : d0.h) {
            o2.getClass();
            if (o2 instanceof Q2) {
                this.b.a((C0245l1) o2.w0().c);
            } else if (o2 instanceof S2) {
                this.b.a((C0322w2) o2.A0().c);
            } else if (o2 instanceof T2) {
                a((C0336y2) o2.B0().c, b5);
            }
        }
    }

    public final void a(C0336y2 c0336y2, B5 b5) {
        if (c0336y2.e.a()) {
            this.b.a(c0336y2.o0());
        } else if (!c && !c0336y2.e.g()) {
            x1f.a();
        } else {
            this.b.a(c0336y2.p0());
        }
    }
}
