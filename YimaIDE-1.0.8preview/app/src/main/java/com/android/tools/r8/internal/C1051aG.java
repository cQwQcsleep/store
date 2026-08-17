package com.android.tools.r8.internal;

import java.util.function.BiPredicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.aG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1051aG extends YF {
    public final GG d;

    public C1051aG(AbstractC3114yW abstractC3114yW, GG gg) {
        super(abstractC3114yW, gg);
        this.d = gg;
    }

    @Override // com.android.tools.r8.internal.YF, com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(String str, String str2, String str3) {
        if (!str2.equals("Lcom/android/tools/r8/keepanno/annotations/MethodAccessFlags;")) {
            super.a(str, str2, str3);
        }
        if (YF.a(str3, new BiPredicate() { // from class: obg
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return this.b.b((String) obj, (Boolean) obj2);
            }
        })) {
            return;
        }
        super.a(str, "Lcom/android/tools/r8/keepanno/annotations/MemberAccessFlags;", str3);
    }

    public final boolean b(String str, Boolean bool) {
        str.getClass();
        switch (str) {
            case "NATIVE":
                this.d.h = AbstractC1484fP.a(bool.booleanValue());
                return true;
            case "ABSTRACT":
                this.d.i = AbstractC1484fP.a(bool.booleanValue());
                return true;
            case "STRICT_FP":
                this.d.j = AbstractC1484fP.a(bool.booleanValue());
                return true;
            case "SYNCHRONIZED":
                this.d.f = AbstractC1484fP.a(bool.booleanValue());
                return true;
            case "BRIDGE":
                this.d.g = AbstractC1484fP.a(bool.booleanValue());
                return true;
            default:
                return false;
        }
    }
}
