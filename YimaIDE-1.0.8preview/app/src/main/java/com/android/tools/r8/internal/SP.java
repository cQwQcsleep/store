package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.D2;
import defpackage.flg;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class SP extends VP {
    public SP(Vd0 vd0) {
        super(vd0);
    }

    public abstract int a(C0231j1 c0231j1);

    @Override // com.android.tools.r8.internal.VP
    public final void a(ArrayList arrayList, com.android.tools.r8.dex.q0 q0Var, com.android.tools.r8.dex.t0 t0Var, com.android.tools.r8.dex.r0 r0Var) {
        ToIntFunction toIntFunction = new ToIntFunction() { // from class: doc
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return this.b.a((D2) obj);
            }
        };
        final C2481r30 c2481r30 = new C2481r30();
        c2481r30.b = 0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            com.android.tools.r8.graph.D2 d2 = (com.android.tools.r8.graph.D2) it.next();
            c2481r30.b(toIntFunction.applyAsInt(d2), d2);
        }
        ArrayList arrayList2 = new ArrayList(arrayList);
        arrayList2.sort(Comparator.comparingInt(new ToIntFunction() { // from class: eoc
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return c2481r30.b((D2) obj);
            }
        }).thenComparing(new flg()));
        VP.a(arrayList2, t0Var, r0Var);
    }

    public final int a(com.android.tools.r8.graph.D2 d2) {
        boolean zA = false;
        int iA = 0;
        for (C0231j1 c0231j1 : d2.C1()) {
            iA += a(c0231j1);
            zA |= this.a.a(c0231j1.getReference());
        }
        if (!(this instanceof RP) || zA) {
            return iA;
        }
        return Integer.MAX_VALUE;
    }
}
