package j$.util.stream;

/* renamed from: j$.util.stream.w, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0204w extends AbstractC0151l0 {
    public final /* synthetic */ int m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0204w(AbstractC0100b abstractC0100b, int i, int i2) {
        super(abstractC0100b, i, 1);
        this.m = i2;
    }

    @Override // j$.util.stream.AbstractC0100b
    final InterfaceC0182r2 R(int i, InterfaceC0182r2 interfaceC0182r2) {
        switch (this.m) {
            case 0:
                return new C0184s(this, interfaceC0182r2, 3);
            case 1:
                return new X(0, interfaceC0182r2);
            case 2:
                return new V(this, interfaceC0182r2, 3);
            case 3:
                return new C0126g0(this, interfaceC0182r2, 1);
            case 4:
                return interfaceC0182r2;
            default:
                return new C0126g0(this, interfaceC0182r2, 4);
        }
    }
}
