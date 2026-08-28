package j$.util.stream;

import j$.util.AbstractC0078b;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class C3 extends F3 implements j$.util.H, DoubleConsumer {
    double f;

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.d(this, doubleConsumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.a(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.h(this, consumer);
    }

    @Override // j$.util.stream.I3
    protected final j$.util.U c(j$.util.U u) {
        return new C3((j$.util.H) u, this);
    }

    @Override // j$.util.stream.F3
    protected final void e(Object obj) {
        ((DoubleConsumer) obj).accept(this.f);
    }

    @Override // java.util.function.DoubleConsumer
    public final void accept(double d) {
        this.f = d;
    }

    @Override // j$.util.stream.F3
    protected final AbstractC0159m3 f(int i) {
        return new C0144j3(i);
    }
}
