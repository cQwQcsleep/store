package j$.util.stream;

import java.util.function.Supplier;

/* renamed from: j$.util.stream.q0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0176q0 implements Supplier {
    public final /* synthetic */ int a;
    public final /* synthetic */ EnumC0210x0 b;

    public /* synthetic */ C0176q0(EnumC0210x0 enumC0210x0, int i) {
        this.a = i;
        this.b = enumC0210x0;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        switch (this.a) {
            case 0:
                return new C0195u0(this.b);
            case 1:
                return new C0190t0(this.b);
            default:
                return new C0200v0(this.b);
        }
    }
}
