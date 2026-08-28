package j$.util.stream;

import j$.util.AbstractC0078b;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class V2 extends Z2 implements j$.util.K {
    final /* synthetic */ W2 g;

    @Override // j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.b(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.i(this, consumer);
    }

    @Override // j$.util.stream.Z2
    final void a(int i, Object obj, Object obj2) {
        ((IntConsumer) obj2).accept(((int[]) obj)[i]);
    }

    @Override // j$.util.stream.Z2
    final j$.util.Q b(Object obj, int i, int i2) {
        return j$.util.i0.k((int[]) obj, i, i2 + i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    V2(W2 w2, int i, int i2, int i3, int i4) {
        super(w2, i, i2, i3, i4);
        this.g = w2;
    }

    @Override // j$.util.stream.Z2
    final j$.util.Q c(int i, int i2, int i3, int i4) {
        return new V2(this.g, i, i2, i3, i4);
    }
}
