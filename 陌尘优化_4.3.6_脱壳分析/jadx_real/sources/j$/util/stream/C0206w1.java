package j$.util.stream;

import j$.util.function.LongConsumer$CC;
import java.util.function.LongConsumer;

/* renamed from: j$.util.stream.w1, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0206w1 extends AbstractC0216y1 implements InterfaceC0178q2 {
    private final long[] h;

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final /* bridge */ /* synthetic */ void q(Object obj) {
        k((Long) obj);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // j$.util.stream.InterfaceC0178q2
    public final /* synthetic */ void k(Long l) {
        A0.i(this, l);
    }

    C0206w1(j$.util.U u, AbstractC0100b abstractC0100b, long[] jArr) {
        super(u, abstractC0100b, jArr.length);
        this.h = jArr;
    }

    C0206w1(C0206w1 c0206w1, j$.util.U u, long j, long j2) {
        super(c0206w1, u, j, j2, c0206w1.h.length);
        this.h = c0206w1.h;
    }

    @Override // j$.util.stream.AbstractC0216y1
    final AbstractC0216y1 b(j$.util.U u, long j, long j2) {
        return new C0206w1(this, u, j, j2);
    }

    @Override // j$.util.stream.AbstractC0216y1, j$.util.stream.InterfaceC0182r2, j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
    public final void accept(long j) {
        int i = this.f;
        if (i >= this.g) {
            throw new IndexOutOfBoundsException(Integer.toString(this.f));
        }
        long[] jArr = this.h;
        this.f = i + 1;
        jArr[i] = j;
    }
}
