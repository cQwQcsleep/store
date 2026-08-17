package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1330dd extends AbstractC1501fd {
    public static final /* synthetic */ boolean d = true;
    public final AbstractC1501fd b;
    public final Set c;

    public C1330dd(com.android.tools.r8.graph.V v, AbstractC1501fd abstractC1501fd, AbstractC2554rv abstractC2554rv) {
        super(v);
        if (!d && (abstractC1501fd instanceof C1330dd)) {
            x01.a("Nested Filtering class providers");
            throw null;
        }
        this.b = abstractC1501fd;
        this.c = abstractC2554rv;
    }

    @Override // com.android.tools.r8.internal.AbstractC1501fd
    public final C1330dd a(Cc0 cc0) {
        int i = AbstractC2554rv.c;
        C1870jv c1870jv = new C1870jv();
        c1870jv.a((Iterable) this.c);
        Iterator it = cc0.iterator();
        while (true) {
            IC ic = (IC) it;
            if (!ic.hasNext()) {
                return new C1330dd(this.a, this.b, c1870jv.a());
            }
            c1870jv.a(ic.next());
        }
    }

    public final String toString() {
        return this.b + " without " + this.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC1501fd
    public final void a(com.android.tools.r8.graph.I2 i2, Consumer consumer) {
        if (this.c.contains(i2)) {
            return;
        }
        this.b.a(i2, consumer);
    }

    @Override // com.android.tools.r8.internal.AbstractC1501fd
    public final Collection a() {
        Collection collectionA = this.b.a();
        collectionA.removeAll(this.c);
        return collectionA;
    }
}
