package com.android.tools.r8.graph;

import java.util.ArrayList;
import java.util.function.BiFunction;

/* JADX INFO: renamed from: com.android.tools.r8.graph.l4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0248l4 implements BiFunction {
    public final C0220h4 a;
    public int b = 0;

    public C0248l4(C0220h4 c0220h4) {
        this.a = c0220h4;
    }

    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        ArrayList arrayList = this.a.c;
        int i = this.b;
        this.b = i + 1;
        return ((AbstractC0223i0) arrayList.get(i)).q0();
    }
}
