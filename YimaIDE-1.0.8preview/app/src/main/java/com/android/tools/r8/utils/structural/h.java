package com.android.tools.r8.utils.structural;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.utils.structural.h;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class h {
    public static int a(x xVar, Object obj, v vVar) {
        t tVar = new t() { // from class: gzg
            @Override // com.android.tools.r8.utils.structural.t
            public final I2 a(I2 i2) {
                return h.a(i2);
            }
        };
        if (xVar == obj) {
            return 0;
        }
        return vVar.a(xVar, obj, new f(tVar));
    }

    public static /* synthetic */ I2 a(I2 i2) {
        return i2;
    }
}
