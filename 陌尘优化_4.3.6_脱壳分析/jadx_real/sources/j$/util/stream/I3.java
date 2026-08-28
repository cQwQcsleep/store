package j$.util.stream;

import java.util.concurrent.atomic.AtomicLong;

/* loaded from: /workspace/unpacked/classes3.dex */
abstract class I3 {
    protected final j$.util.U a;
    protected final boolean b;
    protected final int c;
    private final long d;
    private final AtomicLong e;

    protected abstract j$.util.U c(j$.util.U u);

    I3(j$.util.U u, long j, long j2) {
        this.a = u;
        this.b = j2 < 0;
        this.d = j2 >= 0 ? j2 : 0L;
        this.c = 128;
        this.e = new AtomicLong(j2 >= 0 ? j + j2 : j);
    }

    I3(j$.util.U u, I3 i3) {
        this.a = u;
        this.b = i3.b;
        this.e = i3.e;
        this.d = i3.d;
        this.c = i3.c;
    }

    protected final long b(long j) {
        AtomicLong atomicLong;
        long j2;
        boolean z;
        long jMin;
        do {
            atomicLong = this.e;
            j2 = atomicLong.get();
            z = this.b;
            if (j2 != 0) {
                jMin = Math.min(j2, j);
                if (jMin <= 0) {
                    break;
                }
            } else {
                if (z) {
                    return j;
                }
                return 0L;
            }
        } while (!atomicLong.compareAndSet(j2, j2 - jMin));
        if (z) {
            return Math.max(j - jMin, 0L);
        }
        long j3 = this.d;
        return j2 > j3 ? Math.max(jMin - (j2 - j3), 0L) : jMin;
    }

    protected final H3 d() {
        if (this.e.get() > 0) {
            return H3.MAYBE_MORE;
        }
        return this.b ? H3.UNLIMITED : H3.NO_MORE;
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public final j$.util.U m276trySplit() {
        j$.util.U uTrySplit;
        if (this.e.get() == 0 || (uTrySplit = this.a.trySplit()) == null) {
            return null;
        }
        return c(uTrySplit);
    }

    public final long estimateSize() {
        return this.a.estimateSize();
    }

    public final int characteristics() {
        return this.a.characteristics() & (-16465);
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ j$.util.Q m275trySplit() {
        return (j$.util.Q) m276trySplit();
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ j$.util.K m273trySplit() {
        return (j$.util.K) m276trySplit();
    }

    /* renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ j$.util.N m274trySplit() {
        return (j$.util.N) m276trySplit();
    }

    public /* bridge */ /* synthetic */ j$.util.H trySplit() {
        return (j$.util.H) m276trySplit();
    }
}
