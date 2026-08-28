package j$.util.stream;

import java.util.function.DoublePredicate;

/* loaded from: /workspace/unpacked/classes3.dex */
final class d4 extends AbstractC0148k2 implements g4 {
    final /* synthetic */ e4 b;

    @Override // j$.util.stream.g4
    public final long g() {
        return 0L;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    d4(e4 e4Var, InterfaceC0182r2 interfaceC0182r2, boolean z) {
        super(interfaceC0182r2);
        this.b = e4Var;
    }

    @Override // j$.util.stream.InterfaceC0168o2, j$.util.stream.InterfaceC0182r2
    public final void accept(double d) {
        this.b.getClass();
        DoublePredicate doublePredicate = null;
        doublePredicate.test(d);
        throw null;
    }
}
