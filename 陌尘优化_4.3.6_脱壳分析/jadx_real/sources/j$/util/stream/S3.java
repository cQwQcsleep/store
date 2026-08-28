package j$.util.stream;

import java.util.function.IntFunction;
import java.util.function.Predicate;

/* loaded from: /workspace/unpacked/classes3.dex */
final class S3 extends AbstractC0138i2 implements f4 {
    final /* synthetic */ Predicate m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S3(AbstractC0143j2 abstractC0143j2, int i, Predicate predicate) {
        super(abstractC0143j2, i, 0);
        this.m = predicate;
    }

    @Override // j$.util.stream.AbstractC0100b
    final j$.util.U P(AbstractC0100b abstractC0100b, j$.util.U u) {
        return EnumC0129g3.ORDERED.n(abstractC0100b.K()) ? O(abstractC0100b, u, new r(29)).spliterator() : new m4(abstractC0100b.X(u), this.m, 0);
    }

    @Override // j$.util.stream.AbstractC0100b
    final M0 O(AbstractC0100b abstractC0100b, j$.util.U u, IntFunction intFunction) {
        return (M0) new h4(this, abstractC0100b, u, intFunction).invoke();
    }

    @Override // j$.util.stream.AbstractC0100b
    final InterfaceC0182r2 R(int i, InterfaceC0182r2 interfaceC0182r2) {
        return new R3(this, interfaceC0182r2, false);
    }

    @Override // j$.util.stream.f4
    public final g4 k(E0 e0, boolean z) {
        return new R3(this, e0, z);
    }
}
