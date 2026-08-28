package j$.util.stream;

import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
abstract class Q implements M3, N3 {
    private final boolean a;

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
    public final /* synthetic */ void m(long j) {
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ boolean o() {
        return false;
    }

    protected Q(boolean z) {
        this.a = z;
    }

    @Override // j$.util.stream.M3
    public final int d() {
        if (this.a) {
            return 0;
        }
        return EnumC0129g3.r;
    }

    public final void e(AbstractC0100b abstractC0100b, j$.util.U u) {
        if (this.a) {
            new S(abstractC0100b, u, this).invoke();
        } else {
            new T(abstractC0100b, u, abstractC0100b.W(this)).invoke();
        }
    }
}
