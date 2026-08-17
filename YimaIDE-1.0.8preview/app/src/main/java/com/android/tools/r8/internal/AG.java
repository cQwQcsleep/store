package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class AG extends AbstractC2671tG {
    public static final /* synthetic */ boolean c = true;
    public final PE a;
    public final FG b;

    public AG(PE pe, FG fg) {
        boolean z = c;
        if (!z && pe == null) {
            x1f.a();
            throw null;
        }
        if (!z && fg == null) {
            x1f.a();
            throw null;
        }
        this.a = pe;
        this.b = fg;
    }

    public static C3183zG g() {
        return new C3183zG();
    }

    @Override // com.android.tools.r8.internal.AbstractC2671tG
    public final AG b() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2671tG
    public final Collection c() {
        return this.a.h();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AG)) {
            return false;
        }
        AG ag = (AG) obj;
        return this.a.equals(ag.a) && this.b.equals(ag.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC2671tG
    public final AbstractC2757uG f() {
        return new CG(this);
    }

    public final PE h() {
        return this.a;
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b);
    }

    public final String toString() {
        return "KeepMemberItemPattern{ class=" + this.a + ", members=" + this.b + "}";
    }
}
