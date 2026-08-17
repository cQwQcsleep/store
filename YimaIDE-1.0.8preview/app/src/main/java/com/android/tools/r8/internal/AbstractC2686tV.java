package com.android.tools.r8.internal;

import defpackage.obi;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tV, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2686tV {
    public static final /* synthetic */ boolean a = true;

    public static String a(C2600sV c2600sV, String str, String str2) {
        String strA = c2600sV.a();
        if (!strA.equals(str) && !strA.equals(str2)) {
            if (!strA.startsWith(str) || strA.charAt(str.length()) != '=') {
                return null;
            }
            c2600sV.b();
            return strA.substring(str.length() + 1);
        }
        String strB = c2600sV.b();
        if (strB != null) {
            c2600sV.b();
            return strB;
        }
        obi.a("Missing argument for '", strA, "'.");
        return null;
    }

    public static Boolean a(C2600sV c2600sV, String str) {
        if (c2600sV.a().equals(str)) {
            c2600sV.b();
            return Boolean.TRUE;
        }
        if (!a && !str.startsWith("--")) {
            x1f.a();
            return null;
        }
        if (!c2600sV.a().equals("--no".concat(str.substring(2)))) {
            return null;
        }
        c2600sV.b();
        return Boolean.FALSE;
    }
}
