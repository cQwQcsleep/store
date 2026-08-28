package j$.util.stream;

import java.util.function.IntConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class Z1 extends AbstractC0108c2 implements InterfaceC0173p2 {
    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final /* bridge */ /* synthetic */ void q(Object obj) {
        n((Integer) obj);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.com.android.tools.r8.a.e(this, intConsumer);
    }

    @Override // j$.util.stream.InterfaceC0173p2
    public final /* synthetic */ void n(Integer num) {
        A0.g(this, num);
    }

    @Override // j$.util.stream.X1, java.util.function.Supplier
    public final Object get() {
        return Long.valueOf(this.b);
    }

    @Override // j$.util.stream.W1
    public final void h(W1 w1) {
        this.b += ((AbstractC0108c2) w1).b;
    }

    @Override // j$.util.stream.AbstractC0108c2, j$.util.stream.InterfaceC0182r2
    public final void accept(int i) {
        this.b++;
    }
}
