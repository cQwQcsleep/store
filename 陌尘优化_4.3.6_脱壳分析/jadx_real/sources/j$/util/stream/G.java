package j$.util.stream;

import j$.util.C0091o;
import java.util.function.DoubleConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class G extends K implements InterfaceC0168o2 {
    static final F c;
    static final F d;

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.d(this, doubleConsumer);
    }

    @Override // j$.util.stream.K, j$.util.stream.InterfaceC0182r2
    public final void accept(double d2) {
        q(Double.valueOf(d2));
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        if (this.a) {
            return C0091o.d(((Double) this.b).doubleValue());
        }
        return null;
    }

    static {
        EnumC0134h3 enumC0134h3 = EnumC0134h3.DOUBLE_VALUE;
        c = new F(true, enumC0134h3, C0091o.a(), new r(1), new C0170p(4));
        d = new F(false, enumC0134h3, C0091o.a(), new r(1), new C0170p(4));
    }
}
