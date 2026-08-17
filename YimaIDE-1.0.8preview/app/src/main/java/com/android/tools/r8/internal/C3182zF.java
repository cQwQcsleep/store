package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC2671tG;
import com.android.tools.r8.internal.C2159nH;
import com.android.tools.r8.internal.HE;
import com.android.tools.r8.internal.QE;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3182zF {
    public final C2415qF a;
    public final HashMap b = new HashMap();
    public final GE c;
    public final C1052aH d;
    public final RE e;

    public C3182zF(C2415qF c2415qF) {
        IE ie = IE.b;
        this.c = new GE();
        this.d = new C1052aH();
        this.e = new RE();
        this.a = c2415qF;
    }

    public final C2415qF a() {
        this.a.b.a(new BiConsumer() { // from class: rzi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a((HE) obj, (AbstractC2671tG) obj2);
            }
        });
        this.a.c.a(new Consumer() { // from class: szi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((QE) obj);
            }
        });
        this.a.d.a(new Consumer() { // from class: tzi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C2159nH) obj);
            }
        });
        C3097yF c3097yF = C3097yF.c;
        IE ie = IE.b;
        C3097yF c3097yF2 = this.a.a;
        IE ieA = this.c.a();
        C1052aH c1052aH = this.d;
        AbstractC1222cH c1138bH = c1052aH.a.isEmpty() ? ZG.a : new C1138bH(c1052aH.a);
        SE seA = this.e.a();
        if (!seA.a.isEmpty()) {
            return new C2415qF(c3097yF2, ieA, c1138bH, seA);
        }
        defpackage.l0.a("KeepEdge must have non-empty set of consequences.");
        return null;
    }

    public final /* synthetic */ void a(HE he, AbstractC2671tG abstractC2671tG) {
        AbstractC2671tG abstractC2671tGA = a(abstractC2671tG);
        this.c.a(he, abstractC2671tGA);
        this.b.put(he, abstractC2671tGA);
    }

    public final void a(QE qe) {
        C1052aH c1052aH = this.d;
        c1052aH.a.add(new QE(a(qe.a)));
    }

    public final void a(C2159nH c2159nH) {
        RE re = this.e;
        AbstractC2072mF abstractC2072mF = c2159nH.b;
        AbstractC2757uG abstractC2757uGA = a(c2159nH.a);
        if (abstractC2757uGA != null) {
            re.a.add(new C2159nH(abstractC2757uGA, abstractC2072mF));
        } else {
            defpackage.l0.a("Target must define an item pattern");
        }
    }

    public final AbstractC2757uG a(AbstractC2757uG abstractC2757uG) {
        EE c3098yG;
        if (abstractC2757uG.f()) {
            EE eeA = abstractC2757uG.a();
            if (eeA.c()) {
                AbstractC2671tG abstractC2671tG = (AbstractC2671tG) this.b.get(eeA.a);
                if (abstractC2671tG.e()) {
                    return abstractC2671tG.b().a;
                }
            }
            return abstractC2757uG;
        }
        AbstractC2671tG abstractC2671tGA = a(abstractC2757uG.c());
        this.c.getClass();
        HE he = new HE("SyntheticBinding");
        this.c.a(he, abstractC2671tGA);
        if (abstractC2671tGA.d()) {
            c3098yG = new KE(he);
        } else {
            c3098yG = new C3098yG(he);
        }
        return c3098yG.d();
    }

    public final AbstractC2671tG a(AbstractC2671tG abstractC2671tG) {
        if (abstractC2671tG.d()) {
            return abstractC2671tG;
        }
        AG agB = abstractC2671tG.b();
        PE pe = agB.a;
        if (pe.f()) {
            return agB;
        }
        ME meB = pe.b();
        this.c.getClass();
        HE he = new HE("SyntheticBinding");
        this.c.a(he, meB);
        ME.g();
        EG eg = EG.d;
        return new AG(new NE(new KE(he)), agB.b);
    }
}
