package j$.util.stream;

import java.util.function.IntFunction;

/* loaded from: /workspace/unpacked/classes3.dex */
final class Y3 extends AbstractC0151l0 {
    @Override // j$.util.stream.AbstractC0100b
    final j$.util.U P(AbstractC0100b abstractC0100b, j$.util.U u) {
        return EnumC0129g3.ORDERED.n(abstractC0100b.K()) ? O(abstractC0100b, u, new C0118e2(8)).spliterator() : new l4((j$.util.N) abstractC0100b.X(u), 1);
    }

    @Override // j$.util.stream.AbstractC0100b
    final M0 O(AbstractC0100b abstractC0100b, j$.util.U u, IntFunction intFunction) {
        return (M0) new i4(this, abstractC0100b, u, intFunction).invoke();
    }

    @Override // j$.util.stream.AbstractC0100b
    final InterfaceC0182r2 R(int i, InterfaceC0182r2 interfaceC0182r2) {
        return new X3(this, interfaceC0182r2);
    }
}
