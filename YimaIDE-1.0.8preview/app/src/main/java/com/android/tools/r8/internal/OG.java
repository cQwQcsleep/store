package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class OG extends QG {
    public static final OG b = new OG(C2245oH.a);
    public static final /* synthetic */ boolean c = true;
    public final AbstractC2587sH a;

    public OG(AbstractC2587sH abstractC2587sH) {
        if (c || abstractC2587sH != null) {
            this.a = abstractC2587sH;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.QG
    public final AbstractC2587sH a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || OG.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((OG) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a.toString();
    }
}
