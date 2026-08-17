package defpackage;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class pbf$b {
    public final boolean a;
    public final int b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;

    public pbf$b(boolean z, int i, String str, String str2, String str3, String str4) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        str4.getClass();
        this.a = z;
        this.b = i;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
    }

    public final String a() {
        return this.d;
    }

    public final boolean b() {
        return this.a;
    }

    public final int c() {
        return this.b;
    }

    public final String d() {
        return this.c;
    }

    public final String e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof pbf$b)) {
            return false;
        }
        pbf$b pbf_b = (pbf$b) obj;
        return this.a == pbf_b.a && this.b == pbf_b.b && Intrinsics.areEqual(this.c, pbf_b.c) && Intrinsics.areEqual(this.d, pbf_b.d) && Intrinsics.areEqual(this.e, pbf_b.e) && Intrinsics.areEqual(this.f, pbf_b.f);
    }

    public final String f() {
        return this.e;
    }

    public int hashCode() {
        return (((((((((Boolean.hashCode(this.a) * 31) + Integer.hashCode(this.b)) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode();
    }

    public String toString() {
        return "UpdateInfo(forceUpdate=" + this.a + ", latestVersionCode=" + this.b + ", latestVersionName=" + this.c + ", downloadUrl=" + this.d + ", title=" + this.e + ", message=" + this.f + ")";
    }
}
