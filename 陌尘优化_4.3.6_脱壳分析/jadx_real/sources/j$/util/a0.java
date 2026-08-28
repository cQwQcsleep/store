package j$.util;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class a0 implements H {
    private final double[] a;
    private int b;
    private final int c;
    private final int d;

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

    public a0(double[] dArr, int i, int i2, int i3) {
        this.a = dArr;
        this.b = i;
        this.c = i2;
        this.d = i3 | 16448;
    }

    @Override // j$.util.U
    public final H trySplit() {
        int i = this.b;
        int i2 = (this.c + i) >>> 1;
        if (i >= i2) {
            return null;
        }
        this.b = i2;
        return new a0(this.a, i, i2, this.d);
    }

    @Override // j$.util.Q
    public final void forEachRemaining(DoubleConsumer doubleConsumer) {
        int i;
        doubleConsumer.getClass();
        double[] dArr = this.a;
        int length = dArr.length;
        int i2 = this.c;
        if (length < i2 || (i = this.b) < 0) {
            return;
        }
        this.b = i2;
        if (i < i2) {
            do {
                doubleConsumer.accept(dArr[i]);
                i++;
            } while (i < i2);
        }
    }

    @Override // j$.util.Q
    public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
        doubleConsumer.getClass();
        int i = this.b;
        if (i < 0 || i >= this.c) {
            return false;
        }
        this.b = i + 1;
        doubleConsumer.accept(this.a[i]);
        return true;
    }

    @Override // j$.util.U
    public final long estimateSize() {
        return this.c - this.b;
    }

    @Override // j$.util.U
    public final int characteristics() {
        return this.d;
    }

    @Override // j$.util.U
    public final java.util.Comparator getComparator() {
        if (AbstractC0078b.e(this, 4)) {
            return null;
        }
        throw new IllegalStateException();
    }
}
