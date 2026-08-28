package j$.util.stream;

import j$.util.Objects;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/* renamed from: j$.util.stream.b, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0100b implements InterfaceC0130h {
    private final AbstractC0100b a;
    private final AbstractC0100b b;
    protected final int c;
    private AbstractC0100b d;
    private int e;
    private int f;
    private j$.util.U g;
    private boolean h;
    private boolean i;
    private Runnable j;
    private boolean k;

    abstract M0 F(AbstractC0100b abstractC0100b, j$.util.U u, boolean z, IntFunction intFunction);

    abstract boolean H(j$.util.U u, InterfaceC0182r2 interfaceC0182r2);

    abstract EnumC0134h3 I();

    abstract E0 N(long j, IntFunction intFunction);

    abstract boolean Q();

    abstract InterfaceC0182r2 R(int i, InterfaceC0182r2 interfaceC0182r2);

    abstract j$.util.U U(AbstractC0100b abstractC0100b, Supplier supplier, boolean z);

    AbstractC0100b(j$.util.U u, int i, boolean z) {
        this.b = null;
        this.g = u;
        this.a = this;
        int i2 = EnumC0129g3.g & i;
        this.c = i2;
        this.f = (~(i2 << 1)) & EnumC0129g3.l;
        this.e = 0;
        this.k = z;
    }

    AbstractC0100b(AbstractC0100b abstractC0100b, int i) {
        if (abstractC0100b.h) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        abstractC0100b.h = true;
        abstractC0100b.d = this;
        this.b = abstractC0100b;
        this.c = EnumC0129g3.h & i;
        this.f = EnumC0129g3.j(i, abstractC0100b.f);
        AbstractC0100b abstractC0100b2 = abstractC0100b.a;
        this.a = abstractC0100b2;
        if (Q()) {
            abstractC0100b2.i = true;
        }
        this.e = abstractC0100b.e + 1;
    }

    final Object D(M3 m3) {
        if (this.h) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.h = true;
        if (this.a.k) {
            return m3.c(this, S(m3.d()));
        }
        return m3.b(this, S(m3.d()));
    }

    final M0 E(IntFunction intFunction) {
        AbstractC0100b abstractC0100b;
        if (this.h) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.h = true;
        if (this.a.k && (abstractC0100b = this.b) != null && Q()) {
            this.e = 0;
            return O(abstractC0100b, abstractC0100b.S(0), intFunction);
        }
        return C(S(0), true, intFunction);
    }

    final j$.util.U T() {
        AbstractC0100b abstractC0100b = this.a;
        if (this != abstractC0100b) {
            throw new IllegalStateException();
        }
        if (this.h) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.h = true;
        j$.util.U u = abstractC0100b.g;
        if (u != null) {
            abstractC0100b.g = null;
            return u;
        }
        throw new IllegalStateException("source already consumed or closed");
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final InterfaceC0130h sequential() {
        this.a.k = false;
        return this;
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final InterfaceC0130h parallel() {
        this.a.k = true;
        return this;
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        this.h = true;
        this.g = null;
        AbstractC0100b abstractC0100b = this.a;
        Runnable runnable = abstractC0100b.j;
        if (runnable != null) {
            abstractC0100b.j = null;
            runnable.run();
        }
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final InterfaceC0130h onClose(Runnable runnable) {
        if (this.h) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        Objects.requireNonNull(runnable);
        AbstractC0100b abstractC0100b = this.a;
        Runnable runnable2 = abstractC0100b.j;
        if (runnable2 != null) {
            runnable = new L3(runnable2, runnable);
        }
        abstractC0100b.j = runnable;
        return this;
    }

    @Override // j$.util.stream.InterfaceC0130h
    public j$.util.U spliterator() {
        if (this.h) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.h = true;
        AbstractC0100b abstractC0100b = this.a;
        if (this == abstractC0100b) {
            j$.util.U u = abstractC0100b.g;
            if (u != null) {
                abstractC0100b.g = null;
                return u;
            }
            throw new IllegalStateException("source already consumed or closed");
        }
        return U(this, new C0095a(this, 0), abstractC0100b.k);
    }

    final /* synthetic */ j$.util.U M() {
        return S(0);
    }

    final M0 C(j$.util.U u, boolean z, IntFunction intFunction) {
        if (this.a.k) {
            return F(this, u, z, intFunction);
        }
        E0 e0N = N(G(u), intFunction);
        V(u, e0N);
        return e0N.a();
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final boolean isParallel() {
        return this.a.k;
    }

    private j$.util.U S(int i) {
        int i2;
        int i3;
        AbstractC0100b abstractC0100b = this.a;
        j$.util.U uP = abstractC0100b.g;
        if (uP != null) {
            abstractC0100b.g = null;
            if (abstractC0100b.k && abstractC0100b.i) {
                AbstractC0100b abstractC0100b2 = abstractC0100b.d;
                int i4 = 1;
                while (abstractC0100b != this) {
                    int i5 = abstractC0100b2.c;
                    if (abstractC0100b2.Q()) {
                        if (EnumC0129g3.SHORT_CIRCUIT.n(i5)) {
                            i5 &= ~EnumC0129g3.u;
                        }
                        uP = abstractC0100b2.P(abstractC0100b, uP);
                        if (uP.hasCharacteristics(64)) {
                            i2 = (~EnumC0129g3.t) & i5;
                            i3 = EnumC0129g3.s;
                        } else {
                            i2 = (~EnumC0129g3.s) & i5;
                            i3 = EnumC0129g3.t;
                        }
                        i5 = i2 | i3;
                        i4 = 0;
                    }
                    abstractC0100b2.e = i4;
                    abstractC0100b2.f = EnumC0129g3.j(i5, abstractC0100b.f);
                    i4++;
                    AbstractC0100b abstractC0100b3 = abstractC0100b2;
                    abstractC0100b2 = abstractC0100b2.d;
                    abstractC0100b = abstractC0100b3;
                }
            }
            if (i != 0) {
                this.f = EnumC0129g3.j(i, this.f);
            }
            return uP;
        }
        throw new IllegalStateException("source already consumed or closed");
    }

    final EnumC0134h3 J() {
        AbstractC0100b abstractC0100b = this;
        while (abstractC0100b.e > 0) {
            abstractC0100b = abstractC0100b.b;
        }
        return abstractC0100b.I();
    }

    final long G(j$.util.U u) {
        if (EnumC0129g3.SIZED.n(this.f)) {
            return u.getExactSizeIfKnown();
        }
        return -1L;
    }

    final InterfaceC0182r2 V(j$.util.U u, InterfaceC0182r2 interfaceC0182r2) {
        A(u, W((InterfaceC0182r2) Objects.requireNonNull(interfaceC0182r2)));
        return interfaceC0182r2;
    }

    final void A(j$.util.U u, InterfaceC0182r2 interfaceC0182r2) {
        Objects.requireNonNull(interfaceC0182r2);
        if (!EnumC0129g3.SHORT_CIRCUIT.n(this.f)) {
            interfaceC0182r2.m(u.getExactSizeIfKnown());
            u.forEachRemaining(interfaceC0182r2);
            interfaceC0182r2.l();
            return;
        }
        B(u, interfaceC0182r2);
    }

    final boolean B(j$.util.U u, InterfaceC0182r2 interfaceC0182r2) {
        AbstractC0100b abstractC0100b = this;
        while (abstractC0100b.e > 0) {
            abstractC0100b = abstractC0100b.b;
        }
        interfaceC0182r2.m(u.getExactSizeIfKnown());
        boolean zH = abstractC0100b.H(u, interfaceC0182r2);
        interfaceC0182r2.l();
        return zH;
    }

    final int K() {
        return this.f;
    }

    final boolean L() {
        return EnumC0129g3.ORDERED.n(this.f);
    }

    final InterfaceC0182r2 W(InterfaceC0182r2 interfaceC0182r2) {
        Objects.requireNonNull(interfaceC0182r2);
        AbstractC0100b abstractC0100b = this;
        while (abstractC0100b.e > 0) {
            AbstractC0100b abstractC0100b2 = abstractC0100b.b;
            interfaceC0182r2 = abstractC0100b.R(abstractC0100b2.f, interfaceC0182r2);
            abstractC0100b = abstractC0100b2;
        }
        return interfaceC0182r2;
    }

    final j$.util.U X(j$.util.U u) {
        return this.e == 0 ? u : U(this, new C0095a(u, 6), this.a.k);
    }

    M0 O(AbstractC0100b abstractC0100b, j$.util.U u, IntFunction intFunction) {
        throw new UnsupportedOperationException("Parallel evaluation is not supported");
    }

    j$.util.U P(AbstractC0100b abstractC0100b, j$.util.U u) {
        return O(abstractC0100b, u, new C0170p(15)).spliterator();
    }
}
