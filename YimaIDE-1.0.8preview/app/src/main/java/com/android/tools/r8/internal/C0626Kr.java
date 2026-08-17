package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Kr, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0626Kr {
    public final Iterator a;
    public Map.Entry b;
    public final boolean c;

    public C0626Kr(Lr lr) {
        C0494Fp c0494Fp = lr.b;
        boolean z = c0494Fp.c;
        Dc0 dc0 = c0494Fp.a;
        Iterator c2760uJ = z ? new C2760uJ(new Nc0(((Pc0) dc0.entrySet()).b)) : new Nc0(((Pc0) dc0.entrySet()).b);
        this.a = c2760uJ;
        if (c2760uJ.hasNext()) {
            this.b = (Map.Entry) c2760uJ.next();
        }
        this.c = false;
    }

    public final void a(int i, C0767Qd c0767Qd) {
        while (true) {
            Map.Entry entry = this.b;
            if (entry == null || ((C0677Mr) entry.getKey()).b >= i) {
                return;
            }
            C0677Mr c0677Mr = (C0677Mr) this.b.getKey();
            int iA = 0;
            if (this.c && c0677Mr.c.b == Om0.k && !c0677Mr.d) {
                int i2 = c0677Mr.b;
                L0 l0 = (L0) this.b.getValue();
                c0767Qd.c(1, 3);
                c0767Qd.c(2, 0);
                c0767Qd.g(i2);
                c0767Qd.b(3, l0);
                c0767Qd.c(1, 4);
            } else {
                Object value = this.b.getValue();
                C0494Fp c0494Fp = C0494Fp.d;
                Mm0 mm0 = c0677Mr.c;
                int i3 = c0677Mr.b;
                if (c0677Mr.d) {
                    List list = (List) value;
                    if (c0677Mr.e) {
                        c0767Qd.c(i3, 2);
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            iA += C0494Fp.a(mm0, it.next());
                        }
                        c0767Qd.g(iA);
                        Iterator it2 = list.iterator();
                        while (it2.hasNext()) {
                            C0494Fp.a(c0767Qd, mm0, it2.next());
                        }
                    } else {
                        for (Object obj : list) {
                            if (mm0 == Mm0.f) {
                                c0767Qd.c(i3, 3);
                                ((L0) obj).a(c0767Qd);
                                c0767Qd.c(i3, 4);
                            } else {
                                c0767Qd.c(i3, mm0.c);
                                C0494Fp.a(c0767Qd, mm0, obj);
                            }
                        }
                    }
                } else if (mm0 == Mm0.f) {
                    c0767Qd.c(i3, 3);
                    ((L0) value).a(c0767Qd);
                    c0767Qd.c(i3, 4);
                } else {
                    c0767Qd.c(i3, mm0.c);
                    C0494Fp.a(c0767Qd, mm0, value);
                }
            }
            if (this.a.hasNext()) {
                this.b = (Map.Entry) this.a.next();
            } else {
                this.b = null;
            }
        }
    }
}
