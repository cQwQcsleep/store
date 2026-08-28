package j$.util.stream;

import java.util.function.LongConsumer;

/* renamed from: j$.util.stream.j0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0141j0 extends AbstractC0151l0 {
    public final /* synthetic */ int m;
    final /* synthetic */ Object n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0141j0(AbstractC0100b abstractC0100b, int i, Object obj, int i2) {
        super(abstractC0100b, i, 1);
        this.m = i2;
        this.n = obj;
    }

    @Override // j$.util.stream.AbstractC0100b
    final InterfaceC0182r2 R(int i, InterfaceC0182r2 interfaceC0182r2) {
        switch (this.m) {
            case 0:
                return new C0136i0(this, interfaceC0182r2);
            case 1:
                return new C0126g0(this, interfaceC0182r2, 5);
            case 2:
                return new C0123f2(this, interfaceC0182r2);
            default:
                return new C0155m(this, interfaceC0182r2, 5);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0141j0(AbstractC0156m0 abstractC0156m0, LongConsumer longConsumer) {
        super(abstractC0156m0, 0, 1);
        this.m = 1;
        this.n = longConsumer;
    }
}
