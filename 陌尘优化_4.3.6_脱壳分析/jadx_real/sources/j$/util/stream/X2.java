package j$.util.stream;

import j$.util.AbstractC0078b;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class X2 extends Z2 implements j$.util.N {
    final /* synthetic */ Y2 g;

    @Override // j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.c(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.j(this, consumer);
    }

    @Override // j$.util.stream.Z2
    final void a(int i, Object obj, Object obj2) {
        ((LongConsumer) obj2).accept(((long[]) obj)[i]);
    }

    @Override // j$.util.stream.Z2
    final j$.util.Q b(Object obj, int i, int i2) {
        return j$.util.i0.l((long[]) obj, i, i2 + i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    X2(Y2 y2, int i, int i2, int i3, int i4) {
        super(y2, i, i2, i3, i4);
        this.g = y2;
    }

    @Override // j$.util.stream.Z2
    final j$.util.Q c(int i, int i2, int i3, int i4) {
        return new X2(this.g, i, i2, i3, i4);
    }
}
