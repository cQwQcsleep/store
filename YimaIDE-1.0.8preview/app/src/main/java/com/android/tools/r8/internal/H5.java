package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.E5;
import com.android.tools.r8.internal.H5;
import com.android.tools.r8.ir.optimize.C3242a;
import defpackage.b36;
import defpackage.iti;
import defpackage.n33;
import defpackage.z26;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class H5 {
    public static final /* synthetic */ boolean p = true;
    public InterfaceC2045lz a;
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public Set d = null;
    public C2490r8 e = C2490r8.e;
    public LinkedList f = new LinkedList();
    public int g = -1;
    public ArrayList h = new ArrayList();
    public boolean i = false;
    public boolean j = false;
    public final HashMap k = new HashMap();
    public int l = 0;
    public int m = 0;
    public int n = 0;
    public HashMap o = new HashMap();

    public final boolean A() {
        return z() && u().s().size() == 1;
    }

    public final boolean B() {
        return this.c.size() == 1;
    }

    public final boolean C() {
        return this.b.size() == 1;
    }

    public final boolean D() {
        return h().v2();
    }

    public final boolean E() {
        return this.j;
    }

    public final boolean F() {
        H5 h5 = this;
        boolean z = false;
        do {
            List listN = this.n();
            if (listN.size() > 1) {
                return false;
            }
            if (listN.size() == 0) {
                return this.h().C2();
            }
            this = (H5) listN.get(0);
            if (z) {
                h5 = (H5) h5.n().get(0);
            }
            z = !z;
        } while (this != h5);
        return false;
    }

    public boolean G() {
        return this.f.size() == 1 && h().K1();
    }

    public J5 H() {
        return new J5(this);
    }

    public final void I() {
        Set set = this.d;
        if (set != null) {
            set.forEach(new Consumer() { // from class: x26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.b((E5) obj);
                }
            });
        }
    }

    public final void J() {
        Set set = this.d;
        if (set != null) {
            set.forEach(new Consumer() { // from class: w26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.c((E5) obj);
                }
            });
        }
    }

    public final int K() {
        if (x()) {
            return this.e.a().size();
        }
        return 0;
    }

    public final int L() {
        boolean zX = x();
        ArrayList arrayList = this.b;
        return zX ? arrayList.size() - this.e.a().size() : arrayList.size();
    }

    public final void M() {
        if (!p && !x()) {
            x1f.a();
            return;
        }
        C0919Vz c0919Vz = new C0919Vz(16);
        int iK = K();
        for (int i = 0; i < iK; i++) {
            c0919Vz.add(i);
            ((H5) this.b.get(i)).l().remove(this);
        }
        a(c0919Vz);
    }

    public final void N() {
        if (!x()) {
            this.b.clear();
            return;
        }
        C0919Vz c0919Vz = new C0919Vz(16);
        int size = this.b.size();
        for (int iK = K(); iK < size; iK++) {
            c0919Vz.add(iK);
        }
        a(c0919Vz);
    }

    public void O() {
        this.i = true;
    }

    public final String P() {
        String str;
        String strY0;
        StringBuilder sb = new StringBuilder();
        sb.append("block ");
        sb.append(this.g);
        sb.append(", pred-counts: " + this.c.size());
        int i = this.m;
        if (i > 0) {
            sb.append(" (" + i + " unfilled)");
        }
        sb.append(", succ-count: " + this.b.size());
        sb.append(", filled: " + this.i);
        sb.append(", sealed: " + this.j);
        sb.append("\npredecessors: ");
        ArrayList<H5> arrayList = this.c;
        Function function = new Function() { // from class: f36
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XmlPullParser.NO_NAMESPACE;
            }
        };
        if (arrayList.size() > 0) {
            for (H5 h5 : arrayList) {
                sb.append(h5.p());
                sb.append((String) function.apply(h5));
                sb.append(' ');
            }
        } else {
            sb.append('-');
        }
        sb.append("\nsuccessors: ");
        ArrayList<H5> arrayList2 = this.b;
        Function function2 = new Function() { // from class: g36
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.h((H5) obj);
            }
        };
        if (arrayList2.size() > 0) {
            for (H5 h6 : arrayList2) {
                sb.append(h6.p());
                sb.append((String) function2.apply(h6));
                sb.append(' ');
            }
        } else {
            sb.append('-');
        }
        if (this.b.size() > 0) {
            sb.append(" (");
            if (x()) {
                sb.append(this.e.size());
            } else {
                sb.append("no");
            }
            sb.append(" try/catch successors)");
        }
        sb.append('\n');
        ArrayList arrayList3 = this.h;
        if (arrayList3 == null || arrayList3.size() <= 0) {
            sb.append("no phis\n");
        } else {
            for (PW pw : this.h) {
                sb.append(pw.e0());
                if (this.k.values().contains(pw)) {
                    sb.append(" (incomplete)");
                }
                sb.append('\n');
            }
        }
        if (this.a != null) {
            sb.append("locals: ");
            Wf0.a(sb, this.a.c(), ", ", Wf0.a.e);
            sb.append('\n');
        }
        int iMax = 0;
        int iMax2 = 0;
        for (AbstractC0890Uw abstractC0890Uw : this.f) {
            iMax2 = Math.max(iMax2, abstractC0890Uw.Y0().length());
            iMax = Math.max(iMax, (int) Math.ceil(Math.log10(abstractC0890Uw.W0() + 1)));
        }
        String str2 = null;
        for (AbstractC0890Uw abstractC0890Uw2 : this.f) {
            if (iMax2 > 0) {
                if (abstractC0890Uw2.Y0().equals(str2)) {
                    str = str2;
                    strY0 = XmlPullParser.NO_NAMESPACE;
                } else {
                    strY0 = abstractC0890Uw2.Y0();
                    str = strY0;
                }
                Wf0.a(iMax2 + 1, strY0, sb);
                sb.append(": ");
                str2 = str;
            }
            int iW0 = abstractC0890Uw2.W0();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(iW0);
            Wf0.a(iMax + 1, sb2.toString(), sb);
            sb.append(": ");
            sb.append(abstractC0890Uw2.toString());
            int i2 = C0230j0.e;
            if (!abstractC0890Uw2.S0().isEmpty()) {
                sb.append(" [end: ");
                Wf0.a(sb, abstractC0890Uw2.S0(), ", ", Wf0.a.e);
                sb.append("]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public final void Q() {
        if (!p && this.c.size() != 1) {
            x1f.a();
        } else {
            ((H5) this.c.get(0)).j(this);
            l().clear();
        }
    }

    public final H5 R() {
        boolean z = p;
        if (!z && this.c.size() != 1) {
            x1f.a();
            return null;
        }
        if (!z && ((H5) this.c.get(0)).b.size() != 1) {
            x1f.a();
            return null;
        }
        H5 h5 = (H5) this.c.get(0);
        h5.m().clear();
        l().clear();
        return h5;
    }

    public final void S() {
        boolean z = p;
        if (!z && this.c.size() != 1) {
            x1f.a();
        } else if (!z && !((H5) this.c.get(0)).b.contains(this)) {
            x1f.a();
        } else {
            l().get(0).m().remove(this);
            l().clear();
        }
    }

    public final void T() {
        boolean z = p;
        if (!z && this.l != this.c.size()) {
            x1f.a();
        } else {
            if (z || this.m == 0) {
                return;
            }
            x1f.a();
        }
    }

    public final ArrayList a(H5 h5) {
        if (!p && !h5.x()) {
            x1f.a();
            return null;
        }
        C2490r8 c2490r8 = h5.e;
        AbstractC0551Hu abstractC0551Hu = c2490r8.c;
        AbstractC0551Hu abstractC0551Hu2 = c2490r8.b;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        if (x()) {
            arrayList2.addAll(this.e.b);
            arrayList3.addAll(this.e.c);
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Integer) it.next()).intValue();
                H5 h6 = (H5) this.b.get(iIntValue);
                if (!arrayList.contains(h6)) {
                    arrayList.add(h6);
                }
                int iIndexOf = arrayList.indexOf(h6);
                if (!p && iIndexOf != iIntValue) {
                    x1f.a();
                    return null;
                }
            }
        }
        int size = arrayList.size();
        for (int i = 0; i < abstractC0551Hu.size(); i++) {
            int iIntValue2 = ((Integer) abstractC0551Hu.get(i)).intValue();
            com.android.tools.r8.graph.I2 i2 = (com.android.tools.r8.graph.I2) abstractC0551Hu2.get(i);
            if (!arrayList2.contains(i2)) {
                H5 h7 = (H5) h5.b.get(iIntValue2);
                boolean z = p;
                if (!z && h7.s().size() != 1) {
                    x1f.a();
                    return null;
                }
                if (!z && !h7.q().isEmpty()) {
                    x1f.a();
                    return null;
                }
                int iIndexOf2 = arrayList.indexOf(h7);
                if (iIndexOf2 == -1) {
                    arrayList.add(h7);
                    iIndexOf2 = arrayList.size() - 1;
                }
                arrayList2.add(i2);
                arrayList3.add(Integer.valueOf(iIndexOf2));
            }
        }
        List<H5> listM = m();
        ArrayList<H5> arrayList4 = new ArrayList(listM);
        listM.clear();
        ArrayList arrayList5 = new ArrayList();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            if (i3 < size) {
                if (!p && !((H5) arrayList.get(i3)).s().contains(this)) {
                    x1f.a();
                    return null;
                }
                listM.add((H5) arrayList.get(i3));
            } else {
                if (!p && ((H5) arrayList.get(i3)).s().contains(this)) {
                    x1f.a();
                    return null;
                }
                g((H5) arrayList.get(i3));
                arrayList5.add((H5) arrayList.get(i3));
            }
        }
        this.e = new C2490r8(arrayList2, arrayList3);
        int size2 = listM.size();
        for (H5 h8 : arrayList4) {
            if (!listM.contains(h8)) {
                if (!p && h().C2()) {
                    x1f.a();
                    return null;
                }
                listM.add(h8);
            }
        }
        if (p || listM.size() == size2 || !h().C2()) {
            return arrayList5;
        }
        x1f.a();
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:61:0x013a  */
    public final void b(H5 h5, H5 h6) {
        if (!p && !this.b.contains(h5)) {
            x01.a("attempt to replace non-existent successor");
            return;
        }
        int i = 0;
        if (!this.b.contains(h6)) {
            while (i < this.b.size()) {
                if (this.b.get(i) == h5) {
                    m().set(i, h6);
                    return;
                }
                i++;
            }
            return;
        }
        int iIndexOf = this.b.indexOf(h5);
        int iIndexOf2 = this.b.indexOf(h6);
        if (x()) {
            ArrayList arrayList = new ArrayList(this.e.c);
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (((Integer) arrayList.get(i2)).intValue() == iIndexOf) {
                    arrayList.set(i2, Integer.valueOf(iIndexOf2));
                }
                if (((Integer) arrayList.get(i2)).intValue() > iIndexOf) {
                    arrayList.set(i2, Integer.valueOf(((Integer) arrayList.get(i2)).intValue() - 1));
                }
            }
            this.e = new C2490r8(this.e.b, arrayList);
        }
        if (h().K1()) {
            if (iIndexOf == this.b.size() - 1 && iIndexOf2 != this.b.size() - 2) {
                a(iIndexOf - 1, iIndexOf2);
            }
        } else if (h().L1()) {
            if (iIndexOf2 >= this.b.size() - 2 && iIndexOf >= this.b.size() - 2) {
                AbstractC0890Uw abstractC0890UwRemoveLast = k().removeLast();
                for (int size = abstractC0890UwRemoveLast.c.size() - 1; size >= 0; size--) {
                    C2543rl0 c2543rl0 = (C2543rl0) abstractC0890UwRemoveLast.c.get(size);
                    if (c2543rl0.P()) {
                        if (c2543rl0.j()) {
                            if (p) {
                            }
                            C1492fX c1492fX = new C1492fX(c2543rl0);
                            c1492fX.a(this);
                            c1492fX.b(abstractC0890UwRemoveLast.getPosition());
                            k().addLast(c1492fX);
                        } else {
                            AbstractC0890Uw abstractC0890Uw = c2543rl0.c;
                            abstractC0890Uw.getClass();
                            if ((abstractC0890Uw instanceof EL) && c2543rl0.c.i() == this) {
                                if (!p) {
                                    H5 h5I = c2543rl0.c.i();
                                    H5 h7 = this;
                                    while (h7 != h5I) {
                                        if (h7.s().size() == 1) {
                                            H5 h8 = h7.s().get(0);
                                            if (h8.h().K1() && C5.a(h8) == h7) {
                                                h7 = h8;
                                            }
                                        }
                                        x1f.a();
                                        return;
                                    }
                                }
                                c2543rl0.c.i().e(c2543rl0.c);
                            } else {
                                if (p && (c2543rl0 instanceof Pd0)) {
                                    x1f.a();
                                    return;
                                }
                                C1492fX c1492fX2 = new C1492fX(c2543rl0);
                                c1492fX2.a(this);
                                c1492fX2.b(abstractC0890UwRemoveLast.getPosition());
                                k().addLast(c1492fX2);
                            }
                        }
                    }
                    if (c2543rl0.D()) {
                        c2543rl0.d.remove(abstractC0890UwRemoveLast);
                        c2543rl0.e = null;
                    }
                }
                C2636ss c2636ss = new C2636ss();
                c2636ss.a(this);
                c2636ss.b(abstractC0890UwRemoveLast.getPosition());
                k().addLast(c2636ss);
            } else if (iIndexOf >= this.b.size() - 2) {
                a(iIndexOf - 1, iIndexOf2);
            }
        } else if (h().B2()) {
            AbstractC2618sg0 abstractC2618sg0M0 = h().M0();
            if (abstractC2618sg0M0.k == iIndexOf) {
                abstractC2618sg0M0.k = iIndexOf2;
            }
            int i3 = abstractC2618sg0M0.k;
            if (i3 > iIndexOf) {
                abstractC2618sg0M0.k = i3 - 1;
            }
            int[] iArr = abstractC2618sg0M0.j;
            while (i < iArr.length) {
                if (iArr[i] == iIndexOf) {
                    iArr[i] = iIndexOf2;
                }
                int i4 = iArr[i];
                if (i4 > iIndexOf) {
                    iArr[i] = i4 - 1;
                }
                i++;
            }
        } else if (!p && !h().v2() && !h().C2()) {
            x1f.a();
            return;
        }
        boolean zRemove = m().remove(h5);
        if (p || zRemove) {
            return;
        }
        x1f.a();
    }

    public final void c() {
        if (x()) {
            boolean z = p;
            if (!z && !h().K1() && !h().C2()) {
                x1f.a();
                return;
            }
            C2490r8 c2490r8 = this.e;
            if (!z && c2490r8.b.size() != AbstractC2554rv.a(c2490r8.b).size()) {
                x1f.a();
                return;
            }
            AbstractC0551Hu abstractC0551Hu = c2490r8.b;
            int size = abstractC0551Hu.size() - 1;
            for (int i = 0; i < abstractC0551Hu.size(); i++) {
                if (!p && ((com.android.tools.r8.graph.I2) abstractC0551Hu.get(i)).Z0().equals("Ljava/lang/Throwable;") && i != size) {
                    x1f.a();
                    return;
                }
            }
            ArrayList arrayList = new ArrayList(c2490r8.c);
            arrayList.sort(Comparator.naturalOrder());
            int iIntValue = ((Integer) arrayList.get(0)).intValue();
            int iIntValue2 = ((Integer) arrayList.get(arrayList.size() - 1)).intValue();
            boolean z2 = p;
            if (!z2 && iIntValue != 0) {
                x1f.a();
                return;
            }
            if (!z2 && iIntValue2 >= arrayList.size()) {
                x1f.a();
                return;
            }
            int size2 = t().size();
            int i2 = size2 - 1;
            if (z2 || iIntValue2 == i2 || iIntValue2 == size2 - 2) {
                if (z2 || iIntValue2 == i2 || !h().C2()) {
                    return;
                }
                x1f.a();
            } else {
                x1f.a();
            }
        }
    }

    public final boolean d(H5 h5) {
        Set setC = AbstractC2780ub0.c();
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(this);
        while (!arrayDeque.isEmpty()) {
            H5 h6 = (H5) arrayDeque.pop();
            if (h6 == h5) {
                return true;
            }
            setC.add(h6);
            for (H5 h7 : h6.t()) {
                if (!setC.contains(h7)) {
                    arrayDeque.push(h7);
                }
            }
        }
        return false;
    }

    public final void e(AbstractC0890Uw abstractC0890Uw) {
        int iIndexOf = this.f.indexOf(abstractC0890Uw);
        if (!p && iIndexOf < 0) {
            x1f.a();
            return;
        }
        List<Integer> listSingletonList = Collections.singletonList(Integer.valueOf(iIndexOf));
        if (listSingletonList.isEmpty()) {
            return;
        }
        LinkedList linkedList = new LinkedList();
        int iIntValue = 0;
        for (Integer num : listSingletonList) {
            if (!p && num.intValue() < iIntValue) {
                x1f.a();
                return;
            }
            linkedList.addAll(this.f.subList(iIntValue, num.intValue()));
            AbstractC0890Uw abstractC0890Uw2 = (AbstractC0890Uw) this.f.get(num.intValue());
            if (!AbstractC0890Uw.h && abstractC0890Uw2.d == null) {
                x1f.a();
                return;
            } else {
                abstractC0890Uw2.d = null;
                iIntValue = num.intValue() + 1;
            }
        }
        if (iIntValue < this.f.size()) {
            LinkedList linkedList2 = this.f;
            linkedList.addAll(linkedList2.subList(iIntValue, linkedList2.size()));
        }
        if (!p) {
            if (this.f.size() != listSingletonList.size() + linkedList.size()) {
                x1f.a();
                return;
            }
        }
        this.f = linkedList;
    }

    public final H5 f() {
        boolean z = false;
        H5 h5A = this;
        while (this.G()) {
            this = C5.a(this);
            if (z) {
                h5A = C5.a(h5A);
            }
            z = !z;
            if (this == h5A) {
                return null;
            }
        }
        return this;
    }

    public final AbstractC0890Uw g() {
        if (!p && !x()) {
            x1f.a();
            return null;
        }
        J5 j5 = new J5(this, this.f.size());
        while (j5.b.hasPrevious()) {
            AbstractC0890Uw abstractC0890Uw = (AbstractC0890Uw) j5.b.previous();
            if (abstractC0890Uw.g()) {
                return abstractC0890Uw;
            }
        }
        return null;
    }

    public final String h(H5 h5) {
        if (!c(h5)) {
            return XmlPullParser.NO_NAMESPACE;
        }
        if (!p && !c(h5)) {
            x1f.a();
            return null;
        }
        int iIndexOf = this.b.indexOf(h5);
        Iterator it = this.e.c.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((Integer) it.next()).intValue() == iIndexOf) {
                i++;
            }
        }
        if (p || i > 0) {
            return new String(new char[i]).replace("\u0000", "*");
        }
        x1f.a();
        return null;
    }

    public final C2490r8 i() {
        if (!x()) {
            return C2490r8.f;
        }
        AbstractC0551Hu abstractC0551Hu = this.e.c;
        final ArrayList arrayList = this.b;
        Objects.requireNonNull(arrayList);
        return new C2490r8(this.e.b, C2847vL.a((Collection) abstractC0551Hu, new Function() { // from class: h36
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (H5) arrayList.get(((Integer) obj).intValue());
            }
        }));
    }

    public final H5 j() {
        if (this.f.size() == 2) {
            AbstractC0890Uw abstractC0890Uw = (AbstractC0890Uw) this.f.get(0);
            abstractC0890Uw.getClass();
            if ((abstractC0890Uw instanceof C2766uP) && h().K1()) {
                if (p || !x()) {
                    return u();
                }
                x01.a("Trampoline should not have catch handlers");
                return null;
            }
        }
        return null;
    }

    public LinkedList<AbstractC0890Uw> k() {
        return this.f;
    }

    public List<H5> l() {
        if (!p) {
            I();
        }
        return this.c;
    }

    public List<H5> m() {
        if (!p) {
            J();
        }
        return this.b;
    }

    public final List n() {
        if (!x()) {
            return this.b;
        }
        final C0473Eu c0473EuG = AbstractC0551Hu.g();
        Consumer consumer = new Consumer() { // from class: u26
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c0473EuG.a((H5) obj);
            }
        };
        for (int size = this.b.size() - L(); size < this.b.size(); size++) {
            consumer.accept((H5) this.b.get(size));
        }
        return c0473EuG.a();
    }

    public int o() {
        if (p || this.g >= 0) {
            return this.g;
        }
        x1f.a();
        return 0;
    }

    public final String p() {
        int i = this.g;
        if (i < 0) {
            return "<unknown>";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        return sb.toString();
    }

    public List<PW> q() {
        return this.h;
    }

    public final AbstractC2004lX r() {
        return ((AbstractC0890Uw) this.f.get(0)).getPosition();
    }

    public List<H5> s() {
        ArrayList arrayList = this.c;
        boolean z = C2847vL.a;
        return C2752uB.b() ? Collections.unmodifiableList(arrayList) : arrayList;
    }

    public List<H5> t() {
        ArrayList arrayList = this.b;
        boolean z = C2847vL.a;
        return C2752uB.b() ? Collections.unmodifiableList(arrayList) : arrayList;
    }

    public final String toString() {
        return P();
    }

    public H5 u() {
        if (p || z()) {
            return (H5) C2847vL.b(this.b);
        }
        x1f.a();
        return null;
    }

    public final H5 v() {
        if (p || B()) {
            return (H5) this.c.get(0);
        }
        x1f.a();
        return null;
    }

    public final H5 w() {
        if (p || C()) {
            return (H5) this.b.get(0);
        }
        x1f.a();
        return null;
    }

    public boolean x() {
        if (p || this.e != null) {
            return !this.e.isEmpty();
        }
        x1f.a();
        return false;
    }

    public final boolean y() {
        ArrayList arrayList = this.h;
        return (arrayList == null || arrayList.isEmpty()) ? false : true;
    }

    public final boolean z() {
        return L() == 1;
    }

    public final void i(H5 h5) {
        a(h5, (C3242a) null, C0822Sg.b(), MX.c);
    }

    public final void g(H5 h5) {
        boolean z = p;
        if (!z && this.b.contains(h5)) {
            x1f.a();
        } else if (!z && h5.c.contains(this)) {
            x1f.a();
        } else {
            m().add(h5);
            h5.l().add(this);
        }
    }

    public final void j(H5 h5) {
        int iIndexOf = this.b.indexOf(h5);
        if (p || iIndexOf >= 0) {
            a(new C0919Vz(new int[]{iIndexOf}));
        } else {
            x01.a("removeSuccessor did not find the successor to remove");
        }
    }

    public final void d() {
        this.m--;
        this.l--;
    }

    public final /* synthetic */ Iterator d(AbstractC0890Uw abstractC0890Uw) {
        return new D5(this, abstractC0890Uw);
    }

    public AbstractC2925wD h() {
        boolean z = p;
        if (!z && !this.i) {
            x1f.a();
            return null;
        }
        if (!z) {
            LinkedList linkedList = this.f;
            if (!((AbstractC0890Uw) linkedList.get(linkedList.size() - 1)).d2()) {
                x1f.a();
                return null;
            }
        }
        LinkedList linkedList2 = this.f;
        return ((AbstractC0890Uw) linkedList2.get(linkedList2.size() - 1)).i0();
    }

    public static /* synthetic */ void e(H5 h5) {
        boolean z = p;
        if (!z && !h5.c.isEmpty()) {
            x1f.a();
        } else {
            if (z || h5.b.isEmpty()) {
                return;
            }
            x1f.a();
        }
    }

    public final void e() {
        G5 g5 = new G5();
        HashMap map = new HashMap();
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            PW pw = (PW) it.next();
            C2119mo c2119mo = new C2119mo(g5, pw);
            PW pw2 = (PW) map.get(c2119mo);
            if (pw2 == null) {
                map.put(c2119mo, pw);
            } else {
                if (pw.r() != pw2.r()) {
                    if (pw2.r() == null) {
                        pw2.a(pw.r());
                    } else if (pw.r() != null) {
                        if (!p && (!pw.y() || !pw2.y())) {
                            x1f.a();
                            return;
                        }
                    }
                }
                pw.f(pw2);
                for (C2543rl0 c2543rl0 : pw.c0()) {
                    c2543rl0.f.remove(pw);
                    c2543rl0.g = null;
                }
                it.remove();
            }
        }
    }

    public void c(int i) {
        if (p || i >= 0) {
            this.g = i;
        } else {
            x1f.a();
        }
    }

    public final void c(E5 e5) {
        ((C2031lm) e5).e = true;
    }

    public boolean c(H5 h5) {
        int iIndexOf;
        int iK = K();
        return iK != 0 && (iIndexOf = this.b.indexOf(h5)) >= 0 && iIndexOf < iK;
    }

    public final Iterator c(AbstractC0890Uw abstractC0890Uw) {
        return new J5(this, abstractC0890Uw);
    }

    public final void a(final C0333y c0333y, final com.android.tools.r8.graph.B5 b5, final Ol0 ol0) {
        if (p || this.f.stream().allMatch(new Predicate() { // from class: c36
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return H5.a(c0333y, b5, ol0, (AbstractC0890Uw) obj);
            }
        })) {
            return;
        }
        x1f.a();
    }

    public static /* synthetic */ boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, Ol0 ol0, AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.a(c0333y, b5, ol0);
        return true;
    }

    public final void a(AbstractC2925wD abstractC2925wD, C0705Nt c0705Nt) {
        K5 k5A = a(c0705Nt, k().size());
        k5A.previous();
        k5A.a(abstractC2925wD, (Set<C2543rl0>) null);
    }

    public final AbstractC1597gi0 a(Object obj, BiFunction biFunction) {
        AbstractC1597gi0 c1512fi0 = new C1512fi0(obj);
        for (H5 h5 : s()) {
            if (h5.c(this)) {
                c1512fi0 = (AbstractC1597gi0) biFunction.apply(h5, c1512fi0.b().f());
                if (c1512fi0.c()) {
                    break;
                }
            }
        }
        return c1512fi0;
    }

    public final AbstractC1597gi0 a(BiFunction biFunction) {
        AbstractC1597gi0 c1512fi0 = new C1512fi0(null);
        for (int size = this.b.size() - L(); size < this.b.size(); size++) {
            c1512fi0 = (AbstractC1597gi0) biFunction.apply(this.b.get(size), c1512fi0.b().f());
            if (c1512fi0.c()) {
                return c1512fi0;
            }
        }
        return c1512fi0;
    }

    public K5 a(C0705Nt c0705Nt) {
        return new K5(c0705Nt.i, this);
    }

    public final void a(E5 e5) {
        if (this.d == null) {
            this.d = Collections.newSetFromMap(new WeakHashMap());
        }
        this.d.add(e5);
    }

    public final void a(H5 h5, C3242a c3242a, Consumer consumer, Predicate predicate) {
        int iIndexOf = this.c.indexOf(h5);
        if (!p && iIndexOf < 0) {
            x01.a("removePredecessor did not find the predecessor to remove");
            return;
        }
        l().remove(iIndexOf);
        if (y()) {
            Iterator<PW> it = q().iterator();
            while (it.hasNext()) {
                it.next().a(iIndexOf, c3242a, predicate);
            }
            ArrayList arrayList = new ArrayList();
            for (PW pw : q()) {
                if (pw.d0()) {
                    arrayList.add(pw);
                }
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ((PW) it2.next()).a((C0602Jt) null, c3242a, consumer, predicate);
            }
        }
    }

    public final void a(int i, int i2) {
        boolean z = p;
        if (!z && i == i2) {
            x1f.a();
            return;
        }
        if (x()) {
            ArrayList arrayList = new ArrayList(this.e.c);
            if (!z && arrayList.contains(Integer.valueOf(i)) != arrayList.contains(Integer.valueOf(i2))) {
                x01.a("Swapping normal successor and catch handler");
                return;
            }
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                if (((Integer) arrayList.get(i3)).intValue() == i) {
                    arrayList.set(i3, Integer.valueOf(i2));
                } else if (((Integer) arrayList.get(i3)).intValue() == i2) {
                    arrayList.set(i3, Integer.valueOf(i));
                }
            }
            this.e = new C2490r8(this.e.b, arrayList);
        }
        List<H5> listM = m();
        H5 h5 = listM.get(i);
        listM.set(i, listM.get(i2));
        listM.set(i2, h5);
    }

    public final void a(H5 h5, H5 h6) {
        for (int i = 0; i < this.c.size(); i++) {
            if (this.c.get(i) == h5) {
                if (!p) {
                    I();
                }
                l().set(i, h6);
                return;
            }
        }
        if (p) {
            return;
        }
        x01.a("replaceSuccessor did not find the predecessor to replace");
    }

    public final void a(C0919Vz c0919Vz) {
        if (c0919Vz.isEmpty()) {
            return;
        }
        if (!p) {
            boolean z = C2847vL.a;
            for (int i = c0919Vz.c - 1; i > 0; i--) {
                if (Integer.valueOf(c0919Vz.i(i)).compareTo(Integer.valueOf(c0919Vz.i(i - 1))) < 0) {
                    x1f.a();
                    return;
                }
            }
        }
        List<H5> listM = m();
        ArrayList arrayList = new ArrayList(listM);
        listM.clear();
        Iterator itO = c0919Vz.o(0);
        int i2 = 0;
        while (itO.hasNext()) {
            int iQ = ((W) itO).q();
            listM.addAll(arrayList.subList(i2, iQ));
            i2 = iQ + 1;
        }
        listM.addAll(arrayList.subList(i2, arrayList.size()));
        if (x()) {
            C2490r8 c2490r8 = this.e;
            AbstractC0551Hu abstractC0551Hu = c2490r8.c;
            AbstractC0551Hu abstractC0551Hu2 = c2490r8.b;
            int size = c2490r8.size();
            ArrayList arrayList2 = new ArrayList(size);
            ArrayList arrayList3 = new ArrayList(size);
            for (int i3 = 0; i3 < abstractC0551Hu.size(); i3++) {
                int iIntValue = ((Integer) abstractC0551Hu.get(i3)).intValue();
                Iterator itO2 = c0919Vz.o(0);
                int i4 = 0;
                while (true) {
                    if (itO2.hasNext()) {
                        int iQ2 = ((W) itO2).q();
                        if (iIntValue == iQ2) {
                            break;
                        } else if (iIntValue >= iQ2) {
                            i4++;
                        }
                    }
                    arrayList3.add(Integer.valueOf(iIntValue - i4));
                    arrayList2.add((com.android.tools.r8.graph.I2) abstractC0551Hu2.get(i3));
                    break;
                }
            }
            if (arrayList3.isEmpty()) {
                this.e = C2490r8.e;
            } else {
                this.e = new C2490r8(arrayList2, arrayList3);
            }
        }
    }

    public final Iterable a(final AbstractC0890Uw abstractC0890Uw) {
        return new Iterable() { // from class: e36
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return this.b.c(abstractC0890Uw);
            }
        };
    }

    public final boolean a(int i) {
        if (p || i != 0) {
            return (this.n & i) != 0;
        }
        x1f.a();
        return false;
    }

    public final void a(PW pw, C3242a c3242a, Consumer consumer) {
        HashMap map;
        this.h.remove(pw);
        if (!p && (map = this.o) != null && map.containsValue(pw)) {
            iti.a("Attempt to remove Phi ", pw, " which is present in currentDefinitions");
            return;
        }
        if (c3242a != null) {
            c3242a.remove(pw);
        }
        consumer.accept(pw);
    }

    public final void a(Collection collection) {
        HashMap map;
        if (p || (map = this.o) == null || map.isEmpty()) {
            this.h.removeAll(collection);
        } else {
            x1f.a();
        }
    }

    public void a(AbstractC0890Uw abstractC0890Uw, C0887Ut c0887Ut) {
        if (!p && this.i) {
            x1f.a();
            return;
        }
        this.f.add(abstractC0890Uw);
        c0887Ut.a(abstractC0890Uw);
        abstractC0890Uw.a(this);
    }

    public final void a(C0602Jt c0602Jt) {
        boolean z = p;
        if (!z && this.i) {
            x1f.a();
            return;
        }
        if (!z && this.f.isEmpty()) {
            x1f.a();
            return;
        }
        this.i = true;
        this.j = this.m == 0;
        if (!z) {
            h().getClass();
        }
        if (!z && x()) {
            J5 j5 = new J5(this, this.f.size());
            while (j5.b.hasPrevious()) {
                AbstractC0890Uw abstractC0890Uw = (AbstractC0890Uw) j5.b.previous();
                if (abstractC0890Uw.g()) {
                    break;
                }
                if (!p && abstractC0890Uw.c() != null) {
                    x1f.a();
                    return;
                }
            }
        }
        for (H5 h5 : this.b) {
            boolean z2 = p;
            if (!z2 && h5.m <= 0) {
                x1f.a();
                return;
            }
            int i = h5.m - 1;
            h5.m = i;
            if (i == 0) {
                if (!z2 && h5.l != h5.c.size()) {
                    x1f.a();
                    return;
                }
                for (Map.Entry entry : h5.k.entrySet()) {
                    int iIntValue = ((Integer) entry.getKey()).intValue();
                    if (iIntValue < 0) {
                        iIntValue = -(iIntValue + 1);
                    }
                    ((PW) entry.getValue()).a(iIntValue, c0602Jt);
                }
                h5.j = true;
                h5.k.clear();
            }
        }
    }

    public final void b(E5 e5) {
        ((C2031lm) e5).e = true;
    }

    public static void a(Set set) {
        set.forEach(new Consumer() { // from class: v26
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                H5.e((H5) obj);
            }
        });
    }

    public final void a(com.android.tools.r8.graph.I2 i2) {
        int iIndexOf = this.e.b.indexOf(i2);
        if (iIndexOf >= 0) {
            final int iIntValue = ((Integer) this.e.c.get(iIndexOf)).intValue();
            boolean z = p;
            if (!z && iIntValue < 0) {
                x1f.a();
                return;
            }
            this.e = this.e.a(i2);
            if (i().c.stream().noneMatch(new Predicate() { // from class: d36
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.b.a(iIntValue, (H5) obj);
                }
            })) {
                m().remove(iIntValue);
            }
            if (z) {
                return;
            }
            c();
        }
    }

    public final AbstractC1597gi0 b(Object obj, BiFunction biFunction) {
        AbstractC1597gi0 c1512fi0 = new C1512fi0(obj);
        for (H5 h5 : s()) {
            if (!h5.c(this)) {
                c1512fi0 = (AbstractC1597gi0) biFunction.apply(h5, c1512fi0.b().f());
                if (c1512fi0.c()) {
                    break;
                }
            }
        }
        return c1512fi0;
    }

    public final Iterable b(final AbstractC0890Uw abstractC0890Uw) {
        return new Iterable() { // from class: y26
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return this.b.d(abstractC0890Uw);
            }
        };
    }

    public final void b() {
        this.h = null;
        this.f.forEach(new Consumer() { // from class: i36
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC0890Uw.a((AbstractC0890Uw) obj);
            }
        });
    }

    public final void b(int i) {
        boolean z = p;
        if (!z && i == 0) {
            x1f.a();
            return;
        }
        if (!z && a(i)) {
            x1f.a();
            return;
        }
        this.n |= i;
        if (z || a(i)) {
            return;
        }
        x1f.a();
    }

    public final F5 b(H5 h5) {
        if (p || this.b.indexOf(h5) >= 0) {
            return c(h5) ? F5.d : F5.c;
        }
        x1f.a();
        return null;
    }

    public final /* synthetic */ boolean a(int i, H5 h5) {
        return h5 == this.b.get(i);
    }

    public final Set a(H5 h5, C2031lm c2031lm, C3242a c3242a) {
        boolean z = p;
        if (!z && c3242a == null) {
            x1f.a();
            return null;
        }
        if (!z && !this.b.contains(h5)) {
            x1f.a();
            return null;
        }
        if (!z && h5.c.size() != 1) {
            x1f.a();
            return null;
        }
        if (!z && h5.c.get(0) != this) {
            x1f.a();
            return null;
        }
        Set set = (Set) c2031lm.a(h5, AbstractC2780ub0.c());
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((H5) it.next()).a(c3242a, C0822Sg.b(), new b36(set));
        }
        if (!p) {
            a(set);
        }
        return set;
    }

    public final void a(C3242a c3242a, Consumer consumer, Predicate predicate) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((H5) it.next()).a(this, c3242a, consumer, predicate);
        }
        m().clear();
        Iterator it2 = this.c.iterator();
        while (it2.hasNext()) {
            ((H5) it2.next()).j(this);
        }
        l().clear();
        for (PW pw : q()) {
            c3242a.a(predicate, pw);
            for (C2543rl0 c2543rl0 : pw.c0()) {
                c2543rl0.f.remove(pw);
                c2543rl0.g = null;
            }
            c3242a.b.remove(pw);
            consumer.accept(pw);
        }
        q().clear();
        for (AbstractC0890Uw abstractC0890Uw : k()) {
            if (abstractC0890Uw.c1()) {
                C2543rl0 c2543rl0C = abstractC0890Uw.c();
                c3242a.a(predicate, c2543rl0C);
                c2543rl0C.d();
                abstractC0890Uw.d(null);
                c3242a.b.remove(c2543rl0C);
                consumer.accept(c2543rl0C);
            }
            for (C2543rl0 c2543rl1 : abstractC0890Uw.c) {
                c2543rl1.d.remove(abstractC0890Uw);
                c2543rl1.e = null;
            }
            Iterator it3 = abstractC0890Uw.S0().iterator();
            while (it3.hasNext()) {
                ((C2543rl0) it3.next()).c(abstractC0890Uw);
            }
        }
    }

    public final void a(H5 h5, com.android.tools.r8.graph.I2 i2) {
        int size;
        if (a()) {
            if (x()) {
                if (this.e.b.contains(i2)) {
                    return;
                }
                int iIndexOf = this.b.indexOf(h5);
                if (iIndexOf < 0) {
                    List<H5> listM = m();
                    int size2 = listM.size();
                    int iL = L();
                    if (iL > 0) {
                        size = size2 - iL;
                        listM.add(size, h5);
                    } else {
                        size = listM.size();
                        listM.add(h5);
                    }
                    iIndexOf = size;
                    h5.l().add(this);
                }
                C2490r8 c2490r8 = this.e;
                Integer numValueOf = Integer.valueOf(iIndexOf);
                if (!C2490r8.g) {
                    if (c2490r8.b.contains(i2)) {
                        x1f.a();
                        return;
                    }
                } else {
                    c2490r8.getClass();
                }
                this.e = new C2490r8(AbstractC0551Hu.g().b((Iterable) c2490r8.b).a(i2).a(), AbstractC0551Hu.g().b((Iterable) c2490r8.c).a(numValueOf).a());
                return;
            }
            if (!p && this.f.stream().filter(new z26()).count() != 1) {
                x1f.a();
                return;
            }
            m().add(0, h5);
            h5.l().add(this);
            this.e = new C2490r8(new Bc0(i2), new Bc0(0));
        }
    }

    public final boolean a(XR xr, AbstractC3148ys abstractC3148ys) {
        if (!p && !x()) {
            x1f.a();
            return false;
        }
        ArrayList arrayList = new ArrayList(this.e.b.size());
        boolean z = false;
        for (com.android.tools.r8.graph.I2 i2 : this.e.b) {
            com.android.tools.r8.graph.I2 i2C = xr.c(abstractC3148ys, i2);
            arrayList.add(i2C);
            z |= i2C != i2;
        }
        if (z) {
            this.e = new C2490r8(arrayList, this.e.c);
        }
        return z;
    }

    public final void a(int i, PW pw, F5 f5) {
        if ((f5 == F5.d ? (C2543rl0) this.o.get(Integer.valueOf(-(i + 1))) : null) != null) {
            i = -(i + 1);
        }
        if (p || !this.k.containsKey(Integer.valueOf(i))) {
            this.k.put(Integer.valueOf(i), pw);
        } else {
            x1f.a();
        }
    }

    public final C2543rl0 a(int i, F5 f5) {
        C2543rl0 c2543rl0 = f5 == F5.d ? (C2543rl0) this.o.get(Integer.valueOf(-(i + 1))) : null;
        if (c2543rl0 != null) {
            if (c2543rl0 == C2543rl0.p) {
                return null;
            }
            return c2543rl0;
        }
        return (C2543rl0) this.o.get(Integer.valueOf(i));
    }

    public final void a(int i, C2543rl0 c2543rl0, F5 f5) {
        if ((f5 == F5.d ? (C2543rl0) this.o.get(Integer.valueOf(-(i + 1))) : null) != null) {
            i = -(i + 1);
        }
        C2543rl0 c2543rl1 = (C2543rl0) this.o.get(Integer.valueOf(i));
        if (c2543rl0.j()) {
            c2543rl0.m().v.add(this.o);
        }
        if (!p && i < 0) {
            for (Integer num : this.o.keySet()) {
                if (!p && num.intValue() < 0 && num.intValue() != i) {
                    x1f.a();
                    return;
                }
            }
        }
        this.o.put(Integer.valueOf(i), c2543rl0);
        if (c2543rl1 == null || !c2543rl1.j() || this.o.values().contains(c2543rl1)) {
            return;
        }
        c2543rl1.m().v.remove(this.o);
    }

    public final void a(int i, C2543rl0 c2543rl0, int i2) {
        if (i2 == 2) {
            C2543rl0 c2543rl1 = (C2543rl0) this.o.get(Integer.valueOf(i));
            if (!p && i < 0) {
                for (Integer num : this.o.keySet()) {
                    if (!p && num.intValue() < 0 && num.intValue() != i) {
                        x1f.a();
                        return;
                    }
                }
            }
            HashMap map = this.o;
            Integer numValueOf = Integer.valueOf(-(i + 1));
            if (c2543rl1 == null) {
                c2543rl1 = C2543rl0.p;
            }
            map.put(numValueOf, c2543rl1);
        }
        a(i, c2543rl0, F5.b);
    }

    public static H5 a(int i, AbstractC2004lX abstractC2004lX, C0887Ut c0887Ut, H5 h5) {
        H5 h5A = a(i, abstractC2004lX, c0887Ut);
        h5A.m().add(h5);
        return h5A;
    }

    public static H5 a(int i, AbstractC2004lX abstractC2004lX, C0887Ut c0887Ut) {
        H5 h5 = new H5();
        h5.a(new C2636ss(), c0887Ut);
        h5.a((C0602Jt) null);
        h5.c(i);
        ((AbstractC0890Uw) h5.f.get(0)).b(abstractC2004lX);
        return h5;
    }

    public boolean a() {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            if (((AbstractC0890Uw) it.next()).g()) {
                return true;
            }
        }
        return false;
    }

    public K5 a(C0705Nt c0705Nt, int i) {
        return new K5(c0705Nt.i, this, i);
    }

    public final K5 a(C0705Nt c0705Nt, AbstractC0890Uw abstractC0890Uw) {
        return new K5(c0705Nt.i, this, abstractC0890Uw);
    }

    public final H5 a(int i, boolean z) {
        boolean zX = x();
        H5 h5 = new H5();
        h5.c(i);
        h5.m().addAll(this.b);
        Iterator<H5> it = h5.t().iterator();
        while (it.hasNext()) {
            it.next().a(this, h5);
        }
        m().clear();
        h5.e = this.e;
        this.e = C2490r8.e;
        if (z && zX) {
            for (H5 h6 : a(h5)) {
                h5.m().remove(h6);
                h6.getClass();
                h6.a(h5, (C3242a) null, C0822Sg.b(), MX.c);
            }
            h5.e = C2490r8.e;
        }
        g(h5);
        h5.i = true;
        h5.j = true;
        return h5;
    }

    public final void a(C0705Nt c0705Nt, ListIterator listIterator, H5 h5, C2752uB c2752uB) {
        AbstractC2624sj0 abstractC2624sj0;
        C2766uP c2766uP;
        com.android.tools.r8.graph.I2 i2;
        C2490r8 c2490r8 = this.e;
        if (c2490r8 != null) {
            com.android.tools.r8.graph.B1 b1 = c2752uB.a;
            if (c2490r8.b.size() > 0) {
                AbstractC0551Hu abstractC0551Hu = c2490r8.b;
                if (abstractC0551Hu.get(abstractC0551Hu.size() - 1) == b1.o3) {
                    return;
                }
            }
        }
        Iterator it = a(h5).iterator();
        while (it.hasNext()) {
            H5 h6 = (H5) it.next();
            List<H5> listL = h6.l();
            AbstractC0890Uw abstractC0890Uw = (AbstractC0890Uw) h6.f.get(0);
            abstractC0890Uw.getClass();
            boolean z = abstractC0890Uw instanceof C2766uP;
            AbstractC2004lX position = ((AbstractC0890Uw) h6.f.get(0)).getPosition();
            if (z) {
                C2766uP c2766uPN0 = ((AbstractC0890Uw) h6.f.get(0)).n0();
                AbstractC2624sj0 abstractC2624sj0A = c2766uPN0.a();
                i2 = c2766uPN0.i;
                if (!p && !c2766uPN0.S0().isEmpty()) {
                    x1f.a();
                    return;
                } else {
                    h6.k().remove(0);
                    abstractC2624sj0 = abstractC2624sj0A;
                    c2766uP = c2766uPN0;
                }
            } else {
                abstractC2624sj0 = null;
                c2766uP = null;
                i2 = null;
            }
            ArrayList arrayList = new ArrayList(listL.size());
            ArrayList arrayList2 = new ArrayList(listL.size());
            for (H5 h7 : listL) {
                if (h7.c(h6)) {
                    H5 h8 = new H5();
                    Iterator it2 = it;
                    h8.c(c0705Nt.f.a());
                    arrayList.add(h8);
                    if (z) {
                        C2543rl0 c2543rl0A = c0705Nt.a(abstractC2624sj0, c2766uP.k());
                        arrayList2.add(c2543rl0A);
                        C2766uP c2766uP2 = new C2766uP(c2543rl0A, i2, c2752uB);
                        h8.a(c2766uP2, c0705Nt.i);
                        c2766uP2.b(position);
                    }
                    C2636ss c2636ss = new C2636ss();
                    c2636ss.b(position);
                    h8.a(c2636ss, c0705Nt.i);
                    h8.a((C0602Jt) null);
                    h8.m().add(h6);
                    h8.l().add(h7);
                    h7.b(h6, h8);
                    if (listIterator == null) {
                        c0705Nt.d.add(h8);
                    } else {
                        listIterator.add(h8);
                    }
                    it = it2;
                    z = z;
                } else {
                    n33.a("Invalid block structure: catch block reachable via non-exceptional flow.");
                    return;
                }
            }
            Iterator it3 = it;
            boolean z2 = z;
            listL.clear();
            listL.addAll(arrayList);
            if (z2) {
                PW pw = new PW(c0705Nt.e.a(), h6, abstractC2624sj0, c2766uP.k(), PW.a.b);
                pw.a(arrayList2);
                c2766uP.c().f(pw);
            }
            it = it3;
        }
    }

    public static boolean a(C0785Qv c0785Qv, boolean z) {
        if (!z) {
            return true;
        }
        boolean z2 = p;
        if (!z2 && !c0785Qv.c1()) {
            x1f.a();
            return false;
        }
        if (!z2 && c0785Qv.c().x()) {
            x1f.a();
            return false;
        }
        if (!z2 && c0785Qv.c().A()) {
            x1f.a();
            return false;
        }
        if (z2 || c0785Qv.c().b0().stream().allMatch(new Predicate() { // from class: a36
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((AbstractC0890Uw) obj).s2();
            }
        })) {
            return true;
        }
        x1f.a();
        return false;
    }
}
