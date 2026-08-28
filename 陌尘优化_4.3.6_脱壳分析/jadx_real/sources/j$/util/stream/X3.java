package j$.util.stream;

import java.util.function.LongPredicate;

/* loaded from: /workspace/unpacked/classes3.dex */
final class X3 extends AbstractC0158m2 {
    boolean b;
    final /* synthetic */ Y3 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    X3(Y3 y3, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.c = y3;
        this.b = true;
    }

    @Override // j$.util.stream.AbstractC0158m2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        this.a.m(-1L);
    }

    @Override // j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
    public final void accept(long j) {
        if (this.b) {
            this.c.getClass();
            LongPredicate longPredicate = null;
            longPredicate.test(j);
            throw null;
        }
    }

    @Override // j$.util.stream.AbstractC0158m2, j$.util.stream.InterfaceC0182r2
    public final boolean o() {
        return !this.b || this.a.o();
    }
}
