package j$.util.stream;

import j$.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* renamed from: j$.util.stream.k2, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public abstract class AbstractC0148k2 implements InterfaceC0168o2 {
    protected final InterfaceC0182r2 a;

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
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        q((Double) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.d(this, doubleConsumer);
    }

    @Override // j$.util.stream.InterfaceC0168o2
    public final /* synthetic */ void q(Double d) {
        A0.e(this, d);
    }

    public AbstractC0148k2(InterfaceC0182r2 interfaceC0182r2) {
        this.a = (InterfaceC0182r2) Objects.requireNonNull(interfaceC0182r2);
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public void m(long j) {
        this.a.m(j);
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public void l() {
        this.a.l();
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public boolean o() {
        return this.a.o();
    }
}
