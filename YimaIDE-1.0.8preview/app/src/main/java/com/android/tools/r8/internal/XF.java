package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC2072mF;
import com.android.tools.r8.internal.XF;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class XF extends VF {
    public final InterfaceC1221cG j;
    public final C1475fG k;
    public final EF l;
    public final C2074mH m;

    public XF(C2516rW c2516rW, InterfaceC1221cG interfaceC1221cG, C1475fG c1475fG) {
        super(c2516rW);
        this.m = new C2074mH();
        this.j = interfaceC1221cG;
        this.k = c1475fG;
        this.l = new EF(c2516rW);
    }

    @Override // com.android.tools.r8.internal.VF, com.android.tools.r8.internal.J2
    public final void a() {
        AG agB;
        Collection<AbstractC2757uG> collectionA;
        super.a();
        this.m.b = this.l.d();
        AbstractC2757uG abstractC2757uG = this.h;
        if (abstractC2757uG == null) {
            AbstractC3114yW abstractC3114yW = this.c;
            abstractC3114yW.getClass();
            throw new C3096yE(abstractC3114yW, "Item reference not finalized. Missing call to visitEnd()");
        }
        if (abstractC2757uG.f()) {
            collectionA = Collections.singletonList(this.h);
        } else {
            MF mf = this.e;
            if (mf == null) {
                AbstractC3114yW abstractC3114yW2 = this.c;
                abstractC3114yW2.getClass();
                throw new C3096yE(abstractC3114yW2, "Unexpected state: unknown kind for an item pattern");
            }
            MF mf2 = MF.b;
            if ((mf.equals(mf2) || mf.equals(MF.f) || mf.equals(MF.g) || mf.equals(MF.h)) && !mf.equals(mf2)) {
                AbstractC2671tG abstractC2671tGC = this.h.c();
                if (abstractC2671tGC.d()) {
                    ME.g();
                    EG eg = EG.d;
                    ME meA = abstractC2671tGC.a();
                    meA.getClass();
                    agB = new AG(new OE(meA), eg);
                } else {
                    agB = abstractC2671tGC.b();
                }
                PE pe = agB.a;
                if (pe.b() != null) {
                    ME meB = pe.b();
                    C1475fG c1475fG = this.k;
                    c1475fG.a.getClass();
                    HE he = new HE("CLASS");
                    c1475fG.a.a(he, meB);
                    NE ne = new NE(new KE(he));
                    ME.g();
                    EG eg2 = EG.d;
                    agB = new AG(ne, agB.b);
                    pe = ne;
                }
                boolean z = VF.i;
                if (!z && !pe.f()) {
                    x1f.a();
                    return;
                } else {
                    if (!z && !agB.a.equals(pe)) {
                        x1f.a();
                        return;
                    }
                    collectionA = AbstractC0551Hu.a(pe, new CG(agB));
                }
            } else {
                collectionA = Collections.singletonList(this.h);
            }
        }
        for (AbstractC2757uG abstractC2757uG2 : collectionA) {
            InterfaceC1221cG interfaceC1221cG = this.j;
            C2074mH c2074mH = this.m;
            c2074mH.a = abstractC2757uG2;
            interfaceC1221cG.accept(c2074mH.a());
        }
    }

    @Override // com.android.tools.r8.internal.VF
    public final C1475fG b() {
        return this.k;
    }

    @Override // com.android.tools.r8.internal.VF, com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str) {
        EF ef = this.l;
        new Consumer() { // from class: rxf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                XF.a((AbstractC2072mF) obj);
            }
        };
        J2 j2A = ef.a(str);
        return j2A != null ? j2A : super.a(str);
    }

    public static /* synthetic */ void a(AbstractC2072mF abstractC2072mF) {
    }
}
