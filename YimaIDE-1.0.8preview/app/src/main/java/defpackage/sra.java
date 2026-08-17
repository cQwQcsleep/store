package defpackage;

import io.github.rosemoe.sora.widget.CodeEditor;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class sra {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final List g;
    public final List h;

    public /* synthetic */ sra(String str, String str2, String str3, String str4, String str5, String str6, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? CollectionsKt.emptyList() : list, (i & CodeEditor.FLAG_DRAW_SOFT_WRAP) != 0 ? CollectionsKt.emptyList() : list2);
    }

    public final String a() {
        return this.c;
    }

    public final List b() {
        return this.g;
    }

    public final String c() {
        return this.f;
    }

    public final String d() {
        return this.d;
    }

    public final String e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof sra)) {
            return false;
        }
        sra sraVar = (sra) obj;
        return Intrinsics.areEqual(this.a, sraVar.a) && Intrinsics.areEqual(this.b, sraVar.b) && Intrinsics.areEqual(this.c, sraVar.c) && Intrinsics.areEqual(this.d, sraVar.d) && Intrinsics.areEqual(this.e, sraVar.e) && Intrinsics.areEqual(this.f, sraVar.f) && Intrinsics.areEqual(this.g, sraVar.g) && Intrinsics.areEqual(this.h, sraVar.h);
    }

    public final String f() {
        return this.a;
    }

    public final List g() {
        return this.h;
    }

    public final String h() {
        return this.b;
    }

    public int hashCode() {
        int iHashCode = ((((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31;
        String str = this.f;
        return ((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.g.hashCode()) * 31) + this.h.hashCode();
    }

    public String toString() {
        return "OssComponent(name=" + this.a + ", version=" + this.b + ", copyright=" + this.c + ", licenseId=" + this.d + ", licenseLabel=" + this.e + ", homepage=" + this.f + ", extraLicenseIds=" + this.g + ", sourceLinks=" + this.h + ")";
    }

    public sra(String str, String str2, String str3, String str4, String str5, String str6, List list, List list2) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        str4.getClass();
        str5.getClass();
        list.getClass();
        list2.getClass();
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = list;
        this.h = list2;
    }
}
