package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1476fH;
import com.android.tools.r8.internal.C2158nG;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2158nG extends FG {
    public static final /* synthetic */ boolean f = true;
    public final AbstractC2515rV b;
    public final C1901kG c;
    public final C1987lG d;
    public final AbstractC2330pG e;

    public C2158nG(AbstractC2515rV abstractC2515rV, C1901kG c1901kG, C1987lG c1987lG, AbstractC2330pG abstractC2330pG) {
        boolean z = f;
        if (!z && abstractC2515rV == null) {
            x1f.a();
            throw null;
        }
        if (!z && c1901kG == null) {
            x1f.a();
            throw null;
        }
        if (!z && c1987lG == null) {
            x1f.a();
            throw null;
        }
        if (!z && abstractC2330pG == null) {
            x1f.a();
            throw null;
        }
        this.b = abstractC2515rV;
        this.c = c1901kG;
        this.d = c1987lG;
        this.e = abstractC2330pG;
    }

    public static /* synthetic */ String a(C1476fH c1476fH) {
        return "@" + c1476fH + ", ";
    }

    public static C2073mG i() {
        return new C2073mG();
    }

    @Override // com.android.tools.r8.internal.FG
    public final C2158nG b() {
        return this;
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
        if (!(obj instanceof C2158nG)) {
            return false;
        }
        C2158nG c2158nG = (C2158nG) obj;
        return this.b.equals(c2158nG.b) && this.c.equals(c2158nG.c) && this.d.equals(c2158nG.d) && this.e.equals(c2158nG.e);
    }

    public final int hashCode() {
        return Objects.hash(this.b, this.c, this.d, this.e);
    }

    public final String toString() {
        return "KeepFieldPattern{" + ((String) this.b.a(new Function() { // from class: ovh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C2158nG.a((C1476fH) obj);
            }
        })) + "access=" + this.c + ", name=" + this.d + ", type=" + this.e + "}";
    }
}
