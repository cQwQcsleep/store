package j$.util.stream;

/* renamed from: j$.util.stream.v, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0199v extends AbstractC0096a0 {
    public final /* synthetic */ int m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0199v(AbstractC0100b abstractC0100b, int i, int i2) {
        super(abstractC0100b, i, 1);
        this.m = i2;
    }

    @Override // j$.util.stream.AbstractC0100b
    final InterfaceC0182r2 R(int i, InterfaceC0182r2 interfaceC0182r2) {
        switch (this.m) {
            case 0:
                return new C0184s(this, interfaceC0182r2, 2);
            case 1:
                return new V(this, interfaceC0182r2, 2);
            case 2:
                return interfaceC0182r2;
            case 3:
                return new V(this, interfaceC0182r2, 5);
            default:
                return new C0126g0(this, interfaceC0182r2, 2);
        }
    }
}
