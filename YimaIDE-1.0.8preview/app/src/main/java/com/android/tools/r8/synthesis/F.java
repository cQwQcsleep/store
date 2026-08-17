package com.android.tools.r8.synthesis;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC2469qv;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C2041lv;
import com.android.tools.r8.internal.XR;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class F {
    public static final /* synthetic */ boolean b = true;
    public final ConcurrentHashMap a = new ConcurrentHashMap();

    public final void a(XR xr) {
        if (b) {
            return;
        }
        ConcurrentHashMap.KeySetView keySetViewKeySet = this.a.keySet();
        ConcurrentHashMap.KeySetView keySetViewKeySet2 = this.a.keySet();
        xr.getClass();
        AbstractC2469qv abstractC2469qvA = C2041lv.c;
        Iterator it = keySetViewKeySet2.iterator();
        while (it.hasNext()) {
            I2 i2C = xr.c(AbstractC3148ys.g(), (I2) it.next());
            Objects.requireNonNull(abstractC2469qvA);
            i2C.getClass();
            abstractC2469qvA = abstractC2469qvA.a(i2C);
        }
        Objects.requireNonNull(abstractC2469qvA);
        if (keySetViewKeySet.equals(abstractC2469qvA.c().a())) {
            return;
        }
        x1f.a();
    }
}
