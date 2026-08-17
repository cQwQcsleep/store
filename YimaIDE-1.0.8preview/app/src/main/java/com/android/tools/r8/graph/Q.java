package com.android.tools.r8.graph;

import com.android.tools.r8.dex.C0156t;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C1159bb;
import com.sun.jna.platform.linux.Fcntl;
import java.util.function.BooleanSupplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Q extends AbstractC0208g<Q> {
    public static final /* synthetic */ boolean f = true;

    public Q(int i) {
        super(i, i);
    }

    public static Q F() {
        return new Q(4113);
    }

    public static Q e(int i) {
        return new Q(i & 95807);
    }

    public static Q f(int i) {
        return new Q(i & 30239);
    }

    public static Q g(int i) {
        if (f || (i & 30239) == i) {
            return new Q(i & 30239);
        }
        x1f.a();
        return null;
    }

    public int H() {
        boolean z = f;
        if (!z && L() && !I()) {
            x1f.a();
            return 0;
        }
        if (!z && L() && N()) {
            x1f.a();
            return 0;
        }
        if (!z && L() && f()) {
            x1f.a();
            return 0;
        }
        if (z || !L() || !K()) {
            return this.c;
        }
        x1f.a();
        return 0;
    }

    public boolean I() {
        return AbstractC0208g.d(this.c, Fcntl.S_ISGID);
    }

    public boolean J() {
        return AbstractC0208g.d(this.c, 8192);
    }

    public boolean K() {
        return AbstractC0208g.d(this.c, 16384);
    }

    public boolean L() {
        return AbstractC0208g.d(this.c, 512);
    }

    public final boolean M() {
        return AbstractC0208g.d(this.c, 65536);
    }

    public boolean N() {
        return AbstractC0208g.d(this.c, 32);
    }

    public void O() {
        b(Fcntl.S_ISGID);
    }

    public void P() {
        b(8192);
    }

    public void Q() {
        c(Fcntl.S_ISGID);
    }

    @Override // com.android.tools.r8.graph.AbstractC0208g, com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public void S() {
        c(16384);
    }

    public void T() {
        c(512);
    }

    public void U() {
        c(32);
    }

    public final boolean a(C1159bb c1159bb, boolean z) {
        if (!L()) {
            return (J() || (f() && I())) ? false : true;
        }
        if (c1159bb.a(C1159bb.j) && N()) {
            return false;
        }
        return ((c1159bb.d(C0156t.b) && !I() && !z) || f() || K()) ? false : true;
    }

    @Override // com.android.tools.r8.graph.AbstractC0208g
    public final AbstractC0551Hu c() {
        return new C0473Eu(4).b((Iterable) AbstractC0208g.d).a("interface").a("abstract").a("annotation").a("enum").a("super").a("record").a();
    }

    @Override // com.android.tools.r8.graph.AbstractC0208g
    public final AbstractC0551Hu d() {
        return new C0473Eu(4).b((Iterable) super.d()).a(new BooleanSupplier() { // from class: svb
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.L();
            }
        }).a(new BooleanSupplier() { // from class: vvb
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.I();
            }
        }).a(new BooleanSupplier() { // from class: xvb
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.J();
            }
        }).a(new BooleanSupplier() { // from class: zvb
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.K();
            }
        }).a(new BooleanSupplier() { // from class: bwb
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.N();
            }
        }).a(new BooleanSupplier() { // from class: dwb
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.M();
            }
        }).a();
    }

    @Override // com.android.tools.r8.graph.AbstractC0208g
    /* JADX INFO: renamed from: t */
    public final AbstractC0208g R() {
        return this;
    }
}
