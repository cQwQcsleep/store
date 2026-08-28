package j$.util.stream;

import java.util.function.DoubleConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class Y1 extends AbstractC0108c2 implements InterfaceC0168o2 {
    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final /* bridge */ /* synthetic */ void q(Object obj) {
        q((Double) obj);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.d(this, doubleConsumer);
    }

    @Override // j$.util.stream.InterfaceC0168o2
    public final /* synthetic */ void q(Double d) {
        A0.e(this, d);
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
    public final void accept(double d) {
        this.b++;
    }
}
