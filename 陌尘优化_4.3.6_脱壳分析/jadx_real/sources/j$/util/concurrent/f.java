package j$.util.concurrent;

import j$.util.AbstractC0078b;
import j$.util.U;
import java.util.Comparator;
import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class f extends p implements U {
    final ConcurrentHashMap i;
    long j;

    @Override // j$.util.U
    public final int characteristics() {
        return 4353;
    }

    @Override // j$.util.U
    public final /* synthetic */ long getExactSizeIfKnown() {
        return AbstractC0078b.d(this);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return AbstractC0078b.e(this, i);
    }

    @Override // j$.util.U
    public final Comparator getComparator() {
        throw new IllegalStateException();
    }

    f(l[] lVarArr, int i, int i2, int i3, long j, ConcurrentHashMap concurrentHashMap) {
        super(lVarArr, i, i2, i3);
        this.i = concurrentHashMap;
        this.j = j;
    }

    @Override // j$.util.U
    public final U trySplit() {
        int i = this.f;
        int i2 = this.g;
        int i3 = (i + i2) >>> 1;
        if (i3 <= i) {
            return null;
        }
        l[] lVarArr = this.a;
        this.g = i3;
        long j = this.j >>> 1;
        this.j = j;
        return new f(lVarArr, this.h, i3, i2, j, this.i);
    }

    @Override // j$.util.U
    public final void forEachRemaining(Consumer consumer) {
        consumer.getClass();
        while (true) {
            l lVarA = a();
            if (lVarA == null) {
                return;
            } else {
                consumer.accept(new k(lVarA.b, lVarA.c, this.i));
            }
        }
    }

    @Override // j$.util.U
    public final boolean tryAdvance(Consumer consumer) {
        consumer.getClass();
        l lVarA = a();
        if (lVarA == null) {
            return false;
        }
        consumer.accept(new k(lVarA.b, lVarA.c, this.i));
        return true;
    }

    @Override // j$.util.U
    public final long estimateSize() {
        return this.j;
    }
}
