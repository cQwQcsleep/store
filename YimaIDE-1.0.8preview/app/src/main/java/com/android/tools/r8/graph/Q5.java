package com.android.tools.r8.graph;

import com.android.tools.r8.graph.P5;
import java.util.TreeMap;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Q5 extends F5 {
    public static final /* synthetic */ boolean c = true;

    public Q5() {
        super(new TreeMap());
    }

    public final void a(D2 d2) {
        E5 e5 = (E5) this.b.computeIfAbsent(d2.getType().D0(), new Function() { // from class: gyb
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new P5((String) obj);
            }
        });
        if (!E5.d) {
            e5.getClass();
            if (!d2.getType().D0().equals(e5.b)) {
                x1f.a();
                return;
            }
        }
        e5.c.add(d2);
    }
}
