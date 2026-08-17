package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.shaking.O;
import defpackage.e3h;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O {
    public final Set a = AbstractC2780ub0.c();
    public final IdentityHashMap b = new IdentityHashMap();

    public final boolean a(Object obj, M0 m0) {
        if (!this.a.add(obj)) {
            return false;
        }
        ((List) this.b.getOrDefault(obj, Collections.EMPTY_LIST)).forEach(new e3h());
        return true;
    }

    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        return this.a.contains(d2);
    }

    public final boolean a(Object obj, com.android.tools.r8.internal.Y1 y1) {
        if (this.a.contains(obj)) {
            return false;
        }
        ((List) this.b.computeIfAbsent(obj, new Function() { // from class: ala
            @Override // java.util.function.Function
            public final Object apply(Object obj2) {
                return O.a(obj2);
            }
        })).add(y1);
        return true;
    }

    public static /* synthetic */ List a(Object obj) {
        return new ArrayList();
    }
}
