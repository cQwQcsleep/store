package com.android.tools.r8.internal;

import defpackage.g3c;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class E40 extends AbstractC3220zi0 {
    public final Map a;

    public E40(LinkedHashMap linkedHashMap) {
        this.a = linkedHashMap;
    }

    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        if (obj == null) {
            c2754uD.i();
            return;
        }
        c2754uD.e();
        try {
            Iterator it = this.a.values().iterator();
            while (it.hasNext()) {
                ((D40) it.next()).a(c2754uD, obj);
            }
            c2754uD.g();
        } catch (IllegalAccessException e) {
            AbstractC3082y40 abstractC3082y40 = B40.a;
            g3c.a("Unexpected IllegalAccessException occurred (Gson 2.10.1). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", e);
        }
    }
}
