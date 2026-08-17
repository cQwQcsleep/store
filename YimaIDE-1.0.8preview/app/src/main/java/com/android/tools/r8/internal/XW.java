package com.android.tools.r8.internal;

import java.util.logging.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class XW {
    public static final VW a;

    static {
        Logger.getLogger(XW.class.getName());
        a = new VW();
    }

    public static String a(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return str;
    }
}
