package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.shaking.H0;
import java.util.IdentityHashMap;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H0 {
    public final IdentityHashMap a = new IdentityHashMap();

    public static /* synthetic */ I0 b(C0245l1 c0245l1) {
        return new I0();
    }

    public final I0 a(C0245l1 c0245l1) {
        return (I0) this.a.computeIfAbsent(c0245l1, new Function() { // from class: e16
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return H0.b((C0245l1) obj);
            }
        });
    }
}
