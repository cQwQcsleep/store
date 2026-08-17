package defpackage;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class fcc {
    public final String a;
    public final int b;
    public final String c;
    public final String d;

    public fcc(String str, int i, String str2, String str3) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        this.a = str;
        this.b = i;
        this.c = str2;
        this.d = str3;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.d;
    }

    public final int c() {
        return this.b;
    }

    public final String d() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof fcc)) {
            return false;
        }
        fcc fccVar = (fcc) obj;
        return Intrinsics.areEqual(this.a, fccVar.a) && this.b == fccVar.b && Intrinsics.areEqual(this.c, fccVar.c) && Intrinsics.areEqual(this.d, fccVar.d);
    }

    public int hashCode() {
        return (((((this.a.hashCode() * 31) + Integer.hashCode(this.b)) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }

    public String toString() {
        return "RemoteServerConfig(host=" + this.a + ", port=" + this.b + ", username=" + this.c + ", password=" + this.d + ")";
    }
}
