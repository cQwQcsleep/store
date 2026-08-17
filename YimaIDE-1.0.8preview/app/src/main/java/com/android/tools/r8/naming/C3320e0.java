package com.android.tools.r8.naming;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.internal.C2119mo;
import com.android.tools.r8.internal.PO;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.naming.e0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3320e0 extends AbstractC3316c0 {
    public final C3320e0 c;

    public C3320e0(C3320e0 c3320e0, Function function) {
        super(function);
        this.c = c3320e0;
    }

    public final void a(com.android.tools.r8.graph.H0 h0, H2 h2) {
        try {
            ((C3318d0) c(h0.getReference())).a(h0, h2);
        } catch (AssertionError e) {
            throw new RuntimeException(String.format("Assertion error when trying to reserve name '%s' for method '%s'", h2, h0), e);
        }
    }

    public final Set d(C0322w2 c0322w2) {
        C3320e0 c3320e0;
        HashMap map;
        C3318d0 c3318d0 = (C3318d0) b(c0322w2);
        Set set = null;
        if (c3318d0 != null && (map = c3318d0.a) != null) {
            set = (Set) map.get(new C2119mo(PO.a, c0322w2));
        }
        return (set != null || (c3320e0 = this.c) == null) ? set : c3320e0.d(c0322w2);
    }

    public final boolean a(H2 h2, C0322w2 c0322w2) {
        HashSet hashSet;
        C3318d0 c3318d0 = (C3318d0) b(c0322w2);
        if (c3318d0 != null && (hashSet = c3318d0.b) != null && hashSet.contains(h2)) {
            return true;
        }
        C3320e0 c3320e0 = this.c;
        if (c3320e0 != null) {
            return c3320e0.a(h2, c0322w2);
        }
        return false;
    }

    @Override // com.android.tools.r8.naming.AbstractC3316c0
    public final Object a(C0322w2 c0322w2) {
        return new C3318d0();
    }
}
