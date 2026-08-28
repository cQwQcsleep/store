package j$.util.stream;

import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ForkJoinPool;

/* renamed from: j$.util.stream.e, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0115e extends CountedCompleter {
    private static final int g = ForkJoinPool.getCommonPoolParallelism() << 2;
    protected final AbstractC0100b a;
    protected j$.util.U b;
    protected long c;
    protected AbstractC0115e d;
    protected AbstractC0115e e;
    private Object f;

    protected abstract Object a();

    protected abstract AbstractC0115e e(j$.util.U u);

    protected AbstractC0115e(AbstractC0100b abstractC0100b, j$.util.U u) {
        super(null);
        this.a = abstractC0100b;
        this.b = u;
        this.c = 0L;
    }

    protected AbstractC0115e(AbstractC0115e abstractC0115e, j$.util.U u) {
        super(abstractC0115e);
        this.b = u;
        this.a = abstractC0115e.a;
        this.c = abstractC0115e.c;
    }

    public static int b() {
        return g;
    }

    public static long g(long j) {
        long j2 = j / g;
        if (j2 > 0) {
            return j2;
        }
        return 1L;
    }

    @Override // java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    public Object getRawResult() {
        return this.f;
    }

    @Override // java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    protected final void setRawResult(Object obj) {
        if (obj != null) {
            throw new IllegalStateException();
        }
    }

    protected Object c() {
        return this.f;
    }

    protected void f(Object obj) {
        this.f = obj;
    }

    protected final boolean d() {
        return ((AbstractC0115e) getCompleter()) == null;
    }

    @Override // java.util.concurrent.CountedCompleter
    public void compute() {
        j$.util.U uTrySplit;
        j$.util.U u = this.b;
        long jEstimateSize = u.estimateSize();
        long jG = this.c;
        if (jG == 0) {
            jG = g(jEstimateSize);
            this.c = jG;
        }
        boolean z = false;
        AbstractC0115e abstractC0115e = this;
        while (jEstimateSize > jG && (uTrySplit = u.trySplit()) != null) {
            AbstractC0115e abstractC0115eE = abstractC0115e.e(uTrySplit);
            abstractC0115e.d = abstractC0115eE;
            AbstractC0115e abstractC0115eE2 = abstractC0115e.e(u);
            abstractC0115e.e = abstractC0115eE2;
            abstractC0115e.setPendingCount(1);
            if (z) {
                u = uTrySplit;
                abstractC0115e = abstractC0115eE;
                abstractC0115eE = abstractC0115eE2;
            } else {
                abstractC0115e = abstractC0115eE2;
            }
            z = !z;
            abstractC0115eE.fork();
            jEstimateSize = u.estimateSize();
        }
        abstractC0115e.f(abstractC0115e.a());
        abstractC0115e.tryComplete();
    }

    @Override // java.util.concurrent.CountedCompleter
    public void onCompletion(CountedCompleter countedCompleter) {
        this.b = null;
        this.e = null;
        this.d = null;
    }
}
