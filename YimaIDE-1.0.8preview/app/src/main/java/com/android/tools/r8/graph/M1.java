package com.android.tools.r8.graph;

import com.android.tools.r8.internal.C1755ib0;
import java.util.Set;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class M1 {
    public final E2 a;
    public final Supplier b;
    public final Set c;
    public final /* synthetic */ B1 d;

    public M1(B1 b1, E2 e2, Supplier supplier, Set set) {
        this.d = b1;
        this.a = e2;
        this.b = supplier;
        this.c = C1755ib0.a((Iterable) set);
    }
}
