package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.ir.optimize.C3242a;
import defpackage.n33;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class PW extends C2543rl0 implements InterfaceC0994Yw {
    public static final /* synthetic */ boolean w = true;
    public H5 r;
    public final ArrayList s;
    public a t;
    public boolean u;
    public ArrayList v;

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class a {
        public static final a b = new a(0, "NORMAL");
        public static final a c = new a(1, "DEBUG");

        public a(int i, String str) {
            super(str, i);
        }
    }

    public PW(int i, H5 h5, AbstractC2624sj0 abstractC2624sj0, C0230j0 c0230j0, a aVar) {
        super(i, abstractC2624sj0, c0230j0);
        this.s = new ArrayList();
        this.v = new ArrayList();
        this.r = h5;
        this.t = aVar;
        h5.h.add(this);
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final boolean J() {
        return false;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final boolean P() {
        if (!w) {
            c(AbstractC2780ub0.c());
        }
        return this.u;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final void S() {
        this.t = a.b;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final boolean T() {
        return !P();
    }

    public final boolean a(C0602Jt c0602Jt, C3242a c3242a, Consumer consumer, Predicate predicate) {
        C2543rl0 c2543rl0 = null;
        for (C2543rl0 c2543rl1 : this.s) {
            if (c2543rl1 != c2543rl0 && c2543rl1 != this) {
                if (c2543rl0 != null) {
                    if (!w && d0()) {
                        x1f.a();
                    }
                    return false;
                }
                c2543rl0 = c2543rl1;
            }
        }
        boolean z = w;
        if (!z && !d0()) {
            x1f.a();
            return false;
        }
        if (c2543rl0 == null) {
            return false;
        }
        if (r() != c2543rl0.r() && r() != null) {
            if (c2543rl0.r() != null) {
                if (!z && (!y() || !c2543rl0.y())) {
                    x1f.a();
                }
                return false;
            }
            c2543rl0.a(r());
        }
        if (c0602Jt != null && this.o.G()) {
            AbstractC2624sj0 abstractC2624sj0 = this.o;
            abstractC2624sj0.getClass();
            if (!(abstractC2624sj0 instanceof C1720i7)) {
                c2543rl0.a(Gl0.a(this.o), c0602Jt.m, c0602Jt.o.M().i);
            }
        }
        if (c3242a != null) {
            c3242a.a(predicate, this);
        }
        for (C2543rl0 c2543rl2 : this.s) {
            c2543rl2.f.remove(this);
            c2543rl2.g = null;
        }
        ArrayList<Map> arrayList = this.v;
        if (arrayList != null) {
            for (Map map : arrayList) {
                for (Map.Entry entry : map.entrySet()) {
                    if (entry.getValue() == this) {
                        entry.setValue(c2543rl0);
                        if (c2543rl0.j()) {
                            c2543rl0.m().v.add(map);
                        }
                    }
                }
            }
        }
        Set setA0 = a0();
        f(c2543rl0);
        Iterator it = setA0.iterator();
        while (it.hasNext()) {
            ((PW) it.next()).a(c0602Jt, c3242a, consumer, predicate);
        }
        this.r.a(this, c3242a, consumer);
        return true;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final AbstractC2624sj0 b(C0333y c0333y) {
        Set setC = AbstractC2780ub0.c();
        setC.add(this);
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(this);
        while (!arrayDeque.isEmpty()) {
            PW pw = (PW) arrayDeque.removeFirst();
            if (!w && !setC.contains(pw)) {
                x1f.a();
                return null;
            }
            Iterator<C2543rl0> it = pw.c0().iterator();
            while (it.hasNext()) {
                PW pwM = it.next().h().m();
                if (pwM != null && setC.add(pwM)) {
                    arrayDeque.addLast(pwM);
                }
            }
        }
        Set setC2 = AbstractC2780ub0.c();
        AbstractC2624sj0 abstractC2624sj0F = AbstractC2624sj0.f();
        Iterator it2 = setC.iterator();
        while (it2.hasNext()) {
            for (C2543rl0 c2543rl0 : ((PW) it2.next()).c0()) {
                if (!c2543rl0.h().j() && setC2.add(c2543rl0)) {
                    abstractC2624sj0F = abstractC2624sj0F.a(c0333y, c2543rl0.b(c0333y));
                }
            }
        }
        return (t().I() && t().x()) ? abstractC2624sj0F.d().P() : abstractC2624sj0F;
    }

    public final void c(final Set set) {
        set.add(this);
        this.s.forEach(new Consumer() { // from class: kva
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(set, (C2543rl0) obj);
            }
        });
    }

    public List<C2543rl0> c0() {
        return this.s;
    }

    public AbstractC2624sj0 d(C0333y c0333y) {
        AbstractC2624sj0 abstractC2624sj0F = AbstractC2624sj0.f();
        Iterator<C2543rl0> it = c0().iterator();
        while (it.hasNext()) {
            abstractC2624sj0F = abstractC2624sj0F.a(c0333y, it.next().t());
        }
        return abstractC2624sj0F;
    }

    public boolean d0() {
        C2543rl0 c2543rl0 = null;
        for (C2543rl0 c2543rl1 : this.s) {
            if (c2543rl1 != c2543rl0 && c2543rl1 != this) {
                if (c2543rl0 != null) {
                    return false;
                }
                c2543rl0 = c2543rl1;
            }
        }
        return true;
    }

    public final String e0() {
        StringBuilder sb = new StringBuilder();
        sb.append("v");
        sb.append(this.b);
        if (y()) {
            sb.append("(");
            sb.append(r());
            sb.append(")");
        }
        sb.append(" <- phi");
        Wf0.a(sb, C2847vL.a((Collection) this.s, new Function() { // from class: jva
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C2543rl0) obj).toString();
            }
        }));
        sb.append(" : ");
        sb.append(t());
        return sb.toString();
    }

    public final void f0() {
        if (!w && C()) {
            x1f.a();
            return;
        }
        for (C2543rl0 c2543rl0 : c0()) {
            c2543rl0.f.remove(this);
            c2543rl0.g = null;
        }
        H5 h5 = this.r;
        h5.getClass();
        h5.a(this, (C3242a) null, C0822Sg.b());
    }

    public final void g0() {
        a((C0602Jt) null, (C3242a) null, C0822Sg.b(), MX.c);
    }

    @Override // com.android.tools.r8.internal.C2543rl0, com.android.tools.r8.internal.InterfaceC0994Yw
    public final H5 i() {
        return this.r;
    }

    @Override // com.android.tools.r8.internal.C2543rl0, com.android.tools.r8.internal.InterfaceC0994Yw
    public final boolean j() {
        return true;
    }

    @Override // com.android.tools.r8.internal.C2543rl0, com.android.tools.r8.internal.InterfaceC0994Yw
    public final PW m() {
        return this;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final boolean w() {
        return this.r != null;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final boolean c(Predicate predicate) {
        return false;
    }

    public final void b(Gl0 gl0) {
        if (a(gl0) != null) {
            return;
        }
        throw new NB("Type information in locals-table is inconsistent. Cannot constrain type: " + this.o + " for value: " + this + " by constraint " + gl0 + ".");
    }

    public final C2543rl0 b(int i) {
        return (C2543rl0) this.s.get(i);
    }

    public final void a(int i, C0602Jt c0602Jt) {
        int i2;
        C0602Jt c0602Jt2;
        C2543rl0 c2543rl0A;
        if (!w && !this.s.isEmpty()) {
            x1f.a();
            return;
        }
        if (this.r.s().size() != 0) {
            Gl0 gl0A = C2539rj0.a(this.o);
            ArrayList<C2543rl0> arrayList = new ArrayList(this.r.s().size());
            for (H5 h5 : this.r.s()) {
                F5 f5B = h5.b(this.r);
                a aVar = this.t;
                c0602Jt.e(i);
                C2543rl0 c2543rl0A2 = h5.a(i, f5B);
                if (c2543rl0A2 != null) {
                    i2 = i;
                    c2543rl0A = c2543rl0A2;
                    c0602Jt2 = c0602Jt;
                } else {
                    i2 = i;
                    c0602Jt2 = c0602Jt;
                    c2543rl0A = c0602Jt2.a(i2, h5, f5B, gl0A, aVar);
                }
                arrayList.add(c2543rl0A);
                c0602Jt = c0602Jt2;
                i = i2;
            }
            C0602Jt c0602Jt3 = c0602Jt;
            if (this.t == a.c) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    b(C2539rj0.a(((C2543rl0) it.next()).t()));
                }
            }
            for (C2543rl0 c2543rl0 : arrayList) {
                c2543rl0.a(gl0A, c0602Jt3.m, c0602Jt3.o.M().i);
                this.s.add(c2543rl0);
                c2543rl0.f.add(this);
                c2543rl0.g = null;
            }
            a(c0602Jt3, (C3242a) null, C0822Sg.b(), MX.c);
            return;
        }
        n33.a("Undefined value encountered during compilation. This is typically caused by invalid dex input that uses a register that is not defined on all control-flow paths leading to the use.");
    }

    public void a(List<C2543rl0> list) {
        a((List) list, true);
    }

    public final void a(List list, boolean z) {
        if (!w && !this.s.isEmpty()) {
            x1f.a();
            return;
        }
        if (list.size() != 0) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                C2543rl0 c2543rl0 = (C2543rl0) it.next();
                this.s.add(c2543rl0);
                c2543rl0.f.add(this);
                c2543rl0.g = null;
            }
            if (z) {
                g0();
                return;
            }
            return;
        }
        n33.a("Undefined value encountered during compilation. This is typically caused by invalid dex input that uses a register that is not defined on all control-flow paths leading to the use.");
    }

    public final void a(int i, C3242a c3242a, Predicate predicate) {
        C2543rl0 c2543rl0 = (C2543rl0) this.s.get(i);
        c2543rl0.f.remove(this);
        c2543rl0.g = null;
        this.s.remove(i);
        if (c3242a == null || predicate.test(this.r)) {
            return;
        }
        c3242a.b.add(this);
    }

    public final void a(int i, C2543rl0 c2543rl0, C3242a c3242a) {
        C2543rl0 c2543rl1 = (C2543rl0) this.s.get(i);
        this.s.set(i, c2543rl0);
        c2543rl0.f.add(this);
        c2543rl0.g = null;
        c2543rl1.f.remove(this);
        c2543rl1.g = null;
        if (c3242a != null) {
            c3242a.b.add(this);
        }
    }

    public final void a(C2543rl0 c2543rl0, C2543rl0 c2543rl1, Set set) {
        for (int i = 0; i < this.s.size(); i++) {
            if (this.s.get(i) == c2543rl0) {
                this.s.set(i, c2543rl1);
                c2543rl1.f.add(this);
                c2543rl1.g = null;
            }
        }
        if (set != null) {
            set.add(this);
        }
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final void a(Gl0 gl0, com.android.tools.r8.graph.B5 b5, C2742u50 c2742u50) {
        if (this.t == a.c) {
            b(gl0);
        }
        super.a(gl0, b5, c2742u50);
    }

    public final /* synthetic */ void a(Set set, C2543rl0 c2543rl0) {
        if (c2543rl0.j()) {
            if (w || set.contains(c2543rl0)) {
                return;
            }
            c2543rl0.m().c(set);
            return;
        }
        if (w || c2543rl0.P() == this.u) {
            return;
        }
        x1f.a();
    }

    public com.android.tools.r8.graph.I2 a(C0333y c0333y, Nj0 nj0) {
        if (!w && !El0.a(this.o).a()) {
            x1f.a();
            return null;
        }
        HashSet hashSet = new HashSet(this.s.size());
        Iterator it = this.s.iterator();
        while (it.hasNext()) {
            com.android.tools.r8.graph.I2 i2A = nj0.a((C2543rl0) it.next());
            if (i2A != null) {
                hashSet.add(i2A);
            }
        }
        return nj0.a(hashSet);
    }
}
