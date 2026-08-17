package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class FX {
    public final Set a = AbstractC2780ub0.c();
    public final ArrayList b = new ArrayList();

    public final boolean a(Object obj) {
        if (this.a.contains(obj)) {
            return true;
        }
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            if (((Predicate) it.next()).test(obj)) {
                return true;
            }
        }
        return false;
    }

    public final void a(com.android.tools.r8.graph.I2 i2) {
        this.a.add(i2);
    }
}
