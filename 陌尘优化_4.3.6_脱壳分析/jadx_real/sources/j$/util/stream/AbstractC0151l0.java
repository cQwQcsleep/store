package j$.util.stream;

/* renamed from: j$.util.stream.l0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0151l0 extends AbstractC0156m0 {
    public final /* synthetic */ int l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AbstractC0151l0(AbstractC0100b abstractC0100b, int i, int i2) {
        super(abstractC0100b, i);
        this.l = i2;
    }

    @Override // j$.util.stream.AbstractC0100b
    final boolean Q() {
        switch (this.l) {
            case 0:
                return true;
            default:
                return false;
        }
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final InterfaceC0130h unordered() {
        switch (this.l) {
            case 0:
                if (!L()) {
                    break;
                } else {
                    break;
                }
            default:
                if (!L()) {
                    break;
                } else {
                    break;
                }
        }
        return new C0204w(this, EnumC0129g3.r, 4);
    }

    @Override // j$.util.stream.AbstractC0100b, j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* bridge */ /* synthetic */ InterfaceC0171p0 parallel() {
        switch (this.l) {
            case 0:
                parallel();
                break;
            default:
                parallel();
                break;
        }
        return this;
    }

    @Override // j$.util.stream.AbstractC0100b, j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* bridge */ /* synthetic */ InterfaceC0171p0 sequential() {
        switch (this.l) {
            case 0:
                sequential();
                break;
            default:
                sequential();
                break;
        }
        return this;
    }

    @Override // j$.util.stream.AbstractC0100b, j$.util.stream.InterfaceC0130h
    public final /* bridge */ /* synthetic */ j$.util.U spliterator() {
        switch (this.l) {
        }
        return spliterator();
    }
}
