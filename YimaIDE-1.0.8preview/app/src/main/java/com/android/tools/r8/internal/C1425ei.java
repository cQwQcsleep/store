package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ei, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1425ei {
    public final Set a = AbstractC2780ub0.c();
    public final Set b = AbstractC2780ub0.c();

    public final C1340di a() {
        this.a.removeAll(this.b);
        return (this.a.isEmpty() && this.b.isEmpty()) ? C1340di.c : new C1340di(AL.a(this.a), AL.a(this.b));
    }

    public final void a(C0322w2 c0322w2) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            C0231j1 c0231j1 = (C0231j1) it.next();
            if (c0322w2.a(c0231j1)) {
                this.b.add(c0231j1);
                it.remove();
            }
        }
    }

    public final void a(C0231j1 c0231j1) {
        this.a.add(c0231j1);
    }

    public final void a(C1340di c1340di) {
        this.a.addAll(c1340di.a);
        this.b.addAll(c1340di.b);
    }
}
