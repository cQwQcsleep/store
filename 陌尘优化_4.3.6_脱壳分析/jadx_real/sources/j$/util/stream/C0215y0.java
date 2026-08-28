package j$.util.stream;

import java.util.function.Supplier;

/* renamed from: j$.util.stream.y0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0215y0 implements M3 {
    final EnumC0210x0 a;
    final Supplier b;

    C0215y0(EnumC0134h3 enumC0134h3, EnumC0210x0 enumC0210x0, Supplier supplier) {
        this.a = enumC0210x0;
        this.b = supplier;
    }

    @Override // j$.util.stream.M3
    public final int d() {
        return EnumC0129g3.u | EnumC0129g3.r;
    }

    @Override // j$.util.stream.M3
    public final Object b(AbstractC0100b abstractC0100b, j$.util.U u) {
        AbstractC0205w0 abstractC0205w0 = (AbstractC0205w0) this.b.get();
        abstractC0100b.V(u, abstractC0205w0);
        return Boolean.valueOf(abstractC0205w0.b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // j$.util.stream.M3
    public final Object c(AbstractC0100b abstractC0100b, j$.util.U u) {
        return (Boolean) new C0220z0(this, abstractC0100b, u).invoke();
    }
}
