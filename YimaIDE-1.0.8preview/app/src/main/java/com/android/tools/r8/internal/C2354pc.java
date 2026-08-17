package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2354pc;
import com.android.tools.r8.internal.C2543rl0;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2354pc {
    public static final /* synthetic */ boolean d = true;
    public final Set a;
    public final Set b = AbstractC2780ub0.c();
    public final IdentityHashMap c = new IdentityHashMap();

    public C2354pc(C2543rl0 c2543rl0) {
        Set setC = AbstractC2780ub0.c();
        setC.add(c2543rl0);
        this.a = setC;
    }

    public static /* synthetic */ List b(C2543rl0 c2543rl0) {
        return new ArrayList();
    }

    public final boolean a(C2543rl0 c2543rl0) {
        if (this.b.contains(c2543rl0)) {
            return false;
        }
        List list = (List) this.c.get(c2543rl0);
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (!((BooleanSupplier) it.next()).getAsBoolean()) {
                    return false;
                }
            }
        }
        this.c.remove(c2543rl0);
        boolean zAdd = this.a.add(c2543rl0);
        if (d || zAdd) {
            return true;
        }
        throw new AssertionError(c2543rl0.toString() + " already added as an alias");
    }

    public final void a(C2543rl0 c2543rl0, BooleanSupplier booleanSupplier) {
        boolean z = d;
        if (!z && this.a.contains(c2543rl0)) {
            x1f.a();
            return;
        }
        if (this.b.contains(c2543rl0)) {
            if (z || !this.c.containsKey(c2543rl0)) {
                return;
            }
            x1f.a();
            return;
        }
        ((List) this.c.computeIfAbsent(c2543rl0, new Function() { // from class: s0i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C2354pc.b((C2543rl0) obj);
            }
        })).add(booleanSupplier);
    }
}
