package com.android.tools.r8.naming;

import com.android.tools.r8.graph.H0;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.internal.AbstractC1597gi0;
import com.android.tools.r8.internal.C0386Bl;
import com.android.tools.r8.internal.C1341di0;
import com.android.tools.r8.internal.C1512fi0;
import com.android.tools.r8.internal.IM;
import com.android.tools.r8.naming.I;
import com.android.tools.r8.naming.J;
import defpackage.jc4;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class I implements Comparable {
    public final HashSet b = new HashSet();
    public final C0386Bl c = C0386Bl.k();
    public final ArrayList d = new ArrayList();
    public final /* synthetic */ K e;

    public I(K k) {
        this.e = k;
    }

    public final H2 a() {
        H2 h2 = null;
        if (this.c.b.isEmpty()) {
            return null;
        }
        for (com.android.tools.r8.graph.H0 h0 : this.c.i()) {
            C0386Bl c0386Bl = this.c;
            Iterator it = ((Set) c0386Bl.b.get(c0386Bl.b(h0))).iterator();
            while (it.hasNext()) {
                H2 h2A = ((J) it.next()).a(h0);
                if (h0.getReference().x0().b(h2A)) {
                    return h0.getReference().x0();
                }
                if (h2A != null) {
                    h2 = h2A;
                }
            }
        }
        return h2;
    }

    public final /* synthetic */ void b(H2 h2, com.android.tools.r8.graph.H0 h0, J j) {
        H2 h2A = j.a(h0);
        if (h2A != null) {
            j.c(h0, h2A);
            this.e.c.a(h0, h2A);
        } else {
            j.c(h0, h2);
            this.e.c.a(h0, h2);
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return ((I) obj).c.b.size() - this.c.b.size();
    }

    public final void b(final H2 h2) {
        a(new BiConsumer() { // from class: jd6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.b(h2, (H0) obj, (J) obj2);
            }
        });
    }

    public final void a(I i) {
        this.b.addAll(i.b);
        this.d.addAll(i.d);
        i.c.forEach(new BiConsumer() { // from class: hd6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a((H0) obj, (Set) obj2);
            }
        });
    }

    public final void a(com.android.tools.r8.graph.H0 h0, Set set) {
        C0386Bl c0386Bl = this.c;
        ((Set) c0386Bl.a(IM.a(new jc4()), c0386Bl.b(h0))).addAll(set);
    }

    public final void a(com.android.tools.r8.graph.H0 h0, J j) {
        C0386Bl c0386Bl = this.c;
        ((Set) c0386Bl.a(IM.a(new jc4()), c0386Bl.b(h0))).add(j);
    }

    public final boolean a(final H2 h2) {
        Boolean bool = (Boolean) a(new BiFunction() { // from class: kd6
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return I.a(h2, (H0) obj, (J) obj2);
            }
        });
        return bool == null || bool.booleanValue();
    }

    public static /* synthetic */ Boolean a(H2 h2, com.android.tools.r8.graph.H0 h0, J j) {
        if (j.b(h0, h2)) {
            return null;
        }
        return Boolean.FALSE;
    }

    public final void a(final H2 h2, final Y y) {
        a(new BiConsumer() { // from class: dd6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                I.a(h2, y, (H0) obj, (J) obj2);
            }
        });
    }

    public static /* synthetic */ void a(H2 h2, Y y, com.android.tools.r8.graph.H0 h0, J j) {
        j.a(h0, h2);
        y.a(h0, h2);
    }

    public final void a(final BiConsumer biConsumer) {
        a(new BiFunction() { // from class: id6
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return I.a(biConsumer, (H0) obj, (J) obj2);
            }
        });
    }

    public static /* synthetic */ Object a(BiConsumer biConsumer, com.android.tools.r8.graph.H0 h0, J j) {
        biConsumer.accept(h0, j);
        return null;
    }

    public final Object a(final BiFunction biFunction) {
        AbstractC1597gi0 abstractC1597gi0A = this.c.a(new BiFunction() { // from class: ld6
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return I.a(biFunction, (H0) obj, (Set) obj2);
            }
        });
        if (abstractC1597gi0A.c()) {
            return abstractC1597gi0A.a().e();
        }
        return null;
    }

    public static AbstractC1597gi0 a(BiFunction biFunction, com.android.tools.r8.graph.H0 h0, Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Object objApply = biFunction.apply(h0, (J) it.next());
            if (objApply != null) {
                return new C1341di0(objApply);
            }
        }
        return C1512fi0.c;
    }
}
