package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Dg0 {
    public final C0333y a;
    public final com.android.tools.r8.graph.I2 b;

    public Dg0(C0333y c0333y, com.android.tools.r8.graph.I2 i2) {
        this.a = c0333y;
        this.b = i2;
    }

    public final com.android.tools.r8.graph.G a(ArrayList arrayList) {
        com.android.tools.r8.graph.I2 i2 = this.b;
        List list = Collections.EMPTY_LIST;
        return new com.android.tools.r8.graph.G(i2, 16, 16, arrayList, list, list);
    }
}
