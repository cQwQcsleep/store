package com.android.tools.r8.graph;

import com.android.tools.r8.graph.AbstractC0208g;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.utils.structural.A;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.graph.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0208g<T extends AbstractC0208g<T>> implements com.android.tools.r8.utils.structural.x<T> {
    public static final AbstractC0551Hu d = AbstractC0551Hu.a("public", "private", "protected", "static", "final", "synthetic");
    public static final /* synthetic */ boolean e = true;
    public int b;
    public int c;

    public AbstractC0208g(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    public void A() {
        b(4096);
    }

    public void B() {
        c(2);
    }

    public void C() {
        c(4);
    }

    public void D() {
        c(1);
    }

    public final String a(boolean z) {
        AbstractC0551Hu abstractC0551HuC = c();
        AbstractC0551Hu abstractC0551HuD = d();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < abstractC0551HuC.size(); i++) {
            if (((BooleanSupplier) abstractC0551HuD.get(i)).getAsBoolean() && (!z || !((String) abstractC0551HuC.get(i)).equals("super"))) {
                if (sb.length() > 0) {
                    sb.append(' ');
                }
                sb.append((String) abstractC0551HuC.get(i));
            }
        }
        return sb.toString();
    }

    public final void b(int i) {
        this.b |= i;
        this.c = i | this.c;
    }

    public final void c(int i) {
        int i2 = this.b;
        int i3 = ~i;
        this.b = i2 & i3;
        this.c = i3 & this.c;
    }

    public AbstractC0551Hu d() {
        return AbstractC0551Hu.a(new BooleanSupplier() { // from class: vvg
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.m();
            }
        }, new BooleanSupplier() { // from class: wvg
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.i();
            }
        }, new BooleanSupplier() { // from class: xvg
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.l();
            }
        }, new BooleanSupplier() { // from class: yvg
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.n();
            }
        }, new BooleanSupplier() { // from class: zvg
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.f();
            }
        }, new BooleanSupplier() { // from class: awg
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.p();
            }
        });
    }

    public final int e() {
        if (m()) {
            return 3;
        }
        if (l()) {
            return 2;
        }
        return i() ? 0 : 1;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof AbstractC0208g) {
            AbstractC0208g abstractC0208g = (AbstractC0208g) obj;
            if (this.b == abstractC0208g.b && this.c == abstractC0208g.c) {
                return true;
            }
        }
        return false;
    }

    public boolean f() {
        return d(this.c, 16);
    }

    public boolean g() {
        return (m() || i() || l()) ? false : true;
    }

    public final boolean h() {
        return (m() || i()) ? false : true;
    }

    public final int hashCode() {
        return this.c | this.b;
    }

    public boolean i() {
        return d(this.c, 2);
    }

    public final boolean j() {
        return !d(this.b, 1) && d(this.c, 1);
    }

    public final boolean k() {
        return d(this.b, 2) && !d(this.c, 2) && j();
    }

    public boolean l() {
        return d(this.c, 4);
    }

    public boolean m() {
        return d(this.c, 1);
    }

    public boolean n() {
        return d(this.c, 8);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: uvg
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                AbstractC0208g.a(a);
            }
        };
    }

    public boolean p() {
        return d(this.c, 4096);
    }

    public final boolean q() {
        return e() == 1 || e() == 2;
    }

    public final AbstractC0208g r() {
        this.c |= 16;
        return R();
    }

    public T s() {
        this.c = (this.c & (-7)) | 1;
        return (T) R();
    }

    @Override // com.android.tools.r8.utils.structural.x
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public abstract AbstractC0208g R();

    public final String toString() {
        return a(false);
    }

    public void u() {
        b(16);
    }

    public void w() {
        if (e || !(m() || l())) {
            b(2);
        } else {
            x1f.a();
        }
    }

    public void x() {
        if (e || !(m() || i())) {
            b(4);
        } else {
            x1f.a();
        }
    }

    public void y() {
        if (e || !(i() || l())) {
            b(1);
        } else {
            x1f.a();
        }
    }

    public void z() {
        b(8);
    }

    public final void b() {
        this.c &= -4097;
    }

    public AbstractC0551Hu c() {
        return d;
    }

    public static boolean d(int i, int i2) {
        return (i & i2) != 0;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: bwg
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((AbstractC0208g) obj).b;
            }
        }).a(new ToIntFunction() { // from class: cwg
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((AbstractC0208g) obj).c;
            }
        });
    }

    public final void a(boolean z, Consumer consumer) {
        if (z) {
            consumer.accept(R());
        }
    }

    public final AbstractC0208g a() {
        this.c &= -17;
        return R();
    }
}
