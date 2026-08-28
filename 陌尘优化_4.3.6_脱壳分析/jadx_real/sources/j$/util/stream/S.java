package j$.util.stream;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountedCompleter;

/* loaded from: /workspace/unpacked/classes3.dex */
final class S extends CountedCompleter {
    private final AbstractC0100b a;
    private j$.util.U b;
    private final long c;
    private final ConcurrentHashMap d;
    private final Q e;
    private final S f;
    private M0 g;

    protected S(AbstractC0100b abstractC0100b, j$.util.U u, Q q) {
        super(null);
        this.a = abstractC0100b;
        this.b = u;
        this.c = AbstractC0115e.g(u.estimateSize());
        this.d = new ConcurrentHashMap(Math.max(16, AbstractC0115e.b() << 1), 1);
        this.e = q;
        this.f = null;
    }

    S(S s, j$.util.U u, S s2) {
        super(s);
        this.a = s.a;
        this.b = u;
        this.c = s.c;
        this.d = s.d;
        this.e = s.e;
        this.f = s2;
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        j$.util.U uTrySplit;
        j$.util.U u = this.b;
        long j = this.c;
        boolean z = false;
        S s = this;
        while (u.estimateSize() > j && (uTrySplit = u.trySplit()) != null) {
            S s2 = new S(s, uTrySplit, s.f);
            S s3 = new S(s, u, s2);
            s.addToPendingCount(1);
            s3.addToPendingCount(1);
            s.d.put(s2, s3);
            if (s.f != null) {
                s2.addToPendingCount(1);
                if (s.d.replace(s.f, s, s2)) {
                    s.addToPendingCount(-1);
                } else {
                    s2.addToPendingCount(-1);
                }
            }
            if (z) {
                u = uTrySplit;
                s = s2;
                s2 = s3;
            } else {
                s = s3;
            }
            z = !z;
            s2.fork();
        }
        if (s.getPendingCount() > 0) {
            r rVar = new r(5);
            AbstractC0100b abstractC0100b = s.a;
            E0 e0N = abstractC0100b.N(abstractC0100b.G(u), rVar);
            s.a.V(u, e0N);
            s.g = e0N.a();
            s.b = null;
        }
        s.tryComplete();
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        M0 m0 = this.g;
        if (m0 != null) {
            m0.forEach(this.e);
            this.g = null;
        } else {
            j$.util.U u = this.b;
            if (u != null) {
                this.a.V(u, this.e);
                this.b = null;
            }
        }
        S s = (S) this.d.remove(this);
        if (s != null) {
            s.tryComplete();
        }
    }
}
