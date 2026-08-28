package j$.util.stream;

import java.util.function.DoubleConsumer;

/* renamed from: j$.util.stream.y, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0214y extends A {
    public final /* synthetic */ int m;
    final /* synthetic */ Object n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0214y(AbstractC0100b abstractC0100b, int i, Object obj, int i2) {
        super(abstractC0100b, i, 1);
        this.m = i2;
        this.n = obj;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0214y(B b, DoubleConsumer doubleConsumer) {
        super(b, 0, 1);
        this.m = 1;
        this.n = doubleConsumer;
    }

    @Override // j$.util.stream.AbstractC0100b
    final InterfaceC0182r2 R(int i, InterfaceC0182r2 interfaceC0182r2) {
        switch (this.m) {
            case 0:
                return new C0209x(this, interfaceC0182r2);
            case 1:
                return new C0184s(this, interfaceC0182r2, 5);
            case 2:
                return new C0155m(this, interfaceC0182r2, 6);
            default:
                return new C0123f2(this, interfaceC0182r2);
        }
    }
}
