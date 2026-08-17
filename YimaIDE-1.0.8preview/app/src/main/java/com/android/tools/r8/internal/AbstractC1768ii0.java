package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0191d3;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0235j5;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0311u5;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.shaking.C3403i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ii0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1768ii0 {
    public static final /* synthetic */ boolean e = true;
    public final C0333y a;
    public final com.android.tools.r8.graph.B1 b;
    public final IdentityHashMap c = new IdentityHashMap();
    public final IdentityHashMap d = new IdentityHashMap();

    public AbstractC1768ii0(C0333y c0333y) {
        this.a = c0333y;
        this.b = c0333y.a();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r2v0 com.android.tools.r8.graph.D2, still in use, count: 2, list:
          (r2v0 com.android.tools.r8.graph.D2) from 0x009a: PHI (r2v1 com.android.tools.r8.graph.D2) = (r2v0 com.android.tools.r8.graph.D2), (r2v7 com.android.tools.r8.graph.D2) binds: [B:25:0x008b, B:32:0x00cf] A[DONT_GENERATE, DONT_INLINE]
          (r2v0 com.android.tools.r8.graph.D2) from 0x0080: MOVE (r20v4 com.android.tools.r8.graph.D2) = (r2v0 com.android.tools.r8.graph.D2)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:93)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:59)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    public final com.android.tools.r8.graph.D2 a(final com.android.tools.r8.graph.D2 r27) {
        /*
            Method dump skipped, instruction units count: 405
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.tools.r8.internal.AbstractC1768ii0.a(com.android.tools.r8.graph.D2):com.android.tools.r8.graph.D2");
    }

    public abstract void a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3);

    public abstract void a(C0245l1 c0245l1, C0245l1 c0245l2);

    public abstract void a(C0322w2 c0322w2, C0322w2 c0322w3);

    public abstract com.android.tools.r8.graph.I2 b(com.android.tools.r8.graph.I2 i2);

    public final List b(List list) {
        if (!list.isEmpty()) {
            ArrayList arrayList = new ArrayList(list.size());
            Iterator it = list.iterator();
            boolean z = false;
            while (it.hasNext()) {
                com.android.tools.r8.graph.I2 i2A = ((C0235j5) it.next()).a();
                com.android.tools.r8.graph.I2 i2A2 = a(i2A);
                arrayList.add(new C0235j5(i2A2));
                z |= i2A2 != i2A;
            }
            if (z) {
                return arrayList;
            }
        }
        return list;
    }

    public final List c(List list) {
        if (!list.isEmpty()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(list.size());
            Iterator it = list.iterator();
            boolean z = false;
            while (it.hasNext()) {
                com.android.tools.r8.graph.I2 i2A = ((C0311u5) it.next()).a();
                com.android.tools.r8.graph.I2 i2A2 = a(i2A);
                linkedHashSet.add(i2A2);
                z |= i2A2 != i2A;
            }
            if (z) {
                ArrayList arrayList = new ArrayList(linkedHashSet.size());
                Iterator it2 = linkedHashSet.iterator();
                while (it2.hasNext()) {
                    arrayList.add(new C0311u5((com.android.tools.r8.graph.I2) it2.next()));
                }
                return arrayList;
            }
        }
        return list;
    }

    public final /* synthetic */ void b(C0245l1 c0245l1) {
        C0245l1 c0245l1A = a(c0245l1);
        if (c0245l1 != c0245l1A) {
            a(c0245l1, c0245l1A);
        }
    }

    public final /* synthetic */ void b(C0322w2 c0322w2) {
        C0322w2 c0322w2A = a(c0322w2);
        if (c0322w2 != c0322w2A) {
            a(c0322w2, c0322w2A);
        }
    }

    public final C0191d3 a(C0191d3 c0191d3) {
        if (c0191d3 == null) {
            return null;
        }
        com.android.tools.r8.graph.I2 i2 = c0191d3.a;
        if (i2 != null) {
            com.android.tools.r8.graph.I2 i2A = a(i2);
            return i2A != i2 ? new C0191d3(i2A) : c0191d3;
        }
        C0322w2 c0322w2B = c0191d3.b();
        if (e || c0322w2B != null) {
            C0322w2 c0322w2A = a(c0191d3.b());
            return c0322w2A != c0322w2B ? new C0191d3(c0322w2A) : c0191d3;
        }
        x1f.a();
        return null;
    }

    public final void a() {
        if (this.a.g().i()) {
            C3403i c3403iM = this.a.g().m();
            c3403iM.o.forEach(new Consumer() { // from class: z8h
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.b((C0245l1) obj);
                }
            });
            c3403iM.n.forEach(new Consumer() { // from class: a9h
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.b((C0322w2) obj);
                }
            });
        }
    }

    public final ArrayList a(Collection collection) {
        ArrayList arrayList = new ArrayList();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            final com.android.tools.r8.graph.D2 d2 = (com.android.tools.r8.graph.D2) it.next();
            arrayList.add((com.android.tools.r8.graph.D2) this.c.computeIfAbsent(d2.getType(), new Function() { // from class: y8h
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.a(d2, (I2) obj);
                }
            }));
        }
        return arrayList;
    }

    public final /* synthetic */ com.android.tools.r8.graph.D2 a(com.android.tools.r8.graph.D2 d2, com.android.tools.r8.graph.I2 i2) {
        return a(d2);
    }

    public final com.android.tools.r8.graph.H4 a(com.android.tools.r8.graph.D2 d2, com.android.tools.r8.graph.E0 e0) {
        com.android.tools.r8.graph.H4 h4V = d2.V();
        return new com.android.tools.r8.graph.H4(e0, h4V.b.a(new Function() { // from class: c9h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a((C0231j1) obj);
            }
        }));
    }

    public final C0210g1[] a(Consumer consumer, List list) {
        if (list == null) {
            return C0210g1.o;
        }
        C0210g1[] c0210g1Arr = new C0210g1[list.size()];
        for (int i = 0; i < list.size(); i++) {
            C0210g1 c0210g1 = (C0210g1) list.get(i);
            C0245l1 reference = c0210g1.getReference();
            C0245l1 c0245l1A = a(reference);
            if (c0245l1A != reference) {
                C0210g1 c0210g1A = c0210g1.a(this.a, c0245l1A, consumer);
                a(c0210g1.getReference(), c0210g1A.getReference());
                c0210g1 = c0210g1A;
            }
            c0210g1Arr[i] = c0210g1;
        }
        return c0210g1Arr;
    }

    public C0245l1 a(C0245l1 c0245l1) {
        com.android.tools.r8.graph.I2 i2A = a(c0245l1.i);
        return this.b.a(a(c0245l1.f), i2A, c0245l1.g);
    }

    public final List a(List list) {
        if (!list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            boolean z = false;
            while (it.hasNext()) {
                com.android.tools.r8.graph.Y3 y3 = (com.android.tools.r8.graph.Y3) it.next();
                com.android.tools.r8.graph.I2 i2B = y3.b();
                com.android.tools.r8.graph.I2 i2A = i2B != null ? a(i2B) : null;
                com.android.tools.r8.graph.I2 i2D = y3.d();
                com.android.tools.r8.graph.I2 i2A2 = i2D != null ? a(i2D) : null;
                com.android.tools.r8.graph.H2 h2C = y3.c();
                boolean z2 = (i2A == i2B && i2A2 == i2D) ? false : true;
                if (z2 && i2B != null && i2D != null && y3.c() != null) {
                    String strB = C0929Wj.b(i2A2.G0(), i2A.G0());
                    if (strB != null) {
                        h2C = this.b.c(strB);
                    } else {
                        boolean z3 = e;
                        if (!z3 && this.a.M().g0()) {
                            x1f.a();
                            return null;
                        }
                        if (!z3 && this.a.g().c(i2A2) != null) {
                            x1f.a();
                            return null;
                        }
                    }
                }
                arrayList.add(new com.android.tools.r8.graph.Y3(y3.a(), i2A, i2A2, h2C));
                z |= z2;
            }
            if (z) {
                return arrayList;
            }
        }
        return list;
    }

    public final C0231j1 a(C0231j1 c0231j1) {
        C0322w2 reference = c0231j1.getReference();
        C0322w2 c0322w2A = a(reference);
        if (reference.a(c0322w2A)) {
            return c0231j1;
        }
        com.android.tools.r8.graph.B1 b1 = this.b;
        c0231j1.O0();
        C0231j1 c0231j1A = c0231j1.a(c0322w2A, b1, (Consumer) null);
        a(c0231j1.getReference(), c0231j1A.getReference());
        return c0231j1A;
    }

    public C0322w2 a(C0322w2 c0322w2) {
        return this.b.a(a(c0322w2.f), a(c0322w2.i), c0322w2.g);
    }

    public final com.android.tools.r8.graph.E2 a(com.android.tools.r8.graph.E2 e2) {
        com.android.tools.r8.graph.E2 e3 = (com.android.tools.r8.graph.E2) this.d.get(e2);
        if (e3 != null) {
            return e3;
        }
        com.android.tools.r8.graph.I2 i2A = a(e2.e);
        com.android.tools.r8.graph.I2[] i2Arr = e2.f.b;
        int length = i2Arr.length;
        com.android.tools.r8.graph.I2[] i2Arr2 = new com.android.tools.r8.graph.I2[length];
        boolean z = false;
        for (int i = 0; i < length; i++) {
            com.android.tools.r8.graph.I2 i2 = i2Arr[i];
            com.android.tools.r8.graph.I2 i2A2 = a(i2);
            i2Arr2[i] = i2A2;
            z |= i2A2 != i2;
        }
        if (z) {
            i2Arr = i2Arr2;
        }
        com.android.tools.r8.graph.E2 e2A = this.b.a(i2A, i2Arr);
        this.d.put(e2, e2A);
        return e2A;
    }

    public com.android.tools.r8.graph.I2 a(com.android.tools.r8.graph.I2 i2) {
        if (i2.I0()) {
            com.android.tools.r8.graph.I2 i2A = i2.a(this.b);
            com.android.tools.r8.graph.I2 i2A2 = a(i2A);
            if (i2A != i2A2) {
                return i2.a(this.b, i2A2);
            }
        } else if (i2.M0()) {
            return b(i2);
        }
        return i2;
    }
}
