package j$.util.stream;

import j$.util.C0093q;
import j$.util.function.LongConsumer$CC;
import java.util.function.LongConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class I extends K implements InterfaceC0178q2 {
    static final F c;
    static final F d;

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // j$.util.stream.K, j$.util.stream.InterfaceC0182r2, j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
    public final void accept(long j) {
        q(Long.valueOf(j));
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        if (this.a) {
            return C0093q.d(((Long) this.b).longValue());
        }
        return null;
    }

    static {
        EnumC0134h3 enumC0134h3 = EnumC0134h3.LONG_VALUE;
        c = new F(true, enumC0134h3, C0093q.a(), new r(3), new C0170p(6));
        d = new F(false, enumC0134h3, C0093q.a(), new r(3), new C0170p(6));
    }
}
