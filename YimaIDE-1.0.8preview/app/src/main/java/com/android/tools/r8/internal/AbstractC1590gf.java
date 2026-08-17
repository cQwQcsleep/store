package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.Map;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1590gf extends RO {
    public static final /* synthetic */ boolean a = true;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.internal.QO
    public final QO a(C0333y c0333y, com.android.tools.r8.graph.B2 b2, QO qo, AbstractC1589ge0 abstractC1589ge0) {
        QO qo2;
        if (qo.d()) {
            return this;
        }
        if (qo.isUnknown()) {
            return qo;
        }
        AbstractC1590gf abstractC1590gfE = qo.e();
        if (this instanceof C1676hf) {
            abstractC1590gfE.getClass();
            if (abstractC1590gfE instanceof C1676hf) {
                return ((C1676hf) this).a(c0333y, b2, abstractC1590gfE.b(), abstractC1589ge0);
            }
        }
        if (this instanceof C2017lf) {
            abstractC1590gfE.getClass();
            if (abstractC1590gfE instanceof C2017lf) {
                C2017lf c2017lf = (C2017lf) this;
                C2017lf c2017lfJ = abstractC1590gfE.j();
                boolean z = C2017lf.c;
                if (!z && c2017lf.b.isEmpty()) {
                    x1f.a();
                    return null;
                }
                if (!z && c2017lf.a(AbstractC0439Dm.m()).isUnknown()) {
                    x1f.a();
                    return null;
                }
                if (!z && c2017lfJ.b.isEmpty()) {
                    x1f.a();
                    return null;
                }
                if (!z) {
                    c2017lfJ.getClass();
                    if (c2017lfJ.a(AbstractC0439Dm.m()).isUnknown()) {
                        x1f.a();
                        return null;
                    }
                }
                for (Map.Entry entry : c2017lfJ.b.entrySet()) {
                    C0491Fm c0491Fm = (C0491Fm) entry.getKey();
                    InterfaceC1846jf interfaceC1846jf = (InterfaceC1846jf) entry.getValue();
                    boolean z2 = C2017lf.c;
                    if (!z2 && c2017lf.b.isEmpty()) {
                        x1f.a();
                        return null;
                    }
                    if (!z2 && c2017lf.a(AbstractC0439Dm.m()).isUnknown()) {
                        x1f.a();
                        return null;
                    }
                    RO ro = (RO) interfaceC1846jf;
                    ro.getClass();
                    if (!(ro instanceof C2969wk0)) {
                        if (!z2 && !(ro instanceof C1676hf)) {
                            x1f.a();
                            return null;
                        }
                        Object objA = C2017lf.a(c0333y, b2, (InterfaceC1846jf) c2017lf.b.get(c0491Fm), interfaceC1846jf, abstractC1589ge0);
                        if (c0491Fm.l()) {
                            RO ro2 = (RO) objA;
                            ro2.getClass();
                            if (ro2 instanceof C2969wk0) {
                                qo2 = C2969wk0.a;
                            }
                        }
                        c2017lf.b.put(c0491Fm, objA);
                        qo2 = c2017lf;
                    } else if (c0491Fm.l()) {
                        qo2 = C2969wk0.a;
                    } else {
                        c2017lf.b.put(c0491Fm, interfaceC1846jf);
                        qo2 = c2017lf;
                    }
                    if (qo2 instanceof C2969wk0) {
                        return qo2;
                    }
                    if (!z2 && qo2 != c2017lf) {
                        x1f.a();
                        return null;
                    }
                }
                if (C2017lf.c || !c2017lf.a(AbstractC0439Dm.m()).isUnknown()) {
                    return c2017lf;
                }
                x1f.a();
                return null;
            }
        }
        if (a) {
            return C2969wk0.a;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.RO, com.android.tools.r8.internal.QO
    public final AbstractC1590gf e() {
        return this;
    }

    @Override // com.android.tools.r8.internal.RO, com.android.tools.r8.internal.QO
    public final boolean f() {
        return true;
    }

    @Override // com.android.tools.r8.internal.QO
    public final QO a(C0333y c0333y, com.android.tools.r8.graph.B2 b2, Function function, C1504fe0 c1504fe0) {
        return a(c0333y, b2, (QO) function.apply(this), c1504fe0);
    }
}
