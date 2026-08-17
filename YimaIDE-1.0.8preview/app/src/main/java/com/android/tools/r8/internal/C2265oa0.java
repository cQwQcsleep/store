package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0005a;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.Reference;
import defpackage.f44;
import defpackage.n33;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oa0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2265oa0 {
    public ClassReference a;

    public C2265oa0(ClassReference classReference) {
        this.a = classReference;
    }

    public static C2265oa0 a(AbstractC1643hD abstractC1643hD) {
        String strG = abstractC1643hD.g();
        int iIndexOf = strG.indexOf(40);
        if (iIndexOf < 1 || !strG.endsWith(")")) {
            n33.a("Invalid formatted condition: ".concat(strG));
            return null;
        }
        String strSubstring = strG.substring(0, iIndexOf);
        String strA = AbstractC0005a.a(1, iIndexOf + 1, strG);
        if ("throws".equals(strSubstring)) {
            return a(strA);
        }
        n33.a("Unexpected condition: ".concat(strG));
        return null;
    }

    public final C2155nD a() {
        return new C2155nD("throws(" + this.a.getDescriptor() + ")");
    }

    public static C2265oa0 a(String str) {
        if (C0929Wj.z(str)) {
            return new C2265oa0(Reference.classFromDescriptor(str));
        }
        f44.a("Unexpected throws-descriptor: ", str);
        return null;
    }
}
