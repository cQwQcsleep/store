package j$.util.stream;

import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class J3 implements InterfaceC0182r2 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Consumer b;

    public /* synthetic */ J3(Consumer consumer, int i) {
        this.a = i;
        this.b = consumer;
    }

    private final /* synthetic */ void b(long j) {
    }

    private final /* synthetic */ void c(long j) {
    }

    private final /* synthetic */ void d() {
    }

    private final /* synthetic */ void e() {
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ void accept(double d) {
        switch (this.a) {
            case 0:
                A0.a();
                throw null;
            default:
                A0.a();
                throw null;
        }
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ void accept(int i) {
        switch (this.a) {
            case 0:
                A0.k();
                throw null;
            default:
                A0.k();
                throw null;
        }
    }

    @Override // j$.util.stream.InterfaceC0182r2, j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
    public final /* synthetic */ void accept(long j) {
        switch (this.a) {
            case 0:
                A0.l();
                throw null;
            default:
                A0.l();
                throw null;
        }
    }

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final void q(Object obj) {
        switch (this.a) {
            case 0:
                ((C0104b3) this.b).q(obj);
                break;
            default:
                this.b.q(obj);
                break;
        }
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.a) {
        }
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ void l() {
        int i = this.a;
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ void m(long j) {
        int i = this.a;
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ boolean o() {
        switch (this.a) {
        }
        return false;
    }
}
