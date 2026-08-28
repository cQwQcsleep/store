package j$.util.stream;

import java.util.function.IntConsumer;

/* renamed from: j$.util.stream.v1, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0201v1 extends AbstractC0216y1 implements InterfaceC0173p2 {
    private final int[] h;

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final /* bridge */ /* synthetic */ void q(Object obj) {
        n((Integer) obj);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.com.android.tools.r8.a.e(this, intConsumer);
    }

    @Override // j$.util.stream.InterfaceC0173p2
    public final /* synthetic */ void n(Integer num) {
        A0.g(this, num);
    }

    C0201v1(j$.util.U u, AbstractC0100b abstractC0100b, int[] iArr) {
        super(u, abstractC0100b, iArr.length);
        this.h = iArr;
    }

    C0201v1(C0201v1 c0201v1, j$.util.U u, long j, long j2) {
        super(c0201v1, u, j, j2, c0201v1.h.length);
        this.h = c0201v1.h;
    }

    @Override // j$.util.stream.AbstractC0216y1
    final AbstractC0216y1 b(j$.util.U u, long j, long j2) {
        return new C0201v1(this, u, j, j2);
    }

    @Override // j$.util.stream.AbstractC0216y1, j$.util.stream.InterfaceC0182r2
    public final void accept(int i) {
        int i2 = this.f;
        if (i2 >= this.g) {
            throw new IndexOutOfBoundsException(Integer.toString(this.f));
        }
        int[] iArr = this.h;
        this.f = i2 + 1;
        iArr[i2] = i;
    }
}
