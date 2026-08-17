package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class PN implements ON {
    public static final /* synthetic */ boolean b = true;
    public final ArrayList a = new ArrayList();

    @Override // com.android.tools.r8.internal.ON
    public final boolean a(com.android.tools.r8.graph.I2 i2) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            if (((ON) it.next()).a(i2)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.ON
    public final boolean b(com.android.tools.r8.graph.I2 i2) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            if (((ON) it.next()).b(i2)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.ON
    public final com.android.tools.r8.graph.I2 a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.internal.ON
    public final void a(C0333y c0333y) {
        for (ON on : this.a) {
            if (!b) {
                on.a(c0333y);
            }
        }
    }
}
