package defpackage;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class lcc$a {
    public final boolean a;
    public final String b;
    public final String c;

    public lcc$a(boolean z, String str, String str2) {
        str.getClass();
        str2.getClass();
        this.a = z;
        this.b = str;
        this.c = str2;
    }

    public final boolean a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof lcc$a)) {
            return false;
        }
        lcc$a lcc_a = (lcc$a) obj;
        return this.a == lcc_a.a && Intrinsics.areEqual(this.b, lcc_a.b) && Intrinsics.areEqual(this.c, lcc_a.c);
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.a) * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    public String toString() {
        return "Status(connected=" + this.a + ", label=" + this.b + ", lastError=" + this.c + ")";
    }

    public /* synthetic */ lcc$a(boolean z, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? "" : str, (i & 4) != 0 ? "" : str2);
    }
}
