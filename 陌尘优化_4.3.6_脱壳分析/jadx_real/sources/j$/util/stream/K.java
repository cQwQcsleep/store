package j$.util.stream;

import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
abstract class K implements N3 {
    boolean a;
    Object b;

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

    @Override // java.util.function.Consumer
    /* renamed from: accept, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final void q(Object obj) {
        if (this.a) {
            return;
        }
        this.a = true;
        this.b = obj;
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final boolean o() {
        return this.a;
    }
}
