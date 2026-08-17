package com.android.tools.r8.internal;

import defpackage.yj6;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xl0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3056xl0 {
    public static final /* synthetic */ boolean c = true;
    public final IdentityHashMap a = new IdentityHashMap();
    public final IdentityHashMap b = new IdentityHashMap();

    public final Set a(C2543rl0 c2543rl0) {
        if (!c && this.a.containsKey(c2543rl0)) {
            x1f.a();
            return null;
        }
        Set<C2543rl0> set = (Set) IM.a(this.b, c2543rl0, Collections.EMPTY_SET);
        for (C2543rl0 c2543rl1 : set) {
            Set set2 = (Set) this.a.get(c2543rl1);
            boolean zRemove = set2.remove(c2543rl0);
            if (!c && !zRemove) {
                x1f.a();
                return null;
            }
            if (set2.isEmpty()) {
                this.a.remove(c2543rl1);
            }
        }
        return set;
    }

    public final void b(C2543rl0 c2543rl0) {
        for (C2543rl0 c2543rl1 : (Set) IM.a(this.a, c2543rl0, Collections.EMPTY_SET)) {
            Set set = (Set) this.b.get(c2543rl1);
            boolean zRemove = set.remove(c2543rl0);
            if (!c && !zRemove) {
                x1f.a();
                return;
            } else if (set.isEmpty()) {
                this.b.remove(c2543rl1);
            }
        }
    }

    public final void a(C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        ((Set) this.a.computeIfAbsent(c2543rl0, IM.a(new yj6()))).add(c2543rl1);
        ((Set) this.b.computeIfAbsent(c2543rl1, IM.a(new yj6()))).add(c2543rl0);
    }
}
