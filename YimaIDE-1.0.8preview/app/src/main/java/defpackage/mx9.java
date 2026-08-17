package defpackage;

import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class mx9 {
    public final String a;
    public final String b;
    public final JSONObject c;

    public mx9(String str, String str2, JSONObject jSONObject) {
        str.getClass();
        str2.getClass();
        jSONObject.getClass();
        this.a = str;
        this.b = str2;
        this.c = jSONObject;
    }

    public final String a() {
        return this.b;
    }

    public final JSONObject b() {
        return this.c;
    }

    public final String c() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof mx9)) {
            return false;
        }
        mx9 mx9Var = (mx9) obj;
        return Intrinsics.areEqual(this.a, mx9Var.a) && Intrinsics.areEqual(this.b, mx9Var.b) && Intrinsics.areEqual(this.c, mx9Var.c);
    }

    public int hashCode() {
        return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    public String toString() {
        return "McpToolDef(name=" + this.a + ", description=" + this.b + ", inputSchema=" + this.c + ")";
    }
}
