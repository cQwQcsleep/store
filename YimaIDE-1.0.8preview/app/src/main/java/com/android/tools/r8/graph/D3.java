package com.android.tools.r8.graph;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class D3 {
    public static final /* synthetic */ boolean d = true;
    public final ArrayList a = new ArrayList();
    public B3.c b = null;
    public final ArrayList c = new ArrayList();

    public final B3.b a(B1 b1) {
        B3.c cVar = this.b;
        if (cVar != null) {
            if (cVar.b == b1.a2) {
                if (!d) {
                    Iterator<B3.e> it = cVar.c.iterator();
                    while (it.hasNext()) {
                        if (it.next().o()) {
                            x1f.a();
                            return null;
                        }
                    }
                }
                this.b = null;
            } else if (cVar.a()) {
                this.b = null;
            }
        }
        return (this.b == null && this.a.isEmpty() && this.c.isEmpty()) ? B3.b.f() : new B3.b(this.a, this.b, this.c);
    }

    public final D3 a(B3.f fVar) {
        this.a.add(fVar);
        return this;
    }
}
