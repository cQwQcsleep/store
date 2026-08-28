package j$.util.stream;

/* loaded from: /workspace/unpacked/classes3.dex */
abstract class A extends B {
    public final /* synthetic */ int l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ A(AbstractC0100b abstractC0100b, int i, int i2) {
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
        return new C0194u(this, EnumC0129g3.r, 1);
    }

    @Override // j$.util.stream.AbstractC0100b, j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* bridge */ /* synthetic */ E parallel() {
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
    public final /* bridge */ /* synthetic */ E sequential() {
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
