package j$.util.stream;

import java.util.concurrent.CountedCompleter;

/* loaded from: /workspace/unpacked/classes3.dex */
final class T extends CountedCompleter {
    private j$.util.U a;
    private final InterfaceC0182r2 b;
    private final AbstractC0100b c;
    private long d;

    T(AbstractC0100b abstractC0100b, j$.util.U u, InterfaceC0182r2 interfaceC0182r2) {
        super(null);
        this.b = interfaceC0182r2;
        this.c = abstractC0100b;
        this.a = u;
        this.d = 0L;
    }

    T(T t, j$.util.U u) {
        super(t);
        this.a = u;
        this.b = t.b;
        this.d = t.d;
        this.c = t.c;
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        j$.util.U uTrySplit;
        j$.util.U u = this.a;
        long jEstimateSize = u.estimateSize();
        long jG = this.d;
        if (jG == 0) {
            jG = AbstractC0115e.g(jEstimateSize);
            this.d = jG;
        }
        boolean zN = EnumC0129g3.SHORT_CIRCUIT.n(this.c.K());
        InterfaceC0182r2 interfaceC0182r2 = this.b;
        boolean z = false;
        T t = this;
        while (true) {
            if (zN && interfaceC0182r2.o()) {
                break;
            }
            if (jEstimateSize <= jG || (uTrySplit = u.trySplit()) == null) {
                break;
            }
            T t2 = new T(t, uTrySplit);
            t.addToPendingCount(1);
            if (z) {
                u = uTrySplit;
            } else {
                T t3 = t;
                t = t2;
                t2 = t3;
            }
            z = !z;
            t.fork();
            t = t2;
            jEstimateSize = u.estimateSize();
        }
        t.c.A(u, interfaceC0182r2);
        t.a = null;
        t.propagateCompletion();
    }
}
