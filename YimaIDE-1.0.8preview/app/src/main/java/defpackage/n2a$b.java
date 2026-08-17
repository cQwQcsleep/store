package defpackage;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class n2a$b {
    public final String a;
    public final String b;

    public n2a$b(String str, String str2) {
        str.getClass();
        str2.getClass();
        this.a = str;
        this.b = str2;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n2a$b)) {
            return false;
        }
        n2a$b n2a_b = (n2a$b) obj;
        return Intrinsics.areEqual(this.a, n2a_b.a) && Intrinsics.areEqual(this.b, n2a_b.b);
    }

    public int hashCode() {
        return (this.a.hashCode() * 31) + this.b.hashCode();
    }

    public String toString() {
        return "SupportInfo(qq=" + this.a + ", wechat=" + this.b + ")";
    }
}
