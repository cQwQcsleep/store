package j$.util.stream;

/* loaded from: /workspace/unpacked/classes3.dex */
final class X extends AbstractC0153l2 {
    public final /* synthetic */ int b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ X(int i, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.b = i;
    }

    @Override // j$.util.stream.InterfaceC0173p2, j$.util.stream.InterfaceC0182r2
    public final void accept(int i) {
        switch (this.b) {
            case 0:
                this.a.accept(i);
                break;
            default:
                this.a.accept(i);
                break;
        }
    }
}
