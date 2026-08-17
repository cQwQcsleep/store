package defpackage;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class sa0 {
    public final String a;
    public final long b;

    public sa0(String str, long j) {
        str.getClass();
        this.a = str;
        this.b = j;
    }

    public final String a() {
        return this.a;
    }

    public final long b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof sa0)) {
            return false;
        }
        sa0 sa0Var = (sa0) obj;
        return Intrinsics.areEqual(this.a, sa0Var.a) && this.b == sa0Var.b;
    }

    public int hashCode() {
        return (this.a.hashCode() * 31) + Long.hashCode(this.b);
    }

    public String toString() {
        return "ApiHistoryMessageMeta(role=" + this.a + ", tokenEstimate=" + this.b + ")";
    }
}
