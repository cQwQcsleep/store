package com.android.tools.r8.internal;

import defpackage.qc6;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Hb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0532Hb extends C2136n2 {
    public final DB j;
    public C2042lw k;
    public int l;

    public C0532Hb(P5 p5) {
        super(p5);
        this.j = p5;
    }

    public final void a(int i, C0936Wq c0936Wq, boolean z) throws C2222o2 {
        String str;
        C0936Wq[] c0936WqArr = this.e;
        C0936Wq c0936Wq2 = c0936WqArr[i];
        if (c0936Wq2 == null) {
            if (z) {
                throw new C2222o2(null, CX.a(i, "Expected stack map frame at instruction "));
            }
            c0936WqArr[i] = C2136n2.a(c0936Wq);
            return;
        }
        int i2 = c0936Wq.c;
        if (i2 != c0936Wq2.c) {
            x1f.a();
            return;
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 >= i2) {
                int i5 = c0936Wq.d;
                if (i5 == c0936Wq2.d) {
                    while (true) {
                        if (i3 >= i5) {
                            str = null;
                            break;
                        }
                        DB db = this.j;
                        InterfaceC2458ql0 interfaceC2458ql0 = c0936Wq.b[c0936Wq.c + i3];
                        InterfaceC2458ql0 interfaceC2458ql1 = c0936Wq2.b[c0936Wq2.c + i3];
                        ((P5) db).getClass();
                        O5 o5 = (O5) interfaceC2458ql0;
                        if (!o5.equals((O5) interfaceC2458ql1)) {
                            o5 = O5.b;
                        }
                        if (!o5.equals(c0936Wq2.b[c0936Wq2.c + i3])) {
                            str = "incompatible types at stack item " + i3 + ": " + c0936Wq.b[c0936Wq.c + i3] + " and " + c0936Wq2.b[c0936Wq2.c + i3];
                            break;
                        }
                        i3++;
                    }
                } else {
                    str = "incompatible stack heights";
                    break;
                }
            } else {
                DB db2 = this.j;
                InterfaceC2458ql0 interfaceC2458ql0A = c0936Wq.a(i4);
                InterfaceC2458ql0 interfaceC2458ql0A2 = c0936Wq2.a(i4);
                ((P5) db2).getClass();
                O5 o6 = (O5) interfaceC2458ql0A;
                if (!o6.equals((O5) interfaceC2458ql0A2)) {
                    o6 = O5.b;
                }
                if (!o6.equals(c0936Wq2.a(i4))) {
                    str = "incompatible types at local " + i4 + ": " + c0936Wq.a(i4) + " and " + c0936Wq2.a(i4);
                    break;
                }
                i4++;
            }
        }
        if (str == null) {
            return;
        }
        throw new C2222o2(null, "Stack map frame incompatible with frame at instruction " + i + " (" + str + ")");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v13, types: [int] */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v23 */
    @Override // com.android.tools.r8.internal.C2136n2
    public final void d(C3106yO c3106yO) throws C2222o2 {
        G g;
        G gJ;
        this.k = c3106yO.q;
        int iC = C3050xi0.c(c3106yO.e) >> 2;
        this.l = iC;
        if ((c3106yO.c & 8) != 0) {
            this.l = iC - 1;
        }
        C0936Wq[] c0936WqArr = this.e;
        boolean z = false;
        C0936Wq c0936Wq = c0936WqArr[0];
        C2042lw c2042lw = c3106yO.q;
        c2042lw.getClass();
        int i = c2042lw.b;
        if (i < 0) {
            qc6.a();
            return;
        }
        if (i == 0) {
            g = null;
        } else {
            g = c2042lw.c;
            G g2 = g.d;
        }
        int i2 = -1;
        int i3 = 0;
        C0936Wq c0936WqA = c0936Wq;
        while (g != null) {
            if (g == null) {
                z0e.a();
                return;
            }
            G g3 = g.e;
            boolean z2 = g instanceof C0988Yq;
            if (z2) {
                try {
                    c0936WqA = a(c0936WqA, (C0988Yq) g);
                    for (int i4 = i2 + 1; i4 <= i3; i4++) {
                        this.e[i4] = c0936WqA;
                    }
                } catch (C2222o2 e) {
                    throw new C2222o2(e.b, "Error at instruction " + i3 + ": " + e.getMessage(), e);
                }
            }
            if (g.a >= 0 || z2) {
                i2 = i3;
            }
            i3++;
            g = g3;
        }
        int i5 = 0;
        while (i5 < this.k.b) {
            C0936Wq c0936Wq2 = c0936WqArr[i5];
            try {
                try {
                    gJ = c3106yO.q.j(i5);
                    try {
                        int i6 = gJ.a;
                        int iA = gJ.a();
                        if (iA == 8 || iA == 15 || iA == 14) {
                            a(i5 + 1, c0936Wq2, z);
                        } else {
                            c0936Wq.a(c0936Wq2).a(gJ, this.j);
                            if (gJ instanceof C2839vD) {
                                if (i6 == 168) {
                                    throw new C2222o2(gJ, "JSR instructions are unsupported");
                                }
                                a(this.k.b(((C2839vD) gJ).g), c0936Wq, true);
                                if (i6 == 167) {
                                    a(i5);
                                } else {
                                    a(i5 + 1, c0936Wq, z);
                                }
                            } else if (gJ instanceof C2421qM) {
                                C2421qM c2421qM = (C2421qM) gJ;
                                a(this.k.b(c2421qM.g), c0936Wq, true);
                                for (int i7 = z ? 1 : 0; i7 < c2421qM.i.size(); i7++) {
                                    a(this.k.b((XI) c2421qM.i.get(i7)), c0936Wq, true);
                                }
                                a(i5);
                            } else if (gJ instanceof Mg0) {
                                Mg0 mg0 = (Mg0) gJ;
                                a(this.k.b(mg0.i), c0936Wq, true);
                                for (int i8 = z ? 1 : 0; i8 < mg0.j.size(); i8++) {
                                    a(this.k.b((XI) mg0.j.get(i8)), c0936Wq, true);
                                }
                                a(i5);
                            } else {
                                if (i6 == 169) {
                                    throw new C2222o2(gJ, "RET instructions are unsupported");
                                }
                                if (i6 == 191 || (i6 >= 172 && i6 <= 177)) {
                                    a(i5);
                                } else {
                                    a(i5 + 1, c0936Wq, z);
                                }
                            }
                        }
                        List<C2708ti0> list = this.d[i5];
                        if (list != null) {
                            ?? r3 = z;
                            for (C2708ti0 c2708ti0 : list) {
                                String str = c2708ti0.d;
                                C3050xi0 c3050xi0E = str == null ? C3050xi0.e("java/lang/Throwable") : C3050xi0.e(str);
                                C0936Wq c0936WqA2 = C2136n2.a(c0936Wq2);
                                c0936WqA2.d = r3;
                                this.j.getClass();
                                c0936WqA2.a(P5.a(c3050xi0E));
                                a(this.k.b(c2708ti0.c), c0936WqA2, true);
                                r3 = 0;
                            }
                        }
                        for (G g4 = this.k.j(i5).e; g4 != null; g4 = g4.e) {
                            if (g4.a < 0 && !(g4 instanceof C0988Yq)) {
                            }
                            i5++;
                            z = false;
                        }
                        return;
                    } catch (RuntimeException e2) {
                        e = e2;
                        throw new C2222o2(gJ, "Error at instruction " + i5 + ": " + e.getMessage(), e);
                    }
                } catch (C2222o2 e3) {
                    throw new C2222o2(e3.b, "Error at instruction " + i5 + ": " + e3.getMessage(), e3);
                }
            } catch (RuntimeException e4) {
                e = e4;
                gJ = null;
            }
        }
    }

    public final O5 a(C0988Yq c0988Yq, Object obj) throws C2222o2 {
        if (obj == 0) {
            ((P5) this.j).getClass();
            return O5.b;
        }
        if (obj == 1) {
            ((P5) this.j).getClass();
            return O5.c;
        }
        if (obj == 2) {
            ((P5) this.j).getClass();
            return O5.d;
        }
        if (obj == 4) {
            ((P5) this.j).getClass();
            return O5.e;
        }
        if (obj == 3) {
            ((P5) this.j).getClass();
            return O5.f;
        }
        if (obj == 5) {
            return this.j.a(new C2127mw(1));
        }
        if (obj == 6) {
            DB db = this.j;
            C3050xi0 c3050xi0E = C3050xi0.e("dummy");
            ((P5) db).getClass();
            return P5.a(c3050xi0E);
        }
        if (obj instanceof String) {
            DB db2 = this.j;
            C3050xi0 c3050xi0E2 = C3050xi0.e((String) obj);
            ((P5) db2).getClass();
            return P5.a(c3050xi0E2);
        }
        if (obj instanceof XI) {
            G g = (XI) obj;
            while (g != null && g.a < 0) {
                g = g.e;
            }
            if (g != null && g.a == 187) {
                DB db3 = this.j;
                C3050xi0 c3050xi0E3 = C3050xi0.e(((C2710tj0) g).g);
                ((P5) db3).getClass();
                return P5.a(c3050xi0E3);
            }
            throw new C2222o2(c0988Yq, "LabelNode does not designate a NEW instruction");
        }
        throw new C2222o2(c0988Yq, "Illegal stack map frame value " + obj);
    }

    public final void a(int i) throws C2222o2 {
        for (G g = this.k.j(i).e; g != null; g = g.e) {
            if (g.a >= 0 || (g instanceof C0988Yq)) {
                int i2 = i + 1;
                if (this.e[i2] == null) {
                    throw new C2222o2(null, CX.a(i2, "Expected stack map frame at instruction "));
                }
                return;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:45:0x00a3 A[LOOP:0: B:43:0x009f->B:45:0x00a3, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:48:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:52:0x00c5 A[LOOP:1: B:50:0x00bf->B:52:0x00c5, LOOP_END] */
    public final C0936Wq a(C0936Wq c0936Wq, C0988Yq c0988Yq) throws C2222o2 {
        List list;
        Iterator it;
        C0936Wq c0936WqA = C2136n2.a(c0936Wq);
        List list2 = c0988Yq.h;
        if (list2 == null) {
            list2 = Collections.EMPTY_LIST;
        }
        int i = this.l;
        int i2 = c0988Yq.g;
        if (i2 != -1 && i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    Iterator it2 = list2.iterator();
                    while (it2.hasNext()) {
                        it2.next();
                        if (i > 0) {
                            i = (i <= 1 || ((O5) c0936WqA.a(i + (-2))).a() != 2) ? i - 1 : i - 2;
                        } else {
                            throw new C2222o2(c0988Yq, "Cannot chop more locals than defined");
                        }
                    }
                } else if (i2 != 3 && i2 != 4) {
                    throw new C2222o2(c0988Yq, CX.a(c0988Yq.g, "Illegal frame type "));
                }
            }
            this.l = i;
            while (i < c0936WqA.c) {
                ((P5) this.j).getClass();
                c0936WqA.a(i, O5.b);
                i++;
            }
            list = c0988Yq.i;
            if (list == null) {
                list = Collections.EMPTY_LIST;
            }
            c0936WqA.d = 0;
            it = list.iterator();
            while (it.hasNext()) {
                c0936WqA.a(a(c0988Yq, it.next()));
            }
            return c0936WqA;
        }
        i = 0;
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            O5 o5A = a(c0988Yq, it3.next());
            if (o5A.a() + i <= c0936WqA.c) {
                int i3 = i + 1;
                c0936WqA.a(i, o5A);
                if (o5A.a() == 2) {
                    i += 2;
                    ((P5) this.j).getClass();
                    c0936WqA.a(i3, O5.b);
                } else {
                    i = i3;
                }
            } else {
                throw new C2222o2(c0988Yq, "Cannot append more locals than maxLocals");
            }
        }
        this.l = i;
        while (i < c0936WqA.c) {
            ((P5) this.j).getClass();
            c0936WqA.a(i, O5.b);
            i++;
        }
        list = c0988Yq.i;
        if (list == null) {
            list = Collections.EMPTY_LIST;
        }
        c0936WqA.d = 0;
        it = list.iterator();
        while (it.hasNext()) {
            c0936WqA.a(a(c0988Yq, it.next()));
        }
        return c0936WqA;
    }
}
