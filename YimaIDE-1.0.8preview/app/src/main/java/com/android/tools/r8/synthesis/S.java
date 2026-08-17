package com.android.tools.r8.synthesis;

import com.android.tools.r8.Version;
import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC1016Zs;
import com.android.tools.r8.internal.AbstractC1103at;
import com.android.tools.r8.internal.AbstractC1960l;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.InterfaceC0990Ys;
import com.android.tools.r8.internal.QN;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.Reference;
import defpackage.x0g;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class S {
    public static final /* synthetic */ boolean T = true;
    public final b A;
    public final b B;
    public final b C;
    public final b D;
    public final b E;
    public final b F;
    public final b G;
    public final b H;
    public final b I;
    public final b J;
    public final b K;
    public final b L;
    public final b M;
    public final b N;
    public final b O;
    public final b P;
    public final b Q;
    public final ArrayList R;
    public String S;
    public final b a;
    public final b b;
    public final b c;
    public final b d;
    public final b e;
    public final b f;
    public final b g;
    public final b h;
    public final b i;
    public final b j;
    public final b k;
    public final b l;
    public final b m;
    public final b n;
    public final b o;
    public final b p;
    public final b q;
    public final b r;
    public final b s;
    public final b t;
    public final b u;
    public final b v;
    public final b w;
    public final b x;
    public final b y;
    public final b z;

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class a {
        public static final a b = new a(0, "INTERNAL");
        public static final a c = new a(1, "EXTERNAL");

        public a(int i, String str) {
            super(str, i);
        }
    }

    public S() {
        Q q = new Q();
        int i = q.a;
        q.a = i + 1;
        this.a = q.a(new U(i, XmlPullParser.NO_NAMESPACE, true));
        int i2 = q.a;
        q.a = i2 + 1;
        this.b = q.a(new U(i2, XmlPullParser.NO_NAMESPACE, true));
        int i3 = q.a;
        q.a = i3 + 1;
        this.c = q.a(new U(i3, XmlPullParser.NO_NAMESPACE, true));
        int i4 = q.a;
        q.a = i4 + 1;
        this.d = q.a(new U(i4, XmlPullParser.NO_NAMESPACE, true));
        int i5 = q.a;
        q.a = i5 + 1;
        this.e = q.a(new U(i5, XmlPullParser.NO_NAMESPACE, false));
        int i6 = q.a;
        q.a = i6 + 1;
        this.f = q.a(new U(i6, XmlPullParser.NO_NAMESPACE, false));
        int i7 = q.a;
        q.a = i7 + 1;
        this.g = q.a(new U(i7, XmlPullParser.NO_NAMESPACE, false));
        this.h = q.a("$EnumUnboxingLocalUtility");
        this.i = q.a("$EnumUnboxingSharedUtility");
        this.j = q.a("$-CC");
        this.k = q.a("$-EL");
        this.l = q.a("RetargetClass");
        this.m = q.a("RetargetInterface");
        this.n = q.a("$Wrapper");
        this.o = q.a("$VivifiedWrapper");
        this.p = q.a("-IA");
        this.q = q.a("$EnumConversion");
        int i8 = q.a;
        q.a = i8 + 1;
        this.r = q.a(new T(i8, "Lambda", true));
        int i9 = q.a;
        q.a = i9 + 1;
        this.s = q.a(new T(i9, "ThreadLocal", true));
        int i10 = q.a;
        q.a = i10 + 1;
        this.t = q.a(new T(i10, "SharedSuper", false));
        int i11 = q.a;
        q.a = i11 + 1;
        this.u = q.a(new T(i11, "$IA", false));
        int i12 = q.a;
        q.a = i12 + 1;
        this.v = q.a(new T(i12, "$Condy", false));
        this.w = q.c("CheckNotZero");
        this.x = q.c("Record");
        this.y = q.c("Backport");
        this.z = q.b("BackportWithForwarding");
        this.A = q.b("StaticInterfaceCall");
        this.B = q.c("ToStringIfNotNull");
        this.C = q.c("ThrowCCEIfNotNull");
        this.D = q.c("ThrowIAE");
        this.E = q.c("ThrowICCE");
        this.F = q.c("ThrowNSME");
        this.G = q.c("ThrowRTE");
        this.H = q.c("TwrCloseResource");
        this.I = q.c("ServiceLoad");
        this.J = q.b("Outline");
        this.K = q.b("CovariantOutline");
        this.L = q.b("APIConversion");
        this.M = q.b("APIConversionParameters");
        this.N = q.b("$CollectionConversion");
        this.O = q.c("ApiModelOutline");
        this.P = q.b("ApiModelOutline");
        this.Q = q.b("DesugaredLibraryBridge");
        this.S = null;
        ArrayList arrayList = q.b;
        q.b = null;
        this.R = arrayList;
    }

    public static String b(b bVar, I2 i2) {
        String strY0 = i2.Y0();
        if (bVar.e()) {
            return strY0;
        }
        int iLastIndexOf = strY0.lastIndexOf(bVar.d() ? bVar.c : "$$");
        if (iLastIndexOf >= 0) {
            return strY0.substring(0, iLastIndexOf);
        }
        x0g.a("Unexpected failure to compute a synthetic prefix for ".concat(strY0));
        return null;
    }

    public final String a() {
        if (this.S == null) {
            int i = AbstractC1103at.a;
            InterfaceC0990Ys interfaceC0990YsA = AbstractC1016Zs.a.a();
            String versionString = Version.getVersionString();
            com.android.tools.r8.internal.E e = (com.android.tools.r8.internal.E) interfaceC0990YsA;
            e.a(versionString.toString().getBytes(StandardCharsets.UTF_8));
            for (b bVar : this.R) {
                AbstractC1960l abstractC1960l = (AbstractC1960l) interfaceC0990YsA;
                abstractC1960l.a.putInt(bVar.b);
                abstractC1960l.b(4);
                String strB = bVar.b();
                e.a(strB.toString().getBytes(StandardCharsets.UTF_8));
                bVar.a(interfaceC0990YsA);
            }
            this.S = ((QN) interfaceC0990YsA).a().toString();
        }
        return this.S;
    }

    public static abstract class b implements com.android.tools.r8.utils.structural.s<b> {
        public final int b;
        public final String c;

        public b(int i, String str) {
            this.b = i;
            this.c = str;
        }

        @Override // com.android.tools.r8.utils.structural.s, java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final int compareTo(b bVar) {
            return Integer.compare(this.b, bVar.b);
        }

        public abstract void a(InterfaceC0990Ys interfaceC0990Ys);

        public String b() {
            return this.c;
        }

        public final int c() {
            return this.b;
        }

        public abstract boolean d();

        public abstract boolean e();

        public final boolean equals(Object obj) {
            return com.android.tools.r8.utils.structural.k.a(this, obj);
        }

        public abstract boolean f();

        public abstract boolean g();

        public abstract boolean h();

        public final int hashCode() {
            return this.b;
        }

        public V a() {
            return null;
        }
    }

    public Collection<b> b() {
        return this.R;
    }

    public static String a(b bVar, I2 i2) {
        String str;
        if (!T && bVar.e()) {
            x1f.a();
            return null;
        }
        String strY0 = i2.Y0();
        if (bVar.d()) {
            str = bVar.c;
        } else {
            str = "$$ExternalSynthetic" + bVar.b();
        }
        int iLastIndexOf = strY0.lastIndexOf(str);
        if (iLastIndexOf >= 0) {
            return strY0.substring(0, iLastIndexOf);
        }
        x0g.a("Unexpected failure to determine the context of synthetic class: ".concat(strY0));
        return null;
    }

    public static I2 a(b bVar, C3502k c3502k, B1 b1) {
        if (T || bVar.d()) {
            return b1.e(a(XmlPullParser.NO_NAMESPACE, bVar, c3502k.b.A0(), XmlPullParser.NO_NAMESPACE));
        }
        x1f.a();
        return null;
    }

    public static String a(String str, b bVar, String str2, String str3) {
        return C0929Wj.l(str2 + str + bVar.c + str3);
    }

    public static boolean a(ClassReference classReference) {
        a(classReference.getDescriptor());
        return true;
    }

    public static void a(String str) {
        if (T || !str.contains("$$InternalSynthetic")) {
            return;
        }
        x01.a(str);
    }

    public static String a(a aVar) {
        if (!T && aVar == null) {
            x1f.a();
            return null;
        }
        if (aVar == a.b) {
            return "$$InternalSynthetic";
        }
        return "$$ExternalSynthetic";
    }

    public static ClassReference a(ClassReference classReference, b bVar, String str) {
        return Reference.classFromDescriptor(a("$$ExternalSynthetic", bVar, classReference.getBinaryName(), str));
    }

    public static boolean a(ClassReference classReference, a aVar, b bVar) {
        String typeName = classReference.getTypeName();
        if (bVar.d()) {
            if (T || aVar == null) {
                return classReference.getBinaryName().endsWith(bVar.c);
            }
            x1f.a();
            return false;
        }
        String strA = a(aVar);
        int iLastIndexOf = typeName.lastIndexOf(strA);
        if (iLastIndexOf >= 0) {
            if (a(bVar, typeName, iLastIndexOf, strA, aVar == a.c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(b bVar, String str, int i, String str2, boolean z) {
        int length = bVar.c.length() + str2.length() + i;
        if (length >= str.length()) {
            return false;
        }
        if (str.substring(i, length).equals(str2 + bVar.c)) {
            if (z) {
                String strSubstring = str.substring(length);
                if (!strSubstring.isEmpty()) {
                    if ('0' == strSubstring.charAt(0)) {
                        if (strSubstring.length() == 1) {
                        }
                    } else {
                        for (int i2 = 0; i2 < strSubstring.length(); i2++) {
                            if (Character.isDigit(strSubstring.charAt(i2))) {
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
}
