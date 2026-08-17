package com.android.tools.r8.internal;

import com.android.tools.r8.DataResource;
import com.android.tools.r8.internal.AbstractC3035xa0;
import com.android.tools.r8.internal.C1306dH;
import com.android.tools.r8.internal.C1476fH;
import com.android.tools.r8.internal.CE;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xa0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3035xa0 {
    public static final /* synthetic */ boolean a = true;

    public static void a(FG fg, C2949wa0 c2949wa0, C1902kH c1902kH) {
        fg.getClass();
        if (fg == EG.d) {
            c2949wa0.b("*").a(";");
            return;
        }
        if (!fg.e().b()) {
            c2949wa0.a("@");
            b(c2949wa0, (C1476fH) fg.e().a());
            c2949wa0.a(" ");
        }
        if (!fg.h()) {
            if (!fg.f()) {
                if (!a && fg.d().a() && fg.e().b()) {
                    x1f.a();
                    return;
                } else {
                    a(c2949wa0, fg.d());
                    c2949wa0.b("*").a(";");
                    return;
                }
            }
            C2158nG c2158nGB = fg.b();
            C1901kG c1901kG = c2158nGB.c;
            a(c2949wa0, c1901kG);
            a(c2949wa0, c1901kG.f, "volatile");
            a(c2949wa0, c1901kG.g, "transient");
            a(c2949wa0.a(c1902kH.a()), ((C2244oG) c2158nGB.e).a);
            c2949wa0.a(" ");
            C1988lH c1988lH = c2158nGB.d.a;
            String str = c1988lH.a;
            if (str != null) {
                c2949wa0.a(str);
            } else {
                String str2 = c1988lH.b;
                if (str2 != null) {
                    c2949wa0.a(str2);
                }
                c2949wa0.d();
                String str3 = c1988lH.c;
                if (str3 != null) {
                    c2949wa0.a(str3);
                }
            }
            c2949wa0.a(";");
            return;
        }
        NG ngC = fg.c();
        HG hg = ngC.c;
        a(c2949wa0, hg);
        a(c2949wa0, hg.f, "synchronized");
        a(c2949wa0, hg.g, "bridge");
        a(c2949wa0, hg.h, "native");
        a(c2949wa0, hg.i, "abstract");
        a(c2949wa0, hg.j, "strictfp");
        C2949wa0 c2949wa0A = c2949wa0.a(c1902kH.c());
        QG qg = ngC.e;
        qg.getClass();
        if (qg instanceof PG) {
            c2949wa0A.a("void");
        } else {
            a(c2949wa0A, qg.a());
        }
        c2949wa0.a(" ");
        C1988lH c1988lH2 = ngC.d.a;
        String str4 = c1988lH2.a;
        if (str4 != null) {
            c2949wa0.a(str4);
        } else {
            String str5 = c1988lH2.b;
            if (str5 != null) {
                c2949wa0.a(str5);
            }
            c2949wa0.d();
            String str6 = c1988lH2.c;
            if (str6 != null) {
                c2949wa0.a(str6);
            }
        }
        LG lg = ngC.f;
        lg.getClass();
        if (lg instanceof JG) {
            c2949wa0.a(false).a();
        } else {
            c2949wa0.a("(");
            List listA = lg.a();
            for (int i = 0; i < listA.size(); i++) {
                if (i > 0) {
                    c2949wa0.a(", ");
                }
                a(c2949wa0.a(c1902kH.b()), (AbstractC2587sH) listA.get(i));
            }
            c2949wa0.a(")");
        }
        c2949wa0.a(";");
    }

    public static C2949wa0 b(C2949wa0 c2949wa0, C1476fH c1476fH) {
        if (c1476fH.d()) {
            return c2949wa0.b();
        }
        YG yg = c1476fH.a;
        if (yg.b()) {
            c2949wa0.b().a(".");
        } else if (!yg.d()) {
            if (!a && !yg.c()) {
                x1f.a();
                return null;
            }
            c2949wa0.a(yg.a()).a(".");
        }
        AbstractC2843vH abstractC2843vH = c1476fH.b;
        if (abstractC2843vH.b()) {
            return c2949wa0.d();
        }
        if (a || abstractC2843vH.c()) {
            return c2949wa0.a(abstractC2843vH.a().a);
        }
        x1f.a();
        return null;
    }

    public static String c(String str) {
        Object obj;
        String str2;
        char[] charArray = str.toCharArray();
        int i = 0;
        while (i < charArray.length) {
            char c = charArray[i];
            if (c == '\n') {
                obj = "\\n";
            } else {
                obj = c == '\r' ? "\\r" : null;
            }
            if (obj != null) {
                StringBuilder sb = new StringBuilder(str.substring(0, i));
                while (i < charArray.length) {
                    char c2 = charArray[i];
                    if (c2 == '\n') {
                        str2 = "\\n";
                    } else {
                        str2 = c2 == '\r' ? "\\r" : null;
                    }
                    if (str2 != null) {
                        sb.append(str2);
                    } else {
                        sb.append(c2);
                    }
                    i++;
                }
                return sb.toString();
            }
            i++;
        }
        return str;
    }

    public static String b(String str) {
        int length = str.length() - 1;
        if (str.charAt(0) == 'L' && str.charAt(length) == ';') {
            return str.substring(1, length).replace(DataResource.SEPARATOR, '.');
        }
        defpackage.l0.a("Invalid class descriptor: ".concat(str));
        return null;
    }

    public static void a(StringBuilder sb, ME me, BiConsumer biConsumer) {
        AbstractC2515rV abstractC2515rV = me.c;
        if (!abstractC2515rV.b()) {
            sb.append("@");
            b(new C2949wa0(sb), (C1476fH) abstractC2515rV.a());
            sb.append(" ");
        }
        sb.append("class ");
        biConsumer.accept(sb, me.a);
        C2416qG c2416qG = (C2416qG) me.b;
        if (c2416qG.a.d()) {
            return;
        }
        sb.append(" extends ");
        b(new C2949wa0(sb), c2416qG.a);
    }

    public static C2949wa0 a(final C2949wa0 c2949wa0, AbstractC2587sH abstractC2587sH) {
        Objects.requireNonNull(c2949wa0);
        return (C2949wa0) abstractC2587sH.a(new Supplier() { // from class: asi
            @Override // java.util.function.Supplier
            public final Object get() {
                return c2949wa0.e();
            }
        }, new Function() { // from class: bsi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC3035xa0.a(c2949wa0, (C1306dH) obj);
            }
        }, new Function() { // from class: csi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC3035xa0.a(c2949wa0, (CE) obj);
            }
        }, new Function() { // from class: dsi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC3035xa0.b(c2949wa0, (C1476fH) obj);
            }
        });
    }

    public static C2949wa0 a(C2949wa0 c2949wa0, C1306dH c1306dH) {
        String strB;
        c1306dH.getClass();
        if (c1306dH == C1306dH.b) {
            return c2949wa0.c();
        }
        String string = Character.toString(c1306dH.a());
        if (!string.isEmpty()) {
            if (string.length() == 1) {
                strB = a(string.charAt(0));
            } else if (string.charAt(0) == '[') {
                strB = a(string);
            } else {
                strB = b(string);
            }
            return c2949wa0.a(strB);
        }
        defpackage.l0.a("Invalid empty type descriptor");
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
    
        if ((r0 instanceof com.android.tools.r8.internal.C2245oH) != false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static C2949wa0 a(C2949wa0 c2949wa0, CE ce) {
        if (!a) {
            ce.getClass();
            if (CE.c.equals(ce)) {
                if (ce.b == 1) {
                    AbstractC2587sH abstractC2587sH = ce.a;
                    abstractC2587sH.getClass();
                }
                x1f.a();
                return null;
            }
        }
        a(c2949wa0, ce.a);
        for (int i = 0; i < ce.b; i++) {
            c2949wa0.a("[]");
        }
        return c2949wa0;
    }

    public static void a(C2949wa0 c2949wa0, C3014xG c3014xG) {
        if (c3014xG.a()) {
            return;
        }
        if (!X1.a(c3014xG.a)) {
            Set set = c3014xG.a;
            boolean zContains = set.contains(X1.d);
            for (X1 x1 : (X1[]) X1.g.clone()) {
                if (!x1.equals(X1.d) && (!zContains) == set.contains(x1)) {
                    if (zContains) {
                        c2949wa0.a("!");
                    }
                    c2949wa0.a(x1.a()).a(" ");
                }
            }
        }
        a(c2949wa0, c3014xG.b, "static");
        a(c2949wa0, c3014xG.c, "final");
        a(c2949wa0, c3014xG.d, "synthetic");
    }

    public static void a(C2949wa0 c2949wa0, AbstractC1484fP abstractC1484fP, String str) {
        abstractC1484fP.getClass();
        if (abstractC1484fP instanceof C1230cP) {
            return;
        }
        if (abstractC1484fP instanceof C1398eP) {
            c2949wa0.a("!");
        }
        c2949wa0.a(str).a(" ");
    }

    public static String a(char c) {
        if (c == 'B') {
            return "byte";
        }
        if (c == 'D') {
            return "double";
        }
        if (c == 'F') {
            return "float";
        }
        if (c == 'S') {
            return "short";
        }
        if (c == 'Z') {
            return "boolean";
        }
        if (c == 'I') {
            return "int";
        }
        if (c == 'J') {
            return "long";
        }
        throw new C2499rF("Invalid primitive descriptor: " + c);
    }

    public static String a(String str) {
        String strB;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '[') {
                StringBuilder sb = new StringBuilder();
                String strSubstring = str.substring(i);
                if (!strSubstring.isEmpty()) {
                    if (strSubstring.length() == 1) {
                        strB = a(strSubstring.charAt(0));
                    } else if (strSubstring.charAt(0) == '[') {
                        strB = a(strSubstring);
                    } else {
                        strB = b(strSubstring);
                    }
                    sb.append(strB);
                    for (int i2 = 0; i2 < i; i2++) {
                        sb.append("[]");
                    }
                    return sb.toString();
                }
                defpackage.l0.a("Invalid empty type descriptor");
                return null;
            }
        }
        defpackage.l0.a("Invalid array descriptor: ".concat(str));
        return null;
    }
}
