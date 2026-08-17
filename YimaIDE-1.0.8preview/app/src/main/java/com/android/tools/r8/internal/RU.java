package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0333y;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class RU {
    public static RU b() {
        return C1097an.a;
    }

    public abstract B1 a(C0245l1 c0245l1);

    public abstract RU a(C0333y c0333y, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2);

    public abstract void a(BiConsumer biConsumer);

    public final boolean a(C0333y c0333y, C0210g1 c0210g1, com.android.tools.r8.graph.B5 b5, Predicate predicate) {
        B1 b1A = a(c0210g1.getReference());
        if (!b1A.g()) {
            return false;
        }
        AbstractC3122yc0 abstractC3122yc0J = b1A.j();
        if (abstractC3122yc0J.b(c0333y, b5)) {
            return predicate.test(abstractC3122yc0J);
        }
        return false;
    }

    public int c() {
        throw new Kk0();
    }

    public boolean d() {
        return this instanceof C1778io;
    }

    public abstract boolean e();

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public C1778io a() {
        return null;
    }
}
