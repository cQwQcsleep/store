package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ME extends AbstractC2671tG {
    public static final /* synthetic */ boolean d = true;
    public final C1476fH a;
    public final AbstractC2500rG b;
    public final AbstractC2515rV c;

    public ME(C1476fH c1476fH, AbstractC2500rG abstractC2500rG, AbstractC2515rV abstractC2515rV) {
        boolean z = d;
        if (!z && c1476fH == null) {
            x1f.a();
            throw null;
        }
        if (!z && abstractC2500rG == null) {
            x1f.a();
            throw null;
        }
        if (!z && abstractC2515rV == null) {
            x1f.a();
            throw null;
        }
        this.a = c1476fH;
        this.b = abstractC2500rG;
        this.c = abstractC2515rV;
    }

    public static ME g() {
        return new ME(C1476fH.a(), C2416qG.c, C2345pV.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC2671tG
    public final ME a() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2671tG
    public final Collection c() {
        return Collections.EMPTY_LIST;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ME)) {
            return false;
        }
        ME me = (ME) obj;
        return this.a.equals(me.a) && this.b.equals(me.b) && this.c.equals(me.c);
    }

    @Override // com.android.tools.r8.internal.AbstractC2671tG
    public final AbstractC2757uG f() {
        return new OE(this);
    }

    public final C1476fH h() {
        return this.a;
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b, this.c);
    }

    public final AbstractC2500rG i() {
        return this.b;
    }

    public final String toString() {
        return "KeepClassItemPattern{ class=" + this.a + ", annotated-by=" + this.c + ", instance-of=" + this.b + "}";
    }
}
