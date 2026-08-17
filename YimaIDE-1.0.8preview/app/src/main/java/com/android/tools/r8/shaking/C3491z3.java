package com.android.tools.r8.shaking;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.z3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3491z3 extends A3 {
    public final String c;

    public C3491z3(String str) {
        this.c = str;
    }

    @Override // com.android.tools.r8.shaking.A3
    public final boolean a(String str) {
        return this.c.equals(str);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C3491z3) && this.c.equals(((C3491z3) obj).c);
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    public final String toString() {
        return this.c;
    }
}
