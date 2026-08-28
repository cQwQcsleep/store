package j$.util.stream;

import j$.util.Comparator;
import j$.util.Objects;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;

/* loaded from: /workspace/unpacked/classes3.dex */
final class M2 extends AbstractC0138i2 {
    private final boolean m;
    private final Comparator n;

    M2(AbstractC0143j2 abstractC0143j2) {
        super(abstractC0143j2, EnumC0129g3.q | EnumC0129g3.o, 0);
        this.m = true;
        this.n = Comparator.CC.a();
    }

    M2(AbstractC0143j2 abstractC0143j2, java.util.Comparator comparator) {
        super(abstractC0143j2, EnumC0129g3.q | EnumC0129g3.p, 0);
        this.m = false;
        this.n = (java.util.Comparator) Objects.requireNonNull(comparator);
    }

    @Override // j$.util.stream.AbstractC0100b
    public final InterfaceC0182r2 R(int i, InterfaceC0182r2 interfaceC0182r2) {
        Objects.requireNonNull(interfaceC0182r2);
        if (EnumC0129g3.SORTED.n(i) && this.m) {
            return interfaceC0182r2;
        }
        boolean zN = EnumC0129g3.SIZED.n(i);
        java.util.Comparator comparator = this.n;
        if (zN) {
            return new R2(interfaceC0182r2, comparator);
        }
        return new N2(interfaceC0182r2, comparator);
    }

    @Override // j$.util.stream.AbstractC0100b
    public final M0 O(AbstractC0100b abstractC0100b, j$.util.U u, IntFunction intFunction) {
        if (EnumC0129g3.SORTED.n(abstractC0100b.K()) && this.m) {
            return abstractC0100b.C(u, false, intFunction);
        }
        Object[] objArrP = abstractC0100b.C(u, true, intFunction).p(intFunction);
        Arrays.sort(objArrP, this.n);
        return new P0(objArrP);
    }
}
