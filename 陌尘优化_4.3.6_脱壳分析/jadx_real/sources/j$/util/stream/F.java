package j$.util.stream;

import java.util.function.Predicate;
import java.util.function.Supplier;

/* loaded from: /workspace/unpacked/classes3.dex */
final class F implements M3 {
    final int a;
    final Object b;
    final Predicate c;
    final Supplier d;

    F(boolean z, EnumC0134h3 enumC0134h3, Object obj, Predicate predicate, Supplier supplier) {
        this.a = (z ? 0 : EnumC0129g3.r) | EnumC0129g3.u;
        this.b = obj;
        this.c = predicate;
        this.d = supplier;
    }

    @Override // j$.util.stream.M3
    public final int d() {
        return this.a;
    }

    @Override // j$.util.stream.M3
    public final Object b(AbstractC0100b abstractC0100b, j$.util.U u) {
        N3 n3 = (N3) this.d.get();
        abstractC0100b.V(u, n3);
        Object obj = n3.get();
        return obj != null ? obj : this.b;
    }

    @Override // j$.util.stream.M3
    public final Object c(AbstractC0100b abstractC0100b, j$.util.U u) {
        return new L(this, EnumC0129g3.ORDERED.n(abstractC0100b.K()), abstractC0100b, u).invoke();
    }
}
