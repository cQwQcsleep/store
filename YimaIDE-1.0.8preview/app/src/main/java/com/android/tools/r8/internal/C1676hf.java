package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.C1676hf;
import com.android.tools.r8.internal.Cl0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1676hf extends AbstractC1590gf implements Cif, InterfaceC1846jf {
    public static final /* synthetic */ boolean d = true;
    public boolean b;
    public final List c;

    public C1676hf(List list, boolean z) {
        boolean z2 = d;
        if (!z2 && !De0.a(new C3094yC(list)).noneMatch(new Predicate() { // from class: i4h
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C1676hf.a((Cl0) obj);
            }
        })) {
            x1f.a();
            throw null;
        }
        this.b = z;
        this.c = list;
        if (z2 || !a(list, z)) {
            return;
        }
        x01.a("Must use UnknownMethodState instead");
        throw null;
    }

    public final InterfaceC1846jf a(C0333y c0333y, com.android.tools.r8.graph.B2 b2, C1676hf c1676hf, AbstractC1589ge0 abstractC1589ge0) {
        C0333y c0333y2;
        AbstractC1589ge0 abstractC1589ge1;
        if (this.c.size() != c1676hf.c.size()) {
            if (d) {
                return C2969wk0.a;
            }
            x1f.a();
            return null;
        }
        int i = 1;
        if (c1676hf.b) {
            this.b = true;
        }
        int i2 = 0;
        if (this.c.size() <= b2.b().p0()) {
            c0333y2 = c0333y;
            abstractC1589ge1 = abstractC1589ge0;
            i = 0;
        } else {
            if (!d && this.c.size() != b2.b().p0() + 1) {
                x1f.a();
                return null;
            }
            Cl0 cl0 = (Cl0) this.c.get(0);
            Cl0 cl1 = (Cl0) c1676hf.c.get(0);
            List list = this.c;
            cl0.getClass();
            c0333y2 = c0333y;
            abstractC1589ge1 = abstractC1589ge0;
            list.set(0, cl0.a(c0333y2, cl1, null, abstractC1589ge1, Y1.a));
        }
        while (i < this.c.size()) {
            Cl0 cl2 = (Cl0) this.c.get(i);
            Cl0 cl3 = (Cl0) c1676hf.c.get(i);
            com.android.tools.r8.graph.I2 i3 = b2.b().f.b[i2];
            List list2 = this.c;
            cl2.getClass();
            list2.set(i, cl2.a(c0333y2, cl3, i3, abstractC1589ge1, Y1.a));
            if (!d && ((Cl0) this.c.get(i)).h()) {
                AbstractC2530rf abstractC2530rfC = ((Cl0) this.c.get(i)).c();
                abstractC2530rfC.getClass();
                if (abstractC2530rfC instanceof C2360pf) {
                    x1f.a();
                    return null;
                }
            }
            i++;
            i2++;
        }
        return a(this.c, this.b) ? C2969wk0.a : this;
    }

    @Override // com.android.tools.r8.internal.RO, com.android.tools.r8.internal.QO
    public final C1676hf b() {
        return this;
    }

    @Override // com.android.tools.r8.internal.RO, com.android.tools.r8.internal.QO
    public final Cif g() {
        return this;
    }

    public final boolean k() {
        return AbstractC3179zC.b(this.c, new EX() { // from class: h4h
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return ((Cl0) obj).g();
            }
        });
    }

    public static boolean a(List list, boolean z) {
        return z && AbstractC3179zC.a(list, new EX() { // from class: j4h
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return ((Cl0) obj).k();
            }
        });
    }

    @Override // com.android.tools.r8.internal.QO
    public final InterfaceC1846jf a() {
        ArrayList arrayList = new ArrayList(this.c.size());
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            arrayList.add(((Cl0) it.next()).l());
        }
        return new C1676hf(arrayList, this.b);
    }

    @Override // com.android.tools.r8.internal.QO
    public final QO a() {
        ArrayList arrayList = new ArrayList(this.c.size());
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            arrayList.add(((Cl0) it.next()).l());
        }
        return new C1676hf(arrayList, this.b);
    }

    public final void a(int i, Cl0 cl0) {
        if (!d && i != 0 && cl0.h()) {
            AbstractC2530rf abstractC2530rfC = cl0.c();
            abstractC2530rfC.getClass();
            if (abstractC2530rfC instanceof C2360pf) {
                x1f.a();
                return;
            }
        }
        this.c.set(i, cl0);
    }

    public static boolean a(Cl0 cl0) {
        if (!cl0.h()) {
            return false;
        }
        AbstractC2530rf abstractC2530rfC = cl0.c();
        abstractC2530rfC.getClass();
        return abstractC2530rfC instanceof C2360pf;
    }
}
