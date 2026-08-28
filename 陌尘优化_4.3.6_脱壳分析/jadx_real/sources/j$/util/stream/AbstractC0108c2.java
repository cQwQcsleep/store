package j$.util.stream;

import java.util.function.Consumer;

/* renamed from: j$.util.stream.c2, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0108c2 extends X1 implements W1 {
    long b;

    public /* synthetic */ void accept(double d) {
        A0.a();
        throw null;
    }

    public /* synthetic */ void accept(int i) {
        A0.k();
        throw null;
    }

    public /* synthetic */ void accept(long j) {
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

    @Override // j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        this.b = 0L;
    }
}
