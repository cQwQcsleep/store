package j$.util.stream;

import java.util.function.IntConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class N extends Q implements InterfaceC0173p2 {
    final IntConsumer b;

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final /* bridge */ /* synthetic */ void q(Object obj) {
        n((Integer) obj);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.com.android.tools.r8.a.e(this, intConsumer);
    }

    @Override // java.util.function.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return null;
    }

    @Override // j$.util.stream.InterfaceC0173p2
    public final /* synthetic */ void n(Integer num) {
        A0.g(this, num);
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

    N(IntConsumer intConsumer, boolean z) {
        super(z);
        this.b = intConsumer;
    }

    @Override // j$.util.stream.Q, j$.util.stream.InterfaceC0182r2
    public final void accept(int i) {
        this.b.accept(i);
    }
}
