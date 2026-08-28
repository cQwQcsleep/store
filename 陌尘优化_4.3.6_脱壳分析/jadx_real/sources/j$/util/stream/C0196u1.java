package j$.util.stream;

import java.util.function.DoubleConsumer;

/* renamed from: j$.util.stream.u1, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0196u1 extends AbstractC0216y1 implements InterfaceC0168o2 {
    private final double[] h;

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final /* bridge */ /* synthetic */ void q(Object obj) {
        q((Double) obj);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.d(this, doubleConsumer);
    }

    @Override // j$.util.stream.InterfaceC0168o2
    public final /* synthetic */ void q(Double d) {
        A0.e(this, d);
    }

    C0196u1(j$.util.U u, AbstractC0100b abstractC0100b, double[] dArr) {
        super(u, abstractC0100b, dArr.length);
        this.h = dArr;
    }

    C0196u1(C0196u1 c0196u1, j$.util.U u, long j, long j2) {
        super(c0196u1, u, j, j2, c0196u1.h.length);
        this.h = c0196u1.h;
    }

    @Override // j$.util.stream.AbstractC0216y1
    final AbstractC0216y1 b(j$.util.U u, long j, long j2) {
        return new C0196u1(this, u, j, j2);
    }

    @Override // j$.util.stream.AbstractC0216y1, j$.util.stream.InterfaceC0182r2
    public final void accept(double d) {
        int i = this.f;
        if (i >= this.g) {
            throw new IndexOutOfBoundsException(Integer.toString(this.f));
        }
        double[] dArr = this.h;
        this.f = i + 1;
        dArr[i] = d;
    }
}
