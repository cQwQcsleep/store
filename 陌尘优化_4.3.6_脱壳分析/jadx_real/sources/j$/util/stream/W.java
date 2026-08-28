package j$.util.stream;

import java.util.function.IntConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class W extends AbstractC0096a0 {
    public final /* synthetic */ int m;
    final /* synthetic */ Object n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ W(AbstractC0100b abstractC0100b, int i, Object obj, int i2) {
        super(abstractC0100b, i, 1);
        this.m = i2;
        this.n = obj;
    }

    @Override // j$.util.stream.AbstractC0100b
    final InterfaceC0182r2 R(int i, InterfaceC0182r2 interfaceC0182r2) {
        switch (this.m) {
            case 0:
                return new V(this, interfaceC0182r2, 1);
            case 1:
                return new Y(this, interfaceC0182r2);
            case 2:
                return new C0155m(this, interfaceC0182r2, 4);
            default:
                return new C0123f2(this, interfaceC0182r2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public W(AbstractC0101b0 abstractC0101b0, IntConsumer intConsumer) {
        super(abstractC0101b0, 0, 1);
        this.m = 0;
        this.n = intConsumer;
    }
}
