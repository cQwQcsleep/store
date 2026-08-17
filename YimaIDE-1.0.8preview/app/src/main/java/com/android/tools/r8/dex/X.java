package com.android.tools.r8.dex;

import com.android.tools.r8.graph.AbstractC0259n1;
import com.android.tools.r8.graph.C0203f1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0285r0;
import com.android.tools.r8.graph.C0292s0;
import com.android.tools.r8.graph.C0304t5;
import com.android.tools.r8.graph.C0306u0;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.InterfaceC0170a3;
import com.android.tools.r8.graph.K2;
import com.android.tools.r8.graph.Z0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class X {
    public abstract void a(D2 d2, C0292s0 c0292s0);

    public abstract boolean a(D2 d2);

    public abstract boolean a(K2 k2);

    public abstract boolean a(Z0 z0);

    public abstract boolean a(C0203f1 c0203f1);

    public abstract boolean a(C0231j1 c0231j1);

    public abstract boolean a(C0285r0 c0285r0);

    public abstract boolean a(C0304t5 c0304t5);

    public abstract boolean a(C0306u0 c0306u0);

    public void b(C0231j1 c0231j1) {
        InterfaceC0170a3 interfaceC0170a3V0 = c0231j1.V0();
        if (interfaceC0170a3V0 != null && a(c0231j1)) {
            interfaceC0170a3V0.a(this);
        }
        C0306u0 c0306u0N0 = c0231j1.n0();
        c0306u0N0.getClass();
        a(c0306u0N0);
        AbstractC0259n1.a(this, c0306u0N0.d);
        C0304t5 c0304t5 = c0231j1.i;
        AbstractC0259n1.a(this, c0304t5.b);
        a(c0304t5);
    }
}
