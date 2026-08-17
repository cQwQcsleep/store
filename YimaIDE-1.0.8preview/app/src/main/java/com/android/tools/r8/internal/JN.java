package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0287r2;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class JN extends AbstractC1511fi {
    public static final /* synthetic */ int g = 0;
    public final Map f;

    public JN(C0333y c0333y, IdentityHashMap identityHashMap) {
        super(c0333y);
        this.f = identityHashMap;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x005b  */
    /* JADX WARN: Code duplicated, block: B:23:0x0063  */
    /* JADX WARN: Code duplicated, block: B:24:0x0066  */
    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public final C2850vO a(C2850vO c2850vO, C0322w2 c0322w2, AbstractC3148ys abstractC3148ys) {
        com.android.tools.r8.graph.E0 e0D;
        Map map = (Map) this.f.getOrDefault(c2850vO.c, Collections.EMPTY_MAP);
        AbstractC0287r2 abstractC0287r2 = c2850vO.a;
        C0322w2 c0322w3 = (C0322w2) map.getOrDefault(abstractC0287r2, (C0322w2) abstractC0287r2);
        com.android.tools.r8.graph.proto.j jVar = com.android.tools.r8.graph.proto.j.d;
        com.android.tools.r8.graph.proto.j jVar2 = c2850vO.d;
        C0333y c0333y = this.b;
        C0322w2 c0322w4 = (C0322w2) c2850vO.a;
        EnumC2326pC enumC2326pC = c2850vO.c;
        C0698Nm c0698Nm = CQ.j;
        EnumC2326pC enumC2326pC2 = EnumC2326pC.h;
        if ((enumC2326pC == enumC2326pC2 || enumC2326pC == EnumC2326pC.e) && (e0D = c0333y.d(c0322w3.w0())) != null) {
            com.android.tools.r8.graph.E0 e0D2 = c0333y.d(c0322w4.w0());
            if (e0D2 != null) {
                boolean zIsInterface = e0D2.isInterface();
                EnumC2326pC enumC2326pC3 = EnumC2326pC.e;
                if ((enumC2326pC == enumC2326pC3) ^ zIsInterface) {
                    if (e0D.f.L()) {
                        enumC2326pC = enumC2326pC2;
                    } else {
                        enumC2326pC = enumC2326pC3;
                    }
                } else if (e0D.f.L()) {
                    enumC2326pC = EnumC2326pC.e;
                } else {
                    enumC2326pC = enumC2326pC2;
                }
            } else if (e0D.f.L()) {
                enumC2326pC = EnumC2326pC.e;
            } else {
                enumC2326pC = enumC2326pC2;
            }
        }
        return new C2850vO(c0322w3, c0322w3, enumC2326pC, jVar2).a(this);
    }

    @Override // com.android.tools.r8.internal.XR, com.android.tools.r8.internal.AbstractC3148ys
    public final boolean b(AbstractC3148ys abstractC3148ys) {
        if (this == abstractC3148ys) {
            return true;
        }
        return this.d.b(abstractC3148ys);
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public final C2035lp b(C2035lp c2035lp) {
        return c2035lp;
    }
}
