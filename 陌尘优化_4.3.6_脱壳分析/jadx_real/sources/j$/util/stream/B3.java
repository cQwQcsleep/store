package j$.util.stream;

/* loaded from: /workspace/unpacked/classes3.dex */
abstract class B3 {
    final long a;
    final long b;
    j$.util.U c;
    long d;
    long e;

    protected abstract j$.util.U a(j$.util.U u, long j, long j2, long j3, long j4);

    B3(j$.util.U u, long j, long j2, long j3, long j4) {
        this.c = u;
        this.a = j;
        this.b = j2;
        this.d = j3;
        this.e = j4;
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public final j$.util.U m272trySplit() {
        long j = this.e;
        if (this.a >= j || this.d >= j) {
            return null;
        }
        while (true) {
            j$.util.U uTrySplit = this.c.trySplit();
            if (uTrySplit == null) {
                return null;
            }
            long jEstimateSize = uTrySplit.estimateSize() + this.d;
            long jMin = Math.min(jEstimateSize, this.b);
            long j2 = this.a;
            if (j2 >= jMin) {
                this.d = jMin;
            } else {
                long j3 = this.b;
                if (jMin >= j3) {
                    this.c = uTrySplit;
                    this.e = jMin;
                } else {
                    long j4 = this.d;
                    if (j4 >= j2 && jEstimateSize <= j3) {
                        this.d = jMin;
                        return uTrySplit;
                    }
                    this.d = jMin;
                    return a(uTrySplit, j2, j3, j4, jMin);
                }
            }
        }
    }

    public final long estimateSize() {
        long j = this.e;
        long j2 = this.a;
        if (j2 < j) {
            return j - Math.max(j2, this.d);
        }
        return 0L;
    }

    public final int characteristics() {
        return this.c.characteristics();
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ j$.util.Q m271trySplit() {
        return (j$.util.Q) m272trySplit();
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ j$.util.K m269trySplit() {
        return (j$.util.K) m272trySplit();
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ j$.util.N m270trySplit() {
        return (j$.util.N) m272trySplit();
    }

    public /* bridge */ /* synthetic */ j$.util.H trySplit() {
        return (j$.util.H) m272trySplit();
    }
}
