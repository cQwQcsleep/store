package j$.util.stream;

import java.util.function.LongPredicate;

/* loaded from: /workspace/unpacked/classes3.dex */
final class Z3 extends AbstractC0158m2 implements g4 {
    final /* synthetic */ a4 b;

    @Override // j$.util.stream.g4
    public final long g() {
        return 0L;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Z3(a4 a4Var, InterfaceC0182r2 interfaceC0182r2, boolean z) {
        super(interfaceC0182r2);
        this.b = a4Var;
    }

    @Override // j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
    public final void accept(long j) {
        this.b.getClass();
        LongPredicate longPredicate = null;
        longPredicate.test(j);
        throw null;
    }
}
