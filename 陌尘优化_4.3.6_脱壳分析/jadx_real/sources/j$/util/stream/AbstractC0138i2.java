package j$.util.stream;

/* renamed from: j$.util.stream.i2, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0138i2 extends AbstractC0143j2 {
    public final /* synthetic */ int l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AbstractC0138i2(AbstractC0100b abstractC0100b, int i, int i2) {
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
        return new C0128g2(this, EnumC0129g3.r, 1);
    }
}
