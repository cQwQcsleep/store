package defpackage;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class lsa {
    public final String a;
    public final String b;

    public lsa(String str, String str2) {
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
        if (!(obj instanceof lsa)) {
            return false;
        }
        lsa lsaVar = (lsa) obj;
        return Intrinsics.areEqual(this.a, lsaVar.a) && Intrinsics.areEqual(this.b, lsaVar.b);
    }

    public int hashCode() {
        return (this.a.hashCode() * 31) + this.b.hashCode();
    }

    public String toString() {
        return "OssSourceLink(label=" + this.a + ", url=" + this.b + ")";
    }
}
