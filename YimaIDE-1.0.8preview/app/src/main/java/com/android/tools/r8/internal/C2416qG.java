package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2416qG extends AbstractC2500rG {
    public static final C2416qG c = new C2416qG(C1476fH.a(), true);
    public static final /* synthetic */ boolean d = true;
    public final C1476fH a;
    public final boolean b;

    public C2416qG(C1476fH c1476fH, boolean z) {
        if (!d && c1476fH == null) {
            x1f.a();
            throw null;
        }
        this.a = c1476fH;
        this.b = z;
    }

    @Override // com.android.tools.r8.internal.AbstractC2500rG
    public final boolean a() {
        return this.a.d();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C2416qG) {
            return this.a.equals(((C2416qG) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        String string = this.a.toString();
        if (this.b) {
            return string;
        }
        return "excl(" + string + ")";
    }
}
