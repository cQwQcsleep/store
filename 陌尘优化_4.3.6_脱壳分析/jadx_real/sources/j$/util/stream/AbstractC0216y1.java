package j$.util.stream;

import java.util.concurrent.CountedCompleter;
import java.util.function.Consumer;

/* renamed from: j$.util.stream.y1, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0216y1 extends CountedCompleter implements InterfaceC0182r2 {
    protected final j$.util.U a;
    protected final AbstractC0100b b;
    protected final long c;
    protected long d;
    protected long e;
    protected int f;
    protected int g;

    public /* synthetic */ void accept(double d) {
        A0.a();
        throw null;
    }

    public /* synthetic */ void accept(int i) {
        A0.k();
        throw null;
    }

    public /* synthetic */ void accept(long j) {
        A0.l();
        throw null;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    abstract AbstractC0216y1 b(j$.util.U u, long j, long j2);

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ void l() {
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ boolean o() {
        return false;
    }

    AbstractC0216y1(j$.util.U u, AbstractC0100b abstractC0100b, int i) {
        this.a = u;
        this.b = abstractC0100b;
        this.c = AbstractC0115e.g(u.estimateSize());
        this.d = 0L;
        this.e = i;
    }

    AbstractC0216y1(AbstractC0216y1 abstractC0216y1, j$.util.U u, long j, long j2, int i) {
        super(abstractC0216y1);
        this.a = u;
        this.b = abstractC0216y1.b;
        this.c = abstractC0216y1.c;
        this.d = j;
        this.e = j2;
        if (j < 0 || j2 < 0 || (j + j2) - 1 >= i) {
            throw new IllegalArgumentException(String.format("offset and length interval [%d, %d + %d) is not within array size interval [0, %d)", Long.valueOf(j), Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(i)));
        }
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        j$.util.U uTrySplit;
        j$.util.U u = this.a;
        AbstractC0216y1 abstractC0216y1B = this;
        while (u.estimateSize() > abstractC0216y1B.c && (uTrySplit = u.trySplit()) != null) {
            abstractC0216y1B.setPendingCount(1);
            long jEstimateSize = uTrySplit.estimateSize();
            abstractC0216y1B.b(uTrySplit, abstractC0216y1B.d, jEstimateSize).fork();
            abstractC0216y1B = abstractC0216y1B.b(u, abstractC0216y1B.d + jEstimateSize, abstractC0216y1B.e - jEstimateSize);
        }
        abstractC0216y1B.b.V(u, abstractC0216y1B);
        abstractC0216y1B.propagateCompletion();
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        long j2 = this.e;
        if (j > j2) {
            throw new IllegalStateException("size passed to Sink.begin exceeds array length");
        }
        int i = (int) this.d;
        this.f = i;
        this.g = i + ((int) j2);
    }
}
