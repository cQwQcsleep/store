package j$.util.stream;

import j$.util.Objects;
import j$.util.function.LongConsumer$CC;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* renamed from: j$.util.stream.m2, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public abstract class AbstractC0158m2 implements InterfaceC0178q2 {
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

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
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

    public AbstractC0158m2(InterfaceC0182r2 interfaceC0182r2) {
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
