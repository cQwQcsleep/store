package defpackage;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class tbc$a {
    public final String a;
    public final String b;
    public final String c;

    public tbc$a(String str, String str2, String str3) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof tbc$a)) {
            return false;
        }
        tbc$a tbc_a = (tbc$a) obj;
        return Intrinsics.areEqual(this.a, tbc_a.a) && Intrinsics.areEqual(this.b, tbc_a.b) && Intrinsics.areEqual(this.c, tbc_a.c);
    }

    public int hashCode() {
        return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    public String toString() {
        return "Grant(unlockId=" + this.a + ", ticketId=" + this.b + ", nonce=" + this.c + ")";
    }
}
