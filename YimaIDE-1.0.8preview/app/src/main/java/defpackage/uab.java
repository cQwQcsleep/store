package defpackage;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.vector.ImageVector;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class uab {
    public final tab a;
    public final String b;
    public final ImageVector c;
    public final long d;
    public final boolean e;

    public uab(tab tabVar, String str, ImageVector imageVector, long j, boolean z) {
        tabVar.getClass();
        str.getClass();
        imageVector.getClass();
        this.a = tabVar;
        this.b = str;
        this.c = imageVector;
        this.d = j;
        this.e = z;
    }

    public final boolean a() {
        return this.e;
    }

    public final ImageVector b() {
        return this.c;
    }

    public final long c() {
        return this.d;
    }

    public final tab d() {
        return this.a;
    }

    public final String e() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof uab)) {
            return false;
        }
        uab uabVar = (uab) obj;
        return this.a == uabVar.a && Intrinsics.areEqual(this.b, uabVar.b) && Intrinsics.areEqual(this.c, uabVar.c) && Color.equals-impl0(this.d, uabVar.d) && this.e == uabVar.e;
    }

    public int hashCode() {
        return (((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + Color.hashCode-impl(this.d)) * 31) + Boolean.hashCode(this.e);
    }

    public String toString() {
        return "ProfileMenuItem(id=" + this.a + ", title=" + this.b + ", icon=" + this.c + ", iconTint=" + Color.toString-impl(this.d) + ", danger=" + this.e + ")";
    }

    public /* synthetic */ uab(tab tabVar, String str, ImageVector imageVector, long j, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(tabVar, str, imageVector, j, z);
    }

    public /* synthetic */ uab(tab tabVar, String str, ImageVector imageVector, long j, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(tabVar, str, imageVector, j, (i & 16) != 0 ? false : z, null);
    }
}
