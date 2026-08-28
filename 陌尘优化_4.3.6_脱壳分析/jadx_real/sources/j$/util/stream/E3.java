package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.function.LongConsumer$CC;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class E3 extends F3 implements j$.util.N, LongConsumer {
    long f;

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.c(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.j(this, consumer);
    }

    @Override // j$.util.stream.I3
    protected final j$.util.U c(j$.util.U u) {
        return new E3((j$.util.N) u, this);
    }

    @Override // j$.util.stream.F3
    protected final void e(Object obj) {
        ((LongConsumer) obj).accept(this.f);
    }

    @Override // java.util.function.LongConsumer
    public final void accept(long j) {
        this.f = j;
    }

    @Override // j$.util.stream.F3
    protected final AbstractC0159m3 f(int i) {
        return new C0154l3(i);
    }
}
