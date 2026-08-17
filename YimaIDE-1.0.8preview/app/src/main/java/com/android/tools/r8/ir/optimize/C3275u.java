package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.O2;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.ir.optimize.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3275u {
    public static final C3275u b = new C3275u(null);
    public static final /* synthetic */ boolean c = true;
    public final Map a;

    public C3275u(IdentityHashMap identityHashMap) {
        this.a = identityHashMap;
    }

    public final void a(final BiConsumer biConsumer, final C0333y c0333y) {
        a(new BiConsumer() { // from class: wdi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                O2 o2 = (O2) obj2;
                biConsumer.accept(((C0210g1) obj).a(c0333y), o2);
            }
        });
    }

    public final void a(BiConsumer biConsumer) {
        Map map = this.a;
        if (map != null) {
            map.forEach(biConsumer);
        }
    }
}
