package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ma, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2092ma {
    public static AbstractC2092ma a(C0333y c0333y, PA pa, Predicate predicate) {
        if (!c0333y.M().E0.a()) {
            return C1921ka.a;
        }
        ArrayList arrayList = new ArrayList();
        if (c0333y.M().K1.c.r() && !c0333y.M().K1.a) {
            arrayList.add(new C0722Ok(c0333y));
        }
        if (pa != null) {
            arrayList.add(pa);
        }
        C0567Ik c0567Ik = null;
        C0359Ak c0359Ak = c0333y.z.a() ? new C0359Ak(c0333y, predicate) : null;
        if (c0359Ak != null) {
            arrayList.add(c0359Ak);
        }
        C2137n20 c2137n20 = c0333y.M().o0() ? new C2137n20(c0333y) : null;
        if (c2137n20 != null) {
            arrayList.add(c2137n20);
        }
        Iterator it = c0333y.a().h3.iterator();
        while (it.hasNext()) {
            if (c0333y.g().c((com.android.tools.r8.graph.I2) it.next()) != null) {
                c0567Ik = new C0567Ik(c0333y);
                break;
            }
        }
        if (c0567Ik != null) {
            arrayList.add(c0567Ik);
        }
        return arrayList.isEmpty() ? C1921ka.a : new C2007la(arrayList);
    }

    public abstract void a(Collection collection, AbstractC2350pa abstractC2350pa, ExecutorService executorService);
}
