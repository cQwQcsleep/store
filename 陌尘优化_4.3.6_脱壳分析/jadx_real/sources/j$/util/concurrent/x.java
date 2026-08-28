package j$.util.concurrent;

import j$.util.AbstractC0078b;
import j$.util.K;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class x implements K {
    long a;
    final long b;
    final int c;
    final int d;

    @Override // j$.util.U
    public final int characteristics() {
        return 17728;
    }

    @Override // j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.b(this, consumer);
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
        return AbstractC0078b.i(this, consumer);
    }

    @Override // j$.util.U
    public final Comparator getComparator() {
        throw new IllegalStateException();
    }

    x(long j, long j2, int i, int i2) {
        this.a = j;
        this.b = j2;
        this.c = i;
        this.d = i2;
    }

    @Override // j$.util.U
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final x trySplit() {
        long j = this.a;
        long j2 = (this.b + j) >>> 1;
        if (j2 <= j) {
            return null;
        }
        this.a = j2;
        return new x(j, j2, this.c, this.d);
    }

    @Override // j$.util.U
    public final long estimateSize() {
        return this.b - this.a;
    }

    @Override // j$.util.Q
    public final boolean tryAdvance(IntConsumer intConsumer) {
        intConsumer.getClass();
        long j = this.a;
        if (j >= this.b) {
            return false;
        }
        intConsumer.accept(z.b().e(this.c, this.d));
        this.a = j + 1;
        return true;
    }

    @Override // j$.util.Q
    public final void forEachRemaining(IntConsumer intConsumer) {
        intConsumer.getClass();
        long j = this.a;
        long j2 = this.b;
        if (j < j2) {
            this.a = j2;
            z zVarB = z.b();
            do {
                intConsumer.accept(zVarB.e(this.c, this.d));
                j++;
            } while (j < j2);
        }
    }
}
