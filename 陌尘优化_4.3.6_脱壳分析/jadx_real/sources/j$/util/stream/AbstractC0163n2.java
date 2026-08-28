package j$.util.stream;

import j$.util.Objects;
import java.util.function.Consumer;

/* renamed from: j$.util.stream.n2, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public abstract class AbstractC0163n2 implements InterfaceC0182r2 {
    protected final InterfaceC0182r2 a;

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

    @Override // j$.util.stream.InterfaceC0182r2, j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
    public final /* synthetic */ void accept(long j) {
        A0.l();
        throw null;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    public AbstractC0163n2(InterfaceC0182r2 interfaceC0182r2) {
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
