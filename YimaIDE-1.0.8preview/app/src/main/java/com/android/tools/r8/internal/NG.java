package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1476fH;
import com.android.tools.r8.internal.NG;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class NG extends FG {
    public static final /* synthetic */ boolean g = true;
    public final AbstractC2515rV b;
    public final HG c;
    public final IG d;
    public final QG e;
    public final LG f;

    public NG(AbstractC2515rV abstractC2515rV, HG hg, IG ig, QG qg, LG lg) {
        boolean z = g;
        if (!z && abstractC2515rV == null) {
            x1f.a();
            throw null;
        }
        if (!z && hg == null) {
            x1f.a();
            throw null;
        }
        if (!z && ig == null) {
            x1f.a();
            throw null;
        }
        if (!z && qg == null) {
            x1f.a();
            throw null;
        }
        if (!z && lg == null) {
            x1f.a();
            throw null;
        }
        this.b = abstractC2515rV;
        this.c = hg;
        this.d = ig;
        this.e = qg;
        this.f = lg;
    }

    public static /* synthetic */ String a(C1476fH c1476fH) {
        return "@" + c1476fH + ", ";
    }

    public static MG i() {
        return new MG();
    }

    @Override // com.android.tools.r8.internal.FG
    public final NG c() {
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
        if (!(obj instanceof NG)) {
            return false;
        }
        NG ng = (NG) obj;
        return this.b.equals(ng.b) && this.c.equals(ng.c) && this.d.equals(ng.d) && this.e.equals(ng.e) && this.f.equals(ng.f);
    }

    public final int hashCode() {
        return Objects.hash(this.b, this.c, this.d, this.e, this.f);
    }

    public final String toString() {
        return "KeepMethodPattern{" + ((String) this.b.a(new Function() { // from class: dba
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return NG.a((C1476fH) obj);
            }
        })) + "access=" + this.c + ", name=" + this.d + ", returnType=" + this.e + ", parameters=" + this.f + "}";
    }
}
