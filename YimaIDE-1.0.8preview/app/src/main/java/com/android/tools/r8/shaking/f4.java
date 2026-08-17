package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.PY;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class f4 {
    public final C a;
    public final Map b;
    public final List c;
    public final PY d;

    public f4(C c, Map map, List list, PY py) {
        this.a = c;
        this.b = map;
        this.c = list;
        this.d = py;
    }

    public final Set a(com.android.tools.r8.graph.I2 i2) {
        return (Set) this.b.get(i2);
    }

    public final C a() {
        return this.a;
    }
}
