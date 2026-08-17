package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.C0705Nt;
import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.internal.H5;
import com.android.tools.r8.internal.PW;
import com.android.tools.r8.ir.optimize.C3242a;
import defpackage.bka;
import defpackage.jh6;
import defpackage.pja;
import defpackage.zja;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Nt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0705Nt implements InterfaceC0809Rt, InterfaceC2714tl0 {
    public static final /* synthetic */ boolean k = true;
    public final com.android.tools.r8.graph.B5 a;
    public final AbstractC2166nO.a b;
    public final AbstractC2004lX c;
    public LinkedList<H5> d;
    public final AS e;
    public final AS f;
    public int g = 0;
    public int h = 0;
    public final C0887Ut i;
    public final C2752uB j;

    public C0705Nt(C2752uB c2752uB, com.android.tools.r8.graph.B5 b5, AbstractC2004lX abstractC2004lX, LinkedList<H5> linkedList, AS as, AS as2, C0887Ut c0887Ut, AbstractC2166nO.a aVar) {
        boolean z = k;
        if (!z && c0887Ut == null) {
            x1f.a();
            throw null;
        }
        if (!z && c2752uB == null) {
            x1f.a();
            throw null;
        }
        if (!z && linkedList.size() != as2.b()) {
            x1f.a();
            throw null;
        }
        if (!z && abstractC2004lX == null) {
            x1f.a();
            throw null;
        }
        this.j = c2752uB;
        this.b = aVar;
        this.a = b5;
        this.c = abstractC2004lX;
        this.d = linkedList;
        this.e = as;
        this.f = as2;
        this.i = c0887Ut;
    }

    public static /* synthetic */ boolean c(C2543rl0 c2543rl0) {
        if (k || !c2543rl0.C() || c2543rl0.b0().stream().anyMatch(new Predicate() { // from class: gka
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0705Nt.b((AbstractC0890Uw) obj);
            }
        })) {
            return true;
        }
        if ((!c2543rl0.j() && c2543rl0.c.k1()) || !c2543rl0.x() || c2543rl0.g().stream().anyMatch(new Predicate() { // from class: hka
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0705Nt.c((AbstractC0890Uw) obj);
            }
        }) || c2543rl0.V() > 0) {
            return true;
        }
        x01.a(Wf0.a(System.lineSeparator(), c2543rl0.b0()));
        return false;
    }

    public final int A() {
        int i = 1;
        if (!k) {
            for (int i2 = 1; (this.g & i2) == i2; i2 <<= 1) {
                if (i2 > 1073741824) {
                    x1f.a();
                    return 0;
                }
            }
        }
        while (true) {
            int i3 = this.g;
            if ((i3 & i) != i) {
                int i4 = i3 | i;
                this.g = i4;
                boolean z = k;
                if (!z && (i4 & i) == 0) {
                    x1f.a();
                    return 0;
                }
                if (!z) {
                    for (H5 h5 : this.d) {
                        if (!k && h5.a(i)) {
                            x1f.a();
                            return 0;
                        }
                    }
                }
                return i;
            }
            if (!k && i > 1073741824) {
                x1f.a();
                return 0;
            }
            i <<= 1;
        }
    }

    public final void B() {
        ArrayList arrayList = new ArrayList();
        for (H5 h5 : this.d) {
            List<H5> listL = h5.l();
            if (listL.size() > 1) {
                if (!k) {
                    AbstractC0890Uw abstractC0890Uw = (AbstractC0890Uw) h5.f.get(0);
                    abstractC0890Uw.getClass();
                    if (abstractC0890Uw instanceof C2766uP) {
                        x1f.a();
                        return;
                    }
                }
                for (int i = 0; i < listL.size(); i++) {
                    H5 h6 = listL.get(i);
                    if (h6.b.size() != 1 || !h6.h().K1()) {
                        H5 h5A = H5.a(this.f.a(), h6.h().getPosition(), this.i, h5);
                        arrayList.add(h5A);
                        h6.b(h5, h5A);
                        h5A.l().add(h6);
                        listL.set(i, h5A);
                    }
                }
            }
        }
        this.d.addAll(arrayList);
    }

    public Stream<AbstractC0890Uw> C() {
        return De0.a(s());
    }

    public final AbstractC0551Hu D() {
        ArrayList arrayList = new ArrayList(this.d.size());
        HashSet hashSet = new HashSet(this.d.size());
        ArrayDeque arrayDeque = new ArrayDeque(this.d.size());
        arrayDeque.addLast(j());
        while (!arrayDeque.isEmpty()) {
            Object objRemoveLast = arrayDeque.removeLast();
            if (objRemoveLast instanceof C0653Lt) {
                arrayList.add(((C0653Lt) objRemoveLast).a);
            } else {
                H5 h5 = (H5) objRemoveLast;
                if (!hashSet.contains(h5)) {
                    hashSet.add(h5);
                    arrayDeque.addLast(new C0653Lt(h5));
                    for (int size = h5.t().size() - 1; size >= 0; size--) {
                        arrayDeque.addLast(h5.t().get(size));
                    }
                }
            }
        }
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            c0473EuG.a((H5) arrayList.get(size2));
        }
        AbstractC0551Hu abstractC0551HuA = c0473EuG.a();
        if (!this.j.u1.g0) {
            return abstractC0551HuA;
        }
        C0473Eu c0473EuG2 = AbstractC0551Hu.g();
        Ck0 it = abstractC0551HuA.iterator();
        while (it.hasNext()) {
            H5 h6 = (H5) it.next();
            AbstractC0890Uw abstractC0890Uw = (AbstractC0890Uw) h6.f.get(0);
            abstractC0890Uw.getClass();
            if (!(abstractC0890Uw instanceof C2766uP)) {
                c0473EuG2.a(h6);
            }
        }
        Ck0 it2 = abstractC0551HuA.iterator();
        while (it2.hasNext()) {
            H5 h7 = (H5) it2.next();
            AbstractC0890Uw abstractC0890Uw2 = (AbstractC0890Uw) h7.f.get(0);
            abstractC0890Uw2.getClass();
            if (abstractC0890Uw2 instanceof C2766uP) {
                c0473EuG2.a(h7);
            }
        }
        return c0473EuG2.a();
    }

    public final void E() {
        H5 h5;
        AbstractC0551Hu abstractC0551HuD = D();
        int iA = A();
        LinkedList<H5> linkedList = new LinkedList<>();
        Ck0 it = abstractC0551HuD.iterator();
        while (it.hasNext()) {
            H5 h6 = (H5) it.next();
            if (!h6.a(iA)) {
                h6.b(iA);
                linkedList.add(h6);
                H5 h5K2 = h6.h().K2();
                while (true) {
                    H5 h7 = h5K2;
                    h5 = h6;
                    h6 = h7;
                    if (h6 == null || h6.a(iA)) {
                        break;
                    }
                    h6.b(iA);
                    linkedList.add(h6);
                    h5K2 = h6.h().K2();
                }
                if (h6 != null) {
                    H5 h5A = H5.a(this.f.a(), h5.h().getPosition(), this.i, h6);
                    h5.h().b(h5A);
                    h5A.l().add(h5);
                    h6.a(h5, h5A);
                    h5A.b(iA);
                    linkedList.add(h5A);
                }
            }
        }
        this.d = linkedList;
        a(iA);
        if (k || this.g == 0) {
            return;
        }
        x1f.a();
    }

    public final void F() {
        e(f(new Predicate() { // from class: tja
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0705Nt.a((C2543rl0) obj);
            }
        }));
    }

    public final void G() {
        e(f(new Predicate() { // from class: dka
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0705Nt.b((C2543rl0) obj);
            }
        }));
    }

    public final void H() {
        e(f(new Predicate() { // from class: eka
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0705Nt.c((C2543rl0) obj);
            }
        }));
    }

    public final void a(C0333y c0333y, boolean z) {
        boolean z2 = true;
        for (H5 h5 : this.d) {
            if (!k) {
                boolean z3 = this.j.Z0 || i().a().f(c0333y);
                for (AbstractC0890Uw abstractC0890Uw : h5.k()) {
                    boolean z4 = H5.p;
                    if (!z4) {
                        abstractC0890Uw.a(z3);
                    }
                    if (!z4 && abstractC0890Uw.i() != h5) {
                        x1f.a();
                        return;
                    }
                    if (!z4 && abstractC0890Uw.k1() && !z2) {
                        x1f.a();
                        return;
                    }
                    if (!z4) {
                        abstractC0890Uw.getClass();
                        if ((abstractC0890Uw instanceof C0590Jh) && abstractC0890Uw.S0().isEmpty()) {
                            x1f.a();
                            return;
                        }
                    }
                    if (!z4 && abstractC0890Uw.M1() && !H5.a(abstractC0890Uw.S(), z)) {
                        x1f.a();
                        return;
                    }
                    abstractC0890Uw.getClass();
                    if (abstractC0890Uw instanceof C2766uP) {
                        if (!z4 && abstractC0890Uw != ((AbstractC0890Uw) h5.f.get(0))) {
                            x1f.a();
                            return;
                        }
                        for (H5 h6 : h5.s()) {
                            if (!H5.p && !h6.c(h5) && (!h6.G() || h6.f() != h5)) {
                                x1f.a();
                                return;
                            }
                        }
                    }
                    if (!abstractC0890Uw.k1()) {
                        z2 = false;
                    }
                }
            }
            z2 = false;
        }
    }

    public final void b(C0333y c0333y, boolean z) {
        boolean z2 = k;
        if (!z2 && this.g != 0) {
            x1f.a();
            return;
        }
        if (!z2) {
            f();
        }
        if (!z2) {
            h();
        }
        if (!z2) {
            for (H5 h5 : this.d) {
                if (!k) {
                    h5.c();
                }
            }
        }
        boolean z3 = k;
        if (!z3) {
            a(c0333y, z);
        }
        if (!z3) {
            for (AbstractC0890Uw abstractC0890Uw : s()) {
                if (abstractC0890Uw.h1()) {
                    if (!k && (!this.i.b(0) || !this.i.a())) {
                        x01.a("IR metadata should indicate that code has an add");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C2308p2) {
                    if (!k && (!this.i.b(4) || !this.i.a())) {
                        x01.a("IR metadata should indicate that code has an and");
                        return;
                    }
                } else if (abstractC0890Uw.u1()) {
                    if (!k && !this.i.b(10)) {
                        x01.a("IR metadata should indicate that code has a check-cast");
                        return;
                    }
                } else if (abstractC0890Uw.z1()) {
                    if (!k && !this.i.b(15)) {
                        x01.a("IR metadata should indicate that code has a const-number");
                        return;
                    }
                } else if (abstractC0890Uw.A1()) {
                    if (!k && !this.i.b(16)) {
                        x01.a("IR metadata should indicate that code has a const-string");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C0719Oh) {
                    if (!k && !this.i.b(19)) {
                        x01.a("IR metadata should indicate that code has a debug position");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C0646Ll) {
                    if (!k && !this.i.b(20)) {
                        x01.a("IR metadata should indicate that code has a dex-item-based-const-string");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C1433em) {
                    if (!k && (!this.i.b(21) || !this.i.a())) {
                        x01.a("IR metadata should indicate that code has a div");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C3152yw) {
                    if (!k && !this.i.b(28)) {
                        x01.a("IR metadata should indicate that code has an instance-get");
                        return;
                    }
                } else if (abstractC0890Uw.Q1()) {
                    if (!k && !this.i.b(30)) {
                        x01.a("IR metadata should indicate that code has an instance-put");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C0605Jw) {
                    if (!k && !this.i.b(29)) {
                        x01.a("IR metadata should indicate that code has an instance-of");
                        return;
                    }
                } else if (abstractC0890Uw.R1()) {
                    if (!k && !this.i.b(31)) {
                        x01.a("IR metadata should indicate that code has an int-switch");
                        return;
                    }
                } else if (abstractC0890Uw.U1()) {
                    if (!k && !this.i.b(33)) {
                        x01.a("IR metadata should indicate that code has an invoke-direct");
                        return;
                    }
                } else if (abstractC0890Uw instanceof YB) {
                    if (!k && !this.i.b(34)) {
                        x01.a("IR metadata should indicate that code has an invoke-interface");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C1301dC) {
                    if (!k && !this.i.b(37)) {
                        x01.a("IR metadata should indicate that code has an invoke-polymorphic");
                        return;
                    }
                } else if (abstractC0890Uw.a2()) {
                    if (!k && !this.i.b(38)) {
                        x01.a("IR metadata should indicate that code has an invoke-static");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C2069mC) {
                    if (!k && !this.i.b(39)) {
                        x01.a("IR metadata should indicate that code has an invoke-super");
                        return;
                    }
                } else if (abstractC0890Uw.c2()) {
                    if (!k && !this.i.b(40)) {
                        x01.a("IR metadata should indicate that code has an invoke-virtual");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C2772uV) {
                    if (!k && (!this.i.b(53) || !this.i.a())) {
                        x01.a("IR metadata should indicate that code has an or");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C2424qP) {
                    if (!k && !this.i.b(42)) {
                        x01.a("IR metadata should indicate that code has a monitor instruction");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C3023xP) {
                    if (!k && (!this.i.b(45) || !this.i.a())) {
                        x01.a("IR metadata should indicate that code has a mul");
                        return;
                    }
                } else if (abstractC0890Uw.o2()) {
                    if (!k && !this.i.b(49)) {
                        x01.a("IR metadata should indicate that code has a new-instance");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C1036a50) {
                    if (!k && (!this.i.b(55) || !this.i.a())) {
                        x01.a("IR metadata should indicate that code has a rem");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C3037xb0) {
                    if (!k && (!this.i.b(57) || !this.i.a())) {
                        x01.a("IR metadata should indicate that code has a shl");
                        return;
                    }
                } else if (abstractC0890Uw instanceof Bb0) {
                    if (!k && (!this.i.b(58) || !this.i.a())) {
                        x01.a("IR metadata should indicate that code has a shr");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C2529re0) {
                    if (!k && !this.i.b(59)) {
                        x01.a("IR metadata should indicate that code has a static-get");
                        return;
                    }
                } else if (abstractC0890Uw.d()) {
                    if (!k && !this.i.b(60)) {
                        x01.a("IR metadata should indicate that code has a static-put");
                        return;
                    }
                } else if (abstractC0890Uw.z2()) {
                    if (!k && !this.i.b(62)) {
                        x01.a("IR metadata should indicate that code has a string-switch");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C1764ig0) {
                    if (!k && (!this.i.b(63) || !this.i.a())) {
                        x01.a("IR metadata should indicate that code has a sub");
                        return;
                    }
                } else if (abstractC0890Uw instanceof C1603gl0) {
                    if (!k && (!this.i.b(67) || !this.i.a())) {
                        x01.a("IR metadata should indicate that code has an ushr");
                        return;
                    }
                } else if ((abstractC0890Uw instanceof Ym0) && !k && (!this.i.b(68) || !this.i.a())) {
                    x01.a("IR metadata should indicate that code has an xor");
                    return;
                }
            }
        }
        if (k) {
            return;
        }
        for (AbstractC0890Uw abstractC0890Uw2 : s()) {
            if (abstractC0890Uw2.g() && !abstractC0890Uw2.A1() && !(abstractC0890Uw2 instanceof C0646Ll) && abstractC0890Uw2.getPosition().n()) {
                AbstractC2004lX position = abstractC0890Uw2.getPosition();
                position.getClass();
                if (position != AbstractC2004lX.c.h) {
                    x1f.a();
                    return;
                }
            }
        }
    }

    public final IdentityHashMap d() {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        ArrayDeque arrayDeque = new ArrayDeque();
        AbstractC0551Hu abstractC0551HuD = D();
        arrayDeque.addAll(abstractC0551HuD.j());
        while (!arrayDeque.isEmpty()) {
            H5 h5 = (H5) arrayDeque.poll();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Set setC = AbstractC2780ub0.c();
            ArrayDeque arrayDeque2 = new ArrayDeque();
            Set setA = h5.i().a();
            for (H5 h6 : h5.t()) {
                C0679Mt c0679Mt = (C0679Mt) identityHashMap.get(h6);
                if (c0679Mt != null) {
                    AbstractC1820jK.a(linkedHashSet, c0679Mt.a);
                    setC.addAll(c0679Mt.b);
                    if (!setA.contains(h6)) {
                        if (!k && !arrayDeque2.isEmpty()) {
                            x1f.a();
                            return null;
                        }
                        arrayDeque2 = new ArrayDeque(c0679Mt.c);
                    } else if (!k && c0679Mt.c.size() != 0) {
                        x1f.a();
                        return null;
                    }
                }
                int iIndexOf = h6.s().indexOf(h5);
                for (PW pw : h6.q()) {
                    C2543rl0 c2543rl0B = pw.b(iIndexOf);
                    if (c2543rl0B.P()) {
                        arrayDeque2.addLast(c2543rl0B);
                    } else {
                        linkedHashSet.add(c2543rl0B);
                        if (!pw.y()) {
                            continue;
                        } else {
                            if (!k && pw.r() != c2543rl0B.r()) {
                                x1f.a();
                                return null;
                            }
                            setC.add(c2543rl0B);
                        }
                    }
                }
            }
            if (!k && !arrayDeque2.isEmpty() && h5.t().size() - setA.size() != 1) {
                x1f.a();
                return null;
            }
            K5 k5A = h5.a(this, h5.k().size());
            while (k5A.hasPrevious()) {
                AbstractC0890Uw abstractC0890UwPrevious = k5A.previous();
                C2543rl0 c2543rl0C = abstractC0890UwPrevious.c();
                if (c2543rl0C != null) {
                    if (c2543rl0C instanceof Od0) {
                        C2543rl0 c2543rl0 = (C2543rl0) arrayDeque2.removeLast();
                        if (!k && c2543rl0 != c2543rl0C) {
                            x1f.a();
                            return null;
                        }
                    } else if (c2543rl0C instanceof Pd0) {
                        Od0[] od0ArrC0 = ((Pd0) c2543rl0C).c0();
                        for (int length = od0ArrC0.length - 1; length >= 0; length--) {
                            C2543rl0 c2543rl1 = (C2543rl0) arrayDeque2.removeLast();
                            if (!k && c2543rl1 != od0ArrC0[length]) {
                                x1f.a();
                                return null;
                            }
                        }
                    } else {
                        linkedHashSet.remove(c2543rl0C);
                        if (!k && !c2543rl0C.y() && setC.contains(c2543rl0C)) {
                            x1f.a();
                            return null;
                        }
                        if (c2543rl0C.y()) {
                            setC.remove(c2543rl0C);
                        }
                    }
                }
                for (C2543rl0 c2543rl2 : (ArrayList) abstractC0890UwPrevious.f1()) {
                    if (c2543rl2.T()) {
                        linkedHashSet.add(c2543rl2);
                    } else if (c2543rl2.P()) {
                        arrayDeque2.addLast(c2543rl2);
                    }
                }
                if (!abstractC0890UwPrevious.S0().isEmpty()) {
                    ArrayList arrayList = new ArrayList(abstractC0890UwPrevious.S0());
                    arrayList.sort(new zja());
                    boolean z = k;
                    if (!z && !arrayList.stream().allMatch(new Predicate() { // from class: aka
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return ((C2543rl0) obj).T();
                        }
                    })) {
                        x1f.a();
                        return null;
                    }
                    if (!z && !arrayList.stream().allMatch(new bka())) {
                        x1f.a();
                        return null;
                    }
                    linkedHashSet.addAll(arrayList);
                    setC.addAll(arrayList);
                }
            }
            for (PW pw2 : h5.q()) {
                if (pw2.P()) {
                    arrayDeque2.remove(pw2);
                } else {
                    linkedHashSet.remove(pw2);
                }
                if (!k && !pw2.y() && setC.contains(pw2)) {
                    x1f.a();
                    return null;
                }
                if (pw2.y()) {
                    setC.remove(pw2);
                }
            }
            C0679Mt c0679Mt2 = new C0679Mt(linkedHashSet, setC, arrayDeque2);
            C0679Mt c0679Mt3 = (C0679Mt) identityHashMap.put(h5, c0679Mt2);
            if (c0679Mt3 == null || !c0679Mt3.equals(c0679Mt2)) {
                for (H5 h7 : h5.s()) {
                    if (!arrayDeque.contains(h7)) {
                        arrayDeque.add(h7);
                    }
                }
            }
        }
        if (k || ((C0679Mt) identityHashMap.get(abstractC0551HuD.get(0))).a()) {
            return identityHashMap;
        }
        pe1.a("Unexpected values live at entry to first block: ", ((C0679Mt) identityHashMap.get(abstractC0551HuD.get(0))).a);
        return null;
    }

    public final void e(C0333y c0333y) {
        boolean z = k;
        if (!z) {
            b(c0333y, true);
        }
        if (!z) {
            a(c0333y, true);
        }
        if (!z) {
            g();
        }
        if (!z) {
            for (H5 h5 : this.d) {
                if (h5.x()) {
                    if (!k && h5 == j()) {
                        x1f.a();
                        return;
                    }
                    for (H5 h6 : h5.i().a()) {
                        if (!k && h6.s().size() != 1) {
                            x1f.a();
                            return;
                        }
                    }
                    boolean z2 = false;
                    for (AbstractC0890Uw abstractC0890Uw : h5.k()) {
                        if (abstractC0890Uw.g()) {
                            if (!k && z2) {
                                x1f.a();
                                return;
                            }
                            z2 = true;
                        } else if (!k && z2 && !abstractC0890Uw.i1()) {
                            x1f.a();
                            return;
                        }
                    }
                }
            }
        }
        if (!k) {
            for (H5 h7 : this.d) {
                List<H5> listS = h7.s();
                if (listS.size() > 1) {
                    if (!(((AbstractC0890Uw) h7.f.get(0)) instanceof C2766uP)) {
                        for (int i = 0; i < listS.size(); i++) {
                            H5 h8 = listS.get(i);
                            if (h8.b.size() != 1 || !h8.h().K1()) {
                                if (!k) {
                                    x1f.a();
                                    return;
                                }
                            }
                        }
                    } else if (!k) {
                        x1f.a();
                        return;
                    }
                    x1f.a();
                    return;
                }
            }
        }
        if (k) {
            return;
        }
        H();
    }

    public final boolean f(C0333y c0333y) {
        if (c0333y.Q().Q) {
            return true;
        }
        for (AbstractC1047aC abstractC1047aC : b((Predicate) new pja())) {
            com.android.tools.r8.graph.I2 i2W0 = abstractC1047aC.U2().w0();
            if (!i2W0.I0()) {
                com.android.tools.r8.graph.E0 e0A = c0333y.a(i().a(), i2W0);
                if (!k && e0A != null && abstractC1047aC.T2() != e0A.isInterface()) {
                    x1f.a();
                    return false;
                }
            }
        }
        return true;
    }

    public final void g() {
        C2986wz c2986wz = new C2986wz();
        for (H5 h5 : this.d) {
            int size = h5.s().size();
            for (PW pw : h5.q()) {
                boolean z = k;
                if (!z && pw.d0()) {
                    pe1.a("Unexpected trivial phi in ", i().v());
                    return;
                }
                if (!z && pw.c0().size() != size) {
                    x1f.a();
                    return;
                }
                a(c2986wz, pw);
                for (C2543rl0 c2543rl0 : pw.c0()) {
                    a(c2986wz, c2543rl0);
                    boolean z2 = k;
                    if (!z2 && !c2543rl0.a0().contains(pw)) {
                        x1f.a();
                        return;
                    }
                    if (!z2 && pw.y() && pw.r() != c2543rl0.r()) {
                        x1f.a();
                        return;
                    } else if (!z2 && !c2543rl0.j() && !c2543rl0.c.Z0()) {
                        x1f.a();
                        return;
                    }
                }
            }
            for (AbstractC0890Uw abstractC0890Uw : h5.k()) {
                boolean z3 = k;
                if (!z3 && abstractC0890Uw.i() != h5) {
                    x1f.a();
                    return;
                }
                C2543rl0 c2543rl0C = abstractC0890Uw.c();
                if (c2543rl0C != null) {
                    a(c2986wz, c2543rl0C);
                    if (!z3 && c2543rl0C.c != abstractC0890Uw) {
                        x1f.a();
                        return;
                    }
                }
                for (C2543rl0 c2543rl1 : (ArrayList) abstractC0890Uw.f1()) {
                    a(c2986wz, c2543rl1);
                    if (!k && !c2543rl1.b0().contains(abstractC0890Uw)) {
                        x1f.a();
                        return;
                    }
                }
                for (C2543rl0 c2543rl2 : abstractC0890Uw.S0()) {
                    a(c2986wz, c2543rl2);
                    if (!k && !c2543rl2.g().contains(abstractC0890Uw)) {
                        x1f.a();
                        return;
                    }
                }
            }
        }
        BU it = ((C2302oz) c2986wz.values()).iterator();
        while (it.hasNext()) {
            C2543rl0 c2543rl3 = (C2543rl0) it.next();
            boolean z4 = k;
            if (!z4 && !z4) {
                if (c2543rl3.j()) {
                    PW pwM = c2543rl3.m();
                    if (!z4 && !pwM.r.q().contains(pwM)) {
                        x1f.a();
                        return;
                    }
                } else {
                    C2543rl0 c2543rl0C2 = c2543rl3.c.c();
                    if (!z4 && c2543rl0C2 != c2543rl3 && (!(c2543rl3 instanceof Od0) || !Arrays.asList(((Pd0) c2543rl0C2).r).contains(c2543rl3))) {
                        x1f.a();
                        return;
                    }
                }
            }
            if (!z4) {
                for (AbstractC0890Uw abstractC0890Uw2 : c2543rl3.b0()) {
                    if (!k && !abstractC0890Uw2.c.contains(c2543rl3)) {
                        x1f.a();
                        return;
                    }
                }
                for (PW pw2 : c2543rl3.a0()) {
                    boolean z5 = k;
                    if (!z5 && !pw2.c0().contains(c2543rl3)) {
                        x1f.a();
                        return;
                    } else if (!z5 && !pw2.r.q().contains(pw2)) {
                        x1f.a();
                        return;
                    }
                }
                if (c2543rl3.y()) {
                    for (AbstractC0890Uw abstractC0890Uw3 : c2543rl3.g()) {
                        if (!k && !abstractC0890Uw3.S0().contains(c2543rl3)) {
                            x1f.a();
                            return;
                        }
                    }
                } else {
                    continue;
                }
            }
        }
    }

    public final void h() {
        Set setA = C1755ib0.a(this.d);
        IdentityHashMap identityHashMap = new IdentityHashMap(this.d.size());
        IdentityHashMap identityHashMap2 = new IdentityHashMap(this.d.size());
        final Function function = new Function() { // from class: kja
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0705Nt.a((Collection) obj);
            }
        };
        for (H5 h5 : this.d) {
            Collection<?> collection = (Collection) identityHashMap.computeIfAbsent(h5, new Function() { // from class: lja
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return C0705Nt.a(function, (H5) obj);
                }
            });
            Collection<?> collection2 = (Collection) identityHashMap2.computeIfAbsent(h5, new Function() { // from class: mja
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return C0705Nt.b(function, (H5) obj);
                }
            });
            boolean z = k;
            if (!z && collection.size() != h5.s().size()) {
                x1f.a();
                return;
            }
            if (!z && collection2.size() != h5.t().size()) {
                x1f.a();
                return;
            }
            if (!z && !setA.containsAll(collection)) {
                x1f.a();
                return;
            }
            if (!z && !setA.containsAll(collection2)) {
                x1f.a();
                return;
            }
            Iterator<?> it = collection2.iterator();
            while (it.hasNext()) {
                Collection collection3 = (Collection) identityHashMap.computeIfAbsent((H5) it.next(), new Function() { // from class: nja
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return C0705Nt.c(function, (H5) obj);
                    }
                });
                if (!k && !collection3.contains(h5)) {
                    x1f.a();
                    return;
                }
            }
            Iterator<?> it2 = collection.iterator();
            while (it2.hasNext()) {
                Collection collection4 = (Collection) identityHashMap2.computeIfAbsent((H5) it2.next(), new Function() { // from class: oja
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return C0705Nt.d(function, (H5) obj);
                    }
                });
                if (!k && !collection4.contains(h5)) {
                    x1f.a();
                    return;
                }
            }
        }
    }

    public com.android.tools.r8.graph.B5 i() {
        return this.a;
    }

    public H5 j() {
        return this.d.getFirst();
    }

    public final AbstractC2166nO.a k() {
        return this.b;
    }

    public final AbstractC2004lX l() {
        return this.c;
    }

    public final C1201c3 m() {
        H5 h5J = j();
        int iN = n() - 1;
        h5J.getClass();
        J5 j5 = new J5(h5J, iN);
        C1201c3 c1201c3R = j5.next().r();
        boolean z = k;
        if (!z && c1201c3R == null) {
            x1f.a();
            return null;
        }
        if (z || !j5.m().k1()) {
            return c1201c3R;
        }
        x1f.a();
        return null;
    }

    public final int n() {
        return Y6.a(!i().e().z0()) + i().getReference().A0();
    }

    public final C2543rl0 o() {
        if (v().g.n()) {
            return null;
        }
        AbstractC0890Uw abstractC0890Uw = (AbstractC0890Uw) j().H().a(new jh6());
        boolean z = k;
        if (!z && abstractC0890Uw == null) {
            x1f.a();
            return null;
        }
        C2543rl0 c2543rl0C = abstractC0890Uw.r().c();
        if (z || c2543rl0C.l) {
            return c2543rl0C;
        }
        x1f.a();
        return null;
    }

    public final Set p() {
        Set setC = AbstractC2780ub0.c();
        int iA = A();
        a(AbstractC2878vi.a(j()), iA);
        for (H5 h5 : this.d) {
            if (!h5.a(iA)) {
                setC.add(h5);
            }
        }
        a(iA);
        return setC;
    }

    public InterfaceC0916Vw q() {
        return new C0731Ot(this);
    }

    public InterfaceC0968Xw r() {
        return new C0757Pt(this);
    }

    public Iterable<AbstractC0890Uw> s() {
        return new Iterable() { // from class: rja
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return this.b.q();
            }
        };
    }

    public L5 t() {
        return new L5(this);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("blocks:\n");
        Iterator<H5> it = this.d.iterator();
        while (it.hasNext()) {
            sb.append(it.next().P());
            sb.append("\n");
        }
        return sb.toString();
    }

    public final C0887Ut u() {
        return this.i;
    }

    public C0231j1 v() {
        return this.a.e();
    }

    public final void w() {
        H5 h5J = j();
        L5 l5T = t();
        while (l5T.hasNext()) {
            H5 next = l5T.next();
            K5 k5A = next.a(this);
            boolean z = false;
            while (k5A.hasNext()) {
                AbstractC0890Uw next2 = k5A.next();
                boolean zG = next2.g();
                if ((z && !next2.i1()) || (zG && next == h5J)) {
                    k5A.previous();
                    k5A.a(this, l5T);
                    l5T.previous();
                    break;
                } else if (zG) {
                    z = true;
                }
            }
            if (z) {
                List<H5> listT = next.t();
                if (listT.size() == 1 && ((H5) C2847vL.a(listT)).s().size() > 1) {
                    H5 h5A = next.a(this.f.a(), true);
                    C2636ss c2636ss = new C2636ss();
                    c2636ss.a(next);
                    c2636ss.b(AbstractC2004lX.r());
                    h5A.a(this).add(c2636ss);
                    l5T.add(h5A);
                }
            }
        }
        if (k || this.d.stream().allMatch(new Predicate() { // from class: ija
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0705Nt.a((H5) obj);
            }
        })) {
            return;
        }
        x1f.a();
    }

    public final void x() {
        a((C0602Jt) null, (C3242a) null);
    }

    public void y() {
        ArrayList arrayList = new ArrayList();
        for (final H5 h5 : this.d) {
            boolean z = k;
            if (!z && arrayList.contains(h5)) {
                x1f.a();
                return;
            }
            if (h5.C() && h5.w().s().size() == 1 && h5.k().size() == 1 && h5.h().K1() && h5.h().S0().isEmpty() && !h5.s().isEmpty()) {
                if (!z && h5.w().l().size() != 1) {
                    x1f.a();
                    return;
                }
                if (!z && h5.w().l().get(0) != h5) {
                    x1f.a();
                    return;
                }
                if (!z && h5.w().q().size() != 0) {
                    x1f.a();
                    return;
                }
                final H5 h5W = h5.w();
                h5W.l().clear();
                h5W.l().addAll(h5.s());
                h5W.q().addAll(h5.q());
                h5W.q().forEach(new Consumer() { // from class: ika
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        C0705Nt.a(h5, (PW) obj);
                    }
                });
                h5.s().forEach(new Consumer() { // from class: jka
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((H5) obj).b(h5, h5W);
                    }
                });
                h5.l().clear();
                h5.m().clear();
                h5.q().clear();
                arrayList.add(h5);
            }
        }
        this.d.removeAll(arrayList);
    }

    public final C3242a z() {
        C3242a c3242a = new C3242a();
        a(c3242a, C0822Sg.b());
        return c3242a;
    }

    public final void f() {
        ((Map) this.d.stream().collect(Collectors.groupingBy(new Function() { // from class: uja
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((H5) obj).o());
            }
        }, Collectors.counting()))).forEach(new BiConsumer() { // from class: vja
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a((Integer) obj, (Long) obj2);
            }
        });
    }

    public static Predicate f(final Predicate predicate) {
        return new Predicate() { // from class: xja
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0705Nt.a(predicate, (C2543rl0) obj);
            }
        };
    }

    public static /* synthetic */ Collection c(Function function, H5 h5) {
        return (Collection) function.apply(h5.s());
    }

    public boolean c(C0333y<?> c0333y) {
        e((C0333y) c0333y);
        boolean z = k;
        if (!z) {
            F();
        }
        if (z) {
            return true;
        }
        g(c0333y);
        return true;
    }

    public static boolean c(AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.getClass();
        return !(abstractC0890Uw instanceof C3165z4);
    }

    public final Iterator c(Predicate predicate) {
        return new AC(t(), predicate);
    }

    public List<C2543rl0> c() {
        ArrayList arrayList = new ArrayList();
        J5 j5H = j().H();
        C1201c3 c1201c3R = j5H.next().r();
        while (c1201c3R != null) {
            if (c1201c3R != null) {
                C1201c3 c1201c3R2 = j5H.next().r();
                arrayList.add(c1201c3R.c());
                c1201c3R = c1201c3R2;
            } else {
                z0e.a();
                return null;
            }
        }
        if (k || arrayList.size() == v().getReference().A0() + (!v().g.n() ? 1 : 0)) {
            return arrayList;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1004Zg
    public final AbstractC1597gi0 a(Object obj, BiFunction biFunction, AbstractC3159z1 abstractC3159z1) {
        AbstractC1597gi0 c1512fi0 = new C1512fi0(abstractC3159z1);
        Iterator<AbstractC0890Uw> it = ((H5) obj).k().iterator();
        while (it.hasNext()) {
            c1512fi0 = (AbstractC1597gi0) biFunction.apply(it.next(), c1512fi0.b().e());
            if (c1512fi0.c()) {
                break;
            }
        }
        return c1512fi0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1004Zg
    public final AbstractC1597gi0 a(Object obj, Object obj2, BiFunction biFunction) {
        return ((H5) obj).b(obj2, biFunction);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1004Zg
    public final Object a() {
        return this.d.getFirst();
    }

    public final boolean a(Consumer consumer) {
        for (H5 h5 : this.d) {
            if (h5.x()) {
                return true;
            }
            if (h5.h().L1()) {
                C2040lu c2040luR = h5.h().R();
                consumer.accept((C2543rl0) c2040luR.c.get(0));
                if (!c2040luR.P2()) {
                    consumer.accept(c2040luR.R2());
                }
            } else if (h5.h().B2()) {
                consumer.accept((C2543rl0) h5.h().M0().c.get(0));
            }
        }
        return false;
    }

    public final void a(C0333y c0333y) {
        final PN pnD = c0333y.d();
        for (AbstractC0890Uw abstractC0890Uw : s()) {
            C2543rl0 c2543rl0 = abstractC0890Uw.b;
            if (c2543rl0 != null && c2543rl0.t().w()) {
                C2441qd c2441qdB = abstractC0890Uw.b.t().b();
                boolean z = k;
                if (!z && pnD.b(c2441qdB.Q())) {
                    mu3.a("Expected reference to ", c2441qdB.Q().H0(), " to be rewritten at instruction ", abstractC0890Uw);
                    return;
                } else if (!z && c2441qdB.R().a(new BiPredicate() { // from class: jja
                    @Override // java.util.function.BiPredicate
                    public final boolean test(Object obj, Object obj2) {
                        return C0705Nt.a(pnD, (I2) obj, (Boolean) obj2);
                    }
                })) {
                    x1f.a();
                    return;
                }
            }
        }
    }

    public static /* synthetic */ boolean a(PN pn, com.android.tools.r8.graph.I2 i2, Boolean bool) {
        if (k || !pn.b(i2)) {
            return false;
        }
        x1f.a();
        return false;
    }

    public final /* synthetic */ boolean a(C0333y c0333y, Ol0 ol0, H5 h5) {
        h5.a(c0333y, i(), ol0);
        return true;
    }

    public final void a(C2986wz c2986wz, C2543rl0 c2543rl0) {
        boolean z = k;
        if (!z && c2543rl0 == null) {
            x1f.a();
            return;
        }
        int iS = c2543rl0.s();
        C2543rl0 c2543rl1 = (C2543rl0) c2986wz.a(iS, c2543rl0);
        if (z || this.j.u1.j || c2543rl1 == null || c2543rl1 == c2543rl0) {
            return;
        }
        if (iS == -1 && c2543rl0.P()) {
            return;
        }
        throw new AssertionError("Multiple value definitions with number " + iS + ": " + c2543rl0 + " and " + c2543rl1);
    }

    public final void e(Predicate predicate) {
        for (H5 h5 : this.d) {
            for (AbstractC0890Uw abstractC0890Uw : h5.k()) {
                if (abstractC0890Uw.c1() && !k && !predicate.test(abstractC0890Uw.c())) {
                    x1f.a();
                    return;
                }
            }
            for (PW pw : h5.q()) {
                if (!k && !predicate.test(pw)) {
                    x1f.a();
                    return;
                }
            }
        }
    }

    public static /* synthetic */ Collection a(Collection collection) {
        return collection.size() > 5 ? C1755ib0.a(collection) : collection;
    }

    public List<H5> e() {
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        for (H5 h5 : this.d) {
            if (h5.h().v2()) {
                c0473EuG.a(h5);
            }
        }
        return c0473EuG.a();
    }

    public static /* synthetic */ Collection a(Function function, H5 h5) {
        return (Collection) function.apply(h5.s());
    }

    public final /* synthetic */ void a(Integer num, Long l) {
        boolean z = k;
        if (!z && l.longValue() != 1) {
            x1f.a();
            return;
        }
        if (!z && num.intValue() < 0) {
            x1f.a();
        } else {
            if (z || num.intValue() <= this.f.b()) {
                return;
            }
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1004Zg
    public final AbstractC1597gi0 a(Object obj, InterfaceC1938ki0 interfaceC1938ki0, Object obj2) {
        H5 h5 = (H5) obj;
        int iK = h5.K();
        AbstractC1597gi0 c1512fi0 = new C1512fi0(obj2);
        for (int i = 0; i < iK; i++) {
            c1512fi0 = (AbstractC1597gi0) interfaceC1938ki0.a(h5.b.get(i), (com.android.tools.r8.graph.I2) h5.e.b.get(i), c1512fi0.b().f());
            if (c1512fi0.c()) {
                return c1512fi0;
            }
        }
        return c1512fi0;
    }

    public static boolean a(C2543rl0 c2543rl0) {
        boolean z = k;
        if (!z && !c2543rl0.t().G()) {
            x1f.a();
            return false;
        }
        if (!z && c2543rl0.t().A()) {
            x1f.a();
            return false;
        }
        if (!z) {
            AbstractC2624sj0 abstractC2624sj0T = c2543rl0.t();
            abstractC2624sj0T.getClass();
            if (abstractC2624sj0T instanceof C1720i7) {
                x1f.a();
                return false;
            }
        }
        if (z) {
            return true;
        }
        H h = c2543rl0.c;
        if (!(h instanceof InterfaceC3237zv) || ((InterfaceC3237zv) h).e().a()) {
            return true;
        }
        x1f.a();
        return false;
    }

    public static boolean a(Predicate predicate, C2543rl0 c2543rl0) {
        if (c2543rl0 instanceof Pd0) {
            return Stream.of((Object[]) ((Pd0) c2543rl0).r).allMatch(predicate);
        }
        return predicate.test(c2543rl0);
    }

    public final Iterable a(final Predicate predicate) {
        return new Iterable() { // from class: yja
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return this.b.c(predicate);
            }
        };
    }

    @Override // com.android.tools.r8.internal.InterfaceC2714tl0
    public C2543rl0 a(AbstractC2624sj0 abstractC2624sj0, C0230j0 c0230j0) {
        return new C2543rl0(this.e.a(), abstractC2624sj0, c0230j0);
    }

    public final C1678hg a(long j, AbstractC2624sj0 abstractC2624sj0) {
        return new C1678hg(a(abstractC2624sj0, (C0230j0) null), j);
    }

    public final C1678hg a(int i, C0230j0 c0230j0) {
        return new C1678hg(a(AbstractC2624sj0.k(), c0230j0), i);
    }

    public static void a(H5 h5, PW pw) {
        pw.r = h5.w();
    }

    public final boolean a(C0602Jt c0602Jt, C3242a c3242a) {
        Iterator<H5> it = this.d.iterator();
        boolean zA = false;
        while (it.hasNext()) {
            for (PW pw : new ArrayList(it.next().q())) {
                Sm0 sm0 = new Sm0(2);
                sm0.b(pw);
                while (true) {
                    if (sm0.b()) {
                        PW pw2 = (PW) sm0.d();
                        if (!pw2.C() && !pw2.x()) {
                            sm0.b((Iterable) pw2.a0());
                        } else {
                            zA |= pw.a(c0602Jt, c3242a, C0822Sg.b(), MX.c);
                            break;
                        }
                    } else {
                        sm0.a().forEach(new Consumer() { // from class: fka
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ((PW) obj).f0();
                            }
                        });
                        zA = true;
                        break;
                    }
                }
            }
        }
        return zA;
    }

    public final void a(int i) {
        if (!k && (this.g & i) == 0) {
            x1f.a();
            return;
        }
        for (H5 h5 : this.d) {
            h5.getClass();
            boolean z = H5.p;
            if (!z && i == 0) {
                x1f.a();
                return;
            }
            h5.n &= ~i;
            if (!z && h5.a(i)) {
                x1f.a();
                return;
            }
        }
        this.g = (~i) & this.g;
    }

    public Iterable<AbstractC0890Uw> a(final AbstractC0890Uw abstractC0890Uw) {
        final H5 h5I = abstractC0890Uw.i();
        Set<H5> setC = AbstractC2780ub0.c();
        int iA = A();
        a(new ArrayDeque(h5I.t()), iA);
        for (H5 h5 : this.d) {
            if (h5.a(iA)) {
                setC.add(h5);
            }
        }
        a(iA);
        if (setC.contains(h5I)) {
            Iterable<AbstractC0890Uw> iterableA = null;
            for (H5 h6 : setC) {
                if (iterableA != null) {
                    iterableA = AbstractC0728Oq.a(iterableA, h6.k());
                } else {
                    iterableA = h6.k();
                }
            }
            return iterableA;
        }
        Iterable<AbstractC0890Uw> iterableA2 = new Iterable() { // from class: qja
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return C0705Nt.a(h5I, abstractC0890Uw);
            }
        };
        Iterator it = setC.iterator();
        while (it.hasNext()) {
            iterableA2 = AbstractC0728Oq.a(iterableA2, ((H5) it.next()).k());
        }
        return iterableA2;
    }

    public static Iterator a(H5 h5, AbstractC0890Uw abstractC0890Uw) {
        h5.getClass();
        return new J5(h5, abstractC0890Uw);
    }

    public final void a(C3242a c3242a, Consumer consumer) {
        final int iA = A();
        a(AbstractC2878vi.a(j()), iA);
        L5 l5T = t();
        while (l5T.hasNext()) {
            H5 next = l5T.next();
            if (!next.a(iA)) {
                next.a(c3242a, consumer, new Predicate() { // from class: wja
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return C0705Nt.a(iA, (H5) obj);
                    }
                });
                l5T.remove();
            }
        }
        a(iA);
    }

    public static /* synthetic */ boolean a(int i, H5 h5) {
        return !h5.a(i);
    }

    public final void a(ArrayDeque arrayDeque, int i) {
        if (!k) {
            if ((this.g & i) != 0) {
                Iterator<H5> it = this.d.iterator();
                while (it.hasNext()) {
                    if (!it.next().a(i)) {
                    }
                }
            }
            x1f.a();
            return;
        }
        while (!arrayDeque.isEmpty()) {
            H5 h5 = (H5) arrayDeque.poll();
            if (!h5.a(i)) {
                h5.b(i);
                for (H5 h6 : h5.t()) {
                    if (!h6.a(i)) {
                        arrayDeque.add(h6);
                    }
                }
            }
        }
    }

    public final void a(H5 h5, int i) {
        if (!k && (this.g & i) == 0) {
            x1f.a();
            return;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(h5);
        while (!arrayDeque.isEmpty()) {
            H5 h6 = (H5) arrayDeque.poll();
            if (!h6.a(i)) {
                h6.b(i);
                for (H5 h7 : h6.s()) {
                    if (!h7.a(i)) {
                        arrayDeque.add(h7);
                    }
                }
            }
        }
    }

    public static boolean a(H5 h5) {
        Iterator<AbstractC0890Uw> it = h5.k().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().g()) {
                i++;
            }
        }
        return i <= 1;
    }

    public final void g(final C0333y c0333y) {
        final Ol0 ol0 = new Ol0(c0333y);
        boolean z = k;
        if (!z && c0333y.o() && !new C2454qj0(c0333y.V(), ol0).a(this)) {
            x1f.a();
            return;
        }
        C2283oj0.a(c0333y, this);
        if (z || this.d.stream().allMatch(new Predicate() { // from class: cka
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.a(c0333y, ol0, (H5) obj);
            }
        })) {
            return;
        }
        x1f.a();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1004Zg
    public final AbstractC1597gi0 d(Object obj, BiFunction biFunction) {
        return ((H5) obj).a(biFunction);
    }

    public final void d(C0333y c0333y) {
        boolean z = k;
        if (!z) {
            e(c0333y);
        }
        if (z) {
            return;
        }
        for (H5 h5 : this.d) {
            if (!k && h5.C() && h5.w().s().size() == 1 && h5.k().size() == 1 && h5.h().K1() && h5.h().S0().isEmpty() && !h5.s().isEmpty()) {
                x1f.a();
                return;
            }
        }
    }

    public static /* synthetic */ Collection d(Function function, H5 h5) {
        return (Collection) function.apply(h5.t());
    }

    public final Iterator d(Predicate predicate) {
        return new AC(q(), predicate);
    }

    public final void b(Consumer consumer) {
        consumer.accept(this.b);
    }

    public void b(Collection<H5> collection) {
        if (collection.isEmpty()) {
            return;
        }
        this.d.removeAll(collection);
    }

    public boolean b(C0333y<?> c0333y) {
        d((C0333y) c0333y);
        boolean z = k;
        if (!z) {
            F();
        }
        if (z) {
            return true;
        }
        g(c0333y);
        return true;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1004Zg
    public final AbstractC1597gi0 b(Object obj, Object obj2, BiFunction biFunction) {
        return ((H5) obj).a(obj2, biFunction);
    }

    public static /* synthetic */ Collection b(Function function, H5 h5) {
        return (Collection) function.apply(h5.t());
    }

    public static boolean b(C2543rl0 c2543rl0) {
        if (k || c2543rl0.t().H() || c2543rl0.t().d().b != C2427qS.a()) {
            return true;
        }
        x1f.a();
        return false;
    }

    public static boolean b(AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.getClass();
        return !(abstractC0890Uw instanceof C3165z4);
    }

    public final Iterable b(final Predicate predicate) {
        return new Iterable() { // from class: sja
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return this.b.d(predicate);
            }
        };
    }

    public final Iterator b() {
        return new C0628Kt(this);
    }
}
