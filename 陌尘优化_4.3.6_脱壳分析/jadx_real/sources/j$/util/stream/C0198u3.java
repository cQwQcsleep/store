package j$.util.stream;

import j$.util.function.LongConsumer$CC;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* renamed from: j$.util.stream.u3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0198u3 implements InterfaceC0178q2 {
    public final /* synthetic */ int a;
    public final /* synthetic */ LongConsumer b;

    public /* synthetic */ C0198u3(LongConsumer longConsumer, int i) {
        this.a = i;
        this.b = longConsumer;
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

    @Override // j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
    public final void accept(long j) {
        switch (this.a) {
            case 0:
                ((Y2) this.b).accept(j);
                break;
            default:
                this.b.accept(j);
                break;
        }
    }

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final /* bridge */ /* synthetic */ void q(Object obj) {
        switch (this.a) {
            case 0:
                k((Long) obj);
                break;
            default:
                k((Long) obj);
                break;
        }
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.a) {
        }
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        switch (this.a) {
        }
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // j$.util.stream.InterfaceC0178q2
    public final /* synthetic */ void k(Long l) {
        switch (this.a) {
            case 0:
                A0.i(this, l);
                break;
            default:
                A0.i(this, l);
                break;
        }
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
