package defpackage;

import java.io.IOException;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class tv9 {
    public final String a;
    public final String b;
    public final String c;
    public final boolean d;
    public final Map e;

    public tv9(String str, String str2, String str3, boolean z, Map map) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        map.getClass();
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = z;
        this.e = map;
    }

    public static /* synthetic */ tv9 b(tv9 tv9Var, String str, String str2, String str3, boolean z, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tv9Var.a;
        }
        if ((i & 2) != 0) {
            str2 = tv9Var.b;
        }
        if ((i & 4) != 0) {
            str3 = tv9Var.c;
        }
        if ((i & 8) != 0) {
            z = tv9Var.d;
        }
        if ((i & 16) != 0) {
            map = tv9Var.e;
        }
        Map map2 = map;
        String str4 = str3;
        return tv9Var.a(str, str2, str4, z, map2);
    }

    public final tv9 a(String str, String str2, String str3, boolean z, Map map) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        map.getClass();
        return new tv9(str, str2, str3, z, map);
    }

    public final boolean c() {
        return this.d;
    }

    public final Map d() {
        return this.e;
    }

    public final String e() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof tv9)) {
            return false;
        }
        tv9 tv9Var = (tv9) obj;
        return Intrinsics.areEqual(this.a, tv9Var.a) && Intrinsics.areEqual(this.b, tv9Var.b) && Intrinsics.areEqual(this.c, tv9Var.c) && this.d == tv9Var.d && Intrinsics.areEqual(this.e, tv9Var.e);
    }

    public final String f() {
        return this.b;
    }

    public final String g() throws IOException {
        String str = this.a;
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (Character.isLetterOrDigit(cCharAt) || cCharAt == '_' || cCharAt == '-') {
                sb.append(cCharAt);
            }
        }
        String strTake = StringsKt.take(sb.toString(), 12);
        return StringsKt.isBlank(strTake) ? "srv" : strTake;
    }

    public final String h() {
        return this.c;
    }

    public int hashCode() {
        return (((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + Boolean.hashCode(this.d)) * 31) + this.e.hashCode();
    }

    public final boolean i(tv9 tv9Var) {
        tv9Var.getClass();
        return Intrinsics.areEqual(StringsKt.trim(this.c).toString(), StringsKt.trim(tv9Var.c).toString()) && Intrinsics.areEqual(this.e, tv9Var.e);
    }

    public String toString() {
        return "McpServerConfig(id=" + this.a + ", name=" + this.b + ", url=" + this.c + ", enabled=" + this.d + ", headers=" + this.e + ")";
    }
}
