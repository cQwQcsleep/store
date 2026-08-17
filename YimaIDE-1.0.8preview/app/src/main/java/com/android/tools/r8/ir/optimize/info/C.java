package com.android.tools.r8.ir.optimize.info;

import com.android.tools.r8.graph.A5;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.C0346z5;
import com.android.tools.r8.graph.F0;
import com.android.tools.r8.graph.H0;
import com.android.tools.r8.internal.AbstractC0439Dm;
import com.android.tools.r8.internal.AbstractC0449Dw;
import com.android.tools.r8.internal.AbstractC0570In;
import com.android.tools.r8.internal.B1;
import com.android.tools.r8.internal.B7;
import com.android.tools.r8.internal.C1518fl0;
import com.android.tools.r8.internal.Gb0;
import com.android.tools.r8.internal.InterfaceC2182nc;
import com.android.tools.r8.ir.optimize.N;
import com.android.tools.r8.ir.optimize.info.w;
import java.util.BitSet;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C extends y {
    public static final C b = new C();
    public static final /* synthetic */ boolean c = true;

    public static void j(B5 b5) {
        Consumer consumer = new Consumer() { // from class: g51
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).R();
            }
        };
        if (b5.D().d()) {
            consumer.accept(b5.D().a());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.internal.InterfaceC3061xp
    public final void a(C0210g1 c0210g1, C0333y c0333y, B1 b1) {
        v vVar;
        if (!c) {
            A5 a5B = c0210g1.b(c0333y);
            if (!y.a) {
                c0333y.getClass();
                ((F0) a5B).f0();
                if (!c0333y.a(((C0346z5) a5B).O()).a(c0333y, a5B)) {
                    x1f.a();
                    return;
                }
            }
        }
        synchronized (c0210g1) {
            vVar = (v) c0210g1.l.b();
            c0210g1.l = vVar;
        }
        vVar.a(b1, c0210g1);
    }

    @Override // com.android.tools.r8.internal.AO
    public final void b(B5 b5, BitSet bitSet) {
        w wVarY0 = b5.e().Y0();
        wVarY0.getClass();
        if (bitSet.isEmpty()) {
            wVarY0.s = null;
        } else {
            wVarY0.s = bitSet;
        }
    }

    @Override // com.android.tools.r8.internal.AO
    public final void c(B5 b5) {
        b5.e().Y0().u |= 32;
    }

    public final void d(B5 b5) {
        b(new Consumer() { // from class: o51
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).I();
            }
        }, b5);
    }

    @Override // com.android.tools.r8.internal.AO
    public final void e(C0231j1 c0231j1) {
        c0231j1.Y0().u |= 2;
    }

    public final void f(C0231j1 c0231j1) {
        c0231j1.Y0().u |= 1;
    }

    public final void g(C0231j1 c0231j1) {
        Consumer consumer = new Consumer() { // from class: q41
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).J();
            }
        };
        c0231j1.O0();
        if (c0231j1.m.d()) {
            c0231j1.O0();
            consumer.accept(c0231j1.m.a());
        }
    }

    public final void h(B5 b5) {
        b(new Consumer() { // from class: v41
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).P();
            }
        }, b5);
    }

    public final void i(B5 b5) {
        b(new Consumer() { // from class: m41
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).Q();
            }
        }, b5);
    }

    public final void k(B5 b5) {
        b(new Consumer() { // from class: k51
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).S();
            }
        }, b5);
    }

    public final void l(B5 b5) {
        b(new Consumer() { // from class: s51
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).T();
            }
        }, b5);
    }

    public final void m(B5 b5) {
        b(new Consumer() { // from class: c51
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).U();
            }
        }, b5);
    }

    public final void n(B5 b5) {
        b(new Consumer() { // from class: e61
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).V();
            }
        }, b5);
    }

    public final void o(B5 b5) {
        b(new Consumer() { // from class: h41
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).W();
            }
        }, b5);
    }

    public final void p(B5 b5) {
        b(new Consumer() { // from class: a51
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).X();
            }
        }, b5);
    }

    public final void q(B5 b5) {
        b(new Consumer() { // from class: b51
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).Y();
            }
        }, b5);
    }

    public final void r(B5 b5) {
        b(new Consumer() { // from class: x41
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).Z();
            }
        }, b5);
    }

    public final void s(B5 b5) {
        b(new Consumer() { // from class: r41
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).a0();
            }
        }, b5);
    }

    public final void t(B5 b5) {
        b(new Consumer() { // from class: z41
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).b0();
            }
        }, b5);
    }

    @Override // com.android.tools.r8.internal.AO
    public final void d(C0231j1 c0231j1) {
    }

    public final void e(B5 b5) {
        b(new Consumer() { // from class: a61
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).K();
            }
        }, b5);
    }

    public final void f(B5 b5) {
        b(new Consumer() { // from class: e51
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).L();
            }
        }, b5);
    }

    @Override // com.android.tools.r8.internal.AO
    public final void c(C0231j1 c0231j1) {
        c0231j1.Y0().u |= 4;
    }

    @Override // com.android.tools.r8.internal.AO
    public final void b(C0231j1 c0231j1, BitSet bitSet) {
        c0231j1.Y0().o = bitSet;
    }

    @Override // com.android.tools.r8.internal.AO
    public final void b(B5 b5, Gb0 gb0) {
        b5.e().Y0().p = gb0;
    }

    public final void g(B5 b5) {
        b(new Consumer() { // from class: d51
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).M();
            }
        }, b5);
    }

    @Override // com.android.tools.r8.internal.AO
    public final void b(C0231j1 c0231j1) {
        c0231j1.Y0().u &= -9;
    }

    @Override // com.android.tools.r8.internal.AO
    public void b(B5 b5) {
        b(new Consumer() { // from class: i61
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).O();
            }
        }, b5);
    }

    public static void b(Consumer consumer, B5 b5) {
        if (b5.D().d()) {
            consumer.accept(b5.D().a());
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC3061xp
    public final void a(C0210g1 c0210g1, int i) {
    }

    @Override // com.android.tools.r8.internal.AO
    public final void a(Set set, C0231j1 c0231j1) {
    }

    public static void a(C0210g1 c0210g1) {
        v vVar;
        synchronized (c0210g1) {
            vVar = (v) c0210g1.l.b();
            c0210g1.l = vVar;
        }
        vVar.b |= 1;
    }

    @Override // com.android.tools.r8.internal.InterfaceC3061xp
    public final void a(C0210g1 c0210g1, AbstractC0439Dm abstractC0439Dm) {
        v vVar;
        synchronized (c0210g1) {
            vVar = (v) c0210g1.l.b();
            c0210g1.l = vVar;
        }
        vVar.d = abstractC0439Dm;
    }

    @Override // com.android.tools.r8.internal.AO
    public final void a(B5 b5, InterfaceC2182nc interfaceC2182nc) {
    }

    @Override // com.android.tools.r8.internal.AO
    public final void a(C0231j1 c0231j1, int i) {
        c0231j1.Y0().b(i);
    }

    @Override // com.android.tools.r8.internal.AO
    public final void a(C0231j1 c0231j1, C0333y c0333y, B1 b1) {
        c0231j1.Y0().a(b1, c0231j1);
    }

    @Override // com.android.tools.r8.internal.AO
    public final void a(C0231j1 c0231j1, C1518fl0 c1518fl0) {
        if (c) {
            return;
        }
        x1f.a();
    }

    @Override // com.android.tools.r8.internal.AO
    public final void a(C0333y c0333y, C0231j1 c0231j1, AbstractC0439Dm abstractC0439Dm) {
        c0231j1.Y0().a(c0333y, c0231j1, abstractC0439Dm);
    }

    @Override // com.android.tools.r8.internal.AO
    public final void a(C0231j1 c0231j1, N n) {
        c0231j1.a(n);
    }

    @Override // com.android.tools.r8.internal.AO
    public final void a(B5 b5, B7 b7) {
        b5.e().Y0().l = b7;
    }

    public static void a(H0 h0) {
        h0.e().Y0().h = true;
    }

    @Override // com.android.tools.r8.internal.AO
    public final void a(B5 b5, AbstractC0570In abstractC0570In) {
        b5.e().Y0().a(abstractC0570In);
    }

    @Override // com.android.tools.r8.internal.AO
    public final void a(C0231j1 c0231j1, AbstractC0449Dw abstractC0449Dw) {
        c0231j1.Y0().m = abstractC0449Dw;
    }

    @Override // com.android.tools.r8.internal.AO
    public final void a(C0231j1 c0231j1) {
        c0231j1.Y0().u |= 128;
    }

    @Override // com.android.tools.r8.internal.AO
    public final void a(C0231j1 c0231j1, BitSet bitSet) {
        c0231j1.Y0().n = bitSet;
    }

    @Override // com.android.tools.r8.internal.AO
    public final void a(B5 b5, Gb0 gb0) {
        b5.e().Y0().q = gb0;
    }

    @Override // com.android.tools.r8.internal.AO
    public final void a(B5 b5, BitSet bitSet) {
        b5.e().Y0().a(bitSet);
    }

    public static void a(Consumer consumer, B5 b5) {
        if (b5.D().x()) {
            w wVarY0 = b5.e().Y0();
            BitSet bitSet = (BitSet) wVarY0.t.clone();
            consumer.accept(bitSet);
            if (bitSet == null || bitSet.isEmpty()) {
                bitSet = null;
            }
            wVarY0.t = bitSet;
        }
    }

    @Override // com.android.tools.r8.internal.AO
    public final void a(B5 b5) {
        b(new Consumer() { // from class: w51
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((w) obj).N();
            }
        }, b5);
    }
}
