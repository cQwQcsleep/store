package j$.util.stream;

import j$.util.C0090n;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class M1 implements W1 {
    private boolean a;
    private Object b;
    final /* synthetic */ BinaryOperator c;

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ void accept(double d) {
        A0.a();
        throw null;
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ void accept(int i) {
        A0.k();
        throw null;
    }

    @Override // j$.util.stream.InterfaceC0182r2, j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
    public final /* synthetic */ void accept(long j) {
        A0.l();
        throw null;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ void l() {
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ boolean o() {
        return false;
    }

    M1(BinaryOperator binaryOperator) {
        this.c = binaryOperator;
    }

    @Override // j$.util.stream.W1
    public final void h(W1 w1) {
        M1 m1 = (M1) w1;
        if (m1.a) {
            return;
        }
        q(m1.b);
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        this.a = true;
        this.b = null;
    }

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final void q(Object obj) {
        if (this.a) {
            this.a = false;
            this.b = obj;
        } else {
            this.b = this.c.apply(this.b, obj);
        }
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.a ? C0090n.a() : C0090n.d(this.b);
    }
}
