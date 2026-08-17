package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C1755ib0;
import com.android.tools.r8.internal.XR;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class k4 {
    public final Set a;
    public final Set b;
    public final Set c;

    public k4(Set set, Set set2, Set set3) {
        this.a = set;
        this.b = set2;
        this.c = set3;
    }

    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        return this.a.contains(d2.e) || this.b.contains(d2.e) || this.c.contains(d2.e);
    }

    public final k4 a(final XR xr, final AbstractC3148ys abstractC3148ys) {
        Function function = new Function() { // from class: rhh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return xr.c(abstractC3148ys, (I2) obj);
            }
        };
        return new k4(C1755ib0.a(this.a, function), C1755ib0.a(this.b, function), C1755ib0.a(this.c, function));
    }
}
