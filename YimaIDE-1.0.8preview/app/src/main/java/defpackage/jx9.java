package defpackage;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class jx9 {
    public final String a;
    public final boolean b;
    public final int c;
    public final String d;
    public final String e;

    public /* synthetic */ jx9(String str, boolean z, int i, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? "" : str2, (i2 & 16) != 0 ? "" : str3);
    }

    public final boolean a() {
        return this.b;
    }

    public final String b() {
        return this.d;
    }

    public final String c() {
        return this.e;
    }

    public final int d() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof jx9)) {
            return false;
        }
        jx9 jx9Var = (jx9) obj;
        return Intrinsics.areEqual(this.a, jx9Var.a) && this.b == jx9Var.b && this.c == jx9Var.c && Intrinsics.areEqual(this.d, jx9Var.d) && Intrinsics.areEqual(this.e, jx9Var.e);
    }

    public int hashCode() {
        return (((((((this.a.hashCode() * 31) + Boolean.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + this.d.hashCode()) * 31) + this.e.hashCode();
    }

    public String toString() {
        return "McpSessionStatus(serverId=" + this.a + ", connected=" + this.b + ", toolCount=" + this.c + ", lastError=" + this.d + ", serverLabel=" + this.e + ")";
    }

    public jx9(String str, boolean z, int i, String str2, String str3) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        this.a = str;
        this.b = z;
        this.c = i;
        this.d = str2;
        this.e = str3;
    }
}
