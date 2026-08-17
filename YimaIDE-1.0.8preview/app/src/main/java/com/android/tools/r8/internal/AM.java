package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0005a;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.position.TextPosition;
import defpackage.zhi;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class AM {
    public static Set<com.android.tools.r8.graph.I2> a(com.android.tools.r8.t0 t0Var, com.android.tools.r8.graph.B1 b1) {
        try {
            String strA = t0Var.a();
            Set<com.android.tools.r8.graph.I2> setC = AbstractC2780ub0.c();
            int i = 0;
            int i2 = 0;
            while (i < strA.length()) {
                i2++;
                int iIndexOf = strA.indexOf(10, i);
                if (iIndexOf == -1) {
                    iIndexOf = strA.length();
                }
                String strK = Wf0.k(strA.substring(i, iIndexOf));
                if (!strK.isEmpty()) {
                    try {
                        setC.add(a(strK, b1));
                    } catch (C0613Ke e) {
                        throw new C0613Ke(e.getMessage(), e, t0Var.getOrigin(), new TextPosition(i, i2, -1));
                    }
                }
                i = iIndexOf + 1;
            }
            return setC;
        } catch (ResourceException e2) {
            throw new C0613Ke(t0Var.getOrigin(), "Failed to parse main-dex resource", e2);
        }
    }

    public static com.android.tools.r8.graph.I2 a(String str, com.android.tools.r8.graph.B1 b1) {
        if (str.endsWith(".class")) {
            String strA = AbstractC0005a.a(6, 0, str);
            if (!strA.contains(".")) {
                return b1.e("L" + strA + ";");
            }
            zhi.a("Illegal main-dex-list entry '", str, "'.");
            return null;
        }
        zhi.a("Illegal main-dex-list entry '", str, "'.");
        return null;
    }
}
