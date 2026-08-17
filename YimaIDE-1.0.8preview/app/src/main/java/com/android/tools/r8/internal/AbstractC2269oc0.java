package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0330x3;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC2269oc0;
import com.android.tools.r8.shaking.C3403i;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oc0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2269oc0 extends AbstractC3122yc0 {
    public static final /* synthetic */ boolean c = true;
    public final C0245l1 b;

    public AbstractC2269oc0(C0245l1 c0245l1) {
        this.b = c0245l1;
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean K() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3122yc0
    public final boolean Q() {
        return true;
    }

    public final C0245l1 R() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC3122yc0
    public final AbstractC0890Uw[] a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, InterfaceC2714tl0 interfaceC2714tl0, InterfaceC2507rN interfaceC2507rN) {
        AbstractC2624sj0 abstractC2624sj0A = AbstractC2624sj0.a(this.b.getType(), C2427qS.h(), (C0333y<?>) c0333y);
        if (!c && !abstractC2624sj0A.a(interfaceC2507rN.a(), (C0333y<?>) c0333y) && c0333y.o() && !abstractC2624sj0A.a(c0333y.U())) {
            x1f.a();
            return null;
        }
        boolean z = C2529re0.k;
        C2444qe0 c2444qe0 = new C2444qe0();
        c2444qe0.d = this.b;
        C2444qe0 c2444qe1 = (C2444qe0) c2444qe0.a(interfaceC2714tl0, abstractC2624sj0A, interfaceC2507rN.k());
        c2444qe1.b = interfaceC2507rN.getPosition();
        return new AbstractC0890Uw[]{(C2529re0) c2444qe1.a(new C2529re0(c2444qe1.d, c2444qe1.a))};
    }

    @Override // com.android.tools.r8.internal.AbstractC3122yc0, com.android.tools.r8.internal.B1
    /* JADX INFO: renamed from: c */
    public final AbstractC3122yc0 b(C0333y c0333y, com.android.tools.r8.graph.I2 i2, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        C1 c1 = c0333y.t;
        C2974wn c2974wnR = c0333y.R();
        boolean zB = c2974wnR.b(this.b);
        C0245l1 c0245l1 = this.b;
        if (zB) {
            return c1.a(c2974wnR.a(c0245l1), AbstractC2624sj0.k());
        }
        C0245l1 c0245l1E = abstractC3148ys.e(abstractC3148ys2, c0245l1);
        RU ruA = y().a(c0333y, abstractC3148ys, abstractC3148ys2);
        if (c0245l1E == this.b && ruA == y()) {
            return this;
        }
        c1.getClass();
        return C1.a(c0245l1E, ruA);
    }

    @Override // com.android.tools.r8.internal.B1
    public final AbstractC2269oc0 r() {
        return this;
    }

    @Override // com.android.tools.r8.internal.B1
    public abstract RU y();

    @Override // com.android.tools.r8.internal.InterfaceC2385pw
    public final InterfaceC2385pw a(com.android.tools.r8.graph.proto.c cVar) {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3122yc0
    public final boolean a(final C0333y c0333y, final com.android.tools.r8.graph.B5 b5) {
        final AbstractC0330x3 abstractC0330x3C = ((C0229j) c0333y.g()).c(this.b);
        Objects.requireNonNull(abstractC0330x3C);
        return AbstractC0780Qq.a(new Consumer() { // from class: dyh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                abstractC0330x3C.a((Consumer<AbstractC0330x3>) obj);
            }
        }, new Predicate() { // from class: eyh
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AbstractC2269oc0.a(b5, c0333y, (AbstractC0330x3) obj);
            }
        });
    }

    public static /* synthetic */ boolean a(com.android.tools.r8.graph.B5 b5, C0333y c0333y, AbstractC0330x3 abstractC0330x3) {
        if (abstractC0330x3.x()) {
            return false;
        }
        return abstractC0330x3.a(b5, c0333y).d();
    }

    @Override // com.android.tools.r8.internal.AbstractC3122yc0
    public final boolean a(C0333y c0333y) {
        C0210g1 c0210g1Q = ((C3403i) c0333y.g()).c(this.b).q();
        if (c0210g1Q == null) {
            if (c) {
                return false;
            }
            x1f.a();
            return false;
        }
        if (!c0210g1Q.K0()) {
            return false;
        }
        com.android.tools.r8.graph.E0 e0D = c0333y.d(c0210g1Q.E0());
        if (e0D == null) {
            if (c) {
                return false;
            }
            x1f.a();
            return false;
        }
        if (!e0D.z1()) {
            return false;
        }
        ((C3403i) c0333y.g()).getClass();
        return (e0D.a0() && C2098md.a(e0D.X(), c0333y)) ? false : true;
    }
}
