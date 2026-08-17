package com.android.tools.r8.internal;

import com.android.tools.r8.Version;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Kg0 {
    public static boolean a(String str, String str2, boolean z) {
        if (str2 == null) {
            return z;
        }
        if (Wf0.c(str2)) {
            return false;
        }
        if (Wf0.d(str2)) {
            return true;
        }
        h0f.a("Expected value of ", str, " to be a boolean, but was: ", str2);
        return false;
    }

    public static Object a(Function function, Supplier supplier) {
        IX ix = MX.b;
        if (System.getProperty("com.android.tools.r8.startup.profile") != null) {
            return function.apply(System.getProperty("com.android.tools.r8.startup.profile"));
        }
        return supplier.get();
    }

    public static String a(String str) {
        if (Version.isDevelopmentVersion()) {
            return System.getProperty(str);
        }
        return null;
    }
}
