package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.EnumC2326pC;
import com.android.tools.r8.internal.IN;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class IN {
    public static final /* synthetic */ boolean c = true;
    public final C0333y a;
    public final IdentityHashMap b = new IdentityHashMap();

    public IN(C0333y c0333y) {
        this.a = c0333y;
    }

    public final void a(C0322w2 c0322w2, C0322w2 c0322w3, EnumC2326pC enumC2326pC) {
        if (c0322w2 == c0322w3) {
            if (c || !this.b.containsKey(enumC2326pC) || ((Map) this.b.get(enumC2326pC)).getOrDefault(c0322w2, c0322w3) == c0322w3) {
                return;
            }
            x1f.a();
            return;
        }
        Map map = (Map) this.b.computeIfAbsent(enumC2326pC, new Function() { // from class: ng6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return IN.a((EnumC2326pC) obj);
            }
        });
        if (c || map.getOrDefault(c0322w2, c0322w3) == c0322w3) {
            map.put(c0322w2, c0322w3);
        } else {
            x1f.a();
        }
    }

    public static /* synthetic */ Map a(EnumC2326pC enumC2326pC) {
        return new IdentityHashMap();
    }
}
