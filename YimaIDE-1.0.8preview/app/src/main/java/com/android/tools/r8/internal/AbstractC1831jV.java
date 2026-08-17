package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jV, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1831jV {
    public static final /* synthetic */ boolean a = true;

    public abstract AbstractC1831jV a(com.android.tools.r8.graph.I5 i5, Ch0 ch0);

    public abstract AbstractC1831jV a(AbstractC3148ys abstractC3148ys, Ch0 ch0);

    public abstract boolean a();

    public abstract boolean a(com.android.tools.r8.graph.E0 e0);

    public final boolean a(final C0333y c0333y, Supplier supplier, AbstractC2624sj0 abstractC2624sj0) {
        if (!abstractC2624sj0.w()) {
            return true;
        }
        C2441qd c2441qdB = abstractC2624sj0.b();
        if (c2441qdB.Q() != c0333y.a().a2 || c2441qdB.b.e()) {
            return true;
        }
        IA iaR = c2441qdB.R();
        Predicate predicate = new Predicate() { // from class: rdh
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.a(c0333y, (I2) obj);
            }
        };
        BU it = iaR.a.g().iterator();
        while (it.hasNext()) {
            I20 i20 = (I20) it.next();
            if (i20.getBooleanValue() && !predicate.test((com.android.tools.r8.graph.I2) i20.getKey())) {
                AbstractC0439Dm abstractC0439Dm = (AbstractC0439Dm) supplier.get();
                if (abstractC0439Dm.k()) {
                    return true;
                }
                if (abstractC0439Dm.l()) {
                    return false;
                }
                AbstractC2624sj0 abstractC2624sj0A = abstractC0439Dm.a(abstractC2624sj0);
                if (abstractC2624sj0A.r()) {
                    return abstractC2624sj0A.b(abstractC2624sj0, c0333y);
                }
                if (abstractC2624sj0A.w()) {
                    C2441qd c2441qdB2 = abstractC2624sj0A.b();
                    if (c2441qdB2.Q() != c0333y.a().a2) {
                        return c2441qdB2.b(abstractC2624sj0, c0333y);
                    }
                    return false;
                }
                if (a) {
                    return false;
                }
                x1f.a();
                return false;
            }
        }
        return true;
    }

    public final boolean a(final C0333y c0333y, final C2543rl0 c2543rl0) {
        return a(c0333y, new Supplier() { // from class: qdh
            @Override // java.util.function.Supplier
            public final Object get() {
                return c2543rl0.a(c0333y);
            }
        }, c2543rl0.t());
    }

    public final /* synthetic */ boolean a(C0333y c0333y, com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.E0 e0D = c0333y.d(i2);
        return e0D != null && a(e0D);
    }
}
