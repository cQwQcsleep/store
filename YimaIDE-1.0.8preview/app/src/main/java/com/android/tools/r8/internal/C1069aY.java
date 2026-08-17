package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.C1069aY;
import com.android.tools.r8.internal.UX;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.aY, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1069aY extends NP {
    public final C0333y b;
    public final com.android.tools.r8.graph.W3 c;
    public final VX d = new VX(this);
    public final XX e = new XX(this);
    public final YX f = new YX(this);

    public C1069aY(C0333y c0333y, com.android.tools.r8.graph.W3 w3) {
        this.b = c0333y;
        this.c = w3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.internal.NP
    public final Collection a(C1868jt c1868jt) {
        if (c1868jt.j()) {
            int i = AbstractC0551Hu.c;
            return new Bc0(c1868jt);
        }
        C0775Ql c0775Ql = new C0775Ql(new LinkedHashSet());
        Iterator it = c1868jt.b.iterator();
        while (it.hasNext()) {
            c0775Ql.a(((com.android.tools.r8.graph.D2) it.next()).C1());
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (com.android.tools.r8.graph.D2 d2 : c1868jt.b) {
            HashSet hashSet = new HashSet(this.f.a(d2).b);
            C0775Ql c0775Ql2 = new C0775Ql(hashSet);
            hashSet.removeAll(this.e.a(d2));
            UX ux = new UX();
            for (com.android.tools.r8.graph.B2 b2 : c0775Ql.b) {
                WX wx = WX.b;
                if (c0775Ql2.b.contains(b2)) {
                    com.android.tools.r8.graph.B1 b1A = this.b.a();
                    b2.getClass();
                    com.android.tools.r8.graph.T4.c<?> cVarO = ((C0229j) this.b.g()).d(d2, b1A.a(d2.z().z(), b2.b(), b2.a())).o();
                    if (cVarO == null || cVarO.d().isInterface()) {
                        wx = WX.c;
                    }
                }
                WX wx2 = (WX) ux.put(b2, wx);
                if (!UX.b && wx2 != null) {
                    x1f.a();
                    return null;
                }
            }
            ((C1868jt) linkedHashMap.computeIfAbsent(ux, new Function() { // from class: ubg
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return C1069aY.a((UX) obj);
                }
            })).b.add(d2);
        }
        return AbstractC1238cX.a(linkedHashMap.values());
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "PreventClassMethodAndDefaultMethodCollisions";
    }

    public static /* synthetic */ C1868jt a(UX ux) {
        return new C1868jt();
    }
}
