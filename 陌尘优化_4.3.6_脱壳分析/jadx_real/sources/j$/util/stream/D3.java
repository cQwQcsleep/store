package j$.util.stream;

import j$.util.AbstractC0078b;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class D3 extends F3 implements j$.util.K, IntConsumer {
    int f;

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.com.android.tools.r8.a.e(this, intConsumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.b(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.i(this, consumer);
    }

    @Override // j$.util.stream.I3
    protected final j$.util.U c(j$.util.U u) {
        return new D3((j$.util.K) u, this);
    }

    @Override // j$.util.stream.F3
    protected final void e(Object obj) {
        ((IntConsumer) obj).accept(this.f);
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        this.f = i;
    }

    @Override // j$.util.stream.F3
    protected final AbstractC0159m3 f(int i) {
        return new C0149k3(i);
    }
}
