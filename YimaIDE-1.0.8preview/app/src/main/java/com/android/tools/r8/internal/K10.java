package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class K10 extends L10 implements EX {
    public static final K10 d = new K10(C2021lh.c, C1935kh.c);
    public final AbstractC2106mh b;
    public final AbstractC2106mh c;

    public K10(AbstractC2106mh abstractC2106mh, AbstractC2106mh abstractC2106mh2) {
        abstractC2106mh.getClass();
        this.b = abstractC2106mh;
        abstractC2106mh2.getClass();
        this.c = abstractC2106mh2;
        if (abstractC2106mh.compareTo(abstractC2106mh2) > 0 || abstractC2106mh == C1935kh.c || abstractC2106mh2 == C2021lh.c) {
            StringBuilder sb = new StringBuilder(16);
            abstractC2106mh.a(sb);
            sb.append("..");
            abstractC2106mh2.b(sb);
            w01.a("Invalid range: ".concat(sb.toString()));
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.EX
    public final boolean apply(Object obj) {
        ((Comparable) obj).getClass();
        return this.b.a() && !this.c.a();
    }

    @Override // com.android.tools.r8.internal.EX
    public final boolean equals(Object obj) {
        if (obj instanceof K10) {
            K10 k10 = (K10) obj;
            if (this.b.equals(k10.b) && this.c.equals(k10.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.c.hashCode() + (this.b.hashCode() * 31);
    }

    public final String toString() {
        AbstractC2106mh abstractC2106mh = this.b;
        AbstractC2106mh abstractC2106mh2 = this.c;
        StringBuilder sb = new StringBuilder(16);
        abstractC2106mh.a(sb);
        sb.append("..");
        abstractC2106mh2.b(sb);
        return sb.toString();
    }
}
