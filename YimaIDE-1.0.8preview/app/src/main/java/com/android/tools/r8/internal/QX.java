package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0208g;
import com.android.tools.r8.graph.C0231j1;
import com.sun.jna.platform.linux.Fcntl;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class QX {
    public final com.android.tools.r8.graph.F4 a;
    public final boolean b;
    public final AbstractC2173nV c;

    public QX(C0231j1 c0231j1, boolean z) {
        boolean z2 = com.android.tools.r8.graph.F4.f;
        com.android.tools.r8.graph.E4 e4 = new com.android.tools.r8.graph.E4();
        boolean zI = c0231j1.getAccessFlags().i();
        AbstractC0208g abstractC0208g = e4.a;
        if (zI) {
            abstractC0208g.w();
        } else {
            abstractC0208g.B();
        }
        boolean zL = c0231j1.getAccessFlags().l();
        AbstractC0208g abstractC0208g2 = e4.a;
        if (zL) {
            abstractC0208g2.x();
        } else {
            abstractC0208g2.C();
        }
        com.android.tools.r8.graph.E4 e5 = (com.android.tools.r8.graph.E4) e4.a(c0231j1.getAccessFlags().m());
        if (c0231j1.getAccessFlags().N()) {
            ((com.android.tools.r8.graph.F4) e5.a).b(Fcntl.S_ISUID);
        } else {
            ((com.android.tools.r8.graph.F4) e5.a).c(Fcntl.S_ISUID);
        }
        if (c0231j1.getAccessFlags().O()) {
            ((com.android.tools.r8.graph.F4) e5.a).b(32);
        } else {
            ((com.android.tools.r8.graph.F4) e5.a).c(32);
        }
        this.a = (com.android.tools.r8.graph.F4) e5.a;
        this.b = z;
        this.c = c0231j1.r1();
    }

    public final boolean equals(Object obj) {
        if (obj == null || QX.class != obj.getClass()) {
            return false;
        }
        QX qx = (QX) obj;
        return this.a.equals(qx.a) && this.b == qx.b && this.c == qx.c;
    }

    public final int hashCode() {
        return Objects.hash(this.a, Boolean.valueOf(this.b), Integer.valueOf(this.c.f()));
    }
}
