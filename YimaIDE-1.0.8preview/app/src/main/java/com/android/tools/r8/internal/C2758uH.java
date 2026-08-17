package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2758uH extends AbstractC2843vH {
    public static final /* synthetic */ boolean b = true;
    public final String a;

    public C2758uH(String str) {
        if (b || str != null) {
            this.a = str;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2843vH
    public final C2758uH a() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2843vH
    public final boolean b() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC2843vH
    public final boolean c() {
        return true;
    }

    public final String d() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2758uH.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((C2758uH) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a;
    }
}
