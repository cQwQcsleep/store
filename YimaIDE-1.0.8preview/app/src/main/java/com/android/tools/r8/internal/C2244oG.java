package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2244oG extends AbstractC2330pG {
    public static final C2244oG b = new C2244oG(C2245oH.a);
    public static final /* synthetic */ boolean c = true;
    public final AbstractC2587sH a;

    public C2244oG(AbstractC2587sH abstractC2587sH) {
        if (c || abstractC2587sH != null) {
            this.a = abstractC2587sH;
        } else {
            x1f.a();
            throw null;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2244oG.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((C2244oG) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a.toString();
    }
}
