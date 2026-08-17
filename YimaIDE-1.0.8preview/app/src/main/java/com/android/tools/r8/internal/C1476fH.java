package com.android.tools.r8.internal;

import com.android.tools.r8.DataResource;
import defpackage.kx5;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1476fH {
    public static final /* synthetic */ boolean c = true;
    public final YG a;
    public final AbstractC2843vH b;

    public C1476fH(YG yg, AbstractC2843vH abstractC2843vH) {
        boolean z = c;
        if (!z && yg == null) {
            x1f.a();
            throw null;
        }
        if (!z && abstractC2843vH == null) {
            x1f.a();
            throw null;
        }
        this.a = yg;
        this.b = abstractC2843vH;
    }

    public static C1476fH a(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf != 0) {
            return iLastIndexOf > 0 ? new C1390eH().a(YG.a(str.substring(0, iLastIndexOf))).a(AbstractC2843vH.a(str.substring(iLastIndexOf + 1))).a() : new C1390eH().a(YG.e()).a(AbstractC2843vH.a(str)).a();
        }
        kx5.a("Unexpected '.' at index 0 in '", str);
        return null;
    }

    public static C1476fH b(String str) {
        if (str.startsWith("L") || !str.endsWith(";")) {
            return a(str.substring(1, str.length() - 1).replace(DataResource.SEPARATOR, '.'));
        }
        defpackage.l0.a("Invalid class descriptor: ".concat(str));
        return null;
    }

    public final String c() {
        if (!e()) {
            defpackage.l0.a("Attempt to obtain exact qualified type for inexact pattern");
            return null;
        }
        return "L" + this.a.a().replace('.', DataResource.SEPARATOR) + (this.a.d() ? XmlPullParser.NO_NAMESPACE : "/") + this.b.a().d() + ";";
    }

    public final boolean d() {
        return this.a.b() && this.b.b();
    }

    public final boolean e() {
        return this.a.c() && this.b.c();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && C1476fH.class == obj.getClass()) {
            C1476fH c1476fH = (C1476fH) obj;
            if (this.a.equals(c1476fH.a) && this.b.equals(c1476fH.b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.a.hashCode()), Integer.valueOf(this.b.hashCode()));
    }

    public final String toString() {
        YG yg = this.a;
        return yg + (yg.d() ? XmlPullParser.NO_NAMESPACE : ".") + this.b;
    }

    public static C1390eH b() {
        return new C1390eH();
    }

    public static C1476fH a() {
        return new C1476fH(UG.a, C2672tH.a);
    }
}
