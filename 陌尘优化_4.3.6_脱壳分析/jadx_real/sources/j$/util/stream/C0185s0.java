package j$.util.stream;

import java.util.function.Predicate;

/* renamed from: j$.util.stream.s0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0185s0 extends AbstractC0205w0 {
    final /* synthetic */ EnumC0210x0 c;
    final /* synthetic */ Predicate d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0185s0(EnumC0210x0 enumC0210x0, Predicate predicate) {
        super(enumC0210x0);
        this.c = enumC0210x0;
        this.d = predicate;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        if (this.a) {
            return;
        }
        boolean zTest = this.d.test(obj);
        EnumC0210x0 enumC0210x0 = this.c;
        if (zTest == enumC0210x0.a) {
            this.a = true;
            this.b = enumC0210x0.b;
        }
    }
}
