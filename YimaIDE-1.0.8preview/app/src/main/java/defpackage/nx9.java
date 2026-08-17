package defpackage;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class nx9 {
    public final List a;
    public final boolean b;

    public nx9(List list, boolean z) {
        list.getClass();
        this.a = list;
        this.b = z;
    }

    public final List a() {
        return this.a;
    }

    public final boolean b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof nx9)) {
            return false;
        }
        nx9 nx9Var = (nx9) obj;
        return Intrinsics.areEqual(this.a, nx9Var.a) && this.b == nx9Var.b;
    }

    public int hashCode() {
        return (this.a.hashCode() * 31) + Boolean.hashCode(this.b);
    }

    public String toString() {
        return "McpToolsListResult(tools=" + this.a + ", truncated=" + this.b + ")";
    }
}
