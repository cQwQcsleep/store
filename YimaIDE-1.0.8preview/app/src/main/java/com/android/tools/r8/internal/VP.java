package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class VP {
    public static final /* synthetic */ boolean b = true;
    public final Vd0 a;

    public VP(Vd0 vd0) {
        this.a = vd0;
    }

    public static VP a(C2752uB c2752uB, Vd0 vd0) {
        String strA = c2752uB.L().a();
        if (strA == null) {
            return new TP(vd0);
        }
        switch (strA) {
            case "classByNumberOfStartupMethods":
                return new QP(vd0);
            case "packageByNumberOfStartupMethods":
                throw new C1345dk0();
            case "classByName":
                return new TP(vd0);
            case "classByNumberOfStartupMethodsMinusNumberOfNonStartupMethods":
                return new RP(vd0);
            case "packageByName":
                return new UP(vd0);
            default:
                w01.a("Unexpected multi startup dex distribution strategy: ".concat(strA));
                return null;
        }
    }

    public abstract void a(ArrayList arrayList, com.android.tools.r8.dex.q0 q0Var, com.android.tools.r8.dex.t0 t0Var, com.android.tools.r8.dex.r0 r0Var);

    public static void a(ArrayList arrayList, com.android.tools.r8.dex.t0 t0Var, com.android.tools.r8.dex.r0 r0Var) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            com.android.tools.r8.graph.D2 d2 = (com.android.tools.r8.graph.D2) it.next();
            t0Var.a(d2);
            if (!t0Var.a(65536)) {
                t0Var.c.a();
            } else {
                t0Var.a();
                r0Var.getClass();
                t0Var = new com.android.tools.r8.dex.t0(r0Var.d.b(), r0Var.c, r0Var.g);
                r0Var.a.add(t0Var);
                r0Var.b.add(t0Var);
                r0Var.e = NC.a(r0Var.b);
                r0Var.c();
                t0Var.a(d2);
                if (!b && t0Var.a(65536)) {
                    x1f.a();
                    return;
                }
                t0Var.c.a();
            }
        }
    }
}
