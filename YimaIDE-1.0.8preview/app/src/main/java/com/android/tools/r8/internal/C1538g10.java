package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0343z2;
import com.android.tools.r8.graph.K2;
import com.android.tools.r8.internal.C1538g10;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.g10, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1538g10 {
    public final HashMap a;
    public final C0775Ql b;

    public C1538g10(ConcurrentHashMap concurrentHashMap, C0775Ql c0775Ql) {
        this.a = a(concurrentHashMap);
        this.b = c0775Ql;
    }

    public final com.android.tools.r8.graph.K2 a(C0343z2 c0343z2) {
        com.android.tools.r8.graph.K2 k2Q0 = c0343z2.c.C0().q0();
        com.android.tools.r8.graph.I2[] i2Arr = k2Q0.b;
        if (i2Arr.length > 1) {
            com.android.tools.r8.graph.I2[] i2Arr2 = (com.android.tools.r8.graph.I2[]) i2Arr.clone();
            Arrays.sort(i2Arr2);
            k2Q0 = new com.android.tools.r8.graph.K2(i2Arr2);
        }
        return (com.android.tools.r8.graph.K2) this.a.getOrDefault(k2Q0, k2Q0);
    }

    public final boolean b(C0343z2 c0343z2) {
        return this.b.b.contains(c0343z2);
    }

    public static HashMap a(ConcurrentHashMap concurrentHashMap) {
        final HashMap map = new HashMap();
        concurrentHashMap.forEach(new BiConsumer() { // from class: swg
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C1538g10.a(map, (K2) obj, (Set) obj2);
            }
        });
        return map;
    }

    public static /* synthetic */ void a(Map map, com.android.tools.r8.graph.K2 k2, Set set) {
        Iterator it = set.iterator();
        com.android.tools.r8.graph.K2 k3 = (com.android.tools.r8.graph.K2) it.next();
        while (it.hasNext()) {
            com.android.tools.r8.graph.K2 k4 = (com.android.tools.r8.graph.K2) it.next();
            if (k4.compareTo(k3) < 0) {
                k3 = k4;
            }
        }
        map.put(k2, k3);
    }
}
