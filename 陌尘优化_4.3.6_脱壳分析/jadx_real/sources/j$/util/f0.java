package j$.util;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class f0 implements K {
    private final int[] a;
    private int b;
    private final int c;
    private final int d;

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

    public f0(int[] iArr, int i, int i2, int i3) {
        this.a = iArr;
        this.b = i;
        this.c = i2;
        this.d = i3 | 16448;
    }

    @Override // j$.util.U
    public final K trySplit() {
        int i = this.b;
        int i2 = (this.c + i) >>> 1;
        if (i >= i2) {
            return null;
        }
        this.b = i2;
        return new f0(this.a, i, i2, this.d);
    }

    @Override // j$.util.Q
    public final void forEachRemaining(IntConsumer intConsumer) {
        int i;
        intConsumer.getClass();
        int[] iArr = this.a;
        int length = iArr.length;
        int i2 = this.c;
        if (length < i2 || (i = this.b) < 0) {
            return;
        }
        this.b = i2;
        if (i < i2) {
            do {
                intConsumer.accept(iArr[i]);
                i++;
            } while (i < i2);
        }
    }

    @Override // j$.util.Q
    public final boolean tryAdvance(IntConsumer intConsumer) {
        intConsumer.getClass();
        int i = this.b;
        if (i < 0 || i >= this.c) {
            return false;
        }
        this.b = i + 1;
        intConsumer.accept(this.a[i]);
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
