package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC1597gi0;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.C1341di0;
import com.android.tools.r8.internal.C1405eW;
import com.android.tools.r8.internal.C1512fi0;
import com.android.tools.r8.internal.C1755ib0;
import com.android.tools.r8.internal.C1819jJ;
import com.android.tools.r8.internal.C2098md;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.internal.IA;
import com.android.tools.r8.internal.InterfaceC1853ji0;
import com.android.tools.r8.internal.InterfaceC1938ki0;
import com.android.tools.r8.synthesis.C3492a;
import defpackage.q9h;
import defpackage.w9h;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.graph.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0229j extends C0215h {
    public static final /* synthetic */ boolean i = true;
    public final C2098md g;
    public final com.android.tools.r8.shaking.X1 h;

    public C0229j(C0215h c0215h) {
        super(c0215h.a, c0215h.d, c0215h.c, c0215h.e);
        this.g = C2098md.a();
        this.h = new com.android.tools.r8.shaking.X1(AbstractC2780ub0.c());
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0068  */
    /* JADX WARN: Code duplicated, block: B:28:0x006a  */
    public final boolean a(C0231j1 c0231j1, I2 i2) {
        boolean zEquals;
        E0 e0D = d(i2);
        if (e0D == null) {
            return false;
        }
        if (!i && !e0D.isInterface()) {
            x1f.a();
            return false;
        }
        for (C0231j1 c0231j2 : e0D.G1()) {
            if (c0231j2.getReference().d(c0231j1.getReference())) {
                F4 accessFlags = c0231j2.getAccessFlags();
                if (accessFlags.e() != c0231j1.getAccessFlags().e()) {
                    zEquals = false;
                } else if (accessFlags.m()) {
                    zEquals = true;
                } else if (!accessFlags.i()) {
                    if (!C0231j1.x && !accessFlags.q()) {
                        x1f.a();
                        return false;
                    }
                    zEquals = c0231j2.E0().E0().equals(c0231j1.E0().E0());
                } else if (c0231j2.E0() == c0231j1.E0()) {
                    zEquals = true;
                } else {
                    zEquals = false;
                }
                if (zEquals) {
                    return true;
                }
            }
        }
        for (I2 i3 : e0D.h.b) {
            if (a(c0231j1, i3)) {
                return true;
            }
        }
        return false;
    }

    public final AbstractC1597gi0 b(E0 e0, InterfaceC1938ki0 interfaceC1938ki0) {
        E0 e0D = e0;
        int length = 0;
        while (e0D != null) {
            length += e0D.h.b.length;
            I2 i2 = e0D.g;
            if (i2 == null) {
                break;
            }
            AbstractC1597gi0 abstractC1597gi0 = (AbstractC1597gi0) interfaceC1938ki0.a(i2, e0D, Boolean.FALSE);
            if (abstractC1597gi0.c()) {
                return abstractC1597gi0;
            }
            e0D = d(e0D.g);
        }
        if (length == 0) {
            return C1512fi0.c;
        }
        Set setC = AbstractC2780ub0.c();
        ArrayDeque arrayDeque = new ArrayDeque();
        while (e0 != null) {
            for (I2 i3 : e0.h.b) {
                if (setC.add(i3)) {
                    AbstractC1597gi0 abstractC1597gi1 = (AbstractC1597gi0) interfaceC1938ki0.a(i3, e0, Boolean.TRUE);
                    if (abstractC1597gi1.c()) {
                        return abstractC1597gi1;
                    }
                    arrayDeque.addLast(i3);
                }
            }
            I2 i4 = e0.g;
            if (i4 == null) {
                break;
            }
            e0 = d(i4);
        }
        while (!arrayDeque.isEmpty()) {
            E0 e0D2 = d((I2) arrayDeque.removeFirst());
            if (e0D2 != null) {
                for (I2 i5 : e0D2.h.b) {
                    if (setC.add(i5)) {
                        AbstractC1597gi0 abstractC1597gi2 = (AbstractC1597gi0) interfaceC1938ki0.a(i5, e0D2, Boolean.TRUE);
                        if (abstractC1597gi2.c()) {
                            return abstractC1597gi2;
                        }
                        arrayDeque.addLast(i5);
                    }
                }
            }
        }
        return C1512fi0.c;
    }

    public final boolean c(E0 e0, E0 e1) {
        if (e1.isInterface()) {
            return c(e0.getType(), e1.getType());
        }
        boolean z = i;
        if (!z && e0 == null) {
            x1f.a();
            return false;
        }
        if (!z && e1.isInterface()) {
            x1f.a();
            return false;
        }
        if (e0.isInterface()) {
            if (e1.getType() == a().a2) {
                return true;
            }
        } else if (e0 == e1 || b(e0, e1)) {
            return true;
        }
        return false;
    }

    public final T4 d(E0 e0, E2 e2, H2 h2) {
        if (!i) {
            c();
        }
        return O4.a(a(), new w9h(this)).b(e0, e2, h2);
    }

    public T4 e(C0322w2 c0322w2) {
        if (!i) {
            c();
        }
        return new O4(new q9h(this), a(), true, true).a(c0322w2);
    }

    public T4 f(C0322w2 c0322w2) {
        if (!i) {
            c();
        }
        return O4.a(a(), new w9h(this)).a(c0322w2);
    }

    /* JADX WARN: Code duplicated, block: B:82:0x013b  */
    public IA h(I2 i2) {
        boolean z;
        if (!i && !i2.M0()) {
            x1f.a();
            return null;
        }
        E0 e0D = d(i2);
        if (e0D == null) {
            return IA.b;
        }
        if (e0D.g == a().a2 && e0D.h.isEmpty()) {
            return e0D.isInterface() ? IA.c(i2) : IA.b;
        }
        IA.a aVarA = IA.a();
        if (e0D.isInterface()) {
            aVarA.a(i2, true);
        }
        Set setC = AbstractC2780ub0.c();
        ArrayDeque arrayDeque = new ArrayDeque();
        while (e0D != null) {
            Iterator<I2> it = e0D.h.iterator();
            while (it.hasNext()) {
                I2 next = it.next();
                if (!setC.contains(next)) {
                    I2 type = e0D.getType();
                    C2752uB c2752uBJ = j();
                    c2752uBJ.getClass();
                    boolean z2 = (c2752uBJ.a(EnumC3077y2.u) && type == c2752uBJ.s().w2 && next == c2752uBJ.s().v2) ? false : true;
                    aVarA.a(next, z2);
                    if (z2) {
                        setC.add(next);
                    }
                    E0 e0D2 = d(next);
                    if (e0D2 != null && !e0D2.h.isEmpty()) {
                        arrayDeque.add(new C1405eW(e0D2, Boolean.valueOf(z2)));
                    }
                }
            }
            I2 i3 = e0D.g;
            if (i3 == null || i3 == j().s().a2) {
                break;
            }
            e0D = d(e0D.g);
        }
        while (!arrayDeque.isEmpty()) {
            C1405eW c1405eW = (C1405eW) arrayDeque.poll();
            E0 e0 = (E0) c1405eW.a();
            if (!i && e0.h.isEmpty()) {
                x1f.a();
                return null;
            }
            for (I2 i4 : e0.h) {
                if (!setC.contains(i4)) {
                    if (((Boolean) c1405eW.b()).booleanValue()) {
                        I2 type2 = e0.getType();
                        C2752uB c2752uBJ2 = j();
                        c2752uBJ2.getClass();
                        if (c2752uBJ2.a(EnumC3077y2.u) && type2 == c2752uBJ2.s().w2 && i4 == c2752uBJ2.s().v2) {
                            z = false;
                        } else {
                            z = true;
                        }
                    } else {
                        z = false;
                    }
                    aVarA.a(i4, z);
                    if (z) {
                        setC.add(i4);
                    }
                    E0 e0D3 = d(i4);
                    if (e0D3 != null && !e0D3.h.isEmpty()) {
                        arrayDeque.add(new C1405eW(e0D3, Boolean.valueOf(z)));
                    }
                }
            }
        }
        return aVarA.a();
    }

    public final boolean i(I2 i2) {
        E0 e0D = d(i2);
        if (e0D == null) {
            return true;
        }
        I2 i3 = e0D.g;
        if (i3 != null && i(i3)) {
            return true;
        }
        for (I2 i4 : e0D.h.b) {
            if (i(i4)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.graph.C0215h
    public final C0229j l() {
        if (!i) {
            c();
        }
        return this;
    }

    public final C2098md n() {
        return this.g;
    }

    public final com.android.tools.r8.shaking.X1 o() {
        return this.h;
    }

    public void p() {
    }

    public T4 d(C0322w2 c0322w2) {
        if (!i) {
            c();
        }
        return c(c0322w2.w0(), c0322w2);
    }

    public T4 f(E0 e0, C0322w2 c0322w2) {
        if (!i) {
            c();
        }
        if (e0.isInterface()) {
            return e(e0, c0322w2);
        }
        return d(e0, c0322w2);
    }

    public T4 d(E0 e0, C0322w2 c0322w2) {
        if (!i) {
            c();
        }
        return b(e0, c0322w2.C0(), c0322w2.x0());
    }

    public T4 e(E0 e0, C0322w2 c0322w2) {
        if (!i) {
            c();
        }
        return d(e0, c0322w2.C0(), c0322w2.x0());
    }

    public C0229j(C3492a c3492a, C2098md c2098md, com.android.tools.r8.shaking.R1 r1, com.android.tools.r8.shaking.X1 x1) {
        super(c3492a, r1);
        this.g = c2098md;
        this.h = x1;
    }

    public boolean c(I2 i2, I2 i3) {
        boolean z = i;
        if (!z && i2 == null) {
            x1f.a();
            return false;
        }
        if (!z && i3 == null) {
            x1f.a();
            return false;
        }
        if (!z && !i2.M0()) {
            x1f.a();
            return false;
        }
        if (z || i3.M0()) {
            return i2 == i3 || b(i2, i3);
        }
        x1f.a();
        return false;
    }

    public final T4 c(E0 e0, C0322w2 c0322w2) {
        O4 o4A = O4.a(a(), new w9h(this));
        L4 l4 = new L4(o4A.b, o4A.a);
        o4A.a(c0322w2.C0(), c0322w2.x0(), e0, l4, e0.g, Arrays.asList(e0.h.b));
        return l4.a(e0);
    }

    public T4 c(C0322w2 c0322w2) {
        if (!i) {
            c();
        }
        return a(c0322w2.w0(), c0322w2);
    }

    public T4 c(I2 i2, C0322w2 c0322w2) {
        if (!i) {
            c();
        }
        return O4.a(a(), new w9h(this)).b(i2, c0322w2.C0(), c0322w2.x0());
    }

    public final T4 c(E0 e0, E2 e2, H2 h2) {
        if (!i) {
            c();
        }
        return new O4(new q9h(this), a(), true, true).b(e0, e2, h2);
    }

    public AbstractC0330x3 c(C0245l1 c0245l1) {
        if (!i) {
            c();
        }
        return a(c0245l1.f, c0245l1);
    }

    @Override // com.android.tools.r8.graph.C0215h
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public C0229j a(I5 i5, ExecutorService executorService, Ch0 ch0) {
        boolean z = i;
        if (!z && getClass() != C0229j.class) {
            x1f.a();
            return null;
        }
        if (!z) {
            c();
        }
        if (!z && i5.a != b()) {
            x1f.a();
            return null;
        }
        if (i5.d()) {
            return this;
        }
        ch0.a("Pruning AppInfoWithClassHierarchy");
        com.android.tools.r8.synthesis.J jG = g();
        C0229j c0229j = new C0229j(com.android.tools.r8.synthesis.J.a(i5, jG.d, jG.e, jG.c, jG.a, jG.f), this.g.a(i5), f().a(i5), this.h);
        ch0.b();
        return c0229j;
    }

    public static C0229j a(AbstractC0327x0 abstractC0327x0, C2098md c2098md, com.android.tools.r8.shaking.R1 r1, com.android.tools.r8.synthesis.E e) {
        return new C0229j(com.android.tools.r8.synthesis.J.a(abstractC0327x0, e), c2098md, r1, new com.android.tools.r8.shaking.X1(AbstractC2780ub0.c()));
    }

    public final C0229j a(C3492a c3492a) {
        return new C0229j(c3492a, this.g, f(), this.h);
    }

    @Override // com.android.tools.r8.graph.C0215h
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public C0229j a(com.android.tools.r8.shaking.R1 r1) {
        boolean z = i;
        if (!z && getClass() != C0229j.class) {
            x1f.a();
            return null;
        }
        if (!z) {
            c();
        }
        return new C0229j(g().a(b()), this.g, r1, this.h);
    }

    public final C0229j a(Function function) {
        if (!i) {
            c();
        }
        return new C0229j(g().a((AbstractC0327x0) function.apply(b())), this.g, f(), this.h);
    }

    public boolean b(I2 i2, final I2 i3) {
        E0 e0D;
        boolean z = i;
        if (!z && i2 == null) {
            x1f.a();
            return false;
        }
        if (!z && i3 == null) {
            x1f.a();
            return false;
        }
        if (!z && !i2.M0()) {
            x1f.a();
            return false;
        }
        if (!z && !i3.M0()) {
            x1f.a();
            return false;
        }
        if (i2 == i3 || i2 == a().a2) {
            return false;
        }
        if (i3 == a().a2) {
            return true;
        }
        if (i2.M0() && i3.M0() && (e0D = d(i2)) != null) {
            return b(e0D, new InterfaceC1938ki0() { // from class: iah
                @Override // com.android.tools.r8.internal.InterfaceC1938ki0
                public final Object a(Object obj, Object obj2, Object obj3) {
                    return C0229j.a(i3, (I2) obj, (E0) obj2, (Boolean) obj3);
                }
            }).c();
        }
        return false;
    }

    public final AbstractC1597gi0 a(E0 e0, InterfaceC1938ki0 interfaceC1938ki0) {
        while (e0 != null && e0.d1() != null) {
            E0 e0D = d(e0.d1());
            AbstractC1597gi0 abstractC1597gi0 = (AbstractC1597gi0) interfaceC1938ki0.a(e0.d1(), e0D, e0);
            if (abstractC1597gi0.c()) {
                return abstractC1597gi0;
            }
            e0 = e0D;
        }
        return C1512fi0.c;
    }

    public final void a(D2 d2, final InterfaceC1853ji0 interfaceC1853ji0) {
        b(d2, new InterfaceC1938ki0() { // from class: fah
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj, Object obj2, Object obj3) {
                return C0229j.a(interfaceC1853ji0, (I2) obj, (E0) obj2, (Boolean) obj3);
            }
        });
    }

    public static /* synthetic */ AbstractC1597gi0 a(InterfaceC1853ji0 interfaceC1853ji0, I2 i2, E0 e0, Boolean bool) {
        interfaceC1853ji0.a(i2, e0, bool);
        return C1512fi0.c;
    }

    public static /* synthetic */ AbstractC1597gi0 a(I2 i2, I2 i3, E0 e0, Boolean bool) {
        return i3 == i2 ? C1341di0.c : C1512fi0.c;
    }

    public final boolean b(E0 e0, final E0 e1) {
        boolean z = i;
        if (!z && e0 == null) {
            x1f.a();
            return false;
        }
        if (!z && e1 == null) {
            x1f.a();
            return false;
        }
        if (!z && e0.isInterface()) {
            x1f.a();
            return false;
        }
        if (!z && e1.isInterface()) {
            x1f.a();
            return false;
        }
        if (e0 == e1 || e0.getType() == a().a2) {
            return false;
        }
        if (e1.getType() == a().a2) {
            return true;
        }
        AbstractC1597gi0 abstractC1597gi0A = a(e0, new InterfaceC1938ki0() { // from class: bah
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj, Object obj2, Object obj3) {
                return C0229j.a(e1, (I2) obj, (E0) obj2, (E0) obj3);
            }
        });
        return abstractC1597gi0A.c() && ((Boolean) abstractC1597gi0A.a().e()).booleanValue();
    }

    public static AbstractC1597gi0 a(E0 e0, I2 i2, E0 e1, E0 e2) {
        if (i2 == e0.getType()) {
            return new C1341di0(Boolean.TRUE);
        }
        if (e1 == null) {
            return new C1341di0(Boolean.FALSE);
        }
        if (e0.a0() && !e1.a0()) {
            return new C1341di0(Boolean.FALSE);
        }
        return C1512fi0.c;
    }

    public final ArrayList b(D2 d2, I2 i2) {
        boolean z = i;
        if (!z && !c(d2.e, i2)) {
            x1f.a();
            return null;
        }
        if (!z && d2.isInterface()) {
            x1f.a();
            return null;
        }
        if (!z && i2 != a().a2 && d(i2) != null && d(i2).isInterface()) {
            x1f.a();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        E0 e0D = d2;
        while (e0D != null) {
            if (e0D.a0()) {
                arrayList.add(e0D.X());
            }
            if (e0D.e == i2) {
                break;
            }
            e0D = d(e0D.g);
        }
        return arrayList;
    }

    public final boolean a(I2 i2, I2 i3) {
        boolean z = i;
        if (!z && !i2.M0()) {
            x1f.a();
            return false;
        }
        if (z || i3.M0()) {
            return !(c(i2, i3) || c(i3, i2));
        }
        x1f.a();
        return false;
    }

    public final ArrayList a(E0 e0, C0322w2 c0322w2) {
        O4 o4A = O4.a(a(), new w9h(this));
        L4 l4 = new L4(o4A.b, o4A.a);
        o4A.a(c0322w2.C0(), c0322w2.x0(), e0, l4, e0.g, Arrays.asList(e0.h.b));
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : l4.a.entrySet()) {
            C0231j1 c0231j1 = (C0231j1) entry.getValue();
            if (c0231j1 != null && c0231j1.k1()) {
                arrayList.add(entry);
            }
        }
        for (Map.Entry entry2 : l4.b.entrySet()) {
            C0231j1 c0231j2 = (C0231j1) entry2.getValue();
            if (c0231j2 != null && c0231j2.k1()) {
                arrayList.add(entry2);
            }
        }
        return arrayList;
    }

    public final H0 b(E0 e0, C0322w2 c0322w2) {
        O4 o4A = O4.a(a(), new w9h(this));
        L4 l4 = new L4(o4A.b, o4A.a);
        o4A.a(c0322w2.C0(), c0322w2.x0(), e0, l4, e0.g, Arrays.asList(e0.h.b));
        return l4.a((E0) null).p();
    }

    public F0 b(C0245l1 c0245l1) {
        I2 i2W0 = c0245l1.w0();
        boolean z = i;
        if (!z) {
            c();
        }
        if (!z && !i2W0.M0()) {
            x1f.a();
            return null;
        }
        F0 f0P = a(i2W0, c0245l1).p();
        if (f0P == null || !f0P.getAccessFlags().n()) {
            return null;
        }
        return f0P;
    }

    public final T4 a(C1819jJ c1819jJ, C0322w2 c0322w2) {
        O4 o4A = O4.a(a(), new w9h(this));
        L4 l4 = new L4(o4A.b, o4A.a);
        o4A.a(c0322w2.C0(), c0322w2.x0(), (E0) null, l4, o4A.b.a2, c1819jJ.e);
        return l4.a((E0) null);
    }

    public H0 b(C0322w2 c0322w2, B5 b5, C0333y<? extends C0229j> c0333y) {
        D2 d2A = b5.a();
        C0229j c0229j = (C0229j) c0333y.g();
        if (!i) {
            c();
        }
        return f(c0322w2).b(d2A, (C0333y) c0333y, c0229j);
    }

    public H0 a(C0322w2 c0322w2, D2 d2, C0333y<? extends C0229j> c0333y) {
        return a(c0322w2, d2, c0333y, (C0229j) c0333y.g());
    }

    public final H0 a(C0322w2 c0322w2, D2 d2, C0333y c0333y, C0229j c0229j) {
        if (!i) {
            c();
        }
        return f(c0322w2).c(d2, c0333y, c0229j);
    }

    public final H0 b(C0322w2 c0322w2, B5 b5, C0333y c0333y, C0229j c0229j) {
        D2 d2A = b5.a();
        if (!i) {
            c();
        }
        return f(c0322w2).b(d2A, c0333y, c0229j);
    }

    public H0 a(C0322w2 c0322w2, B5 b5, C0333y<? extends C0229j> c0333y) {
        return a(c0322w2, b5, c0333y, (C0229j) c0333y.g());
    }

    public final H0 a(C0322w2 c0322w2, B5 b5, C0333y c0333y, C0229j c0229j) {
        D2 d2A = b5.a();
        if (!i) {
            c();
        }
        return f(c0322w2).a(d2A, c0333y, c0229j);
    }

    public T4 b(C0322w2 c0322w2, boolean z) {
        if (!i) {
            c();
        }
        return b(c0322w2, c0322w2.w0(), z);
    }

    public final T4 b(C0322w2 c0322w2, I2 i2, boolean z) {
        if (!i) {
            c();
        }
        if (z) {
            return c(i2, c0322w2);
        }
        return a(i2, c0322w2);
    }

    public T4 a(I2 i2, C0322w2 c0322w2) {
        if (!i) {
            c();
        }
        return b(i2, c0322w2.C0(), c0322w2.x0());
    }

    public final T4 b(I2 i2, E2 e2, H2 h2) {
        if (!i) {
            c();
        }
        return O4.a(a(), new w9h(this)).a(i2, e2, h2);
    }

    public final T4 a(C0322w2 c0322w2, boolean z) {
        if (!i) {
            c();
        }
        return a(c0322w2, c0322w2.w0(), z);
    }

    public final T4 a(C0322w2 c0322w2, I2 i2, boolean z) {
        boolean z2 = i;
        if (!z2) {
            c();
        }
        if (z) {
            return b(i2, c0322w2);
        }
        if (!z2) {
            c();
        }
        return a(i2, c0322w2.C0(), c0322w2.x0());
    }

    public final T4 b(E0 e0, E2 e2, H2 h2) {
        if (!i) {
            c();
        }
        return O4.a(a(), new w9h(this)).a(e0, e2, h2);
    }

    public final T4 b(I2 i2, C0322w2 c0322w2) {
        if (!i) {
            c();
        }
        return new O4(new q9h(this), a(), true, true).b(i2, c0322w2.C0(), c0322w2.x0());
    }

    public final T4 a(I2 i2, E2 e2, H2 h2) {
        if (!i) {
            c();
        }
        return new O4(new q9h(this), a(), true, true).a(i2, e2, h2);
    }

    public final T4 a(E0 e0, E2 e2, H2 h2) {
        if (!i) {
            c();
        }
        return new O4(new q9h(this), a(), true, true).a(e0, e2, h2);
    }

    @Override // com.android.tools.r8.graph.C0215h
    public final AbstractC0330x3 a(I2 i2, C0245l1 c0245l1, B5 b5) {
        if (!i) {
            c();
        }
        return a(i2, c0245l1);
    }

    public final AbstractC0330x3 a(I2 i2, C0245l1 c0245l1) {
        if (!i) {
            c();
        }
        return new C0268o3(this).a(i2, c0245l1);
    }

    public final AbstractC0330x3 a(E0 e0, C0245l1 c0245l1) {
        if (!i) {
            c();
        }
        C0268o3 c0268o3 = new C0268o3(this);
        if (!C0268o3.b && e0 == null) {
            x1f.a();
            return null;
        }
        boolean z = AbstractC0330x3.a;
        C0275p3 c0275p3 = new C0275p3();
        c0268o3.a(e0, c0245l1, e0, C1755ib0.b(8), c0275p3);
        C0282q3 c0282q3 = C0282q3.b;
        AbstractC0330x3 abstractC0330x3 = c0275p3.a;
        return abstractC0330x3 == null ? c0282q3 : abstractC0330x3;
    }

    @Override // com.android.tools.r8.graph.C0215h
    public final boolean h() {
        if (i) {
            return true;
        }
        c();
        return true;
    }
}
