package j$.util.stream;

import j$.util.Objects;
import java.util.Arrays;
import java.util.function.IntFunction;

/* loaded from: /workspace/unpacked/classes3.dex */
final class J2 extends A {
    @Override // j$.util.stream.AbstractC0100b
    public final InterfaceC0182r2 R(int i, InterfaceC0182r2 interfaceC0182r2) {
        Objects.requireNonNull(interfaceC0182r2);
        return EnumC0129g3.SORTED.n(i) ? interfaceC0182r2 : EnumC0129g3.SIZED.n(i) ? new O2(interfaceC0182r2) : new G2(interfaceC0182r2);
    }

    @Override // j$.util.stream.AbstractC0100b
    public final M0 O(AbstractC0100b abstractC0100b, j$.util.U u, IntFunction intFunction) {
        if (EnumC0129g3.SORTED.n(abstractC0100b.K())) {
            return abstractC0100b.C(u, false, intFunction);
        }
        double[] dArr = (double[]) ((G0) abstractC0100b.C(u, true, intFunction)).e();
        Arrays.sort(dArr);
        return new Z0(dArr);
    }
}
