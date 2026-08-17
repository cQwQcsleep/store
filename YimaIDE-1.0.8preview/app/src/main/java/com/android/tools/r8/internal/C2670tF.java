package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2670tF extends C2756uF {
    public static final /* synthetic */ boolean c = true;
    public final String b;

    public C2670tF(String str) {
        if (c || str != null) {
            this.b = str;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.C2756uF
    public final String a() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.C2756uF
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C2670tF) {
            return this.b.equals(((C2670tF) obj).b);
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.C2756uF
    public final int hashCode() {
        return this.b.hashCode();
    }
}
