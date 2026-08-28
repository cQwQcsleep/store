package j$.util.concurrent;

import j$.util.AbstractC0078b;
import j$.util.H;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class w implements H {
    long a;
    final long b;
    final double c;
    final double d;

    @Override // j$.util.U
    public final int characteristics() {
        return 17728;
    }

    @Override // j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.a(this, consumer);
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
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.h(this, consumer);
    }

    @Override // j$.util.U
    public final Comparator getComparator() {
        throw new IllegalStateException();
    }

    w(long j, long j2, double d, double d2) {
        this.a = j;
        this.b = j2;
        this.c = d;
        this.d = d2;
    }

    @Override // j$.util.U
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final w trySplit() {
        long j = this.a;
        long j2 = (this.b + j) >>> 1;
        if (j2 <= j) {
            return null;
        }
        this.a = j2;
        return new w(j, j2, this.c, this.d);
    }

    @Override // j$.util.U
    public final long estimateSize() {
        return this.b - this.a;
    }

    @Override // j$.util.Q
    public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
        doubleConsumer.getClass();
        long j = this.a;
        if (j >= this.b) {
            return false;
        }
        doubleConsumer.accept(z.b().d(this.c, this.d));
        this.a = j + 1;
        return true;
    }

    @Override // j$.util.Q
    public final void forEachRemaining(DoubleConsumer doubleConsumer) {
        doubleConsumer.getClass();
        long j = this.a;
        long j2 = this.b;
        if (j < j2) {
            this.a = j2;
            z zVarB = z.b();
            do {
                doubleConsumer.accept(zVarB.d(this.c, this.d));
                j++;
            } while (j < j2);
        }
    }
}
