package j$.util.stream;

import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/* renamed from: j$.util.stream.t0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0190t0 extends AbstractC0205w0 implements InterfaceC0173p2 {
    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        n((Integer) obj);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.com.android.tools.r8.a.e(this, intConsumer);
    }

    @Override // j$.util.stream.InterfaceC0173p2
    public final /* synthetic */ void n(Integer num) {
        A0.g(this, num);
    }

    @Override // j$.util.stream.AbstractC0205w0, j$.util.stream.InterfaceC0182r2
    public final void accept(int i) {
        if (this.a) {
            return;
        }
        IntPredicate intPredicate = null;
        intPredicate.test(i);
        throw null;
    }
}
