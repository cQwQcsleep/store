package com.android.tools.r8.internal;

import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2587sH {
    public static AbstractC2587sH a(String str) {
        char cCharAt = str.charAt(0);
        int i = 1;
        if (cCharAt == 'L') {
            if (str.charAt(str.length() - 1) == ';') {
                return new C2417qH(C1476fH.b(str));
            }
            defpackage.l0.a("Invalid type descriptor: ".concat(str));
            return null;
        }
        if (cCharAt == '[') {
            while (str.charAt(i) == '[') {
                i++;
            }
            return new C2331pH(new CE(a(str.substring(i)), i));
        }
        C2501rH c2501rH = (C2501rH) C2501rH.b.get(str);
        if (c2501rH != null) {
            return c2501rH;
        }
        defpackage.l0.a("Invalid type descriptor: ".concat(str));
        return null;
    }

    public abstract Object a(Supplier supplier, Function function, Function function2, Function function3);
}
