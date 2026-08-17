package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC3114yW;
import com.android.tools.r8.internal.C2967wj0;
import com.android.tools.r8.internal.HO;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class HO extends AbstractC1085ah {
    public HO(C2773uW c2773uW) {
        super(new M3(c2773uW, new Function() { // from class: a46
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new C2967wj0((AbstractC3114yW) obj);
            }
        }), new Function() { // from class: b46
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return HO.a((List) obj);
            }
        });
    }

    public static LG a(List list) {
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            c0473EuG.a((AbstractC2587sH) it.next());
        }
        AbstractC0551Hu abstractC0551HuA = c0473EuG.a();
        return abstractC0551HuA.isEmpty() ? KG.b : new KG(abstractC0551HuA);
    }
}
