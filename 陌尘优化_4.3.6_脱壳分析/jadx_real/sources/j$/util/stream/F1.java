package j$.util.stream;

import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class F1 implements W1, InterfaceC0168o2 {
    private double a;
    final /* synthetic */ double b;
    final /* synthetic */ DoubleBinaryOperator c;

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

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final /* bridge */ /* synthetic */ void q(Object obj) {
        q((Double) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.d(this, doubleConsumer);
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ void l() {
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ boolean o() {
        return false;
    }

    @Override // j$.util.stream.InterfaceC0168o2
    public final /* synthetic */ void q(Double d) {
        A0.e(this, d);
    }

    F1(double d, DoubleBinaryOperator doubleBinaryOperator) {
        this.b = d;
        this.c = doubleBinaryOperator;
    }

    @Override // j$.util.stream.W1
    public final void h(W1 w1) {
        accept(((F1) w1).a);
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        this.a = this.b;
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final void accept(double d) {
        this.a = this.c.applyAsDouble(this.a, d);
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return Double.valueOf(this.a);
    }
}
