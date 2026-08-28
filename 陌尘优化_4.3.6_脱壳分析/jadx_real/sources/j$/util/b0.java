package j$.util;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class b0 extends AbstractC0078b implements H {
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

    @Override // j$.util.AbstractC0078b, j$.util.H, j$.util.Q, j$.util.U
    public final /* bridge */ /* synthetic */ H trySplit() {
        return null;
    }

    @Override // j$.util.AbstractC0078b, j$.util.H, j$.util.Q, j$.util.U
    public final /* bridge */ /* synthetic */ Q trySplit() {
        return null;
    }

    @Override // j$.util.U
    public final java.util.Comparator getComparator() {
        throw new IllegalStateException();
    }

    @Override // j$.util.H
    public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        return false;
    }

    @Override // j$.util.H
    public final void forEachRemaining(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
    }
}
