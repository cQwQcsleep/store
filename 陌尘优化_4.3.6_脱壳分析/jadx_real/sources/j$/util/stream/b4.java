package j$.util.stream;

import java.util.function.DoublePredicate;

/* loaded from: /workspace/unpacked/classes3.dex */
final class b4 extends AbstractC0148k2 {
    boolean b;
    final /* synthetic */ c4 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    b4(c4 c4Var, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.c = c4Var;
        this.b = true;
    }

    @Override // j$.util.stream.AbstractC0148k2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        this.a.m(-1L);
    }

    @Override // j$.util.stream.InterfaceC0168o2, j$.util.stream.InterfaceC0182r2
    public final void accept(double d) {
        if (this.b) {
            this.c.getClass();
            DoublePredicate doublePredicate = null;
            doublePredicate.test(d);
            throw null;
        }
    }

    @Override // j$.util.stream.AbstractC0148k2, j$.util.stream.InterfaceC0182r2
    public final boolean o() {
        return !this.b || this.a.o();
    }
}
