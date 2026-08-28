package j$.util.stream;

import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;

/* renamed from: j$.util.stream.v0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0200v0 extends AbstractC0205w0 implements InterfaceC0168o2 {
    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        q((Double) obj);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.d(this, doubleConsumer);
    }

    @Override // j$.util.stream.InterfaceC0168o2
    public final /* synthetic */ void q(Double d) {
        A0.e(this, d);
    }

    @Override // j$.util.stream.AbstractC0205w0, j$.util.stream.InterfaceC0182r2
    public final void accept(double d) {
        if (this.a) {
            return;
        }
        DoublePredicate doublePredicate = null;
        doublePredicate.test(d);
        throw null;
    }
}
