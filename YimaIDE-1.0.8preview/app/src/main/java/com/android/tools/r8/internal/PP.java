package com.android.tools.r8.internal;

import com.android.tools.r8.internal.PP;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class PP<T> extends NP {
    public abstract T a(com.android.tools.r8.graph.D2 d2);

    @Override // com.android.tools.r8.internal.NP
    public final Collection a(C1868jt c1868jt) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (com.android.tools.r8.graph.D2 d2 : c1868jt.b) {
            T tA = a(d2);
            if (tA != null) {
                ((C1868jt) linkedHashMap.computeIfAbsent(tA, new Function() { // from class: dva
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return PP.a(obj);
                    }
                })).b.add(d2);
            }
        }
        AbstractC1238cX.a(linkedHashMap.values());
        return linkedHashMap.values();
    }

    public static /* synthetic */ C1868jt a(Object obj) {
        return new C1868jt();
    }
}
