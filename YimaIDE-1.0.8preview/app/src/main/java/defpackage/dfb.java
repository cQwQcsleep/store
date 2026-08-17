package defpackage;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class dfb {
    public static final int g = 8;
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final List f;

    public dfb(String str, String str2, String str3, String str4, String str5, List list) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        str4.getClass();
        str5.getClass();
        list.getClass();
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = list;
    }

    public static /* synthetic */ dfb b(dfb dfbVar, String str, String str2, String str3, String str4, String str5, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = dfbVar.a;
        }
        if ((i & 2) != 0) {
            str2 = dfbVar.b;
        }
        if ((i & 4) != 0) {
            str3 = dfbVar.c;
        }
        if ((i & 8) != 0) {
            str4 = dfbVar.d;
        }
        if ((i & 16) != 0) {
            str5 = dfbVar.e;
        }
        if ((i & 32) != 0) {
            list = dfbVar.f;
        }
        String str6 = str5;
        List list2 = list;
        return dfbVar.a(str, str2, str3, str4, str6, list2);
    }

    public final dfb a(String str, String str2, String str3, String str4, String str5, List list) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        str4.getClass();
        str5.getClass();
        list.getClass();
        return new dfb(str, str2, str3, str4, str5, list);
    }

    public final String c() {
        String str = this.c;
        return StringsKt.isBlank(str) ? this.b : str;
    }

    public final List d() {
        return this.f;
    }

    public final String e() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof dfb)) {
            return false;
        }
        dfb dfbVar = (dfb) obj;
        return Intrinsics.areEqual(this.a, dfbVar.a) && Intrinsics.areEqual(this.b, dfbVar.b) && Intrinsics.areEqual(this.c, dfbVar.c) && Intrinsics.areEqual(this.d, dfbVar.d) && Intrinsics.areEqual(this.e, dfbVar.e) && Intrinsics.areEqual(this.f, dfbVar.f);
    }

    public final String f() {
        return this.b;
    }

    public final String g() {
        return this.c;
    }

    public final String h() {
        return this.d;
    }

    public int hashCode() {
        return (((((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode();
    }

    public final String i() {
        return this.e;
    }

    public String toString() {
        return "ProjectCardUiState(id=" + this.a + ", name=" + this.b + ", nickname=" + this.c + ", packageName=" + this.d + ", type=" + this.e + ", files=" + this.f + ")";
    }

    public /* synthetic */ dfb(String str, String str2, String str3, String str4, String str5, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "android" : str5, (i & 32) != 0 ? CollectionsKt.listOf("AndroidManifest.xml") : list);
    }
}
