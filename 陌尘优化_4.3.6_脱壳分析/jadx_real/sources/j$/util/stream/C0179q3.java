package j$.util.stream;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* renamed from: j$.util.stream.q3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0179q3 implements InterfaceC0168o2 {
    public final /* synthetic */ int a;
    public final /* synthetic */ DoubleConsumer b;

    public /* synthetic */ C0179q3(DoubleConsumer doubleConsumer, int i) {
        this.a = i;
        this.b = doubleConsumer;
    }

    private final /* synthetic */ void b(long j) {
    }

    private final /* synthetic */ void c(long j) {
    }

    private final /* synthetic */ void d() {
    }

    private final /* synthetic */ void e() {
    }

    @Override // j$.util.stream.InterfaceC0168o2, j$.util.stream.InterfaceC0182r2
    public final void accept(double d) {
        switch (this.a) {
            case 0:
                ((U2) this.b).accept(d);
                break;
            default:
                this.b.accept(d);
                break;
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
    public final /* bridge */ /* synthetic */ void q(Object obj) {
        switch (this.a) {
            case 0:
                q((Double) obj);
                break;
            default:
                q((Double) obj);
                break;
        }
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.a) {
        }
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        switch (this.a) {
        }
        return j$.com.android.tools.r8.a.d(this, doubleConsumer);
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

    @Override // j$.util.stream.InterfaceC0168o2
    public final /* synthetic */ void q(Double d) {
        switch (this.a) {
            case 0:
                A0.e(this, d);
                break;
            default:
                A0.e(this, d);
                break;
        }
    }
}
