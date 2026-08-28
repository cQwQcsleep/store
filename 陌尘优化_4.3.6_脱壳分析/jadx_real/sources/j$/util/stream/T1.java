package j$.util.stream;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

/* loaded from: /workspace/unpacked/classes3.dex */
final class T1 extends X1 implements W1, InterfaceC0173p2 {
    final /* synthetic */ Supplier b;
    final /* synthetic */ ObjIntConsumer c;
    final /* synthetic */ C0175q d;

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ void accept(double d) {
        A0.a();
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
        n((Integer) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.com.android.tools.r8.a.e(this, intConsumer);
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ void l() {
    }

    @Override // j$.util.stream.InterfaceC0173p2
    public final /* synthetic */ void n(Integer num) {
        A0.g(this, num);
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ boolean o() {
        return false;
    }

    @Override // j$.util.stream.W1
    public final void h(W1 w1) {
        this.a = this.d.apply(this.a, ((T1) w1).a);
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        this.a = this.b.get();
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final void accept(int i) {
        this.c.accept(this.a, i);
    }

    T1(Supplier supplier, ObjIntConsumer objIntConsumer, C0175q c0175q) {
        this.b = supplier;
        this.c = objIntConsumer;
        this.d = c0175q;
    }
}
