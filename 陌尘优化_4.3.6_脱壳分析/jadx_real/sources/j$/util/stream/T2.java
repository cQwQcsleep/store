package j$.util.stream;

import j$.util.AbstractC0078b;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class T2 extends Z2 implements j$.util.H {
    final /* synthetic */ U2 g;

    @Override // j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.a(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.h(this, consumer);
    }

    @Override // j$.util.stream.Z2
    final void a(int i, Object obj, Object obj2) {
        ((DoubleConsumer) obj2).accept(((double[]) obj)[i]);
    }

    @Override // j$.util.stream.Z2
    final j$.util.Q b(Object obj, int i, int i2) {
        return j$.util.i0.j((double[]) obj, i, i2 + i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    T2(U2 u2, int i, int i2, int i3, int i4) {
        super(u2, i, i2, i3, i4);
        this.g = u2;
    }

    @Override // j$.util.stream.Z2
    final j$.util.Q c(int i, int i2, int i3, int i4) {
        return new T2(this.g, i, i2, i3, i4);
    }
}
