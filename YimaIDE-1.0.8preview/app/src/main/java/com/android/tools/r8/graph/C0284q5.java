package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0336y2;
import com.android.tools.r8.graph.D0;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.E2;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC2647t1;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C1627h30;
import com.android.tools.r8.internal.C1975l7;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import com.android.tools.r8.utils.structural.x;
import defpackage.s2i;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.graph.q5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0284q5 {
    public static final /* synthetic */ boolean p = true;
    public final int a;
    public final C0333y b;
    public final AbstractC3148ys c;
    public final RJ d;
    public final D2[] e;
    public final C1627h30 f;
    public final C1627h30 g;
    public final C1627h30 h;
    public final C1627h30 i;
    public C1627h30 j;
    public final C1627h30 k;
    public final C1627h30 l;
    public final Map m;
    public H2 n;
    public final C0270o5 o;

    public C0284q5(C0333y<?> c0333y, C0284q5 c0284q5, RJ rj, Collection<D2> collection, Map<E2, H2> map, Collection<I2> collection2, Collection<C0322w2> collection3, Collection<C0245l1> collection4, Collection<H2> collection5, Collection<D0> collection6, Collection<C0336y2> collection7, int i, Ch0 ch0) {
        boolean z = p;
        if (!z && c0333y == null) {
            x1f.a();
            throw null;
        }
        if (!z && collection == null) {
            x1f.a();
            throw null;
        }
        if (!z && map == null) {
            x1f.a();
            throw null;
        }
        if (!z && collection2 == null) {
            x1f.a();
            throw null;
        }
        if (!z && collection3 == null) {
            x1f.a();
            throw null;
        }
        if (!z && collection4 == null) {
            x1f.a();
            throw null;
        }
        if (!z && collection5 == null) {
            x1f.a();
            throw null;
        }
        if (!z && collection6 == null) {
            x1f.a();
            throw null;
        }
        if (!z && collection7 == null) {
            x1f.a();
            throw null;
        }
        this.a = i;
        this.b = c0333y;
        this.c = c0333y.A();
        AbstractC3345r0 abstractC3345r0W = c0333y.w();
        this.d = rj;
        this.m = map;
        ch0.a("Sort strings");
        if (c0284q5 == null) {
            this.j = a(collection5, new s2i(), new Consumer() { // from class: a3i
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a((H2) obj);
                }
            }, i);
        } else {
            this.j = c0284q5.j;
            this.n = c0284q5.n;
        }
        final C1627h30 c1627h30 = this.j;
        Objects.requireNonNull(c1627h30);
        com.android.tools.r8.utils.structural.e eVar = new com.android.tools.r8.utils.structural.e(abstractC3345r0W, new ToIntFunction() { // from class: y2i
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return c1627h30.b((H2) obj);
            }
        });
        ch0.b();
        ch0.a("Sort types");
        final C1627h30 c1627h30A = a(collection2, a(eVar), new Consumer() { // from class: b3i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((I2) obj);
            }
        }, 0);
        this.g = c1627h30A;
        final C1627h30 c1627h31 = this.j;
        Objects.requireNonNull(c1627h31);
        com.android.tools.r8.utils.structural.g gVar = new com.android.tools.r8.utils.structural.g(abstractC3345r0W, new ToIntFunction() { // from class: y2i
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return c1627h31.b((H2) obj);
            }
        }, new ToIntFunction() { // from class: z2i
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return c1627h30A.b((I2) obj);
            }
        });
        ch0.b();
        ch0.a("Sort classes");
        this.e = a(collection, gVar);
        ch0.b();
        ch0.a("Sort protos");
        this.f = a(map.keySet(), a(gVar), new Consumer() { // from class: c3i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((E2) obj);
            }
        }, 0);
        ch0.b();
        ch0.a("Sort methods");
        this.h = a(collection3, a(gVar), new Consumer() { // from class: d3i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C0322w2) obj);
            }
        }, 0);
        ch0.b();
        ch0.a("Sort fields");
        this.i = a(collection4, a(gVar), new Consumer() { // from class: e3i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C0245l1) obj);
            }
        }, 0);
        ch0.b();
        ch0.a("Sort call-sites");
        this.k = a(collection6, a(gVar), new Consumer() { // from class: t2i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((D0) obj);
            }
        }, 0);
        ch0.b();
        ch0.a("Sort method handles");
        this.l = a(collection7, a(gVar), new Consumer() { // from class: x2i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C0336y2) obj);
            }
        }, 0);
        ch0.b();
        final C1627h30 c1627h32 = this.j;
        Objects.requireNonNull(c1627h32);
        this.o = new C0270o5(abstractC3345r0W, new ToIntFunction() { // from class: y2i
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return c1627h32.b((H2) obj);
            }
        }, new ToIntFunction() { // from class: z2i
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return c1627h30A.b((I2) obj);
            }
        }, this);
    }

    public final void a(List list) {
        if (!p && this.a != list.size()) {
            x1f.a();
            return;
        }
        if (list.isEmpty()) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            H2 h2 = (H2) it.next();
            if (h2 != null) {
                this.j.b(-1, h2);
            }
        }
        final C1975l7 c1975l7 = new C1975l7();
        this.j = a(this.j.keySet(), new s2i(), new Consumer() { // from class: w2i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c1975l7.a((H2) obj);
            }
        }, 0);
        if (p || this.n == null || ((H2) c1975l7.a()).a(this.n)) {
            return;
        }
        x1f.a();
    }

    public static Comparator a(final com.android.tools.r8.utils.structural.e eVar) {
        return new Comparator() { // from class: v2i
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((x) obj).a((x) obj2, eVar);
            }
        };
    }

    public final void a(H2 h2) {
        if (p || this.n == null) {
            this.n = h2;
        } else {
            x1f.a();
        }
    }

    public final void a(AbstractC0259n1 abstractC0259n1) {
        throw new C0613Ke("Index overflow for " + abstractC0259n1.getClass());
    }

    public static C1627h30 a(Collection collection, Comparator comparator, Consumer consumer, int i) {
        if (collection.isEmpty()) {
            return new C1627h30(16);
        }
        ArrayList arrayList = new ArrayList(collection);
        arrayList.sort(comparator);
        C1627h30 c1627h30 = new C1627h30(collection.size());
        c1627h30.b = -1;
        int i2 = 0;
        for (Object obj : arrayList) {
            if (i2 + i == 65536) {
                consumer.accept(obj);
            }
            c1627h30.b(i2, obj);
            i2++;
        }
        return c1627h30;
    }

    public final D2[] a(Collection collection, final com.android.tools.r8.utils.structural.g gVar) {
        final C0277p5 c0277p5 = new C0277p5(this.b.g());
        D2[] d2Arr = (D2[]) collection.toArray(D2.B);
        Arrays.sort(d2Arr, new Comparator() { // from class: u2i
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return C0284q5.a(c0277p5, gVar, (D2) obj, (D2) obj2);
            }
        });
        return d2Arr;
    }

    public static /* synthetic */ int a(C0277p5 c0277p5, AbstractC3519a abstractC3519a, D2 d2, D2 d3) {
        int iA = c0277p5.a(d2);
        int iA2 = c0277p5.a(d3);
        return iA != iA2 ? iA - iA2 : abstractC3519a.a(d2.e, d3.e);
    }

    public static Collection a(C1627h30 c1627h30) {
        return c1627h30 == null ? Collections.EMPTY_LIST : c1627h30.keySet();
    }

    public static int a(X3 x3, AbstractC2647t1 abstractC2647t1) {
        int iB = abstractC2647t1.b(x3);
        if (p || iB != -1) {
            return iB;
        }
        s22.a("Missing dependency: ", x3);
        return 0;
    }
}
