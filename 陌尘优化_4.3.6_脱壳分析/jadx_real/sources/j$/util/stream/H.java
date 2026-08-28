package j$.util.stream;

import j$.util.C0092p;
import java.util.function.IntConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class H extends K implements InterfaceC0173p2 {
    static final F c;
    static final F d;

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.com.android.tools.r8.a.e(this, intConsumer);
    }

    @Override // j$.util.stream.K, j$.util.stream.InterfaceC0182r2
    public final void accept(int i) {
        q(Integer.valueOf(i));
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        if (this.a) {
            return C0092p.d(((Integer) this.b).intValue());
        }
        return null;
    }

    static {
        EnumC0134h3 enumC0134h3 = EnumC0134h3.INT_VALUE;
        c = new F(true, enumC0134h3, C0092p.a(), new r(2), new C0170p(5));
        d = new F(false, enumC0134h3, C0092p.a(), new r(2), new C0170p(5));
    }
}
