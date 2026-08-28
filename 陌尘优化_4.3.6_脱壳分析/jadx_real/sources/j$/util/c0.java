package j$.util;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class c0 extends AbstractC0078b implements K {
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

    @Override // j$.util.AbstractC0078b, j$.util.H, j$.util.Q, j$.util.U
    public final /* bridge */ /* synthetic */ K trySplit() {
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

    @Override // j$.util.K
    public final boolean tryAdvance(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        return false;
    }

    @Override // j$.util.K
    public final void forEachRemaining(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
    }
}
