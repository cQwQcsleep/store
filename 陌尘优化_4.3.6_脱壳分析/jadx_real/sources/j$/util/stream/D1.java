package j$.util.stream;

import j$.util.function.LongConsumer$CC;
import java.util.function.Consumer;
import java.util.function.LongConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* loaded from: /workspace/unpacked/classes3.dex */
final class D1 extends X1 implements W1, InterfaceC0178q2 {
    final /* synthetic */ Supplier b;
    final /* synthetic */ ObjLongConsumer c;
    final /* synthetic */ C0175q d;

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

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final /* bridge */ /* synthetic */ void q(Object obj) {
        k((Long) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // j$.util.stream.InterfaceC0178q2
    public final /* synthetic */ void k(Long l) {
        A0.i(this, l);
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ void l() {
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ boolean o() {
        return false;
    }

    @Override // j$.util.stream.W1
    public final void h(W1 w1) {
        this.a = this.d.apply(this.a, ((D1) w1).a);
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        this.a = this.b.get();
    }

    @Override // j$.util.stream.InterfaceC0182r2, j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
    public final void accept(long j) {
        this.c.accept(this.a, j);
    }

    D1(Supplier supplier, ObjLongConsumer objLongConsumer, C0175q c0175q) {
        this.b = supplier;
        this.c = objLongConsumer;
        this.d = c0175q;
    }
}
