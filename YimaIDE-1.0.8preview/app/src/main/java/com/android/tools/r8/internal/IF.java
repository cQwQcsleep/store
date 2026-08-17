package com.android.tools.r8.internal;

import java.util.function.BiPredicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class IF extends YF {
    public final C1816jG d;

    public IF(AbstractC3114yW abstractC3114yW, C1816jG c1816jG) {
        super(abstractC3114yW, c1816jG);
        this.d = c1816jG;
    }

    @Override // com.android.tools.r8.internal.YF, com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(String str, String str2, String str3) {
        if (!str2.equals("Lcom/android/tools/r8/keepanno/annotations/FieldAccessFlags;")) {
            super.a(str, str2, str3);
        }
        if (YF.a(str3, new BiPredicate() { // from class: sf6
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
        if (str.equals("VOLATILE")) {
            this.d.f = AbstractC1484fP.a(bool.booleanValue());
            return true;
        }
        if (!str.equals("TRANSIENT")) {
            return false;
        }
        this.d.g = AbstractC1484fP.a(bool.booleanValue());
        return true;
    }
}
