package j$.util.stream;

import java.util.function.Consumer;

/* renamed from: j$.util.stream.t, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0189t extends AbstractC0138i2 {
    public final /* synthetic */ int m;
    final /* synthetic */ Object n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0189t(AbstractC0100b abstractC0100b, int i, Object obj, int i2) {
        super(abstractC0100b, i, 1);
        this.m = i2;
        this.n = obj;
    }

    @Override // j$.util.stream.AbstractC0100b
    final InterfaceC0182r2 R(int i, InterfaceC0182r2 interfaceC0182r2) {
        switch (this.m) {
            case 0:
                return new C0184s(this, interfaceC0182r2, 0);
            case 1:
                return new V(this, interfaceC0182r2, 0);
            case 2:
                return new C0126g0(this, interfaceC0182r2, 0);
            case 3:
                return new C0155m(this, interfaceC0182r2, 1);
            case 4:
                return new C0155m(this, interfaceC0182r2, 2);
            case 5:
                return new C0155m(this, interfaceC0182r2, 3);
            default:
                return new C0150l(this, interfaceC0182r2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0189t(AbstractC0143j2 abstractC0143j2, Consumer consumer) {
        super(abstractC0143j2, 0, 1);
        this.m = 3;
        this.n = consumer;
    }
}
