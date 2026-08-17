package defpackage;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ufb$g {
    public final String a;
    public final List b;
    public final List c;
    public final boolean d;
    public final boolean e;
    public final boolean f;

    public ufb$g(String str, List list, List list2, boolean z, boolean z2, boolean z3) {
        str.getClass();
        list.getClass();
        list2.getClass();
        this.a = str;
        this.b = list;
        this.c = list2;
        this.d = z;
        this.e = z2;
        this.f = z3;
    }

    public final String a() {
        return this.a;
    }

    public final List b() {
        return this.c;
    }

    public final List c() {
        return this.b;
    }

    public final boolean d() {
        return this.f;
    }

    public final boolean e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ufb$g)) {
            return false;
        }
        ufb$g ufb_g = (ufb$g) obj;
        return Intrinsics.areEqual(this.a, ufb_g.a) && Intrinsics.areEqual(this.b, ufb_g.b) && Intrinsics.areEqual(this.c, ufb_g.c) && this.d == ufb_g.d && this.e == ufb_g.e && this.f == ufb_g.f;
    }

    public final boolean f() {
        return this.e;
    }

    public int hashCode() {
        return (((((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + Boolean.hashCode(this.d)) * 31) + Boolean.hashCode(this.e)) * 31) + Boolean.hashCode(this.f);
    }

    public String toString() {
        return "WorkbenchSession(currentFilePath=" + this.a + ", openTabs=" + this.b + ", expandedDirectories=" + this.c + ", isEditorFullscreen=" + this.d + ", isFileTreeExpanded=" + this.e + ", isEditorExpanded=" + this.f + ")";
    }
}
