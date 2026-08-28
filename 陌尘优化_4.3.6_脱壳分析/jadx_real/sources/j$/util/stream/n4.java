package j$.util.stream;

import j$.util.AbstractC0078b;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
abstract class n4 implements j$.util.U {
    final j$.util.U a;
    final AtomicBoolean b;
    boolean c;
    int d;

    abstract j$.util.U c(j$.util.U u);

    @Override // j$.util.U
    public final long getExactSizeIfKnown() {
        return -1L;
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return AbstractC0078b.e(this, i);
    }

    @Override // j$.util.U
    public void forEachRemaining(Consumer consumer) {
        while (tryAdvance(consumer)) {
        }
    }

    n4(j$.util.U u) {
        this.c = true;
        this.a = u;
        this.b = new AtomicBoolean();
    }

    n4(j$.util.U u, n4 n4Var) {
        this.c = true;
        this.a = u;
        n4Var.getClass();
        this.b = n4Var.b;
    }

    @Override // j$.util.U
    public final long estimateSize() {
        return this.a.estimateSize();
    }

    @Override // j$.util.U
    public final int characteristics() {
        return this.a.characteristics() & (-16449);
    }

    @Override // j$.util.U
    public final Comparator getComparator() {
        return this.a.getComparator();
    }

    @Override // j$.util.U
    public j$.util.U trySplit() {
        j$.util.U uTrySplit = this.a.trySplit();
        if (uTrySplit != null) {
            return c(uTrySplit);
        }
        return null;
    }

    final boolean b() {
        return (this.d == 0 && this.b.get()) ? false : true;
    }

    @Override // j$.util.U
    public /* bridge */ /* synthetic */ j$.util.K trySplit() {
        return (j$.util.K) trySplit();
    }

    @Override // j$.util.U
    public /* bridge */ /* synthetic */ j$.util.N trySplit() {
        return (j$.util.N) trySplit();
    }

    @Override // j$.util.U
    public /* bridge */ /* synthetic */ j$.util.H trySplit() {
        return (j$.util.H) trySplit();
    }

    @Override // j$.util.U
    public /* bridge */ /* synthetic */ j$.util.Q trySplit() {
        return (j$.util.Q) trySplit();
    }
}
