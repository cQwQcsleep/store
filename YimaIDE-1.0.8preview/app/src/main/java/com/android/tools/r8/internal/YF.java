package com.android.tools.r8.internal;

import java.util.function.BiPredicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class YF extends K2 {
    public final AbstractC2928wG c;

    public YF(AbstractC3114yW abstractC3114yW, AbstractC2928wG abstractC2928wG) {
        super(abstractC3114yW);
        this.c = abstractC2928wG;
    }

    public final boolean a(String str, Boolean bool) {
        X1 x1;
        str.getClass();
        switch (str) {
            case "PROTECTED":
                x1 = X1.c;
                break;
            case "PUBLIC":
                x1 = X1.b;
                break;
            case "PRIVATE":
                x1 = X1.e;
                break;
            case "PACKAGE_PRIVATE":
                x1 = X1.d;
                break;
            default:
                x1 = null;
                break;
        }
        if (x1 != null) {
            AbstractC2928wG abstractC2928wG = this.c;
            (bool.booleanValue() ? abstractC2928wG.a : abstractC2928wG.b).add(x1);
            abstractC2928wG.getClass();
            return true;
        }
        switch (str) {
            case "STATIC":
                this.c.c = AbstractC1484fP.a(bool.booleanValue());
                return true;
            case "SYNTHETIC":
                this.c.e = AbstractC1484fP.a(bool.booleanValue());
                return true;
            case "FINAL":
                this.c.d = AbstractC1484fP.a(bool.booleanValue());
                return true;
            default:
                return false;
        }
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public void a(String str, String str2, String str3) {
        if (str2.equals("Lcom/android/tools/r8/keepanno/annotations/MemberAccessFlags;")) {
            if (a(str3, new BiPredicate() { // from class: w1g
                @Override // java.util.function.BiPredicate
                public final boolean test(Object obj, Object obj2) {
                    return this.b.a((String) obj, (Boolean) obj2);
                }
            })) {
                return;
            }
            super.a(str, str2, str3);
            throw null;
        }
        super.a(str, str2, str3);
        throw null;
    }

    public static boolean a(String str, BiPredicate biPredicate) {
        if (!str.startsWith("NON_")) {
            return biPredicate.test(str, Boolean.TRUE);
        }
        return biPredicate.test(str.substring(4), Boolean.FALSE);
    }
}
