package j$.util.stream;

import java.util.concurrent.CountedCompleter;
import java.util.function.IntFunction;

/* loaded from: /workspace/unpacked/classes3.dex */
final class B2 extends AbstractC0105c {
    private final AbstractC0100b j;
    private final IntFunction k;
    private final long l;
    private final long m;
    private long n;
    private volatile boolean o;

    @Override // j$.util.stream.AbstractC0105c
    protected final void h() {
        this.i = true;
        if (this.o) {
            f(A0.L(this.j.I()));
        }
    }

    @Override // j$.util.stream.AbstractC0115e, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        B2 b2;
        M0 m0L;
        AbstractC0115e abstractC0115e = this.d;
        if (!(abstractC0115e == null)) {
            this.n = ((B2) abstractC0115e).n + ((B2) this.e).n;
            if (this.i) {
                this.n = 0L;
                m0L = A0.L(this.j.I());
            } else {
                m0L = this.n == 0 ? A0.L(this.j.I()) : ((B2) this.d).n == 0 ? (M0) ((B2) this.e).c() : A0.I(this.j.I(), (M0) ((B2) this.d).c(), (M0) ((B2) this.e).c());
            }
            M0 m0I = m0L;
            if (d()) {
                m0I = m0I.i(this.l, this.m >= 0 ? Math.min(m0I.count(), this.l + this.m) : this.n, this.k);
            }
            f(m0I);
            this.o = true;
        }
        if (this.m >= 0 && !d()) {
            long j = this.l + this.m;
            long jK = this.o ? this.n : k(j);
            if (jK >= j) {
                i();
            } else {
                B2 b22 = (B2) ((AbstractC0115e) getCompleter());
                B2 b23 = this;
                while (true) {
                    if (b22 == null) {
                        if (jK >= j) {
                            break;
                        }
                    } else {
                        if (b23 == b22.e && (b2 = (B2) b22.d) != null) {
                            jK += b2.k(j);
                            if (jK >= j) {
                                break;
                            }
                        }
                        b23 = b22;
                        b22 = (B2) ((AbstractC0115e) b22.getCompleter());
                    }
                }
                i();
            }
        }
        super.onCompletion(countedCompleter);
    }

    B2(AbstractC0100b abstractC0100b, AbstractC0100b abstractC0100b2, j$.util.U u, IntFunction intFunction, long j, long j2) {
        super(abstractC0100b2, u);
        this.j = abstractC0100b;
        this.k = intFunction;
        this.l = j;
        this.m = j2;
    }

    B2(B2 b2, j$.util.U u) {
        super(b2, u);
        this.j = b2.j;
        this.k = b2.k;
        this.l = b2.l;
        this.m = b2.m;
    }

    @Override // j$.util.stream.AbstractC0115e
    protected final AbstractC0115e e(j$.util.U u) {
        return new B2(this, u);
    }

    @Override // j$.util.stream.AbstractC0105c
    protected final Object j() {
        return A0.L(this.j.I());
    }

    @Override // j$.util.stream.AbstractC0115e
    protected final Object a() {
        if (d()) {
            E0 e0N = this.j.N(EnumC0129g3.SIZED.s(this.j.c) ? this.j.G(this.b) : -1L, this.k);
            InterfaceC0182r2 interfaceC0182r2R = this.j.R(this.a.K(), e0N);
            AbstractC0100b abstractC0100b = this.a;
            abstractC0100b.B(this.b, abstractC0100b.W(interfaceC0182r2R));
            return e0N.a();
        }
        E0 e0N2 = this.j.N(-1L, this.k);
        if (this.l == 0) {
            InterfaceC0182r2 interfaceC0182r2R2 = this.j.R(this.a.K(), e0N2);
            AbstractC0100b abstractC0100b2 = this.a;
            abstractC0100b2.B(this.b, abstractC0100b2.W(interfaceC0182r2R2));
        } else {
            this.a.V(this.b, e0N2);
        }
        M0 m0A = e0N2.a();
        this.n = m0A.count();
        this.o = true;
        this.b = null;
        return m0A;
    }

    private long k(long j) {
        if (this.o) {
            return this.n;
        }
        B2 b2 = (B2) this.d;
        B2 b22 = (B2) this.e;
        if (b2 == null || b22 == null) {
            return this.n;
        }
        long jK = b2.k(j);
        return jK >= j ? jK : jK + b22.k(j);
    }
}
