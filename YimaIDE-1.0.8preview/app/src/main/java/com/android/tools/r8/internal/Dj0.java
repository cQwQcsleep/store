package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Dj0 {
    public abstract com.android.tools.r8.graph.I2 a(com.android.tools.r8.graph.I2 i2);

    public abstract void a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3);

    public abstract void a(Consumer consumer);

    public abstract boolean a();

    public final boolean a(com.android.tools.r8.graph.E2 e2, C0333y c0333y) {
        if (a(c0333y, e2.e)) {
            return true;
        }
        for (com.android.tools.r8.graph.I2 i2 : e2.f.b) {
            if (a(c0333y, i2)) {
                return true;
            }
        }
        return false;
    }

    public abstract com.android.tools.r8.graph.I2 b(C0333y c0333y, com.android.tools.r8.graph.I2 i2);

    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.I2 i2) {
        return b(c0333y, i2) != null;
    }
}
