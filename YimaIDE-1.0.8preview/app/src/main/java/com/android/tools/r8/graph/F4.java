package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0473Eu;
import com.sun.jna.platform.linux.Fcntl;
import java.util.function.BooleanSupplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class F4 extends AbstractC0208g<F4> {
    public static final /* synthetic */ boolean f = true;

    public F4(int i) {
        super(i, i);
    }

    public static F4 H() {
        return b(4105, false);
    }

    public static F4 a(int i, boolean z) {
        return new F4((i & 7679) | (z ? 65536 : 0));
    }

    public static F4 b(int i, boolean z) {
        if (f || (i & 7679) == i) {
            return a(i, z);
        }
        x1f.a();
        return null;
    }

    public static F4 e(int i) {
        F4 f4 = new F4(i & 204287);
        if (AbstractC0208g.d(f4.c, 131072)) {
            f4.b(32);
            f4.c(131072);
        }
        return f4;
    }

    public final boolean F() {
        return n() || i() || L();
    }

    public int I() {
        return this.c & (-65537);
    }

    public boolean J() {
        return AbstractC0208g.d(this.c, Fcntl.S_ISGID);
    }

    public boolean K() {
        return AbstractC0208g.d(this.c, 64);
    }

    public final boolean L() {
        return AbstractC0208g.d(this.c, 65536);
    }

    public boolean M() {
        return AbstractC0208g.d(this.c, Fcntl.S_IRUSR);
    }

    public boolean N() {
        return AbstractC0208g.d(this.c, Fcntl.S_ISUID);
    }

    public boolean O() {
        return AbstractC0208g.d(this.c, 32);
    }

    public final boolean P() {
        return AbstractC0208g.d(this.c, 128);
    }

    public void Q() {
        b(64);
    }

    @Override // com.android.tools.r8.graph.AbstractC0208g, com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public void S() {
        b(Fcntl.S_IRUSR);
    }

    public final void T() {
        c(Fcntl.S_ISGID);
    }

    @Override // com.android.tools.r8.graph.AbstractC0208g
    public final AbstractC0551Hu c() {
        return new C0473Eu(4).b((Iterable) AbstractC0208g.d).a("synchronized").a("bridge").a("varargs").a("native").a("abstract").a("strictfp").a();
    }

    @Override // com.android.tools.r8.graph.AbstractC0208g
    public final AbstractC0551Hu d() {
        return new C0473Eu(4).b((Iterable) super.d()).a(new BooleanSupplier() { // from class: zk4
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.O();
            }
        }).a(new BooleanSupplier() { // from class: al4
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.K();
            }
        }).a(new BooleanSupplier() { // from class: bl4
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.P();
            }
        }).a(new BooleanSupplier() { // from class: cl4
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.M();
            }
        }).a(new BooleanSupplier() { // from class: dl4
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.J();
            }
        }).a(new BooleanSupplier() { // from class: el4
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.N();
            }
        }).a();
    }

    @Override // com.android.tools.r8.graph.AbstractC0208g
    /* JADX INFO: renamed from: t */
    public final AbstractC0208g R() {
        return this;
    }

    public F4(int i, int i2) {
        super(i, i2);
    }
}
