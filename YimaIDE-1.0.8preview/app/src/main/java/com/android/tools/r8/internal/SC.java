package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0262n4;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class SC extends C3106yO {
    public final BitSet A;
    public final BitSet y;
    public final HashMap z;

    public SC(C0262n4 c0262n4, int i, String str, String str2, String[] strArr) {
        super(589824, i, str, str2, strArr);
        this.y = new BitSet();
        this.z = new HashMap();
        this.A = new BitSet();
        this.b = c0262n4;
    }

    public final void a(int i, BitSet bitSet, BitSet bitSet2) {
        while (i < this.q.b && !bitSet.get(i)) {
            bitSet.set(i);
            if (bitSet2.get(i)) {
                this.A.set(i);
            }
            bitSet2.set(i);
            G gJ = this.q.j(i);
            if (gJ.a() == 7 && gJ.a != 168) {
                a(this.q.b(((C2839vD) gJ).g), bitSet, bitSet2);
            } else if (gJ.a() == 11) {
                Mg0 mg0 = (Mg0) gJ;
                a(this.q.b(mg0.i), bitSet, bitSet2);
                Iterator it = mg0.j.iterator();
                while (it.hasNext()) {
                    a(this.q.b((XI) it.next()), bitSet, bitSet2);
                }
            } else if (gJ.a() == 12) {
                C2421qM c2421qM = (C2421qM) gJ;
                a(this.q.b(c2421qM.g), bitSet, bitSet2);
                Iterator it2 = c2421qM.i.iterator();
                while (it2.hasNext()) {
                    a(this.q.b((XI) it2.next()), bitSet, bitSet2);
                }
            }
            int i2 = this.q.j(i).a;
            if (i2 != 167 && i2 != 191) {
                switch (i2) {
                    case 169:
                    case 170:
                    case 171:
                    case 172:
                    case 173:
                    case 174:
                    case 175:
                    case 176:
                    case 177:
                        return;
                    default:
                        i++;
                        break;
                }
            } else {
                return;
            }
        }
    }

    public final void b(int i, BitSet bitSet, BitSet bitSet2) {
        boolean z;
        a(i, bitSet, bitSet2);
        do {
            z = false;
            for (C2708ti0 c2708ti0 : this.r) {
                int iB = this.q.b(c2708ti0.c);
                if (!bitSet.get(iB)) {
                    int iB2 = this.q.b(c2708ti0.a);
                    int iB3 = this.q.b(c2708ti0.b);
                    int iNextSetBit = bitSet.nextSetBit(iB2);
                    if (iNextSetBit >= iB2 && iNextSetBit < iB3) {
                        a(iB, bitSet, bitSet2);
                        z = true;
                    }
                }
            }
        } while (z);
    }

    @Override // com.android.tools.r8.internal.C3106yO, com.android.tools.r8.internal.XO
    public final void c() {
        if (!this.z.isEmpty()) {
            BitSet bitSet = new BitSet();
            b(0, this.y, bitSet);
            for (Map.Entry entry : this.z.entrySet()) {
                b(this.q.b((XI) entry.getKey()), (BitSet) entry.getValue(), bitSet);
            }
            LinkedList linkedList = new LinkedList();
            linkedList.add(new RC(this, null, this.y));
            C2042lw c2042lw = new C2042lw();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            while (!linkedList.isEmpty()) {
                RC rc = (RC) linkedList.removeFirst();
                int i = 0;
                XI xi = null;
                while (true) {
                    C2042lw c2042lw2 = this.q;
                    if (i < c2042lw2.b) {
                        G gJ = c2042lw2.j(i);
                        if (gJ.a() == 8) {
                            XI xi2 = (XI) rc.d.get((XI) gJ);
                            if (xi2 != xi) {
                                c2042lw.a(xi2);
                                xi = xi2;
                            }
                        } else if (rc.a(i) != rc) {
                            continue;
                        } else {
                            int i2 = gJ.a;
                            if (i2 == 169) {
                                XI xi3 = null;
                                for (RC rc2 = rc; rc2 != null; rc2 = rc2.b) {
                                    if (rc2.c.get(i)) {
                                        xi3 = rc2.e;
                                    }
                                }
                                if (xi3 == null) {
                                    w01.a(AbstractC1784iv.a(i, "Instruction #", " is a RET not owned by any subroutine"));
                                    return;
                                }
                                c2042lw.a(new C2839vD(167, xi3));
                            } else if (i2 == 168) {
                                XI xi4 = ((C2839vD) gJ).g;
                                RC rc3 = new RC(this, rc, (BitSet) this.z.get(xi4));
                                XI xi5 = (XI) rc3.a(this.q.b(xi4)).d.get(xi4);
                                c2042lw.a(new C2127mw(1));
                                c2042lw.a(new C2839vD(167, xi5));
                                c2042lw.a(rc3.e);
                                linkedList.add(rc3);
                            } else {
                                c2042lw.a(gJ.a(rc));
                            }
                        }
                        i++;
                    }
                }
                for (C2708ti0 c2708ti0 : this.r) {
                    XI xi6 = (XI) rc.d.get(c2708ti0.a);
                    XI xi7 = (XI) rc.d.get(c2708ti0.b);
                    if (xi6 != xi7) {
                        XI xi8 = c2708ti0.c;
                        XI xi9 = (XI) rc.a(rc.f.q.b(xi8)).d.get(xi8);
                        if (xi6 == null || xi7 == null || xi9 == null) {
                            x01.a("Internal error!");
                            return;
                        }
                        arrayList.add(new C2708ti0(xi6, xi7, xi9, c2708ti0.d));
                    }
                }
                for (NL nl : this.u) {
                    XI xi10 = (XI) rc.d.get(nl.d);
                    XI xi11 = (XI) rc.d.get(nl.e);
                    if (xi10 != xi11) {
                        arrayList2.add(new NL(nl.a, nl.b, nl.c, xi10, xi11, nl.f));
                    }
                }
            }
            this.q = c2042lw;
            this.r = arrayList;
            this.u = arrayList2;
        }
        XO xo = this.b;
        if (xo != null) {
            a(xo);
        }
    }

    @Override // com.android.tools.r8.internal.C3106yO, com.android.tools.r8.internal.XO
    public final void a(int i, WI wi) {
        super.a(i, wi);
        XI xi = ((C2839vD) this.q.d).g;
        if (i != 168 || this.z.containsKey(xi)) {
            return;
        }
        this.z.put(xi, new BitSet());
    }
}
