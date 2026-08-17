package com.android.tools.r8.internal;

import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class DM {
    public static com.android.tools.r8.naming.Q a(com.android.tools.r8.naming.Q q, Object obj, Function function) {
        if (obj == null) {
            return q;
        }
        com.android.tools.r8.naming.Q q2 = (com.android.tools.r8.naming.Q) function.apply(obj);
        return q == null ? q2 : new CM(q, q2);
    }
}
