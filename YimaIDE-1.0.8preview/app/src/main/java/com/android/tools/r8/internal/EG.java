package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1476fH;
import com.android.tools.r8.internal.EG;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EG extends FG {
    public static final EG d = new EG(C2345pV.b, C3014xG.e);
    public final AbstractC2515rV b;
    public final C3014xG c;

    public EG(AbstractC2515rV abstractC2515rV, C3014xG c3014xG) {
        this.b = abstractC2515rV;
        this.c = c3014xG;
    }

    public static /* synthetic */ String a(C1476fH c1476fH) {
        return "@" + c1476fH + ", ";
    }

    @Override // com.android.tools.r8.internal.FG
    public final C3014xG d() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.FG
    public final AbstractC2515rV e() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EG)) {
            return false;
        }
        EG eg = (EG) obj;
        return this.b.equals(eg.b) && this.c.equals(eg.c);
    }

    public final int hashCode() {
        return Objects.hash(this.b, this.c);
    }

    public final String toString() {
        return "Member{" + ((String) this.b.a(new Function() { // from class: x34
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return EG.a((C1476fH) obj);
            }
        })) + "access=" + this.c + "}";
    }
}
