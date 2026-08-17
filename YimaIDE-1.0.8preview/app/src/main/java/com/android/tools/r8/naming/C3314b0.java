package com.android.tools.r8.naming;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.internal.C2119mo;
import com.android.tools.r8.internal.PO;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.naming.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3314b0 extends AbstractC3316c0 {
    public static final /* synthetic */ boolean f = true;
    public final C3320e0 c;
    public final C3314b0 d;
    public final W e;

    public C3314b0(C3314b0 c3314b0, Function function, W w, C3320e0 c3320e0) {
        super(function);
        this.d = c3314b0;
        this.e = w;
        this.c = c3320e0;
    }

    public final H2 a(com.android.tools.r8.graph.H0 h0) {
        BiPredicate biPredicate = new BiPredicate() { // from class: jgg
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return this.b.b((H2) obj, (C0322w2) obj2);
            }
        };
        H2 h2D = d(h0.getReference());
        if (h2D != null) {
            return h2D;
        }
        Set setD = this.c.d(h0.getReference());
        if (setD != null && setD.size() == 1) {
            H2 h2 = (H2) setD.iterator().next();
            if (b(h2, h0.getReference())) {
                return h2;
            }
        }
        H2 h2A = this.e.a(h0, (C3312a0) c(h0.getReference()), biPredicate);
        if (f || h2A != null) {
            return h2A;
        }
        x1f.a();
        return null;
    }

    public final boolean b(H2 h2, C0322w2 c0322w2) {
        Set setA = a(h2, c0322w2);
        if (setA != null && setA.contains(new C2119mo(PO.a, c0322w2))) {
            return true;
        }
        if (!this.c.a(h2, c0322w2) && setA == null) {
            return true;
        }
        Set setD = this.c.d(c0322w2);
        return setD != null && setD.contains(h2);
    }

    public final H2 d(C0322w2 c0322w2) {
        C3314b0 c3314b0;
        C3312a0 c3312a0 = (C3312a0) b(c0322w2);
        H2 h2 = c3312a0 != null ? (H2) c3312a0.c.get(new C2119mo(PO.a, c0322w2)) : null;
        return (h2 != null || (c3314b0 = this.d) == null) ? h2 : c3314b0.d(c0322w2);
    }

    public final C3314b0 a(C3320e0 c3320e0) {
        return new C3314b0(this, this.b, this.e, c3320e0);
    }

    public final Set a(H2 h2, C0322w2 c0322w2) {
        C3314b0 c3314b0;
        C3312a0 c3312a0 = (C3312a0) b(c0322w2);
        Set set = c3312a0 != null ? (Set) c3312a0.d.get(h2) : null;
        return (set != null || (c3314b0 = this.d) == null) ? set : c3314b0.a(h2, c0322w2);
    }

    @Override // com.android.tools.r8.naming.AbstractC3316c0
    public final Object a(C0322w2 c0322w2) {
        C3314b0 c3314b0 = this.d;
        return new C3312a0(c3314b0 != null ? (C3312a0) c3314b0.c(c0322w2) : null);
    }
}
