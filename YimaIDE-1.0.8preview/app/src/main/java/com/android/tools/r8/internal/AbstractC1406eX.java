package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC1238cX;
import com.android.tools.r8.internal.AbstractC1406eX;
import com.android.tools.r8.internal.Ml0;
import com.android.tools.r8.internal.NP;
import defpackage.ftg;
import defpackage.gtg;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eX, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1406eX {
    public static final /* synthetic */ boolean a = true;

    public static void a(List list) {
        int iB = C2847vL.b(list, new Predicate() { // from class: jtg
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AbstractC1406eX.a((AbstractC1238cX) obj);
            }
        });
        if (iB >= 0) {
            for (AbstractC1238cX abstractC1238cX : list.subList(iB + 1, list.size())) {
                if (!a && !abstractC1238cX.g()) {
                    x1f.a();
                    return;
                }
            }
        }
    }

    public static void b(C0333y c0333y, C0473Eu c0473Eu) {
        DR dr = new DR();
        OQ oq = new OQ();
        C1486fR c1486fR = new C1486fR(c0333y, null);
        C1743iR c1743iR = new C1743iR(c0333y);
        C2426qR c2426qR = new C2426qR(c0333y);
        C2255oR c2255oR = new C2255oR();
        C2341pR c2341pR = new C2341pR();
        C2939wR c2939wR = new C2939wR();
        C3194zR c3194zR = new C3194zR();
        FR fr = new FR(c0333y);
        CR cr = new CR();
        int i = AbstractC0551Hu.c;
        Object[] objArrA = AbstractC2856vU.a(11, new Object[]{dr, oq, c1486fR, c1743iR, c2426qR, c2255oR, c2341pR, c2939wR, c3194zR, fr, cr});
        AbstractC0551Hu.b(objArrA.length, objArrA).stream().map(new ftg()).forEach(new gtg(c0473Eu));
    }

    public static void c(C0333y c0333y, C0473Eu c0473Eu) {
        DR dr = new DR();
        OQ oq = new OQ();
        C1486fR c1486fR = new C1486fR(c0333y, null);
        C2426qR c2426qR = new C2426qR(c0333y);
        C2255oR c2255oR = new C2255oR();
        C2341pR c2341pR = new C2341pR();
        C2939wR c2939wR = new C2939wR();
        C3194zR c3194zR = new C3194zR();
        CR cr = new CR();
        int i = AbstractC0551Hu.c;
        Object[] objArrA = AbstractC2856vU.a(9, new Object[]{dr, oq, c1486fR, c2426qR, c2255oR, c2341pR, c2939wR, c3194zR, cr});
        AbstractC0551Hu.b(objArrA.length, objArrA).stream().map(new ftg()).forEach(new gtg(c0473Eu));
    }

    public static void a(C0333y c0333y, final C0473Eu c0473Eu) {
        AbstractC0551Hu.a(new Hg0(c0333y), new Ma0()).stream().map(new Function() { // from class: htg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new Ml0((NP) obj);
            }
        }).forEach(new Consumer() { // from class: itg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c0473Eu.a((Ml0) obj);
            }
        });
    }

    public static /* synthetic */ boolean a(AbstractC1238cX abstractC1238cX) {
        return abstractC1238cX instanceof C1747iV;
    }
}
