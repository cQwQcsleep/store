package j$.util.stream;

import java.util.function.Consumer;

/* renamed from: j$.util.stream.w0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0205w0 implements InterfaceC0182r2 {
    boolean a;
    boolean b;

    @Override // j$.util.stream.InterfaceC0182r2
    public /* synthetic */ void accept(double d) {
        A0.a();
        throw null;
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public /* synthetic */ void accept(int i) {
        A0.k();
        throw null;
    }

    @Override // j$.util.stream.InterfaceC0182r2, j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
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
    public final /* synthetic */ void m(long j) {
    }

    AbstractC0205w0(EnumC0210x0 enumC0210x0) {
        this.b = !enumC0210x0.b;
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final boolean o() {
        return this.a;
    }
}
