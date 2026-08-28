package j$.util.stream;

import j$.util.function.LongConsumer$CC;
import java.util.function.LongConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class O extends Q implements InterfaceC0178q2 {
    final LongConsumer b;

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final /* bridge */ /* synthetic */ void q(Object obj) {
        k((Long) obj);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // java.util.function.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return null;
    }

    @Override // j$.util.stream.InterfaceC0178q2
    public final /* synthetic */ void k(Long l) {
        A0.i(this, l);
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

    O(LongConsumer longConsumer, boolean z) {
        super(z);
        this.b = longConsumer;
    }

    @Override // j$.util.stream.Q, j$.util.stream.InterfaceC0182r2, j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
    public final void accept(long j) {
        this.b.accept(j);
    }
}
