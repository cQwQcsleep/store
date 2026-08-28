package j$.util.stream;

import java.util.concurrent.CountedCompleter;

/* renamed from: j$.util.stream.d2, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0113d2 extends AbstractC0115e {
    private final A0 h;

    @Override // j$.util.stream.AbstractC0115e, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        AbstractC0115e abstractC0115e = this.d;
        if (abstractC0115e != null) {
            W1 w1 = (W1) ((C0113d2) abstractC0115e).c();
            w1.h((W1) ((C0113d2) this.e).c());
            f(w1);
        }
        super.onCompletion(countedCompleter);
    }

    C0113d2(A0 a0, AbstractC0100b abstractC0100b, j$.util.U u) {
        super(abstractC0100b, u);
        this.h = a0;
    }

    C0113d2(C0113d2 c0113d2, j$.util.U u) {
        super(c0113d2, u);
        this.h = c0113d2.h;
    }

    @Override // j$.util.stream.AbstractC0115e
    protected final AbstractC0115e e(j$.util.U u) {
        return new C0113d2(this, u);
    }

    @Override // j$.util.stream.AbstractC0115e
    protected final Object a() {
        AbstractC0100b abstractC0100b = this.a;
        W1 w1E0 = this.h.e0();
        abstractC0100b.V(this.b, w1E0);
        return w1E0;
    }
}
