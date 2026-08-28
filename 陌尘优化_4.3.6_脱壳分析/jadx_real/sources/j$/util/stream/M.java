package j$.util.stream;

import java.util.function.DoubleConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class M extends Q implements InterfaceC0168o2 {
    final DoubleConsumer b;

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final /* bridge */ /* synthetic */ void q(Object obj) {
        q((Double) obj);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.d(this, doubleConsumer);
    }

    @Override // java.util.function.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return null;
    }

    @Override // j$.util.stream.InterfaceC0168o2
    public final /* synthetic */ void q(Double d) {
        A0.e(this, d);
    }

    @Override // j$.util.stream.M3
    public final Object b(AbstractC0100b abstractC0100b, j$.util.U u) {
        abstractC0100b.V(u, this);
        return null;
    }

    @Override // j$.util.stream.M3
    public final /* bridge */ /* synthetic */ Object c(AbstractC0100b abstractC0100b, j$.util.U u) {
        e(abstractC0100b, u);
        return null;
    }

    M(DoubleConsumer doubleConsumer, boolean z) {
        super(z);
        this.b = doubleConsumer;
    }

    @Override // j$.util.stream.Q, j$.util.stream.InterfaceC0182r2
    public final void accept(double d) {
        this.b.accept(d);
    }
}
