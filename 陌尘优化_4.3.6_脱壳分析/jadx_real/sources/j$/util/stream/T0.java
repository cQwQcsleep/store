package j$.util.stream;

import java.util.concurrent.CountedCompleter;
import java.util.function.BinaryOperator;
import java.util.function.LongFunction;

/* loaded from: /workspace/unpacked/classes3.dex */
class T0 extends AbstractC0115e {
    protected final AbstractC0100b h;
    protected final LongFunction i;
    protected final BinaryOperator j;

    @Override // j$.util.stream.AbstractC0115e, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        AbstractC0115e abstractC0115e = this.d;
        if (abstractC0115e != null) {
            f((M0) this.j.apply((M0) ((T0) abstractC0115e).c(), (M0) ((T0) this.e).c()));
        }
        super.onCompletion(countedCompleter);
    }

    T0(AbstractC0100b abstractC0100b, j$.util.U u, LongFunction longFunction, BinaryOperator binaryOperator) {
        super(abstractC0100b, u);
        this.h = abstractC0100b;
        this.i = longFunction;
        this.j = binaryOperator;
    }

    T0(T0 t0, j$.util.U u) {
        super(t0, u);
        this.h = t0.h;
        this.i = t0.i;
        this.j = t0.j;
    }

    @Override // j$.util.stream.AbstractC0115e
    protected AbstractC0115e e(j$.util.U u) {
        return new T0(this, u);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // j$.util.stream.AbstractC0115e
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public final M0 a() {
        E0 e0 = (E0) this.i.apply(this.h.G(this.b));
        this.h.V(this.b, e0);
        return e0.a();
    }
}
