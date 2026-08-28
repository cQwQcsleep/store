package j$.util.stream;

import java.util.concurrent.CountedCompleter;
import java.util.function.IntFunction;

/* loaded from: /workspace/unpacked/classes3.dex */
final class h4 extends AbstractC0115e {
    private final AbstractC0100b h;
    private final IntFunction i;
    private final boolean j;
    private long k;
    private long l;

    @Override // j$.util.stream.AbstractC0115e, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        M0 m0I;
        AbstractC0115e abstractC0115e = this.d;
        if (abstractC0115e != null) {
            if (this.j) {
                h4 h4Var = (h4) abstractC0115e;
                long j = h4Var.l;
                this.l = j;
                if (j == h4Var.k) {
                    this.l = j + ((h4) this.e).l;
                }
            }
            h4 h4Var2 = (h4) abstractC0115e;
            long j2 = h4Var2.k;
            h4 h4Var3 = (h4) this.e;
            this.k = j2 + h4Var3.k;
            if (h4Var2.k == 0) {
                m0I = (M0) h4Var3.c();
            } else if (h4Var3.k == 0) {
                m0I = (M0) h4Var2.c();
            } else {
                m0I = A0.I(this.h.I(), (M0) ((h4) this.d).c(), (M0) ((h4) this.e).c());
            }
            M0 m0I2 = m0I;
            if (d() && this.j) {
                m0I2 = m0I2.i(this.l, m0I2.count(), this.i);
            }
            f(m0I2);
        }
        super.onCompletion(countedCompleter);
    }

    h4(AbstractC0100b abstractC0100b, AbstractC0100b abstractC0100b2, j$.util.U u, IntFunction intFunction) {
        super(abstractC0100b2, u);
        this.h = abstractC0100b;
        this.i = intFunction;
        this.j = EnumC0129g3.ORDERED.n(abstractC0100b2.K());
    }

    h4(h4 h4Var, j$.util.U u) {
        super(h4Var, u);
        this.h = h4Var.h;
        this.i = h4Var.i;
        this.j = h4Var.j;
    }

    @Override // j$.util.stream.AbstractC0115e
    protected final AbstractC0115e e(j$.util.U u) {
        return new h4(this, u);
    }

    @Override // j$.util.stream.AbstractC0115e
    protected final Object a() {
        boolean zD = d();
        E0 e0N = this.a.N((!zD && this.j && EnumC0129g3.SIZED.s(this.h.c)) ? this.h.G(this.b) : -1L, this.i);
        g4 g4VarK = ((f4) this.h).k(e0N, this.j && !zD);
        this.a.V(this.b, g4VarK);
        M0 m0A = e0N.a();
        this.k = m0A.count();
        this.l = g4VarK.g();
        return m0A;
    }
}
