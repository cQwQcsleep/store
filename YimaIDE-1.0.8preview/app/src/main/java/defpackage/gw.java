package defpackage;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class gw {
    public final String a;
    public final String b;
    public final List c;
    public final boolean d;
    public final boolean e;

    public gw(String str, String str2, List list, boolean z, boolean z2) {
        str.getClass();
        str2.getClass();
        list.getClass();
        this.a = str;
        this.b = str2;
        this.c = list;
        this.d = z;
        this.e = z2;
    }

    public final boolean a() {
        return this.e;
    }

    public final boolean b() {
        return this.d;
    }

    public final String c() {
        return this.a;
    }

    public final List d() {
        return this.c;
    }

    public final String e() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof gw)) {
            return false;
        }
        gw gwVar = (gw) obj;
        return Intrinsics.areEqual(this.a, gwVar.a) && Intrinsics.areEqual(this.b, gwVar.b) && Intrinsics.areEqual(this.c, gwVar.c) && this.d == gwVar.d && this.e == gwVar.e;
    }

    public int hashCode() {
        return (((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + Boolean.hashCode(this.d)) * 31) + Boolean.hashCode(this.e);
    }

    public String toString() {
        return "AgentQuestionItem(id=" + this.a + ", prompt=" + this.b + ", options=" + this.c + ", allowMultiple=" + this.d + ", allowCustom=" + this.e + ")";
    }
}
