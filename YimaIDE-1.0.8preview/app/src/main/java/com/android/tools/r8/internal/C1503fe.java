package com.android.tools.r8.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fe, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1503fe extends AbstractC3220zi0 {
    public final Bi0 a;

    public C1503fe(C0471Es c0471Es, Type type, AbstractC3220zi0 abstractC3220zi0, AU au) {
        this.a = new Bi0(c0471Es, abstractC3220zi0, type);
    }

    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        Collection collection = (Collection) obj;
        if (collection == null) {
            c2754uD.i();
            return;
        }
        c2754uD.d();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            this.a.a(c2754uD, it.next());
        }
        c2754uD.f();
    }
}
