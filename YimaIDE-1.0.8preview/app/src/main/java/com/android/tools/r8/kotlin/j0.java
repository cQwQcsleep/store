package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0333y;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface j0 extends G, f0 {
    @Override // com.android.tools.r8.kotlin.P
    default boolean a() {
        return true;
    }

    boolean a(Consumer consumer, C0210g1 c0210g1, C0231j1 c0231j1, C0231j1 c0231j2, C0231j1 c0231j3, C0333y c0333y);

    boolean a(Consumer consumer, C0333y c0333y);

    @Override // com.android.tools.r8.kotlin.P
    default j0 b() {
        return this;
    }

    default j0 getReference() {
        return this;
    }

    default int s() {
        return 4;
    }
}
