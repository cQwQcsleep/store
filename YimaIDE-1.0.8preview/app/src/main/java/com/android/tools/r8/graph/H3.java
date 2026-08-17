package com.android.tools.r8.graph;

import com.android.tools.r8.graph.B3;
import com.android.tools.r8.graph.H3;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H3 {
    public final Map a;

    public H3(HashMap map) {
        this.a = map;
    }

    public static void a(Map map, B3.f fVar) {
        B3.e eVar = fVar.b;
        if (eVar != null && eVar.b() && fVar.b.m()) {
            map.put(fVar.a, fVar.b);
        } else if (fVar.c.isEmpty() || !fVar.c.get(0).m()) {
            map.put(fVar.a, null);
        } else {
            map.put(fVar.a, fVar.c.get(0));
        }
    }

    public static H3 a(List list) {
        final HashMap map = new HashMap();
        list.forEach(new Consumer() { // from class: j26
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                H3.a(map, (B3.f) obj);
            }
        });
        return new H3(map);
    }
}
