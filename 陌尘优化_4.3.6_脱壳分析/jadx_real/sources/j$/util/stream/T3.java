package j$.util.stream;

import java.util.function.IntPredicate;

/* loaded from: /workspace/unpacked/classes3.dex */
final class T3 extends AbstractC0153l2 {
    boolean b;
    final /* synthetic */ U3 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    T3(U3 u3, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.c = u3;
        this.b = true;
    }

    @Override // j$.util.stream.AbstractC0153l2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        this.a.m(-1L);
    }

    @Override // j$.util.stream.InterfaceC0173p2, j$.util.stream.InterfaceC0182r2
    public final void accept(int i) {
        if (this.b) {
            this.c.getClass();
            IntPredicate intPredicate = null;
            intPredicate.test(i);
            throw null;
        }
    }

    @Override // j$.util.stream.AbstractC0153l2, j$.util.stream.InterfaceC0182r2
    public final boolean o() {
        return !this.b || this.a.o();
    }
}
