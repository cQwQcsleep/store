package j$.util.stream;

import java.util.function.IntPredicate;

/* loaded from: /workspace/unpacked/classes3.dex */
final class V3 extends AbstractC0153l2 implements g4 {
    final /* synthetic */ W3 b;

    @Override // j$.util.stream.g4
    public final long g() {
        return 0L;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    V3(W3 w3, InterfaceC0182r2 interfaceC0182r2, boolean z) {
        super(interfaceC0182r2);
        this.b = w3;
    }

    @Override // j$.util.stream.InterfaceC0173p2, j$.util.stream.InterfaceC0182r2
    public final void accept(int i) {
        this.b.getClass();
        IntPredicate intPredicate = null;
        intPredicate.test(i);
        throw null;
    }
}
