package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ul0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2799ul0 {
    public final C2543rl0 a;
    public final Set b;
    public final Set c;
    public final Set d;

    public C2799ul0(C2543rl0 c2543rl0) {
        Set setC = AbstractC2780ub0.c();
        this.b = setC;
        this.c = AbstractC2780ub0.c();
        this.d = AbstractC2780ub0.c();
        setC.add(c2543rl0);
        this.a = c2543rl0;
    }

    public final boolean a(Predicate predicate) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            if (predicate.test((C2799ul0) it.next())) {
                return true;
            }
        }
        return false;
    }

    public final Set a() {
        return this.d;
    }
}
