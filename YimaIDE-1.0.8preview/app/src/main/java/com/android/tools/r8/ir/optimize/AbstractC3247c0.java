package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.InterfaceC0189d1;

/* JADX INFO: renamed from: com.android.tools.r8.ir.optimize.c0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3247c0 {
    public static final /* synthetic */ boolean a = true;

    public static boolean a(I2 i2, I2 i3, InterfaceC0189d1 interfaceC0189d1) {
        com.android.tools.r8.graph.E0 e0D;
        if (i2 == i3) {
            return true;
        }
        com.android.tools.r8.graph.E0 e0D2 = interfaceC0189d1.d(i2);
        return e0D2 != null && e0D2.t1() && (e0D = interfaceC0189d1.d(i3)) != null && e0D2.W0() == e0D.W0();
    }
}
