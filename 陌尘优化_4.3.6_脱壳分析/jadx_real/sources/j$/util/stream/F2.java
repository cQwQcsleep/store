package j$.util.stream;

import java.util.Comparator;

/* loaded from: /workspace/unpacked/classes3.dex */
abstract class F2 extends AbstractC0163n2 {
    protected final Comparator b;
    protected boolean c;

    F2(InterfaceC0182r2 interfaceC0182r2, Comparator comparator) {
        super(interfaceC0182r2);
        this.b = comparator;
    }

    @Override // j$.util.stream.AbstractC0163n2, j$.util.stream.InterfaceC0182r2
    public final boolean o() {
        this.c = true;
        return false;
    }
}
