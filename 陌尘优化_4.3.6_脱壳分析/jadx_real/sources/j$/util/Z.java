package j$.util;

import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class Z implements U {
    private final Object[] a;
    private int b;
    private final int c;
    private final int d;

    @Override // j$.util.U
    public final /* synthetic */ long getExactSizeIfKnown() {
        return AbstractC0078b.d(this);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return AbstractC0078b.e(this, i);
    }

    public Z(Object[] objArr, int i, int i2, int i3) {
        this.a = objArr;
        this.b = i;
        this.c = i2;
        this.d = i3 | 16448;
    }

    @Override // j$.util.U
    public final U trySplit() {
        int i = this.b;
        int i2 = (this.c + i) >>> 1;
        if (i >= i2) {
            return null;
        }
        this.b = i2;
        return new Z(this.a, i, i2, this.d);
    }

    @Override // j$.util.U
    public final void forEachRemaining(Consumer consumer) {
        int i;
        consumer.getClass();
        Object[] objArr = this.a;
        int length = objArr.length;
        int i2 = this.c;
        if (length < i2 || (i = this.b) < 0) {
            return;
        }
        this.b = i2;
        if (i < i2) {
            do {
                consumer.accept(objArr[i]);
                i++;
            } while (i < i2);
        }
    }

    @Override // j$.util.U
    public final boolean tryAdvance(Consumer consumer) {
        consumer.getClass();
        int i = this.b;
        if (i < 0 || i >= this.c) {
            return false;
        }
        this.b = i + 1;
        consumer.accept(this.a[i]);
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
