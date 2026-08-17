package com.android.tools.r8.ir.regalloc;

import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.C0919Vz;
import com.android.tools.r8.internal.C2424qP;
import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.ir.regalloc.c;
import defpackage.nvg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.function.IntConsumer;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class c implements Comparable<c> {
    public static final /* synthetic */ boolean s = true;
    public final C2543rl0 b;
    public c c;
    public c d;
    public final c e;
    public Integer m;
    public final boolean o;
    public final ArrayList f = new ArrayList();
    public final C0919Vz g = new C0919Vz(16);
    public boolean h = false;
    public List i = new ArrayList();
    public final TreeSet j = new TreeSet();
    public int k = -1;
    public int l = Integer.MIN_VALUE;
    public boolean n = false;
    public int p = 65535;
    public int q = Integer.MIN_VALUE;
    public boolean r = false;

    public c(C2543rl0 c2543rl0) {
        boolean z = false;
        this.o = false;
        this.b = c2543rl0;
        for (AbstractC0890Uw abstractC0890Uw : c2543rl0.b0()) {
            abstractC0890Uw.getClass();
            if (abstractC0890Uw instanceof C2424qP) {
                z = true;
                break;
            }
        }
        this.o = z;
        this.e = this;
        if (!C2543rl0.q && c2543rl0.j != null) {
            x1f.a();
            throw null;
        }
        c2543rl0.j = this;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0032  */
    /* JADX WARN: Code duplicated, block: B:19:0x003b  */
    public void a(e eVar) {
        boolean z = true;
        if (this.i.size() > 0) {
            List list = this.i;
            e eVar2 = (e) list.get(list.size() - 1);
            eVar2.getClass();
            if (eVar2 == e.d) {
                z = false;
            } else {
                int i = eVar.b;
                if (i % 2 != 0) {
                    i++;
                }
                int i2 = eVar2.c;
                if (i2 % 2 != 0) {
                    i2++;
                }
                if (i2 > i) {
                    z = false;
                } else if (i2 == i) {
                    eVar2.c = eVar.c;
                } else {
                    this.i.add(eVar);
                }
            }
        } else {
            this.i.add(eVar);
        }
        if (s || z) {
            return;
        }
        x1f.a();
    }

    public final int b(int i) {
        Iterator it = this.j.iterator();
        while (it.hasNext()) {
            int i2 = ((d) it.next()).b;
            if (i2 >= i) {
                return i2;
            }
        }
        return Integer.MAX_VALUE;
    }

    public c c(int i) {
        int iBinarySearch;
        if (!s && this.e != this) {
            x1f.a();
            return null;
        }
        if (e() <= i && a() > i) {
            return this;
        }
        c cVar = a() == i ? this : null;
        if (this.f.size() > 100) {
            l();
            iBinarySearch = Collections.binarySearch(this.g, Integer.valueOf(i));
            if (iBinarySearch < 0) {
                iBinarySearch = -(iBinarySearch + 1);
            }
        } else {
            iBinarySearch = 0;
        }
        while (iBinarySearch < this.f.size()) {
            c cVar2 = (c) this.f.get(iBinarySearch);
            if (cVar2.e() <= i && cVar2.a() > i) {
                return cVar2;
            }
            if (cVar2.a() == i) {
                cVar = cVar2;
            }
            iBinarySearch++;
        }
        if (cVar != null) {
            return cVar;
        }
        if (s) {
            return null;
        }
        x01.a("Couldn't find split covering instruction position.");
        return null;
    }

    @Override // java.lang.Comparable
    public final int compareTo(c cVar) {
        int iIntValue;
        c cVar2 = cVar;
        int iE = e() - cVar2.e();
        if (iE != 0) {
            return iE;
        }
        Integer num = this.m;
        if (num != null && cVar2.m != null && (iIntValue = num.intValue() - cVar2.m.intValue()) != 0) {
            return iIntValue;
        }
        Integer num2 = this.m;
        if (num2 != null && cVar2.m == null) {
            return -1;
        }
        if (num2 == null && cVar2.m != null) {
            return 1;
        }
        int iS = this.b.s() - cVar2.b.s();
        if (s || iS != 0) {
            return iS;
        }
        x1f.a();
        return 0;
    }

    public final int d() {
        return this.l;
    }

    public final boolean e(int i) {
        for (e eVar : this.i) {
            if (eVar.b > i) {
                return false;
            }
            if (i < eVar.c) {
                return true;
            }
        }
        return false;
    }

    public void f(int i) {
        int i2;
        if (s || (i2 = this.l) == Integer.MIN_VALUE || i2 == i) {
            this.l = i;
        } else {
            x1f.a();
        }
    }

    public final c g(int i) {
        e eVar;
        int i2;
        List list;
        int i3 = i % 2;
        int i4 = i3 == 0 ? i : i + 1;
        int iE = e();
        if (iE % 2 != 0) {
            iE++;
        }
        if (i4 == iE) {
            if (s || this.j.size() == 0 || b() != i) {
                this.l = Integer.MIN_VALUE;
                return this;
            }
            x1f.a();
            return null;
        }
        if (!s && this.b.c(new nvg())) {
            x1f.a();
            return null;
        }
        if (i3 != 1) {
            i--;
        }
        c cVar = new c(this.e);
        this.e.f.add(cVar);
        this.e.h = false;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (i == a()) {
            List list2 = this.i;
            arrayList2.add(new e(i, i));
            list = list2;
        } else {
            int i5 = 0;
            while (i5 < this.i.size() && (((i2 = (eVar = (e) this.i.get(i5)).b) > i || eVar.c <= i) && i2 <= i)) {
                i5++;
            }
            e eVar2 = (e) this.i.get(i5);
            arrayList.addAll(this.i.subList(0, i5));
            if (eVar2.b < i) {
                arrayList.add(new e(eVar2.b, i));
                arrayList2.add(new e(i, eVar2.c));
            } else {
                arrayList2.add(eVar2);
            }
            List list3 = this.i;
            arrayList2.addAll(list3.subList(i5 + 1, list3.size()));
            list = arrayList;
        }
        cVar.i = arrayList2;
        this.i = list;
        while (!this.j.isEmpty() && ((d) this.j.last()).b >= i) {
            cVar.a((d) this.j.pollLast());
        }
        this.p = 65535;
        Iterator it = this.j.iterator();
        while (it.hasNext()) {
            this.p = Math.min(this.p, ((d) it.next()).c);
        }
        boolean z = s;
        if (!z && this.i.isEmpty()) {
            x1f.a();
            return null;
        }
        if (z || !cVar.i.isEmpty()) {
            return cVar;
        }
        x1f.a();
        return null;
    }

    public final boolean h() {
        AbstractC0890Uw abstractC0890Uw = this.e.b.c;
        return abstractC0890Uw != null && abstractC0890Uw.k1();
    }

    public final boolean i() {
        c cVar = this.e;
        return (cVar.d == null && cVar.c == null) ? false : true;
    }

    public final boolean j() {
        if (this.n) {
            c cVar = this.e;
            if (!s && cVar.e != cVar) {
                x1f.a();
                return false;
            }
            if (cVar.r) {
                return true;
            }
        }
        return false;
    }

    public final int k() {
        return this.b.Y().c();
    }

    public final void l() {
        if (this.h) {
            return;
        }
        this.f.sort(Comparator.comparingInt(new ToIntFunction() { // from class: dig
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((c) obj).a();
            }
        }));
        this.g.c = 0;
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            this.g.add(((c) it.next()).a());
        }
        if (!s) {
            for (int i = 0; i < this.f.size(); i++) {
                boolean z = s;
                if (!z && ((c) this.f.get(i)).a() != this.g.i(i)) {
                    x1f.a();
                    return;
                } else {
                    if (!z && i != 0 && this.g.i(i - 1) > this.g.i(i)) {
                        x1f.a();
                        return;
                    }
                }
            }
        }
        this.h = true;
    }

    public final String m() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (e eVar : this.i) {
            eVar.getClass();
            if (eVar == e.d) {
                sb.append("--- infinite ---...");
                break;
            }
            while (i < eVar.b) {
                sb.append(" ");
                i++;
            }
            while (i < eVar.c) {
                sb.append("-");
                i++;
            }
        }
        return sb.toString();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("(cons ");
        sb.append(this.k);
        sb.append("): ");
        Iterator it = this.i.iterator();
        while (it.hasNext()) {
            sb.append((e) it.next());
            sb.append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }

    public final C2543rl0 f() {
        return this.b;
    }

    public void b(c cVar) {
        if (!s && this.k != -1) {
            x1f.a();
        } else {
            this.c = cVar;
            cVar.d = this;
        }
    }

    public final int b() {
        return ((d) this.j.first()).b;
    }

    public int e() {
        if (s || !this.i.isEmpty()) {
            return ((e) this.i.get(0)).b;
        }
        x1f.a();
        return 0;
    }

    public final boolean a(c cVar) {
        c cVar2 = this.e;
        if (cVar2.c(cVar) != -1) {
            return true;
        }
        Iterator it = cVar2.f.iterator();
        while (it.hasNext()) {
            if (((c) it.next()).c(cVar) != -1) {
                return true;
            }
        }
        return false;
    }

    public final void a(boolean z) {
        boolean z2 = s;
        if (!z2 && this.l == Integer.MIN_VALUE) {
            x1f.a();
            return;
        }
        if (!z2 && z && h() && this.l != this.e.l) {
            x1f.a();
        } else {
            this.n = z;
        }
    }

    public void a(d dVar) {
        this.j.add(dVar);
        this.p = Math.min(this.p, dVar.c);
    }

    public int a() {
        if (s || !this.i.isEmpty()) {
            List list = this.i;
            return ((e) list.get(list.size() - 1)).c;
        }
        x1f.a();
        return 0;
    }

    public final boolean a(int i, boolean z) {
        if (this.l == i) {
            return true;
        }
        if (this.b.Y().b() && this.l + 1 == i) {
            return true;
        }
        return z && this.l == i + 1;
    }

    public final void a(c cVar, PriorityQueue priorityQueue) {
        if (c(cVar) != -1) {
            return;
        }
        boolean zRemove = priorityQueue.remove(this);
        this.m = Integer.valueOf(cVar.l);
        if (zRemove) {
            priorityQueue.add(this);
        }
    }

    public final void a(IntConsumer intConsumer) {
        if (!s && this.l == Integer.MIN_VALUE) {
            x1f.a();
            return;
        }
        intConsumer.accept(this.l);
        if (this.b.Y().b()) {
            intConsumer.accept(this.l + 1);
        }
    }

    public c(c cVar) {
        this.o = false;
        this.e = cVar;
        this.b = cVar.b;
        this.o = cVar.o;
    }

    public final int c(c cVar) {
        int i;
        e eVar;
        Iterator it = cVar.i.iterator();
        e eVar2 = (e) it.next();
        Iterator it2 = this.i.iterator();
        do {
            i = -1;
            if (!it2.hasNext()) {
                break;
            }
            eVar = (e) it2.next();
            while (eVar2.c <= eVar.b) {
                if (!it.hasNext()) {
                    return -1;
                }
                eVar2 = (e) it.next();
            }
            i = eVar2.b;
        } while (i >= eVar.c);
        return i;
    }

    public final int c() {
        c cVar = this.e;
        int i = cVar.q;
        if (i != Integer.MIN_VALUE) {
            return i;
        }
        boolean z = s;
        if (!z && cVar.e != cVar) {
            x1f.a();
            return 0;
        }
        if (!z && i != Integer.MIN_VALUE) {
            x1f.a();
            return 0;
        }
        if (!cVar.n) {
            cVar.q = cVar.l;
        }
        for (c cVar2 : cVar.f) {
            if (!cVar2.n) {
                cVar.q = Math.max(cVar.q, cVar2.l);
            }
        }
        return cVar.q;
    }

    public final boolean g() {
        return this.f.size() != 0;
    }
}
