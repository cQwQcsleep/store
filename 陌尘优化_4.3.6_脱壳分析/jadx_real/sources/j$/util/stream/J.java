package j$.util.stream;

import j$.util.C0090n;

/* loaded from: /workspace/unpacked/classes3.dex */
final class J extends K {
    static final F c;
    static final F d;

    @Override // java.util.function.Supplier
    public final Object get() {
        if (this.a) {
            return C0090n.d(this.b);
        }
        return null;
    }

    static {
        EnumC0134h3 enumC0134h3 = EnumC0134h3.REFERENCE;
        c = new F(true, enumC0134h3, C0090n.a(), new r(4), new C0170p(7));
        d = new F(false, enumC0134h3, C0090n.a(), new r(4), new C0170p(7));
    }
}
