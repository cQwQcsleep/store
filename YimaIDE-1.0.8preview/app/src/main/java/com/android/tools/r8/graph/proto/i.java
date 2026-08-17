package com.android.tools.r8.graph.proto;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC3122yc0;
import com.android.tools.r8.internal.AbstractC3148ys;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class i extends g {
    public i(AbstractC3122yc0 abstractC3122yc0, I2 i2) {
        super(abstractC3122yc0, i2);
    }

    @Override // com.android.tools.r8.graph.proto.g, com.android.tools.r8.graph.proto.b
    public final b a(C0333y c0333y, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        I2 i2C = abstractC3148ys.c(abstractC3148ys2, f());
        AbstractC3122yc0 abstractC3122yc0B = g() ? this.b.b(c0333y, i2C, abstractC3148ys, abstractC3148ys2) : null;
        return (abstractC3122yc0B == this.b && i2C == f()) ? this : new i(abstractC3122yc0B, i2C);
    }

    @Override // com.android.tools.r8.graph.proto.g
    /* JADX INFO: renamed from: b */
    public final g a(C0333y c0333y, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        I2 i2C = abstractC3148ys.c(abstractC3148ys2, f());
        AbstractC3122yc0 abstractC3122yc0B = g() ? this.b.b(c0333y, i2C, abstractC3148ys, abstractC3148ys2) : null;
        return (abstractC3122yc0B == this.b && i2C == f()) ? this : new i(abstractC3122yc0B, i2C);
    }

    @Override // com.android.tools.r8.graph.proto.g
    public final boolean equals(Object obj) {
        if (obj != null && i.class == obj.getClass()) {
            i iVar = (i) obj;
            if (f() == iVar.f() && Objects.equals(this.b, iVar.b)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.graph.proto.g
    public final int hashCode() {
        return Objects.hash(this.b, f());
    }
}
